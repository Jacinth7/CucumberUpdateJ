package com.stepDefinition;

import java.util.List;

import com.pages.TempPage;
//import com.ObjectRepository.HealthcareVolunteer;
import com.utilityFunctions.FunctionalLiberary;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



public class HealthCareSteps extends FunctionalLiberary {


	@Given("user navigate to home")
	public void user_navigate_to_home() {
		System.out.println("HC home");
	}
//	@When("user enters data")
//	public void user_enters_data(Map<String, String> data) throws Throwable  {
//		System.out.println("hc data");
//		System.out.println("Username: "+data.get("Username"));
//		System.out.println("Password: "+data.get("Password"));
//	}
	
	@When("user enters data")
	public void user_enters_data(DataTable table) throws Throwable  {
		List<List<String>> data = table.cells();
		System.out.println("hc data");
		System.out.println("Username: "+data.get(0).get(1));
		System.out.println("Password: "+data.get(1).get(1));
	}
	




}
