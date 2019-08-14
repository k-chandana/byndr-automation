package com.byndr;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TestBase {
	public static final Logger logger = Logger.getLogger(TestBase.class.getName());
	public static WebDriver driver;
	
	//Initialize property file
	public static Properties prop = new Properties();
	private static TestBase testBase;
	//Intialize sikuli
	
	//Initialize java robot class

	public static TestBase getTestBaseInstance(){

		if( testBase == null){
			testBase = new TestBase();
		}
		return testBase;
	}
	private TestBase(){
		logger.info("initializing the test base");
		String log4jConfPath = "D:\\Automation\\byndr\\byndr-automation\\log4j.properties";
		//log4jConfPath variable name
		PropertyConfigurator.configure(log4jConfPath);
		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream("D:\\Automation\\byndr\\byndr-automation\\src\\main\resources\\login.properties");
			prop.load(fis);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		openApplicationInBrowser();
	}



	public void init(){
		if( driver == null){

			openApplicationInBrowser();
		}
	}


	    public static void openApplicationInBrowser(){

		String browserName = prop.getProperty("browser");
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver",System.getProperty("D:\\softwares\\chromedriver_win32\\chromedriver.exe"));
			ChromeOptions options = new ChromeOptions(); 
			options.addArguments("disable-infobars"); 
			driver = new ChromeDriver(options);
		
			logger.info("Chrome browser initiliazed");
		}
		else if (browserName.equals("firefox"))
		{
			driver= new FirefoxDriver();	
		}
		driver.manage().window().maximize(); // maximize window
		logger.info("window maximize");
		
		driver.manage().deleteAllCookies();  // delete all cookies
		logger.info("delete all cokkies.");
		
		driver.get(prop.getProperty("URL"));
		logger.info("URL Entered"+prop.getProperty("URL"));
		waitMethod();
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		
	}
	    public void close(){
	    	driver.close();
	    	logger.info("Browser closing");
	    }
	    
	    public static  void waitMethod(){
	    	//implicit wait 
	    	 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			 logger.info("implicit wait max 20 sec");
	    }
	    
	    public void ScrollDown(){
	    	JavascriptExecutor javascriptexecuter= ((JavascriptExecutor) driver);
	    	 javascriptexecuter.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	    }
	    
	 
	     
	    public void CTRL_V() throws AWTException{
	    	Robot robot = new Robot();
	    	 robot.keyPress(KeyEvent.VK_CONTROL);
	    	    robot.keyPress(KeyEvent.VK_V);
	    	    robot.keyRelease(KeyEvent.VK_V);
	    	    robot.keyRelease(KeyEvent.VK_CONTROL);  
	    }
	    
	    
	    public void CTRL_ENTER() throws AWTException
	    
	    {
	    	Robot robot = new Robot();
	    	    //Click enter to click Open button
	    	    robot.keyPress(KeyEvent.VK_ENTER);
	    	    robot.keyRelease(KeyEvent.VK_ENTER);
	    }
	    
	    public void copyPath()
	    
	    {
	    	StringSelection imgPath = new StringSelection("C:\\Users\\Veera\\Desktop\\download.jpg");
	    	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(imgPath, null);
	    }
	    
	    public void ExplictWait(By WebElement)
	    {
	     WebDriverWait wait = new WebDriverWait(driver,20);  	
	     wait.until(ExpectedConditions.presenceOfElementLocated(WebElement));
	    }
	    
	    }


