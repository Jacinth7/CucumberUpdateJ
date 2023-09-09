package com.testRunner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;

import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;



//@RunWith(Cucumber.class)
@CucumberOptions(features = "src\\main\\resources\\Features", 
	
	glue = { "com.stepDefinition" },
	tags =  "@temp" ,
	
//	@products @domains @demo
	dryRun = false, 
	monochrome = true,
			plugin={"html:target/cucumber.html", "json:target/cucumber.json",
					"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
					"rerun:target/failed_scenarios.txt"}
)

public class TestRunnerParallel extends AbstractTestNGCucumberTests {
 
		
		@DataProvider(parallel=true)
		@Override
		public Object[][] scenarios()
		{
			return super.scenarios();
		}
		
}
