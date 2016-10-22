package com.test.skytap;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.test.util.PropFileHandler;

public class E_TemplatesConfigurations extends BaseFixture 
{	
	public void searchTemplates() throws InterruptedException 
	{	
		Reporter.log(" *** Starting Templates and Environment Test ***", true);
		driver.get(PropFileHandler.readProperty("url") +"/templates?scope=public&query=region%3A" +PropFileHandler.readProperty("region"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".resource-name.configuration-name")));
		driver.findElement(By.cssSelector(".query")).sendKeys("windows 7");
		driver.findElement(By.cssSelector(".index-search-btn")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".resource-name.configuration-name")));
		Reporter.log("Searched template successfully", true);
	}
	
	public static void createTemplate() throws InterruptedException 
	{
		Reporter.log(" *** Starting with Creating Template ***", true);
		driver.get(regiontemplateurl);
		driver.findElement(By.cssSelector(".clone")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#name")));
		driver.findElement(By.cssSelector("#name")).clear();
        String timestamp = Long.toString(System.currentTimeMillis());
		driver.findElement(By.cssSelector("#name")).sendKeys("QAIT Automation Template " +timestamp);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'This template was successfully copied from')]")));
		Reporter.log("Created New Template with Name = QAIT Automation Template " +timestamp +" and id = " +driver.getCurrentUrl(), true);;
		createdtemplate = driver.getCurrentUrl();
		createdtemplatename = "QAIT Automation Template " +timestamp;
	}
	
	public static void createAndRunVmInEnvironment() throws InterruptedException 
	{
		Reporter.log(" *** Starting with Creating an Environment ***", true);
		fetchTemplate();
		driver.get(regiontemplateurl);
		driver.findElement(By.cssSelector(".configuration")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".vm-screenshot")));
		driver.findElement(By.cssSelector(".edit-environment-name")).click();
		driver.findElement(By.cssSelector(".name-field")).clear();
        String timestamp = Long.toString(System.currentTimeMillis());
		driver.findElement(By.cssSelector(".name-field")).sendKeys("Environment for PO Test");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Details successfully updated for this environment.']")));	
		createdenvironmentname = driver.findElement(By.xpath("//h1[@class='resource-name']")).getText();
		createdenvironment = driver.getCurrentUrl();
		Reporter.log("Created New Environment with Name = QAIT Automation Environment " +timestamp +" and id = " +createdenvironment, true);
		
		driver.findElement(By.cssSelector(".run-vm")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='Running']")));	
	}
	
	public void addTemplateToEnvironment() throws InterruptedException
	{
		driver.get(PropFileHandler.readProperty("url") +"/templates");
		try{driver.findElement(By.xpath("//a[@title='Clear all filters']")).click();}catch(Exception E){}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".resource-name.configuration-name")));
		driver.get(PropFileHandler.readProperty("url") +"/templates?scope=public&query=region%3A" +PropFileHandler.readProperty("region"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".resource-name.configuration-name")));
		driver.findElement(By.cssSelector(".query")).sendKeys("Win 7 SP1 VPN");
		driver.findElement(By.cssSelector(".index-search-btn")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".resource-name.configuration-name")));
		driver.findElement(By.xpath("(//div[@class='btn-group'])[1]/button[2]")).click();
		driver.findElement(By.xpath("(//a[text()='Add to existing environment'])[1]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//input[@value='Add to Environment']")));
		Select environment = new Select(driver.findElement(By.cssSelector("#add-to-config-dialog-conf")));
		environment.selectByVisibleText(createdenvironmentname);
		driver.findElement(By.xpath(".//input[@value='Add to Environment']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Virtual machine(s) added to your environment')]")));
		Reporter.log("Added template to Environment Successfully", true);
		
		driver.findElement(By.xpath("//span[@title='Powered off']/../../div[@class='vm-secondary-actions btn-group']/a[@class='action-item-dangerous delete-vm']")).click();
		driver.findElement(By.xpath("//input[@value='Delete VM']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='VMs (1)']")));
	}
	
	public void insertISO() throws InterruptedException 
	{
		driver.findElement(By.cssSelector(".load-iso")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='You may only load an ISO from the same region.']")));
		driver.findElement(By.cssSelector(".all>a")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@type='radio'])[1]")));
		driver.findElement(By.xpath("(//input[@type='radio'])[1]")).click();
		driver.findElement(By.xpath(".//input[@name='submit']")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hideable")));	
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(".hide-msg")).click();
		Reporter.log("ISO successfuly inserted to Environment " +createdenvironment, true);
	}
	
	public void suspendVM() throws InterruptedException 
	{
		driver.findElement(By.cssSelector(".suspend-vm")).click();
		longwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='Suspended']")));	
		Reporter.log("VM Level Suspend", true);
		
		driver.findElement(By.cssSelector(".run-vm")).click();
		Thread.sleep(180000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='Running']")));	
	}
	
	public void shutDownVM() throws InterruptedException 
	{
		driver.findElement(By.cssSelector(".shutdown-vm")).click();
		longwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='Powered off']")));		
		Reporter.log("VM Level Shut Down Success", true);
		
		driver.findElement(By.cssSelector(".run-vm")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='Running']")));	
	}
	
	public void powerOffVM() throws InterruptedException 
	{
		driver.findElement(By.xpath("//button[@title='Power options for this VM']")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".change-runstate.power-off")).click();
		longwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='Powered off']")));
		Reporter.log("VM Level Power-Off Success", true);
	}
	
	public void runEnv() throws InterruptedException 
	{
		driver.findElement(By.cssSelector(".run-configuration")).click();
		Thread.sleep(180000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='Running']")));
		Reporter.log("Environment Level VM Run Success", true);
	}
	
	public void suspendEnv() throws InterruptedException 
	{	
		driver.findElement(By.cssSelector(".suspend-configuration")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='Suspended']")));
		Reporter.log("Environment Level Suspend Success", true);
		
		driver.findElement(By.cssSelector(".run-configuration")).click();
		Thread.sleep(180000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='Running']")));
	}
	
	public void shutDownEnv() throws InterruptedException 
	{
		driver.findElement(By.cssSelector(".shutdown-configuration")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='Powered off']")));
		Reporter.log("Environment Level Shut-Down Success", true);
	}
	
	public void powerOffEnv() throws InterruptedException 
	{
		driver.findElement(By.cssSelector(".run-configuration")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='Running']")));
		
		driver.findElement(By.xpath("//button[@title='Power options for the selected VM(s)']")).click();
		driver.findElement(By.xpath("//button[@title='Power Off this Environment']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='Powered off']")));
		Reporter.log("Environment Level Power-Off Success", true);
	}
	
	public void publishVM() throws InterruptedException 
	{
		Reporter.log(" *** Starting with Creating a new sharing portal ***", true);
		driver.get(createdenvironment);
		driver.findElement(By.xpath("//a[text()='Sharing Portals']")).click();
		driver.findElement(By.cssSelector(".icon-add")).click();
		driver.findElement(By.cssSelector("#new-publish-set-name")).clear();
		driver.findElement(By.cssSelector("#new-publish-set-name")).sendKeys("QAIT Automation Sharing portal");
		driver.findElement(By.xpath("//input[@value='Create portal']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sharing-portal-list")));
		publishseturl = driver.getCurrentUrl();
		portalurl = driver.findElement(By.xpath("//a[@class='visit-portal']")).getAttribute("href");
		driver.findElement(By.cssSelector(".sharing-portal-list")).click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".save_as_template")));
		Reporter.log("Created Sharing Portal with URL = " +publishseturl, true);
		Reporter.log("Portal URL = " +portalurl, true);
	}
	
	public void saveEnv() throws InterruptedException 
	{
		driver.get(createdenvironment);
		driver.findElement(By.cssSelector(".save_as_template")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#site_message_text")));
		saveenvastemplateurl = driver.getCurrentUrl();
		driver.findElement(By.cssSelector("#site_message_text>a")).click();
		Reporter.log("Save Environment to template success with id = " +saveenvastemplateurl, true);
		Thread.sleep(10000);
	}
	
	public void deleteVmAndEnv() throws InterruptedException 
	{
		driver.get(createdenvironment);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".copy")));
		driver.findElement(By.cssSelector(".copy")).click();
		longwait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Copy']")));
		driver.findElement(By.xpath("//button[text()='Copy']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='msg block'][contains(text(),'This environment was successfully copied')]")));
		String deletedenv = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[@class='action-item-dangerous delete-vm']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Delete VM']")));
		driver.findElement(By.xpath("//input[@value='Delete VM']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='no-resource-description']")));
		Reporter.log("Delete VM Success, VM id = " +vmid, true);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".delete")));
		driver.findElement(By.cssSelector(".delete")).click();
		driver.findElement(By.xpath("//input[@value='Delete Environment']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='msg block success hideable']")));	
		Reporter.log("Delete Environment Success " +deletedenv, true);
	}
}