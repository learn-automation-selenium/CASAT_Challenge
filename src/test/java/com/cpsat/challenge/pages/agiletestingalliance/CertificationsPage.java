package com.cpsat.challenge.pages.agiletestingalliance;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import logger.LoggerHandler;
import wrappers.GenericHandlers;

public class CertificationsPage {

	private static final Logger log = LoggerHandler.getLogger(CertificationsPage.class);
	
	private WebDriver driver;
	GenericHandlers handlers;
	
	@FindBys( { @FindBy (xpath="//map/area") })
	List<WebElement> certificationsList;
	
	@FindBy(how=How.XPATH, using= "//map[@name='image-map']//descendant::area[contains(@href,'cct.html')]")
	WebElement cpcctElement;
	

	
	public CertificationsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		handlers = new GenericHandlers(driver);
	}

	public List<WebElement> getCertificationsCount() {
		log.info("Getting the count of all the certifications");
		return certificationsList;
	}
	
	public void performMouseHoverOverCPCCT() {
		log.info("Performing mouse hover action");
		if(ExpectedConditions.presenceOfElementLocated(By.name("image-map"))!=null) {
			handlers.mouseOver(cpcctElement);
		}
	}
}