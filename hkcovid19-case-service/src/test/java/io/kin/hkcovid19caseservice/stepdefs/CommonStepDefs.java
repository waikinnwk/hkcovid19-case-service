package io.kin.hkcovid19caseservice.stepdefs;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonStepDefs {
	protected static final Logger logger = LogManager.getLogger(CommonStepDefs.class);
	protected RestTemplate restTemplate = new RestTemplate();
	protected ObjectMapper objectMapper  = new ObjectMapper();
	protected String postUrl = "http://localhost";
	@LocalServerPort
	protected int port;
	protected int responseStatusCode;
	protected String responseBody;
}
