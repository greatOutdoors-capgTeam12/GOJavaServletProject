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
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\kuroycho\\Project Dependencies\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:9090/go/index.html");
	}

	@When("^User Clicks on Login Tab$")
	public void user_clicks_on_login_tab() {
		driver.findElement(By.xpath("//*[@id=\"myNavbar\"]/ul/li[8]/a")).click();
	}
	
	@When("^User Clicks on Logout Button$")
	public void user_clicks_on_logout_button() {
		driver.findElement(By.xpath("//*[@id=\"myNavbar\"]/ul/li[8]/ul/li[3]/a")).click();
	}

	@When("User enters {string}")
	public void user_enters(String UserId) {
		driver.findElement(By.xpath("//*[@id=\"logout\"]/form/div/input")).sendKeys(UserId);

	}

	@When("^User clicks on the submit button$")
	public void user_clicks_the_submit_button() {
		driver.findElement(By.xpath("//*[@id=\"logout\"]/form/div/div/button")).click();
	}

	@Then("^User Successfully Logs-Out$")
	public void user_successfully_logout() {
		String Title = driver.getTitle();
		String title = "go";
		assertEquals(Title, title);
		//Thread.sleep(2000);
		driver.close();
	}

}
