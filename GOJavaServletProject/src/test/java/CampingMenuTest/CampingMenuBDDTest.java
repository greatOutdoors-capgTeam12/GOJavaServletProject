package CampingMenuTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class CampingMenuBDDTest {
	
	String chromeDriverPath = "C:\\Users\\kuroycho\\Project Dependencies\\chromedriver_win32\\chromedriver.exe";
	WebDriver driver = null;

	@Given("^I am on Camping Menu$")
	public void goToCampingMenu() {
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		driver = new ChromeDriver();
		driver.navigate().to("http://localhost:9090/go/CampingMenu.html");
		System.out.println("Title: " + driver.getTitle());
	}

	@When("^I enter username as \"(.*)\"$")
	public void enterUsername(String arg1) {
		System.out.println(arg1);
		driver.findElement(By.id("UserId")).sendKeys(arg1);
	}
	@When("^I enter start date as \"(.*)\"$")
	public void enterStartDate(String arg1) {
		System.out.println(arg1);
		driver.findElement(By.id("dEntry")).sendKeys(arg1);
	}
	@When("^I enter end date as \"(.*)\"$")
	public void enterEndDate(String arg1) throws InterruptedException {
		System.out.println(arg1);
		driver.findElement(By.id("dExit")).sendKeys(arg1);
		Thread.sleep(10000);
	}

	@When("^Clicks on submit$")
	public void submitCheck() throws InterruptedException {
		driver.findElement(By.id("Camping_Menu_Submit")).click();
		Thread.sleep(5000);
	}

	// driver.close();

	@After
	public void close() {
		System.out.println("Closing the driver.");
		if (driver != null) {

			driver.quit();
		}
	}
}