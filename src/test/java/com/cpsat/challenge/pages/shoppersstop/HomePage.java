package com.cpsat.challenge.pages.shoppersstop;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import configreader.ObjectRepository;
import logger.LoggerHandler;
import wrappers.GenericHandlers;
import wrappers.WaitHandler;

public class HomePage {

	private static final Logger log = LoggerHandler.getLogger(HomePage.class);
	private GenericHandlers handlers;
	private WaitHandler waitHandler;
	WebDriver driver;
	
	@FindAll(@FindBy(how=How.XPATH, using=""))
	List<WebElement> banners;
	
	@FindBy(how=How.LINK_TEXT, using="MEN")
	WebElement menSection;
	
	@FindBy(how=How.LINK_TEXT, using="Men's Fragrance")
	WebElement mensFragranceSection;
	
	@FindBy(how=How.LINK_TEXT, using="All Stores")
	WebElement storeFinder;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		handlers = new GenericHandlers(this.driver);
		waitHandler = new WaitHandler(this.driver);
	}
	
	public HomePage mouseHoverOverMenSection() {
		log.info("Mousehover over the Men section");
		waitHandler.waitForElementToBeVisible(menSection, ObjectRepository.reader.getExplicitWait());
		handlers.mouseOver(menSection);
		return this;
	}
	
	public HomePage mouseHoverOverMensFragrance() {
		log.info("Mousehover over the Men's Fragrance section");
		waitHandler.waitForElementToBeVisible(mensFragranceSection, ObjectRepository.reader.getExplicitWait());
		handlers.mouseOver(mensFragranceSection);
		return this;
	}

	public List<String> getAllMensFragrance(){
		log.info("Fetching all mens fragrance");
		List<String> menFragranceList = new ArrayList<String>();
		
		return menFragranceList;
	}
	
	public StoreFinderPage clickOnStoreFinder() {
		log.info("Clicking on store finder link");
		handlers.clickElement(storeFinder);
		return new StoreFinderPage(this.driver);
	}
}
