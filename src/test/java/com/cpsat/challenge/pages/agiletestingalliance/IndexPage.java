package com.cpsat.challenge.pages.agiletestingalliance;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import logger.LoggerHandler;
import wrappers.GenericHandlers;

public class IndexPage {

	private static final Logger log = LoggerHandler.getLogger(IndexPage.class);
	private GenericHandlers handlers;
	WebDriver driver;
	
	@FindBy(linkText="Certifications")
	WebElement certificationsLink;
	
	public IndexPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		handlers = new GenericHandlers(this.driver);
	}

	public CertificationsPage clickCertificationsLink() {
		log.info("Clicking Certifications link");
		handlers.clickElement(certificationsLink);
		return new CertificationsPage(driver);
	}
}
