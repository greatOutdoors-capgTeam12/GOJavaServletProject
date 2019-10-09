package logoutTest;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class logoutTest {

	WebDriver driver;

	@Given("^User is on GO homepage$")
	public void User_is_on_GO_homepage() {
		System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\araj15\\\\Documents\\\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:9090/go/index.html");
	}

	@When("^User Clicks on Login Tab$")
	public void user_clicks_on_login_tab() {
		driver.findElement(By.id("Login-tab")).click();
	}
	
	@When("^User Clicks on Logout Button$")
	public void user_clicks_on_logout_button() {
		driver.findElement(By.id("logout-nav")).click();
	}

	@When("User enters {string}")
	public void user_enters(String UserId) {
		driver.findElement(By.id("logout-ID")).sendKeys(UserId);

	}

	@When("^User clicks on the submit button$")
	public void user_clicks_the_submit_button() {
		driver.findElement(By.id("logout-submit")).click();
	}

	@Then("^\"(.*)\" Successfully Logs-Out$")
	public void user_successfully_logout(String userId) {
		String Title = driver.findElement(By.xpath("//*[@id=\"msg\"]/h2")).getText();
		String title = "USER WITH USER ID : " + userId + " HAS BEEN SUCCESSFULLY LOGGED OUT";
		assertEquals(Title, title);
		System.out.println("Closing the driver.");
		if (driver != null) {
			driver.quit();
		}
	}

	@Then("^User already logged out Message displayed$")
	public void user_logout_denied() throws InterruptedException {
		String Title = driver.findElement(By.xpath("//*[@id=\"err\"]/h2")).getText();
		String title = "ERROR IN LOGGING OUT >> USER ALREADY LOGGED-OUT";
		assertEquals(Title, title);
		System.out.println("Closing the driver.");
		if (driver != null) {
			driver.quit();
		}
	}
}
