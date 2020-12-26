package io.kin.hkcovid19caseservice.resources;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.kin.hkcovid19caseservice.model.CasesRelatedBuilding;
import io.kin.hkcovid19caseservice.model.CasesRelatedBuildingDB;
import io.kin.hkcovid19caseservice.model.InsertResult;
import io.kin.hkcovid19caseservice.repository.CasesRelatedBuildingRepository;

@RestController
@RequestMapping("/hkcovid19caserelatedbuilding")
public class CasesRelatedBuildingResources {
	@Autowired
	private CasesRelatedBuildingRepository casesRelatedBuildingRepository;
	
	@RequestMapping(value = "/addMultiple", method = RequestMethod.POST, headers = "Accept=application/json")
	public List<CasesRelatedBuilding> addMultipleCase(
			@RequestBody List<CasesRelatedBuilding>  cList) {
		InsertResult result = new InsertResult();
		try {
			Collection<CasesRelatedBuildingDB> c = new ArrayList<CasesRelatedBuildingDB>();
			for(CasesRelatedBuilding cObj :cList) {
				c.add(cObj.toDBObj());
			}
			
			casesRelatedBuildingRepository.insertCase(c);
			result.successResult();
		}
		catch(Exception e) {
			e.printStackTrace();
			result.errorResult(e.getMessage());
		}

		return cList;
	}
	
	@RequestMapping("/getAll")
	public List<CasesRelatedBuilding> getAllCasesRelatedBuilding() {
		List<CasesRelatedBuildingDB> casesRelatedBuildingListDB = casesRelatedBuildingRepository.getAllCaseRelatedBuilding();
		List<CasesRelatedBuilding> casesRelatedBuildingList = new ArrayList<CasesRelatedBuilding>();
		for(CasesRelatedBuildingDB db :casesRelatedBuildingListDB) {
			casesRelatedBuildingList.add(db.toObj());
		}
		return casesRelatedBuildingList;
	}
	
	@RequestMapping("/getLatest")
	public List<CasesRelatedBuilding> getLatestCasesRelatedBuilding() {
		List<CasesRelatedBuildingDB> casesRelatedBuildingListDB = casesRelatedBuildingRepository.getLatestCaseRelatedBuilding();
		List<CasesRelatedBuilding> casesRelatedBuildingList = new ArrayList<CasesRelatedBuilding>();
		casesRelatedBuildingListDB.forEach(db -> casesRelatedBuildingList.add(db.toObj()));
		return casesRelatedBuildingList;
	}
	
	@RequestMapping("/getLatestDistrictCaseData")
	public Map<String, Long> getLatestDistrictCaseData() {
		return casesRelatedBuildingRepository.getLatestDistrictCaseData();
	}
}
