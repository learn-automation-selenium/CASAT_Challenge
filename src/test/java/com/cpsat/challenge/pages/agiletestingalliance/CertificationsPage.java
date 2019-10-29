package com.cpsat.challenge.pages.agiletestingalliance;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import logger.LoggerHandler;

public class CertificationsPage {

	private static final Logger log = LoggerHandler.getLogger(CertificationsPage.class);
	
	WebDriver driver;
	
	@FindBys( { @FindBy (xpath="//map/area") })
	List<WebElement> certificationsList;
	
	public CertificationsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public List<WebElement> getCertificationsCount() {
		log.info("Getting the count of all the certifications");
		System.out.println(certificationsList.size());
		return certificationsList;
	}
}
