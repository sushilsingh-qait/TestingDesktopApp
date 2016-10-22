package com.test.skytap;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;

import com.test.util.PropFileHandler;

public class BaseFixture {
	 static WebDriver driver;
	 static WebDriver tempdriver;
	 static WebDriverWait wait;
	 static WebDriverWait tempwait;
	 static WebDriverWait longwait;
	 static Robot robot;
	 static String []arg=new String[10];
	 static String customername = "";
	 static String newCustomerurl = "";
	 static String customerURL = "";
	 static String createdadminuserwithsuper = "";
	 static String createdadminusernamewithsuper = "";
	 static String activationURL = "";
	 static String createdenvironment = "";
	 static String adminurl = "";
	 static String uploadedasset = "";
	 static String createdtemplate = "";
	 static String createdadminuser = "";
	 static String creatednonadminuser = "";
	 static String regiontemplateurl = "";
	 static String loggedinuser = "";
	 static String createdenvironmentname = "";
	 static String createdtemplatename = "";
	 static String publishseturl = "";
	 static String portalurl = "";
	 static String saveenvastemplateurl = "";
	 static String vmid = "";
	 static String scheduleid = "";
	 static String exportdownloadurl = "";
	 static String x,y ="";
	 static String projAndSVMEnvironment = "";
	 static int totalImports = 0;
	 int random = (int)(Math.random()*1000);
	
	public  void activationurl() {
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        try {
            Session session = Session.getInstance(props, null);
            Store store = session.getStore();
            store.connect("imap.gmail.com", "automation.skytap@gmail.com", "Foobar@1.0");
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            Message msg = inbox.getMessage(inbox.getMessageCount());            
            Multipart mp = (Multipart) msg.getContent();
            BodyPart bp = mp.getBodyPart(0);            
            String test1 = (String) bp.getContent();
            activationURL =test1.substring(test1.indexOf("https:"),test1.indexOf("https:")+77).trim();
        } catch (Exception mex) {
            mex.printStackTrace();
        }
    }
	
	public static void getFFbrowser(String url) 
	{	
		 ProfilesIni allProfiles = new ProfilesIni();
		 FirefoxProfile profile = allProfiles.getProfile("default");
		 driver=new FirefoxDriver(profile);
		 wait = new WebDriverWait(driver, 120);
		 longwait = new WebDriverWait(driver, 300);
		 
		 driver.manage().window().maximize();
	     driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		 driver.get(url);
	}
		 
	public static void getGCbrowser(String url) 
	{
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver64.exe");
		 driver=new ChromeDriver();
		 wait = new WebDriverWait(driver, 120); 
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		 driver.get(url);		 
	}
	
	public static void getIEbrowser(String url)
	{
		 System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\IEDriverServer.exe");
		 driver=new InternetExplorerDriver();
		 wait = new WebDriverWait(driver, 120); 
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		 driver.get(url);
	}
	
	public static void readbrowser() 
	{
		if(PropFileHandler.readProperty("browser").equals("FF"))
    	{
			BaseFixture.getFFbrowser(PropFileHandler.readProperty("url"));
    	}
		else
		if(PropFileHandler.readProperty("browser").equals("GC"))
    	{
			BaseFixture.getGCbrowser(PropFileHandler.readProperty("url"));
    	}
		else
		if(PropFileHandler.readProperty("browser").equals("IE"))
	    {
			BaseFixture.getIEbrowser(PropFileHandler.readProperty("url"));
	    }
	}
	
	public static void takescreenshot(ITestResult result)
	{
	// Here will compare if test is failing then only it will enter into if condition
	if(ITestResult.FAILURE==result.getStatus())
	{
	try 
	{
	// Create refernce of TakesScreenshot
	TakesScreenshot ts=(TakesScreenshot)driver;
	 
	// Call method to capture screenshot
	File source=ts.getScreenshotAs(OutputType.FILE);
	 
	// Copy files to specific location here it will save all screenshot in our project home directory and
	// result.getName() will return name of test case so that screenshot name will be same
	FileUtils.copyFile(source, new File("./Screenshots/"+result.getName()+".png"));
	 
	System.out.println("Screenshot taken");
	} 
	catch (Exception e)
			{ 
				System.out.println("Exception while taking screenshot "+e.getMessage());
			}
		}
	}
	
	public static void adminLogin() throws InterruptedException {
		driver.findElement(By.id("userLoginName")).sendKeys(PropFileHandler.readProperty("adminUsername"));
		
		driver.findElement(By.id("userLoginPassword")).clear();
		driver.findElement(By.id("userLoginPassword")).sendKeys(PropFileHandler.readProperty("adminPassword"));

		driver.findElement(By.cssSelector(".action-button")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@role='menuitem']/a[@title='Go to your dashboard']")));    	
		Reporter.log("Successfully logged in with Admin User - " +PropFileHandler.readProperty("adminUsername"), true);
	}
	
