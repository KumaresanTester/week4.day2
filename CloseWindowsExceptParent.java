package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CloseWindowsExceptParent {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://www.irctc.co.in/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.findElement(By.xpath("//button[text()='OK']")).click();
		driver.findElement(By.xpath("//a[text()=' FLIGHTS ']")).click();

		
		Set<String> setWindowHandles = driver.getWindowHandles();
		List<String> listWindowHandles = new ArrayList<String>(setWindowHandles);
		

		for (int i = listWindowHandles.size(); i > 1; i--) {
			driver.switchTo().window(listWindowHandles.get(i - 1));
			String pageTitle = driver.getTitle();
			driver.close();
			System.out.println(pageTitle + " Closed Window Successfully ");
		}
	}
}
