package com.testRunner;


import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;




@RunWith(Cucumber.class)
@CucumberOptions(features = "@target/failed_scenarios.txt", 
	
	glue = { "com.ebx.SmokeTest", "com.ebx.CTMS", "com.ebx.StepDefinition"  },
//	tags = { "@SmokeTest" },
	
//	@products @domains @demo
	dryRun = false, 
	monochrome = true,
			plugin={"html:target/cucumber.html", "json:target/cucumber.json",
					"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
					}
)

public class FailedTestRunner {
	

}
