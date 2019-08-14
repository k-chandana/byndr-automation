package testing;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Teacher {
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
		fis=new FileInputStream("D:\\Automation\\byndr\\byndr-automation\\src\\main\\resources\\teacher.properties");
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
		driver.findElement(By.xpath(prt.getProperty("username"))).sendKeys("shalini_teacher@byndr.in");
		driver.findElement(By.xpath(prt.getProperty("password"))).sendKeys("7331123014");
		driver.findElement(By.xpath(prt.getProperty("login"))).click();
		log.info("login completed successfully ");
	}
	/**
	 * to take attendance in c++ 
	 */
	@Test(priority=2)
	public void attendance(){
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.xpath(prt.getProperty("takeattend"))).click();
		driver.findElement(By.xpath(prt.getProperty("select1"))).click();
		driver.findElement(By.xpath(prt.getProperty("date"))).click();
		driver.findElement(By.xpath(prt.getProperty("time"))).click();
		driver.findElement(By.xpath(prt.getProperty("student1"))).click();
		driver.findElement(By.xpath(prt.getProperty("student2"))).click();
		driver.findElement(By.xpath(prt.getProperty("student3"))).click();
		driver.findElement(By.xpath(prt.getProperty("save"))).click();
		log.info("attendance completed successfully");
	}
	/**
	 * to post document to student
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@Test(priority=2)
	public void post() throws IOException, InterruptedException{
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.xpath(prt.getProperty("course"))).click();
		driver.findElement(By.xpath(prt.getProperty("cplus"))).click();
		driver.findElement(By.xpath(prt.getProperty("text"))).sendKeys("testing");
		driver.findElement(By.xpath(prt.getProperty("addmaterial"))).click();
		driver.findElement(By.xpath(prt.getProperty("upload"))).click();
		java.lang.Runtime.getRuntime().exec(prt.getProperty("itfilepath"));
		WebDriverWait wait=new WebDriverWait(driver, 8);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prt.getProperty("post1"))));
		driver.findElement(By.xpath(prt.getProperty("post1"))).click();
		log.info("file posted successfully");
		
	}
	/**
	 * to post comment to students after sending a doc
	 */
	@Test(priority=3)
	public void comment(){
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.xpath(prt.getProperty("comment"))).sendKeys("for testing");
		driver.findElement(By.xpath(prt.getProperty("post2"))).click();
		log.info("comment posted successfully");
	}

}
