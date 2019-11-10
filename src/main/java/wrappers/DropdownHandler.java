package wrappers;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import logger.LoggerHandler;

public class DropdownHandler {

	private static final Logger log = LoggerHandler.getLogger(DropdownHandler.class);
	private WebDriver driver;
	
	public DropdownHandler(WebDriver driver) {
		this.driver = driver;
		log.info("DropdownHandler : " + this.driver.hashCode());
	}
	
	/**
	 * This method will select the drop down value based on the visible text
	 * @param element - The webelement of the dropdown
	 * @param value - the value to be selected (visibletext) from the dropdown 
	 */
	public void selectByVisibileText(WebElement element, String value) {
		try{
			new Select(element).selectByVisibleText(value);
			log.info("The element : " +element+ " is selected with value : " +value);
		} catch (Exception e) {
			log.info("The value: " +value+ " could not be selected from dropdown");
		}
	}

	/**
	 * This method will select the drop down value based on the index provided
	 * @param element - The webelement of the dropdown
	 * @param id The id (locator) of the drop down element
	 */
	public void selectByIndex(WebElement element, int id) {
		try{
			new Select(element).selectByIndex(id);
			log.info("The element: " +element+ " is selected with index :" +id);
		} catch (Exception e) {
			log.info("The index: " +id+ " could not be selected.");
		}
	}
	
	/**
	 * This method will select the drop down value based on the value provided 
	 * @param element The webelement of the dropdown
	 * @param value The value to be selected (value) from the dropdown 
	 */
	public void selectByValue(WebElement element, String value) {
		try{
			new Select(element).selectByValue(value);
			log.info("The element : " +element+ " is selected with value : " +value);
		} catch (Exception e) {
			log.info("The value: " +value+ " could not be selected.");
		}
	}
	
	/**
	 * This method will fetch all the options from the dropdown
	 * @param element - the WebElement of the dropdown
	 * 
	 * @return List<WebElement> - returns a list of weblement
	 * */
	public List<WebElement> getOptions(WebElement element) {
		List<WebElement> getAllOptions = null;
		try{
			log.info("Fetch all the options for the element : " +element);
			getAllOptions =  new Select(element).getOptions();
		} catch (Exception e) {
			log.info("Options couldn't be selected for the element : " +element);
		}
		return getAllOptions;
	}
}