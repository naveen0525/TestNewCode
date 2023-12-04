package testClasses;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import objectsMethods.AccountingARPage;
import objectsMethods.LoginPage;
import utiles.BaseClass;

@Listeners(BaseClass.class)
public class M_AccountingARTest extends BaseClass {

	LoginPage loginPg;
	AccountingARPage ARpage;

	/* This test is for filtering the Account Receivable Report. */
	@Test
	public void accountReceivableReport() throws IOException, InterruptedException {

		driver = setUp();
		loginPg = new LoginPage(driver);
		ARpage = new AccountingARPage(driver);
		Properties prop = readPropertiesFile(".//src//Resources//Property//AR.properties");
		test = report.createTest("accountReceivableReport");

		loginPg.enterCredentials(prop.getProperty("loginEmail"), prop.getProperty("loginPassword"));
		test.log(Status.INFO, "Enter Email ID and Password");

		loginPg.clickLogin();
		test.log(Status.INFO, "Click on Login Button");

		ARpage.accountingDashboard();
		test.log(Status.INFO, "Click on Accounting Dashboard");

		ARpage.selectCustomerFromDropDown(prop.getProperty("Customer"));
		test.log(Status.INFO, "Select Customer");

		ARpage.selectDate(prop.getProperty("fromDate"), prop.getProperty("toDate"));
		test.log(Status.INFO, "Select from and to date");

		ARpage.clickSubmitButton();
		test.log(Status.INFO, "Click on Submit Button");

		ARpage.aRAging();
		test.log(Status.INFO, "Click on AR Paging Button");

		WebElement element = driver.findElement(By.xpath("(//div[@class='row'])[2]"));

		String expectedValue = "Account Receivable";

		Assert.assertEquals(element.getText(), expectedValue);
		System.out.print("------");
	}

}
