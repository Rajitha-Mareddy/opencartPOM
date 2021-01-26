package com.qa.utilities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {
	
	static WebDriver driver;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);

		return element;
	}

	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public void doLinkclick(List<WebElement> eb, String value) {
		for (WebElement w : eb) {
			String text = w.getText();
			System.out.println(text);

			if (text.equals(value))
				;
			w.click();
			break;
		}
	}

	// *******************************dropdown util******************************

	public void selectDropdownByVisibleText(By locator, String Value) {
		Select s = new Select(getElement(locator));
		s.selectByVisibleText(Value);
	}

	public void selectDropdownByVisibleValue(By locator, String Value) {
		Select s = new Select(getElement(locator));
		s.selectByValue(Value);
	}

	public void selectDropdownByVisibleIndex(By locator, int Value) {
		Select s = new Select(getElement(locator));
		s.selectByIndex(Value);
	}

	// **************************dropdownOptions util **********************
	public int getDropDownOptionsCount(By locator) {
		Select s = new Select(getElement(locator));
		List<WebElement> s1 = s.getOptions();
		return s1.size();

	}

	public List<String> getDropDownOptionsText(By locator) {
		List<String> textList = new ArrayList<String>();
		Select s = new Select(getElement(locator));
		List<WebElement> s1 = s.getOptions();
		for (WebElement w : s1) {
			String text = w.getText();
			textList.add(text);

		}
		return textList;
	}

	public void selectDropdownvalueWithoutSelectClass(By locator, String value) {

		List<WebElement> countryList = driver.findElements(locator);
		for (WebElement w : countryList) {
			String countryname = w.getText();
			if (countryname.equalsIgnoreCase(value)) {
				w.click();
				break;
			}

		}
	}

	// *********** sendkeys and click using Actions class********
	public void doActionSendKeys(By locator, String value) {

		Actions a = new Actions(driver);

		a.sendKeys(getElement(locator), value).perform();

	}

	public void doActionClick(By locator) {
		Actions a = new Actions(driver);
		a.click(getElement(locator)).perform();

	}

	// *************************implicit waits*****************************
	
	public List<WebElement> waitforAllElementsVisibility(int time,By locator)
	{
	WebDriverWait wait = new WebDriverWait(driver, time);
	return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public void getPageLinks(int time,By locator) {
		waitforAllElementsVisibility(time, locator).stream().forEach(ele->System.out.println(ele.getText()));
	}
	public WebElement waitForElementPresent(By locator, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public String waitForTitlePresent(int time, String Text) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.titleContains(Text));
		return driver.getTitle();

	}

	public String waitToGetTitle(int time, String Text) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.titleIs(Text));
		return driver.getTitle();

	}

	public WebElement waitForElementToBeVisible(By locator, int time) {
		WebElement element = getElement(locator);
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.visibilityOf(element));

	}

	public Boolean waitForUrl(int time, String Text) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.urlContains(Text));

	}

	public Alert waitForAlertToBePresent(int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.alertIsPresent());

	}

	public static WebElement waitForElementToBeClickable(By locator, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void waitClickWhenReady(By locator, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();

	}
	
	public WebElement WaitForElementWithFluentWait(By locator,int time,int interval)
	{
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(time)) // time out time
				.pollingEvery(Duration.ofSeconds(interval)) //in how many sec it have to repeat checking of the webelement
				.ignoring(NoSuchElementException.class); // if eleemnt is found in 3rd interval the for 1st and 2nd time it gives no such element to ignore that we use this ignoring
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
		
		
	}

}
