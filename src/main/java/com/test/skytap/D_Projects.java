package com.test.skytap;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;

import com.test.util.PropFileHandler;

public class D_Projects extends BaseFixture 
{
	String projecturl = "";
	String projname = ""; 
	
	public void createProject() throws InterruptedException 
	{
		driver.get(PropFileHandler.readProperty("url") +"/projects");
		Reporter.log(" *** Starting Projects Test ***", true);
		driver.findElement(By.cssSelector(".icon-add")).click(); // Click on new project    	    	 
        String timestamp = Long.toString(System.currentTimeMillis());
        driver.findElement(By.cssSelector("#projectName")).sendKeys("QAIT Automation Project " +timestamp); // Enter project name
        driver.findElement(By.xpath("//button[text()='Create project']")).click(); // Click on create project button 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".primary-option.back")));
		projname = driver.findElement(By.xpath("//h1[@id='project_name']/span")).getText();
		projecturl = driver.getCurrentUrl();
		Reporter.log("Created Project with Name = " +projname +" and ID =" +projecturl, true);
	}
	
	public void addTemplateToProject() throws InterruptedException 
	{
		driver.get(projecturl);
        driver.findElement(By.cssSelector(".primary-option.template")).click(); // Click on template icon
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id,'config_template_link')]")));
        driver.findElement(By.xpath("(//td[contains(@id,'config_template_link')])[1]")).click(); // Click on first template
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".lightbox_ok_button.MB_focusable")));
        driver.findElement(By.cssSelector(".lightbox_ok_button.MB_focusable")).click(); // Click on Add button
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='site_message_text']")));
		Reporter.log("Added Template to Project successfully", true);
	}
	
	public void addEnvironmentToProject() throws InterruptedException
	{
		fetchTemplate();
		driver.get(regiontemplateurl);
		driver.findElement(By.cssSelector(".configuration")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".run-vm")));
		driver.findElement(By.cssSelector(".run-vm")).click();
		longwait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@title='Running']")));
		vmid = driver.findElement(By.xpath("//li[contains(@class,'vm in-multi-select-mode')]")).getAttribute("data-vm-id");
		driver.findElement(By.cssSelector(".edit-environment-name")).click();
		driver.findElement(By.cssSelector(".name-field")).clear();
		driver.findElement(By.cssSelector(".name-field")).sendKeys("Environment for Projects and SVM usage validation");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text()='Details successfully updated for this environment.']")));
		projAndSVMEnvironment = driver.getCurrentUrl();

		driver.get(projecturl);
		driver.findElement(By.cssSelector(".add-config-to-project")).click(); // Click on environment icon        
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id,'instance_row')]")));
        driver.findElement(By.xpath("(//tr[contains(@id,'instance_row')])[1]")).click(); // Click on first environment
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".lightbox_ok_button.MB_focusable")));
        driver.findElement(By.cssSelector(".lightbox_ok_button.MB_focusable")).click(); // Click on Add button
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='site_message_text']")));
		Reporter.log("Added Environment to Project successfully", true);
	}
	
	public void addAssetToProject() throws InterruptedException
	{
		driver.get(projecturl);
		driver.findElement(By.cssSelector(".asset")).click(); // Click on asset icon
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(@id,'asset_link')]")));
        driver.findElement(By.xpath("(//td[contains(@id,'asset_link')])[1]")).click(); // Click on first asset
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".lightbox_ok_button.MB_focusable")));
        driver.findElement(By.cssSelector(".lightbox_ok_button.MB_focusable")).click(); // Click on Add button
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='site_message_text']")));
		Reporter.log("Added Asset to Project successfully", true);
	}	

	public void addUserToProject() throws InterruptedException 
	{
		driver.get(projecturl);
		driver.findElement(By.cssSelector(".add-user-to-project")).click(); // Click on Add users icon
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[contains(@id,'_user')]/td[@class='actions']")));
        driver.findElement(By.xpath("//tr[contains(@id,'_user')]/td[@class='actions']")).click(); // Click on link Add in pop up window
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Added']")));
        driver.findElement(By.xpath("//input[@value='Done']")).click(); // click on done button to close pop up
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".linkbutton.icon_remove")));    	
    	Reporter.log("Added User to Project successfully", true);
	}
	
	public void deleteProject() throws InterruptedException 
	{
		driver.get(projecturl);
		driver.findElement(By.cssSelector(".primary-option.delete")).click(); // Click on Delete icon
        Thread.sleep(2000);
        driver.switchTo().alert();
        driver.switchTo().alert().accept();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".icon-add")));
		Reporter.log("Deleted Project successfully", true);
	}
}