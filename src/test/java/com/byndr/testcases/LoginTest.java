package com.byndr.testcases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.byndr.pageobjects.LoginPage;

public class LoginTest extends TestBase {
	public static final Logger logger = Logger.getLogger(LoginTest.class.getName());
	TestBase tb;
	LoginPage loginpage;
	
	@Test(priority=1)
	public void LoginPageTitleTest(){
		String title = page.getInstance(LoginPage.class).getLoginPageTitle();
		Assert.assertEquals(title, "Social Learning Management System (LMS) & Platform | Byndr");
		
	}
	@Test(priority=2)
	public void loginTest(){
		 page.getInstance(LoginPage.class).doLogin(prop.getProperty("adminUsername"), prop.getProperty("adminPassword"));
	}
}
