package com.orangehrm.org;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Utilities extends Core{
	
	public static WebElement findElement(String objParameter){
		
		WebElement returnElement = null;
		
		try {
			
			String[] str = objParameter.split(".");
			
			if(str[3].equalsIgnoreCase("id")){
				returnElement = driver.findElement(By.id(""));
			}else if(str[3].equalsIgnoreCase("class")){
				returnElement = driver.findElement(By.className(""));
			}else if(str[3].equalsIgnoreCase("css")){
				returnElement = driver.findElement(By.cssSelector(""));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnElement;
		
	}
	
	public static String RetrievePropertiesValue(String filePath, String propertyKey){
		
		Properties prop = new Properties();
		
		try {			
			
			InputStream input = new FileInputStream(new File(filePath));
			prop.load(input);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return prop.getProperty(propertyKey);
		
	}
	
	public void insertText(WebElement element, String text){
		
		try {
			element.sendKeys(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
