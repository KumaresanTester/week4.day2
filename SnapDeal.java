package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal {

	public static void main(String[] args) throws InterruptedException, IOException {
//		Browser and Driver Setup
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

//		Launch Web Application
		driver.get("https://www.snapdeal.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

//		Moved to Mens Fashion
		WebElement eleMensFasion = driver
				.findElement(By.xpath("//li[text()='More Categories']/following::span[contains(text(),'Men')]"));
		Actions builder = new Actions(driver);
		builder.moveToElement(eleMensFasion).perform();

//		MOved Sports Shoes
		driver.findElement(By.xpath("//span[text()='Sports Shoes']")).click();

//		Get the count of the sports shoes
		Thread.sleep(2000);
		int size = driver.findElements(By.xpath("//div[@id='products']//img")).size();
		System.out.println("Count of Sports Shoes :" + size);

//		Click Training shoes
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();

//		Sort by Low to High
		driver.findElement(By.xpath("//span[text()='Sort by:']/following-sibling::i")).click();
		driver.findElement(By.xpath("//li[@class='search-li']")).click();

//		Get the count of the Training shoes
		Thread.sleep(3000);
		List<WebElement> eleSizeofTrainingShoes = driver
				.findElements(By.xpath("//div[@id='products']//span[@class='lfloat product-price']"));
		System.out.println("Count of Training Shoes " + eleSizeofTrainingShoes.size());

//		Created object for List
		List<Integer> listOfTrainingShoes = new ArrayList<Integer>();

//		Created one boolean data type
		boolean sort = false;

//		Added all the Training Shoes price in Created List object
		for (int i = 0; i < eleSizeofTrainingShoes.size(); i++) {
			String price = eleSizeofTrainingShoes.get(i).getAttribute("data-price");
//			Converted String to Integer
			int iPrice = Integer.parseInt(price);
			listOfTrainingShoes.add(iPrice);
		}

//		Sorted the List
		Collections.sort(listOfTrainingShoes);

//		Check if the items displayed are sorted correctly
		for (int i = 0; i < eleSizeofTrainingShoes.size(); i++) {
			String attributePrice = eleSizeofTrainingShoes.get(i).getAttribute("data-price");
//			Converted String to Integer
			int integerPrice = Integer.parseInt(attributePrice);
			if (integerPrice == listOfTrainingShoes.get(i)) {
				sort = true;
			} else {
				sort = false;
			}
		}

//		Give the Result whether correctly Low to High price Sorted
		if (sort == true) {
			System.out.println("Low To High Pice Sort Done Successfully");
		} else {
			System.out.println("Low To High Pice Sort not working Properly");
		}

//		Select the price range (900-1500)
		String priceRange = "Rs. 900 - Rs. 1500";
		WebElement eleFromValue = driver.findElement(By.xpath("//div[@class='price-text-box']/input[@name='fromVal']"));
		eleFromValue.clear();
		eleFromValue.sendKeys("900");
		WebElement eleToValue = driver.findElement(By.xpath("//div[@class='price-text-box']/input[@name='toVal']"));
		eleToValue.clear();
		eleToValue.sendKeys("1500");
		driver.findElement(By.xpath("//div[@class='price-go-arrow btn btn-line btn-theme-secondary']")).click();

//		Filter with color Navy
		Thread.sleep(3000);
		String color = "Navy";
		WebElement eleColor = driver.findElement(By.xpath("//label[@for='Color_s-Navy']"));
		eleColor.click();

//		verify the all applied filters 
		Thread.sleep(3000);
		String priceRangeText = driver.findElement(By.xpath("//a[@data-key='Price|Price']")).getText();
		if (priceRange.equalsIgnoreCase(priceRangeText)) {
			System.out.println("Correct Price Range Selected as " + priceRangeText);
		} else {
			System.out.println("Incorrect Price Range Selected as " + priceRangeText);
		}

		String colorSelected = driver.findElement(By.xpath("//a[@data-key='Color_s|Color']")).getText();
		if (color.equalsIgnoreCase(colorSelected)) {
			System.out.println("Correct Color Selected as " + colorSelected);
		} else {
			System.out.println("Incorrect Color Selected as " + colorSelected);
		}

//		Mouse Hover on first resulting Training shoes
		WebElement eleFirstResultingShoe = driver.findElement(By.xpath("//div[@class='product-desc-rating ']"));
		builder.moveToElement(eleFirstResultingShoe).perform();

//		click QuickView button
		driver.findElement(By.xpath("//div[@class='clearfix row-disc']/div")).click();

//		Print the cost and the discount percentage
		WebElement eleCostAndDiscount = driver
				.findElement(By.xpath("//div[@class='product-price pdp-e-i-PAY-l clearfix']"));
		System.out.println("Cost and Discount Percentage is :" + eleCostAndDiscount.getText());

//		Take the snapshot of the shoes
		WebElement eleShoes = driver.findElement(By.xpath("//div[@class='col-xs-11 quickViewModal ']"));
		File screenShotShoes = eleShoes.getScreenshotAs(OutputType.FILE);
		File path = new File("./Shoes/Pic.png");
		FileUtils.copyFile(screenShotShoes, path);

//		Close the current window
		driver.findElement(By.xpath("//div[@class='close close1 marR10']")).click();

//		Close the main window
		driver.close();
	}

}
