package contact;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class OrganizationPageReport extends ExtendBaseReport {
	@Test
	public void gettingOrganizationPageTitle() {
		
		test=reports.createTest("gettingOrganizationPageTitle");
		driver.findElement(By.xpath("//td[@class='tabUnSelected']/a[text()='Organizations']")).click();
		String organizationPageTitle = driver.getTitle();
		System.out.println("Organization page Title is -- "+organizationPageTitle);
		
	}

}
