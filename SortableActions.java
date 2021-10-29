package week4.day2;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SortableActions {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://jqueryui.com/sortable/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		Actions action = new Actions(driver);
		action.sendKeys(Keys.ARROW_DOWN).perform();
		driver.switchTo().frame(0);

		WebElement item1 = driver.findElement(By.xpath("//ul[@id='sortable']/li[text()='Item 1']"));
//		WebElement item2 = driver.findElement(By.xpath("//ul[@id='sortable']/li[text()='Item 2']"));
//		WebElement item3 = driver.findElement(By.xpath("//ul[@id='sortable']/li[text()='Item 3']"));
//		WebElement item4 = driver.findElement(By.xpath("//ul[@id='sortable']/li[text()='Item 4']"));
//		WebElement item5 = driver.findElement(By.xpath("//ul[@id='sortable']/li[text()='Item 5']"));
//		WebElement item6 = driver.findElement(By.xpath("//ul[@id='sortable']/li[text()='Item 6']"));
		WebElement item7 = driver.findElement(By.xpath("//ul[@id='sortable']/li[text()='Item 7']"));

		Point location = item7.getLocation();
		int x = location.getX();
		int y = location.getY();

		action.clickAndHold(item1).moveByOffset(x, y).release().perform();

	}

}
