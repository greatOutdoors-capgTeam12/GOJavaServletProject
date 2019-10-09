package deleteproduct;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestDeleteProduct {
	String chromeDriverPath = "C:\\Users\\kuroycho\\Project Dependencies\\chromedriver_win32\\chromedriver.exe";
	WebDriver driver = null;

	@Given("^I am on Delete Product Form in Great Outdoors Product page$")
	public void goToGoAdminOnChrome() {
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		driver = new ChromeDriver();
		driver.navigate().to("http://localhost:9090/go/allprod.jsp");
		driver.findElement(By.id("delete-product")).click();
		System.out.println("Title: " + driver.getTitle());
	}
	
	
	@When("^I enter product id as \"(.*)\"$")
	public void enterProductId (String productId) {
		System.out.println ("Product Id entered: " + productId);
		driver.findElement(By.id("del-prod-id")).sendKeys(productId);
	}
	
	
	
	@When("^I click on delete button$")
	public void clickSubmitButton () {
		System.out.println ("Delete Product button clicked");
		
		driver.findElement(By.xpath("//*[@id=\"delprod\"]/form/div/button[2]")).click();
		
	}
	
	@When("^I accept the alert$")
	public void clickAlertOk () {
		System.out.println ("Allert Accepted");
		driver.switchTo().alert().accept();
		
	}
	
	
	
	@Then("^\"(.*)\" Delete Product Message Success popped up$")
	public void deleteMessageSuccess(String productId)
	{
		String Message = driver.findElement(By.xpath("//*[@id=\"msg\"]")).getText();
        String msg = "PRODUCT WITH PRODUCT ID : "+productId.toUpperCase()+" HAS BEEN SUCCESSFULLY DELETED";
        assertEquals(Message, msg);
	}
	



	
	@Then("Delete Product Message Failure popped up$")
	public void deleteMessageFail()
	{
		String Message = driver.findElement(By.xpath("//*[@id=\"err\"]")).getText();
        String msg = "ERROR IN DELETING PRODUCT >> >>>NO SUCH PRODUCT EXISTS";
        assertEquals(Message, msg);
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
