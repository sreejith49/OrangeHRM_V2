package com.orangehrm.org;

import java.util.List;

import org.openqa.selenium.WebElement;

public class ObjectActionUtilities extends Core{
	
	//lets start with button
	
	public static void clickButton(WebElement element){
				
		try {
			element.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void clickButton(List<WebElement> elements, String buttonText){
		
		try {
			
			for (WebElement webElement : elements) {
				if(webElement.getText().equalsIgnoreCase(buttonText)){
					webElement.click();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
