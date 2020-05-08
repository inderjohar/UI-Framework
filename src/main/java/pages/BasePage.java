package pages;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

public class BasePage {
	public WebDriver driver;
	public Properties properties;
	
	public WebDriver initializeDriver() throws IOException {
		
		properties = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\johar\\eclipse-workspace\\UI\\src\\main\\java\\resources\\Data.properties");
		properties.load(fis);
		String browserName = properties.getProperty("browser");

		if(browserName.equals("chrome"))
		{
			 System.setProperty("webdriver.chrome.driver", "C:\\Users\\johar\\Downloads\\chromedriver_win32\\chromedriver.exe");
			 driver = new ChromeDriver();
		}
		else
		{
			 driver= new FirefoxDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		return driver;
	}
	
	public static Statement mysqlDB() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		Connection con = null;
		Statement statement;
		String DB_URL = "jdbc:mysql://localhost:3306/user"; 
		String DB_USER = "root";
		String DB_PASSWORD = "root"; 
		String dbClass = "com.mysql.cj.jdbc.Driver";
		Class.forName(dbClass).newInstance();
		con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		statement = con.createStatement();
		return statement;
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		String dateName = new SimpleDateFormat("yyyymmdd").format(new Date());
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + System.getProperty("file.separator") + testCaseName + dateName + ".png";
		FileHandler.copy(srcFile, new File(destinationFile));
		return destinationFile;
	}
}
