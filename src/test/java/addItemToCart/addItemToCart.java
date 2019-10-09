package addItemToCart;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class addItemToCart {
	String chromeDriverPath = "C:\\Users\\kuroycho\\Project Dependencies\\chromedriver_win32\\chromedriver.exe";
	WebDriver driver;

	@Given("^retailer is logged into the go's website$")
	public void retailer_is_logged_into_the_go_s_website() {
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		driver = new ChromeDriver();
	    driver.navigate().to("http://localhost:9090/go/index.html");
	}

	@When("^User clicks the Add to Cart button$")
	public void clicks_the_Add_to_Cart_button() {
		 driver.findElement(By.xpath("//*[@id=\"retailer-dropdown-menu\"]")).click();
	     driver.findElement(By.xpath("//*[@id=\"myNavbar\"]/ul/li[4]/ul/li[1]/a")).click();    
	}

    @When("^It enters the \"(.*)\" ,\"(.*)\" ,\"(.*)\"$")
    public void It_enters_the(String retid, String productId, String quantity){
        driver.findElement(By.id("addItem-userId")).sendKeys(retid);
        driver.findElement(By.id("addItem-prodId")).sendKeys(productId);
        driver.findElement(By.xpath("/html/body/div[16]/form/div/input[3]")).sendKeys(quantity);
    }
    
    @When("^It clicks Add to Cart button$")
    public void It_clicks_Add_to_Cart_button() {
    	try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        driver.findElement(By.id("add-item-to-cart-submit")).click();
    }
    

    @Then("^\"([^\"]*)\" is displayed$")
    public void msg_is_displayed(String message) {
        System.out.println(message);
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        assertEquals("product is added to cart", message);
    }
    
    @After
	public void close() {
		System.out.println("Closing the driver.");
		if (driver != null) {
			driver.quit();
		}
	}
}

