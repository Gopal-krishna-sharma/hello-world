package oakley.Helper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserActions {

	public WebDriver driver;
	WebDriverWait wait;
	Reporting report = null;;

	public BrowserActions(WebDriver driver, Reporting report) {
		this.driver = driver;
		this.report = report;
		// report = new Reporting();
	}

	public void WaittoPageLoad(WebDriver driver) {
		Boolean readyStateComplete = false;
		try {
			while (!readyStateComplete) {
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				readyStateComplete = ((String) executor.executeScript("return document.readyState")).equals("complete");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// Wait for Web Element visibility and log step in report if element is not
	// visible

	public void waitForElementVisibility(WebElement element) {
		try {
			wait = new WebDriverWait(driver, 300);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isElementPresent(WebElement element, int timeout) {
		boolean presence = false;
		for (int i = 0; i < timeout; i++) {
			try {
				Thread.sleep(10);
				presence = element.isEnabled();
				return presence;
			} catch (Exception Ex) {
			}
		}
		return false;
	}
	// Wait for Web element Clickable

	public void waitForElement(WebElement element) {

		try {
			wait = new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void SetText(WebElement element, String value, String Action) throws Exception {
		element.sendKeys(value);
		report.pass(driver, Action);
	}

	public void Click(WebElement element, String Action) throws Exception {
		element.click();
		report.pass(driver, Action);
	}
}
