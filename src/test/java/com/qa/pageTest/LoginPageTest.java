package com.qa.pageTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.BaseTest.BasePageTest;
import com.qa.utilities.constants;

public class LoginPageTest extends BasePageTest {

	@Test

	public void loginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		System.out.println("login page titleee is : " + title);
		Assert.assertEquals(title, constants.lOGIN_PAGE_TITLE);
	}

	@Test

	public void forgotPwdLinkTest() {
		System.out.println("verifying forgot pwd link on login page");
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	
	
	

	@Test
	
	public void loginTest() {
	loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}


}
