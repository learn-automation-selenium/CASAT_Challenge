package com.cpsat.challenge.pages.nseindia;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import configreader.ObjectRepository;
import logger.LoggerHandler;
import wrappers.GenericHandlers;
import wrappers.WaitHandler;
import wrappers.WebTableHandler;

public class TopGainersLosersPage {

	private static final Logger log = LoggerHandler.getLogger(TopGainersLosersPage.class);
	private GenericHandlers handlers;
	private WaitHandler waitHandler;
	private WebTableHandler webTableHandler;
	
	WebDriver driver;
	
	@FindBy(how=How.XPATH, using="//div[@id='tabs_main_content']/ul/li[1]")
	WebElement gainersTab;
	
	@FindBy(how=How.XPATH, using="//div[@id='tabs_main_content']/ul/li[2]")
	WebElement losersTab;
	
	@FindBy(how=How.ID, using="topGainers")
	WebElement topGainersTable;
	
	@FindBy(how=How.ID, using="topLosers")
	WebElement topLosersTable;
	
	@FindBy(how=How.ID, using="dataTime")
	WebElement dateTime;
	
	public TopGainersLosersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		handlers = new GenericHandlers(this.driver);
		waitHandler = new WaitHandler(this.driver);
		webTableHandler = new WebTableHandler(this.driver);
	}

	public String[][] getTopGainers() {
		log.info("Clicking Gainers tab");
		waitHandler.waitForElementToBeClickable(gainersTab, ObjectRepository.reader.getExplicitWait());
		handlers.clickElement(gainersTab);
		return fetchTopGainersOrLosers(topGainersTable);
	}
	
	public String[][] getTopLosers() {
		log.info("Clicking Losers tab");
		waitHandler.waitForElementToBeClickable(gainersTab, ObjectRepository.reader.getExplicitWait());
		handlers.clickElement(losersTab);
		return fetchTopGainersOrLosers(topLosersTable);
	}
	
	public String[][] fetchTopGainersOrLosers(WebElement topGainersOrLosers) {
		log.info("Fetching the data of top Gainers and Losers");
		return webTableHandler.getTableData(topGainersOrLosers);
	}
	
	public String getLastUpdatedDateTime() {
		log.info("Fetching the Last updated date and time");
		return handlers.getElementText(dateTime);
	}
} 