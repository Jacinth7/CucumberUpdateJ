package com.utilityFunctions;

import java.awt.AWTException;
import java.awt.Robot;
//import org.apache.commons.codec.binary.Base64;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import com.google.common.base.Function;
import com.google.common.io.Files;

import io.cucumber.java.Scenario;



public class FunctionalLiberary {
	public static WebDriver driver;
	static Properties prop;
	
	private static SecretKeySpec secretKey;
	private static byte[] key;
	
	static StringBuilder htmlStringBuilder = new StringBuilder();

	public static void openbrowser() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream("src/main/resources/TestData/Test.properties");
		prop.load(fis);
		String browser1 = prop.getProperty("browser");

		if (prop.getProperty("browser").equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
//			DesiredCapabilities caps = new DesiredCapabilities();
//			caps.setAcceptInsecureCerts(true);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
//			options.merge(caps);
			driver = new ChromeDriver(options);
		} else if (prop.getProperty("browser").equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "");
			driver = new FirefoxDriver();
		} else if (prop.getProperty("browser").equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "");
			driver = new ChromeDriver();

		} else if (prop.getProperty("browser").equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", "");
			driver = new InternetExplorerDriver();

		} else if (prop.getProperty("browser").equals("edge")) {
			System.setProperty("webdriver.edge.driver", "");
			driver = new EdgeDriver();
		} else {
			System.out.println(browser1 + " is not a supported browser");

		}
	}

	public static void geturl() {
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
	}
	
	
	public static void type(WebElement element, String enter) {
		element.sendKeys(enter);
	}

	public static void type1(WebElement element, Keys enter) {
		element.sendKeys(enter);
	}
	
	public static void click(WebElement element) {
		element.click();
	}

	public static void implicitWait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public static void clickjs(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	public static void mousehover(WebElement ele) {
		Actions act = new Actions(driver);
		act.moveToElement(ele).build().perform();
	}

	public static void clickact(WebElement ele) {
		Actions act = new Actions(driver);
		act.moveToElement(ele).click().build().perform();
	}

	public static void clickDouble(WebElement ele) {
		Actions a = new Actions(driver);
		a.moveToElement(ele).doubleClick().build().perform();
	}

	public static void waitForElementClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static void waitForElementVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static Map<String, Map<String, String>> setMapData(String name) throws IOException {
		String path = "src\\main\\resources\\TestData\\COA TestData.xlsx";
		FileInputStream fis = new FileInputStream(path);
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet(name);
		int lastRow = sheet.getLastRowNum();
		Map<String, Map<String, String>> excelFileMap = new HashMap<String, Map<String, String>>();
		Map<String, String> dataMap = new HashMap<String, String>();
		for (int i = 0; i <= lastRow; i++) {
			Row row = sheet.getRow(i);
			Cell valueCell = row.getCell(1);
			Cell keyCell = row.getCell(0);
			String value = valueCell.toString();
			String key = keyCell.toString().trim();
			dataMap.put(key, value);
			excelFileMap.put("DataSheet", dataMap);
		}
		return excelFileMap;
	}

	public static String getValue(String shname, String key) throws IOException {
		Map<String, String> m = setMapData(shname).get("DataSheet");
		String value = m.get(key);
		return value.replace(".0", "");
	}

	public static void highLightElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid green;');",
				element);

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {

			System.out.println(e.getMessage());
		}

		js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", element);

	}

	public static void typea(WebElement e, String value) {
		Actions act = new Actions(driver);
		act.moveToElement(e).click().sendKeys(value).build().perform();
	}

	public static void typea1(WebElement e, Keys tab) {
		Actions act = new Actions(driver);
		act.moveToElement(e).click().sendKeys(tab).build().perform();
	}

