package com.orangehrm.org;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class Core {
	
	static WebDriver driver;
	
	@BeforeSuite
	public void SetUp(){
		try {
			
			System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
			driver = new ChromeDriver();			
			driver.navigate().to(Utilities.RetrievePropertiesValue("./App.properties", "appUrl"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	@AfterSuite
	public void TearDown(){
		if(driver !=null){
			driver.close();
			driver.quit();
		}
		
	}

}
