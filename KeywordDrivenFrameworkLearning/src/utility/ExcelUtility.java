package utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import config.Constants;
import executionEngine.DriverScript;

public class ExcelUtility {
	 private static XSSFSheet ExcelWSheet;
     private static XSSFWorkbook ExcelWBook;
     private static XSSFCell Cell;
     //private static org.apache.poi.ss.usermodel.Cell Cell;
     private static XSSFRow Row;
 //This method is to set the File path and to open the Excel file
 //Pass Excel Path and SheetName as Arguments to this method
 /*public static void setExcelFile(String Path,String SheetName) throws Exception {
         FileInputStream ExcelFile = new FileInputStream(Path);
         ExcelWBook = new XSSFWorkbook(ExcelFile);
         ExcelWSheet = ExcelWBook.getSheet(SheetName);
        }*/
     // THis method is to set to read the Test data from the Excel cell 
     public static void setExcelFile(String Path) throws Exception{
    	 try {
				FileInputStream ExcelFile = new FileInputStream(Path);
				ExcelWBook = new XSSFWorkbook(ExcelFile);
			} catch (Exception e){
				Log.error("Class Utils | Method setExcelFile | Exception desc : "+e.getMessage());
				DriverScript.bResult = false;
				}
     }

 //This method is to read the test data from the Excel cell
 //In this we are passing parameters/arguments as Row Num and Col Num
	 public static String getCellData(int RowNum, int ColNum, String SheetName) throws Exception{
		 try{
				ExcelWSheet = ExcelWBook.getSheet(SheetName);
				Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
				String CellData = Cell.getStringCellValue();
				return CellData;
			 }catch (Exception e){
				 Log.error("Class Utils | Method getCellData | Exception desc : "+e.getMessage());
				 DriverScript.bResult = false;
				 return"";
				 }
	 	}
	 	 //This method is to get the row count used of the excel sheet 
	 public static int getRowCount(String SheetName){
		 int iNumber=0;
			try {
				ExcelWSheet = ExcelWBook.getSheet(SheetName);
				iNumber=ExcelWSheet.getLastRowNum()+1;
			} catch (Exception e){
				Log.error("Class Utils | Method getRowCount | Exception desc : "+e.getMessage());
				DriverScript.bResult = false;
				}
			return iNumber;
	 }
	 
	 // This method is to get the row number of the test case 
	 public static int getRowContains(String sTestCaseName, int colNum, String SheetName) throws Exception{
		 int iRowNum=0;	
			try {
				//ExcelWSheet = ExcelWBook.getSheet(SheetName);
				int rowCount = ExcelUtility.getRowCount(SheetName);
				for (; iRowNum<rowCount; iRowNum++){
					if  (ExcelUtility.getCellData(iRowNum,colNum,SheetName).equalsIgnoreCase(sTestCaseName)){
						break;
					}
				}       			
			} catch (Exception e){
				Log.error("Class Utils | Method getRowContains | Exception desc : "+e.getMessage());
				DriverScript.bResult = false;
				}
			return iRowNum;
	 }
	 
	 //This method is to get the count of the test Steps of test cases 
	 public static int getTestStepsCount(String SheetName, String sTestCaseID, int iTestCaseStart) throws Exception{
		 try {
				for(int i=iTestCaseStart;i<=ExcelUtility.getRowCount(SheetName);i++){
					if(!sTestCaseID.equals(ExcelUtility.getCellData(i, Constants.Col_TestCaseID, SheetName))){
						int number = i;
						return number;      				
						}
					}
				ExcelWSheet = ExcelWBook.getSheet(SheetName);
				int number=ExcelWSheet.getLastRowNum()+1;
				return number;
			} catch (Exception e){
				Log.error("Class Utils | Method getRowContains | Exception desc : "+e.getMessage());
				DriverScript.bResult = false;
				return 0;
			}
	 }
	 
	 public static void setCellData(String Result, int RowNum, int ColNum, String SheetName){
		 
		 try{
			 
			 ExcelWSheet = ExcelWBook.getSheet(SheetName);
			 Row = ExcelWSheet.getRow(RowNum);
			 Cell = Row.getCell(ColNum, org.apache.poi.ss.usermodel.Row.RETURN_BLANK_AS_NULL);
			 if(Cell == null){
				 Cell = Row.createCell(ColNum);
				 Cell.setCellValue(Result);
			 } else {
				 Cell.setCellValue(Result);
			 }
			 
			 FileOutputStream fileOut = new FileOutputStream(Constants.Path_TestData);
			 ExcelWBook.write(fileOut);
			 
			 fileOut.close();
			 ExcelWBook = new XSSFWorkbook(new FileInputStream(Constants.Path_TestData));
		 }catch(Exception e){
			 DriverScript.bResult = false;
		 }
	 }

}
