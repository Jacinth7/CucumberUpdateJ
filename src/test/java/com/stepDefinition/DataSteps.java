package com.stepDefinition;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DataSteps {
	
//	@Given("^I verify the search result$")
//	public void i_verify_the_search_result() throws Throwable {
//	    Assert.assertEquals("", "");
//	}
//	

	@Given("User is on the home")
	public void user_is_on_the_home() {
	}
	

	@When("User enters the username {string} and password {int}")
	public void user_enters_the_username_and_password(String string, Integer int1) {
		System.out.println(string);
		System.out.println(int1);
	}



	@When("User gets the values for data")
	public void user_enters_the_date(Map<String, String> data) {
		System.out.println(data.get("Username"));
        System.out.println(data.get("Password"));

	}
	@Then("Validate the result")
	public void validate_the_result() {
	}
	

	@When("User get the values for datatable")
	public void user_enters_the_datatabMap(DataTable table) {
	//	List<List<String>> data = table.raw(); //old versions
		List<List<String>> data= table.cells();
		System.out.println(data.get(0).get(0));
        System.out.println(data.get(0).get(1));
        System.out.println(table.cell(2, 0));
        System.out.println(table.cell(2, 1));

	}
	
	@When("User get the values for datatable with map")
	public void user_enters_the_datatab(DataTable table) { //to get all data
		for(Map<String, String> data: table.asMaps(String.class, String.class)) {
//			driver.findElement(By.id("")).sendKeys(data.get("title"));
//			driver.findElement(By.id("")).sendKeys(data.get("amount"));
//			driver.findElement(By.id("")).click(); Login button
			System.out.println(data.get("title"));
			System.out.println(data.get("amount"));
			System.out.println(data.get("probability"));
			System.out.println(data.get("commission"));
		}
		

	}
	
	@When("User get the values for datatable with map separate")
	public void user_enters_the_datatabeSepatate(DataTable table) {
		List<Map<String, String>> data=table.asMaps();

			System.out.println(data.get(2).get("title"));
			System.out.println(data.get(2).get("amount"));
			System.out.println(data.get(2).get("probability"));
			System.out.println(data.get(2).get("commission"));
		}
	




}
