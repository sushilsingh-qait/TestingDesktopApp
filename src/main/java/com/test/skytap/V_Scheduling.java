package com.test.skytap;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;

import com.test.util.PropFileHandler;

public class V_Scheduling extends BaseFixture 
{
	public void createScheduleAdmin() throws InterruptedException 
	{	
		Reporter.log(" *** Starting with creating a new Schedule *** " ,true);
		driver.get(PropFileHandler.readProperty("url") +"/schedules/new");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//*[@class='resource'])[1]")));
		driver.findElement(By.xpath("//*[text()='Filter by Region']/../div/input[1]")).sendKeys(PropFileHandler.readProperty("region"));
		driver.findElement(By.xpath("//li[@class='resource region active']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//*[@class='resource'])[1]")));
		driver.findElement(By.xpath("(.//*[@class='resource'])[1]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action-button.loud.next")));
    	driver.findElement(By.cssSelector(".action-button.loud.next")).click();
    	driver.findElement(By.cssSelector("#notifyUser")).click();    	
    	driver.findElement(By.cssSelector(".action-button.loud")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".icon-add")));
		driver.get(PropFileHandler.readProperty("url") +"/schedules?scope=me&sort=next_action_time");
		scheduleid = driver.findElement(By.xpath("(//a[contains(@class,'schedule-name')])[1]")).getAttribute("href");
		String scheduletime = driver.findElement(By.xpath("(//*[text()='Starts'])[1]/../dd[1]")).getText();
		Reporter.log("Successfully created schedule with Admin with id = " +scheduleid +" Scheduled at " +scheduletime,true);
	}
	
	public void createEnvironmentNonAdmin() throws InterruptedException 
	{
		fetchTemplate();
		driver.get(regiontemplateurl);
		driver.findElement(By.cssSelector(".configuration")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".run-vm")));
		driver.findElement(By.cssSelector(".edit-environment-name")).click();
		driver.findElement(By.cssSelector(".name-field")).clear();
		driver.findElement(By.cssSelector(".name-field")).sendKeys("Environment for Non-Admin Schedule");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text()='Details successfully updated for this environment.']")));

		driver.findElement(By.className("edit-owner")).click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@id,'assign-to-user')]")));
		driver.findElement(By.xpath("//dd[text()='"+PropFileHandler.readProperty("nonadminUser")+"']/../../../../td[@class='resource-select']/input")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text()='Change owner']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Environment owner successfully changed.']")));
	}
	public void createScheduleNonAdmin() throws InterruptedException 
	{	
		createEnvironmentNonAdmin();
		driver.get(PropFileHandler.readProperty("url") +"/login");
		driver.findElement(By.id("userLoginName")).sendKeys(PropFileHandler.readProperty("nonadminUser"));

		driver.findElement(By.id("userLoginPassword")).clear();
		driver.findElement(By.id("userLoginPassword")).sendKeys(PropFileHandler.readProperty("nonadminPassword"));

		driver.findElement(By.cssSelector(".action-button")).click();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@role='menuitem']/a[@title='Go to your dashboard']")));    	
    	driver.get(PropFileHandler.readProperty("url") +"/schedules/new");
    	
//    	driver.findElement(By.xpath("//label[text()='Filter by Region']/../div/input[1]")).sendKeys(PropFileHandler.readProperty("region"));
//		driver.findElement(By.xpath("//li[@class='resource region active']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(.//tr[@class='resource'])[1]")));
		driver.findElement(By.xpath("(.//*[@class='resource'])[1]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action-button.loud.next")));
    	driver.findElement(By.cssSelector(".action-button.loud.next")).click();
    	driver.findElement(By.cssSelector("#notifyUser")).click();	
    	driver.findElement(By.cssSelector(".action-button.loud")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".icon-add")));
		driver.get(PropFileHandler.readProperty("url") +"/schedules?scope=me&sort=next_action_time");
		scheduleid = driver.findElement(By.xpath("(//a[contains(@class,'schedule-name')])[1]")).getAttribute("href");
		String scheduletime = driver.findElement(By.xpath("(//*[text()='Starts'])[1]/../dd[1]")).getText();
		Reporter.log("Successfully created schedule by Non-Admin with id = " +scheduleid +" Scheduled at " +scheduletime,true);
	}
	
	public void schedulesList() throws InterruptedException 
	{
		driver.get(PropFileHandler.readProperty("url") +"/schedules?scope=me");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='resource-name schedule-name']")));
		Reporter.log("Schedules owned by me found",true);
	}
}