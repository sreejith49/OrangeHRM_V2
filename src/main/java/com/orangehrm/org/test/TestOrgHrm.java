package com.orangehrm.org.test;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.testng.annotations.Test;

import com.orangehrm.org.AppUtilities;
import com.orangehrm.org.Core;
import com.orangehrm.org.ExcelUtilities;

public class TestOrgHrm extends Core {
	
	
	//Created this class to store data in collections and execute from it instead of from excel sheets
	
	int Test_Scenario_Id = 0;
	int Test_cases_Id = 1;	
	int Description = 2;
	int Test_Steps = 3;
	int Status = 4;
	int Run_Mode = 5;
	
	
	//@Test
	public void RegressionSuite(){
		
		try {
			
			Sheet scenarioSheet = ExcelUtilities.initiateSheet(ExcelUtilities.initiateWorkbook(new File("TestData/testData.xlsx")), "TestScenarios");
			Sheet casesSheet = ExcelUtilities.initiateSheet(ExcelUtilities.initiateWorkbook(new File("TestData/testData.xlsx")), "TestCases");
			
			String[][] str = ExcelUtilities.convertExcelData(casesSheet);
			
			List<String> scenarioData;
			List<String> casesData=null;
			
			//Map<Integer, List<String>> casesMap = new HashMap<Integer, List<String>>();
			
			
			Map<String, List<String>> casesMap = new LinkedHashMap<String, List<String>>(); //giving a try with testcases as key
									
			for (int i = 0; i < str.length; i++) {
				casesData = Arrays.asList(str[i]); //storing an array data into list

				casesMap.put(str[i][1], casesData); // storing list data and key into map
			}
			
			List<String> fCasesData;
			
			for (Map.Entry entry : casesMap.entrySet()) {
				
			   System.out.println("Fetching detail of "+entry.getKey());
			   
			   fCasesData = casesMap.get(entry.getKey().toString());
			   
			   if(fCasesData.get(5).equalsIgnoreCase("Yes")){ //check for runmode as Yes
				   
				   System.out.println("Description: "+fCasesData.get(Description));
				   
				   String[] testStep = fCasesData.get(Test_Steps).split("\\|");
				   
				   for (int i = 0; i < testStep.length; i++) {
					   
					   String[] stepData = testStep[i].split("\\:");
					   System.out.println(stepData[0]+":#:"+stepData[1]);
					   
					   //AppUtilities.executeObjectAction(stepData[0], stepData[1]);
					
				   }
				   
			   }
			   
			
			   //System.out.println("########################");
			
			   	
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/*public String[][] convertExcelData(Sheet sheet){
		
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
	}*/

}
