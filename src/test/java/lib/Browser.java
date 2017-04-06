package lib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class Browser {
	private static WebDriver driver;
	private static void setDriver() {
		ChromeDriverManager.getInstance().setup();
		Browser.driver = new ChromeDriver();
  	}   
	public static WebDriver getDriver() {
  	  if (Browser.driver == null) {
  		setDriver();
  	  }
  	  return Browser.driver;
  	}
//	public static void close() {
//		Browser.driver.close();
//		Browser.driver = null;  
//	}
	public static void quit() {
		try {
			Browser.driver.quit();
		}catch (UnreachableBrowserException eQuit){
			Thread.currentThread().interrupt();
			Browser.driver.quit();
		}
		Browser.driver = null;
	}
}