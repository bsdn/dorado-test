package cn.bsdn.selenium_client;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Untitled {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.firefox.bin", "C:/Program Files (x86)/Mozilla Firefox/firefox.exe");
    System.setProperty("webdriver.gecko.driver","E:/devtools/geckodriver-v0.15.0-win64/geckodriver.exe");
    driver = new FirefoxDriver();
    baseUrl = "http://localhost";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testUntitled() throws Exception {
    driver.get(baseUrl + "/oc/com.gopher.oc.view.login.Login.d");
    delay(5);
    driver.findElement(By.id("username_")).clear();
    driver.findElement(By.id("username_")).sendKeys("chenyijing");
    driver.findElement(By.id("password_")).clear();
    driver.findElement(By.id("password_")).sendKeys("chenyijing");
    driver.findElement(By.xpath("//input[@value='登录']")).click();
    driver.findElement(By.cssSelector("label.node-label")).click();
    driver.findElement(By.cssSelector("span.caption")).click();
    driver.findElement(By.xpath("//div[@id='d_tabControlWorkarea']/div/div[2]/ul/li[2]/span/span[2]")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | _self | 30000]]
  }

	private static void delay(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
  public void tearDown() throws Exception {
    if(driver != null)
	  driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
