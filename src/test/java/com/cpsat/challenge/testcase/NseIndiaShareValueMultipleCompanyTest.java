package com.cpsat.challenge.testcase;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cpsat.challenge.pages.nseindia.CompanyDetailsPage;
import com.cpsat.challenge.pages.nseindia.NSEIndexPage;
import com.cpsat.challenge.testcase.common.TC_Common;

import configreader.ObjectRepository;
import configreader.PropertyFileReader;

public class NseIndiaShareValueMultipleCompanyTest extends TC_Common{

	
	@BeforeClass
	public void setData() {
		ObjectRepository.reader = new PropertyFileReader();
		browser = ObjectRepository.reader.getChromeBrowser();
		dataFileName = "Company_Name";
		dataSheetName = "Companies";
		url = "https://www.nseindia.com/";
	}
	
	@Test(dataProvider="fetchCompanyName")
	public void companyDetailsTest(String companyName) {
		
		// verify company name
		CompanyDetailsPage companyDetails = new NSEIndexPage(driver).searchCompanyDetails(companyName);
		
		String cName = companyDetails.getCompanyName();
		System.out.println(cName);
		
		// fetch the following and print in the console
		//1. Face Value
		String faceValue = companyDetails.getFaceValue();
		System.out.println("Face value " +faceValue);
		
		//2. 52 week high
		String high52WeekValue = companyDetails.get52WeekHigh();
		System.out.println("52 weeks high " +high52WeekValue);
		
		//3. 52 week low
		String low52WeekValue = companyDetails.get52WeekLow();
		System.out.println("52 weeks low " +low52WeekValue);
		
		//take screenshot
		takeSnap(companyName);
	}
}