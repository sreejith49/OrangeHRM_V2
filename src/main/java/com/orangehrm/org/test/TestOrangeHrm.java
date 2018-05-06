package com.orangehrm.org.test;

import java.io.File;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.testng.annotations.Test;

import com.orangehrm.org.AppUtilities;
import com.orangehrm.org.Core;
import com.orangehrm.org.ExcelUtilities;

public class TestOrangeHrm extends Core{
	
	//@Test
	public void RegressionSuite(){
		
		try {
			
			Sheet scenarioSheet = ExcelUtilities.initiateSheet(ExcelUtilities.initiateWorkbook(new File("TestData/testData.xlsx")), "TestScenarios");
			Sheet casesSheet = ExcelUtilities.initiateSheet(ExcelUtilities.initiateWorkbook(new File("TestData/testData.xlsx")), "TestCases");
			
			//Scenario variables declaration
			String scenarioId = null;
			String status = null;
			String runMode = null;
			
			//Cases variables declaration
			String testStep = null;
			String casesRunMode = null; 
			
			
			int rowCount = scenarioSheet.getPhysicalNumberOfRows();
			
			for (int row = 1; row < rowCount; row++) {
				
				int columnCount = ExcelUtilities.getExcelRow(scenarioSheet, row).getPhysicalNumberOfCells();
				
				//Scenario variables
				scenarioId = ExcelUtilities.getCellData(scenarioSheet, row, 0);
				status = ExcelUtilities.getCellData(scenarioSheet, row, 2);
				runMode = ExcelUtilities.getCellData(scenarioSheet, row, 3);
				
				//Cases variables
				
				if(runMode.equalsIgnoreCase("Yes")){
										
					int casesRowCount = casesSheet.getPhysicalNumberOfRows();
					
					for (int cRow = 1; cRow < casesRowCount; cRow++) {
						
						casesRunMode = ExcelUtilities.getCellData(casesSheet, cRow, 5);
						testStep = ExcelUtilities.getCellData(casesSheet, cRow, 3);
						
						if(ExcelUtilities.getCellData(casesSheet, cRow, 0).equalsIgnoreCase(scenarioId)){
							
							if(casesRunMode.equalsIgnoreCase("Yes")){
								System.out.println(testStep);
								
								
								String[] arrTestStep = testStep.split("\\|");
								
								for (int i = 0; i < arrTestStep.length; i++) {
									System.out.println(arrTestStep[i]);
									
									String[] stepData = arrTestStep[i].split("\\:");
									
									//AppUtilities.executeObjectAction(stepData[0], stepData[1]);
									
								}
							}
							
						}
						
					}
				}
				else
					System.out.println("No need to execute");
			}
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
