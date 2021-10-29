package week4.day2;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebTableClass {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("http://www.leafground.com/pages/table.html");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		WebElement table = driver.findElement(By.id("table_id"));
		List<WebElement> tableRows = table.findElements(By.tagName("tr"));
		System.out.println("Number of Rows in Table " + tableRows.size());
		WebElement rowElement = tableRows.get(0);

		List<WebElement> tableColumn = rowElement.findElements(By.tagName("th"));
		System.out.println("Number of Column in Table " + tableColumn.size());

		for (WebElement rowContent : tableRows) {
			System.out.println(rowContent.getText());
		}

		for (int i = 1; i < tableRows.size(); i++) {
			WebElement getTableRow = tableRows.get(i);
			List<WebElement> getCoulumnTable = getTableRow.findElements(By.tagName("td"));
			WebElement printColumn = getCoulumnTable.get(1);
			System.out.println(printColumn.getText());
		}

	}

}
