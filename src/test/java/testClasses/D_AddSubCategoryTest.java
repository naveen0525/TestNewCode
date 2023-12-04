package testClasses;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import objectsMethods.AddNewSubCategoryPage;
import objectsMethods.LoginPage;
import utiles.BaseClass;

@Listeners(BaseClass.class)
public class D_AddSubCategoryTest extends BaseClass {

	LoginPage loginPg;
	AddNewSubCategoryPage AddSubCategoryPg;

	/* This test is for Adding Sub Category */
	@Test
	public void addNewSubCategory() throws IOException, InterruptedException {
		driver = setUp();
		loginPg = new LoginPage(driver);
		AddSubCategoryPg = new AddNewSubCategoryPage(driver);
		Properties prop = readPropertiesFile(".//src//Resources//Property//addNewSubCategory.properties");
		test = report.createTest("addNewSubCategory");
		
		/* Provider's Inventory DashBoard */

		/* TestCase# 13 */
		loginPg.enterCredentials(prop.getProperty("loginEmail"), prop.getProperty("loginPassword"));
		test.log(Status.INFO, "Enter Email ID and Password");

		loginPg.clickLogin();
		test.log(Status.INFO, "Click on Login Button");

		AddSubCategoryPg.clickInventoryModule();
		test.log(Status.INFO, "Click on Inventory Module Link");

		AddSubCategoryPg.categoryTab();
		test.log(Status.INFO, "Click on Category Tab");

		AddSubCategoryPg.selectCategoryFromList();
		test.log(Status.INFO, "Select Any Category");

		AddSubCategoryPg.addNewSubCategory_action(prop.getProperty("NewSubCategoryName"),
				prop.getProperty("NewSubCategoryDescription"), prop.getProperty("NewSubCategoryPriority"));
		test.log(Status.INFO, "Enter New SubCategory Detail");

		AddSubCategoryPg.saveButton();
		test.log(Status.INFO, "Click on Save Button");

		WebElement element = driver.findElement(By.xpath("(//strong)[1]"));

		String expectedValue = "Record has been saved successfully.";

		Assert.assertEquals(element.getText(), expectedValue);
		System.out.print("------");

	}

}
