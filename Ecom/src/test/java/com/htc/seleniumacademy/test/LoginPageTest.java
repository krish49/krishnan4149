package com.htc.seleniumacademy.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.htc.seleniumacademy.page.HomePage;
import com.htc.seleniumacademy.page.LoginPage;
import com.htc.seleniumacademy.util.ReadExcelFile;
import com.htc.seleniumacademy.page.RegistrationPage;

public class LoginPageTest {
	RegistrationPage rpage = null;
	HomePage hpage = null;
	LoginPage lpage = null;
	WebDriver driver = null;


	Properties data=null;

	@BeforeClass
	public void loadProptiesFile()
	{
		data=new Properties();
		try {
			FileInputStream fis=new FileInputStream("E:\\program\\Ecom\\src\\test\\resources\\apps.properties");
			System.out.println(fis);
			data.load(fis);
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@BeforeMethod
	public void setup() {
		System.setProperty("chromedriver", "C:\\Users\\star\\Desktop\\Testing");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		hpage=new HomePage(driver);
		lpage=new LoginPage(driver);
		driver.get(hpage.getURL());
	}

	@AfterMethod
	public void tearDown() {
		//chromeDriver.close();
	}
	@DataProvider(name = "ecomLogin_valid")
	public Object[][] ecomStoreSigninValidData() throws IOException
	{

		ReadExcelFile ref= new ReadExcelFile(data.getProperty("datarepository.ecomstore.signin"));

		Object[][] loginDataSet=ref.getRecords(data.getProperty("datarepository.ecomstore.signin.sheetname.valid"));

		System.out.println("test"+loginDataSet.length);
		return loginDataSet;

	}
	@DataProvider(name = "ecomLogin_invalid")
	public Object[][] ecomStoreSigninInValidData() throws IOException
	{

		ReadExcelFile ref= new ReadExcelFile(data.getProperty("datarepository.ecomstore.signin"));

		Object[][] loginDataSet=ref.getRecords(data.getProperty("datarepository.ecomstore.signin.sheetname.invalid"));

		System.out.println(loginDataSet.length);
		return loginDataSet;

	}

	@Test(dataProvider ="ecomLogin_valid" )
	public void testLoginPage_enterCredantials_shouldLoginSuccessful(String ... parameters) {

		String email=parameters[0];
		String password=parameters[1];

		lpage=hpage.loginWithCredantials(email, password);
		Assert.assertEquals(lpage.pageTitle(), parameters[2]);


	}
	@Test(dataProvider ="ecomLogin_invalid" )
	public void testBrowserStackSignin_InValidloginCredential_ShouldErrorInSigninPage(String ... parameters)
	{

		String email=parameters[0];
		String password=parameters[1];

		lpage=hpage.loginWithCredantials(email, password);
		hpage.isErrorMsgDisplayed();
		Assert.assertEquals(hpage.isErrorMsgDisplayed(), parameters[2]);
	}

}
