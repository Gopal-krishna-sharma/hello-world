package oakley.SitePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import oakley.Helper.BrowserActions;
import oakley.Helper.Reporting;

public class OO_Login_Page {

	WebDriver driver;
	BrowserActions browser;
	Reporting report = null;

	@FindBy(xpath = ".//*[@id='register.firstName']")
	private WebElement firstName;

	@FindBy(xpath = ".//*[@id='register.lastName']")
	private WebElement lastName;

	@FindBy(xpath = ".//*[@id='dateOfBirth']")
	private WebElement DOB;

	@FindBy(xpath = ".//*[@id='register.email']")
	private WebElement emailAddress;

	@FindBy(xpath = ".//*[@id='register.checkEmail']")
	private WebElement confirmEmailAddress;

	@FindBy(xpath = ".//*[@id='register.pwd']")
	private WebElement password;

	@FindBy(xpath = ".//*[@id='register.checkPwd']")
	private WebElement confirmPassword;

	@FindBy(xpath = ("//label[@for=\"policyForm.consentPrivacy.consentGiven\"]"))
	private WebElement checkBox;

	@FindBy(xpath = (".//*[@class='fancybox-item fancybox-close modal-close']"))
	private WebElement popclose;

	@FindBy(xpath = ".//*[@id='X_CheckoutThankYou_NewAccount_Open']")
	private WebElement createAccountBtn;

	// Default class Constructor

	public OO_Login_Page(WebDriver driver, Reporting report) {
		this.driver = driver;
		this.report = report;
		PageFactory.initElements(driver, this);
		browser = new BrowserActions(driver, report);
	}

	public void popclose() {
		try {
			if (browser.isElementPresent(popclose, 7) == true) {
				browser.Click(popclose, "popup closed");
			} else {
				System.out.println("pop up not displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enterFirstName(String firstname) throws Exception {

		browser.SetText(firstName, firstname, "Firstname Entered");

	}

	public void enterLastName(String lastname) throws Exception {
		browser.SetText(lastName, lastname, "Lastname Entered");
	}

	public void enterDOB() throws Exception {
		String dateOfBirth = "09/18/1986";
		browser.SetText(DOB, dateOfBirth, "Date Of Birth Entered");
		driver.findElement(By.className("main-content")).click();
	}

	public void enterEmailAddress(String emailaddress) throws Exception {
		browser.SetText(emailAddress, emailaddress, "Email address Entered");
	}

	public void enterConfirmEmailAddress(String confirmemailaddress) throws Exception {
		browser.SetText(confirmEmailAddress, confirmemailaddress, "Confirm email address Entered");
	}

	public void enterPassword(String passWord) throws Exception {
		browser.SetText(password, passWord, "Password Entered");
	}

	public void enterConfirmPassword(String confirmpassword) throws Exception {
		browser.SetText(confirmPassword, confirmpassword, "Password Entered");
	}

	public void Checkbox() throws Exception {
		browser.Click(checkBox, "Create Your Account Button Clicked");
	}

	public void clickCreateAccount() throws Exception {
		browser.Click(createAccountBtn, "Create Your Account Button Clicked");
		Thread.sleep(2000);
	}

	// ******* Registration for US, CA, JP and AU***********//

	public void NonEUregistration(String firstname, String lastname, String emailaddress, String confirmemailaddress,
			String password, String confirmpassword) throws Exception {
		try {
			popclose();

			enterFirstName(firstname);
			enterLastName(lastname);
			enterEmailAddress(emailaddress);
			enterConfirmEmailAddress(confirmemailaddress);
			enterPassword(password);
			enterConfirmPassword(confirmpassword);
			clickCreateAccount();
			System.out.println("Login success for Non EU");
		} catch (Exception e) {
			System.out.println("Exception found in Non EU registration for login");
		}
	}

	// ********Registration for European Countries*************//

	public void EUregistration(String firstname, String lastname, String emailaddress, String confirmemailaddress,
			String password, String confirmpassword) throws Exception {
		try {
			popclose();
			enterFirstName(firstname);
			enterLastName(lastname);
			enterDOB();
			enterEmailAddress(emailaddress);
			enterConfirmEmailAddress(confirmemailaddress);
			enterPassword(password);
			enterConfirmPassword(confirmpassword);
			Checkbox();
			clickCreateAccount();
			System.out.println("Login success for EU");
		} catch (Exception e) {
			System.out.println("Exception found in EU registration for login");
		}
	}

}