//	public void assertEquals(String exp, String act) {
//		Assert.assertEquals(exp, act);
//	}

	public void selectByValue(WebElement element, String value) {
		Select sel = new Select(element);
		sel.selectByValue(value);
	}

	public static void selectByVisibleText(WebElement element, String value) {
		Select sel = new Select(element);
		sel.selectByVisibleText(value);
	}

	public static void selectByIndex(WebElement element, int value) {
		Select sel = new Select(element);
		sel.selectByIndex(value);
	}

	public static void takeScreenshot(String filename) throws IOException {
		File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(f, new File("src/test/resources/Screenshot/" + filename + ".jpg"));
	}
	


	public static String getdata(String sheetname, int row, int cell) throws IOException {
		File f = new File("src/main/resources/TestData/TestData.xls");
		FileInputStream fis = new FileInputStream(f);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(sheetname);
		XSSFRow r = sheet.getRow(0);
		return sheet.getRow(row).getCell(cell).getStringCellValue();
	}
	
	public static void writeData(String sheetname, int row, int cell) throws IOException {
		File f = new File("src/main/resources/TestData/TestData.xls");
		FileInputStream fis = new FileInputStream(f);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(sheetname);
		sheet.getRow(row).createCell(cell).setCellValue("Jah10");
		FileOutputStream fout=new FileOutputStream(f);
		wb.write(fout);
	}

//	public void tearDown(Scenario scenario) throws IOException {
//
//		if (scenario.isFailed()) {
//			try {
//				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//				File screenshot_with_scenario_name = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//				FileUtils.copyFile(screenshot_with_scenario_name,
//						new File("./target/test-report/" + scenario.getName() + ".png"));
//				System.out.println(scenario.getName());
//				scenario.embed(screenshot, "image/png");
//			} catch (WebDriverException e) {
//				System.err.println(e.getMessage());
//			}
//		}
//	}

