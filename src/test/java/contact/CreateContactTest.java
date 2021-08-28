package contact;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.vtiger.comcast.genericUtilty.BaseClass;
import com.vtiger.comcast.pomrepository.library.ContactInformationPage;
import com.vtiger.comcast.pomrepository.library.ContactLookUpPage;
import com.vtiger.comcast.pomrepository.library.ContactPage;
import com.vtiger.comcast.pomrepository.library.CreateContactPage;
import com.vtiger.comcast.pomrepository.library.HomePage;
@Listeners(com.vtiger.comcast.genericUtilty.ListenerImplementationClass.class)
public class CreateContactTest extends BaseClass {

	@Test(groups = {"smoke testing","regression testing"})
	public void TC_07_CreatingContactsSelectingReportsToandClearingItTest() throws Throwable {
		
		String contactName = excelLib.getDataFromExcelSheet("Sheet1", 1, 2)+javaLib.random();
		// click on Contacts link and verify contacts page is displayed
				HomePage homePage= new HomePage(driver);
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
				webdriverLib.switchToWindow(driver, "Contacts&action");

				// selecting the contact
				ContactLookUpPage contactLookupPage = new ContactLookUpPage(driver);
				contactLookupPage.getContactName().click();
				
				// Transferring control back to parent window			
				webdriverLib.switchToWindow(driver, "Contacts");

				// verifying the contact selected			
				SoftAssert asrt = new SoftAssert();
				boolean ReportsToBeSelected = createContact.getReportsToContacId().getAttribute("value").isEmpty();
				asrt.assertFalse(ReportsToBeSelected, "FAIL: Reports To contact not selected");				

				// clearing the reports To and verifying it is cleared
				createContact.getClearReportsToTextField().click();//dont create a method for validation
				boolean ReportsToCleared = createContact.getReportsToContacId().getAttribute("value").isEmpty();
				asrt.assertTrue(ReportsToCleared, "FAIL: Reports To contact is Not cleared");
				/*if(createContact.getReportsToContacId().getAttribute("value").isEmpty()) {
					System.out.println("PASS: Reports To contact is cleared");
				}
				else {
					System.out.println("FAIL: Reports To contact is Not cleared");
				}*/

				ContactInformationPage contactInfo = createContact.save();

				// verifying Header text in contact information page is correct
				contactInfo.isContactInfoHeaderCorrect(contactName);
				asrt.assertAll();
	}
	
	@Test(groups = {"regression testing"})
	public void TC_08_CreateOrganizationWithLeadSourceTest() throws Throwable {
		String contactName = excelLib.getDataFromExcelSheet("Sheet1", 1, 2);
		// click on Contacts link and verify contacts page is displayed
				HomePage homePage= new HomePage(driver);
				ContactPage contactPage = homePage.clikOnContact();
				contactPage.isContactPageDisplayed();
				
				//click on create contact button and verify create contact page is displayed
				CreateContactPage createContact = contactPage.createContact();
				createContact.isCreateContactPageDisplayed();
				
				//creating the contact
				createContact.getLastNameTextField().sendKeys(contactName);
				Select sel = new Select(createContact.getLeadSourceDropDown());
				String leadSource = excelLib.getDataFromExcelSheet("Sheet1", 3, 3);
				sel.selectByVisibleText(leadSource);
				ContactInformationPage contactInfo = createContact.save();
				
				//verifying Header text in contact information page is correct
				contactInfo.isContactInfoHeaderCorrect(contactName);
				boolean assignedLeadSource = contactInfo.getLeadSourceText().getText().contains(leadSource);
				assertTrue(assignedLeadSource, "Lead Source is Not Selected");
				System.out.println("Lead Source is Selected");
	}
	
	
	@Test(groups = {"regression testing"})
	public void TC_09_CreateOrganizationWithAssignedToAdministratorTest() throws Throwable {
		String contactName = excelLib.getDataFromExcelSheet("Sheet1", 5, 2);
		// click on Contacts link and verify contacts page is displayed
				HomePage homePage= new HomePage(driver);
				//click on Contacts link and verify contacts page is displayed
				ContactPage contactPage = homePage.clikOnContact();
				contactPage.isContactPageDisplayed();
				
				//click on create contact button and verify create contact page is displayed
				CreateContactPage createContact = contactPage.createContact();
				createContact.isCreateContactPageDisplayed();
				
				//creating the contact
				createContact.getLastNameTextField().sendKeys(contactName);
				createContact.getUserRadioButton().click();
				Select sel = new Select(createContact.getUserAssignedToDropDown());
				String assignedTo = excelLib.getDataFromExcelSheet("Sheet1", 5, 4);
				sel.selectByVisibleText(assignedTo);
				ContactInformationPage contactInfo = createContact.save();
				
				//verifying Header text in contact information page is correct
				contactInfo.isContactInfoHeaderCorrect(contactName);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
