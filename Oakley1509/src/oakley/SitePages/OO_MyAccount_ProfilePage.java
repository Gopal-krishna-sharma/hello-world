package oakley.SitePages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import oakley.Helper.BrowserActions;
import oakley.Helper.Reporting;

public class OO_MyAccount_ProfilePage {

	WebDriver driver;
	BrowserActions browser;
	Reporting report = null;

	@FindBy(xpath = ".//*[@id='register.firstName']")
	private WebElement firstName;

	@FindBy(xpath = ".//*[@id='register.lastName']")
	private WebElement lastName;

	// Default class Constructor

	public OO_MyAccount_ProfilePage(WebDriver driver, Reporting report) {
		this.driver = driver;
		this.report = report;
		PageFactory.initElements(driver, this);
		browser = new BrowserActions(driver, report);
	}

	// Verify Title
	public void verifyAccountPageTitle(String title) {
		// browser.verifyPageTitle(title);
	}

}
