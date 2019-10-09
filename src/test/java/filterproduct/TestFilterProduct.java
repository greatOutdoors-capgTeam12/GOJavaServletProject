package filterproduct;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestFilterProduct {
	String chromeDriverPath = "C:\\Users\\kuroycho\\Project Dependencies\\chromedriver_win32\\chromedriver.exe";
	WebDriver driver = null;

	@Given("^I am on Filter Product Form in Great Outdoors Product page$")
	public void goToGoAdminOnChrome() {
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		driver = new ChromeDriver();
		driver.navigate().to("http://localhost:9089/go/allprod.jsp");
		driver.findElement(By.id("filter-product")).click();
		System.out.println("Title: " + driver.getTitle());
	}
	
	
	
	
	@When("^I enter product name as \"(.*)\"$")
	public void enterProductName (String productName) {
		System.out.println ("Product Name entered: " + productName);
		driver.findElement(By.id("filter-prod-name")).sendKeys(productName);
	}
	
	

	@When("^I enter product brand as \"(.*)\"$")
	public void enterProductBrand (String productBrand) {
		System.out.println ("Product Brand entered: " + productBrand);
		driver.findElement(By.id("filter-prod-brand")).sendKeys(productBrand);
		
	}
	
	@When("^I enter low price range as \"(.*)\"$")
	public void enterLowPrice (String lowPrice) {
		System.out.println ("Low Price entered: " + lowPrice);
		driver.findElement(By.id("filter-low-price")).sendKeys(lowPrice);
		
	}
	
	@When("^I enter high price as \"(.*)\"$")
	public void enterProductSpecification (String highPrice) {
		System.out.println ("High Price entered: " + highPrice);
		driver.findElement(By.id("filter-high-price")).sendKeys(highPrice);
		
	}
	
	
	
	
	@When("^I  select Category as \"(.*)\"$")
	public void enterCategory (String category) {
		System.out.println ("Product category entered: " + category);
		driver.findElement(By.id("filter-cat")).sendKeys(category);
		
	}
	
	
	
	@When("^I click on submit button$")
	public void clickSubmitButton () {
		System.out.println ("Update Product button clicked");
		WebElement we = driver.findElement(By.xpath("//*[@id=\"mySidenav\"]/div/form/button"));
		we.click();
	
	}
	
	
	@Then("^Filtered Product page shown up$")
    public void FilterProductSuccess()  {
        
		assertEquals("http://localhost:9089/go/FilterProductServlet", driver.getCurrentUrl());
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
