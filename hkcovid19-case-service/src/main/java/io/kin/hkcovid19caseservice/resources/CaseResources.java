package io.kin.hkcovid19caseservice.resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
}
