package com.test.skytap;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.test.util.PropFileHandler;

public class Y_Delete_Unused_Resources extends BaseFixture 
{
	public void deleteAllEnvironments() throws InterruptedException 
	{	
		Reporter.log("Deleting All Environments",true);
		driver.get(PropFileHandler.readProperty("url") +"/configurations?scope=company&count=100&query=none");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='resource-name configuration-name']")));
		driver.findElement(By.cssSelector(".toggle-multi-select-mode")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#rowToggle")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".delete-resources")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@value,'Delete Environment')]")));
		driver.findElement(By.xpath("//input[contains(@value,'Delete Environment')]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='You have no environments.']")));
		Reporter.log("Successfully Deleted All Environments in Account",true);
	}
	
	public void deleteAllTemplates() throws InterruptedException
	{
		driver.get(PropFileHandler.readProperty("url") +"/templates?scope=company&count=100&query=none");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='resource-name configuration-name']")));
		driver.findElement(By.cssSelector(".toggle-multi-select-mode")).click();
		driver.findElement(By.cssSelector("#rowToggle")).click();
		driver.findElement(By.cssSelector(".delete-resources")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@value,'Delete Template')]")));
		driver.findElement(By.xpath("//input[contains(@value,'Delete Template')]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='You have no templates.']")));
		Reporter.log("Successfully Deleted All Templates in Account",true);
	}
	
	public void deleteAllExports() throws InterruptedException 
	{
		driver.get(PropFileHandler.readProperty("url") +"/exports");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='secn']/div/a")));
		exportdownloadurl = driver.findElement(By.xpath("//div[@class='secn']/div/a")).getText();
		Reporter.log("Export template URL = " +exportdownloadurl ,true);
		driver.findElement(By.cssSelector(".delete_button")).click();
		Thread.sleep(2000);
        driver.switchTo().alert().accept();
		Reporter.log("Export Job Successfully deleted",true);
	}
	
	public void deleteAllImports() throws InterruptedException
	{
		totalImports = driver.findElements(By.xpath("//button[@class='action-button icon-only icon-close']")).size();
		for(int i=1;i<=totalImports;i++)
		{
			driver.findElement(By.xpath("(//button[@class='action-button icon-only icon-close'])[1]")).click();
			driver.findElement(By.xpath("//input[@value='Delete Import']")).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Delete successful']/a")));
			driver.findElement(By.xpath("//div[text()='Delete successful']/a")).click();
		}
	}
		
	public void deleteAllVPNs() throws InterruptedException
	{
		int totalVPS = driver.findElements(By.xpath("//a[@class='resource-name vpn-name']")).size();
		
		
		
	}
	
	
}