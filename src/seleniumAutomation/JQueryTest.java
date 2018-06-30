package seleniumAutomation;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;



public class JQueryTest {
	WebDriver driver;
	JavascriptExecutor js;

	@SuppressWarnings("unchecked")
	@Test
	public void jQueryTest() throws InterruptedException {
		driver = new FirefoxDriver();
		driver.get("http://www.sogou.com");
		js = (JavascriptExecutor) driver;
		injectjQueryIfNeeded();
		List<WebElement> elements = (List<WebElement>) js
				.executeScript("return jQuery.find('a')");
		assertEquals(27, elements.size());
		for (int i=0;i<elements.size();i++){
			System.out.println(elements.get(i).getText() + "+++++");
			driver.close();
		}
	}

	public void injectjQueryIfNeeded() {
		if (!jQueryLoaded()) {
			injectjQuery();
		}

	}

	public Boolean jQueryLoaded() {
		Boolean loaded;
		try{
			loaded=(Boolean) js.executeScript("return", "jQuery()!=null");
		}catch(WebDriverException e){
			loaded=false;
		}
		return loaded;
	}
	
	public void injectjQuery(){
		js.executeScript("var headID="
				+ "document.getElementsByTagName(\"head\")[0];"
				+ "var newScript=document.createElement('script');"
				+ "newScript.type='text/javascript';" + "newScript.src="
				+ "'http://ajax.googleapis.com/ajax/" 
				+ "libs/jquery/1.7.2/jquery.min.js';"
				+ "headID.appendChild(newScript);");
	}

}
