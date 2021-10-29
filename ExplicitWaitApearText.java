package week4.day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExplicitWaitApearText {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("http://www.leafground.com/pages/appear.html");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement eleapearText = driver.findElement(By.id("btn"));
		wait.until(ExpectedConditions.visibilityOf(eleapearText));

		System.out.println(driver.findElement(By.id("btn")).getText());
	}

}
