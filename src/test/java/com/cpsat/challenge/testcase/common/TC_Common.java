package com.cpsat.challenge.testcase.common;



import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import browser.BrowserType;
import wrappers.GenericHandlers;

public class TC_Common extends GenericHandlers {

	public SoftAssertions softAssertions;
	public static BrowserType browser;
	
	@Before
	public void setUp() {
		invokeApp(browser);
		softAssertions = new SoftAssertions();
	}
	
	@After
	public void tearDown() {
		softAssertions.assertAll();
		closeBrowser();
	}
	
	@BeforeMethod
	public void setUpTestNG() {
		invokeApp(browser);
	}
	
	@AfterMethod
	public void tearDownTestNG() {
		closeBrowser();
	}
}
