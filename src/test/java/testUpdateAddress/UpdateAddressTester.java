package testUpdateAddress;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UpdateAddressTester {
	
		String chromeDriverPath = "C:\\Users\\kuroycho\\Project Dependencies\\chromedriver_win32\\chromedriver.exe";
		WebDriver driver = null;

		@Given("^User is in go's website$")
		public void The_sales_representative_is_logged_into_the_go_s_website() {
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			driver = new ChromeDriver();
			driver.navigate().to("http://localhost:9090/go/index.html");
			System.out.println("Title: " + driver.getTitle());
		}

		@Given("^User clicks the Update Address button$")
		public void clicks_the_return_order_button() {
			driver.findElement(By.id("retailer-dropdown-menu")).click();
			driver.findElement(By.id("AddressUpdate")).click();
			driver.findElement(By.id("UpdateAddress")).click();
		}
		
		@When("^User enters the addressId as \"(.*)\" and retailerId as \"(.*)\" and Building Number as \"(.*)\" and  City as \"(.*)\"  and  state as \"(.*)\" and Zip Code as \"(.*)\" and country as \"(.*)\"$")
		public void It_enters_required_details(String arg1, String arg2, String arg3,String arg4,String arg5, String arg6, String arg7) {
			System.out.println("RetailerId = " + arg2);			
			System.out.println("building num = " + arg3);
			System.out.println("city=" + arg4);
			System.out.println("state="+ arg5);
			System.out.println("zip="+ arg6);
			System.out.println("country="+ arg7);
			System.out.println("address_Id="+ arg1);
			driver.findElement(By.id("updateretailerId")).sendKeys(arg2);
			driver.findElement(By.id("updatebuilding_num")).sendKeys(arg3);
			driver.findElement(By.id("updatecity")).sendKeys(arg4);
			driver.findElement(By.id("updatestate")).sendKeys(arg5);
			driver.findElement(By.id("updatezip")).sendKeys(arg6);
			driver.findElement(By.id("updatecountry")).sendKeys(arg7);
			driver.findElement(By.id("updateaddress_ID")).sendKeys(arg1);
		}

		@When("^User clicks on Submit Button$")
		public void It_clicks_submit_button() {
			driver.findElement(By.name("updateaddresssubmit")).click();

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


