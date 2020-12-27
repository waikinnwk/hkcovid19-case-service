package io.kin.hkcovid19caseservice.resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.kin.hkcovid19caseservice.model.BuildingLocation;
import io.kin.hkcovid19caseservice.model.CasesRelatedBuilding;
import io.kin.hkcovid19caseservice.model.CasesRelatedBuildingDB;
import io.kin.hkcovid19caseservice.model.CasesRelatedBuildingWithCorrdinate;
import io.kin.hkcovid19caseservice.model.InsertResult;
import io.kin.hkcovid19caseservice.repository.CasesRelatedBuildingRepository;

@RestController
@RequestMapping("/hkcovid19caserelatedbuilding")
public class CasesRelatedBuildingResources {
	@Autowired
	private CasesRelatedBuildingRepository casesRelatedBuildingRepository;
	@Autowired
	private ObjectMapper objectMapper;

	@RequestMapping(value = "/addMultiple", method = RequestMethod.POST, headers = "Accept=application/json")
	public List<CasesRelatedBuilding> addMultipleCase(@RequestBody List<CasesRelatedBuilding> cList) {
		InsertResult result = new InsertResult();
		try {
			Collection<CasesRelatedBuildingDB> c = new ArrayList<CasesRelatedBuildingDB>();
			for (CasesRelatedBuilding cObj : cList) {
				c.add(cObj.toDBObj());
			}

			casesRelatedBuildingRepository.insertCase(c);
			result.successResult();
		} catch (Exception e) {
			e.printStackTrace();
			result.errorResult(e.getMessage());
		}

		return cList;
	}

	@RequestMapping("/getAll")
	public List<CasesRelatedBuilding> getAllCasesRelatedBuilding() {
		List<CasesRelatedBuildingDB> casesRelatedBuildingListDB = casesRelatedBuildingRepository
				.getAllCaseRelatedBuilding();
		List<CasesRelatedBuilding> casesRelatedBuildingList = new ArrayList<CasesRelatedBuilding>();
		for (CasesRelatedBuildingDB db : casesRelatedBuildingListDB) {
			casesRelatedBuildingList.add(db.toObj());
		}
		return casesRelatedBuildingList;
	}

	@RequestMapping("/getLatest")
	public List<CasesRelatedBuilding> getLatestCasesRelatedBuilding() {
		List<CasesRelatedBuildingDB> casesRelatedBuildingListDB = casesRelatedBuildingRepository
				.getLatestCaseRelatedBuilding();
		List<CasesRelatedBuilding> casesRelatedBuildingList = new ArrayList<CasesRelatedBuilding>();
		casesRelatedBuildingListDB.forEach(db -> casesRelatedBuildingList.add(db.toObj()));
		return casesRelatedBuildingList;
	}

	@RequestMapping("/getLatestWithCoordinate")
	public List<CasesRelatedBuildingWithCorrdinate> getLatestWithCoordinate() throws Exception {
		List<CasesRelatedBuildingDB> casesRelatedBuildingListDB = casesRelatedBuildingRepository
				.getLatestCaseRelatedBuilding();
		List<CasesRelatedBuilding> casesRelatedBuildingList = new ArrayList<CasesRelatedBuilding>();
		List<BuildingLocation> buildingLocationList = new ArrayList<BuildingLocation>();
		casesRelatedBuildingListDB.forEach(db -> {
			casesRelatedBuildingList.add(db.toObj());
			BuildingLocation bl = new BuildingLocation();
			bl.setDistrict(db.getDistrict());
			bl.setBuildingName(db.getBuildingName());
			bl.setLat(0.0);
			bl.setLon(0.0);
			buildingLocationList.add(bl);
		});

		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8090/buildinglocation/getCoordinate";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(objectMapper.writeValueAsString(buildingLocationList),headers);
		BuildingLocation[] coordinateResult = restTemplate.postForObject(url, entity, BuildingLocation[].class);
		Map<String, BuildingLocation> map = new HashMap<String, BuildingLocation>();
		for(BuildingLocation b:coordinateResult) {
			map.put(b.getDistrict()+ "_"+ b.getBuildingName(), b);
		}
		List<CasesRelatedBuildingWithCorrdinate> casesRelatedBuildingWithCorrdinateList = new ArrayList<CasesRelatedBuildingWithCorrdinate>();
		casesRelatedBuildingList.forEach(c->{
			CasesRelatedBuildingWithCorrdinate cc = new CasesRelatedBuildingWithCorrdinate(c.getAsOfDate(),
					c.getBuildingName(),
					c.getDistrict(),
					c.getLastDateOfResidenceOfTheCase(),
					c.getRelatedCase(),
					c.getRelatedCaseOth(),
					c.getNoOfCase());
			
			BuildingLocation b = map.get(c.getDistrict()+ "_"+ c.getBuildingName());
			if(b != null) {
				cc.setLat(b.getLat());
				cc.setLon(b.getLon());
			}
			casesRelatedBuildingWithCorrdinateList.add(cc);
		});
		
		return casesRelatedBuildingWithCorrdinateList;
	}

	@RequestMapping("/getLatestDistrictCaseData")
	public Map<String, Long> getLatestDistrictCaseData() {
		return casesRelatedBuildingRepository.getLatestDistrictCaseData();
	}
}
