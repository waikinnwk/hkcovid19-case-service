package io.kin.hkcovid19caseservice.resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.kin.hkcovid19caseservice.model.CasesSummary;
import io.kin.hkcovid19caseservice.model.InsertResult;
import io.kin.hkcovid19caseservice.repository.CasesSummaryRepository;

@RestController
@RequestMapping("/hkcovid19casessummary")
public class CasesSummaryResources {
	@Autowired
	private CasesSummaryRepository casesSummaryRepository;

	@RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
	public InsertResult addCasesSummary(@RequestBody CasesSummary c) {
		InsertResult result = new InsertResult();
		try {
			if (!casesSummaryRepository.isExists(c)) {
				casesSummaryRepository.insertCasesSummary(c);
				result.successResult();
			} else
				result.duplicateResult();
		} catch (Exception e) {
			e.printStackTrace();
			result.errorResult(e.getMessage());
		}

		return result;
	}

	@RequestMapping(value = "/addMultiple", method = RequestMethod.POST, headers = "Accept=application/json")
	public InsertResult addMultipleCasesSummary(@RequestBody List<CasesSummary> cList) {
		InsertResult result = new InsertResult();
		try {
			Collection<CasesSummary> c = new ArrayList<CasesSummary>(cList);
			casesSummaryRepository.insertCasesSummary(c);
			result.successResult();
		} catch (Exception e) {
			e.printStackTrace();
			result.errorResult(e.getMessage());
		}

		return result;
	}

	@RequestMapping("/getAll")
	public List<CasesSummary> getAllCasesSummary() {
		List<CasesSummary> casesSummaryList = casesSummaryRepository.getAllCasesSummary();
		return casesSummaryList;
	}
}
