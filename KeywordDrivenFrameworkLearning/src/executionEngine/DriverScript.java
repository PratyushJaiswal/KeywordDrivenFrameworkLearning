package executionEngine;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;

import utility.ExcelUtility;
import config.ActionKeywords;
import config.Constants;




public class DriverScript{
	
	public static Properties OR; 
	public static ActionKeywords actionkeywords;
	public static String sActionKeyword;
	public static String sPageObject;
	public static Method method[];
	
	public static int iTestSteps;
	public static int iTestLastStep;
	public static String sTestCaseID;
	public static String sRunmode;
	
	public DriverScript() throws NoSuchMethodException, SecurityException{
		actionkeywords = new ActionKeywords();
		method = actionkeywords.getClass().getMethods();
	}
	public static void main(String [] args) throws Exception{
		ExcelUtility.setExcelFile(Constants.Path_TestData);
		
		//Declaring or path 
		String Path_OR = Constants.Path_OR;
		//Creating file system object for Object Repository text\property file 
		FileInputStream fs = new FileInputStream(Path_OR);
		//Creating an object of properties 
		OR = new Properties(System.getProperties());
		//Loading all the properties from the 
		OR.load(fs);
		
		DriverScript startengine = new DriverScript();
		startengine.execute_TestCase();
		/*for(int iRow = 1; iRow<=9; iRow++)
		{
			sActionKeyword = ExcelUtility.getCellData(iRow, Constants.Col_ActionKeyword);
			sPageObject = ExcelUtility.getCellData(iRow, Constants.Col_PageObject);
			execute_Actions();
			
		}*/
	}
	private void execute_TestCase() throws Exception {
		// TODO Auto-generated method stub
		// THis will return total number of test cases mentioned in the tetst cases sheet 
		int iTotalTestCases= ExcelUtility.getRowCount(Constants.Sheet_TestCases);
		// This loop will execute number of times equal to Total number of test cases 
		for(int iTestcase = 1; iTestcase<= iTotalTestCases;iTestcase++){
			// This is to get the Test case name fromthe test cases sheet 
			sTestCaseID = ExcelUtility.getCellData(iTestcase, Constants.Col_TestCaseID, Constants.Sheet_TestCases);
			//This is to get the valure of the Run mode column from the current test case 
			sRunmode = ExcelUtility.getCellData(iTestcase, Constants.Col_RunMode, Constants.Sheet_TestCases);
			if(sRunmode.equals("Yes")){
				//Only if the value is equals to the yes then only test steps will be executed 
				iTestSteps = ExcelUtility.getRowContains(sTestCaseID, Constants.Col_TestCaseID, Constants.Sheet_TestSteps);
				iTestLastStep = ExcelUtility.getTestStepsCount(Constants.Sheet_TestSteps, sTestCaseID, iTestSteps);
				// This loop will execute number of times equal to Total number of test steps 
				for (;iTestSteps<=iTestLastStep;iTestSteps++){
					sActionKeyword = ExcelUtility.getCellData(iTestSteps, Constants.Col_ActionKeyword, Constants.Sheet_TestSteps);
					sPageObject = ExcelUtility.getCellData(iTestSteps, Constants.Col_PageObject, Constants.Sheet_TestSteps);
					execute_Actions();
				}
			}
			
		}
		
	}
	private static void execute_Actions() throws Exception {
		// TODO Auto-generated method stub
		for(int i= 0; i<method.length; i++){
			if(method[i].getName().equals(sActionKeyword)){
				
				method[i].invoke(actionkeywords, sPageObject);
				break;
			}
		}
		
			

		
	}
}