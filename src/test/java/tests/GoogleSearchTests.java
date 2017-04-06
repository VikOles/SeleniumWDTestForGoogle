package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import lib.Browser;
import lib.Google;
//import lib.Log;
import lib.Log;

public class GoogleSearchTests {
	private static String AUTOMATION_TEXT_PATTERN = "automation";

	/**
	 * Testing task for Automation Engineer (Selenium / WebDriver) TESTS TO
	 * DEVELOP: 1. Test #1. Open Google. Search for “automation”. Open the first
	 * link on search results page. Verify that title contains searched word 2.
	 * Test #2. Open Google. Search for “automation”. Verify that there is
	 * expected domain (“testautomationday.com”) on search results pages (page:
	 * 1-5).
	 **/
	@BeforeMethod()
	public static void beforeMethod() {
		Browser.getDriver();// optional
	}

	@AfterMethod()
	public static void afterMethod() {
		Browser.quit();
	}

	@Test(enabled = true)
	public void Test1SearchTitel() {
		Google google = new Google();
		google.invoke();
		google.searchPageInvoke(AUTOMATION_TEXT_PATTERN);
		google.invokeFirstLink();
		String actulTitle = Browser.getDriver().getTitle();
		Assert.assertTrue(actulTitle.toLowerCase().contains(AUTOMATION_TEXT_PATTERN),
				"Actual Title: " + actulTitle + " Expected: " + AUTOMATION_TEXT_PATTERN);
		Log.info("[INFO] Test SearchTitelTest complete");
	}

	@Test(enabled = true)
	public void Test2SearchDomain() {
		Google google = new Google();
		String searchDomainValue = "testautomationday.com";
		int amountOfPage = 5;
		boolean bFound;
		google.invoke();
		google.searchPageInvoke(AUTOMATION_TEXT_PATTERN);
		int iPage = 1;
		do {
			if (iPage != 1) {
				google.nextPageInvoke(iPage);
			}
			bFound = google.existsDomain(searchDomainValue);
			iPage++;
		} while (!bFound && iPage <= amountOfPage);
		Assert.assertTrue(bFound, "[FAILED] " + searchDomainValue + " wasn't found on search Pages 1-" + amountOfPage);
		Log.info("[INFO] Test SearchDomainTest complete");
	}
}