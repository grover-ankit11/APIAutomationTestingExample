package com.qa.application.testcases;

import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qa.application.util.ExtentReportSetup;

public class BaseTest {
	public Logger Log = Logger.getLogger(getClass());
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTest childTest;
	
	@BeforeSuite
	public void SuiteSetup() {
		extent = ExtentReportSetup.initExtentReport(extent, "Assignment 2", "API Automation");
		test = extent.createTest("API Automation Test");
		Log.info("Report initiated");
		Log.info("Test ready to execute");
	}
	
	@AfterMethod
	public void methodTeatDown(ITestResult result) {		
		ExtentReportSetup.loadExtentReport(result, childTest);
	 }
	
	@AfterSuite
	public void suiteTearDown() {
		extent.flush();
		Log.info("Report created");
	}

	public void log(String message) {
		childTest.log(Status.INFO, message);
		Log.info(message);
	}
}
