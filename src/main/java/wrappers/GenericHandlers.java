package wrappers;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import browser.BrowserType;
import configreader.ObjectRepository;
import logger.LoggerHandler;
import utility.ResourceHandler;
import utility.UtilityClass;

public class GenericHandlers {

	private static final Logger log = LoggerHandler.getLogger(GenericHandlers.class);

	public WebDriver driver;
	protected static Properties prop;
	public String sUrl,sHubUrl,sHubPort;
	public static String url;
	public static List<String> failureList = new ArrayList<>();

	public GenericHandlers() {
		
	}

	public GenericHandlers(WebDriver driver) {
		this.driver = driver;
	}

	public void unloadObjects() {
		prop = null;
	}

	/**
	 * This method will launch the browser in local machine and maximise the browser and set the
	 * wait for 30 seconds and load the url
	 * @param url - The url with http or https
	 * 
	 */
	public void invokeApp(BrowserType browser) {
		invokeApp(browser,false);
	}

	/**
	 * This method will launch the browser in grid node (if remote) and maximise the browser and set the
	 * wait for 30 seconds and load the url 
	 * @param url - The url with http or https
	 * 
	 */
	public void invokeApp(BrowserType browser, boolean bRemote) {

		log.info("Launching " + browser + " browser");
		String osName = System.getProperty("os.name");
		try {

			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setBrowserName(browser.toString());

			if (osName.contains("Window")) {
				dc.setPlatform(Platform.WINDOWS);
			} else if (osName.contains("Mac")) {
				dc.setPlatform(Platform.MAC);
			}

			switch (browser) {
			case Chrome:
				if (osName.contains("Window")) {
					System.setProperty("webdriver.chrome.driver", ResourceHandler.getResourcePath("\\resources\\drivers\\chromedriver.exe"));
				} else if (osName.contains("Mac")) {
					System.setProperty("webdriver.chrome.driver", ResourceHandler.getResourcePath("\\resources\\drivers\\chromedriver"));
				}
				
				List<String> chromeArgsList = new ArrayList<>();
				chromeArgsList.add("--disable-notifications");
				
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments(chromeArgsList);
				driver = new ChromeDriver(chromeOptions);
				break;
			case Firefox:
				if (osName.contains("Window")) {
					System.setProperty("webdriver.gecko.driver", ResourceHandler.getResourcePath("\\resources\\drivers\\geckodriver.exe"));
				} else if (osName.contains("Mac")) {
					System.setProperty("webdriver.gecko.driver", ResourceHandler.getResourcePath("\\resources\\drivers\\geckodriver"));
				}
				
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.addPreference("dom.webnotifications.enabled", false);
				driver = new FirefoxDriver(firefoxOptions);
				break;
			default:
				throw new RuntimeException("Given browser name is not correct");
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(ObjectRepository.reader.getImplicitWait(), TimeUnit.SECONDS);
			driver.get(url);
			log.info("The browser:" +browser+ " launched successfully");
		} catch (Exception e) {
			log.error("The browser:" +browser+ " could not be launched");
			log.error(e.getStackTrace());
		}
	}

	/**
	 * This method will enter the value to the text field
	 * @param element
	 * @param data
	 */
	public void enterData(WebElement element, String data) {
		try {
			element.clear();
			element.sendKeys(data);
			log.info("The data: " +data+ " entered successfully in field");
		} catch (NoSuchElementException e) {
			log.error("The data: " +data+ " could not be entered in the field");
			log.error(e.getStackTrace());
		} catch (Exception e) {
			log.error("An unknown exception occured while entering " +data+ " in the field");
			log.error(e.getStackTrace());
		}
	}

	/**
	 * This method will perform key operation on a webElement
	 * @param element
	 * @param enter
	 */
	public void performKeyOperation(WebElement element, Keys enter) {
		try {
			element.sendKeys(enter);
			log.info("Key operation is performed successfully on the " +element);
		} catch (NoSuchElementException e) {
			log.error("The operation: " +enter+ " could not be performed on the " +element);
			log.error(e.getStackTrace());
		} catch (Exception e) {
			log.error("An unknown exception occured while performing " +enter+ " on the " +element);
			log.error(e.getStackTrace());
		}
	}

	/**
	 * This method will enter the value to the text area
	 * @param element
	 * @param data
	 */
	public void enterTextAreaData(WebElement element, String data) {
		try {
			element.sendKeys(data);
			log.info("The data: " +data+ " entered successfully in field");
		} catch (NoSuchElementException e) {
			log.error("The data: " +data+ " could not be entered in the field");
			log.error(e.getStackTrace());
		} catch (Exception e) {
			log.error("An unknown exception occured while entering " +data+ " in the field");
			log.error(e.getStackTrace());
		}
	}

	/**
	 * This method will close all the browsers
	 */
	public void closeAllBrowsers() {
		try {
			if (driver!=null) {
				driver.quit();
			}
		} catch (Exception e) {
			log.error("The browser could not be closed.");
			log.error(e.getStackTrace());
		}
	}

	/**
	 * This method will close current browser window
	 */
	public void closeBrowser() {
		try {
			if (driver!=null) {
				driver.close();
			}
		} catch (Exception e) {
			log.error("The current browser window could not be closed.");
			log.error(e.getStackTrace());
		}
	}

	/**
	 * This method clicks the WebElement
	 * @param element of WebElement type
	 */
	public void clickElement(WebElement element) {
		try{
			element.click();
			log.info("The element : " +element+ " is clicked.");
		} catch (Exception e) {
			log.error("The element : " +element+ " could not be clicked.");
			log.error(e.getStackTrace());
		}
	}

	/**
	 * This method will check if the radio button is not selected, then select the radio button
	 * @param radioElement of WebElement type
	 */
	public void selectElement(WebElement radioElement) {
		boolean isSelected = false;
		try {
			isSelected = radioElement.isSelected();
			if (!isSelected) {
				radioElement.click();
			}
			log.info("The element : " +radioElement+ " is selected.");
		} catch (Exception e) {
			log.error("The element : " +radioElement+ " could not be selected.");
			log.error(e.getStackTrace());
		}
	}

	/**
	 * This method will perform mouse hover action over the element
	 * @param element of WebElement type
	 */
	public void mouseOver(WebElement element) {
		try{
			new Actions(driver).moveToElement(element).build().perform();
			log.info("The mouse hover action over the element: " +element+ " is performed.");
		} catch (Exception e) {
			log.error("The mouse hover action over the element: " +element+ " could not be performed.");
			log.error(e.getStackTrace());
		}
	}
	
	/**
	 * This method will perform mouse hover action over the element using java script Executor.
	 * @param element of WebElement type
	 */
	public void mouseOverJavaScriptExecutor(WebElement element) {
		String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
		try{
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript(mouseOverScript, element);
			log.info("The mouse hover action over the element: " +element+ " is performed.");
		} catch (Exception e) {
			log.error("The mouse hover action over the element: " +element+ " could not be performed.");
			log.error(e.getStackTrace());
		}
	}

	/**
	 * This method will return the text of the element
	 * @param element - Webelement locator
	 */
	public String getElementText(WebElement element){
		String text = "";
		try{
			return element.getText();
		} catch (Exception e) {
			log.error("The element : " +element+ " could not be found.");
			log.error(e.getStackTrace());
		}
		return text; 
	}
	
	/**
	 * This method will return current page title
	 *  
	 **/
	public String getCurrentPageTitle() {
		String pageTitle = "";
		try {
			return driver.getTitle();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return pageTitle;
	}
	
	/**
	 * This method will take snapshot of the browser and 
	 * assign a unique number to the image and stores it to a location
	 * 
	 * @param name- name to be assigned the image
	 * @return screenShotPath - path where the image is stored
	 */
	public String takeSnap(String name){
		long number = UtilityClass.getRandomNumber();
		String destinationPath="";
		File srcFile = null;
		File destFile = null;
		String screenShotPath ="";
		try {
			destinationPath = ResourceHandler.getResourcePath("\\target\\screenshot");
			srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			destFile = new File(destinationPath+"\\" +number+ "_"+ name+".jpg");
			FileUtils.copyFile(srcFile , destFile);
			screenShotPath = destFile.getAbsolutePath();
		} catch (WebDriverException e) {
			log.error(e.getStackTrace());
		} catch (IOException e) {
			log.error(e.getStackTrace());
		}
		return screenShotPath;
	}
}