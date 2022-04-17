package org.baseclass1;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Baseclass {
	public static WebDriver driver;
	public static Robot bot;
	public static Actions act;
	public static Select s;
	public static Alert a;

	public static void browserLaunch(String browser) {
		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		default:
			break;
		}
	}

	//to get url
	public static void sourceUrl(String Url) {
		driver.get(Url);
	}
	//browser window maximize or minimize
	public static void windowMaximize() {
		driver.manage().window().maximize();
	}

	//sendkeys to textbox
	public static void toPassKeys(WebElement element, String input) {
		element.sendKeys(input);
	}
	
	//clear textbox
	
	public static void toClearTxtbox(WebElement element) {
		element.clear();
	}
	//to get current browser url
	public static void toPrintCurrentUrl() {
		String printurl = driver.getCurrentUrl();
		System.out.println(printurl);
	}
	//to print current browser title
	public static void toPrintCurrentTitle() {
		String printtitle = driver.getTitle();
		System.out.println(printtitle);
	}
	
	public static void toPrintText(WebElement element) {
		String gettxt = element.getText();
		System.out.println(gettxt);
	}
	
	public static void toPrintValue(WebElement element) {
		String getvalue = element.getAttribute("value");
		System.out.println(getvalue);
	}
	
	//to close current browser
	public static void toCloseBrowser() {
		driver.close();
	}
	//to close all open browser
	public static void toQuitBrowser() {
		driver.quit();
	}
	//to perform most used keyboard actions 
	public static void toPressTabOrEnterKey(String button) throws AWTException {
		bot = new Robot();
		if (button == "tab") {
			bot.keyPress(KeyEvent.VK_TAB);
			bot.keyRelease(KeyEvent.VK_TAB);
		}
		if (button == "enter") {
			bot.keyPress(KeyEvent.VK_ENTER);
			bot.keyRelease(KeyEvent.VK_ENTER);
		}
		if (button == "down") {
			bot.keyPress(KeyEvent.VK_DOWN);
			bot.keyRelease(KeyEvent.VK_DOWN);
		}
		if(button == "esc") {
			bot.keyPress(KeyEvent.VK_ESCAPE);
			bot.keyRelease(KeyEvent.VK_ESCAPE);
		}
	}
	// to perform most used cursor  actions
	public static void actionsClassClick(WebElement element, String mouseact) {
		act = new Actions(driver);

		if(mouseact == "click") { 
			act.click(element).perform();
		}
		if (mouseact == "contextclick") {
			act.contextClick(element).perform();
		}
		if (mouseact == "movetoelement") {
			act.moveToElement(element).perform();
		}
		if (mouseact=="doubleclick") {
			act.doubleClick(element).perform();
		}
		if (mouseact == "keyup"){
			act.keyUp(mouseact).perform();
		}
		if (mouseact == "keydown") {
			act.keyDown(mouseact).perform();		
		}
	}

	public static void toDragAndDrop(WebElement element, WebElement element2) {
		act = new Actions(driver);
		act.dragAndDrop(element, element2).perform();
	}

	// to interact webelement/hidden webelement fast javascriptexecutor is used
	public static void jsExecutorInputText(WebElement element, String inputtext) {
		//downcasting 
		JavascriptExecutor js = (JavascriptExecutor)driver;
		//javascript executor to send keys 
		js.executeAsyncScript("arguments[0].setAttribute('value', inputtext )", element);
	}
	//javascript executor to print value
	public static void jsExecutorToRetrieveText(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("return arguments[0].getAttribute('value')",element);
	}
	//javascript executor to perform click
	public static void jsExecutorToclick(WebElement webElement) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click");
	}
	//to handle multiple windows 
	public static void toHandleMultipleWindows(int i) {
		Set<String> allWindows = driver.getWindowHandles();
		List <String> li = new ArrayList();
		li.addAll(allWindows);
		System.out.println(li);
		driver.switchTo().window(li.get(i));
		
	}
	//to take screenshots
	public static void toTakeScreenShot(String filename) throws IOException {
		TakesScreenshot screenShot = (TakesScreenshot)driver;
		File source = screenShot.getScreenshotAs(OutputType.FILE);
		File  destination = new File("C:\\Users\\Win10\\eclipse-workspace\\BaseClass\\screenshots\\"+filename+".jpg"); 
		FileUtils.copyFile(source, destination);
	}

	//to get excel data and to covert celltype to sendkeys
	public static String excelData(int row, int cell) throws IOException {
		//1. to mention file location
		File f = new File("C:\\Users\\Win10\\eclipse-workspace\\TestProject\\ExcelDatas\\Hotel Project Task.xlsx");
		//2. to read file location we use class FileInputStram class
		FileInputStream file = new FileInputStream(f);
		// work instanciation or obj creation
		Workbook w = new XSSFWorkbook(file); 

		Sheet s = w.getSheet("Sheet1");

		Row r = s.getRow(row);

		Cell c = r.getCell(cell);
		int cellType = c.getCellType();
		String value;
		if (cellType ==1) {
			value = c.getStringCellValue();
		}
		else if (DateUtil.isCellDateFormatted(c)) {
			Date d= c.getDateCellValue();
			SimpleDateFormat datef = new SimpleDateFormat("dd-mm-yyyy");
			value = datef.format(d);
		}
		else {
			double dd = c.getNumericCellValue();
			long l = (long)dd;
			value = String.valueOf(l);
		}
		return value;
	}
	
	public static void selectDropDown(WebElement element, String dropdown) {
		s = new Select(element);
		s.selectByVisibleText(dropdown);
	}
	
	public static void alertClass(String alertMethod) {
		a = driver.switchTo().alert();
		
		if (alertMethod == "accept" ) {
			a.accept();
		}
		else if (alertMethod == "dismiss") {
			a.dismiss();
		}
		else if (alertMethod == "gettext") {
			a.getText();
		}
		else if (alertMethod == "sendkeys") {
			a.sendKeys(alertMethod);
		}
		else {
			System.out.println("Notes: accept, dismiss, gettext, sendkeys ");
		}
	}
	
	public static void implicitWaits() {
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.MILLISECONDS);
	}
	
		
}
