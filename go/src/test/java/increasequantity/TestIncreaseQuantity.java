package increasequantity;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.corba.se.impl.orbutil.threadpool.TimeoutException;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestIncreaseQuantity {
	String chromeDriverPath = "C:/Users/agchandr/Downloads/chromedriver.exe";
	WebDriver driver = null;

	@Given("^I am on Increase Product Quantity in Great Outdoors Product page$")
	public void goToGoAdminOnChrome() {
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		driver = new ChromeDriver();
		driver.navigate().to("http://localhost:9089/go/allprod.jsp");
		driver.findElement(By.id("increase-qty")).click();
		System.out.println("Title: " + driver.getTitle());
	}
	
	
	@When("^I enter product id as \"(.*)\"$")
	public void enterProductId (String productId) {
		System.out.println ("Product Id entered: " + productId);
		driver.findElement(By.id("inc-prod-id")).sendKeys(productId);
	}
	
	@When("^I enter quantity as \"(.*)\"$")
	public void enterProductQty (String productQty) {
		System.out.println ("Product Id entered: " + productQty);
		driver.findElement(By.id("inc-prod-qty")).sendKeys(productQty);
	}
	
	
	
	@When("^I click on Increase Quantity button$")
	public void clickSubmitButton () {
		System.out.println ("Increase Product Quantity button clicked");
		driver.findElement(By.xpath("//*[@id=\"incqty\"]/form/div/button[2]")).click();
	}
	

	
	@Then("^\"(.*)\" and \"(.*)\" Increase Product Quantity Success Message popped up$")
    public void IncreaseProductQuantitySuccess(String productId, String productQuantity) throws InterruptedException {
        String Message = driver.findElement(By.xpath("//*[@id=\"msg\"]/h2")).getText();
        String msg = "PRODUCT WITH PRODUCT ID : " + productId.toUpperCase() + " HAS BEEN SUCCESSFULLY INCREASED BY "+ productQuantity +" UNITS";
        assertEquals(Message, msg);
	}

	

	
	@Then("Increase Product Quantity Failure Message popped up$")
    public void IncreaseProductQuantityFailure() throws InterruptedException {
        String Message = driver.findElement(By.xpath("//*[@id=\"err\"]")).getText();
        String msg = "ERROR IN INCREASING PRODUCT QUANTITY >> ERROR IN ADDING QUANTITY OF EXISTING PRODUCT>>>PRODUCT ID ENTERED IS INVALID" ;
        assertEquals(Message, msg);
	}
	 
	

	
	@After
	public void close() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
		System.out.println("Closing the driver.");
		if (driver != null) {
			driver.quit();
		}
	}
}
