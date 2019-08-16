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

public class AdminAddCourse {
	WebDriver driver;
	FileInputStream fis;
	Properties prt=new Properties();
	private static Logger log=Logger.getLogger(testing.AdminAddCourse.class);
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
	public void addCourse() throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.findElement(By.xpath(prt.getProperty("addcourse"))).click();
		driver.findElement(By.xpath(prt.getProperty("courseName"))).sendKeys("java");
		//Thread.sleep(3000);
		//driver.findElement(By.xpath(prt.getProperty("description"))).sendKeys("java");
		driver.findElement(By.xpath(prt.getProperty("department"))).click();
		driver.findElement(By.xpath(prt.getProperty("select3"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(prt.getProperty("program"))).click();
		driver.findElement(By.xpath(prt.getProperty("select4"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(prt.getProperty("year"))).click();
		driver.findElement(By.xpath(prt.getProperty("select5"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(prt.getProperty("academy"))).sendKeys("2018-2019");
		driver.findElement(By.xpath(prt.getProperty("faculty"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(prt.getProperty("search"))).sendKeys("maduri");
		driver.findElement(By.xpath(prt.getProperty("selectFaculty"))).click();
		driver.findElement(By.xpath(prt.getProperty("student"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(prt.getProperty("search"))).sendKeys("kumar");
		driver.findElement(By.xpath(prt.getProperty("selectStudent"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(prt.getProperty("search"))).sendKeys("Shalini");
		driver.findElement(By.xpath(prt.getProperty("selectStudent"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(prt.getProperty("search"))).sendKeys("rani");
		driver.findElement(By.xpath(prt.getProperty("selectStudent"))).click();
		driver.findElement(By.xpath(prt.getProperty("save"))).click();
	}
	@Test(priority=2)
	public void courseTest(){
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.findElement(By.xpath(prt.getProperty("departments"))).click();
		driver.findElement(By.xpath(prt.getProperty("demo1"))).click();
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prt.getProperty("programs"))));
		driver.findElement(By.xpath(prt.getProperty("programs"))).click();
		driver.findElement(By.xpath(prt.getProperty("years"))).click();
		driver.findElement(By.xpath(prt.getProperty("java"))).click();
		
		String expected="kumar#09876 Shalini#test rani#05754";
		String actual=driver.findElement(By.xpath(prt.getProperty("java"))).getText();
		if(actual.equals(expected)){
			log.info("courseTest completed successfully ");
		}
		else{
			log.error("courseTest failed ");
		}
	}
}
