package common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CommonUtility {

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