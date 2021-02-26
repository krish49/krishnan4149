package com.htc.extentManager.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestclassUsingListeners {
	public WebDriver driver;
	@BeforeClass
	public void beforeclass() {
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://demo-store.seleniumacademy.com/");
	}
	@AfterClass
	public void afterclass() {
		driver.quit();
	}
	@Test
	public void testsuccessful() {
		System.out.println("Execution Successful");
	}
	@Test
	public void testfailure() {
		System.out.println("Execution Failed");
	}
	@Test
	public void testskip() {
		System.out.println("Execution Skipped");
	}
	
	
}
