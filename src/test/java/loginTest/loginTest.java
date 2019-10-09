package loginTest;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class loginTest {

	WebDriver driver;

	@Given("^User is on GO homepage$")
	public void User_is_on_GO_homepage() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\araj15\\Documents\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:9090/go/index.html");
	}

	@When("^User Clicks on Login Tab$")
	public void user_clicks_on_login_tab(){
		driver.findElement(By.xpath("//*[@id=\"myNavbar\"]/ul/li[8]/a")).click();
	}
	
	@And("^User Clicks on Login Button$")
	public void user_clicks_on_login_button(){
		driver.findElement(By.xpath("//*[@id=\"myNavbar\"]/ul/li[8]/ul/li[1]/a")).click();
	}

	@And("User enters {string} and {string}")
	public void user_enters_and(String UserId, String password){
		driver.findElement(By.name("ID")).sendKeys(UserId);
		driver.findElement(By.name("pass")).sendKeys(password);

	}

	@And("^User clicks on the submit button$")
	public void user_clicks_the_submit_button(){
		driver.findElement(By.xpath("//*[@id=\"login\"]/form/div/div/button[2]")).click();
	}

	@Then("^\"(.*)\" Successfully Logs-In$")
	public void user_successfully_login(String userId) throws InterruptedException {
		String Title = driver.findElement(By.xpath("//*[@id=\"msg\"]/h2")).getText();
		String title = "USER WITH USER ID : " + userId + "HAS BEEN SUCCESSFULLY LOGGED-IN";
		assertEquals(Title, title);
		System.out.println("Closing the driver.");
		if (driver != null) {
			driver.quit();
		}
	}
	
	@Then("^Message UserId doesn't exist pops$")
	public void user_doesnot_exists() throws InterruptedException {
		String Title = driver.findElement(By.xpath("//*[@id=\"err\"]/h2")).getText();
		String title = "ERROR IN LOG-IN >> USER DOESN'T EXIST.PLEASE ENTER VALID USER_ID";
		assertEquals(Title, title);
		System.out.println("Closing the driver.");
		if (driver != null) {
			driver.quit();
		}
	}
	
	@Then("^Message password doesnot match$")
	public void password_doesnot_match() throws InterruptedException {
		String Title = driver.findElement(By.xpath("//*[@id=\"err\"]/h2")).getText();
		String title = "ERROR IN LOG-IN >> PLEASE ENTER THE CORRECT PASSWORD";
		assertEquals(Title, title);
		System.out.println("Closing the driver.");
		if (driver != null) {
			driver.quit();
		}
	}

}
