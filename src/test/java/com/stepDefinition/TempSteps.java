package com.stepDefinition;

import com.pages.TempPage;
import com.utilityFunctions.FunctionalLiberary;
//import com.utilityFunctions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TempSteps extends FunctionalLiberary {
	TempPage tmp=new TempPage();
	
	@Given("^user navigate to url$")
	public void user_navigate_to_url() throws Throwable {
		openbrowser();
		geturl();
		
		System.out.println("Given url");
	}

	@When("^user enters Credentials$")
	public void user_enters_Credentials() throws Throwable {
		TempPage tmp=new TempPage();
		type(tmp.getSearchText(), "Jesus");
		System.out.println("Entered Cred");
	}

	@Then("^user validates$")
	public void user_validates() throws Throwable {
//		click(tmp.getSearchButon());
	}

}
