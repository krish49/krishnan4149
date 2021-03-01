package com.htc.seleniumacademy.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.htc.seleniumacademy.util.Testutil;

//import com.crm.qa.base.ChromeDriver;
//import com.crm.qa.base.FirefoxDriver;
//mport com.crm.qa.util.TestUtil;
//import com.crm.qa.util.WebEventListener;

public class TestBase {
		public static WebDriver driver;
		public static Properties prop;
		public  static EventFiringWebDriver e_driver;
	//	public static WebEventListener eventListener;
		
		public TestBase(){
			try {
				prop = new Properties();
				FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "C:\\Users\\huber\\eclipse-workspace\\Ecommine\\src\\test\\resources\\apps.properties");
				prop.load(ip);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		public static void initialization(){
			String browserName = prop.getProperty("browser");
			
			if(browserName.equals("chrome")){
				System.setProperty("chromedriver", "C:\\Users\\huber\\Desktop");	
				driver = new ChromeDriver(); 
			}
	     	driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(Testutil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(Testutil.IMPLICIT_WAIT, TimeUnit.SECONDS);
			
			driver.get(prop.getProperty("url"));
			
		}
}
		