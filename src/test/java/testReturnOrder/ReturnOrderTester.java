package testReturnOrder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ReturnOrderTester {
	
		String chromeDriverPath = "C:\\Users\\kuroycho\\Project Dependencies\\chromedriver_win32\\chromedriver.exe";
		WebDriver driver = null;

		@Given("^The sales representative is in go's website$")
		public void The_sales_representative_is_logged_into_the_go_s_website() {
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			driver = new ChromeDriver();
			driver.navigate().to("http://localhost:9090/go/index.html");
			System.out.println("Title: " + driver.getTitle());
		}

		@Given("^clicks the return order button$")
		public void clicks_the_return_order_button() {
			driver.findElement(By.name("salesRepDropDownClick")).click();
			driver.findElement(By.name("returnClick")).click();
			driver.findElement(By.name("returnOrderClick")).click();
		}

		@When("^It enters the UserId as \"(.*)\" and OrderId as \"(.*)\" and Reason as \"(.*)\"$")
		public void It_enters_the_UserId_as_and_OrderId_as(String arg1, String arg2, String arg3) {
			System.out.println("UserId = " + arg1);
			System.out.println("OrderId = " + arg2);
			System.out.println("Reason=" + arg3);
			driver.findElement(By.id("ReturnOrderUserId")).sendKeys(arg1);
			driver.findElement(By.id("ReturnOrderOrderId")).sendKeys(arg2);
			driver.findElement(By.id("ReturnOrderReason")).sendKeys(arg3);
		}

		@When("^It clicks submit button$")
		public void It_clicks_submit_button() {
			driver.findElement(By.id("ReturnOrderSubmit")).click();

		}

		@Then("^\"([^\"]*)\" is displayed$")
		public void is_displayed(String arg1) {
			System.out.println(driver.getCurrentUrl());
		}
		
		@After
		public void closeDriver () {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver.close();
		}
	}


