package addProductToFav;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class addProductToFav {
	String chromeDriverPath = "C:\\Users\\kuroycho\\Project Dependencies\\chromedriver_win32\\chromedriver.exe";
	WebDriver driver = null;
	
	@Given("^User is on \"home\" page$")
	public void goToGoAdminOnChrome() {
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		driver = new ChromeDriver();
		driver.navigate().to("http://localhost:9090/go/index.html");
		driver.findElement(By.id("retailer-dropdown-menu")).click();
		driver.findElement(By.id("retailer-dropdown-your-fav")).click();
		driver.findElement(By.id("retailer-add-prod-to-fav")).click();	
		System.out.println("Title: " + driver.getTitle());
	}
	
	@When("^I enter retialerId as \"(.*)\"$")
	public void userFillsRetailerId (String retailerId) {
		driver.findElement(By.id("add-prod-to-fav-retid")).sendKeys(retailerId);
	}
	
	@When("^I enter productId as \"(.*)\"$")
	public void userFillsProductId (String productId) {
		driver.findElement(By.id("add-prod-to-fav-prodid")).sendKeys(productId);
	}
	
	@When("^clicks on \"submit\" button$")
	public void clickOnSubmitButton () {
		driver.findElement(By.id("add-prod-to-fav-submit-button")).click();
	}
	
	@Then("^Product must be added to their wishlist$")
	void checkProdAddedToFav () {
		
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
