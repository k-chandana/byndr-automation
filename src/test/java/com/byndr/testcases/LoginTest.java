package com.byndr.testcases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//import com.byndr.AdminHomePage;
import com.byndr.LoginPage;
import com.byndr.TestBase;

public class LoginTest {
	public static final Logger logger = Logger.getLogger(LoginTest.class.getName());
	TestBase tb;
	LoginPage loginpage;
	//AdminHomePage homepage;
	
	@BeforeClass
	public void init(){
		tb = TestBase.getTestBaseInstance();
		loginpage = new LoginPage(tb);
	}
	@Test(priority=1)
	public void byndrTitleTest(){
		String title = loginpage.validateByndrTitle();
		Assert.assertEquals(title, "Byndr Mobile Learning app - The easy LMS for smartphones");
	}
	@Test(priority=2)
	public void loginTest(){
		 loginpage.adminLogin();
	}
	
	
	

}
