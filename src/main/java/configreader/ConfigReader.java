package configreader;

import browser.BrowserType;

public interface ConfigReader {

	public BrowserType getChromeBrowser();
	public BrowserType getFirefoxBrowser();
	public int getPageLoadTimeOut();
	public int getImplicitWait();
	public int getExplicitWait();
	public long getPollingTimeInMilliSecond();
}
