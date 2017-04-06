package lib;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import lib.Browser;

public class Google {
	public By searchListLocator = By.id("lst-ib");
	public By searchButtonLocator = By.id("_fZl");
	public By searchLink = By.xpath("//*[@data-hveid]//h3/a[@onmousedown]");
	public By domainsCite = By.xpath("//cite");

	public By pageLink(int i) {
		String xPathPart = "Page " + i;
		return By.xpath("//*[@aria-label='" + xPathPart + "']");
	}

	WebDriverWait wait;

	public void invoke() {
		String sURL = "https://www.google.com";
		Browser.getDriver().get(sURL);
		Log.info("[INFO] Trying to invoke page: " + sURL);
		if (!"Google".equals(Browser.getDriver().getTitle())) {
			throw new IllegalStateException("This is not the Google page");
		}
		Log.info("[INFO] Google page load successfully");
	}

	public void searchPageInvoke(String text) {
		Browser.getDriver().findElement(searchListLocator).sendKeys(text);
		Browser.getDriver().findElement(searchButtonLocator).click();
		wait = new WebDriverWait(Browser.getDriver(), 10);
		wait.until(ExpectedConditions.elementToBeClickable(searchLink));
		 Log.info("[INFO] Search for "+text+" was done");

	}

	public void nextPageInvoke(int i) {
		WebElement element = Browser.getDriver().findElement(pageLink(i));
		Dimension size = element.getSize();
		Actions actions = new Actions(Browser.getDriver());
		actions.moveToElement(element, size.getWidth() - 1, size.getHeight() - 1).click().build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(pageLink(i - 1)));
		 Log.info("[INFO] Page "+i+" was invoke");
	}

	public void invokeFirstLink() {
		String url = Browser.getDriver().findElement(searchLink).getAttribute("href");
		Browser.getDriver().findElement(searchLink).click();
		Assert.assertTrue(wait.until(ExpectedConditions.urlToBe(url)), url);
		 Log.info("[INFO] First link was clicked. New page load with URL-"+url);
	}

	public boolean existsDomain(String searchDomainValue) {
		String domainValue;
		boolean bFound = false;
		for (int j = 0; j < Browser.getDriver().findElements(domainsCite).size(); j++) {
			domainValue = Browser.getDriver().findElements(domainsCite).get(j).getText();
			if (domainValue.contains(searchDomainValue)) {
				 Log.info("[INFO]"+searchDomainValue+" was found on Page-"+j);
				bFound = true;
				break;
			}
		}
		return bFound;
	}
}
