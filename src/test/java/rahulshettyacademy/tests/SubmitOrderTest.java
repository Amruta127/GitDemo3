package rahulshettyacademy.tests;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobject.CartPage;
import rahulshettyacademy.pageobject.CheckoutPage;
import rahulshettyacademy.pageobject.ConfirmationPage;
import rahulshettyacademy.pageobject.LandingPage;
import rahulshettyacademy.pageobject.OrderPage;
import rahulshettyacademy.pageobject.ProductCatologue;


public class SubmitOrderTest extends BaseTest {
	String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider="getData",groups= {"Purchase"})
	public void SubmitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{
		
		ProductCatologue ProductCatologue = landingpage.loginApplication(input.get("email"), input.get("password"));
		
		List<WebElement>products=ProductCatologue.getProductList();
		ProductCatologue.addProductToCart(input.get("product"));
		CartPage CartPage = ProductCatologue.goToCartPage();
		
		Boolean match = CartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage CheckoutPage = CartPage.goToCheckout();
		CheckoutPage.selectCountry("India");
		ConfirmationPage ConfirmationPage = CheckoutPage.submitOrder();
		String confirmMessage = ConfirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));	
	}
	
	@Test(dependsOnMethods= {"SubmitOrder"})
	public void orderHistoryTest() {
		//test for verifying that is ADIDAS ORIGINAL is present or not
		ProductCatologue ProductCatologue = landingpage.loginApplication("anjalisharma127@gmail.com", "Anjali@127");
		OrderPage OrderPage = ProductCatologue.goToOrdersPage();
		Assert.assertTrue(OrderPage.verifyOrderDisplay(productName));		
	}
	
	public String getScreenshot(String testCaseName) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data = getJsonToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
		
	}
	
	//@DataProvider
		//public Object[][] getData()
		//{
			//return new Object[][] {{"anjalisharma127@gmail.com", "Anjali@127","ADIDAS ORIGINAL"}, {"shetty@gmail.com", "Iamking@000", "ZARA COAT 3"}};
			
		//}
	
	/*
	 * HashMap<String,String> map = new HashMap<String,String>(); map.put("email",
	 * "anjalisharma127@gmail.com"); map.put("password", "Anjali@127");
	 * map.put("product", "ADIDAS ORIGINAL");
	 * 
	 * HashMap<String,String> map1 = new HashMap<String,String>(); map1.put("email",
	 * "shetty@gmail.com"); map1.put("password", "Iamking@000"); map1.put("product",
	 * "ZARA COAT 3");
	 */
}
