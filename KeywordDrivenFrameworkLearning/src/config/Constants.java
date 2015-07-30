package config;

public class Constants {
//This is the class where we store the constants value such as the file paths we will be using this code 
	
	public static final String URL = "http://www.store.demoqa.com";
	public static final String Path_TestData = System.getProperty("user.dir")+ "//src//dataEngine//DataEngine.xlsx";
	public static final String Path_OR = System.getProperty("user.dir") + "//src//config//OR.txt";
	public static final String file_TestData = "DataEngine.xlsx";
	
	
	//List of the data sheet column numbers 
	
	public static final int Col_TestCaseID = 0;
	public static final int Col_TestScenariousID = 1;
	public static final int Col_ActionKeyword = 4;
	public static final int Col_PageObject = 3;
	public static final int Col_RunMode =2;
	
	//List of Data Engine Excel Sheet 
	public static final String Sheet_TestSteps ="TestSteps";
	public static final String Sheet_TestCases ="Test Cases";
	//List of Test Data 
	
	public static final String UserName = "softway_1";
	public static final String Password = "test@123";
	
}
