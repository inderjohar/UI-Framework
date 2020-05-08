package tests;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import pages.BasePage;
import pages.LoginPage;
import utils.ExcelUtil;
import utils.ExtentTestManager;

public class LoginTest extends BasePage{
	public WebDriver driver;

	Logger log = Logger.getLogger(LoginTest.class);
	
	@BeforeMethod
	public void initialize() throws IOException {
		log.info("Initializing Web Driver");
		driver = initializeDriver();
	}
	
	@Test(dataProvider = "sendData")
	
	public void loginPageNavigation(String Username,String Password) throws IOException
	{
		ExtentTestManager.getTest().log(Status.INFO, "Running Excel Data based test");
		driver.navigate().to(properties.getProperty("url"));
		LoginPage loginPage = new LoginPage(driver);
		loginPage.getLogin();
		loginPage.getEmail().sendKeys(Username);
		loginPage.getPassword().sendKeys(Password);
		loginPage.getLogin().click();
		//Assert.assertTrue(loginPage.getNavigationLabel().isDisplayed());
	}

	@DataProvider
	public Object[][] sendData()
	{
		Object[][] data = ExcelUtil.getTestData("login");
		return data;	
	}
	
	@AfterMethod
	public void teardown()
	{	
		driver.quit();
	}

}