//	public static void waitForElement(WebDriver driver, final By locator) {
//		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(45))
//				.pollingEvery(2, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
//
//		wait.until(new Function<WebDriver, WebElement>() {
//			public WebElement apply(WebDriver driver) {
//				return driver.findElement(locator);
//			}
//		});
//	}

	public void scrollPage(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void consoleInput() {
		Scanner s=new Scanner(System.in);
		System.out.println("Enter input");
		s.nextLine();
	}

	public static void writeData(String sheetname, int row, int cell, String data) throws IOException {
		File f = new File("src\\main\\resources\\TestData\\COA TestData.xlsx");
		FileInputStream fis = new FileInputStream(f);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(sheetname);

		// write
		sheet.getRow(row).createCell(cell).setCellValue(data);
		FileOutputStream fout = new FileOutputStream(f);
		wb.write(fout);
	}

	public static void writeNote(String text) throws IOException {
		FileWriter fr = new FileWriter("C:\\Users\\jsam\\Desktop\\Test.html");
		BufferedWriter br = new BufferedWriter(fr);
		br.write(text);
		br.close();
	}

	public static String getxmlTagValue(String xmlName, String parentTagName, int parentTagIndex, String childTagName)
			throws IOException, ParserConfigurationException, SAXException {
		String filePath = "src\\main\\resources\\TestData\\" + xmlName;
		File file = new File(filePath);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbf.newDocumentBuilder();
		Document doc = dBuilder.parse(file);
		doc.getDocumentElement().normalize();
		NodeList nodeList = doc.getElementsByTagName(parentTagName);
		Node node = nodeList.item(parentTagIndex);
		Element element = (Element) node;
		String childTagName1 = element.getElementsByTagName(childTagName).item(0).getTextContent();
		return childTagName1;
	}

	public static void fileUpload(String value) throws AWTException {
		Robot robot = new Robot();

		robot.setAutoDelay(1000);

		StringSelection selection = new StringSelection(value);

		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

		robot.setAutoDelay(1000);

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);

		robot.setAutoDelay(1000);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}


	public static void closeBrowser() {
		driver.close();
		driver.quit();
	}

	public static String getdata1(String sheetname, int row, int cell) throws IOException  {
		File f = new File("src/main/resources/TestData/TestData1.xls");
		FileInputStream fis = new FileInputStream(f);
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		HSSFSheet sheet = wb.getSheet(sheetname);
		HSSFRow r = sheet.getRow(0);
		return sheet.getRow(row).getCell(cell).getStringCellValue();
	}
	
	public static String getdata2(String filepath, String sheetname, int row, int cell) throws IOException {
		File f = new File(filepath);
		FileInputStream fis = new FileInputStream(f);
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		HSSFSheet sheet = wb.getSheet(sheetname);
		HSSFRow r = sheet.getRow(0);
		return sheet.getRow(row).getCell(cell).getStringCellValue();
	}

	public static void header() {

		// append html header and title
		htmlStringBuilder.append("<html><head><title>Oracle-EBXFields Validation Report</title></head>");
		// append body
		htmlStringBuilder.append("<body>");
		// append table
		// htmlStringBuilder.append("<table border=\"1\" bordercolor=\"#000000\">");
		htmlStringBuilder.append("<table BORDER=2>");

		// append row
		htmlStringBuilder.append(
				"<tr><td><b>FieldName</b></td><td><b>Actual</b></td><td><b>Expected</b></td><td><b>Result</b></td></tr>");

	}

	public static void Forms1(String formName) {
		htmlStringBuilder
				.append("<tr><td style=\"background-color:00FEFE;color:black;\"><b>" + formName + "</b></td></tr>");

	}

	public static void Forms(String formName) {

		switch (formName) {
		case "Testing Form":
			htmlStringBuilder
					.append("<tr><td style=\"background-color:yellow;color:black;\"><b>Hello Testing</b></td></tr>");
			break;
		case "Remit To Address request Form":
			htmlStringBuilder.append(
					"<tr><td style=\"background-color:yellow;color:black;\"><b>Remit To Address request Form</b></td></tr>");
			break;
		case "Bank Account request Form":
			htmlStringBuilder.append(
					"<tr><td style=\"background-color:yellow;color:black;\"><b>Bank Account request Form</b></td></tr>");
			break;
		case "Contact request Form":
			htmlStringBuilder.append(
					"<tr><td style=\"background-color:yellow;color:black;\"><b>Contact request Form</b></td></tr>");
			break;
		}

	}


	public static void htmlReport(String FieldName, String Actual, String Expected, String Result) throws IOException {

		if (Result == "Pass") {
			htmlStringBuilder.append("<tr><td>" + FieldName + "</td><td>" + Actual + "</td><td>" + Expected
					+ "</td><td style=\"background-color:green;color:white;\"><b>" + Result + "</b></td></tr>");
		} else {
			htmlStringBuilder.append("<tr><td>" + FieldName + "</td><td>" + Actual + "</td><td>" + Expected
					+ "</td><td style=\"background-color:red;color:white;\"><b>" + Result + "</b></td></tr>");
		}
	}

	public static void validation(String actual, String expected, String fieldname) throws IOException {
		String result1;
		if (expected. equalsIgnoreCase(actual)) {
			result1 = "Pass";
		} else {
			result1 = "Fail";
			takeScreenshot(fieldname);
		}
		htmlReport(fieldname, actual, expected, result1);
	}

	public static void closehtml() throws IOException, ParserConfigurationException, SAXException {
		// close html file
		htmlStringBuilder.append("</table></body></html>");
		// write html string content to a file
//	    String xmlFilename = "File.XML";
//	    String SupplierID = getxmlTagValue(xmlFilename, "G_VENDOR_DATA", 0, "VENDORCODE");
		WriteToFile(htmlStringBuilder.toString(), "EBXFields-Validation-Report.html");
	}

	public static void WriteToFile(String fileContent, String fileName) throws IOException {

		String projectPath = System.getProperty("user.dir");
		String tempFile = projectPath + File.separator + fileName;
		File file = new File(tempFile);
		// if file does exists, then delete and create a new file
		if (file.exists()) {
			try {
				File newFileName = new File(projectPath + File.separator + "backup_" + fileName);
				file.renameTo(newFileName);
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// write to file with OutputStreamWriter
		OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());
		Writer writer = new OutputStreamWriter(outputStream);
		writer.write(fileContent);
		writer.close();

	}
	
//	public static void failedhtmlScreenshot(Scenario scenario) {
//		if (scenario.isFailed()) {
//			String screenshotName = scenario.getName().replaceAll(" ", "_");
//			try {
//				File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//				File destinationPath = new File(System.getProperty("user.dir") + "/src/test/resources/Screenshot/"
//						+ screenshotName + ".png");
//				Files.copy(sourcePath, destinationPath);
//				Reporter.addScreenCaptureFromPath(destinationPath.toString());
//
//			} catch (IOException e) {
//			}
//		}
//	}
//
//	public static String decodeString(String password)
//	{
//		byte[] decodedString=Base64.decodeBase64(password);
//		return(new String(decodedString));
//	}

	public static void limitChar(String str) {
		str.substring(0, 10);
	}
	
	public static String readExcelCellData( String sheetName, String colName, int rowNum)
			throws IOException {
			rowNum =rowNum-1;
			 FileInputStream fis = null;
			 XSSFWorkbook workbook = null;
			 XSSFSheet sheet = null;
			 XSSFRow row = null;
			 XSSFCell cell = null;
			String cellvalue = "";
			fis = new FileInputStream("D:\\Workspace\\VendorEBX\\src\\main\\resources\\TestData\\AHM.xlsx");
			workbook = new XSSFWorkbook(fis);
			try {
			int col_Num = -1;
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
			if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
			col_Num = i;
			}
			row = sheet.getRow(rowNum);
			cell = row.getCell(col_Num);

			cellvalue = String.valueOf(cell.getStringCellValue());
//			if (cell == null)
//			return "";


//
//			if (cell != null) {
//			switch (cell.getCellType()) {
//			case BOOLEAN:
//			cellvalue = String.valueOf(cell.getBooleanCellValue());
//			break;
//			case NUMERIC:
//			if (DateUtil.isCellDateFormatted(cell)) {
//			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//			} else {
//			cellvalue = BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString();
//			}
//			break;
//			case STRING:
//			cellvalue = String.valueOf(cell.getStringCellValue());
//			break;
//			case FORMULA:
//			cellvalue = String.valueOf(cell.getCellFormula());
//			break;
//			case BLANK:
//			cellvalue = "";
//			break;
//			case ERROR:
//			break;
//			case _NONE:
//			break;
//			default:
//			break;
//			}
//			} else 
//			{ //
//			//logger.error("Cell is null");
//			System.out.println("Cell is null");
//			}



			fis.close();
			return cellvalue.trim();



			} catch (Exception e) {
			e.printStackTrace();
			return "row " + rowNum + " or column " + colName + " does not exist in Excel";
			}
			
			
		}
	
	public static String prop(String text) throws IOException {
		
//		Properties prop= new Properties();
//		FileInputStream fis = new FileInputStream("src/main/resources/TestData/Test.properties");
//		prop.load(fis);
		
		Properties prop1 = new Properties();
		FileInputStream fis1 = new FileInputStream("src/main/resources/TestData/Test.properties");
		prop1.load(fis1);
		prop1.getProperty(text);
		return prop1.getProperty(text);
		
	}
	
	public static String decrypt(String strToDecrypt, String secret) {
		try {
			setKey(secret);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(java.util.Base64.getDecoder().decode(strToDecrypt)));
		} catch (Exception e) {
			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}

	public static void setKey(String myKey) {
		MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static String CurrentFrameName() {

		
		 JavascriptExecutor jsExecutor = (JavascriptExecutor)driver; String
		 currentFrame = (String) jsExecutor.executeScript("return self.name");
		 System.out.println(currentFrame);
		 return currentFrame;

	}

	
}
