package com.bstek.dorado.test.sample;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bstek.dorado.test.core.ScreenHelper;

/**
 * Hello world!
 * 
 */
public class DoradoAutoTestSample {
	public static void helloWorld() {
		// 如果你的 FireFox 没有安装在默认目录，那么必须在程序中设置
		// System.setProperty("webdriver.firefox.bin", "D:\\Program
		// Files\\Mozilla Firefox\\firefox.exe");
		// 创建一个 FireFox 的浏览器实例
		WebDriver driver = new FirefoxDriver();
        System.setProperty("webdriver.gecko.driver", "");
		// 让浏览器访问 Baidu
		driver.get("http://www.baidu.com");
		// 用下面代码也可以实现
		// driver.navigate().to("http://www.baidu.com");

		// 获取 网页的 title
		System.out.println("1 Page title is: " + driver.getTitle());

		// 通过 id 找到 input 的 DOM
		WebElement element = driver.findElement(By.id("kw"));

		// 输入关键字
		element.sendKeys("zTree");

		// 提交 input 所在的 form
		element.submit();

		// 通过判断 title 内容等待搜索页面加载完毕，间隔10秒
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getTitle().toLowerCase().endsWith("ztree");
			}
		});

		// 显示搜索结果页面的 title
		System.out.println("2 Page title is: " + driver.getTitle());

		// 关闭浏览器
		driver.quit();
	}

	public static void main(String[] args) {
		start();
	}

	private static void start() {
		ScreenHelper.recordStart();
		final String url = "http://127.0.0.1/oc/com.gopher.oc.view.login.Login.d";
		WebDriver driver = null;
		try {
			//driver = chromeTest(url);
			driver = ffTest(url);
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File("E:/screenshots/screenshots1.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(screenshot, new File("E:/screenshots/screenshots1.jpg"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		ScreenHelper.recordStop();
		// driver.close();
		// ffTest();
	}

	@SuppressWarnings("unused")
	private static WebDriver chromeTest(String url) {
		//selenium2.47.0 for lower chrome
		System.setProperty("webdriver.chrome.driver", "E:/devtools/chromedriver_win32/chromedriver.exe");
		//System.setProperty("chromedriver --whitelisted-ips", "127.0.0.1");
		// 创建一个 ChromeDriver 的浏览器实例
		WebDriver driver = new ChromeDriver();
		try {
			testLogin(driver, url);
		} catch (Exception e) {
			e.printStackTrace();
			driver.quit();
		}
		return driver;
	}

	private static void testLogin(WebDriver driver, String url) {
		driver.manage().window().maximize();
		driver.get(url);
		delay(5);
		WebElement username1 = driver.findElement(By.id("username_"));
		username1.sendKeys("chenyijing");
		WebElement password1 = driver.findElement(By.id("password_"));
		password1.sendKeys("chenyijing");
		String btLogin = "//*[@id='myForm']/div[4]/input";
		WebElement lg = driver.findElement(By.ByXPath.xpath(btLogin));
		lg.click();
		delay(2);
	}

	private static WebDriver ffTest(String url) {
		System.setProperty("webdriver.firefox.bin", "C:/Program Files (x86)/Mozilla Firefox/firefox.exe");
		System.setProperty("webdriver.gecko.driver","E:/devtools/geckodriver-v0.15.0-win64/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		try {
			testLogin(driver, url);
		} catch (Exception e) {
			e.printStackTrace();
			driver.quit();
		}
		return driver;
	}

	@SuppressWarnings("unused")
	private static void test(WebDriver driver, String url) {
		// driver.navigate().to("http://www.calculator.net/");
		// 创建一个 FireFox 的浏览器实例
		driver.manage().window().maximize();
		driver.get(url);
		delay(1);
		// new WebDriverWait(driver, 5).until(new ExpectedCondition<Boolean>(){
		// public Boolean apply(WebDriver driver) {
		// return true;
		// }});
		// List<WebElement> eleList=driver.findElements(By.tagName("input"));

		// driver.switchTo().window("windowName")
		// Moving the focus from one window to another
		// driver.switchTo().frame("frameName")
		// swing from frame to frame
		// driver.switchTo().alert()
		// Helps in handling alerts
		WebElement broker = driver.findElement(By.id("broker_"));
		WebElement username1 = driver.findElement(By.id("username_show_"));
		broker.sendKeys("0000");
		username1.sendKeys("0000_admin");
		WebElement password1 = driver.findElement(By.id("password_"));
		password1.sendKeys("0000_admin");
		WebElement lg = driver.findElement(By.id("a_login"));
		lg.click();
		delay(2);
		WebElement menu1 = driver.findElement(By.xpath("//*[@id='d__uid_86']/div/table/tbody/tr[1]/td/div/label[2]"));
		menu1.click();
		delay(2);
		driver.switchTo().frame("iframe_com_sfit_dorado_view_trademanagement_systemstatus_SystemStatusSwitch_d")
				.findElement(By.xpath("//*[@id='d_textExchangeID']/div[2]/div"));
		// new WebDriverWait(driver, 5).until(new ExpectedCondition<Boolean>(){
		// public Boolean apply(WebDriver driver) {
		// return true;
		// }});
		delay(2);
		WebElement dp1 = driver.findElement(By.xpath("//*[@id='d_textExchangeID']/div[2]/div"));
		delay(1);
		dp1.click();
		WebElement dtableEle = driver.findElement(By.name("fortest_textExchangeID"));
		delay(1);
		WebElement cell = getTableCell(dtableEle, 2, 1);
		cell.click();
		// Actions action=new Actions(driver);
		// action.click(cell);
		driver.switchTo().parentFrame();
		WebElement logout = driver.findElement(By.linkText("退出"));
		logout.click();
		// WebElement btYes=driver.findElement(By.name("dialogMessageBox_0"));
		WebElement btNo = driver.findElement(By.name("dialogMessageBox_1"));
		// btNo.findElement(By.t)
		// btNo.findElement(By.xpath("span"));
		delay(2);
		btNo.click();
		delay(2);
		// 关闭浏览器
		// driver.quit();
	}

	public static void testUserManager(WebDriver driver) {
		WebElement menu1 = driver.findElement(By.xpath("//*[@id='d__uid_86']/div/table/tbody/tr[2]/td/div/label[2]"));
		menu1.click();
		delay(2);
		driver.switchTo().frame("iframe_com_sfit_dorado_view_trademanagement_SuperUser_d");
		delay(2);
		WebElement add = driver.findElement(By.xpath("//*[@id='d__uid_26']"));
		add.click();
		WebElement dialog = driver.findElement(By.id("d_dialogSuperUserEdit"));
		List<WebElement> inputList = dialog.findElements(By.tagName("input"));
		if (inputList != null && inputList.size() > 0) {
			delay(1);
			WebElement userCodeInput = inputList.get(0);
			delay(1);
			userCodeInput.sendKeys("1234_001");
			delay(1);
			WebElement userNameInput = inputList.get(1);
			delay(1);
			userNameInput.sendKeys("1234_用户一");
			WebElement pwdInput = inputList.get(2);
			delay(1);
			pwdInput.sendKeys("1");
			WebElement pwdInput2 = inputList.get(3);
			delay(1);
			pwdInput2.sendKeys("1");
		}
		WebElement btPanel = driver.findElement(By.id("d__uid_56"));
		WebElement btSure = btPanel.findElement(By.xpath("span"));
		btSure.click();
		delay(2);
		WebElement sure = driver.findElement(By.name("dialogMessageBox_0"));
		sure.findElement(By.xpath("span")).click();
		delay(1);
		//WebElement cancel = driver.findElement(By.name("dialogMessageBox_1"));
		delay(1);
		// cancel.findElement(By.xpath("span")).click();
		// WebElement btQuery = driver.findElement(By.id("d__uid_23"));
		// btQuery.findElement(By.xpath("span")).click();
	}

	private static WebElement getTableCell(WebElement table, int row, int col) {
		String xpath = "tbody/tr[" + row + "]/td[" + col + "]";
		WebElement cell = table.findElement(By.xpath(xpath));
		return cell;
	}

	private static void delay(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
