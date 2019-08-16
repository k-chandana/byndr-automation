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
		fis=new FileInputStream("D:\\Automation\\byndr\\byndr-automation\\src\\main\\resources\\admin.properties");
		prt.load(fis);
		System.setProperty("webdriver.chrome.driver", "D:\\softwares\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get(prt.getProperty("url"));
		driver.manage().window().maximize();
		log.info("page navigated successfully");
	}
	/**
	 * to login by admin
	 * @throws InterruptedException 
	 */
	@Test(priority=0)
	public void login(){
		driver.findElement(By.xpath(prt.getProperty("signin"))).click();
		driver.findElement(By.xpath(prt.getProperty("username"))).sendKeys("satish_admin");
		driver.findElement(By.xpath(prt.getProperty("password"))).sendKeys("password");
		driver.findElement(By.xpath(prt.getProperty("login"))).click();
		log.info("login completed successfully ");
	}
	
	@Test(priority=1)
	public void addFaculty() throws InterruptedException{
		Thread.sleep(3000);
		driver.findElement(By.xpath(prt.getProperty("addfaculty"))).click();
		driver.findElement(By.xpath(prt.getProperty("fullname"))).sendKeys("maduri");
		driver.findElement(By.xpath(prt.getProperty("email"))).sendKeys("maduri12345@gmail.com");
		driver.findElement(By.xpath(prt.getProperty("password"))).sendKeys("12345");
		driver.findElement(By.xpath(prt.getProperty("phone"))).sendKeys("9038271829");
		driver.findElement(By.xpath(prt.getProperty("save"))).click();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath(prt.getProperty("logout"))).click();
		driver.findElement(By.xpath(prt.getProperty("signout"))).click();
	}
	
	@Test(priority=2)
	public void facultyTest() throws InterruptedException{
		Thread.sleep(3000);
		driver.findElement(By.xpath(prt.getProperty("username"))).sendKeys("maduri12345@gmail.com");
		driver.findElement(By.xpath(prt.getProperty("password"))).sendKeys("12345");
		driver.findElement(By.xpath(prt.getProperty("login"))).click();
		
		Thread.sleep(3000);
		String expected="Home - Byndr";
		if(driver.getTitle().contains(expected)){
			log.info("facultyTest completed successfully ");
		}
		else{
			log.error("facultyTest failed ");
		}
			
	}
	
}
