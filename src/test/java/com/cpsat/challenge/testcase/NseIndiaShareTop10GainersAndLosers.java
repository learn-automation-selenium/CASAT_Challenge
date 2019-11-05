package com.cpsat.challenge.testcase;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cpsat.challenge.pages.nseindia.NSEIndexPage;
import com.cpsat.challenge.pages.nseindia.TopGainersLosersPage;
import com.cpsat.challenge.testcase.common.TC_Common;

import common.CommonUtility;
import configreader.ObjectRepository;
import configreader.PropertyFileReader;
import excel.ExcelWriter;
import logger.LoggerHandler;
import util.NseUtil;

public class NseIndiaShareTop10GainersAndLosers extends TC_Common {

	private static final Logger log = LoggerHandler.getLogger(NseIndiaShareTop10GainersAndLosers.class);
	
	@BeforeClass
	public static void setData() {
		ObjectRepository.reader = new PropertyFileReader();
		browser = ObjectRepository.reader.getChromeBrowser();
		url = "https://www.nseindia.com/";
	}
	
	@Test
	public void topGainersLosersTest() {
		TopGainersLosersPage topGainersLosers = new NSEIndexPage(driver).hoverOverLiveMarket().clickTop10GainersOrLosers();
		
		// click on Gainers tab and fetch top gainers data
		String[][] topGainersData = topGainersLosers.getTopGainers();
		// write top gainers to an excel sheet
		ExcelWriter.exportDataToExcel("TopGainers", "GainersList", topGainersData);
		// Assert if the percentage change is high to low for each of Top gainers
		List<String> percentageGainChangeList = CommonUtility.getPercentageChangeData(topGainersData);
		boolean isPercentGainSortedDesc = CommonUtility.verifyListInDecendingOrder(percentageGainChangeList);
		Assert.assertTrue(isPercentGainSortedDesc, "The percentage change for each Top gainers is not sorted in high to low");
		
		// Extract the date and time when top gainers data was taken from the NSE website and compare with the system time
		Date gainersLstUpdateDateTime = null;
		String gainersLastUpdatedDateTime = topGainersLosers.getLastUpdatedDateTime();
		gainersLastUpdatedDateTime = NseUtil.getFinalString(gainersLastUpdatedDateTime, 6);
		try {
			gainersLstUpdateDateTime = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss").parse(gainersLastUpdatedDateTime);
		} catch (ParseException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		
		Date systemDateGainers = NseUtil.getSystemDate();
		
		List<Long> timeCalulationGainers = NseUtil.calculateDateTimeDifference(gainersLstUpdateDateTime, systemDateGainers);
		System.out.print("Gainers details =============> ");
		System.out.print(timeCalulationGainers.get(3) + " days, ");
		System.out.print(timeCalulationGainers.get(2) + " hours, ");
		System.out.print(timeCalulationGainers.get(1) + " minutes, ");
		System.out.print(timeCalulationGainers.get(0) + " seconds. \n");
		
		// click on Losers tab and fetch top losers data
		String[][] topLosersData = topGainersLosers.getTopLosers();
		// write top gainers to an excel sheet
		ExcelWriter.exportDataToExcel("TopLosers", "LosersList", topLosersData);
		// Assert if the percentage change is high to low for each of Top losers
		List<String> percentageLossChangeList = CommonUtility.getPercentageChangeData(topLosersData);
		boolean isPercentLossSortedDesc = CommonUtility.verifyListInDecendingOrder(percentageLossChangeList);
		Assert.assertTrue(isPercentLossSortedDesc, "The percentage change for each Top losers is not sorted in high to low");
		
		// Extract the date and time when top gainers data was taken from the NSE website and compare with the system time
		Date losersLstUpdateDateTime = null;
		String losersLastUpdatedDateTime = topGainersLosers.getLastUpdatedDateTime();
		losersLastUpdatedDateTime = NseUtil.getFinalString(losersLastUpdatedDateTime, 6);
		try {
			losersLstUpdateDateTime = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss").parse(losersLastUpdatedDateTime);
		} catch (ParseException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		
		Date losersSystemDate = NseUtil.getSystemDate();
		
		List<Long> losersTimeCalulation = NseUtil.calculateDateTimeDifference(losersLstUpdateDateTime, losersSystemDate);
		System.out.print("Losers details =============> ");
		System.out.print(losersTimeCalulation.get(3) + " days, ");
		System.out.print(losersTimeCalulation.get(2) + " hours, ");
		System.out.print(losersTimeCalulation.get(1) + " minutes, ");
		System.out.print(losersTimeCalulation.get(0) + " seconds.");
	}
}
