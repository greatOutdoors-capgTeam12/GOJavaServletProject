package shelfTimeReportTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class getShelfTimeReport {
	String chromeDriverPath = "C:\\Users\\kuroycho\\Project Dependencies\\chromedriver_win32\\chromedriver.exe";
	WebDriver driver = null;

	@Given("^I am on Shelf Time Report Form in Great Outdoors home page$")
	public void goToGoAdminOnChrome() {
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		driver = new ChromeDriver();
		driver.navigate().to("http://localhost:9090/go/index.html");
		driver.findElement(By.id("admin-dropdown-list")).click();
		driver.findElement(By.id("admin-get-reports")).click();
		driver.findElement(By.id("admin-shelf-time-report")).click();
		System.out.println("Title: " + driver.getTitle());
	}
	
	
	@When("^I enter retialerId as \"(.*)\"$")
	public void enterRetailerId (String retailerId) {
		System.out.println ("Retailer Id entered: " + retailerId);
		driver.findElement(By.id("shelf-time-report-retailer-id")).sendKeys(retailerId);
	}
	
	@When("^I enter reportType as \"(.*)\"$")
	public void enterReportType (String reportType) {
		System.out.println ("Report Type entered: " + reportType);
		driver.findElement(By.id("shelf-time-report-report-type")).sendKeys(reportType);
	}
	
	@When("^I enter date as \"(.*)\"$")
	public void enterDateSelection (String date) {
		System.out.println ("Date entered: " + date);
		driver.findElement(By.id("shelf-time-report-date-selection")).sendKeys(date);
		driver.findElement(By.id("shelf-time-report-form-submit-button")).click();
	}
		
	@Then("^Report must be generated$")
	public void reportGenerated () {
		System.out.println ("Report Response:");
	}
	
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
