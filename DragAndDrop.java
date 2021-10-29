package week4.day2;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DragAndDrop {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

//		Open Web Application
		driver.get("https://jqueryui.com/droppable/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

//		Create object for Actions class and switch to frame
		Actions action = new Actions(driver);
		driver.switchTo().frame(0);

//		Create WebElement for Draggable and Droppable
		WebElement eleDraggable = driver.findElement(By.id("draggable"));
		WebElement eleDroppable = driver.findElement(By.id("droppable"));
		
//		Performing dragAndDrop action
		action.dragAndDrop(eleDraggable, eleDroppable).perform();

//		Switch back from frame to main page
		driver.switchTo().defaultContent();

//		Close the Web Application
		driver.close();

	}

}
