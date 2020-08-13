/*
 * Add course test in Admin portal
 * Enter all the details and add faculty, student
 * Goto the departments and check whether Course has been created or not
 * check whether the details and selected faculty and student are in the list
 * Check whether the Course page has been created or not
 */

package testing;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AdminAddCourse {
	WebDriver driver;
	FileInputStream fis;
	String departmentSelected;
	String programSelected;
	String yearSelected;
	String sectionSelected;
	Properties prt=new Properties();
	private static Logger log=Logger.getLogger(testing.AdminAddCourse.class);
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
	@Test(priority=0)
	public void login() throws InterruptedException{
		driver.findElement(By.xpath(prt.getProperty("signin"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(prt.getProperty("username"))).sendKeys(prt.getProperty("admin_userid"));
		driver.findElement(By.xpath(prt.getProperty("pswd"))).sendKeys(prt.getProperty("admin_pswd"));
		driver.findElement(By.xpath(prt.getProperty("login"))).click();
		log.info("Admin login successfully ");
	}
	/**
	 * add course and add faculty and student to the course
	 * @throws InterruptedException 
	 */
	@Test(priority=1)
	public void addCourse() throws InterruptedException{
		Thread.sleep(8000);
		driver.findElement(By.xpath(prt.getProperty("addcourse"))).click();
		driver.findElement(By.xpath(prt.getProperty("courseName"))).sendKeys(prt.getProperty("course_name"));
		driver.findElement(By.xpath(prt.getProperty("courseDescription"))).sendKeys(prt.getProperty("course_description"));
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
		driver.findElement(By.xpath(prt.getProperty("courseAcademy"))).sendKeys(prt.getProperty("course_academy"));
		driver.findElement(By.xpath(prt.getProperty("courseFaculty"))).click();
		driver.findElement(By.xpath(prt.getProperty("search"))).sendKeys(prt.getProperty("teacher_name"));
		Thread.sleep(5000);
		driver.findElement(By.xpath(prt.getProperty("selectFaculty"))).click();
		String facultyName=driver.findElement(By.xpath(prt.getProperty("selectedFaculty"))).getText();
		facultyName=facultyName.trim();
		if(facultyName.equals(prt.getProperty("teacher_name"))){
			log.info("faculty selected: "+ prt.getProperty("teacher_name"));
		}
		driver.findElement(By.xpath(prt.getProperty("courseStudent"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(prt.getProperty("search"))).sendKeys(prt.getProperty("student_Name"));
		Thread.sleep(5000);
		driver.findElement(By.xpath(prt.getProperty("selectStudent"))).click();
		String studentName=driver.findElement(By.xpath(prt.getProperty("selectedStudent"))).getText();
		studentName=studentName.trim();
		if(studentName.equals(prt.getProperty("student_Name"))){
			log.info("student selected: "+ prt.getProperty("student_Name"));
		}
		Thread.sleep(3000);
//		driver.findElement(By.xpath(prt.getProperty("save"))).click();
		driver.findElement(By.xpath(prt.getProperty("cancel"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(prt.getProperty("yesDiscard"))).click();
		log.info("addCourse passed successfully ");
	}
	/**
	 * check whether course, selected faculty and course are added 
	 * @throws InterruptedException  
	 */
	@Test(priority=2)
	public void courseTest() throws InterruptedException{
		Thread.sleep(2000);
		driver.findElement(By.xpath(prt.getProperty("departments"))).click();
		driver.findElement(By.xpath(prt.getProperty("demo1"))).click();
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prt.getProperty("programs"))));
		driver.findElement(By.xpath(prt.getProperty("programs"))).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prt.getProperty("years"))));
		driver.findElement(By.xpath(prt.getProperty("years"))).click();
		Thread.sleep(5000);
		String value=driver.findElement(By.xpath(prt.getProperty("courseCount"))).getText();
		value=value.substring(value.indexOf("(")+1,value.indexOf(")"));
		System.out.println("No.of Courses: "+ value);
		int num=Integer.parseInt(value);
		for(int i=1; i<=num ; i++){
			Thread.sleep(1000);
			WebElement courses=driver.findElement(By.xpath(prt.getProperty("listCount1")+i+prt.getProperty("listCount2")));
			String names=courses.getText();
			if(names.equals((prt.getProperty("course_name")))){
				courses.click();
				break;
			}
		}
		Thread.sleep(2000);
		String courseName=driver.findElement(By.xpath(prt.getProperty("ccourseName"))).getText();
		Assert.assertEquals(prt.getProperty("course_name"), courseName);
		String description=driver.findElement(By.xpath(prt.getProperty("cdescription"))).getText();
		Assert.assertEquals(prt.getProperty("course_description"), description);
		String department=driver.findElement(By.xpath(prt.getProperty("cdepartment"))).getText();
		Assert.assertEquals(departmentSelected, department);
		String program=driver.findElement(By.xpath(prt.getProperty("cprogram"))).getText();
		Assert.assertEquals(programSelected, program);
		String year=driver.findElement(By.xpath(prt.getProperty("cyear"))).getText();
		Assert.assertEquals("Year "+yearSelected, year);
		String section=driver.findElement(By.xpath(prt.getProperty("csection"))).getText();
		Assert.assertEquals(sectionSelected, "Section "+section);
		String academy=driver.findElement(By.xpath(prt.getProperty("cacademy"))).getText();
		Assert.assertEquals(prt.getProperty("course_academy"), academy);
		String cfaculty=driver.findElement(By.xpath(prt.getProperty("cfaculty"))).getText();
		cfaculty=cfaculty.trim();
		Assert.assertEquals(prt.getProperty("teacher_name"), cfaculty);
		log.info("Faculty '"+cfaculty+"' has been added to the '"+prt.getProperty("course_name")+"' course successfully ");
		
		String value1=driver.findElement(By.xpath(prt.getProperty("cstudentCount"))).getText();
		value1=value1.substring(1, value1.length()-1);
		int num1=Integer.parseInt(value1);
		for(int i=1; i<=num1; i++){
			WebElement students=driver.findElement(By.xpath(prt.getProperty("cstudentNames1")+i+prt.getProperty("cstudentNames2")));
			String names=students.getText();
			if(names.equals(prt.getProperty("student_Name"))){
				log.info("Student '"+names+"' has been added to the '"+prt.getProperty("course_name")+"' course successfully ");
				break;
			}
		}
		log.info(prt.getProperty("course_name")+" course has been verified successfully ");
		Thread.sleep(2000);
		driver.findElement(By.xpath(prt.getProperty("viewPage"))).click();
		Thread.sleep(2000);
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		it.next();
		String newwin = it.next();
		driver.switchTo().window(newwin);
		Thread.sleep(2000);
		
		String title=driver.getTitle();
		String[] name=title.split("-");
		String coursePageName=name[0].trim();
		Assert.assertEquals(prt.getProperty("course_name"), coursePageName);
		log.info(prt.getProperty("course_name")+ "course page is created successfully with the title " + title );
		
	}
}
