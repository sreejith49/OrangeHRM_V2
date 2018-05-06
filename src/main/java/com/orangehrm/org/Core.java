package com.orangehrm.org;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Core {
	
	public static WebDriver driver;
	
	public static String ObjectRepo;
	
	public static ExtentReports extent;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentTest logger;
	
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
	
	@BeforeTest
    public  static void getReporter(){
        if(extent == null){
            //Set HTML reporting file location
            htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/STMExtentReport.html");
    		extent = new ExtentReports ();
    		extent.attachReporter(htmlReporter);
    		
    		htmlReporter.config().setDocumentTitle("OrangeHRM Test Results");
    		htmlReporter.config().setReportName("OrangeHRM Automation Test Report");
    		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
    		htmlReporter.config().setTheme(Theme.STANDARD);
    		

        }
        
    }
	
	@AfterMethod
	public void getResult(ITestResult result){
		System.out.println("Aftermethod executed");
		if(result.getStatus() == ITestResult.FAILURE){
			//logger.log(Status.FAIL, "Test Case Failed is "+result.getName());
			//MarkupHelper is used to display the output in different colors
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
		}else if(result.getStatus() == ITestResult.SKIP){
			//logger.log(Status.SKIP, "Test Case Skipped is "+result.getName());
			logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));	
		}
	}
	
	@AfterTest
	public void endReport(){
		extent.flush();
    }

	
	@AfterSuite
	public void TearDown() throws InterruptedException, IOException{
		if(driver !=null){
			driver.close();
			driver.quit();
			Runtime.getRuntime().exec( "cscript testData/OpenHtml.vbs" );
		}
		
	}

}
