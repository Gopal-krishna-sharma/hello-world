package oakley.Helper;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Reporting {

	ExtentReports extent;
	ExtentTest test;
	String ScreenShotFolderpath = "";
	String destination;
	String destinationOne;
	String output = " ";
	// TakesScreenshot ts;
	WebDriver driver;

	public Reporting() {
		Timestamp TS = new Timestamp(System.currentTimeMillis());
		output = TS.toString().replace(":", "-").replace(" ", "-");
		extent = new ExtentReports(
				"Reports" + File.separator + output + File.separator + "TestExecutionReport" + ".html", true);
		extent.addSystemInfo("OTL", "Environment").addSystemInfo("Production", "Automation Testing")
				.addSystemInfo("User Name", "Gopal Sharma");

		ScreenShotFolderpath = "./Reports" + File.separator + output + File.separator + "Screenshots" + File.separator;
	}

	public String getScreenshot(WebDriver driver, String screenshotName) throws Exception {

		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		destination = screenshotName + dateName + ".png";
		File finalDestination = new File(ScreenShotFolderpath + destination);
		FileUtils.copyFile(source, finalDestination);
		String destinationOne = "Screenshots" + File.separator + destination;
		return destinationOne;
	}

	public void startTest(String testName) {
		testName = "<font color=#5c1c60><b>" + testName;
		test = extent.startTest(testName);
		test.assignCategory("Regression", "CrossBrowser");
		extent.flush();
	}

	public void pass(WebDriver driver, String action) throws Exception {
		test.log(LogStatus.PASS, action,
				"Test Case Passed" + test.addScreenCapture(getScreenshot(driver, "PassedScreenshot")));
		extent.flush();
	}

	public void fail(WebDriver driver, String action) throws Exception {
		test.log(LogStatus.FAIL, action, "Test Case Failed" + "&nbsp;"
				+ test.addScreenCapture(getScreenshot(driver, "FailedScreenshot")) + test.getDescription());
		extent.flush();
	}

	public void info(WebDriver driver, String Step, String Description) throws Exception {
		test.log(LogStatus.INFO, Step, Description);
		extent.flush();
	}

	public void endTest() {
		extent.endTest(test);
	}

}
