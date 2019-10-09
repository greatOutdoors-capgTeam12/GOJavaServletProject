package updateproduct;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestUpdateProduct {
	String chromeDriverPath = "C:\\Users\\kuroycho\\Project Dependencies\\chromedriver_win32\\chromedriver.exe";
	WebDriver driver = null;

	@Given("^I am on Update Product Form in Great Outdoors Product page$")
	public void goToGoAdminOnChrome() {
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		driver = new ChromeDriver();
		driver.navigate().to("http://localhost:9090/go/allprod.jsp");
		driver.findElement(By.id("update-product")).click();
		System.out.println("Title: " + driver.getTitle());
	}
	
	
	@When("^I enter product id as \"(.*)\"$")
	public void enterProductId (String productId) {
		System.out.println ("Product Id entered: " + productId);
		driver.findElement(By.id("up-prod-id")).sendKeys(productId);
	}
	
	@When("^I enter product name as \"(.*)\"$")
	public void enterProductName (String productName) {
		System.out.println ("Product Name entered: " + productName);
		driver.findElement(By.id("up-prod-name")).sendKeys(productName);
	}
	
	

	@When("^I enter product brand as \"(.*)\"$")
	public void enterProductBrand (String productBrand) {
		System.out.println ("Product Brand entered: " + productBrand);
		driver.findElement(By.id("up-prod-brand")).sendKeys(productBrand);
		
	}
	
	@When("^I enter product dimension as \"(.*)\"$")
	public void enterProductDimension (String productDim) {
		System.out.println ("Product Dimension entered: " + productDim);
		driver.findElement(By.id("up-prod-dimension")).sendKeys(productDim);
		
	}
	
	@When("^I enter product Specification as \"(.*)\"$")
	public void enterProductSpecification (String productspec) {
		System.out.println ("Product Specification entered: " + productspec);
		driver.findElement(By.id("up-prod-spec")).sendKeys(productspec);
		
	}
	
	
	@When("^I enter price as \"(.*)\"$")
	public void enterProductPrice (String price) {
		System.out.println ("Product price entered: " + price);
		driver.findElement(By.id("up-prod-price")).sendKeys(price);
		
	}
	
	@When("^I  select Category as \"(.*)\"$")
	public void enterCategory (String category) {
		System.out.println ("Product category entered: " + category);
		driver.findElement(By.id("up-prod-cat")).sendKeys(category);
		
	}
	
	@When("^I enter color as \"(.*)\"$")
	public void enterProductColor (String color) {
		System.out.println ("Product color entered: " + color);
		driver.findElement(By.id("up-prod-col")).sendKeys(color);
		
	}
	
	@When("^I click on submit button$")
	public void clickSubmitButton () {
		System.out.println ("Update Product button clicked");
		WebElement we = driver.findElement(By.xpath("//*[@id=\"upprod\"]/form/div/button[2]"));
		we.click();
	
	}
	
	
	@Then("^\"(.*)\" Update Product Success Message Popped Up$")
    public void UpdateProductSuccess(String productId) throws InterruptedException {
        String Message = driver.findElement(By.xpath("//*[@id=\"msg\"]")).getText();
        String msg = "PRODUCT() PRODUCT ID : "+productId.toUpperCase()+" HAS BEEN SUCCESSFULLY UPDATED" ;
        assertEquals(Message, msg);
	}
	
	@Then("^Update Product Failure Message Popped Up$")
    public void UpdateProductFailure() throws InterruptedException {
        String Message = driver.findElement(By.xpath("//*[@id=\"err\"]")).getText();
        String msg = "ERROR IN UPDATING PRODUCT >> >>>PRODUCT ID ENTERED IS INVALID" ;
        assertEquals(Message, msg);
	}
	
	
	
	
	
	@After
	public void close() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Closing the driver.");
		if (driver != null) {
			driver.quit();
		}
	}
}
