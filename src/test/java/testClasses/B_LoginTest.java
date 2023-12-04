package testClasses;

import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import objectsMethods.LoginPage;
import utiles.BaseClass;

@Listeners(BaseClass.class)
public class B_LoginTest extends BaseClass {

	LoginPage loginPg;

	/* This test is for the User Login */
	@Test
	public void login() throws IOException, InterruptedException {
		driver = setUp();
		loginPg = new LoginPage(driver);
		Properties prop = readPropertiesFile("./src/Resources/Property/Login.properties");
		test = report.createTest("Login");

		/* Provider's DashBoard */

		/* TestCase# 03 */
		loginPg.enterCredentials(prop.getProperty("loginEmail"), prop.getProperty("loginPassword"));
		test.log(Status.INFO, "Enter Email ID and Password");

		loginPg.clickLogin();
		test.log(Status.INFO, "Click on Login Button");

		String title = driver.getTitle();
		Assert.assertEquals(title, "Provider1 | Dashboard");
		System.out.print("------Login ");

	}

}
