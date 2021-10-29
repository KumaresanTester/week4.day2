package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {

	public static void main(String[] args) throws InterruptedException, IOException {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
//      Launch Amazon Application
		driver.get("https://www.amazon.in/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

//		search as oneplus 9 pro 

		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro");
		driver.findElement(By.id("nav-search-submit-button")).click();

//		Get the price of the first product
		String firstProductPrice = driver.findElement(By.xpath("(//span[@class='a-price-whole'])[1]")).getText();
		System.out.println("Price of the First Product :" + firstProductPrice);

//		Print the number of customer ratings for the first displayed product
		String noOfCustomerRatings = driver.findElement(By.xpath("(//div[@class='a-row a-size-small'])[1]/span[2]"))
				.getText();
		System.out.println("No of Customer Ratings for the the First Product :" + noOfCustomerRatings);

//		Click on the stars
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//div[@class='a-row a-size-small'])[1]/span")).click();

//		Get the percentage of ratings for the 5 star.
		WebElement table = driver.findElement(By.xpath("//table[@id='histogramTable']"));
		String getStarTextPercent = table
				.findElement(By.xpath("(//a[contains(text(),'5 star')]/following::a[contains(text(),'%')])[1]"))
				.getText();
		System.out.println("Percentage of Ratings for the 5 Star is: " + getStarTextPercent);

//		Click the first text link of the first image
		driver.findElement(By.xpath("//div[@class='a-section a-spacing-none']/h2")).click();

//		Take a screen shot of the product displayed
		Set<String> setwindowHandles = driver.getWindowHandles();
		List<String> listwindowHandles = new ArrayList<String>(setwindowHandles);
		driver.switchTo().window(listwindowHandles.get(1));
		File screenShot = driver.getScreenshotAs(OutputType.FILE);
		File path = new File("./Amazon/Pic.png");
		FileUtils.copyFile(screenShot, path);

//		Click 'Add to Cart' button
		driver.findElement(By.id("add-to-cart-button")).click();

//		Get the cart subtotal and verify if it is correct.
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("attach-accessory-cart-subtotal")));
		String cartSubTotal = driver.findElement(By.id("attach-accessory-cart-subtotal")).getText();
		if (firstProductPrice.contains(cartSubTotal)) {
			System.out.println("Cart Sub Total and Product Price Matching Successfully " + "(Product Price :"
					+ firstProductPrice + ")" + "\n" + "(Cart Sub Total " + cartSubTotal + ")");
		} else {
			System.out.println("Cart Sub Total and Product Price Doesn't Matching Successfully " + "(Product Price :"
					+ firstProductPrice + ")" + "\n" + "(Cart Sub Total " + cartSubTotal + ")");
		}

//		Close the Application
		driver.quit();
	}

}
