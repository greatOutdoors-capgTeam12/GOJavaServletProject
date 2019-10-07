package shelfTimeReportTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class getShelfTimeReport {
	String chromeDriverPath = "C:\\Users\\kuroycho\\Project Dependencies\\chromedriver_win32\\chromedriver.exe";
	WebDriver driver = null;

	@Given("^I am on Great Outdoors home page$")
	public void goToGoAdminOnChrome() {
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		driver = new ChromeDriver();
		driver.navigate().to(
				"http://localhost:9090/go/index.html");
		System.out.println("Title: " + driver.getTitle());
	}
	
	
	@When("^I enter retialerId as \"(.*)\"$")
	public void enterRetailerId (String retailerId) {
		System.out.println ("Retailer Id entered: " + retailerId);
		driver.findElement(By.name("retailer-id")).sendKeys(retailerId);
	}
	
	@When("^I enter reportType as \"(.*)\"$")
	public void enterReportType (String reportType) {
		System.out.println ("Report Type entered: " + reportType);
		driver.findElement(By.name("report-type")).sendKeys(reportType);
	}
	
	@When("^I enter date as \"(.*)\"$")
	public void enterDateSelection (String date) {
		System.out.println ("Date entered: " + date);
		driver.findElement(By.name("date-selection")).sendKeys(date);
	}
	
	@Then("^Report must be generated$")
	public void reportGenerated () {
		
	}
}
