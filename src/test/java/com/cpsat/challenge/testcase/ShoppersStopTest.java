package com.cpsat.challenge.testcase;

import org.testng.annotations.Test;

import com.cpsat.challenge.pages.shoppersstop.HomePage;
import com.cpsat.challenge.pages.shoppersstop.StoreFinderPage;
import com.cpsat.challenge.testcase.common.TC_Common;

import configreader.ObjectRepository;
import configreader.PropertyFileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;

public class ShoppersStopTest extends TC_Common {

	private static String STORE_CITY = "Bangalore";
	
	@BeforeClass
	public void beforeClass() {
		ObjectRepository.reader = new PropertyFileReader();
		browser = ObjectRepository.reader.getChromeBrowser();
		url = "https://www.shoppersstop.com/";
	}

	@Test
	public void bannerTest001() {
		List<String> cityList = new ArrayList<String>(Arrays.asList("Agra","Bhopal","Mysore"));
		HomePage homePage = new HomePage(driver);
		
		// Click on the banner slider > for the number of times till the banner gets repeated
		
		// Print all the accessories name under MEN section > Men’s Fragrance
		// TODO: fetching of all the fragrance list is pending now
		/*List<String> menFragranceLst = homePage.mouseHoverOverMenSection().mouseHoverOverMensFragrance().getAllMensFragrance();
		menFragranceLst.forEach(fragrance -> {
			System.out.println(fragrance);
		});*/
		
		// click on All store link, and Print the Cities name that available in Find Stores in your city
		List<String> storeList = homePage.clickOnStoreFinder().selectCityName(STORE_CITY).getAllStoreInYourCity();
		
		// Assert Agra, Bhopal and Mysore are available in Find Stores in your city.
		for (int i = 0; i < storeList.size(); i++) {
			if(cityList.contains(storeList.get(i))) {
				Assert.assertTrue(true, storeList.get(i)+" is available in the list");
			}
		}
		
		// Print the page title in console
		String currentPageTitle = new StoreFinderPage(driver).getPageTitle();
		System.out.println(currentPageTitle);
	}

}
