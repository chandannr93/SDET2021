package com.vtiger.comcast.genericUtilty;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Parameters;

import com.vtiger.comcast.pomrepository.library.HomePage;
import com.vtiger.comcast.pomrepository.library.LoginPage;


public class BaseClass {
	public WebDriver driver;//Declaring in global area
		//Object creattion for library
	public static WebDriver webDriver;
		public JavaUtility javaLib = new JavaUtility();
		public  ExcelUtility excelLib= new ExcelUtility();
		public WebDriverUtility webdriverLib = new WebDriverUtility();
		public  FileUtility fileLib = new FileUtility();
		//@Parameters("Browser")
		@BeforeClass(groups = {"smoke testing","regression testing"})
		public void ConfigBeforeClass() throws Throwable {
			String Browser = fileLib.propertyFile("browser");
			if (Browser.equals("chrome"))
			{
				driver = new ChromeDriver();
				webDriver = driver;
			}
			else if (Browser.equals("firefox"))
			{
				driver = new FirefoxDriver();
			}//driver = new ChromeDriver();
			
			driver.manage().window().maximize();
			webdriverLib.implicitlywait(driver);
			String Url = fileLib.propertyFile("url");
			driver.get(Url);
		}
		@BeforeMethod(groups = {"smoke testing","regression testing"})
		public void ConfigBeforeMethod() throws Throwable {
			String Url = fileLib.propertyFile("url");
			driver.get(Url);
			LoginPage loginPage = new LoginPage(driver);
			//to verify login page is displayed
			loginPage.isLoginPageDisplayed();
			String Username = fileLib.propertyFile("username");
			String Password = fileLib.propertyFile("password");	
			// login to application and verify home page is displayed
			HomePage homePage = loginPage.login(Username , Password);
			homePage.isHomePageDisplayed();				
		}
		@AfterMethod(groups = {"smoke testing","regression testing"})
		public void ConfigAfterMethod() {
			//logout
			new HomePage(driver).logOut();

		}
		
		@AfterClass(groups = {"smoke testing","regression testing"})
		public void ConfigAfterClass() {
			driver.quit();
		}
		
		}
		
	
	

