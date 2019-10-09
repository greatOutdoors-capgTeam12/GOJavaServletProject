package suggestFavProduct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class suggestFavProduct {
	String chromeDriverPath = "C:\\Users\\shapanwa\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe";
	WebDriver driver = null;
	
	@Given("Admin is on \"home\" page")
	public void goToGoAdminOnChrome() {
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		driver = new ChromeDriver();
		driver.navigate().to("http://localhost:9090/go/index.html");
		driver.findElement(By.id("admin-dropdown-menu")).click();
		driver.findElement(By.id("admin-dropdown-suggest-fav")).click();
		System.out.println("Title: " + driver.getTitle());
	}
	
	@When("^I enter retialerId as \"(.*)\"$")
	public void userFillsRetailerId (String retailerId) {
		driver.findElement(By.id("admin-suggest-product-retid")).sendKeys(retailerId);
	}
	
	@When("^clicks on \"submit\" button$")
	public void clickOnSubmitButton () {
		driver.findElement(By.id("suggest-fav-product-submit-button")).click();
	}
	
	@Then("^Product must be displayed to their screen$")
	void checkSuggestFavProd () {
		
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
