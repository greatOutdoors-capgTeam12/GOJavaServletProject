package testCheckBonus;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckBonusTester {
	
	String chromeDriverPath = "C:\\Users\\kuroycho\\Project Dependencies\\chromedriver_win32\\chromedriver.exe";
	WebDriver driver = null;
	
	@Given("^The sales representative is logged into the go's website$")
	public void The_sales_representative_is_logged_into_the_go_s_website() {
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		driver = new ChromeDriver();
		driver.navigate().to("http://localhost:9090/go/index.html");
		System.out.println("Title: " + driver.getTitle());
	}

	@Given("^clicks the check bonus button$")
	public void clicks_the_check_bonus_button() throws InterruptedException {
		driver.findElement(By.name("salesRepDropDownClick")).click();
		Thread.sleep(500);
		driver.findElement(By.name("checkBonus")).click();
		Thread.sleep(500);
	}

	@When("^It enters the UserId as \"([^\"]*)\"$")
	public void It_enters_the_UserId_as(String arg1) throws InterruptedException {
		System.out.println("UserId = "+arg1);
		driver.findElement(By.name("UserIdCheckBonus")).sendKeys(arg1);
		Thread.sleep(1000);
	}

	@When("^It clicks submit button$")
	public void It_clicks_submit_button() throws InterruptedException {
		driver.findElement(By.id("submitCheckBonus")).submit();
		Thread.sleep(5000);
	}

	@Then("^The \"([^\"]*)\" is displayed$")
	public void The_is_displayed(String arg1) {
		System.out.println("Message is " + arg1);
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
