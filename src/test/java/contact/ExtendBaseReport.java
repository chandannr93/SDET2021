package contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

	public class ExtendBaseReport {	
		 public WebDriver driver;
		 public ExtentHtmlReporter reporter;
		 public ExtentReports reports;
		 public ExtentTest test;
		 
		@BeforeSuite
		public void beforeSuiteConfiguration() {///VTigerCRM/SDET20
			reporter = new ExtentHtmlReporter("../VTigerCRM/SDET20/sdet20.html");
			reporter.config().setDocumentTitle("SDET");
			reporter.config().setTheme(Theme.DARK);
			reports= new ExtentReports();
			reports.attachReporter(reporter);
			System.out.println("Before Suite Configuration");		
		}
		
	@AfterSuite
	public void afterSuiteConfiguration() {
		System.out.println("After Suite Configuration");
		reports.flush();
	}

	@BeforeClass
	public void beforeClassConfiguration() {
		
		//launching the browser
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8888/");
	}

	@AfterClass
	public void afterClassConfiguration() {
		driver.quit();
	}

	@BeforeMethod
	public void beforeMethodConfiguration() {
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();	
	}

	

	@AfterMethod
	public void afterMethodConfiguration() {
		Actions actions = new Actions(driver);
		WebElement userIcon = driver.findElement(By.xpath("//img[contains(@src,'user')]"));
		actions.moveToElement(userIcon).perform();
		WebElement signOutLink = driver.findElement(By.linkText("Sign Out"));
		signOutLink.click();	
	}
	}

