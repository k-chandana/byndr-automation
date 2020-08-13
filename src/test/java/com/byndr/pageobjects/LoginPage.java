package com.byndr.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
	public static final Logger logger = Logger.getLogger(LoginPage.class.getName());
	public LoginPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	By signIn=By.xpath("//a[text()='Sign In']");
	By username=By.xpath("//input[@type='username']");
	By password=By.xpath("//input[@type='password']");
	By loginbutton=By.xpath("//button[@type='submit']");
	
	public String getLoginPageTitle(){
		return driver.getTitle();
	}
	public void doLogin(String username, String password){
		doClick(signIn);
		logger.info("signin successfully");
		doSendKeys(this.username, username);
		logger.info("enter user name " + username);
		doSendKeys(this.password, password);
		logger.info("Enter password  : " + password);
		doClick(loginbutton);
		logger.info("click Login button ");
	}
	
	/*public void teacherLogin(){
		doClick(signIn);
		logger.info("signin successfully");
		doSendKeys(username, prop.getProperty("teacherUsername"));
		logger.info("enter user name " + prop.getProperty("teacherUsername"));
		doSendKeys(password, prop.getProperty("teacherPassword"));
		logger.info("Enter password  : " + TestBase.prop.getProperty("teacherPassword"));
		doClick(loginbutton);
		logger.info("Login successfully ");
	}
	public void studentLogin(){
		doClick(signIn);
		logger.info("signin successfully");
		doSendKeys(username, prop.getProperty("studentUsername"));
		logger.info("enter user name " + prop.getProperty("studentUsername"));
		doSendKeys(password, prop.getProperty("studentPassword"));
		logger.info("Enter password  : " + TestBase.prop.getProperty("studentPassword"));
		doClick(loginbutton);
		logger.info("Login successfully ");
	}*/
	

}
