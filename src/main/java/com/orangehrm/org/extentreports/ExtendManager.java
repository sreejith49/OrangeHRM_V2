package com.orangehrm.org.extentreports;

import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.orangehrm.org.Core;

public class ExtendManager {
	
    private static ExtentReports extent;
	static ExtentHtmlReporter htmlReporter;
	ExtentTest logger;

    @BeforeTest
    public  static void getReporter(){
        if(extent == null){
            //Set HTML reporting file location
            htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/STMExtentReport.html");
    		extent = new ExtentReports ();
    		extent.attachReporter(htmlReporter);
    		
    		htmlReporter.config().setDocumentTitle("Title of the Report Comes here");
    		htmlReporter.config().setReportName("Name of the Report Comes here");
    		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
    		htmlReporter.config().setTheme(Theme.STANDARD);

        }
        
    }
    
    


}
