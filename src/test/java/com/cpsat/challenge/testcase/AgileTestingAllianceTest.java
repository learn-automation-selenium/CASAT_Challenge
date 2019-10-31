package com.cpsat.challenge.testcase;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.cpsat.challenge.pages.agiletestingalliance.IndexPage;
import com.cpsat.challenge.testcase.common.TC_Common;

import configreader.ObjectRepository;
import configreader.PropertyFileReader;
import utility.MyRunnable;

public class AgileTestingAllianceTest extends TC_Common{

	ExecutorService executor;
	
	@BeforeClass
	public static void setData() {
		ObjectRepository.reader = new PropertyFileReader();
		browser = ObjectRepository.reader.getChromeBrowser();
		url = "https://agiletestingalliance.org/";
	}
	
	@Test
	public void test001() {
		
		int MYTHREADS = 10;
		executor = Executors.newFixedThreadPool(MYTHREADS);
		//Click on the certification’s menu item
		List<WebElement> certificationsList = new IndexPage(driver).clickCertificationsLink().getCertificationsCount();
		List<WebElement> CP_CCT = new ArrayList<WebElement>();
		
		// verify that the count is 12
		softAssertions.assertThat(certificationsList.size()).isEqualTo(12);
		
		//Print the URL every image is pointing to 
		for(WebElement certification : certificationsList) {
			// TODO: need to check for mouse hover over the CP-CCT icon
			if (certification.getAttribute("title").equals("CP-CCT")) {
				WebElement element = certification;
				CP_CCT.add(element);
			}
			System.out.println(certification.getAttribute("title") + " --> " + certification.getAttribute("href"));
			Runnable worker = new MyRunnable(certification.getAttribute("href"));
			executor.execute(worker);
		}
		executor.shutdown();
		
		while(!executor.isTerminated()) {
		}
		
		//Confirm if the URL’s are working or not
		if (failureList.size() > 0) {
			for(String link : failureList) {
				System.out.println("Failure URL --> " + link);
			}
			softAssertions.assertThat(false).isFalse();
		}
		
		//Take a screenshot 
		takeSnap("before_hover");
		
		//Hover on CP-CCT 
		// TODO: mouse is not working for map tag. Need to be looked upon before submitting the code
		mouseOver(driver.findElement(By.xpath("//map/area[4]")));
				
		//Take a screenshot after hovering
		takeSnap("after_hover");
	}
}