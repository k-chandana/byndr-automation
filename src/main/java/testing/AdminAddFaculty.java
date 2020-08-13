/*
 * Add faculty Test in Admin portal
 * Enter all the details and save
 * search for the faculty in search bar and select the faculty name 
 * check whether given the details are matching
 * Logout and login as faculty
 * check whether the profile is as expected
 */

package testing;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties; 

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AdminAddFaculty {
	WebDriver driver;
	FileInputStream fis;
	Properties prt=new Properties();
	private static Logger log=Logger.getLogger(testing.AdminAddFaculty.class);
	/**
	 * to open chrome browser and navigate to byndr site
	 * @throws IOException
	 */
	@BeforeTest
	public void open() throws IOException{
		DOMConfigurator.configure("log4j.xml");
		fis=new FileInputStream("C:\\Users\\Lenovo\\git\\byndr-automation\\src\\main\\resources\\admin.properties");
		prt.load(fis);
		System.setProperty("webdriver.chrome.driver", "D:\\chandana\\softwares\\chromedriver_win32 (1)\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get(prt.getProperty("url"));
		driver.manage().window().maximize();
		log.info("page navigated successfully");
	}
	/**
	 * to login by admin
	 * @throws InterruptedException 
	 */
	@Test(priority=1)
	public void login() throws InterruptedException{
		driver.findElement(By.xpath(prt.getProperty("signin"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(prt.getProperty("username"))).sendKeys(prt.getProperty("admin_userid"));
		driver.findElement(By.xpath(prt.getProperty("pswd"))).sendKeys(prt.getProperty("admin_pswd"));
		driver.findElement(By.xpath(prt.getProperty("login"))).click();
		log.info("Admin login successfully ");
	}
	/**
	 * add faculty
	 * @throws InterruptedException 
	 */
	@Test(priority=2)
	public void addFaculty() throws InterruptedException, IOException{
		Thread.sleep(8000);
		driver.findElement(By.xpath(prt.getProperty("addfaculty"))).click();
		driver.findElement(By.xpath(prt.getProperty("fullname"))).sendKeys(prt.getProperty("teacher_name"));
		driver.findElement(By.xpath(prt.getProperty("email"))).sendKeys(prt.getProperty("teacher_email"));
		driver.findElement(By.xpath(prt.getProperty("password"))).sendKeys(prt.getProperty("teacher_password"));
		driver.findElement(By.xpath(prt.getProperty("phone"))).sendKeys(prt.getProperty("teacher_phone"));
		Thread.sleep(4000);
//		driver.findElement(By.xpath(prt.getProperty("save"))).click();
		driver.findElement(By.xpath(prt.getProperty("cancelButton"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(prt.getProperty("yesDiscard"))).click();
		log.info("addFaculty passed successfully ");
	}
	/**
	 * check whether faculty is added or not
	 * @throws InterruptedException 
	 */
	@Test(priority=3)
	public void facultyAddTest() throws InterruptedException{
		Thread.sleep(2000);
		driver.findElement(By.xpath(prt.getProperty("searchBar"))).sendKeys(prt.getProperty("teacher_name"));
		Thread.sleep(5000);
		driver.findElement(By.xpath(prt.getProperty("searchResult"))).click();
		Thread.sleep(2000);
		String name=driver.findElement(By.xpath(prt.getProperty("teacherName"))).getText();
		name=name.trim();
		Assert.assertEquals(prt.getProperty("teacher_name"), name);
		String email=driver.findElement(By.xpath(prt.getProperty("teacherEmail"))).getText();
		Assert.assertEquals(prt.getProperty("teacher_email"), email);
		String phone=driver.findElement(By.xpath(prt.getProperty("teacherPhn"))).getText();
		Assert.assertEquals(prt.getProperty("teacher_phone"), phone);
		log.info("faculty details verification passed successfully");
		
		Thread.sleep(2000);
		driver.navigate().back();
		Thread.sleep(2000);
		driver.findElement(By.xpath(prt.getProperty("openProfile"))).click();
		driver.findElement(By.xpath(prt.getProperty("signout"))).click();
	}
	/**
	 * check whether faculty can able to login and confirm profile
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	@Test(priority=4)
	public void facultyLoginTest() throws InterruptedException, IOException{
		Thread.sleep(2000);
		driver.findElement(By.xpath(prt.getProperty("username"))).sendKeys(prt.getProperty("teacher_email"));
		driver.findElement(By.xpath(prt.getProperty("password"))).sendKeys(prt.getProperty("teacher_password"));
		driver.findElement(By.xpath(prt.getProperty("login"))).click();
		Thread.sleep(8000);
		String title=prt.getProperty("homeTitle");;
		if(driver.getTitle().contains(title)){
			log.info("facultyLoginTest passed successfully");
			Thread.sleep(2000);
			driver.findElement(By.xpath(prt.getProperty("profile"))).click();
			driver.findElement(By.xpath(prt.getProperty("settings"))).click();
			String name=driver.findElement(By.xpath(prt.getProperty("profileName"))).getText();
			if(prt.getProperty("teacher_name").equals(name)){
				String email=driver.findElement(By.xpath(prt.getProperty("profileEmail"))).getText();
				Assert.assertEquals(prt.getProperty("teacher_email"), email);
				String phone=driver.findElement(By.xpath(prt.getProperty("profilePhone"))).getText();
				Assert.assertEquals(prt.getProperty("teacher_phone"),phone);
				log.info("facultyProfileTest passed successfully");
			}
			else{
				File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(srcFile, new File("C:\\Users\\Lenovo\\Pictures\\facultyProfile.png"));
				log.error("facultyProfileTest failed");
			}
		}
		else{
			File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File("C:\\Users\\Lenovo\\Pictures\\facultyLogin.png"));
			log.error("facultyLoginTest failed ");
		}
			
	}
	
}
