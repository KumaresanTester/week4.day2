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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Myntra {

	public static void main(String[] args) throws InterruptedException, IOException {

//		Browser Setups and Disable Notifications
		WebDriverManager.chromedriver().setup();
		ChromeOptions notificationDisable = new ChromeOptions();
		notificationDisable.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(notificationDisable);
		driver.manage().window().maximize();

//		Initiate Explicit Wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

//		Open Myntra Application
		driver.get("https://www.myntra.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

//		Mouse hover on Men 
		WebElement eleMen = driver.findElement(By.xpath("//div[@class='desktop-navLink']/a[text()='Men']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(eleMen).perform();

//		Click Jackets 
		driver.findElement(By.linkText("Jackets")).click();

//		Find the Total Count of Item
		Thread.sleep(2000);
		String getTextOfCount = driver.findElement(By.xpath("//span[@class='title-count']")).getText();
		String[] splitArray = getTextOfCount.split(" ");
		int countofItems = Integer.parseInt(splitArray[2]);

//		Validate the sum of categories count matches
		String getTextCountOfJacket = driver.findElement(By.xpath("//span[@class='categories-num']")).getText();
		String countOfJacket = getTextCountOfJacket.replace('(', ' ').replace(')', ' ').trim();
		int coj = Integer.parseInt(countOfJacket);

		String getTextCountOfRainJacket = driver.findElement(By.xpath("(//span[@class='categories-num'])[2]"))
				.getText();
		String countOfRainJacket = getTextCountOfRainJacket.replace('(', ' ').replace(')', ' ').trim();
		int corj = Integer.parseInt(countOfRainJacket);

		int sumOfCategories = coj + corj;

		if (countofItems == sumOfCategories) {
			System.out.println("Sum Of Categories matches Total Count Of Items " + "(Total Count Of Items :"
					+ countofItems + ")" + "\n" + "(Sum Of Categories :" + sumOfCategories + ")");
		} else {
			System.out.println("Sum Of Categories doesn't matches Total Count Of Items " + "(Total Count Of Items :"
					+ countofItems + ")" + "\n" + "(Sum Of Categories :" + sumOfCategories + ")");
		}

//		Check jackets
		driver.findElement(By.xpath("//label[text()='Jackets']/div")).click();

//		Click + More option under BRAND
		driver.findElement(By.className("brand-more")).click();

//		Type Duke and click checkbox
		driver.findElement(By.xpath("//input[@placeholder='Search brand']")).sendKeys("Duke");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//label[text()='Duke']"))));
		driver.findElement(By.xpath("//label[text()='Duke']")).click();

//		Close the pop-up x
		driver.findElement(By.xpath("//span[contains(@class,'FilterDirectory-close')]")).click();

//		Confirm all the Coats are of brand Duke
		List<WebElement> eleCoatsList = driver
				.findElements(By.xpath("//ul[@class='results-base']//h3[@class='product-brand']"));

		boolean brandName = false;

		for (int j = 0; j < eleCoatsList.size(); j++) {
			String brandText = eleCoatsList.get(j).getText();

			if (brandText.equalsIgnoreCase("Duke")) {
				brandName = true;
			} else {
				brandName = false;
			}
		}
		if (brandName == true) {
			System.out.println("Brand Name Matches with Duke");
		} else {
			System.out.println("Brand Name doesn't Matches with Duke");
		}

//		Sort by Better Discount
		WebElement eleSortBy = driver.findElement(By.xpath("//div[@class='sort-sortBy']"));		
		builder.moveToElement(eleSortBy).perform();
		driver.findElement(By.xpath("//label[text()='Better Discount']")).click();
		
//		Find the price of first displayed item
		String firstPrice = driver.findElement(By.xpath("(//li[@class='product-base']//span[@class='product-discountedPrice'])[1]")).getText();
		System.out.println("Price for the First Item "+firstPrice);
		
//		Click on the first product
		driver.findElement(By.xpath("(//li[@class='product-base'])[1]")).click();
		
//		Switch to New window and Take a screen shot
		Set<String> setWindowHandles = driver.getWindowHandles();
		List<String> listWindowHandles = new ArrayList<String>(setWindowHandles);		
		driver.switchTo().window(listWindowHandles.get(1));		
		File screenShot = driver.getScreenshotAs(OutputType.FILE);
		File path = new File("./Myntra/pic.png");
		FileUtils.copyFile(screenShot, path);
		
//		Click on WishList Now
		driver.findElement(By.xpath("//span[text()='WISHLIST']")).click();
		
//		Close the Myntra Application
		driver.quit();

	}

}
