package com.vtiger.comcast.genericUtilty;



import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class provides WebDriver Generic Utility methods
 * @author Chandan
 *
 */
public class WebDriverUtility {

	/**
	 * This method is used to apply impicitlywait on webelements 
	 * @param driver
	 */
	public void implicitlywait(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	
	
	/**
	 * This method is used to apply explicit Wait on webElements
	 * @param driver
	 * @param element
	 */
	public void explicityWait(WebDriver driver, WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * This method waits for the element to be clicked, its custum wait created for ElementInterAcceptable Exception
	 * @param element
	 * @throws InterruptedException
	 */
	public void waitAndClick(WebElement element) throws InterruptedException
	{
		int count = 0;
		while(count<=20)
		{
			try {
				element.click();
				break;
			}
			catch(Exception e)
			{
				Thread.sleep(1000);
				count++;
			}
			
		}
	}
	
	/**
	 * This method is used to Select the Option from the drop down list Box using selectByVisibleText
	 * @param element
	 * @param optionData
	 */
	public void select(WebElement element, String optionData)
	{
		Select s= new Select(element);
		s.selectByVisibleText(optionData);
	}
	
	/**
	 * This method is used to Select the Option from the drop down list Box using selectByIndex
	 * @param element
	 * @param index
	 */
	public void select(WebElement element, int index)
	{
		Select s= new Select(element);
		s.selectByIndex(index);
	}
	
	/**
	 * This method is used to Select the Option from the drop down list Box using selectByValue
	 * @param element
	 * @param valueData
	 * @param flag
	 */
	public void select(WebElement element, String valueData, boolean flag)
	{
		Select s= new Select(element);
		s.selectByValue(valueData);
	}
	/**
	 * This method helps to switch from one window to another
	 * @param driver
	 * @param partialWinTitle
	 */
	public void switchToWindow(WebDriver driver, String partialWinTitle)
	   {
		   Set<String> window = driver.getWindowHandles();
		   Iterator<String> it = window.iterator();
		   while(it.hasNext())
		   {
			   String winId=it.next();
			   String title=driver.switchTo().window(winId).getTitle();
	           if(title.contains(partialWinTitle))
	           {
	        	   break;
	           }
			   
		   }
		   
	   }

	}

