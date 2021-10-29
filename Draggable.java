package week4.day2;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Draggable {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

//		Open Web Application
		driver.get("https://jqueryui.com/draggable/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

//		Create Object for Actions class and use the dragAndDrop method.
		Actions action = new Actions(driver);
		driver.switchTo().frame(0);
		action.dragAndDropBy(driver.findElement(By.id("draggable")), 80, 120).perform();
		
//		Switch back from frame to main page
		driver.switchTo().defaultContent();
		
//		Close the web application
		driver.close();
	}

}
