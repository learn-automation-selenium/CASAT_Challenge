package wrappers;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import logger.LoggerHandler;

public class WebTableHandler {

	private static final Logger log = LoggerHandler.getLogger(WebTableHandler.class);
	private WebDriver driver;
	private String[][] tableData = null;
	private static final String TR = "tr";
	private static final String TH = "th";
	private static final String TD = "td";
	
	public WebTableHandler(WebDriver driver) {
		this.driver = driver;
		log.info("WebTableHandler : " + this.driver.hashCode());
	}

	/**
	 * This method will read the webtable data and store it in a 2D array
	 * It store table data along with it header.
	 * 
	 * @param myTable - WebElement for the webtable
	 * 
	 * @return tableData- it is a 2D array which hold all the data stored in a webtable
	 */
	public String[][] getTableData(WebElement myTable) {

		List<WebElement> rows_table = myTable.findElements(By.tagName(TR));
		List<WebElement> header_table = rows_table.get(0).findElements(By.tagName(TH));

		int row_Count = rows_table.size();
		int col_Count = header_table.size();
		tableData = new String[row_Count][col_Count];

		// add header data to the list
		for (int header=0; header<col_Count; header++) {
			String headerText = header_table.get(header).getText();
			tableData[0][header] = headerText;
		}

		// add cell data to the list
		for (int row=1; row<row_Count; row++) {
			List<WebElement> columns_table = rows_table.get(row).findElements(By.tagName(TD));

			for (int col=0; col<col_Count; col++) {
				String cellText = columns_table.get(col).getText();
				tableData[row ][col] = cellText;
			}
		}
		return tableData;
	}
}