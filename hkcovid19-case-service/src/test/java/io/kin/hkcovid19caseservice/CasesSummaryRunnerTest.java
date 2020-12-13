package io.kin.hkcovid19caseservice;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;

@RunWith(Cucumber.class)
@CucumberContextConfiguration
@SpringBootTest(classes = { Hkcovid19CaseServiceApplication.class,
		CasesSummaryRunnerTest.class }, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@CucumberOptions(plugin = { "pretty" }, features = "classpath:features")
public class CasesSummaryRunnerTest {

}
