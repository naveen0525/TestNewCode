package utiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseClass implements ITestListener {

	public static WebDriver driver;
	public static ExtentSparkReporter htmlReporter;
	public static ExtentReports report;
	protected static ExtentTest test;
	public static ITestResult result;

	public WebDriver setUp() throws IOException {

		Properties prop = readPropertiesFile(".//src//Resources//Property//url.properties");
		System.setProperty("webdriver.chrome.driver", ".//src//Resources//drivers//chromedriver.exe");

		
		  ChromeOptions options = new ChromeOptions();
		  options.addArguments("----window-size=1440x600");
		  options.addArguments("----headless"); driver = new ChromeDriver(options);
		  driver.manage().window().maximize();
		 

//		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
//		driver.manage().window().maximize();

		System.out.println("Running the browser");

		return driver;

	}

	public static Properties readPropertiesFile(String fileName) throws IOException {
		FileInputStream fis = null;
		Properties prop = null;
		try {
			fis = new FileInputStream(fileName);
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			fis.close();
		}
		return prop;
	}

	public static String scrShot(String screenShotName) {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		Date d = new Date();

		String TimeStamp = d.toString().replace(":", "_").replace(" ", "_");

		File Dest = new File(".//src//screenshot//" + screenShotName + "_" + TimeStamp + ".png");
		String errflpath = Dest.getAbsolutePath();
		try {
			FileUtils.copyFile(scrFile, Dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			test.log(Status.FAIL, e);
		}
		return errflpath;

	}

	public void scrollPageDown() {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)");

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/src/reports/AventExtentReportResults.html");
		report = new ExtentReports();
		report.attachReporter(htmlReporter);

	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, (result.getName() + " Test PASSED"));
			report.flush();
		}

		System.out.println("Test Pass");
		driver.quit();
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub

		scrShot(result.getMethod().getMethodName());

		if (result.getStatus() == ITestResult.FAILURE) {

//			test.log(LogStatus.FAIL, (result.getName() + " Test FAILED "));
			String screenShot = scrShot(result.getName());
			test.log(Status.FAIL, "Test Failed: " + test.addScreenCaptureFromPath(screenShot));
			test.log(Status.FAIL, result.getThrowable());
			report.flush();

		}

		System.out.println("Test Fail");
		driver.quit();

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		report.flush();
//		driver.close();

	}

}
