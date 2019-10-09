package testCancelProduct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CancelProductTester {
	String chromeDriverPath = "C:\\Users\\kuroycho\\Project Dependencies\\chromedriver_win32\\chromedriver.exe";
	WebDriver driver = null;

	@Given("^The sales representative is logged into the go's website$")
	public void The_sales_representative_is_logged_into_the_go_s_website() {
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		driver = new ChromeDriver();
		driver.navigate().to("http://localhost:9090/go/index.html");
		System.out.println("Title: " + driver.getTitle());
	}

	@Given("^clicks the cancel product button$")
	public void clicks_the_cancel_product_button() throws InterruptedException {
		driver.findElement(By.name("salesRepDropDownClick")).click();
		Thread.sleep(500);
		driver.findElement(By.name("cancelClick")).click();
		Thread.sleep(500);
		driver.findElement(By.name("cancelProductClick")).click();
		Thread.sleep(500);
	}

	@When("^It enters the \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
	public void It_enters_the_and(String arg1, String arg2, String arg3, String arg4) throws InterruptedException {
		
		System.out.println("UserId = " + arg1);
		driver.findElement(By.name("UserIdCancelProduct")).sendKeys(arg1);
		Thread.sleep(1000);

		System.out.println("OrderId = " + arg2);
		driver.findElement(By.name("OrderIdCancelProduct")).sendKeys(arg2);
		Thread.sleep(1000);

		System.out.println("ProductId = " + arg3);
		driver.findElement(By.name("ProductIdCancelProduct")).sendKeys(arg3);
		Thread.sleep(1000);

		System.out.println("Quantity = " + arg4);
		driver.findElement(By.name("QuantityCancelProduct")).sendKeys(arg4);
		Thread.sleep(1000);
	}

	@When("^It clicks the submit button$")
	public void It_clicks_the_submit_button() throws InterruptedException {
		driver.findElement(By.id("submitCancelProduct")).submit();
		Thread.sleep(7000);
	}

	@Then("^The \\\"([^\\\"]*)\\\" is displayed$")
	public void The_is_displayed(String arg1) {
		System.out.println("Message : " + arg1);
		System.out.println(driver.getCurrentUrl());
	}

	@After
	public void close() {
		System.out.println("Closing the driver.");
		if (driver != null) {
			driver.quit();
		}
	}

}
