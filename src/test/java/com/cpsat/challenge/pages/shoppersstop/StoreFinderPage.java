package com.cpsat.challenge.pages.shoppersstop;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import configreader.ObjectRepository;
import logger.LoggerHandler;
import wrappers.DropdownHandler;
import wrappers.GenericHandlers;
import wrappers.WaitHandler;

public class StoreFinderPage {

	private static final Logger log = LoggerHandler.getLogger(StoreFinderPage.class);
	private GenericHandlers handlers;
	private WaitHandler waitHandler;
	private DropdownHandler dropDownHandler;
	WebDriver driver;
	
	@FindBy(how=How.ID, using="city-name")
	WebElement storeCityName;
	
	@FindBy(how=How.ID, using="selectedPOS")
	WebElement selectedPOS;
	
	@FindBy(how=How.ID, using="firstVisit")
	WebElement cookieContainer;
	
	public StoreFinderPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		handlers = new GenericHandlers(this.driver);
		waitHandler = new WaitHandler(this.driver);
		dropDownHandler = new DropdownHandler(this.driver);
	}

	public void hideCookieContainer() {
		((JavascriptExecutor)driver).executeScript("arguments[0].style.visibility='hidden'", cookieContainer);
	}

	public StoreFinderPage selectCityName(String cityName) {
		log.info("Selecting city name");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//waitHandler.waitForElementToBeClickable(storeCityName, ObjectRepository.reader.getExplicitWait());
		// hide cookie container
		//hideCookieContainer();
		dropDownHandler.selectByValue(storeCityName, cityName);
		return this;
	}
	
	public List<String> getAllStoreInYourCity() {
		log.info("Fetching all the store in the selected city");
		List<String> storeDetails = new ArrayList<>();
		//waitHandler.waitForElementToBeVisible(selectedPOS, ObjectRepository.reader.getExplicitWait());
		List<WebElement> options = dropDownHandler.getOptions(selectedPOS);
		for(WebElement item:options) {
			storeDetails.add(item.getText());
		}
		return storeDetails;
	}
	
	public String getPageTitle() {
		return handlers.getCurrentPageTitle();
	}
}
