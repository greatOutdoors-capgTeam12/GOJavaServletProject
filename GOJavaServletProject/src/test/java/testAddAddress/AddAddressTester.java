package testAddAddress;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddAddressTester {
	
		String chromeDriverPath = "C:\\Users\\kuroycho\\Project Dependencies\\chromedriver_win32\\chromedriver.exe";
		WebDriver driver = null;

		@Given("^User is in go's website$")
		public void The_sales_representative_is_logged_into_the_go_s_website() {
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			driver = new ChromeDriver();
			driver.navigate().to("http://localhost:9090/go/index.html");
			System.out.println("Title: " + driver.getTitle());
		}

		@Given("^User clicks the Add Address button$")
		public void clicks_the_return_order_button() {
			driver.findElement(By.id("retailer-dropdown-menu")).click();
			driver.findElement(By.id("AddressUpdate")).click();
			driver.findElement(By.id("AddAddress")).click();
		}
		
		@When("^User enters the retailerId as \"(.*)\" 	and  Building Number as \"(.*)\" and  City as \"(.*)\"  and  state as \"(.*)\" and Zip Code as \"(.*)\" and country as \"(.*)\"$")
		public void It_enters_the_UserId_as_and_OrderId_as(String arg1, String arg2, String arg3,String arg4,String arg5, String arg6) {
			System.out.println("RetailerId = " + arg1);
			System.out.println("building num = " + arg2);
			System.out.println("city=" + arg3);
			System.out.println("state="+ arg4);
			System.out.println("zip="+ arg5);
			System.out.println("country="+ arg6);
			driver.findElement(By.id("retailerId")).sendKeys(arg1);
			driver.findElement(By.id("building_num")).sendKeys(arg2);
			driver.findElement(By.id("city")).sendKeys(arg3);
			driver.findElement(By.id("state")).sendKeys(arg4);
			driver.findElement(By.id("zip")).sendKeys(arg5);
			driver.findElement(By.id("country")).sendKeys(arg6);
		}

		@When("^User clicks on Submit Button$")
		public void It_clicks_submit_button() {
			driver.findElement(By.name("RetailerSubmit")).click();

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


