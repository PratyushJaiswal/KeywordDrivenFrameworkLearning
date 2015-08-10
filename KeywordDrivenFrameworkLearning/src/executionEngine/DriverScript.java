package executionEngine;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;

import utility.ExcelUtility;
import utility.Log;
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
	public static boolean bResult;
	public static String sData;
	
	public DriverScript() throws NoSuchMethodException, SecurityException{
		actionkeywords = new ActionKeywords();
		method = actionkeywords.getClass().getMethods();
	}
	public static void main(String [] args) throws Exception{
		ExcelUtility.setExcelFile(Constants.Path_TestData);
		
		//This is to start log 4j 
		DOMConfigurator.configure("log4j.xml");
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
		
	}
	private void execute_TestCase() throws Exception {
		// TODO Auto-generated method stub
		// THis will return total number of test cases mentioned in the tetst cases sheet 
		int iTotalTestCases= ExcelUtility.getRowCount(Constants.Sheet_TestCases);
		// This loop will execute number of times equal to Total number of test cases 
		for(int iTestcase = 1; iTestcase< iTotalTestCases;iTestcase++){
			// This is to get the Test case name fromthe test cases sheet 
			bResult = true;
			sTestCaseID = ExcelUtility.getCellData(iTestcase, Constants.Col_TestCaseID, Constants.Sheet_TestCases);
			//This is to get the valure of the Run mode column from the current test case 
			sRunmode = ExcelUtility.getCellData(iTestcase, Constants.Col_RunMode, Constants.Sheet_TestCases);
			if(sRunmode.equals("Yes")){
				Log.startTestCase(sTestCaseID);
				//Only if the value is equals to the yes then only test steps will be executed 
				iTestSteps = ExcelUtility.getRowContains(sTestCaseID, Constants.Col_TestCaseID, Constants.Sheet_TestSteps);
				System.out.println(iTestSteps);
				iTestLastStep = ExcelUtility.getTestStepsCount(Constants.Sheet_TestSteps, sTestCaseID, iTestSteps);
				// This loop will execute number of times equal to Total number of test steps 
				System.out.println(iTestLastStep);
				
				//Setting the value as true before starting any test case
				bResult = true;
				
				for (;iTestSteps<iTestLastStep;iTestSteps++){
					sActionKeyword = ExcelUtility.getCellData(iTestSteps, Constants.Col_ActionKeyword, Constants.Sheet_TestSteps);
					sPageObject = ExcelUtility.getCellData(iTestSteps, Constants.Col_PageObject, Constants.Sheet_TestSteps);
					sData = ExcelUtility.getCellData(iTestSteps, Constants.Col_DataSet, Constants.Sheet_TestSteps);
					execute_Actions();
					//This is the result code which will execute every test step
					//the execution flow go normally if the value of the false 
					if (bResult == false){
						System.out.println("This is if the programs fails");
						// If false then store the test value as false 
						ExcelUtility.setCellData(Constants.Keyword_Fail, iTestcase, Constants.Col_Result, Constants.Sheet_TestCases);
						// end the test case in the logs 
						Log.endTestCase(sTestCaseID);
						//by the break execution will not flow 
						break;
					}
				}
				if (bResult == true){
					// Storing the result as pass if the test case executes 
					ExcelUtility.setCellData(Constants.Keyword_Fail, iTestcase, Constants.Col_Result, Constants.Sheet_TestCases);
					Log.endTestCase(sTestCaseID);
				}
			}
			
		}
		
	}
	private static void execute_Actions() throws Exception {
		// TODO Auto-generated method stub
		for(int i= 0; i<method.length; i++){
			System.out.println("method name "+method[i].getName());
			System.out.println("action keyword  "+sActionKeyword);
			//System.out.println(sPageObject);
			if(method[i].getName().equalsIgnoreCase(sActionKeyword)){
				System.out.println("matching criteria");
				System.out.println(bResult);
				method[i].invoke(actionkeywords, sPageObject, sData);
				if(bResult==true){
					System.out.println("enter in the execute loop true loop");
					ExcelUtility.setCellData(Constants.Keyword_Pass, iTestSteps, Constants.Col_TestStepResult, Constants.Sheet_TestSteps);
					break;
				} else { 	
					//executed steps fail it will update fail in the result section 
					ExcelUtility.setCellData(Constants.Keyword_Fail, iTestSteps, Constants.Col_TestStepResult, Constants.Sheet_TestSteps);
					System.out.println("enter in the execute loop");
					// In case of the failure of a test case browser will not close so it makes sense to close the browser 
					ActionKeywords.closeBrowser("","");
					break;
				}
			}
		}
		
			

		
	}
}