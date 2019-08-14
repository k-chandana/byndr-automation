package testing;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Student {
	WebDriver driver;
	FileInputStream fis;
	Properties prt=new Properties();
	private static Logger log=Logger.getLogger(testing.Teacher.class);
	/**
	 * to open chrome browser and navigate to byndr site
	 * @throws IOException
	 */
	@BeforeTest
	public void open() throws IOException{
		DOMConfigurator.configure("log4j.xml");
		fis=new FileInputStream("D:\\Automation\\byndr\\byndr-automation\\src\\main\\resources\\student.properties");
		prt.load(fis);
		System.setProperty("webdriver.chrome.driver", "D:\\softwares\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get(prt.getProperty("url"));
		driver.manage().window().maximize();
		log.info("page navigated successfully");
	}
	/**
	 * to login by teacher
	 */
	@Test(priority=1)
	public void login(){
		driver.findElement(By.xpath(prt.getProperty("signin"))).click();
		driver.findElement(By.xpath(prt.getProperty("username"))).sendKeys("shalinichavan0909@gmail.com");
		driver.findElement(By.xpath(prt.getProperty("password"))).sendKeys("byndr1234");
		driver.findElement(By.xpath(prt.getProperty("login"))).click();
		log.info("login completed successfully ");
	}
	/**
	 * to post a comment to teacher
	 * @throws InterruptedException 
	 */
	@Test(priority=2)
	public void post() throws InterruptedException{
		Thread.sleep(4000);
		driver.findElement(By.xpath(prt.getProperty("text"))).sendKeys("hai");
		driver.findElement(By.xpath(prt.getProperty("post"))).click();
		log.info("posted successfully");
	}
	

}
