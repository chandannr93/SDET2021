package com.vtiger.comcast.genericUtilty;


import java.io.FileInputStream;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class will give the excel sheet required cell value(data)
 * @author Chandan
 *
 */
public class ExcelUtility {

	/**
	 * This method will return the cell value of the given Sheet Name
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @return
	 */
    public String getDataFromExcelSheet(String sheetName, int rowNum, int cellNum) throws Throwable
    {
    	FileInputStream fis = new FileInputStream("./src/test/resources/data.xlsx");
    	Workbook wb = WorkbookFactory.create(fis);
    	Sheet sheet = wb.getSheet(sheetName);
    	Row row = sheet.getRow(rowNum);
    	Cell cell = row.getCell(cellNum);
    	String value = cell.getStringCellValue();
    	wb.close();
    	return value;
    }
    
    /**
     * This method will return the last Row Number
     * @param sheetName
     * @throws Throwable
     */
    public void getexcelSheetLastRowNum(String sheetName) throws Throwable
    {
    	FileInputStream fis = new FileInputStream("./src/test/resources/data.xlsx");
    	Workbook wb = WorkbookFactory.create(fis);
    	Sheet sheet = wb.getSheet(sheetName);
    	wb.close();
    	int row = sheet.getLastRowNum();
    	
    	
    }
    
	
}