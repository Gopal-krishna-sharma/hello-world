package oakley.Helper;

import org.openqa.selenium.WebDriver;

import oakley.SitePages.OO_Login_Page;

public class TestCases {

	OO_Login_Page objLoginPage;

	WebDriver driver;
	BrowserActions browser;
	Reporting report;

	public TestCases(WebDriver driver, Reporting report) {
		this.driver = driver;
		this.report = report;
		browser = new BrowserActions(driver, report);
	}

	public void ExecuteTestCase(WebDriver driver, Reporting report, Excel testData, String testName, String testCaseId)
			throws Exception {

		// Initialise pages

		objLoginPage = new OO_Login_Page(driver, report);

		switch (testName) {

		case "EUregistration":

			report.startTest(testData.GetTestCaseData(testCaseId, "country"));
			TC_EURegistration(testData.GetTestCaseData(testCaseId, "firstname"),
					testData.GetTestCaseData(testCaseId, "lastname"),
					testData.GetTestCaseData(testCaseId, "emailaddress"),
					testData.GetTestCaseData(testCaseId, "confirmemailaddress"),
					testData.GetTestCaseData(testCaseId, "password"),
					testData.GetTestCaseData(testCaseId, "confirmpassword"));
			break;

		case "NonEUregistration":
			Thread.sleep(2000);
			report.startTest(testData.GetTestCaseData(testCaseId, "country"));
			TC_NonEURegistration(testData.GetTestCaseData(testCaseId, "firstname"),
					testData.GetTestCaseData(testCaseId, "lastname"),
					testData.GetTestCaseData(testCaseId, "emailaddress"),
					testData.GetTestCaseData(testCaseId, "confirmemailaddress"),
					testData.GetTestCaseData(testCaseId, "password"),
					testData.GetTestCaseData(testCaseId, "confirmpassword"));
			break;

		default:
			System.out.println("Wrong Test Method");

		}

	}

	public void TC_EURegistration(String firstname, String lastname, String emailaddress, String confirmemailaddress,
			String password, String confirmpassword) {
		try {

			objLoginPage.EUregistration(firstname, lastname, emailaddress, confirmemailaddress, password,
					confirmpassword);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void TC_NonEURegistration(String firstname, String lastname, String emailaddress, String confirmemailaddress,
			String password, String confirmpassword) {
		try {
			objLoginPage.NonEUregistration(firstname, lastname, emailaddress, confirmemailaddress, password,
					confirmpassword);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
