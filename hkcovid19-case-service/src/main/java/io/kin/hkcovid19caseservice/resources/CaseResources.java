package io.kin.hkcovid19caseservice.resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.kin.hkcovid19caseservice.model.Case;
import io.kin.hkcovid19caseservice.model.InsertResult;
import io.kin.hkcovid19caseservice.repository.CaseRepository;

@RestController
@RequestMapping("/hkcovid19case")
public class CaseResources {
	@Autowired
	private CaseRepository caseRepository;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
	public InsertResult addCase(
			@RequestBody Case c) {
		InsertResult result = new InsertResult();
		try {
			if(!caseRepository.isExists(c)) {
				caseRepository.insertCase(c);
				result.successResult();
			}
			else
				result.duplicateResult();
		}
		catch(Exception e) {
			e.printStackTrace();
			result.errorResult(e.getMessage());
		}

		return result;
	}
	
	@RequestMapping(value = "/addMultiple", method = RequestMethod.POST, headers = "Accept=application/json")
	public InsertResult addMultipleCase(
			@RequestBody List<Case>  cList) {
		InsertResult result = new InsertResult();
		try {
			Collection<Case> c = new ArrayList<Case>(cList);
			caseRepository.insertCase(c);
			result.successResult();
		}
		catch(Exception e) {
			e.printStackTrace();
			result.errorResult(e.getMessage());
		}

		return result;
	}
	
	@RequestMapping("/getAll")
	public List<Case> getAllCase() {
		List<Case> caseList = caseRepository.getAllCase();
	    return caseList;
	}	
	
	@RequestMapping("/getCaseBySymptomatic")
	public Map<String,Integer> getCaseBySymptomatic() {
		Map<String,Integer> resultMap = new HashMap<String,Integer>();
		List<Case> caseList = caseRepository.getAllCase();
		Integer s = 0;
		Integer as = 0;
		for(Case c : caseList) {
			if("Asymptomatic".equalsIgnoreCase(c.getOnsetDate()))
				as++;
			else
				s++;
		}
		resultMap.put("asymptomatic_case", as);
		resultMap.put("symptomatic_case", s);
		
	    return resultMap;
	}	
}
