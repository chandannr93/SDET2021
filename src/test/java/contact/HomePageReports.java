package contact;

import org.testng.annotations.Test;

public class HomePageReports extends ExtendBaseReport {
@Test
public void gettingHomePageTitle() {
	
	test=reports.createTest("gettingHomePageTitle");
	String homepageTitle = driver.getTitle();
	System.out.println("Home page Title is -- "+homepageTitle);
}
}
