package com.example.tests;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Test2 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.firefox.bin",
				"E:/explorers/firefox/firefox.exe");
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();
  }

  @Test
  public void test2() throws Exception {
	driver.get(baseUrl + "/CTP2/dorado.view.frame.login.Login.d;jsessionid=D7B6BBB50E6EED02A2E9FE61CA2956AC");
    driver.findElement(By.id("broker_")).click();
    driver.findElement(By.id("broker_")).click();
    driver.findElement(By.id("broker_")).clear();
    driver.findElement(By.id("broker_")).sendKeys("0000");
    driver.findElement(By.id("username_show_")).clear();
    driver.findElement(By.id("username_show_")).sendKeys("0000_admin");
    driver.findElement(By.id("password_")).clear();
    driver.findElement(By.id("password_")).sendKeys("0000_admin");
    driver.findElement(By.id("a_login")).click();
    driver.findElement(By.xpath("//div[@id='d__uid_86']/div/table/tbody/tr[2]/td/div/label[2]")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectFrame | iframe_com_sfit_dorado_view_trademanagement_SuperUser_d | ]]
    driver.findElement(By.cssSelector("span.caption")).click();
    driver.findElement(By.cssSelector("span.button-left.button-left-hover > span.caption")).click();
    driver.findElement(By.cssSelector("#d__uid_121 > div.editor-wrapper > input.editor")).clear();
    driver.findElement(By.cssSelector("#d__uid_121 > div.editor-wrapper > input.editor")).sendKeys("admin");
    driver.findElement(By.cssSelector("#d__uid_66 > div.editor-wrapper > input.editor")).clear();
    driver.findElement(By.cssSelector("#d__uid_66 > div.editor-wrapper > input.editor")).sendKeys("admin8857");
    driver.findElement(By.cssSelector("span.button-left.button-left-hover > span.caption")).click();
    driver.findElement(By.cssSelector("span.button-left.button-left-hover > span.caption")).click();
    driver.findElement(By.cssSelector("span.button-left.button-left-hover > span.caption")).click();
    driver.findElement(By.cssSelector("td.current-cell > div.cell")).click();
    driver.findElement(By.cssSelector("span.button-left.button-left-hover > span.caption")).click();
    driver.findElement(By.cssSelector("span.button-left.button-left-hover > span.caption")).click();
  }

  @After
  public void tearDown() throws Exception {
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
