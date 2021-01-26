package com.qa.BaseTest;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.base.BasePage;
import com.qa.pages.LoginPage;

public class BasePageTest {

	WebDriver driver;
	public BasePage basePage;
	public Properties prop;
	public LoginPage loginPage;

	
	//parameters helps to control the browser from testng.xml
	
//	@Parameters({"browser"})
//	@BeforeTest
//	public void setUp(String browserName)
//
//	{
//		basePage = new BasePage();
//		prop = basePage.init_prop();
//		prop.setProperty("browsername",browserName );
//		driver = basePage.init_driver(prop);
//		loginPage = new LoginPage(driver);
//	}

	
	@BeforeTest
	public void setUp()

	{
		basePage = new BasePage();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop);
		loginPage = new LoginPage(driver);
	}
	
	@AfterTest
	public void teardown()
	{
		driver.quit();
	}
}
