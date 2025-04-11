package rahulshettyacademy.tests;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobject.CartPage;
import rahulshettyacademy.pageobject.CheckoutPage;
import rahulshettyacademy.pageobject.ConfirmationPage;
import rahulshettyacademy.pageobject.ProductCatologue;


public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"})
	public void SubmitOrder() throws IOException, InterruptedException
	{
		String productName = "ADIDAS ORIGINAL";
		ProductCatologue ProductCatologue = landingpage.loginApplication("anjalisharma127@gmail.com", "Anjali127");
		Assert.assertEquals("Incorrect email or password.",landingpage.getErrorMerssage());
		
	}
	
	@Test
	public void ProductErrorValidation() throws InterruptedException
	{
		String productName = "ADIDAS ORIGINAL";
ProductCatologue ProductCatologue = landingpage.loginApplication("anjalisharma127@gmail.com", "Anjali@127");
		
		List<WebElement>products=ProductCatologue.getProductList();
		ProductCatologue.addProductToCart(productName);
		CartPage CartPage = ProductCatologue.goToCartPage();
		
		Boolean match = CartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage CheckoutPage = CartPage.goToCheckout();
		CheckoutPage.selectCountry("India");
		ConfirmationPage ConfirmationPage = CheckoutPage.submitOrder();
		String confirmMessage = ConfirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

}
