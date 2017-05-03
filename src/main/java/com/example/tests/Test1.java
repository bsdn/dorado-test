//package com.example.tests;
//
//import com.thoughtworks.selenium.*;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import java.util.regex.Pattern;
//
//public class Test1 {
//	private Selenium selenium;
//
//	@Before
//	public void setUp() throws Exception {
//		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://localhost:8080/CTP2/");
//		selenium.start();
//	}
//
//	@Test
//	public void test1() throws Exception {
//		selenium.open("/CTP2/dorado.view.frame.login.Login.d;jsessionid=D7B6BBB50E6EED02A2E9FE61CA2956AC");
//		selenium.click("id=broker_");
//		selenium.click("id=broker_");
//		selenium.type("id=broker_", "0000");
//		selenium.type("id=username_show_", "0000_admin");
//		selenium.type("id=password_", "0000_admin");
//		selenium.click("id=a_login");
//		selenium.waitForPageToLoad("30000");
//		selenium.click("//div[@id='d__uid_86']/div/table/tbody/tr[2]/td/div/label[2]");
//		selenium.selectFrame("iframe_com_sfit_dorado_view_trademanagement_SuperUser_d");
//		selenium.click("css=span.caption");
//		selenium.click("css=span.button-left.button-left-hover > span.caption");
//		selenium.type("css=#d__uid_121 > div.editor-wrapper > input.editor", "admin");
//		selenium.type("css=#d__uid_66 > div.editor-wrapper > input.editor", "admin8857");
//		selenium.click("css=span.button-left.button-left-hover > span.caption");
//		selenium.click("css=span.button-left.button-left-hover > span.caption");
//		selenium.click("css=span.button-left.button-left-hover > span.caption");
//		selenium.click("css=td.current-cell > div.cell");
//		selenium.click("css=span.button-left.button-left-hover > span.caption");
//		selenium.click("css=span.button-left.button-left-hover > span.caption");
//	}
//
//	@After
//	public void tearDown() throws Exception {
//		selenium.stop();
//	}
//}