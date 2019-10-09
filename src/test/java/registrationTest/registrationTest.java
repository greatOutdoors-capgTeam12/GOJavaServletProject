package registrationTest;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class registrationTest {

	WebDriver driver;

	@Given("^User is on GO homepage$")
	public void User_is_on_GO_homepage() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\kuroycho\\Project Dependencies\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:9090/go/index.html");
	}

	@When("^User Clicks on Login Tab$")
	public void user_clicks_on_login_tab(){
		driver.findElement(By.id("Login-tab")).click();
	}
	
	@When("^User Clicks on New Registration Button$")
	public void user_clicks_on_registration_button(){
		driver.findElement(By.id("newregister-nav")).click();
	}

	@When("User enters \"(.*)\" , \"(.*)\" , \"(.*)\" , \"(.*)\" , \"(.*)\" , \"(.*)\"")
	public void user_enters_(String userName, String UserId,String userMail, String password, String userNumber, String userCategory){
		driver.findElement(By.id("reg-name")).sendKeys(userName);
		driver.findElement(By.id("reg-id")).sendKeys(UserId);
		driver.findElement(By.id("reg-mail")).sendKeys(userMail);
		driver.findElement(By.id("reg-pass")).sendKeys(password);
		driver.findElement(By.id("reg-num")).sendKeys(userNumber);
		driver.findElement(By.id("reg-cat")).sendKeys(userCategory);

	}

	@When("^User clicks on the submit button$")
	public void user_clicks_the_submit_button(){
		driver.findElement(By.id("reg-submit")).click();
	}

	@Then("^\"(.*)\" Successfully Registered$")
	public void user_successfully_registers(String userId) throws InterruptedException {
		String Title = driver.findElement(By.xpath("//*[@id=\"msg\"]/h2")).getText();
		String title = "USER WITH USER ID : " + userId + "HAS BEEN SUCCESSFULLY REGISTERED";
		assertEquals(Title, title);
		System.out.println("Closing the driver.");
		if (driver != null) {
			driver.quit();
		}
	}

}
