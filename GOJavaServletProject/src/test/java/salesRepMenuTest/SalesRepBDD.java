package salesRepMenuTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class SalesRepBDD {
	
	String chromeDriverPath = "C:\\Users\\kuroycho\\Project Dependencies\\chromedriver_win32\\chromedriver.exe";
	WebDriver driver = null;

	@Given("^I am on SalesRepMenu$")
	public void goToSalesRepMenu() {
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		driver = new ChromeDriver();
		driver.navigate().to("http://localhost:9090/go/SalesRepMenu.html");
		System.out.println("Title: " + driver.getTitle());
	}

	@When("^I enter username as \"(.*)\"$")
	public void enterUsername(String arg1) {
		System.out.println(arg1);
		driver.findElement(By.id("UserId")).sendKeys(arg1);
	}

	@When("^Clicks on submit$")
	public void submitCheck() throws InterruptedException {
		driver.findElement(By.id("sales-rep-submit")).click();
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