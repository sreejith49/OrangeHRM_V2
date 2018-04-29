package com.orangehrm.org;

import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
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
	
	public static String[][] convertExcelData(Sheet sheet){
		
		String[][] str = null;
		
		try {	
			
			
			int rowCount = sheet.getPhysicalNumberOfRows();
			int columnCount = ExcelUtilities.getExcelRow(sheet, 1).getPhysicalNumberOfCells();
			str = new String[rowCount][columnCount];
			
			for (int row = 0; row < rowCount; row++) {
				
				columnCount = ExcelUtilities.getExcelRow(sheet, row).getPhysicalNumberOfCells();
				
				for (int column = 0; column < columnCount; column++) {
					
					str[row][column] = ExcelUtilities.getCellData(sheet, row, column);
					
				}
				
			}		
			
			
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return str;
	}
	
	public static String[][] convertExcelData(Sheet sheet, int startrownum){
		
		String[][] str = null;
		
		try {	
			
			List<String> lstr = new LinkedList<String>();
			int rowCount = sheet.getPhysicalNumberOfRows();
			int columnCount = ExcelUtilities.getExcelRow(sheet, 1).getPhysicalNumberOfCells();
			str = new String[rowCount-startrownum][columnCount];
			int arrayrownum =0;
			for (int row = startrownum; row < rowCount; row++) {
				
				columnCount = ExcelUtilities.getExcelRow(sheet, row).getPhysicalNumberOfCells();
				
				for (int column = 0; column < columnCount; column++) {
					
					str[arrayrownum][column] = ExcelUtilities.getCellData(sheet, row, column);
					lstr.add(ExcelUtilities.getCellData(sheet, row, column));
				}
				
				arrayrownum++;
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return str;
	}
	
	public static Map<String, List<String>> insertExcelDataInToMap(Sheet sheet, int columnindexofmapkeyvalue){
		
		Map<String, List<String>> map= null;
		List<String> lstr = null;
		
		try {	
			
			map = new LinkedHashMap<String, List<String>>();
			
			int skip =0;
			for (int row = 0; row < sheet.getPhysicalNumberOfRows(); row++) {
				lstr = new LinkedList<String>();
				int columnCount = ExcelUtilities.getExcelRow(sheet, row).getPhysicalNumberOfCells();
				
				for (int column = 0; column < columnCount; column++) {
					
					if(ExcelUtilities.getCellData(sheet, row, column) != "")
						lstr.add(ExcelUtilities.getCellData(sheet, row, column));
					else
						skip=1;
					
					
				}
				
				if(skip != 1)
					map.put(lstr.get(0), lstr);
				
				skip=0;
			}			
		
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}

}
