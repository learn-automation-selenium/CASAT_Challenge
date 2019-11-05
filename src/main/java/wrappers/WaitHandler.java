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

	public void setImplicitWait(long timeout, TimeUnit unit) {
		log.info("Implicit timeout : " + timeout);
		driver.manage().timeouts().implicitlyWait(timeout, unit == null ? TimeUnit.SECONDS : unit);
	}

	public void waitForElementToBeClickable(WebElement element, long timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		log.info("Webelement " +element+" found and can be clicked");
	}

	public void waitForElementToBeVisible(WebElement element, long timeout) {
		WebDriverWait wait = new WebDriverWait(this.driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("Element found : " + element.getText());
	}
}