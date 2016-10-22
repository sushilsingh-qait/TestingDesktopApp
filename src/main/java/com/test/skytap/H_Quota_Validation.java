package com.test.skytap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.test.util.PropFileHandler;

public class H_Quota_Validation extends BaseFixture 
{
	int VMsMaxConcurrent = 0;
	float Storage = 0;
	int	 SVMsMaxConcurrent = 0;
	float SVMHours = 0;
	String quotaTestEnv = "";
	String quotaTestTemp = "";
//	String customerURL = "https://test.skytap.com/customers/8656";
	
	public void createQuotaEnvironment() throws InterruptedException 
	{
		driver.get(regiontemplateurl);
		driver.findElement(By.cssSelector(".configuration")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".vm-screenshot")));
		driver.findElement(By.cssSelector(".edit-environment-name")).click();
		driver.findElement(By.cssSelector(".name-field")).clear();
		driver.findElement(By.cssSelector(".name-field")).sendKeys("Environment for Quota Test");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Details successfully updated for this environment.']")));    
		quotaTestEnv = driver.getCurrentUrl();
		Reporter.log("Created environment for Quota Test - " +quotaTestEnv, true);
	}
	
	public void customerSvmQuotaOnVmRun() throws InterruptedException 
	{
		addCustomerLimit("SVMs (max concurrent)");
		driver.get(quotaTestEnv);
		driver.findElement(By.cssSelector(".run-vm")).click();
		longwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='msg block error block' and contains(text(),'The operation would exceed the SVMs limit')]"))).click();
		String svmerroronrunvm = driver.findElement(By.xpath("//div[@class='msg block error block' and contains(text(),'The operation would exceed the SVMs limit')]")).getText();
		Reporter.log("Customer Quota - SVMs error on running an environment- "+svmerroronrunvm,true);
	}
	
	public void customerStorageQuotaAddTempToEnv() throws InterruptedException 
	{
		addCustomerLimit("Storage (GB)");
		driver.get(regiontemplateurl);
		driver.findElement(By.cssSelector(".add-to-config")).click();
		Select environment = new Select(driver.findElement(By.xpath("//select[@id='id']")));
		environment.selectByVisibleText("Environment for Quota Test");
		driver.findElement(By.xpath("//input[@id='add_to_existing_button']")).click();
		wait.until(ExpectedConditions.alertIsPresent());
		String storageerroraddenvironment = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		Reporter.log("Customer Quota - Storage error on Adding template to Environment- "+storageerroraddenvironment,true);
	}
	
	public void customerStorageQuotaCreateTempFromEnv() throws InterruptedException 
	{
		addCustomerLimit("Storage (GB)");
    	driver.get(quotaTestEnv);
		driver.findElement(By.cssSelector(".save_as_template")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		wait.until(ExpectedConditions.alertIsPresent());
		String storageerrorsavetemplate = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		Reporter.log("Customer Quota - Storage Error while creating template from environment is " +storageerrorsavetemplate,true);
	}
	
	public void customerStorageQuotaAssetUpload() throws InterruptedException
	{
		addCustomerLimit("Storage (GB)");
		driver.get(PropFileHandler.readProperty("url") +"/uploads");
		driver.findElement(By.xpath("//a[contains(@class,'dropdown')]")).click();
		driver.findElement(By.xpath("//input[@checked='checked']")).click();
		driver.findElement(By.xpath("//label[text()='"+PropFileHandler.readProperty("region")+"']/../input")).click();
		driver.findElement(By.xpath("//div[@class='drag-drop-container']")).click();
		executeJavascript("document.getElementsByClassName('browse-for-files-decoy')[0].click();");
		
		String workingDir = System.getProperty("user.dir") +"\\" +PropFileHandler.readProperty("assetuploadassetfile");
		uploadFile(workingDir);		
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//dd[text()='Ready to upload']")));
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(".icon-upload")).click();
		Thread.sleep(3000);
		
		String storageError = driver.findElement(By.xpath("//div[@class='msg block hideable error block']")).getText();
    	driver.findElement(By.cssSelector(".remove-all")).click();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action-button.submit-ok"))); 
    	driver.findElement(By.cssSelector(".action-button.submit-ok")).click();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".no-resource-description")));
    	Reporter.log("Customer Quota - Storage Error on uploading new file - " +storageError,true);
	}
	
	public void customerStorageQuotaAddVM() throws InterruptedException 
	{
		addCustomerLimit("Storage (GB)");
    	driver.get(quotaTestEnv);    	
    	driver.findElement(By.xpath(".//*[contains(@class,'icon-add')]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='template-sort']")));
		driver.findElement(By.xpath("//a[@data-resource='configurations']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='template-sort']")));
		driver.findElement(By.xpath("//ul[contains(@class,'configuration-scopes')]/li/a[text()='My']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='template-sort']")));
		driver.findElement(By.xpath("//label[text()='Environment for Quota Test' and @title='Select this environment']/../../../../td[@class='check']/input")).click();
		driver.findElement(By.xpath("//input[@value='Add VM(s)']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='msg block error dialog-alert' and contains(text(),'This operation would result in a quota violation')]")));
		String addVMerror = driver.findElement(By.xpath("//div[@class='msg block error dialog-alert' and contains(text(),'This operation would result in a quota violation')]")).getText();
		driver.findElement(By.cssSelector(".action-button.quiet.cancel")).click();
    	Reporter.log("Customer Quota - Storage Error on Adding VM to Environment - " +addVMerror,true);
	}
	
	public void customerSvmHourVmAutoSuspend() throws InterruptedException 
	{
		driver.get(quotaTestEnv);
		driver.findElement(By.cssSelector(".run-vm")).click();
		longwait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".suspend-vm")));
		
		addCustomerLimit("SVM hours");
		
		driver.get(quotaTestEnv);
		System.out.println("Waiting for VM to Auto-Suspend");
		longwait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".run-vm")));	
		Reporter.log("Customer Quota - VM in environment " +quotaTestEnv +" is automatically suspended",true);
	}
	
	public void customerSvmHourQuotaOnVmRun() throws InterruptedException 
	{
		addCustomerLimit("SVM hours");
		driver.get(quotaTestEnv);
		driver.findElement(By.cssSelector(".run-vm")).click();
		longwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='msg block error block' and contains(text(),'The operation would exceed the SVM hours limit')]"))).click();
		String svmerroronrunvm = driver.findElement(By.xpath("//div[@class='msg block error block' and contains(text(),'The operation would exceed the SVM hours limit')]")).getText();
		Reporter.log("SVMs error on running an environment- "+svmerroronrunvm,true);
	}
	
	public void userStorageQuotaAddTempToEnv() throws InterruptedException 
	{ 	
		addUserLimit("Storage (GB)");		
		driver.get(regiontemplateurl);
		driver.findElement(By.cssSelector(".add-to-config")).click();
		Select environment = new Select(driver.findElement(By.xpath("//select[@id='id']")));
		environment.selectByVisibleText("Environment for Quota Test");
		driver.findElement(By.xpath("//input[@id='add_to_existing_button']")).click();
		wait.until(ExpectedConditions.alertIsPresent());
		String storageerroraddenvironment = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		Reporter.log("User Quota - Storage error on Adding template to Environment- "+storageerroraddenvironment,true);	
	}
	
	public void userSvmHourQuotaOnVmRun() throws InterruptedException 
	{		
		addUserLimit("SVM hours");
		driver.get(quotaTestEnv);
		driver.findElement(By.cssSelector(".run-vm")).click();
		longwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='msg block error block' and contains(text(),'The operation would exceed the SVM hours limit')]"))).click();
		String svmerroronrunvm = driver.findElement(By.xpath("//div[@class='msg block error block' and contains(text(),'The operation would exceed the SVM hours limit')]")).getText();
		Reporter.log("User Quota - SVM Hours error on running an environment- "+svmerroronrunvm,true);
	}
	
	public void userSvmQuotaOnVmRun() throws InterruptedException 
	{
		addUserLimit("SVMs (max concurrent)");
		driver.get(quotaTestEnv);
    	driver.findElement(By.cssSelector(".run-vm")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='msg block error block' and contains(text(),'The operation would exceed the SVMs limit by')]")));
		String svmerror = driver.findElement(By.xpath("//div[@class='msg block error block' and contains(text(),'The operation would exceed the SVMs limit by')]")).getText();
		Reporter.log("User Quota - SVMs error on running an environment- "+svmerror,true);
	}
	
	public void userStorageQuotaAssetUpload() throws InterruptedException 
	{
		addUserLimit("Storage (GB)");
		driver.get(PropFileHandler.readProperty("url") +"/uploads");
		driver.findElement(By.xpath("//a[contains(@class,'dropdown')]")).click();
		driver.findElement(By.xpath("//input[@checked='checked']")).click();
		driver.findElement(By.xpath("//label[text()='"+PropFileHandler.readProperty("region")+"']/../input")).click();
		driver.findElement(By.xpath("//div[@class='drag-drop-container']")).click();
		executeJavascript("document.getElementsByClassName('browse-for-files-decoy')[0].click();");
		
		String workingDir = System.getProperty("user.dir") +"\\" +PropFileHandler.readProperty("assetuploadassetfile");
		uploadFile(workingDir);		
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//dd[text()='Ready to upload']")));
		Thread.sleep(3000);
			
		String storageError = driver.findElement(By.xpath("//div[contains(text(),'The storage required for all of the files in this list exceeds your available storage')]")).getText();
    	driver.findElement(By.cssSelector(".remove-all")).click();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action-button.submit-ok"))); 
    	driver.findElement(By.cssSelector(".action-button.submit-ok")).click();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".no-resource-description")));
    	Reporter.log("User Quota - Storage Error on uploading new file - " +storageError,true);
	}
	
	public void userStorageQuotaAddVM() throws InterruptedException 
	{
		addUserLimit("Storage (GB)");
		driver.get(quotaTestEnv);
		driver.findElement(By.xpath(".//*[contains(@class,'icon-add')]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='template-sort']")));
		driver.findElement(By.xpath("//a[@data-resource='configurations']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='template-sort']")));
		driver.findElement(By.xpath("//ul[contains(@class,'configuration-scopes')]/li/a[text()='My']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='template-sort']")));
		driver.findElement(By.xpath("//label[text()='Environment for Quota Test' and @title='Select this environment']/../../../../td[@class='check']/input")).click();
		driver.findElement(By.xpath("//input[@value='Add VM(s)']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='msg block error dialog-alert' and contains(text(),'This operation would result in a quota violation')]")));
		String addVMerror = driver.findElement(By.xpath("//div[@class='msg block error dialog-alert' and contains(text(),'This operation would result in a quota violation')]")).getText();
		driver.findElement(By.cssSelector(".action-button.quiet.cancel")).click();
    	Reporter.log("User Quota - Storage Error on Adding VM to Environment - " +addVMerror,true);
	}
	
	public void userSvmHourVmAutoSuspend() throws InterruptedException 
	{
		driver.get(quotaTestEnv);
		try{driver.findElement(By.cssSelector(".suspend-vm")).click();}catch(Exception E){}
		longwait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".run-vm")));
		driver.findElement(By.cssSelector(".run-vm")).click();
		longwait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".suspend-vm")));
		
		addUserLimit("SVM hours");
		
		driver.get(quotaTestEnv);
		System.out.println("Waiting for VM to Auto-Suspend");
		longwait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".run-vm")));
		Reporter.log("User Quota - VM in environment " +quotaTestEnv +" is automatically suspended",true);
	}
}