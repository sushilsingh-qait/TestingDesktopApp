package com.test.skytap;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.test.util.PropFileHandler;

public class Q_Import_Export extends BaseFixture
{
	String ftpurl = "";
	String ftpusername = "";
	String ftppass = "";
	
	public void exportVM() throws InterruptedException
	{	
		driver.get(regiontemplateurl);
		driver.findElement(By.cssSelector(".primary-option.clone")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@type='submit']")));
		driver.findElement(By.xpath("//*[@type='submit']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='site_message_text']")));
		driver.findElement(By.xpath("//*[@id='constituent_machines']/table/tbody/tr[2]/td/a[1]")).click();
		driver.findElement(By.cssSelector(".primary-option.export")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#create_export_button")));
		driver.findElement(By.cssSelector("#create_export_button")).click();
	}
	
	public void importVM() throws InterruptedException, AWTException 
	{
		driver.get(PropFileHandler.readProperty("url") +"/imports");
		driver.findElement(By.cssSelector(".action-button.icon.icon-add")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("#template_name")).sendKeys("Import Test for " +PropFileHandler.readProperty("region") +" " +random);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@title='Save Import Job']")));
		Select region = new Select(driver.findElement(By.cssSelector("#region")));
        region.selectByVisibleText(PropFileHandler.readProperty("region"));
		driver.findElement(By.xpath(".//*[@title='Save Import Job']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Create environment']")));
		ftpurl = driver.findElement(By.xpath("//dd[contains(text(),'sftp')]")).getText();
		ftpusername = driver.findElement(By.xpath("//*[text()='Username:']/../dd[2]")).getText();
		ftppass = driver.findElement(By.xpath("//*[text()='Username:']/../dd[3]")).getText();
	
		uploadVM(ftpurl, ftpusername, ftppass);

	}	
}