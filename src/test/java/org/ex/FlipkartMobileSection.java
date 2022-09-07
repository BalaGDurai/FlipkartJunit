package org.ex;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;

public class FlipkartMobileSection {
	public static WebDriver driver;
	@BeforeClass
	public static void beforeClass() {
		System.out.println("BeforeClass");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\durai\\eclipse-workspace\\ExcelReadWrite\\dri\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");
	}
	
	@Before
	public void before()
	{
		System.out.println("Start Time");
		System.out.println(java.time.LocalTime.now());
	} 
	
	@Test
	public void method1() {
		System.out.println("Flipkart Login");
		WebElement close = driver.findElement(By.xpath("//button[text()='✕']"));
		close.click();
		System.out.println("================================");
	}
	
	@Test
	public void method2() throws IOException {
		System.out.println("Mobile Search");
		WebElement src = driver.findElement(By.xpath("//input[@class='_3704LK']"));
		src.sendKeys("Apple");
		WebElement ent = driver.findElement(By.xpath("//button[@type='submit']"));
		driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);	
		ent.click();
		driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		List<String> al = new ArrayList<String>();
		List<WebElement> products = driver.findElements(By.xpath("//div[@class='_4rR01T']"));
		for (WebElement x : products){
		String text = x.getText();
		al.add(text);
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		System.out.println(text);
		 }
		File ip = new File("C:\\Users\\durai\\eclipse-workspace\\ExcelReadWrite\\src\\test\\resources\\New XLSX Worksheet.xlsx"); 
		Workbook wb = new XSSFWorkbook();
		Sheet sh = wb.createSheet("Mobiles"); 
		for(int i =0;i<al.size(); i++)
		   {
		Row cr = sh.createRow(i);
			Cell cc = cr.createCell(0);
			cc.setCellValue(al.get(i));
		   }
			FileOutputStream v = new FileOutputStream(ip);
			wb.write(v);

		System.out.println("================================");
	}
	
	@Test
	public void method3() {
		System.out.println("Windows Handling");
		WebElement nxtSrc = driver.findElement(By.xpath("//div[text()='APPLE iPhone 11 (Black, 64 GB)']"));
		nxtSrc.click();
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		String par = driver.getWindowHandle();
		Set<String> chi = driver.getWindowHandles();
		for (String string : chi) {
			if (!par.equals(string)) {
				driver.switchTo().window(string);
			}
		}
		System.out.println("================================");
	}
	
	@Test
	public void method4() throws IOException {
		System.out.println("Read Excel");
		WebElement txt = driver.findElement(By.xpath("//span[text()='APPLE iPhone 11 (Black, 64 GB)']"));
		String text1 = txt.getText();
		System.out.println(text1);
		File ip = new File("C:\\Users\\durai\\eclipse-workspace\\ExcelReadWrite\\src\\test\\resources\\New XLSX Worksheet.xlsx"); 
		FileInputStream f = new FileInputStream(ip);
		Workbook wb = new XSSFWorkbook(f);
		Sheet sheet = wb.getSheet("Mobiles");
		Row row = sheet.getRow(1);
		Cell cell = row.getCell(0);
		String cellValue = cell.getStringCellValue();
		
		Assert.assertEquals(text1, cellValue);
		System.out.println("ASSERT PASSED -Values Equal ");
		System.out.println("================================");
	}
	
	@Test
	public void method5() throws IOException {
		System.out.println("Screenshot");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	 	JavascriptExecutor js =(JavascriptExecutor)driver;
	 	WebElement spec = driver.findElement(By.xpath("//div[text()='Specifications']"));
	 	js.executeScript("arguments[0].scrollIntoView(true)", spec);
	 	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		TakesScreenshot ts =(TakesScreenshot)driver;
		File src =ts.getScreenshotAs(OutputType.FILE);
		File trg= new File("C:\\Users\\durai\\eclipse-workspace\\ExcelReadWrite\\target\\Screenshot.png");
		FileUtils.copyFile(src, trg);
		System.out.println("================================");
	}
	
	@After
	public void after()
	{
	 	System.out.println("End Time");
	 	System.out.println(java.time.LocalTime.now());
	}
	
	@AfterClass
	public static void afterClass() {
		System.out.println("AfterClass");
		driver.quit();
		
	}
}
