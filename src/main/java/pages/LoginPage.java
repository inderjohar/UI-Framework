package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

public WebDriver driver;
	
	By email = By.xpath("//*[@type='email']");
	By password = By.xpath("//*[@type='password']");
	By login = By.xpath("//*[@value='Log In']");
	By userNavigation = By.id("userNavigationLabel");
	By logOut = By.xpath("//*[text()='Log Out']");
	
	public LoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}
	
	public WebElement getEmail()
	{
		return driver.findElement(email);
	}
	

	public WebElement getPassword()
	{
		return driver.findElement(password);
	}
	
	public WebElement getLogin()
	{
		return driver.findElement(login);
	}
	
	public WebElement getNavigationLabel()
	{
		return driver.findElement(userNavigation);
	}
	
	public WebElement logout()
	{
		return driver.findElement(logOut);
	}
}
