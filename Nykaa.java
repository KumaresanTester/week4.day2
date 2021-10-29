package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {
	public static void main(String[] args) throws InterruptedException {
//		Browser and Driver Setup
		WebDriverManager.chromedriver().setup();
		ChromeOptions option = new ChromeOptions();

//		Disable Notifications and Launch Web Application
		option.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://www.nykaa.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

//		Using Actions Class Mouse over to Brands and perform Search action
		WebElement eleBrand = driver.findElement(By.xpath("//a[text()='brands']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(eleBrand).perform();
		driver.findElement(By.id("brandSearchBox")).sendKeys("L'Oreal Paris");
		driver.findElement(By.xpath(("(//div[@id='scroller-container']//a[contains(text(),'Oreal Paris')])[1]")))
				.click();

//		Get Page Title and Check whether L'Oreal Paris contains
		String pageTitle = driver.getTitle();
		if (pageTitle.contains("L'Oreal Paris")) {
			System.out.println("Page Title contains L'Oreal Paris " + driver.getTitle());
		} else {
			System.out.println("Page Title not contains L'Oreal Paris " + driver.getTitle());
		}

//		Performing Filter Action Based on the Required categories
		driver.findElement(By.xpath("//span[contains(text(),'Sort By :')]")).click();
		WebElement eleSortType = driver.findElement(By.xpath("//span[text()='customer top rated']"));
		wait.until(ExpectedConditions.visibilityOf(eleSortType));
		eleSortType.click();
		WebElement eleCategpry = driver.findElement(By.xpath("//span[text()='Category']"));
		eleCategpry.click();
		WebElement eleSelectHair = driver.findElement(By.xpath("//span[text()='Hair']"));
		eleSelectHair.click();
		WebElement eleHairCare = driver.findElement(By.xpath("//span[text()='Hair Care']"));
		eleHairCare.click();
		WebElement eleShampooSelect = driver.findElement(By.xpath("//span[text()='Shampoo']"));
		eleShampooSelect.click();
		WebElement eleSelectConcern = driver.findElement(By.xpath("//span[text()='Concern']"));
		eleSelectConcern.click();
		WebElement eleColorProtectSelect = driver
				.findElement(By.xpath("//label[@class='control control-checkbox']//span[text()='Color Protection']"));
		eleColorProtectSelect.click();

//		Checked whether Shampoo selected in Filter
		WebElement eleFilter = driver.findElement(By.xpath("//div[@class='css-1kwg5gr']/span[text()='Shampoo']"));
		if (eleFilter.getText().equalsIgnoreCase("Shampoo")) {
			System.out.println("Shampoo Applied in Filter Successfully");
		} else {
			System.out.println("Shampoo is not Applied in Filter");
		}

//		Clicked the L'Oreal Paris Colour Protect Shampoo product
		driver.findElement(By.xpath("//div[contains(text(),'Oreal Paris Colour Protect Shampoo')]")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

//		Switched to next window by using getWindowHandles method and Perform necessary action
		Set<String> setWindowHandles = driver.getWindowHandles();
		List<String> listWindowHandles = new ArrayList<String>(setWindowHandles);
		driver.switchTo().window(listWindowHandles.get(1));
		WebElement eleSelectTag = driver.findElement(By.tagName("select"));
		Select object = new Select(eleSelectTag);
		object.selectByVisibleText("175ml");
		String textMRP = driver.findElement(By.xpath("(//span[text()='MRP:'])[1]/following-sibling::span")).getText();
		System.out.println("MRP is " + textMRP);
		driver.findElement(By.xpath("//div[@class='css-hx8d3x']//span[text()='ADD TO BAG']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//button[@class='css-5s18zx eoh7kvv0']")).click();

//		Switch to Frame and get the text of Grand Total, Then clicked Proceed
		Thread.sleep(3000);
		driver.switchTo().frame(0);
		WebElement eleGrandTotal = driver.findElement(By.xpath("//span[text()='Grand Total']/following-sibling::div"));
		String shoppingBagPageGrandTotal = eleGrandTotal.getText();
		System.out.println("Grand Total is " + shoppingBagPageGrandTotal);
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();

//		Switch back from Frame to Main page
		driver.switchTo().defaultContent();

//		Clicked Continue as Guest Option
		WebElement eleContinueAsGuest = driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']"));
		wait.until(ExpectedConditions.visibilityOf(eleContinueAsGuest));
		eleContinueAsGuest.click();

//		Get text of Grand Total and checked with Shopping Bag Grand Total whether both are matching by using If statement
		String cartPageGrandTotal = driver.findElement(By.xpath("//div[text()='Grand Total']/following-sibling::div"))
				.getText();
		if (shoppingBagPageGrandTotal.equals(cartPageGrandTotal)) {
			System.out.println("Both Grand Totals are matching. " + "Shopping Bag Grand Total: "
					+ shoppingBagPageGrandTotal + "\n" + "Cart Grand Total :" + cartPageGrandTotal);
		} else {
			System.out.println("Both Grand Total are Not Matching " + "ShoppingBag Grand Total: "
					+ shoppingBagPageGrandTotal + "\n" + "Cart Grand Total :" + cartPageGrandTotal);
		}

//		Close the Application
		driver.quit();
	}
}
