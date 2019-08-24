package oakley.Helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class OO_Main {

	public static void main(String[] args) throws Exception {

		WebDriver driver = null;
		Excel testData = new Excel();
		Reporting RT = new Reporting();
		TestCases test = new TestCases(driver, RT);
		int testCount = testData.GetTestCount();
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();

		capabilities.setCapability("requireWindowFocus", true);

		for (int i = 1; i <= testCount;) {

			switch (testData.GetTestCaseData(testData.GetTestCaseID(i), "browser").toLowerCase()) {

			case "chrome":

				System.setProperty("webdriver.chrome.driver",
						"C:/Users/Gopal/Oxygen/Oakley1509/drivers/chromedriver.exe");
				driver = new ChromeDriver();
				driver.get(testData.GetTestCaseData(testData.GetTestCaseID(i), "url"));
				test.ExecuteTestCase(driver, RT, testData, testData.GetTestName(i), testData.GetTestCaseID(i));
				driver.quit();
				break;

			case "firefox":
				System.setProperty("webdriver.gecko.driver",
						"C:/Users/Gopal/Oxygen/Oakley1509/drivers/geckodriver.exe");
				driver = new FirefoxDriver();
				driver.get(testData.GetTestCaseData(testData.GetTestCaseID(i), "url")); //
				test.ExecuteTestCase(driver, RT, testData, testData.GetTestName(i), testData.GetTestCaseID(i));
				driver.quit();

				break;

			case "edge":
				System.setProperty("webdriver.edge.driver",
						"C:/Users/Gopal/Oxygen/Oakley1509/drivers/MicrosoftWebDriver.exe");
				driver = new EdgeDriver();
				driver.get(testData.GetTestCaseData(testData.GetTestCaseID(i), "url")); //
				test.ExecuteTestCase(driver, RT, testData, testData.GetTestName(i), testData.GetTestCaseID(i));
				driver.quit();
				break;

			case "ie":
				System.out.println("\n************Executed inIE***************");
				System.setProperty("webdriver.ie.driver",
						"C:/Users/Gopal/Oxygen/Oakley1509/drivers/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				driver.get(testData.GetTestCaseData(testData.GetTestCaseID(i), "url")); //
				// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				test.ExecuteTestCase(driver, RT, testData, testData.GetTestName(i), testData.GetTestCaseID(i));
				driver.quit();
				break;

			default:
				System.out.println("Invalid browser name");
			}
			i = i + 1;
		}

		System.exit(0);

	}

}
