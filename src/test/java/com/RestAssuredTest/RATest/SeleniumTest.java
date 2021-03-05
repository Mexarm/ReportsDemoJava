package com.RestAssuredTest.RATest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.hamcrest.CoreMatchers;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.RestAssuredTest.RATest.reports.EReport;

public class SeleniumTest {

	private static WebDriver browser;
	@Rule
	public EReport eReport = new EReport(System.getProperty("user.dir") + "\\target\\ExtentReportResultsSelenium.html");

	@BeforeClass
	public static void setUp() {
		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized", "incognito");
		browser = new ChromeDriver(options);
	}

	@Test
	public void whenCorrectUsernameAndPasswordAreProvided_thenLoginSucceededMessageIsReceived() {
		
		browser.navigate().to("http://the-internet.herokuapp.com/login");
		WebElement username = browser.findElement(By.id("username"));
		WebElement password = browser.findElement(By.id("password"));
		WebElement loginButton = browser.findElement(By.xpath("//*[@id=\"login\"]/button"));
		username.sendKeys("tomsmith");
		password.sendKeys("SuperSecretPassword!");
		loginButton.click();
		WebElement flash = browser.findElement(By.xpath("//div[@id=\"flash\"]"));
		
		assertThat(flash.getText(),CoreMatchers.containsString("You logged into a secure area"));

	}

	@AfterClass
	public static void tearDown() {
		System.out.println("---Tests finished---");
		browser.quit();
	}
}
