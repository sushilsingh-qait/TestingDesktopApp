package com.test.skytap;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import com.test.util.PropFileHandler;

public class I_Usage extends BaseFixture
{
	public void svmUsage() throws InterruptedException 
	{	
		Thread.sleep(30000); 
		driver.get(projAndSVMEnvironment);
		driver.findElement(By.cssSelector(".run-vm")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@title='Running']")));
				
		driver.get(PropFileHandler.readProperty("url") +"/reports");
		Select svm = new Select(driver.findElement(By.cssSelector("#select_resource")));
        svm.selectByVisibleText("SVMs");
        
        Select duration = new Select(driver.findElement(By.cssSelector("#select_time_period")));
        duration.selectByVisibleText("Right now");
        
        driver.findElement(By.xpath("//input[@value='Generate report']")).click();
        Thread.sleep(2000);
		longwait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Generate report']")));
		Reporter.log("Usage Report Successfully Generated" , true);
		Thread.sleep(2000);
		x=driver.findElement(By.xpath("//tr[@class='total ungrouped ']/td[@class='grouping']")).getText();
		y= "All users";
        Assert.assertEquals(x,y);
		result(x, y);
	}
	
	public void storageUsage() throws InterruptedException 
	{
		driver.get(PropFileHandler.readProperty("url") +"/reports");
		
		Select storage = new Select(driver.findElement(By.cssSelector("#select_resource")));
        storage.selectByVisibleText("Storage");
        
        Select duration = new Select(driver.findElement(By.cssSelector("#select_time_period")));
        duration.selectByVisibleText("Right now");
        
        driver.findElement(By.xpath("//input[@value='Generate report']")).click();
        Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Generate report']")));
		Reporter.log("Usage Report Successfully Generated" , true);
		Assert.assertEquals(driver.findElement(By.xpath("//tr[@class='total ungrouped ']/td[@class='grouping']")).getText(), "All users");
	}
	
	public void svmNoGroupingCurrentMonth() throws InterruptedException 
	{
		driver.get(PropFileHandler.readProperty("url") +"/reports");
		
		Select svm = new Select(driver.findElement(By.cssSelector("#select_resource")));
        svm.selectByVisibleText("SVMs");
        
        Select grouping = new Select(driver.findElement(By.cssSelector("#select_group_by")));
        grouping.selectByVisibleText("No grouping (line items)");
        
        Select duration = new Select(driver.findElement(By.cssSelector("#select_time_period")));
        duration.selectByVisibleText("This month");
		
        driver.findElement(By.xpath("//input[@value='Generate report']")).click();
        Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Generate report']")));
		Reporter.log("Usage Report Successfully Generated" , true);
		Assert.assertEquals(driver.findElement(By.xpath("//table[@class='line-items']/thead/tr/th[1]")).getText(), "Start time");  
	}
	
	public void storageNoGroupingCurrentMonth() throws InterruptedException 
	{
		driver.get(PropFileHandler.readProperty("url") +"/reports");
		
		Select storage = new Select(driver.findElement(By.cssSelector("#select_resource")));
        storage.selectByVisibleText("Storage");
		
		Select grouping = new Select(driver.findElement(By.cssSelector("#select_group_by")));
        grouping.selectByVisibleText("No grouping (line items)");
        
        Select duration = new Select(driver.findElement(By.cssSelector("#select_time_period")));
        duration.selectByVisibleText("This month");
		
        driver.findElement(By.xpath("//input[@value='Generate report']")).click();
        Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Generate report']")));
		Reporter.log("Usage Report Successfully Generated" , true);
		Assert.assertEquals(driver.findElement(By.xpath("//table[@class='line-items']/thead/tr/th[1]")).getText(), "Start time");
	}
	
	public void svmNoGroupingLastMonth() throws InterruptedException 
	{
		driver.get(PropFileHandler.readProperty("url") +"/reports");
		
		Select svm = new Select(driver.findElement(By.cssSelector("#select_resource")));
        svm.selectByVisibleText("SVMs");
        
        Select grouping = new Select(driver.findElement(By.cssSelector("#select_group_by")));
        grouping.selectByVisibleText("No grouping (line items)");
        
        Select duration = new Select(driver.findElement(By.cssSelector("#select_time_period")));
        duration.selectByVisibleText("Last month");
		
        driver.findElement(By.xpath("//input[@value='Generate report']")).click();
        Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Generate report']")));
		Reporter.log("Usage Report Successfully Generated" , true);
		Assert.assertEquals(driver.findElement(By.xpath("//table[@class='line-items']/thead/tr/th[1]")).getText(), "Start time");
	}
	
	public void storageNoGroupingLastMonth() throws InterruptedException 
	{
		driver.get(PropFileHandler.readProperty("url") +"/reports");
		
		Select storage = new Select(driver.findElement(By.cssSelector("#select_resource")));
		storage.selectByVisibleText("Storage");
        
        Select grouping = new Select(driver.findElement(By.cssSelector("#select_group_by")));
        grouping.selectByVisibleText("No grouping (line items)");
        
        Select duration = new Select(driver.findElement(By.cssSelector("#select_time_period")));
        duration.selectByVisibleText("Last month");
		
        driver.findElement(By.xpath("//input[@value='Generate report']")).click();
        Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Generate report']")));
		Reporter.log("Usage Report Successfully Generated" , true);
		Assert.assertEquals(driver.findElement(By.xpath("//table[@class='line-items']/thead/tr/th[1]")).getText(), "Start time");
	}
	
	public void svmUsageSuper() throws InterruptedException
	{
		driver.get(PropFileHandler.readProperty("url") +"/login");
		BaseFixture.superLogin();
		
		driver.get(PropFileHandler.readProperty("url") +"/reports");
		longwait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Type to search...']")));
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='text'])[1]")).clear();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='text'])[1]")).sendKeys(PropFileHandler.readProperty("customer"));
		Thread.sleep(5000);
		driver.findElement(By.xpath("//li[@class='resource customer active']/strong")).click();
		
		Select svm = new Select(driver.findElement(By.cssSelector("#select_resource")));
        svm.selectByVisibleText("SVMs");
        
        Select duration = new Select(driver.findElement(By.cssSelector("#select_time_period")));
        duration.selectByVisibleText("Right now");
        
        driver.findElement(By.xpath("//input[@value='Generate report']")).click();
        Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Generate report']")));
		Reporter.log("Usage Report Successfully Generated" , true);
		Assert.assertEquals(driver.findElement(By.xpath("//tr[@class='total ungrouped ']/td[@class='grouping']")).getText(), "All users");
	}
	
	public void storageUsageSuper() throws InterruptedException 
	{
		driver.get(PropFileHandler.readProperty("url") +"/reports");
		longwait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Type to search...']")));
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='text'])[1]")).clear();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='text'])[1]")).sendKeys(PropFileHandler.readProperty("customer"));
		Thread.sleep(5000);
		driver.findElement(By.xpath("//li[@class='resource customer active']/strong")).click();
		
		Select svm = new Select(driver.findElement(By.cssSelector("#select_resource")));
        svm.selectByVisibleText("Storage");
        
        Select duration = new Select(driver.findElement(By.cssSelector("#select_time_period")));
        duration.selectByVisibleText("Right now");
        
        driver.findElement(By.xpath("//input[@value='Generate report']")).click();
        Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Generate report']")));
		Reporter.log("Usage Report Successfully Generated" , true);
		Assert.assertEquals(driver.findElement(By.xpath("//tr[@class='total ungrouped ']/td[@class='grouping']")).getText(), "All users");
	}
}