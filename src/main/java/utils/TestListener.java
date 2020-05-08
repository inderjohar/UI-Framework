package utils;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;	

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import pages.BasePage;

public class TestListener extends BasePage implements ITestListener{
	
	@Override
    public void onTestStart(ITestResult result) {
		System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
		ExtentTestManager.startTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
		ExtentTestManager.getTest().log(Status.PASS, "Test passed");
		ExtentTestManager.endTest();
        ExtentManager.getInstance().flush();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		WebDriver driver = null;
		String destinationFile = null;
		
		try {
			driver =(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} 
		
		try {
			destinationFile = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// attach screenshots to report
		try 
		{
			ExtentTestManager.getTest().fail("Screenshot",
			MediaEntityBuilder.createScreenCaptureFromPath(destinationFile).build());
		} 
		catch (IOException e) {
			e.getCause();
		}
		
		ExtentTestManager.getTest().log(Status.FAIL, "Test Failed " + result.getName());
		ExtentTestManager.endTest();
        ExtentManager.getInstance().flush();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
	}
}
