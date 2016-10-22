package com.test.skytap;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class K_Sharing_Portal extends BaseFixture 
{
	public void createSharingPortal() throws InterruptedException 
	{	
		driver.get(createdenvironment +"&section=publish_sets");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-action='createPublishSet']")));
		driver.findElement(By.xpath("//a[@data-action='createPublishSet']")).click();
		driver.findElement(By.cssSelector("#new-publish-set-name")).clear();
		driver.findElement(By.cssSelector("#new-publish-set-name")).sendKeys("QAIT Automation Sharing Portal");
		driver.findElement(By.xpath("//input[@value='Create portal']")).click();
		portalurl = driver.findElement(By.xpath("//a[@class='visit-portal']")).getAttribute("href");
		publishseturl = driver.getCurrentUrl();
		x = driver.findElement(By.xpath("//div[@class='msg block success hideable']")).getText();
		y = "Your sharing portal was successfully created";
	}
		
	public void noPasswordPortalSingleVM() throws InterruptedException 
	{	
		driver.get(portalurl);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='vm-screenshot']")));
	}
	
	public void passwordPortalSingleVM() throws InterruptedException 
	{
		driver.get(publishseturl);
		driver.findElement(By.xpath("//input[@id='password_enabled']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='password']")));
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("abc");
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='The sharing portal has been successfully updated']")));
		driver.get(portalurl);
		driver.findElement(By.cssSelector(".vm-password")).sendKeys("abc");
		driver.findElement(By.cssSelector(".action-button")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='vm-screenshot']")));
		driver.get(publishseturl);
		driver.findElement(By.xpath("//input[@id='require_password']")).click();
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='The sharing portal has been successfully updated']")));
	}
	
	public void AccessGMTSingleVM() throws InterruptedException 
	{
		driver.get(publishseturl);
		Date date = new Date();
		
		driver.findElement(By.xpath("//input[@id='time_box']")).click();
		
		SimpleDateFormat getGMTHour = new SimpleDateFormat("HH");
		getGMTHour.setTimeZone(TimeZone.getTimeZone("GMT"));
		String CurrentGMThour = getGMTHour.format(date);
		System.out.println("Current GMT Start Hour = " +CurrentGMThour);
		
		Select StartGMThour = new Select(driver.findElement(By.cssSelector("#from-access-time-hour")));
		StartGMThour.selectByVisibleText(CurrentGMThour);
        
		int EndGMTHours = Integer.parseInt(CurrentGMThour);
		EndGMTHours = EndGMTHours + 2;
		String EndGMTHours1 = Integer.toString(EndGMTHours);		
		System.out.println("Current GMT End Hour = " +EndGMTHours1);
        Select EndGMThour = new Select(driver.findElement(By.cssSelector("#end-access-time-hour")));
        EndGMThour.selectByVisibleText(EndGMTHours1);  
        
        Select GMTtimezone = new Select(driver.findElement(By.xpath("//select[@id='limit-access-time-zone']")));
        GMTtimezone.selectByVisibleText("(+00:00) UTC"); 
        driver.findElement(By.xpath("//button[text()='Save']")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='The sharing portal has been successfully updated']")));
		
		driver.get(portalurl);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='vm-screenshot']")));
	}
	
	public void AccessISTSingleVM() throws InterruptedException 
	{
		driver.get(publishseturl);
		Date date = new Date();
		
		driver.findElement(By.xpath("//input[@id='time_box']")).click();
		
		SimpleDateFormat getISTHour = new SimpleDateFormat("HH");
		getISTHour.setTimeZone(TimeZone.getTimeZone("IST"));
		String CurrentISThour = getISTHour.format(date);
		System.out.println("Current IST Start Hour = " +CurrentISThour);
		Select StartISThour = new Select(driver.findElement(By.cssSelector("#from-access-time-hour")));
		StartISThour.selectByVisibleText(CurrentISThour);
        
		int EndISTHours = Integer.parseInt(CurrentISThour);
		EndISTHours = EndISTHours + 2;
		String EndISTHours1 = Integer.toString(EndISTHours);		
		System.out.println("Current IST End Hour = " +EndISTHours1);
        Select EndISThour = new Select(driver.findElement(By.cssSelector("#end-access-time-hour")));
        EndISThour.selectByVisibleText(EndISTHours1);  
        
        Select ISTtimezone = new Select(driver.findElement(By.xpath("//select[@id='limit-access-time-zone']")));
        ISTtimezone.selectByVisibleText("(+05:30) New Delhi"); 
        driver.findElement(By.xpath("//button[text()='Save']")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='The sharing portal has been successfully updated']")));
		
		driver.get(portalurl);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='vm-screenshot']")));
    }
	
	public void powerActionsSingleVM() throws InterruptedException 
	{
		driver.get(portalurl);
		driver.findElement(By.cssSelector(".run-vm")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='Running']")));	
		
		driver.findElement(By.xpath("//div[@class='vm-tile-view-header clearfix']/div[@role='menu']/button[@class='action-button power power-options']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Power Off this VM']")));
		driver.findElement(By.xpath("//button[@title='Power Off this VM']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='Powered off']")));
	}
	
	public void noPasswordPortalMultipleVM() throws InterruptedException 
	{
		// Add a VM to environment
		driver.get(createdenvironment);
		driver.findElement(By.xpath("//button[@class='add-vms action-button icon icon-add']")).click();
		driver.findElement(By.xpath("//label[text()='"+createdenvironmentname+"' and (@title='Select this environment')]/../../../../td[1]/input")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Add VM(s)']"))).click();
		driver.findElement(By.xpath("//input[@value='Add VM(s)']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Virtual machine(s) added to your environment')]")));
		
		// Add new VM to PURL
		driver.get(publishseturl);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-access='run_and_use']")));
		driver.findElement(By.xpath("//a[@data-access='run_and_use']")).click();
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='The sharing portal has been successfully updated']")));
		
		// Now access environment
		driver.get(portalurl);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='vm-screenshot']")));
	}
	
	public void passwordPortalMultipleVM() throws InterruptedException 
	{
		driver.get(publishseturl);
		driver.findElement(By.xpath("//input[@id='password_enabled']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='password']")));
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("def");
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='The sharing portal has been successfully updated']")));
		driver.get(portalurl);
		driver.findElement(By.cssSelector(".vm-password")).sendKeys("def");
		driver.findElement(By.cssSelector(".action-button")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='vm-screenshot']")));
		driver.get(publishseturl);
		driver.findElement(By.xpath("//input[@id='require_password']")).click();
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='The sharing portal has been successfully updated']")));
	}
	
	public void AccessGMTMultipleVM() throws InterruptedException 
	{
		driver.get(publishseturl);
		Date date = new Date();
		
		driver.findElement(By.xpath("//input[@id='time_box']")).click();
		
		SimpleDateFormat getGMTHour = new SimpleDateFormat("HH");
		getGMTHour.setTimeZone(TimeZone.getTimeZone("GMT"));
		String CurrentGMThour = getGMTHour.format(date);
		System.out.println("Current GMT Start Hour = " +CurrentGMThour);
		Select StartGMThour = new Select(driver.findElement(By.cssSelector("#from-access-time-hour")));
		StartGMThour.selectByVisibleText(CurrentGMThour);
        
		int EndGMTHours = Integer.parseInt(CurrentGMThour);
		EndGMTHours = EndGMTHours + 2;
		String EndGMTHours1 = Integer.toString(EndGMTHours);		
		System.out.println("Current GMT End Hour = " +EndGMTHours1);
        Select EndGMThour = new Select(driver.findElement(By.cssSelector("#end-access-time-hour")));
        EndGMThour.selectByVisibleText(EndGMTHours1);  
        
        Select GMTtimezone = new Select(driver.findElement(By.xpath("//select[@id='limit-access-time-zone']")));
        GMTtimezone.selectByVisibleText("(+00:00) UTC"); 
        driver.findElement(By.xpath("//button[text()='Save']")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='The sharing portal has been successfully updated']")));
		
		driver.get(portalurl);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='vm-screenshot']")));
	
	}
	
	public void AccessISTMultipleVM() throws InterruptedException 
	{
		driver.get(publishseturl);
		Date date = new Date();
		
		driver.findElement(By.xpath("//input[@id='time_box']")).click();
		
		SimpleDateFormat getISTHour = new SimpleDateFormat("HH");
		getISTHour.setTimeZone(TimeZone.getTimeZone("IST"));
		String CurrentISThour = getISTHour.format(date);
		System.out.println("Current IST Start Hour = " +CurrentISThour);
		Select StartISThour = new Select(driver.findElement(By.cssSelector("#from-access-time-hour")));
		StartISThour.selectByVisibleText(CurrentISThour);
        
		int EndISTHours = Integer.parseInt(CurrentISThour);
		EndISTHours = EndISTHours + 2;
		String EndISTHours1 = Integer.toString(EndISTHours);		
		System.out.println("Current IST End Hour = " +EndISTHours1);
        Select EndISThour = new Select(driver.findElement(By.cssSelector("#end-access-time-hour")));
        EndISThour.selectByVisibleText(EndISTHours1);  
        
        Select ISTtimezone = new Select(driver.findElement(By.xpath("//select[@id='limit-access-time-zone']")));
        ISTtimezone.selectByVisibleText("(+05:30) New Delhi"); 
        driver.findElement(By.xpath("//button[text()='Save']")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='The sharing portal has been successfully updated']")));
		
		driver.get(portalurl);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='vm-screenshot']")));
	}
	
	public void powerActionsMultipleVM() throws InterruptedException 
	{
		driver.get(portalurl);
		driver.findElement(By.cssSelector(".run-configuration")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='Running']")));	
		
		driver.findElement(By.xpath("//div[@class='vm-tile-view-header clearfix']/div[@role='menu']/button[@class='action-button power power-options']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Power Off this VM']")));
		driver.findElement(By.xpath("//button[@title='Power Off this VM']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='Powered off']")));
	}
}