package io.kin.hkcovid19caseservice.stepdefs;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.kin.hkcovid19caseservice.model.CasesSummary;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CaseSummaryTestStepDefs extends CommonStepDefs{



	@When("^the client calls /hkcovid19casessummary/([a-zA-Z]+)$")
	public void the_client_issues_POST_api_call(String functionName) throws Throwable {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Object> entity = new HttpEntity<Object>(headers);
		logger.info("Calling API :" + functionName);
		ResponseEntity<String> out = restTemplate.exchange(postUrl + ":" + port + "/hkcovid19casessummary/"+functionName,
				HttpMethod.POST, entity, String.class);
		responseStatusCode = out.getStatusCode().value();
		responseBody = out.getBody();
		assertNotNull(out.getBody());
	}

   @Then("^the client receives status code of (\\d*)$")
   public void the_client_receives_status_code(int statusCode) throws Throwable{
	   assertTrue(responseStatusCode == statusCode);
   }
   
   @Then("^the client receives a valid CaseSummay JSON$")
   public void the_client_receives_valid_caseSummay() throws Throwable{
	   try {
		   CasesSummary[] casesSummarys = objectMapper.readValue(responseBody, CasesSummary[].class);
		   logger.info(casesSummarys.length);
		   assertTrue(true);
	   }
	   catch(Exception e) {
		   assertTrue(false);
	   }
	  
   }
}
