package stepDefinitions;

import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DealStepDefnition {

	 WebDriver driver;


	 @Given("^user is already on Login Page$")
	 public void user_already_on_login_page(){
	 System.setProperty("webdriver.chrome.driver", "C:\\Users\\roshni\\jars\\chromedriver.exe");
	 driver = new ChromeDriver();
	 driver.get("https://www.freecrm.com/index.html");
	 driver.manage().window().maximize();
	 }

	 @When("^title of login page is Free CRM$")
	 public void title_of_login_page_is_free_CRM(){
	 String title = driver.getTitle();
	 System.out.println(title);
	 //Assert.assertEquals("#1 Free CRM for Any Business: Online Customer Relationship Software", title);
	 }

	 @Then("^user enters username and password$")
	 public void user_enters_username_and_password(DataTable credentials) throws InterruptedException {
		List<List<String>> data = credentials.raw();
		driver.findElement(By.xpath("//button[text()='Got It']")).click();
		WebElement logIn=driver.findElement(By.xpath("//span[text()='Log In']"));
		 WebDriverWait wait=new WebDriverWait(driver,20);
		 wait.until(ExpectedConditions.elementToBeClickable(logIn));
		 logIn.click();
		 Thread.sleep(3000);
			 wait.until(new Function<WebDriver, Boolean>() {
				 public Boolean apply(WebDriver driver) {
					 System.out.println("Current Window State       : "
							 + String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
					 return String
							 .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
							 .equals("complete");
				 }
			 });
			 driver.findElement(By.xpath(".//a[text()='Classic CRM']")).click();
		 Thread.sleep(3000);
	 driver.findElement(By.xpath("//input[@name='username']")).sendKeys(data.get(0).get(0));
	 driver.findElement(By.xpath("//input[@name='password']")).sendKeys(data.get(0).get(1));
	 }


	 @Then("^user clicks on login button$")
	 public void user_clicks_on_login_button() {
	 WebElement loginBtn =
	 driver.findElement(By.xpath(".//input[@type='submit']"));
	 JavascriptExecutor js = (JavascriptExecutor)driver;
	 js.executeScript("arguments[0].click();", loginBtn);
	 }

	 @Then("^user is on home page$")
	 public void user_is_on_hopme_page(){
	 String title = driver.getTitle();
	 System.out.println("Home Page title ::"+ title);
	 //Assert.assertEquals("CRMPRO", title);
	 }


	 @Then("^user moves to new deal page$")
	 public void user_moves_to_new_contact_page() {
		driver.switchTo().frame("mainpanel");
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//a[contains(text(),'Deals')]"))).build().perform();
		driver.findElement(By.xpath("//a[contains(text(),'New Deal')]")).click();

		}

	 @Then("^user enters deal details$")
	 public void user_enters_contacts_details(DataTable dealData){
		 List<List<String>> dealValues =  dealData.raw();
		 driver.findElement(By.id("title")).sendKeys(dealValues.get(0).get(0));
		 driver.findElement(By.id("amount")).sendKeys(dealValues.get(0).get(1));
		 driver.findElement(By.id("probability")).sendKeys(dealValues.get(0).get(2));
		 driver.findElement(By.id("commission")).sendKeys(dealValues.get(0).get(3));
	 }

	 @Then("^Close the browser$")
	 public void close_the_browser(){
		 driver.quit();
	 }





}
