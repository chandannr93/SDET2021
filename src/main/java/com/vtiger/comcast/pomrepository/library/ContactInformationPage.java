package com.vtiger.comcast.pomrepository.library;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ContactInformationPage {

	WebDriver driver;
	public ContactInformationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement pageHeaderText;
	
	@FindBy(id="mouseArea_Lead Source")
	private WebElement leadSourceText;
	
	public WebElement getPageHeaderText() {
		return pageHeaderText;
	}

	public WebElement getLeadSourceText() {
		return leadSourceText;
	}

	public WebElement getAssignedToText() {
		return assignedToText;
	}

	@FindBy(id="mouseArea_Assigned To")
	private WebElement assignedToText;
	
	public WebElement getPageHederText() {
		return pageHeaderText;
	}
	
	//method to verify the header text is correct
	
	public void isContactInfoHeaderCorrect(String contactName) {
		System.out.println("Header text is : "+pageHeaderText.getText());
		boolean HeaderText = pageHeaderText.getText().contains(contactName+" - Contact Information");
		Assert.assertTrue(HeaderText, "FAIL : Header message is not correct");
		System.out.println("PASS : Header message is correct");
		/*System.out.println("Header text is : "+pageHeaderText.getText());
		if(pageHeaderText.getText().contains(contactName+" - Contact Information"))
		{
		System.out.println("PASS : Header message is correct");
		}
	else 
		{
		System.out.println("FAIL : Header message is not correct");
		}*/		
	}
	//method to verify the assigned text is crct
			public void isAssignedTo(String assignedGroup) {
				boolean assignedText = assignedToText.getText().contains(assignedGroup);
				Assert.assertTrue(assignedText, "Fail: Header Message is not correct");
				System.out.println("PASS: Header Message is correct");
			}

}

		
