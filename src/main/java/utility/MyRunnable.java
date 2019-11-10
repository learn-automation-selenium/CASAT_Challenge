package utility;

public class MyRunnable implements Runnable{

	private final String url;
	
	public MyRunnable(String url) {
		this.url = url;
	}
	
	@Override
	public void run() {
		UtilityClass.getStatusCode(url);
	}
}