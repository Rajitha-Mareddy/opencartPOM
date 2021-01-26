package com.qa.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	
	private static String TEST_DATA_SHEET_PATH = "C:\\JAVA Training\\hubspotPOMDesign\\src\\main\\resources\\hubspot_TestData.xlsx";
	static Workbook book;
	static Sheet sheet; 
	
	public static Object[][] getTestData(String SheetName) {
		Object data[][]=null;
		try {
			FileInputStream ip = new FileInputStream(TEST_DATA_SHEET_PATH);
			try {
				book = WorkbookFactory.create(ip);
				sheet = book.getSheet(SheetName);	
				data= new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
				
				for(int i=0;i<sheet.getLastRowNum();i++) {
					for(int j=0;i<sheet.getRow(0).getLastCellNum();j++)
					{
						data[i][j] = sheet.getRow(i+1).getCell(i).toString();
					}
				}
				
			} catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
		
		
		
		
	}
	
	
	
	
	
	
	

}
