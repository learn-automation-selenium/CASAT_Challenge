package com.cpsat.challenge.pages.nseindia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import configreader.ObjectRepository;
import logger.LoggerHandler;
import wrappers.GenericHandlers;
import wrappers.WaitHandler;

public class NSEIndexPage {

	private static final Logger log = LoggerHandler.getLogger(NSEIndexPage.class);
	private GenericHandlers handlers;
	private WaitHandler waitHandler;
	WebDriver driver;
	
	@FindBy(xpath="//ul[@class='advanceTab']/li")
	List<WebElement> marketWatchWindow;
	
	public NSEIndexPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		handlers = new GenericHandlers(this.driver);
		waitHandler = new WaitHandler(this.driver);
	}
	
	public Map<String, Integer> getMarketWatchDetails() {
		Map<String, Integer> marketWatchMap = new HashMap<>();
		waitHandler.waitForElementToBeVisible(marketWatchWindow.get(0), ObjectRepository.reader.getExplicitWait());
		int windowSize = marketWatchWindow.size();
		for(int i=0; i<windowSize; i++) {
			WebElement item = marketWatchWindow.get(i);
			String watchItem = handlers.getTextByXpath(item.findElement(By.xpath("//ul[@class='advanceTab']/li["+(i+1)+"]/p")));
			Integer watchValue = Integer.valueOf(handlers.getTextByXpath(item.findElement(By.xpath("//ul[@class='advanceTab']/li["+(i+1)+"]/span"))));
			marketWatchMap.put(watchItem, watchValue);
		}
		return marketWatchMap;
	}
}
