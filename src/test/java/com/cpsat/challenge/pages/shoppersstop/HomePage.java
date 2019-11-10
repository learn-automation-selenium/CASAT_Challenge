package com.cpsat.challenge.pages.shoppersstop;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
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
	
	@FindBys({@FindBy(how=How.XPATH, using="//ul[@class='slick-dots']/li")})
	List<WebElement> banners;
	
	@FindBy(how=How.CSS, using="div[class='dy-slick-arrow dy-next-arrow slick-arrow']")
	WebElement rightArrow;
	
	@FindBy(how=How.LINK_TEXT, using="MEN")
	WebElement menSection;
	
	@FindBy(how=How.LINK_TEXT, using="Men's Fragrance")
	WebElement mensFragranceSection;
	
	@FindBys( {@FindBy(how=How.XPATH, using="//li[contains(@class,'yCmsComponent')][4]/div/div/ul/li[6]/div/ul/li/div/ul/li/div/span/a") })
	List<WebElement> allMenFragrance;
	
	@FindBy(how=How.LINK_TEXT, using="All Stores")
	WebElement storeFinder;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		handlers = new GenericHandlers(this.driver);
		waitHandler = new WaitHandler(this.driver);
	}
	
	public void viewAllBanners() {
		log.info("Viewing all the banners");
		int allBannerCount = banners.size();
		for(int i=0; i<allBannerCount; i++) {
			System.out.println("Banner count : " + i);
			handlers.clickElement(rightArrow);
		}
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
		handlers.clickElement(mensFragranceSection);
		return this;
	}

	public List<String> getAllMensFragrance(){
		log.info("Fetching all mens fragrance");
		List<String> menFragranceList = new ArrayList<String>();
		System.out.println(allMenFragrance.size());
		for(int i=0; i<allMenFragrance.size(); i++) {
			menFragranceList.add(allMenFragrance.get(i).getAttribute("innerHTML"));
		}
		return menFragranceList;
	}
	
	public StoreFinderPage clickOnStoreFinder() {
		log.info("Clicking on store finder link");
		handlers.clickElement(storeFinder);
		return new StoreFinderPage(this.driver);
	}
}
