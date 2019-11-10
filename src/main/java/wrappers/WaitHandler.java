package wrappers;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import logger.LoggerHandler;

public class WaitHandler {

	private static final Logger log = LoggerHandler.getLogger(WaitHandler.class);
	private WebDriver driver;

	public WaitHandler(WebDriver driver) {
		this.driver =  driver;
		log.info("Wait : " + this.driver.hashCode());
	}

	/**
	 * This is an implicit wait where driver will for the element
	 * 
	 * @param timeout - it is the number for which driver object will wait
	 * @param unit - it is the timeunit.
	 */
	public void setImplicitWait(long timeout, TimeUnit unit) {
		log.info("Implicit timeout : " + timeout);
		driver.manage().timeouts().implicitlyWait(timeout, unit == null ? TimeUnit.SECONDS : unit);
	}

	/**
	 * This is an explicit wait for which driver until webelement is clickable
	 * @param element - webelement for which driver will wait
	 * @param timeout - it is the number for which driver object will wait- 
	 * 
	 */
	public void waitForElementToBeClickable(WebElement element, long timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		log.info("Webelement " +element+" found and can be clicked");
	}

	/**
	 * This is an explicit wait for which driver until webelement is visible
	 * @param element - webelement for which driver will wait
	 * @param timeout - it is the number for which driver object will wait- 
	 * 
	 */
	public void waitForElementToBeVisible(WebElement element, long timeout) {
		WebDriverWait wait = new WebDriverWait(this.driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("Element found : " + element.getText());
	}
}