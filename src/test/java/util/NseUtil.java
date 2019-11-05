package util;

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
}
