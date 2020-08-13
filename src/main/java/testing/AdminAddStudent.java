/*
 * Add student Test in Admin portal
 * Enter all the details and save
 * Goto the departments and check whether student has been added or not
 * check whether the details are matching or not
 * Logout and login as student
 * check whether the profile is as expected
 */

package testing;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AdminAddStudent {
	WebDriver driver;
	FileInputStream fis;
	String departmentSelected;
	String programSelected;
	String yearSelected;
	String sectionSelected;
	Properties prt=new Properties();
	private static Logger log=Logger.getLogger(testing.AdminAddStudent.class);
	/**
	 * open chrome browser and navigate to byndr site
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
	 * login as admin
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
	 * Add student
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	@Test(priority=2)
	public void addStudent() throws InterruptedException, IOException{
		Thread.sleep(15000);
		driver.findElement(By.xpath(prt.getProperty("addstudent"))).click();
		driver.findElement(By.xpath(prt.getProperty("fullname"))).sendKeys(prt.getProperty("student_Name"));
		driver.findElement(By.xpath(prt.getProperty("email"))).sendKeys(prt.getProperty("student_Email"));
		driver.findElement(By.xpath(prt.getProperty("password"))).sendKeys(prt.getProperty("student_Password"));
		driver.findElement(By.xpath(prt.getProperty("phone"))).sendKeys(prt.getProperty("student_Phone"));
		driver.findElement(By.xpath(prt.getProperty("prntPhn"))).sendKeys(prt.getProperty("student_PrntPhn"));
		driver.findElement(By.xpath(prt.getProperty("id"))).sendKeys(prt.getProperty("student_ID"));
		Thread.sleep(5000);
		driver.findElement(By.xpath(prt.getProperty("department"))).click();
		departmentSelected=driver.findElement(By.xpath(prt.getProperty("select3"))).getText();
		driver.findElement(By.xpath(prt.getProperty("select3"))).click();
		WebElement programLabel=driver.findElement(By.xpath(prt.getProperty("yearLabel")));
		Actions actionProgram = new Actions(driver);
		actionProgram.moveToElement(programLabel).build().perform();
		Thread.sleep(5000);
		driver.findElement(By.xpath(prt.getProperty("program"))).click();
		programSelected=driver.findElement(By.xpath(prt.getProperty("select4"))).getText();
		driver.findElement(By.xpath(prt.getProperty("select4"))).click();
		WebElement yearLabel=driver.findElement(By.xpath(prt.getProperty("yearLabel")));
		Actions builder = new Actions(driver);
		builder.moveToElement(yearLabel).build().perform();
		Thread.sleep(2000);
		driver.findElement(By.xpath(prt.getProperty("year"))).click();
		yearSelected=driver.findElement(By.xpath(prt.getProperty("select5"))).getText();
		driver.findElement(By.xpath(prt.getProperty("select5"))).click();
		Thread.sleep(2000);
		WebElement sectionLabel=driver.findElement(By.xpath(prt.getProperty("sectionLabel")));
		Actions builder1 = new Actions(driver);
		builder1.moveToElement(sectionLabel).build().perform();
		sectionSelected=driver.findElement(By.xpath(prt.getProperty("sectionA"))).getText();
		Thread.sleep(2000);
//		driver.findElement(By.xpath(prt.getProperty("save"))).click();
		driver.findElement(By.xpath(prt.getProperty("cancelButton"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(prt.getProperty("yesDiscard"))).click();
		log.info("addStudent passed successfully ");
		
	}
	/**
	 * check whether student is added or not
	 * @throws InterruptedException 
	 */
	@Test(priority=3)
	public void studentAddTest() throws InterruptedException{
		Thread.sleep(3000);
		driver.findElement(By.xpath(prt.getProperty("departments"))).click();
		driver.findElement(By.xpath(prt.getProperty("demo1"))).click();
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prt.getProperty("programs"))));
		driver.findElement(By.xpath(prt.getProperty("programs"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(prt.getProperty("years"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(prt.getProperty("panel-list-student"))).click();
		Thread.sleep(5000);
		String value=driver.findElement(By.xpath(prt.getProperty("studentCount"))).getText();
		value=value.substring(value.indexOf("(")+1,value.indexOf(")"));
		System.out.println("No.of Students: "+ value);
		int num=Integer.parseInt(value);
		for(int i=1; i<=num ; i++){
			Thread.sleep(1000);
			WebElement students=driver.findElement(By.xpath(prt.getProperty("listCount1")+i+prt.getProperty("listCount2")));
			String names=students.getText();
			if(names.equals((prt.getProperty("student_Name"))+" #"+(prt.getProperty("student_ID")))){
				students.click();
				break;
			}
		}
		Thread.sleep(2000);
		String name=driver.findElement(By.xpath(prt.getProperty("studentName"))).getText();
		Assert.assertEquals(prt.getProperty("student_Name"), name);
		String email=driver.findElement(By.xpath(prt.getProperty("studentEmail"))).getText();
		Assert.assertEquals(prt.getProperty("student_Email"), email);
		String phone=driver.findElement(By.xpath(prt.getProperty("studentPhn"))).getText();
		Assert.assertEquals(prt.getProperty("student_Phone"), phone);
		String id=driver.findElement(By.xpath(prt.getProperty("studentId"))).getText();
		Assert.assertEquals(prt.getProperty("student_ID"), id);
		String department=driver.findElement(By.xpath(prt.getProperty("studentDepartment"))).getText();
		Assert.assertEquals(departmentSelected, department);
		String parentPhone=driver.findElement(By.xpath(prt.getProperty("studentPrntPhn"))).getText();
		Assert.assertEquals(prt.getProperty("student_PrntPhn"), parentPhone);
		String program=driver.findElement(By.xpath(prt.getProperty("studentProgram"))).getText();
		Assert.assertEquals(programSelected, program);
		String year=driver.findElement(By.xpath(prt.getProperty("studentYear"))).getText();
		Assert.assertEquals("Year "+yearSelected, year);
		String section=driver.findElement(By.xpath(prt.getProperty("studentSection"))).getText();
		Assert.assertEquals(sectionSelected, "Section "+section);
		log.info("student details verification passed successfully");
		
		driver.navigate().back();
		Thread.sleep(8000);
		driver.findElement(By.xpath(prt.getProperty("openProfile"))).click();
		driver.findElement(By.xpath(prt.getProperty("signout"))).click();
	}
	
	/**
	 * check whether student can able to login and confirm profile
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	@Test(priority=4)
	public void studentLoginTest() throws InterruptedException, IOException{
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.xpath(prt.getProperty("username"))).sendKeys(prt.getProperty("student_Email"));
		driver.findElement(By.xpath(prt.getProperty("pswd"))).sendKeys(prt.getProperty("student_Password"));
		driver.findElement(By.xpath(prt.getProperty("login"))).click();
		
		Thread.sleep(8000);
		String title=prt.getProperty("homeTitle");
		if(driver.getTitle().equals(title)){
			log.info("student login passed successfully ");
			Thread.sleep(3000);
			driver.findElement(By.xpath(prt.getProperty("profile"))).click();
			driver.findElement(By.xpath(prt.getProperty("settings"))).click();
			String name=driver.findElement(By.xpath(prt.getProperty("profileName"))).getText();
			if(prt.getProperty("student_Name").equals(name)){
				String email=driver.findElement(By.xpath(prt.getProperty("profileEmail"))).getText();
				Assert.assertEquals(prt.getProperty("student_Email"), email);
				String phone=driver.findElement(By.xpath(prt.getProperty("profilePhone"))).getText();
				Assert.assertEquals(prt.getProperty("student_Phone"),phone);
				log.info("studentProfileTest passed successfully");
			}
			else{
				File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(srcFile, new File("C:\\Users\\Lenovo\\Pictures\\studentProfile.png"));
				log.error("studentProfileTest failed");
			}
		}
		else{
			File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File("C:\\Users\\Lenovo\\Pictures\\studentLogin.png"));
			log.error("student login failed ");
		}
		
	}
	
	
}

	
	
	

