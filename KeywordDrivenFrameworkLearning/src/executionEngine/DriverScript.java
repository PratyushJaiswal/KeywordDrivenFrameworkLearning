package executionEngine;

import config.ActionKeywords;
import utility.ExcelUtility;

import org.openqa.selenium.WebDriver;

public class DriverScript {
	 private static WebDriver driver = null;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
			String sPath = System.getProperty("user.dir") + "//src//dataEngine//DataEngine.xlsx";
			
			ExcelUtility.setExcelFile(sPath, "TestSteps");
			
			for(int iRow=1; iRow<=9;iRow++){
				
				String sActionKeywords = ExcelUtility.getCellData(iRow, 3);
				
				//comparing the value of the excel sheeet 
				if(sActionKeywords.equals("OpenBrowser")){
					
					ActionKeywords.openBrowser();
				}
				else if(sActionKeywords.equals("navigate")){
					ActionKeywords.navigate();}
				else if(sActionKeywords.equals("click_MyAccount")){
					ActionKeywords.click_MyAccount();}
				else if(sActionKeywords.equals("input_Username")){
					ActionKeywords.input_Username();}
				else if(sActionKeywords.equals("input_password")){
					ActionKeywords.input_Password();;}
				else if(sActionKeywords.equals("click_Login")){
					ActionKeywords.click_Login();}
				else if(sActionKeywords.equals("waitFor")){
					ActionKeywords.waitFor();}
				else if(sActionKeywords.equals("click_Logout")){
					ActionKeywords.click_Logout();}
				else if(sActionKeywords.equals("closeBrowser")){
					ActionKeywords.closeBrowser();}
			
			}
			
				
				
			
	}
			
		
		
		/*	driver = new FirefoxDriver();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        driver.get("http://www.store.demoqa.com");
	 
	        driver.findElement(By.xpath(".//*[@id='account']/a")).click();
	        driver.findElement(By.id("log")).sendKeys("softway_1"); 
	        driver.findElement(By.id("pwd")).sendKeys("Test@123");
	        driver.findElement(By.id("login")).click();
	        driver.findElement(By.xpath(".//*[@id='account_logout']/a")).click();
	        driver.quit();*/
	            

	

}
