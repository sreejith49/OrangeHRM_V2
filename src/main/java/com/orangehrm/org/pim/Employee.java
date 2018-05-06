package com.orangehrm.org.pim;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.orangehrm.org.Core;
import com.orangehrm.org.Utilities;

public class Employee extends Core{
	
	public static WebElement selectAnEmployee(WebElement webtable, String employeeName){
		
		WebElement lookupElement = null;
		int empFoundCounter = 0;
		
		try {
			
			List<WebElement> tblRows = webtable.findElements(By.tagName("tr"));
			
			for (WebElement webElement : tblRows) {
				
				if(webElement.getText().contains(employeeName)){
					empFoundCounter = 1;
					List<WebElement> tblColumns = webElement.findElements(By.tagName("td"));
					
					for (WebElement webElement2 : tblColumns) {
						
						if(webElement2.findElement(By.tagName("input")).getAttribute("type").equals("checkbox")){
							
							webElement2.findElement(By.tagName("input")).click();
							
						}
						
						break;				
						
					}
					
				}				
				
			}
			
			if(empFoundCounter == 0)
				System.out.println("Employee '"+employeeName+"' not found");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lookupElement;
	}
	
	
	public static void addEmployee(String firstName, String lastName, String gender, String nationality, ExtentTest logger)throws TimeoutException{
		String testValue=null;
		
		try {
			
			
			
			WebDriverWait wdwait = new WebDriverWait(driver, 170000);
			
			testValue="Enter First name";
			logger.createNode(testValue);
			wdwait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Utilities.RetrievePropertiesValue(Core.ObjectRepo, "addemployee.textbox.fname.id")))).sendKeys(firstName);
			logger.log(Status.PASS, MarkupHelper.createLabel(testValue, ExtentColor.GREEN));
			
			testValue="Enter last name";
			logger.createNode(testValue);
			wdwait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Utilities.RetrievePropertiesValue(Core.ObjectRepo, "addemployee.textbox.lname.id")))).sendKeys(lastName);
			logger.log(Status.PASS, MarkupHelper.createLabel(testValue, ExtentColor.GREEN));
			
			testValue="Click on Save button";
			logger.createNode(testValue);
			wdwait.until(ExpectedConditions.elementToBeClickable(By.id(Utilities.RetrievePropertiesValue(Core.ObjectRepo, "addemployee.button.save.id")))).click();
			logger.log(Status.PASS, MarkupHelper.createLabel(testValue, ExtentColor.GREEN));
			
			testValue="Click on Edit button";
			logger.createNode(testValue);			
			wdwait.until(ExpectedConditions.elementToBeClickable(By.id(Utilities.RetrievePropertiesValue(Core.ObjectRepo, "addemployee.button.save.id")))).click();
			logger.log(Status.PASS, MarkupHelper.createLabel(testValue, ExtentColor.GREEN));
			
			if(gender.equalsIgnoreCase("male")){
				testValue = "Select gender as Male";
				logger.createNode(testValue);
				wdwait.until(ExpectedConditions.elementToBeClickable(By.id(Utilities.RetrievePropertiesValue(Core.ObjectRepo, "viewpersonaldetails.radiobutton.gmale.id")))).click();
				logger.log(Status.PASS, MarkupHelper.createLabel(testValue, ExtentColor.GREEN));
			}
			else if(gender.equalsIgnoreCase("female")){
				testValue = "Select gender as Female";
				logger.createNode(testValue);
				wdwait.until(ExpectedConditions.elementToBeClickable(By.id(Utilities.RetrievePropertiesValue(Core.ObjectRepo, "viewpersonaldetails.radiobutton.gfemale.id")))).click();
				logger.log(Status.PASS, MarkupHelper.createLabel(testValue, ExtentColor.GREEN));
			}
				
			
			testValue = "Choose Nationality";
			logger.createNode(testValue);
			wdwait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Utilities.RetrievePropertiesValue(Core.ObjectRepo, "viewpersonaldetails.dropdown.nationality.id")))).sendKeys(nationality);
			logger.log(Status.PASS, MarkupHelper.createLabel(testValue, ExtentColor.GREEN));
			
			testValue = "click Save button";
			
			logger.createNode(testValue);
			wdwait.until(ExpectedConditions.elementToBeClickable(By.id(Utilities.RetrievePropertiesValue(Core.ObjectRepo, "addemployee.button.save.id")))).click();
			logger.log(Status.PASS, MarkupHelper.createLabel(testValue, ExtentColor.GREEN));
		
			
		} catch (Exception e) {
			
			logger.createNode(e.getMessage());
			logger.log(Status.FAIL, MarkupHelper.createLabel(testValue, ExtentColor.RED));
			
		} 
	}

	
}
