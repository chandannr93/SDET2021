package contact;


import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.vtiger.comcast.genericUtilty.ExcelUtility;
import com.vtiger.comcast.genericUtilty.FileUtility;
import com.vtiger.comcast.genericUtilty.JavaUtility;
import com.vtiger.comcast.genericUtilty.WebDriverUtility;
import com.vtiger.comcast.pomrepository.library.ContactInformationPage;
import com.vtiger.comcast.pomrepository.library.ContactLookUpPage;
import com.vtiger.comcast.pomrepository.library.ContactPage;
import com.vtiger.comcast.pomrepository.library.CreateContactPage;
import com.vtiger.comcast.pomrepository.library.HomePage;
import com.vtiger.comcast.pomrepository.library.LoginPage;

public class TC_07_CreatingContactsSelectingReportsToandClearingIt {

	public static void main(String[] args) throws Throwable {
		JavaUtility javaLib = new JavaUtility();
		ExcelUtility excelData = new ExcelUtility();
		FileUtility fu = new FileUtility();
		WebDriverUtility wL= new WebDriverUtility();
		String Browser = fu.propertyFile("browser");
		String Url = fu.propertyFile("url");
		String Username = fu.propertyFile("username");
		String Password = fu.propertyFile("password");
		
		WebDriver driver = null;
		if (Browser.equals("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if (Browser.equals("firefox"))
		{
			driver = new FirefoxDriver();
		}
		
		 driver.manage().window().maximize(); 
		 driver.manage().timeouts().implicitlyWait(11, TimeUnit.SECONDS);		 
		 String contactName = excelData.getDataFromExcelSheet("Sheet1", 1, 2)+javaLib.random();
		driver.get(Url); 
		LoginPage loginPage = new LoginPage(driver);
		// to verify login page is displayed
		loginPage.isLoginPageDisplayed();

		// login to application and verify home page is displayed
		HomePage homePage = loginPage.login(Username, Password);
		homePage.isHomePageDisplayed();

		// click on Contacts link and verify contacts page is displayed
		ContactPage contactPage = homePage.clikOnContact();
		contactPage.isContactPageDisplayed();

		// click on create contact button and verify create contact page is displayed
		CreateContactPage createContact = contactPage.createContact();
		createContact.isCreateContactPageDisplayed();

		// creating the contact
		createContact.getLastNameTextField().sendKeys(contactName);
		createContact.getReportsToAddButton().click();

		// Transferring the driver control to child window
	//ArrayList<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());
		//driver.switchTo().window(windowHandles.get(1));
		wL.switchToWindow(driver, "Contacts&action");

		// selecting the contact
		//driver.findElement(By.id("1")).click();
		ContactLookUpPage contactLookupPage = new ContactLookUpPage(driver);
		//contactLookupPage.getContactName().click();
		contactLookupPage.selectContact("raj singh");
		
		// Transferring control back to parent window
		wL.switchToWindow(driver, "Contacts");

		// verifying the contact selected
		createContact.isReportsToBeSelected();

		// clearing the reports To and verifying it is cleared
		createContact.getClearReportsToTextField().click();
		createContact.isReportsTocleared();

		ContactInformationPage contactInfo = createContact.save();

		// verifying Header text in contact information page is correct
		contactInfo.isContactInfoHeaderCorrect(contactName);

		// logout
		homePage.clikOnContact();
		homePage.logOut();
		driver.quit();
	}
}