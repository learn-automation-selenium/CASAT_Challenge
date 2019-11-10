package util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NseUtil {

	public static String getFinalString(String str, int...beginIndex) {
		return str.substring(beginIndex[0]);
	}
	
	public static Date getSystemDate() {
		return new Date(System.currentTimeMillis());
	}
	
	public static List<Long> calculateDateTimeDifference(Date fromWebSite, Date systemDate) {
		List<Long> timeCal = new ArrayList<>(); 
		long diff = systemDate.getTime() - fromWebSite.getTime();

		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffDays = diff / (24 * 60 * 60 * 1000);
		
		timeCal.add(diffSeconds);
		timeCal.add(diffMinutes);
		timeCal.add(diffHours);
		timeCal.add(diffDays);
		
		return timeCal;
	}
	
	public static List<String> getPercentageChangeData(String[][] topGainersLosersData) {
		List<String> topGainersLosersLst = new ArrayList<String>();

		for (int row = 1; row < topGainersLosersData.length; row++) {
			for (int col = 0; col < topGainersLosersData[row].length; col++) {
				if (col == 2) {
					String data = topGainersLosersData[row][col];
					topGainersLosersLst.add(data);
				}
			}
		}
		return topGainersLosersLst;
	}

	public static boolean verifyListInDecendingOrder(List<String> topGainersLosersLst) {
		boolean isSorted = true;
		BigDecimal previousNumber, currentNumber;
		for (int i = 1; i < topGainersLosersLst.size(); i++) {
			previousNumber = new BigDecimal(topGainersLosersLst.get(i - 1));
			if (previousNumber.compareTo(BigDecimal.ZERO) < 0) {
				previousNumber = previousNumber.negate();
			}
			currentNumber = new BigDecimal(topGainersLosersLst.get(i));
			if (currentNumber.compareTo(BigDecimal.ZERO) < 0) {
				currentNumber = currentNumber.negate();
			}
			if (previousNumber.compareTo(currentNumber) < 0) {
				isSorted = false;
				break;
			}
		}
		return isSorted;
	}
}
