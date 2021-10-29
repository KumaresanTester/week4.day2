package week4.day2;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Resize {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

//		Open Web Application
		driver.get("https://jqueryui.com/resizable/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

//		Create object for Actions class and switch to frame
		Actions action = new Actions(driver);
		driver.switchTo().frame(0);

//		Performing Resize action
		WebElement eleResize = driver.findElement(By.xpath("//div[@id='resizable']/div[3]"));
		action.dragAndDropBy(eleResize, 100, 80).perform();

//		Switch back from frame to main page
		driver.switchTo().defaultContent();

//		Close the Web Application
		driver.close();

	}

}
