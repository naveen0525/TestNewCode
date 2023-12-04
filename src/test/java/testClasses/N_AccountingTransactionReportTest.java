package testClasses;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import objectsMethods.AccountingTransactionReportPage;
import objectsMethods.LoginPage;
import utiles.BaseClass;

@Listeners(BaseClass.class)
public class N_AccountingTransactionReportTest extends BaseClass {

	LoginPage loginPg;
	AccountingTransactionReportPage TransReportPg;

	/* This test is for filtering the Transaction report. */
	@Test
	public void transactionReport() throws IOException, InterruptedException {

		driver = setUp();
		loginPg = new LoginPage(driver);
		TransReportPg = new AccountingTransactionReportPage(driver);
		Properties prop = readPropertiesFile(".//src//Resources//Property//TransactionReport.properties");
		test = report.createTest("transactionReport");

		loginPg.enterCredentials(prop.getProperty("loginEmail"), prop.getProperty("loginPassword"));
		test.log(Status.INFO, "Enter Email ID and Password");

		loginPg.clickLogin();
		test.log(Status.INFO, "Click on Login Button");

		TransReportPg.accountingDashboard();
		test.log(Status.INFO, "Click on Accounting Dashboard");

		TransReportPg.selectDate(prop.getProperty("fromDate"), prop.getProperty("toDate"));
		test.log(Status.INFO, "Select from and to date");

		TransReportPg.clickSubmitButton();
		test.log(Status.INFO, "Click on Submit Button");
		Thread.sleep(3000);

		WebElement element = driver.findElement(By.xpath("//div[contains(text(), 'Success')]"));

		String expectedValue = "Success";

		Assert.assertEquals(element.getText(), expectedValue);
		System.out.print("------");
	}
}
