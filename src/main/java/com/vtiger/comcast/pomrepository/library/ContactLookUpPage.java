package com.vtiger.comcast.pomrepository.library;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactLookUpPage {
	
	WebDriver driver;
	public ContactLookUpPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
@FindBy(xpath="//a[text()=' piyush']") //Stale element exception avoided 
private WebElement contactName;// cannot write dynamic xpath by @findby so we can't concatinate

@FindBy(id="1") 
private WebElement contactId;

public WebElement getContactName() {
	return contactName;
}// encapsulation done here

public WebElement getContactId() {
	return contactId;
}

public void selectContact(String contactName) {
	driver.findElement(By.xpath("//a[text()=' "+contactName+"']")).click();
}//we can concatinate since it's a dynamic xpath

}

