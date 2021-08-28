package contact;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.concurrent.TimeUnit;

import com.vtiger.comcast.genericUtilty.ExcelUtility;
import com.vtiger.comcast.genericUtilty.FileUtility;
import com.vtiger.comcast.pomrepository.library.ContactInformationPage;
import com.vtiger.comcast.pomrepository.library.ContactPage;
import com.vtiger.comcast.pomrepository.library.CreateContactPage;
import com.vtiger.comcast.pomrepository.library.HomePage;
import com.vtiger.comcast.pomrepository.library.LoginPage;

public class TC_08_CreateOrganizationWithLeadSource {
public static void main(String[] args) throws Throwable {
		
	ExcelUtility excelData = new ExcelUtility();
	FileUtility fu = new FileUtility();
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
		String contactName = excelData.getDataFromExcelSheet("Sheet1", 3, 2);
		driver.get(Url); 
		//String contactName="piyush";
		//String assignedLeadSource="Employee";		
		//driver.get("http://localhost:8888/");
		
		LoginPage loginPage = new LoginPage(driver);
		//to verify login page is displayed
		loginPage.isLoginPageDisplayed();
		
		// login to application and verify home page is displayed
		HomePage homePage = loginPage.login(Username , Password);
		homePage.isHomePageDisplayed();
		
		//click on Contacts link and verify contacts page is displayed
		ContactPage contactPage = homePage.clikOnContact();
		contactPage.isContactPageDisplayed();
		
		//click on create contact button and verify create contact page is displayed
		CreateContactPage createContact = contactPage.createContact();
		createContact.isCreateContactPageDisplayed();
		
		//creating the contact
		createContact.getLastNameTextField().sendKeys(contactName);
		Select sel = new Select(createContact.getLeadSourceDropDown());
		String leadSource = excelData.getDataFromExcelSheet("Sheet1", 3, 3);
		sel.selectByVisibleText(leadSource);
		ContactInformationPage contactInfo = createContact.save();
		
		//verifying Header text in contact information page is correct
		contactInfo.isContactInfoHeaderCorrect(contactName);
		
		//logout
		homePage.clikOnContact();
		homePage.logOut();
		driver.quit();
	}
}