	public static void userinfo() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@role='menuitem']/a[@title='Go to your dashboard']"))); 
    	driver.get(PropFileHandler.readProperty("url")+"/account");
    	loggedinuser = driver.findElement(By.xpath("//*[text()='Login name:']/../dd[1]")).getText();
    	Reporter.log("Successfully logged in with user - " +loggedinuser, true);
    	driver.get(PropFileHandler.readProperty("url")+"/account");
    	
    	driver.get(PropFileHandler.readProperty("url")+"/users" +"?query=name%3A" +loggedinuser);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'user-name')]"))); 
    	driver.findElement(By.xpath("(//*[text()='"+loggedinuser+"'])[2]/../../a")).click();
    	adminurl = driver.getCurrentUrl();
    	Reporter.log("URL of Admin User is - " +adminurl, true);
	}
	
	public static void removeuserlimits() throws InterruptedException 
	{
		driver.get(adminurl);

		try{
			driver.findElement(By.xpath("//td[text()='VMs (max concurrent)']/../td[@class='actions']/a[@class='delete']")).click();
			Thread.sleep(3000);
		}catch(Exception E){}
		
		try{
			driver.findElement(By.xpath("//td[text()='Storage (GB)']/../td[@class='actions']/a[@class='delete']")).click();	
			Thread.sleep(3000);
		}catch(Exception E){}

		try{
			driver.findElement(By.xpath("//td[text()='SVMs (max concurrent)']/../td[@class='actions']/a[@class='delete']")).click();
			Thread.sleep(3000);
		}catch(Exception E){}	

		try{
			driver.findElement(By.xpath("//td[text()='SVM hours']/../td[@class='actions']/a[@class='delete']")).click();
			Thread.sleep(3000);
		}catch(Exception E){}		
	}	
	
	public static void removeCustomerlimits() throws InterruptedException 
	{	
		tempDriverLogin(PropFileHandler.readProperty("superUsername"),PropFileHandler.readProperty("superPassword"));
		tempdriver.get(customerURL);

		try{
			tempdriver.findElement(By.xpath("//td[text()='VMs (max concurrent)']/../td[@class='actions']/a[@class='delete']")).click();
			Thread.sleep(3000);
		}catch(Exception E){}
		
		try{
			tempdriver.findElement(By.xpath("//td[text()='Storage (GB)']/../td[@class='actions']/a[@class='delete']")).click();	
			Thread.sleep(3000);
		}catch(Exception E){}

		try{
			tempdriver.findElement(By.xpath("//td[text()='SVMs (max concurrent)']/../td[@class='actions']/a[@class='delete']")).click();
			Thread.sleep(3000);
		}catch(Exception E){}	

		try{
			tempdriver.findElement(By.xpath("//td[text()='SVM hours']/../td[@class='actions']/a[@class='delete']")).click();
			Thread.sleep(3000);
		}catch(Exception E){}
		tempdriver.quit();
	}	
	
	public static void tempDriverLogin(String username, String password) throws InterruptedException 
	{
		tempdriver = new FirefoxDriver();
		tempwait = new WebDriverWait(tempdriver, 120);
		tempdriver.get(PropFileHandler.readProperty("url") +"/login");
		tempdriver.findElement(By.id("userLoginName")).sendKeys(username);

		tempdriver.findElement(By.id("userLoginPassword")).clear();
		tempdriver.findElement(By.id("userLoginPassword")).sendKeys(password);

		tempdriver.findElement(By.cssSelector(".action-button")).click();
		Thread.sleep(3000);
    	tempwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@role='menuitem']/a[@title='Go to your dashboard']")));    	
	}
	
	public static void superLogin() throws InterruptedException {
		driver.findElement(By.id("userLoginName")).sendKeys(PropFileHandler.readProperty("superUsername"));

		driver.findElement(By.id("userLoginPassword")).clear();
		driver.findElement(By.id("userLoginPassword")).sendKeys(PropFileHandler.readProperty("superPassword"));

		driver.findElement(By.cssSelector(".action-button")).click();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@role='menuitem']/a[@title='Go to your dashboard']")));    	
		Reporter.log("Successfully logged in with Super User - " +PropFileHandler.readProperty("superUsername"), true);
	}
	
	protected static void executeJavascript(String script) 
	{
		  ((JavascriptExecutor) driver).executeScript(script);
	}
	
	public static void setClipboardData(String string) {
		//StringSelection is a class that can be used for copy and paste operations.
		   StringSelection stringSelection = new StringSelection(string);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		}
	
	public static void uploadFile(String fileLocation) {
        try {
        	//Setting clipboard with file location
            setClipboardData(fileLocation);
            //native key strokes for CTRL, V and ENTER keys
            robot = new Robot();
	
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            Thread.sleep(3000);
            robot.keyPress(KeyEvent.VK_RIGHT);
            robot.keyRelease(KeyEvent.VK_RIGHT);
            robot.delay(3000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception exp) {
        	exp.printStackTrace();
        }
	}
	public static void uploadVM(String ftpurl, String ftpusername, String ftppass) throws AWTException, InterruptedException
	{
		driver.get("chrome://fireftp/content/fireftp.xul");
		driver.findElement(By.cssSelector("#account")).click();
		Robot robot = new Robot();
		Thread.sleep(1000);
		
		StringSelection urlSelection = new StringSelection(ftpurl);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(urlSelection, null);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        Thread.sleep(1000);
		StringSelection nameSelection = new StringSelection(ftpusername);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(nameSelection, null);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        Thread.sleep(1000);
		StringSelection passSelection = new StringSelection(ftppass);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(passSelection, null);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        Thread.sleep(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER); 
	}
	
	public static void fetchTemplate() 
	{
		if(PropFileHandler.readProperty("region").equals("SoftLayer (London)"))
		{	
			regiontemplateurl = PropFileHandler.readProperty("lontemplate");
		}
	else
		if(PropFileHandler.readProperty("region").equals("US-West"))
		{	
			regiontemplateurl = PropFileHandler.readProperty("uswtemplate");
		}	
	else
		if(PropFileHandler.readProperty("region").equals("Wild-West"))
		{	
			regiontemplateurl = PropFileHandler.readProperty("wwtemplate");
		}
	else
		if(PropFileHandler.readProperty("region").equals("integ/tuk5r1"))
		{
			regiontemplateurl = PropFileHandler.readProperty("tuktemplate");
		}
	else
		if(PropFileHandler.readProperty("region").equals("integ/sea5r1"))
		{	
			regiontemplateurl = PropFileHandler.readProperty("seatemplate");
		}
	else
		if(PropFileHandler.readProperty("region").equals("APAC"))
		{	
			regiontemplateurl = PropFileHandler.readProperty("apactemplate");
		}
		
	else
		if(PropFileHandler.readProperty("region").equals("EMEA"))
		{	
			regiontemplateurl = PropFileHandler.readProperty("emeatemplate");
		}	
	}
	
	public void activateUser(String activationURL) 
	{
		Reporter.log(" *** Activating User ***", true);
		driver.get(activationURL);
		driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("qai2012");
		driver.findElement(By.xpath("//input[@id='confirm_password']")).sendKeys("qai2012");
		driver.findElement(By.cssSelector(".action-button")).click();
		try{
		driver.findElement(By.cssSelector("#accept_button")).click();
		}catch(Exception E){}
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@role='menuitem']/a[@title='Go to your dashboard']")));
	}
	
	public void addUserLimit(String resource) throws InterruptedException 
	{
		driver.get(adminurl);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[text()='"+resource+"']/..//a[text()='not set']")));
		driver.findElement(By.xpath("//td[text()='"+resource+"']/..//a[text()='not set']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//td[text()='"+resource+"']/..//input[@type='text']")).clear();
		driver.findElement(By.xpath("//td[text()='"+resource+"']/..//input[@type='text']")).sendKeys("0");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[text()='"+resource+"']/..//input[@type='submit']")));
		driver.findElement(By.xpath("//td[text()='"+resource+"']/..//input[@type='submit']")).click();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='"+resource+"']/..//a[@class='delete']/img"))); 
    	Thread.sleep(5000);		// wait to settings take impact
	}
	
	public void removeUserLimit(String resource) throws InterruptedException 
	{
		driver.get(adminurl);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[text()='"+resource+"']/..//img")));
		driver.findElement(By.xpath("//td[text()='"+resource+"']/..//img")).click();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='"+resource+"']/..//a[text()='not set']")));
    	Thread.sleep(5000);		// wait to settings take impact
	}
	
	public void addCustomerLimit(String resource) throws InterruptedException 
	{
		tempDriverLogin(PropFileHandler.readProperty("superUsername"),PropFileHandler.readProperty("superPassword"));
		tempdriver.get(customerURL);
		tempdriver.findElement(By.xpath("//td[text()='"+resource+"']/../td/form/input[@name='limit']/../a[@class='edit']")).click();
		tempwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='"+resource+"']/../td/form/input[@name='limit']")));
		tempdriver.findElement(By.xpath("//td[text()='"+resource+"']/../td/form/input[@name='limit']")).clear();
		tempdriver.findElement(By.xpath("//td[text()='"+resource+"']/../td/form/input[@name='limit']")).sendKeys("0");
		tempwait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[text()='"+resource+"']/../td/form/input[@name='limit']/../input[@type='submit']")));
		tempdriver.findElement(By.xpath("//td[text()='"+resource+"']/../td/form/input[@name='limit']/../input[@type='submit']")).click();
    	tempwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='"+resource+"']/../td/form/input[@name='limit']/../a[contains(text(),'0')]"))); 	
    	tempdriver.quit();
    	Thread.sleep(5000);		// wait to settings take impact
	}
	
	public void removeCustomerLimit(String resource) throws InterruptedException 
	{
		tempDriverLogin(PropFileHandler.readProperty("superUsername"),PropFileHandler.readProperty("superPassword"));
		tempdriver.get(customerURL);
		tempdriver.findElement(By.xpath("//td[text()='"+resource+"']/../td[@class='actions']/a")).click();
    	tempwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='"+resource+"']/../td/form/a[text()='not set']")));	
    	tempdriver.quit();
    	Thread.sleep(5000);		// wait to settings take impact
	}
	
	public void addVMIDToConnectivity() 
	{
		System.out.println("VM id is "+vmid);
		driver.get(PropFileHandler.readProperty("url") +"/super/settings");
		driver.findElement(By.xpath("//td[contains(text(),'connectivity_vm') and @class='fake_link']")).click();
		driver.findElement(By.id("setting_value")).sendKeys(vmid);
		Select region = new Select(driver.findElement(By.id("setting_region")));
		region.selectByVisibleText(PropFileHandler.readProperty("region"));
		driver.findElement(By.xpath("//input[@value='Add']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Setting updated']")));
	}
	
	public static void getCustomerURL() 
	{
		driver.get(PropFileHandler.readProperty("url") +"/customers");
		driver.findElement(By.cssSelector("#auto_complete_search_term")).sendKeys(PropFileHandler.readProperty("customer"));
		driver.findElement(By.cssSelector("#auto_complete_button")).click();
		customerURL = driver.findElement(By.xpath("//a[text()='"+PropFileHandler.readProperty("customer")+"']")).getAttribute("href");
	}
	
	public  void result(String x,String y)
	{
		if(x.equals(y))
		{
			arg[0]="pass";
			
		}
		else
		{
			arg[0]="fail";
		}
	}
	
	public static void sendEmailableReportViaEmail()
	{

			final String username = "automation.skytap@gmail.com";
		    final String password = "Foobar@1.0";
		    Properties props = new Properties();
		    props.put("mail.smtp.auth", true);
		    props.put("mail.smtp.starttls.enable", true);
		    props.put("mail.smtp.host", "smtp.gmail.com");
		    props.put("mail.smtp.port", "587");
		    Session session = Session.getInstance(props,

		            new javax.mail.Authenticator() {
		                protected PasswordAuthentication getPasswordAuthentication() {
		                    return new PasswordAuthentication(username, password);
		                }
		            });
		    try {
		    	Message message = new MimeMessage(session);
		        message.setFrom(new InternetAddress("automation.skytap@gmail.com"));
		        message.setRecipients(Message.RecipientType.TO,
		                InternetAddress.parse("ssingh@skytap.com"));
		        message.setSubject("Please find the attached emailable report");
		        message.setText("PFA");
		        MimeBodyPart messageBodyPart = new MimeBodyPart();
		        Multipart multipart = new MimeMultipart();
		        messageBodyPart = new MimeBodyPart();
		        String file = "C:\\Users\\Admin\\workspace\\skytap_automation\\test-output\\emailable-report.html";
		        String fileName = "emailable-report.html";
		        DataSource source = new FileDataSource(file);
		        messageBodyPart.setDataHandler(new DataHandler(source));
		        messageBodyPart.setFileName(fileName);
		        multipart.addBodyPart(messageBodyPart);
		        message.setContent(multipart);
		        System.out.println("Sending");
		        Transport.send(message);
		        System.out.println("Done");
		    } catch (MessagingException e) {
		        e.printStackTrace();
		    }
		  }
		
@AfterSuite	
	public void sendEmailableReport()
	{
	sendEmailableReportViaEmail();
	}

	public void quitBrowser() 
	{
		driver.quit();
	}
}