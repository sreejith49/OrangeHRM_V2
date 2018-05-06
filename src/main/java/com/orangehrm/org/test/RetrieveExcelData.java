package com.orangehrm.org.test;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.orangehrm.org.AppUtilities;
import com.orangehrm.org.Core;
import com.orangehrm.org.ExcelUtilities;

public class RetrieveExcelData extends Core {
	
	final String TestScenarioSheet = "TestScenarios";
	final String TestCasesSheet = "TestCases";
	final String TestDataSheet = "TestData";
	final String MappingSheet = "MappingSheet";
	public static Map<String, List<String>> testData = null;
	
	@Test
	public void readTestSettings(){
		
		try {
			
			
			//##### read start: testsettings.xlsx file #####
			Sheet testSettingSheet = ExcelUtilities.initiateSheet(ExcelUtilities.initiateWorkbook(new File("TestData/Test_Settings.xlsx")), "TestSettings");
			
			String[][] testSettingData = ExcelUtilities.convertExcelData(testSettingSheet,3);
			
			Map<String, List<String>> testSettings = new LinkedHashMap<String, List<String>>();
			List<String> lvalue = null;
			
			for (int rowindex = 0; rowindex < testSettingData.length; rowindex++) {
				lvalue = new LinkedList<String>();				
				
				for (int columnindex = 2; columnindex < testSettingData[rowindex].length; columnindex++) {
						
					lvalue.add(testSettingData[rowindex][columnindex]);
				}
				
				testSettings.put(testSettingData[rowindex][1], lvalue);
			}
			
			
			
			testSettingData = null; //releasing 2d primitive array resource
			
			//##### read complete: testsettings.xlsx file #####
			//##### Map testSettings holds the data retrieved from testSettings.xlsx #####
			
			String FileName = null; //This variable will hold the test file name to execute
			String ObjectRepository = null; //This variable holds the test object repository details
			
			//#### Iterate through test settings map #####
			for (Map.Entry<String, List<String>> entry : testSettings.entrySet()) {
				
				if(entry.getValue().get(2).equalsIgnoreCase("Yes")){
					
					FileName = entry.getValue().get(0);
					ObjectRepository = entry.getValue().get(1);
					Core.ObjectRepo = ObjectRepository;
					
					//##### read start: ts sheet #####
					Sheet testScenarioSheet = ExcelUtilities.initiateSheet(ExcelUtilities.initiateWorkbook(new File("TestData/"+FileName)), TestScenarioSheet);
					
					String[][] testScenarioData = ExcelUtilities.convertExcelData(testScenarioSheet,1);
					
					Map<String, List<String>> testScenarios = new LinkedHashMap<String, List<String>>();
					List<String> scenariovalues = null;
					
					for (int i = 0; i < testScenarioData.length; i++) {
						scenariovalues = new LinkedList<String>();
						for (int j = 1; j < testScenarioData[i].length; j++) {
							
							scenariovalues.add(testScenarioData[i][j]);
						}
						testScenarios.put(testScenarioData[i][0], scenariovalues);
					}
					
					testScenarioData = null; //releasing 2d primitive array resource
					
					//##### read complete: ts sheet #####
					//##### Map testScenarios holds the data retrieved from testScenarios sheet #####
					
					//##### read start: test cases sheet #####
					Sheet testCasesSheet = ExcelUtilities.initiateSheet(ExcelUtilities.initiateWorkbook(new File("TestData/"+FileName)), TestCasesSheet);
					
					String[][] testCasesData = ExcelUtilities.convertExcelData(testCasesSheet,1);
					
					Map<String, List<String>> testCases = new LinkedHashMap<String, List<String>>();
					List<String> Casesvalues = null;
					
					for (int rowindex = 0; rowindex < testCasesData.length; rowindex++) {
						Casesvalues = new LinkedList<String>();
						for (int columnindex = 0; columnindex < testCasesData[rowindex].length; columnindex++) {
							
							Casesvalues.add(testCasesData[rowindex][columnindex]);
						}
						testCases.put(testCasesData[rowindex][0]+testCasesData[rowindex][1]+testCasesData[rowindex][2], Casesvalues);
					}
					
					testCasesData = null; //releasing 2d primitive array resource
					
					//##### read complete: testCases sheet #####
					//##### Map testCases holds the data retrieved from testCases sheet #####
					
					//##### read start: test data sheet #####
					Sheet testDataSheet = ExcelUtilities.initiateSheet(ExcelUtilities.initiateWorkbook(new File("TestData/"+FileName)), TestDataSheet);
					
					testData = new LinkedHashMap<String, List<String>>();
					List<String> datavalues = null;

					testData = ExcelUtilities.insertExcelDataInToMap(testDataSheet, 0);
					
					//##### read completed: test data sheet #####
					//##### Map testData holds the data retrieved from testData sheet #####
										
					//##### Iterate through testScenarios map #####
					for (Map.Entry<String, List<String>> tsentry : testScenarios.entrySet()) {
						
						if(tsentry.getValue().get(2).equalsIgnoreCase("Yes")){
							System.out.println(tsentry.getKey());
							
							//##### Iterate through testCases map #####
							for (Map.Entry<String, List<String>> tcentry : testCases.entrySet()) {
								
								if(tcentry.getValue().get(0).equalsIgnoreCase(tsentry.getKey())){
									
									if(tcentry.getValue().get(6).equalsIgnoreCase("Yes")){
										
										//System.out.println(tcentry.getValue().get(4));
										
										String[] testStep = tcentry.getValue().get(4).split("\\|"); // fCasesData.get(Test_Steps).split("\\|");
										   
										   for (int i = 0; i < testStep.length; i++) {
											   
											   String[] stepData = testStep[i].split("\\:");
											   System.out.println(stepData[0]+":#:"+stepData[1]);
											   
											   AppUtilities.executeObjectAction(stepData[0], stepData[1],tcentry.getValue().get(3));
											
										   }
										   
										   
									}
									
								}
								
							}
						}
					}
						
						
					
					
				}
			}
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//@Test
	public void testDyn(){
		
		try {
			List<WebElement> tablecust = driver.findElements(By.id("customers"));
			List<String> str = new LinkedList<String>();
			for (WebElement webElement : tablecust) {
				
				List<WebElement> tablerow = webElement.findElements(By.tagName("tr"));
				
				
				
				for (int i = 0; i < tablerow.size(); i++) {
					
					List<WebElement> tablecolumn = tablerow.get(i).findElements(By.tagName("td"));
					
					for (int j = 0; j < tablecolumn.size(); j++) {
						
						System.out.println(tablecolumn.get(j).getText());
						
						if(tablecolumn.get(j).getText().equalsIgnoreCase("Manu")){
							
							if(tablecolumn.get(j).getText()!=null)
								str.add(tablecolumn.get(j).getText());
							//System.out.println(tablecolumn.get(j).findElement(By.id("ohrmList_chkSelectAll")).getText());
							tablecolumn.get(5).findElement(By.id("btn")).click();
							Alert alert = driver.switchTo().alert();
							alert.accept();
							tablecolumn.get(0).findElement(By.cssSelector("input[type='checkbox']")).click();
							
							
						}
						
						if(tablecolumn.get(j).getText().equalsIgnoreCase("Dell")){
							
							
							//tablecolumn.get(1).findElement(By.cssSelector("input[type='checkbox']")).click();
						}
						
					}
					
				}
				
			}
			
			
			List<WebElement> divcollection = driver.findElements(By.id("SpecialPan"));
			
			for (int i = 0; i < divcollection.size(); i++) {
				
				System.out.println(divcollection.get(i).getText());
				
				if(divcollection.get(i).getText().contains("SH-0003")){
					
					List<WebElement> tbl = divcollection.get(i).findElements(By.id("customers"));
					List<WebElement> tblrow = tbl.get(1).findElements(By.tagName("tr"));
					List<WebElement> tblcolumn = tblrow.get(5).findElements(By.tagName("td"));
					tblcolumn.get(1).findElement(By.cssSelector("input[type='checkbox']")).click();
					
					/*for (WebElement webElement : tbl) {
						
						List<WebElement> tblrow = webElement.findElements(By.tagName("tr"));
						
						for (int j = 0; j < tblrow.size(); j++) {
							
							List<WebElement> tblcolumn = webElement.findElements(By.tagName("td"));
							
							for (int k = 0; k < tblcolumn.size(); k++) {
								
								if(tblcolumn.get(k).getText().contains("Girish")){
									
									tblcolumn.get(k).findElement(By.cssSelector("input[type='checkbox']")).click();
								}
								
							}
							
							
						}
					}*/
					
					
					if(divcollection.get(i).getText().contains("Girish")){
						System.out.println("::>>"+divcollection.get(i).getText());
						//divcollection.get(i).findElement(By.cssSelector("input[type='checkbox']")).click();
						//divcollection.get(i).findElement(By.tagName("input")).click();
					}
					
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}
