package cn.gloryroad;

import java.sql.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestTable {
	@Test
	public void testHandleiFrame() {
		WebDriver driver = new FirefoxDriver();
		WebElement webTable = driver.findElement(By.xpath(""));
		Table table = new Table(webTable);
		WebElement cell = table.getCell(3, 2);
		Assert.assertEquals(cell.getText(), "");
		WebElement cellInputElement = table.getWebElementInElementCell(3, 3, By.tagName("input"));
		cellInputElement.sendKeys("");
	}
}
