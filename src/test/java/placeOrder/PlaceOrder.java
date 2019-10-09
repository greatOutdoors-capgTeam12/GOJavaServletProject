package placeOrder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PlaceOrder {
	String chromeDriverPath = "C:\\Users\\kuroycho\\Project Dependencies\\chromedriver_win32\\chromedriver.exe";
	WebDriver driver = null;

	@Given("^retailer is logged into the go's website$")
	public void retailer_is_logged_into_the_go_s_website() {
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		driver = new ChromeDriver();
		driver.navigate().to("http://localhost:9090/go/index.html");
		System.out.println("Title: " + driver.getTitle());
	}

	@Given("^clicks the Buy Now button$")
	public void clicks_the_Add_to_Cart_button() {
		driver.findElement(By.id("retailer-dropdown-menu")).click();
		driver.findElement(By.id("retailer-place-order")).click();
	}

	@When("^It enters the UserId as \"(.*)\" and AddressId as \"(.*)\"$")
	public void It_enters_the_UserId_as_and_ProductId_and_Quantity_as(String retid, String addid) {
		driver.findElement(By.id("place-order-retid")).sendKeys(retid);
		driver.findElement(By.id("place-order-addid")).sendKeys(addid);
	}

	@When("^It clicks BuyNow button$")
	public void It_clicks_submit_button() {
		driver.findElement(By.id("place-order-buynow")).submit();
	}

	@Then("^\"([^\"]*)\" is displayed$")
	public void is_displayed(String arg1) {
		System.out.println(arg1);
	}

	@After
	public void close() {
		System.out.println("Closing the driver.");
		if (driver != null) {
			driver.quit();
		}
	}
}
