package addproduct;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddProduct {
	String chromeDriverPath = "C:/Users/agchandr/Downloads/chromedriver.exe";
	WebDriver driver = null;

	@Given("^I am on Add Product Form in Great Outdoors Product page$")
	public void goToGoAdminOnChrome() {
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		driver = new ChromeDriver();
		driver.navigate().to("http://localhost:9089/go/allprod.jsp");
		driver.findElement(By.id("add-product")).click();
		System.out.println("Title: " + driver.getTitle());
	}
	
	
	@When("^I enter product id as \"(.*)\"$")
	public void enterProductId (String productId) {
		System.out.println ("Product Id entered: " + productId);
		driver.findElement(By.id("add-prod-id")).sendKeys(productId);
	}
	
	@When("^I enter product name as \"(.*)\"$")
	public void enterProductName (String productName) {
		System.out.println ("Product Name entered: " + productName);
		driver.findElement(By.id("add-prod-name")).sendKeys(productName);
	}
	
	

	@When("^I enter product brand as \"(.*)\"$")
	public void enterProductBrand (String productBrand) {
		System.out.println ("Product Brand entered: " + productBrand);
		driver.findElement(By.id("add-prod-brand")).sendKeys(productBrand);
		
	}
	
	@When("^I enter product dimension as \"(.*)\"$")
	public void enterProductDimension (String productDim) {
		System.out.println ("Product Dimension entered: " + productDim);
		driver.findElement(By.id("add-prod-dim")).sendKeys(productDim);
		
	}
	
	@When("^I enter product Specification as \"(.*)\"$")
	public void enterProductSpecification (String productspec) {
		System.out.println ("Product Dimension entered: " + productspec);
		driver.findElement(By.id("add-prodspec")).sendKeys(productspec);
		
	}
	
	
	@When("^I enter quantity as \"(.*)\"$")
	public void enterQuantity (String productQty) {
		System.out.println ("Product Quantity entered: " + productQty);
		driver.findElement(By.id("add-prod-qty")).sendKeys(productQty);
		
	}
	
	@When("^I enter price as \"(.*)\"$")
	public void enterProductPrice (String price) {
		System.out.println ("Product Dimension entered: " + price);
		driver.findElement(By.id("add-prod-price")).sendKeys(price);
		
	}
	
	@When("^I  select Category as \"(.*)\"$")
	public void enterCategory (String category) {
		System.out.println ("Product Dimension entered: " + category);
		driver.findElement(By.id("add-prod-cat")).sendKeys(category);
		
	}
	
	@When("^I enter color as \"(.*)\"$")
	public void enterProductColor (String color) {
		System.out.println ("Product Dimension entered: " + color);
		driver.findElement(By.id("add-prod-col")).sendKeys(color);
		
	}
	
	@When("^I click on submit button$")
	public void clickSubmitButton () {
		driver.findElement(By.id("addprod")).click();
	}
	
	/*
	@Then("^Add product successfull message will pop up$")
	public void checkSuccess () {
		assertTrue(driver.getCurrentUrl().equals("http://localhost:9089/go/AddProductServlet"));
	}*/
	
	
	
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
