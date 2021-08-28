package com.vtiger.comcast.pomrepository.library;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ContactPage {

	WebDriver driver;
	public ContactPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//img[@title='Create Contact...']")
	private WebElement createContactButton;
	
	public WebElement getCreateContactButton() {
		return createContactButton;
	}

	// method to verify Contacts page is displayed
		public void isContactPageDisplayed() {
			boolean ContactPage = driver.getTitle().contains("Contacts");
			Assert.assertTrue(ContactPage, "Contacts page is Not dispalyed");
			System.out.println("Contacts page is dispalyed");
			/*if(driver.getTitle().contains("Contacts"))
				System.out.println("Contacts page is dispalyed");
			else
				System.out.println("Contacts page is Not dispalyed");*/
		}
		
	// method to click on create contact
		
	public CreateContactPage createContact() {
		createContactButton.click();
		return new CreateContactPage(driver);
	}
}

