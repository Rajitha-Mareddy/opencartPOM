package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.base.BasePage;
import com.qa.utilities.ElementUtil;
import com.qa.utilities.constants;

public class LoginPage extends BasePage {
	private WebDriver driver;
	ElementUtil elementUtil;
	// By locators

	//1. By Locator / OR
		private By username = By.id("input-email");
		private By password = By.id("input-password");
		private By loginButton = By.cssSelector("input[value = 'Login']");
		private By ForgotPwdLink = By.cssSelector("div.form-group a");
		private By registerLink = By.linkText("Register");
		
		//2. Constructor of page class:
		
		public LoginPage(WebDriver driver) {
			this.driver = driver;
			elementUtil = new ElementUtil(this.driver);
		}
		
		
		public String getLoginPageTitle() {
			
			return elementUtil.waitForTitlePresent(5, constants.lOGIN_PAGE_TITLE);
		}
		
		
		public boolean isForgotPwdLinkExist() {
	
			return elementUtil.doIsDisplayed(ForgotPwdLink);
		}
		
		
		public void doLogin(String un, String pwd) {
			
			elementUtil.doSendKeys(username, un);
			elementUtil.doSendKeys(password, pwd);
			elementUtil.doClick(loginButton);
			
		}
}
