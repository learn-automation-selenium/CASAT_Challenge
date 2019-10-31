package com.cpsat.challenge.testcase;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cpsat.challenge.pages.nseindia.CompanyDetailsPage;
import com.cpsat.challenge.pages.nseindia.NSEIndexPage;
import com.cpsat.challenge.testcase.common.TC_Common;

import configreader.ObjectRepository;
import configreader.PropertyFileReader;

public class NseIndiaShareValueSingleCompanyTest extends TC_Common{

	public static String companyName = "Eicher Motors Limited";
	
	@BeforeClass
	public static void setData() {
		ObjectRepository.reader = new PropertyFileReader();
		browser = ObjectRepository.reader.getFirefoxBrowser();
		url = "https://www.nseindia.com/";
	}
	
	@Test
	public void minNumbetTest() {
		
		// verify company name
		CompanyDetailsPage companyDetails = new NSEIndexPage(driver).searchCompanyDetails(companyName);
		
		String cName = companyDetails.getCompanyName();
		Assert.assertEquals(cName, companyName, "Company name didn't matched");
		
		//take screenshot
		takeSnap(companyName);
		
		// fetch the following and print in the console
		//1. Face Value
		String faceValue = companyDetails.getFaceValue();
		System.out.println(faceValue);
		
		//2. 52 week high
		String high52WeekValue = companyDetails.get52WeekHigh();
		System.out.println(high52WeekValue);
		
		//3. 52 week low
		String low52WeekValue = companyDetails.get52WeekLow();
		System.out.println(low52WeekValue);
	}
}
