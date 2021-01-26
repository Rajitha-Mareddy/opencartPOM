package com.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.google.common.io.Files;
import com.qa.utilities.OptionsManager;

public class BasePage {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;

	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();

	/**
	 * This method is used to intialize driver
	 * 
	 * @param Browsername
	 * @return
	 */
	public WebDriver init_driver(Properties prop) {

		String Browsername = prop.getProperty("browsername");
		optionsManager = new OptionsManager(prop);
		if (Browsername.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\M.RAJITHA\\Documents\\chromedriver.exe");
			// driver = new ChromeDriver(optionsManager.getChromeoptions());
			tldriver.set(new ChromeDriver(optionsManager.getChromeoptions()));
		} else if (Browsername.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.firefox.driver", "C:\\Users\\mrajith\\Downloads\\firefoxdriver.exe");
			// driver = new FirefoxDriver(optionsManager.getFireFoxoptions());
			tldriver.set(new FirefoxDriver(optionsManager.getFireFoxoptions()));

		} else {
			System.out.println("Please pass correct browsername");
		}

		// driver.manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		getDriver().get(prop.getProperty("URL"));
		return getDriver();

	}

	/**
	 * 
	 * get driver
	 * 
	 * @return
	 */

	public static synchronized WebDriver getDriver() {
		return tldriver.get();

	}

	/**
	 * 
	 * This method is used to read properties file
	 * 
	 * @return prop
	 * 
	 */
	public Properties init_prop() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream(
					"C:\\Users\\M.RAJITHA\\Downloads\\JUNEPOMPractice-master\\hubspotPOMDesign\\src\\main\\java\\com\\qa\\config\\config.properties");

			try {
				prop.load(ip);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * This method helps to take screenshot
	 * @return 
	 */

	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/Screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			Files.copy(src, destination);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return path;

	}
}
