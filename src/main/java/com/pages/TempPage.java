package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utilityFunctions.FunctionalLiberary;

public class TempPage {
	public TempPage() {
		PageFactory.initElements(FunctionalLiberary.driver, this);
	}
	
	
	@FindBy(name="q")
	private WebElement searchText;
	
	@FindBy(name="btnK")
	private WebElement searchButon;

	public WebElement getSearchText() {
		return searchText;
	}

	public WebElement getSearchButon() {
		return searchButon;
	}
	
	
	
}
