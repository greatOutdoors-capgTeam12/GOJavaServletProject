package loginTest;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class loginTest {

	WebDriver driver;

	@Given("^User is on GO homepage$")
	public void User_is_on_GO_homepage() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\kuroycho\\Project Dependencies\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:9090/go/index.html");
	}

	@When("^User Clicks on Login Tab$")
	public void user_clicks_on_login_tab() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"myNavbar\"]/ul/li[8]/a")).click();
	}
	
	@And("^User Clicks on Login Button$")
	public void user_clicks_on_login_button() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"myNavbar\"]/ul/li[8]/ul/li[1]/a")).click();
	}

	@And("User enters {string} and {string}")
	public void user_enters_and(String UserId, String password) throws InterruptedException {
		driver.findElement(By.name("ID")).sendKeys(UserId);
		driver.findElement(By.name("pass")).sendKeys(password);

	}

	@And("^User clicks on the submit button$")
	public void user_clicks_the_submit_button() throws InterruptedException {
		driver.findElement(By.xpath("//*[@id=\"login\"]/form/div/div/button[2]")).click();
	}

	@Then("^User Successfully Logs-In$")
	public void user_successfully_login() throws InterruptedException {
		String Title = driver.getTitle();
		String title = "go";
		assertEquals(Title, title);
		Thread.sleep(2000);
		driver.close();
	}

}
