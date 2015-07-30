package config;

import java.util.concurrent.TimeUnit;
import static executionEngine.DriverScript.OR;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ActionKeywords {
	public static WebDriver driver;
	 
	public static void openBrowser(){		
		driver=new FirefoxDriver();
		}
 
	public static void navigate(){	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(Constants.URL);
		}
 
	public static void click(String object){
		//This is fetching the xpath of the element from the Object Repository property file
		driver.findElement(By.xpath(OR.getProperty(object))).click();
		}
	public static void input_Username(String object){
		driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(Constants.UserName); 
		}
 
	public static void input_Password(String object){
		driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(Constants.Password);
		}
 
	
 
	public static void waitFor() throws Exception{
		Thread.sleep(5000);
		}
 
	
 
	public static void closeBrowser(){
			driver.quit();
		}
 
	
}
