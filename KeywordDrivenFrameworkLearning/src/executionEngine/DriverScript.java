package executionEngine;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverScript {
	 private static WebDriver driver = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			driver = new FirefoxDriver();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        driver.get("http://www.store.demoqa.com");
	 
	        driver.findElement(By.xpath(".//*[@id='account']/a")).click();
	        driver.findElement(By.id("log")).sendKeys("softway_1"); 
	        driver.findElement(By.id("pwd")).sendKeys("Test@123");
	        driver.findElement(By.id("login")).click();
	        driver.findElement(By.xpath(".//*[@id='account_logout']/a")).click();
	        driver.quit();
	            

	}

}
