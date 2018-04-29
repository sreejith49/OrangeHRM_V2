package com.orangehrm.org;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import org.testng.log4testng.Logger;

import com.orangehrm.org.pim.Employee;
import com.orangehrm.org.test.RetrieveExcelData;
import com.orangehrm.org.test.testData;

public class AppUtilities extends Core{
	
	//Logger logg = Logger.getLogger(this.getClass());
	
	static String ObjectRepoFilePath = "./ObjRepo.properties"; 
	
	static SoftAssert sAssert = new SoftAssert(); 
	
	private static WebElement identifyElement(String identifierType, String identifier){
		
		WebElement element = null;
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		try {
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(500);
			if(identifierType.equalsIgnoreCase("id")){				
				
				//element = driver.findElement(By.id(identifier));
				
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(identifier)));
				
			}else if(identifierType.equalsIgnoreCase("css")){
				
				//element = driver.findElement(By.cssSelector(identifier));
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(identifier)));
				
			}else if(identifierType.equalsIgnoreCase("class")){
				
				//element = driver.findElement(By.className(identifier));
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(identifier)));
				
			}else if(identifierType.equalsIgnoreCase("xpath")){
				
				//element = driver.findElement(By.xpath(identifier));
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(identifier)));
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return element;
	}

	public static void executeObjectAction_old(String object, String actionOrText){
		//String object example is login.textbox.username.id
		//String actionOrText example is Click or username
		try {
			
			WebElement element;
			String[] arrObject = object.split("\\.");
			String[] arrActionOrText = actionOrText.split("\\.");
			
			element = identifyElement(arrObject[3], Utilities.RetrievePropertiesValue(ObjectRepoFilePath, object));
			
			if(arrObject[1].equalsIgnoreCase("textbox")){
								
				if(arrActionOrText[0].equalsIgnoreCase("verify")){
					
				}else if(arrActionOrText[0].equalsIgnoreCase("enter")){
					element.sendKeys(arrActionOrText[1]);
				}				
				
			}else if(arrObject[1].equalsIgnoreCase("button")){
								
				if(arrActionOrText[0].equalsIgnoreCase("verify")){
					
				}else if(arrActionOrText[0].equalsIgnoreCase("click")){
					element.click();
				}
				
			}else if(arrObject[1].equalsIgnoreCase("link")){
								
				if(arrActionOrText[0].equalsIgnoreCase("verifyText")){
					System.out.println(element.getText());
					sAssert.assertEquals(element.getText(), arrActionOrText[1]);
					if(element.getText().equals(arrActionOrText[1])){
						System.out.println("Text identified successfully");
					}else{
						System.out.println("Not found");
					}
						
					
				}else if(arrActionOrText[0].equalsIgnoreCase("click")){
					element.click();
				}
				
			}else if(arrObject[1].equalsIgnoreCase("webtable")){
				
				if(arrActionOrText[0].equalsIgnoreCase("remove")){
					Employee.selectAnEmployee(element, arrActionOrText[1]);
				}
				
			}
				
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void executeObjectAction(String object, String actionOrText){
		//String object example is login.textbox.username.id
		//String actionOrText example is Click or username
		try {
			
			WebElement element;
			String[] arrObject = object.split("\\.");
			String[] arrActionOrText = actionOrText.split("\\.");
			
			element = identifyElement(arrObject[3], Utilities.RetrievePropertiesValue(ObjectRepoFilePath, object));
			
			
			if(arrActionOrText[0].equalsIgnoreCase("verifyText")){
				//System.out.println(element.getText());
				sAssert.assertEquals(element.getText(), arrActionOrText[1]);
				if(element.getText().equals(arrActionOrText[1])){
					System.out.println("Text identified successfully");
				}else{
					System.out.println("Not found");
				}
					
				
			}else if(arrActionOrText[0].equalsIgnoreCase("click")){
				
				element.click();
				
			}else if(arrActionOrText[0].equalsIgnoreCase("enter")){
				
				element.sendKeys(arrActionOrText[1]);
				
			}else if(arrActionOrText[0].equalsIgnoreCase("remove")){
				Employee.selectAnEmployee(element, testData.getTestDataListFromMap(RetrieveExcelData.testData, arrActionOrText[1]).get(1));
			}else if(arrActionOrText[0].equalsIgnoreCase("addEmployee")){
				Employee.addEmployee(testData.getTestDataListFromMap(RetrieveExcelData.testData, arrActionOrText[1]).get(1), testData.getTestDataListFromMap(RetrieveExcelData.testData, arrActionOrText[1]).get(2));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
