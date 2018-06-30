package seleniumAutomation;

import static org.testng.Assert.assertEquals;

import java.sql.Driver;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class LocateTable {
	WebDriver driver = new ChromeDriver();
  @Test
  public void LocateTable() {
	  WebElement table = driver.findElement(By.id("table"));
	  List<WebElement> rows = table.findElements(By.tagName("tr"));
	  assertEquals(5, rows.size());
	  for(WebElement row:rows){
		  List<WebElement> cols = row.findElements(By.tagName("td"));
		  for(WebElement col:cols){
			  System.out.println(col.getText() + "\t");
		  }
	  }
	  System.out.println(" ");
  }
  @BeforeMethod
  public void beforeMethod() {
  }

  @AfterMethod
  public void afterMethod() {
  }

}
