package com.byndr;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	public static final Logger logger = Logger.getLogger(LoginPage.class.getName());
	TestBase tb =null;

	public LoginPage(TestBase tb){
		this.tb = tb;
		PageFactory.initElements(TestBase.driver, this);
	}
	@FindBy(xpath="//a[@class='btn mobile-more']")
	WebElement signin;
	
	
	@FindBy(xpath="//input[@type='username']")
	WebElement username;


	@FindBy(xpath="//input[@type='password']")
	WebElement password;

	//Click login button
	@FindBy(xpath="//button[@type='submit']")
	WebElement loginbutton;
	
	public String validateByndrTitle(){
		return TestBase.driver.getTitle();
	}
	
	public void adminLogin()
	{
		//Username enter
		signin.click();
		TestBase.waitMethod();
		logger.info("signin successfully");
				
		//Username enter
		username.sendKeys(TestBase.prop.getProperty("adminUsername"));
		logger.info("enter user name " + TestBase.prop.getProperty("adminUsername"));

		//Password enter 
		password.sendKeys(TestBase.prop.getProperty("adminPassword"));
		logger.info("Enter password  : " + TestBase.prop.getProperty("adminPassword"));

		//Click login button
		loginbutton.click();
		TestBase.waitMethod();
		logger.info("Login successfully ");
		
		//return new AdminHomePage();
	}
	public TeacherHomePage teacherLogin()
	{
		//Username enter
		signin.click();
		TestBase.waitMethod();
		logger.info("signin successfully");
				
		//Username enter
		username.sendKeys(TestBase.prop.getProperty("teacherUsername"));
		logger.info("enter user name " + TestBase.prop.getProperty("teacherUsername"));

		//Password enter 
		password.sendKeys(TestBase.prop.getProperty("teacherPassword"));
		logger.info("Enter password  : " + TestBase.prop.getProperty("teacherPassword"));

		//Click login button
		loginbutton.click();
		TestBase.waitMethod();
		logger.info("Login successfully ");
		
		return new TeacherHomePage();
	}
	public StudentHomePage studentLogin()
	{
		//Username enter
		signin.click();
		TestBase.waitMethod();
		logger.info("signin successfully");
				
		//Username enter
		username.sendKeys(TestBase.prop.getProperty("studentUsername"));
		logger.info("enter user name " + TestBase.prop.getProperty("studentUsername"));

		//Password enter 
		password.sendKeys(TestBase.prop.getProperty("studentPassword"));
		logger.info("Enter password  : " + TestBase.prop.getProperty("studentPassword"));

		//Click login button
		loginbutton.click();
		TestBase.waitMethod();
		logger.info("Login successfully");
		
		return new StudentHomePage();
	}
	

}
