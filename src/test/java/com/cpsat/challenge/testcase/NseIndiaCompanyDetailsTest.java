package com.cpsat.challenge.testcase;


import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cpsat.challenge.pages.nseindia.NSEIndexPage;
import com.cpsat.challenge.testcase.common.TC_Common;

import configreader.ObjectRepository;
import configreader.PropertyFileReader;

public class NseIndiaCompanyDetailsTest extends TC_Common{

	@BeforeClass
	public static void setData() {
		ObjectRepository.reader = new PropertyFileReader();
		browser = ObjectRepository.reader.getChromeBrowser();
		url = "https://www.nseindia.com/";
	}
	
	@Test
	public void minNumbetTest() {
		Map<String, Integer> marketDetails = new NSEIndexPage(driver).getMarketWatchDetails();
		
		Entry<String, Integer> min = null;
		for (Entry<String, Integer> entry : marketDetails.entrySet()) {
		    if (min == null || min.getValue() > entry.getValue()) {
		        min = entry;
		    }
		}

		// print the minimum number
		System.out.println(min.getKey() + " " + min.getValue());
	}
}
