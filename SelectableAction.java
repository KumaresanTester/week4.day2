package week4.day2;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SelectableAction {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

//		Open Web Application
		driver.get("https://jqueryui.com/selectable/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

//		Create object for Actions class and switch to frame
		Actions action = new Actions(driver);
		driver.switchTo().frame(0);

//		Created WebElement for each items
		WebElement item1 = driver.findElement(By.xpath("//ol[@id='selectable']/li[text()='Item 1']"));
		WebElement item3 = driver.findElement(By.xpath("//ol[@id='selectable']/li[text()='Item 3']"));
		WebElement item5 = driver.findElement(By.xpath("//ol[@id='selectable']/li[text()='Item 5']"));
		WebElement item7 = driver.findElement(By.xpath("//ol[@id='selectable']/li[text()='Item 7']"));

//		Performing Selecting action by item1 to item7
//		action.clickAndHold(item1).moveToElement(item7).release().perform();

//		Performing Selecting action using some keyboard actions
		action.sendKeys(Keys.ARROW_DOWN).keyDown(Keys.CONTROL).click(item1).click(item3).click(item5).click(item7)
				.keyUp(Keys.CONTROL).perform();
		
//		Switch back from frame to main page
		driver.switchTo().defaultContent();
		
//		Close the Web Application
		driver.close();
	}

}
