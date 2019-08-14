package testing;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
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

public class Admin {
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
	 */
	@Test(priority=0)
	public void login(){
		driver.findElement(By.xpath(prt.getProperty("signin"))).click();
		driver.findElement(By.xpath(prt.getProperty("username"))).sendKeys("satish_admin");
		driver.findElement(By.xpath(prt.getProperty("password"))).sendKeys("password");
		driver.findElement(By.xpath(prt.getProperty("login"))).click();
		log.info("login completed successfully ");
	}
	/**
	 * to add a student 
	 * @throws InterruptedException 
	 */
	/*@Test(priority=1)
	public void addStudent(){
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.xpath(prt.getProperty("addstudent"))).click();
		driver.findElement(By.xpath(prt.getProperty("fullname"))).sendKeys("vani");
		driver.findElement(By.xpath(prt.getProperty("email"))).sendKeys("vani123@gmail.com");
		driver.findElement(By.xpath(prt.getProperty("spassword"))).sendKeys("77890");
		driver.findElement(By.xpath(prt.getProperty("phn"))).sendKeys("0987654322");
		driver.findElement(By.xpath(prt.getProperty("prntPhn"))).sendKeys("0123455777");
		driver.findElement(By.xpath(prt.getProperty("studentId"))).sendKeys("98754");
		driver.findElement(By.xpath(prt.getProperty("department"))).click();
		driver.findElement(By.xpath(prt.getProperty("select3"))).click();
		driver.findElement(By.xpath(prt.getProperty("program"))).click();
		driver.findElement(By.xpath(prt.getProperty("select4"))).click();
		driver.findElement(By.xpath(prt.getProperty("year"))).click();
		driver.findElement(By.xpath(prt.getProperty("select5"))).click();
		driver.findElement(By.xpath(prt.getProperty("save"))).click();
	}
	/**
	 * to take attendance 
	 */
	@Test(priority=1)
	public void attendance() throws InterruptedException{
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.findElement(By.xpath(prt.getProperty("departments"))).click();
		driver.findElement(By.xpath(prt.getProperty("demo1"))).click();
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prt.getProperty("programs"))));
		driver.findElement(By.xpath(prt.getProperty("programs"))).click();
		driver.findElement(By.xpath(prt.getProperty("years"))).click();
		driver.findElement(By.xpath(prt.getProperty("courses"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prt.getProperty("attendance"))));
		driver.findElement(By.xpath(prt.getProperty("attendance"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prt.getProperty("date"))));
		driver.findElement(By.xpath(prt.getProperty("date"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prt.getProperty("student1"))));
		driver.findElement(By.xpath(prt.getProperty("student1"))).click();
		driver.findElement(By.xpath(prt.getProperty("student2"))).click();
		driver.findElement(By.xpath(prt.getProperty("student3"))).click();
		driver.findElement(By.xpath(prt.getProperty("save"))).click();
		log.info("save button Click");
		//driver.navigate().back();
		//driver.navigate().refresh();
	}
	/**
	 * to upload material and post to all students and all faculty in demo by using autoit
	 * @throws IOException
	 * @throws InterruptedException 
	 * @throws AWTException 
	 */
	/*@Test(priority=3)
	public void post() throws IOException, InterruptedException, AWTException{
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.xpath(prt.getProperty("message"))).click();
		driver.findElement(By.xpath(prt.getProperty("to"))).click();
		driver.findElement(By.xpath(prt.getProperty("demo"))).click();
		driver.findElement(By.xpath(prt.getProperty("all"))).click();
		driver.findElement(By.xpath(prt.getProperty("select1"))).click();
		driver.findElement(By.xpath(prt.getProperty("text"))).sendKeys("test2");
		driver.findElement(By.xpath(prt.getProperty("addmaterial"))).click();
		driver.findElement(By.xpath(prt.getProperty("upload"))).click();
		Thread.sleep(3000);
		Robot robot =new Robot();
		StringSelection imgPath = new StringSelection("C:\\Users\\Chandana\\test2.txt");
    	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(imgPath, null);
		Thread.sleep(3000);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_S);
	    robot.keyRelease(KeyEvent.VK_S);
	    robot.keyRelease(KeyEvent.VK_CONTROL);  
	    
	    robot.keyPress(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_V);
	    robot.keyRelease(KeyEvent.VK_V);
	    robot.keyRelease(KeyEvent.VK_CONTROL);  
		
		 //Click enter to click Open button
	    robot.keyPress(KeyEvent.VK_ENTER);
	    robot.keyRelease(KeyEvent.VK_ENTER);
		
		Thread.sleep(3000);

	    driver.findElement(By.xpath(prt.getProperty("review"))).click();
	    Thread.sleep(2000);
		driver.findElement(By.xpath(prt.getProperty("send"))).click();
		log.info("file posted successfully");
	}*/
	
}
