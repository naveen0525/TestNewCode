package testClasses;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import objectsMethods.AddNewProductPage;
import objectsMethods.LoginPage;
import utiles.BaseClass;

@Listeners(BaseClass.class)
public class E_AddNewProductTest extends BaseClass {

	LoginPage loginPg;
	AddNewProductPage AddNewProductPg;

	/* This test is for Adding New Product */
	@Test
	public void addNewProduct() throws Exception {
		driver = setUp();
		loginPg = new LoginPage(driver);
		AddNewProductPg = new AddNewProductPage(driver);
		Properties prop = readPropertiesFile(".//src//Resources//Property//AddNewProduct.properties");
		test = report.createTest("addNewProduct");
		
		/* Provider's Inventory DashBoard */

		/* TestCase# 12 */
		loginPg.enterCredentials(prop.getProperty("loginEmail"), prop.getProperty("loginPassword"));
		test.log(Status.INFO, "Enter Email Id and Password");
		loginPg.clickLogin();
		test.log(Status.INFO, "Click on Login Button");

		AddNewProductPg.clickInventoryLinkDashboard();
		test.log(Status.INFO, "Click on Inventory Dashboard Link");

		AddNewProductPg.clickCategoryLink();
		test.log(Status.INFO, "Click on Category Link");

		AddNewProductPg.clickSelectCategory();
		test.log(Status.INFO, "Select Category");

		AddNewProductPg.clickAddNewProductButton();
		test.log(Status.INFO, "Click on Add New Product Button");

		AddNewProductPg.generalInfo_action(prop.getProperty("NewProductName"), prop.getProperty("NewProdctDescription"),
				prop.getProperty("NewProductPriority"), prop.getProperty("NewProductUnitWeight"));
		test.log(Status.INFO, "Enter General Information");

		AddNewProductPg.clickNextButtonOne();
		test.log(Status.INFO, "Click on Next Button");

		AddNewProductPg.inventoryInfo_action(prop.getProperty("NewProductCurrentInventory"),
				prop.getProperty("NewProductCostPerUnit"), prop.getProperty("NewProductTotalMarkUp"),
				prop.getProperty("NewProductMinInventory"), prop.getProperty("NewProductMaxInventory"),
				prop.getProperty("NewProductWhenInventoryAt"), prop.getProperty("NewProductProductPrice"),
				prop.getProperty("NewProductVendorPrice"));
		test.log(Status.INFO, "Enter Invenotry Information");

		AddNewProductPg.saveButton();
		test.log(Status.INFO, "Click on Save Button");

		WebElement element = driver.findElement(By.xpath("(//strong)[1]"));

		String expectedValue = "Record has been saved successfully.";

		Assert.assertEquals(element.getText(), expectedValue);
		System.out.print("------");

	}

}
