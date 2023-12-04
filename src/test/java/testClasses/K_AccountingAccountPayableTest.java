package testClasses;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import objectsMethods.AccountingAccountPayablePage;
import objectsMethods.LoginPage;
import utiles.BaseClass;

@Listeners(BaseClass.class)

public class K_AccountingAccountPayableTest extends BaseClass {

	LoginPage loginPg;

	AccountingAccountPayablePage AccPayable;

	/* This test is for filtering the Account Payable Report. */
	@Test
	public void accountPayableReport() throws Exception {
		driver = setUp();
		loginPg = new LoginPage(driver);
		AccPayable = new AccountingAccountPayablePage(driver);
		Properties prop = readPropertiesFile(".//src//Resources//Property//accountingPayable.properties");
		test = report.createTest("accountPayableReport");

		loginPg.enterCredentials(prop.getProperty("loginEmail"), prop.getProperty("loginPassword"));
		test.log(Status.INFO, "Enter Email ID and Password");

		loginPg.clickLogin();
		test.log(Status.INFO, "Click on Login Button");

		AccPayable.accountingDashboard();
		test.log(Status.INFO, "Click on Accounting Dashboard");

		AccPayable.selectDate(prop.getProperty("fromDate"), prop.getProperty("toDate"));
		test.log(Status.INFO, "Select from and to date");

		AccPayable.selectPOstatus(prop.getProperty("PO"));
		test.log(Status.INFO, "Select PO");

		AccPayable.selectVendors(prop.getProperty("Vendor"));
		test.log(Status.INFO, "Select Vendor");

		AccPayable.clickSubmitButton();
		test.log(Status.INFO, "Click on Submit Button");
		Thread.sleep(1000);

		WebElement element = driver.findElement(By.xpath("(//a[@class='page-link'])[2]"));

		String expectedValue = "1";

		Assert.assertEquals(element.getText(), expectedValue);
		System.out.print("------");

	}

}
