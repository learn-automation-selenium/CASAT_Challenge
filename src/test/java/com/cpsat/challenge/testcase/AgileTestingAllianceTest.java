package com.cpsat.challenge.testcase;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import com.cpsat.challenge.pages.agiletestingalliance.CertificationsPage;
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
	public void certificationsDetailsTest() {
		
		int MYTHREADS = 10;
		executor = Executors.newFixedThreadPool(MYTHREADS);
		
		//Click on the certification’s menu item
		CertificationsPage certificationPage = new IndexPage(driver).clickCertificationsLink();
		List<WebElement> certificationsList = certificationPage.getCertificationsCount();
		List<WebElement> CP_CCT = new ArrayList<WebElement>();
		
		// verify that the count is 12
		softAssertions.assertThat(certificationsList.size()).isEqualTo(12);
		
		//Print the URL every image is pointing to 
		for(WebElement certification : certificationsList) {
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
		certificationPage.performMouseHoverOverCPCCT();
		
		//Take a screenshot after hovering
		takeSnap("after_hover");
	}
}