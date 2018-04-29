package com.orangehrm.org.pim;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.orangehrm.org.Core;

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
	
	
	public static void addEmployee(String firstName, String lastName){
		
		try {
			
			driver.findElement(By.id("firstName")).sendKeys(firstName);
			driver.findElement(By.id("lastName")).sendKeys(lastName);
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	
}
