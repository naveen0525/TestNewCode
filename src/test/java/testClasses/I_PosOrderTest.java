package testClasses;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import objectsMethods.LoginPage;
import objectsMethods.PosPage;
import utiles.BaseClass;

@Listeners(BaseClass.class)
public class I_PosOrderTest extends BaseClass {

	LoginPage loginPg;
	PosPage posPg;

	/* This test is for POS order. */
	@Test
	public void posOrder() throws IOException, InterruptedException {
		driver = setUp();
		loginPg = new LoginPage(driver);
		posPg = new PosPage(driver);
		Properties prop = readPropertiesFile(".//src//Resources//Property//POS.properties");
		test = report.createTest("posOrder");

		loginPg.enterCredentials(prop.getProperty("loginEmail"), prop.getProperty("loginPassword"));
		test.log(Status.INFO, "Enter Email ID and Password");

		loginPg.clickLogin();
		test.log(Status.INFO, "Click on Login Button");

		/* Provider's Home DashBoard */

		/* TestCase# 130 */
		posPg.clickPOS();
		test.log(Status.INFO, "Click on POS Tab");

		/* TestCase# 131 */
		posPg.searchCustomer(prop.getProperty("firstname"));
		test.log(Status.INFO, "Search and select customer");

		/* TestCase# 133 */
		posPg.searchProduct(prop.getProperty("product"));
		test.log(Status.INFO, "Search and select product");

		posPg.buyNow();
		test.log(Status.INFO, "Click on Buy Now button");

		posPg.continueCheckOut();
		test.log(Status.INFO, "Click on Login-->Checkout button");

		posPg.orderSummary();
		test.log(Status.INFO, "Click on Order Summary-->Continue button");

		posPg.deliveryAddPresent();
		test.log(Status.INFO, "Click on Delivery Address-->Select address and click Continue button");

		posPg.continuePickup();
		test.log(Status.INFO, "Click on Pickup Address-->Continue button");

		posPg.continueCommunication();
		test.log(Status.INFO, "Click on Communication-->Continue button");

		posPg.creditcardRadioBtn();
		test.log(Status.INFO, "Select Credit Card Option");

		posPg.creditCard(prop.getProperty("card_number"), prop.getProperty("exp_month"), prop.getProperty("exp_year"),
				prop.getProperty("cvv"), prop.getProperty("card_holder_name"));
		test.log(Status.INFO, "Enter Card details and click on Pay button");
		Thread.sleep(10000);

		WebElement element = driver.findElement(By.xpath("//h4[@class='w-100 text-center mt-2']"));

		String expectedValue = "Success!";

		Assert.assertEquals(element.getText(), expectedValue);
		System.out.print("------");

	}

}