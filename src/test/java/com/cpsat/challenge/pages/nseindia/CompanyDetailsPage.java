package com.cpsat.challenge.pages.nseindia;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import configreader.ObjectRepository;
import logger.LoggerHandler;
import wrappers.GenericHandlers;
import wrappers.WaitHandler;

public class CompanyDetailsPage {

	private static final Logger log = LoggerHandler.getLogger(CompanyDetailsPage.class);
	private GenericHandlers handlers;
	private WaitHandler waitHandler;
	WebDriver driver;
	
	@FindBy(css="span#companyName")
	WebElement companyName;
	
	@FindBy(id="faceValue")
	WebElement faceValue;
	
	@FindBy(id="high52")
	WebElement high52Week;
	
	@FindBy(id="low52")
	WebElement low52Week;
	
	public CompanyDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		handlers = new GenericHandlers(this.driver);
		waitHandler = new WaitHandler(this.driver);
	}
	
	public String getCompanyName() {
		log.info("Getting the details of the company");
		waitHandler.waitForElementToBeVisible(companyName, ObjectRepository.reader.getExplicitWait());
		return handlers.getElementText(companyName);
	}
	
	public String getFaceValue() {
		log.info("Fetching face value");
		return handlers.getElementText(faceValue);
	}
	
	public String get52WeekHigh() {
		log.info("Fetching 52 week high value");
		return handlers.getElementText(high52Week);
	}
	
	public String get52WeekLow() {
		log.info("Fetching 52 week low value");
		return handlers.getElementText(low52Week);
	}
}
