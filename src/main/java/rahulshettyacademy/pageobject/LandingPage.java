package rahulshettyacademy.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		
		//initialization
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
				
	}
	//WebElement userMail = driver.findElement(By.id("userEmail")); ---> this element we represented using pagefactory
	//pagefactory
	@FindBy(id="userEmail")
	WebElement userMail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	//create an action method to perform the web elements
	
	public ProductCatologue loginApplication(String email, String password)
	{
		userMail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		ProductCatologue ProductCatologue = new ProductCatologue(driver);
		return ProductCatologue;
	}
	
	public String getErrorMerssage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
		
	}
	
	public void  goTo() {
		
		driver.get("https://rahulshettyacademy.com/client");
	}
	

}
