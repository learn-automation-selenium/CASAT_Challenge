package utility;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import wrappers.GenericHandlers;

public class UtilityClass extends GenericHandlers{

	static HttpURLConnection httpURLConnection = null;
	
	public static long getRandomNumber() {
		return (long) Math.floor(Math.random() * 900000000L) + 10000000L;
	}
	
	public static void getStatusCode(String url) {
		int statusCode = 0;
		try {
			httpURLConnection = (HttpURLConnection)(new URL(url).openConnection());
			httpURLConnection.setRequestMethod("HEAD");
			httpURLConnection.connect();
			statusCode =  httpURLConnection.getResponseCode();
			if(statusCode != 200) {
				failureList.add(url);
			}
		} catch (ProtocolException protocolException) {
			protocolException.printStackTrace();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} 
	}
}
