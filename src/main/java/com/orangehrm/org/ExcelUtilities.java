package com.orangehrm.org;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {
	
	public static XSSFWorkbook initiateWorkbook(File file){
		XSSFWorkbook workbook = null;
		FileInputStream fis;
		
		
		try {
			//XSSFWorkbook workbook2 = new XSSFWorkbook(new FileInputStream(new File("")));
			
			fis = new FileInputStream(file);
			workbook = new XSSFWorkbook(fis);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return workbook;
	}
	
	public static Sheet initiateSheet(XSSFWorkbook workbook, String sheetName){
		
		Sheet sheet = null;
		
		try {
			sheet = workbook.getSheet(sheetName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sheet;
	}
	
	public static Row getExcelRow(Sheet sheet, int rowNum){
		
		Row row = null;
		
		try {
			row = sheet.getRow(rowNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return row;
	}
	
	
	public static int getSheetRowCount(Sheet sheet){
		
		int rowCount = -1;
		try {
			rowCount = sheet.getPhysicalNumberOfRows();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rowCount;
	}
	
	
	public static String getCellData(Sheet sheet, int rowNum, int columnNum){
		
		String cellData = null;		
		
		Cell cell = sheet.getRow(rowNum).getCell(columnNum);
		
		try {
			
			switch (cell.getCellType()) {
										
			case Cell.CELL_TYPE_NUMERIC:
				cellData = ""+cell.getNumericCellValue();
				break;
				
			case Cell.CELL_TYPE_STRING:
				cellData = cell.getStringCellValue();
				break;

			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cellData;
		
	}

}
