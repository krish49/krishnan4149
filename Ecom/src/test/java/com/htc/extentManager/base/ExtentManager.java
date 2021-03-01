package com.htc.extentManager.base;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	private static ExtentReports extent;

	public static ExtentReports createInstances() {
	String FileName = "Test_Automation-Report"+".html";
	String fileSeparator = System.getProperty("file.separator");
	String reportFilePath = System.getProperty("user.dir")+fileSeparator+ "TestReport";
    String reportFileLocation =  reportFilePath +fileSeparator+ FileName;
    ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(FileName);
	
	htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
	htmlReporter.config().setChartVisibilityOnOpen(true);
	htmlReporter.config().setTheme(Theme.STANDARD);
	htmlReporter.config().setDocumentTitle(FileName);
	htmlReporter.config().setEncoding("utf-8");
	htmlReporter.config().setReportName(FileName);
	htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	
	extent = new ExtentReports();
	extent.setSystemInfo("OS", "Windows");
	extent.setSystemInfo("AUT", "QA");
	extent.attachReporter(htmlReporter);
	return extent;
	
    
 
    	
	}
/*	private static ExtentReports createInstance() {
		// TODO Auto-generated method stub
		String fileName = getReportPath(reportFilePath);
		
		
		 //Set environment details
		
		return extent;
		
	}
*/
	private static void getReportPath() {
		

}

	public static ExtentReports getInstance() {
		// TODO Auto-generated method stub
		if(extent == null)
    		createInstances();
    	return extent;
    
		//return null;
	}

	public static void startTest(String methodName) {
		// TODO Auto-generated method stub
		
	}
}
