package com.stepDefinition;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.utilityFunctions.FunctionalLiberary;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


public class Hooks extends FunctionalLiberary {
	
	
	
	@After
	public void tearDown(Scenario scenario) {
		System.out.println("hooks after");
		if(scenario.isFailed()) {
			System.out.println("hooks failed");
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			byte[] sourcePath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);

		}
	}
	
	
		
	
}
