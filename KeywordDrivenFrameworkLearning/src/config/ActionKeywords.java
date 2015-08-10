package config;

import java.util.concurrent.TimeUnit;

import static executionEngine.DriverScript.OR;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import executionEngine.DriverScript;
import utility.Log;

public class ActionKeywords {

	public static WebDriver driver;

	public static void openBrowser(String object, String data){
		Log.info("Opening Browser");
		try{
			// if the valye in the data set is the Mozilla 
			if(data.equals("Mozilla")){
				driver=new FirefoxDriver();
				Log.info("mozilla browser started");
			}
			else if (data.equals("IE")){
				driver = new InternetExplorerDriver();
				Log.info("IE browser started");
			}
			else if (data.equals("chrome")){
				driver = new ChromeDriver();
				Log.info("chrome browser started ");
			}
			
			int implicitWaitTime = (10);
			driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);

			//This block will execute only in case of an exception
		}catch(Exception e){
			//This is to print the logs - Method Name & Error description/stack
			Log.info("Not able to open Browser --- " + e.getMessage());
			//Set the value of result variable to false
			DriverScript.bResult = false;
		}
	}

	public static void navigate(String object, String data){
		try{
			Log.info("Navigating to URL");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(Constants.URL);
		}catch(Exception e){
			Log.info("Not able to navigate --- " + e.getMessage());
			DriverScript.bResult = false;
		}
	}

	public static void click(String object, String data){
		try{
			Log.info("Clicking on Webelement "+ object);
			driver.findElement(By.xpath(OR.getProperty(object))).click();
		}catch(Exception e){
			Log.error("Not able to click --- " + e.getMessage());
			DriverScript.bResult = false;
		}
	}

	public static void input(String object, String data){
		try{
			Log.info("Entering the text in UserName");
			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
		}catch(Exception e){
			Log.error("Not able to Enter UserName --- " + e.getMessage());
			DriverScript.bResult = false;
		}
	}

	/*public static void input_Password(String object){
		try{
			Log.info("Entering the text in Password");
			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(Constants.Password);
		}catch(Exception e){
			Log.error("Not able to Enter Password --- " + e.getMessage());
			DriverScript.bResult = false;
		}
	}
*/
	public static void waitFor(String object, String data) throws Exception{
		try{
			Log.info("Wait for 5 seconds");
			Thread.sleep(5000);
		}catch(Exception e){
			Log.error("Not able to Wait --- " + e.getMessage());
			DriverScript.bResult = false;
		}
	}

	public static void closeBrowser(String object, String data){
		try{
			Log.info("Closing the browser");
			driver.quit();
		}catch(Exception e){
			Log.error("Not able to Close the Browser --- " + e.getMessage());
			DriverScript.bResult = false;
		}
	}

}
