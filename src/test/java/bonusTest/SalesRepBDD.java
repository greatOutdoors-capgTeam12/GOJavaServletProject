package bonusTest;

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
	public void goToCGWebMailOnChrome() {
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		driver = new ChromeDriver();
		driver.navigate().to("http://localhost:9090/go/setBonus.html");
		System.out.println("Title: " + driver.getTitle());
	}

	@When("^I enter username as \"(.*)\"$")
	public void enterUsername(String arg1) {
		System.out.println(arg1);
		driver.findElement(By.id("userId")).sendKeys(arg1);
	}
	
	@When("^I enter bonus as \"(.*)\"$")
	public void enterBonus(String arg1) {
		System.out.println(arg1);
		driver.findElement(By.id("bonus")).sendKeys(arg1);
	}
	
	@When("^I click on set bonus$")
	public void submitCheck() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.id("Sales_Rep_Set_Bonus")).click();
	}

	// driver.close();
	@After
	public void close() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Closing the driver.");
		if (driver != null) {
			driver.quit();
		}
	}
}