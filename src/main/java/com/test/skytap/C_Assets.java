package com.test.skytap;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;

import com.test.util.PropFileHandler;

public class C_Assets extends BaseFixture 
{
	public void uploadAsset() throws InterruptedException, AWTException 
	{	
		Reporter.log(" *** Starting with Asset Upload ***", true);
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
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//dd[text()='Completed']")));
		driver.get(PropFileHandler.readProperty("url") +"/assets?sort=created_at_desc&scope=me");
		uploadedasset = driver.findElement(By.xpath("(//a[@class='resource-name asset-name'])[1]")).getAttribute("href");
		Reporter.log("Successfully uploaded asset with id = " +uploadedasset, true);	
	}
	
	public void navigatehome() throws InterruptedException 
	{
		driver.get(PropFileHandler.readProperty("url"));
		try
		{
		driver.switchTo().alert().accept();
		}catch(Exception E){}
	}
	
	public void deleteAsset() throws InterruptedException 
	{
		driver.get(uploadedasset);
		driver.findElement(By.cssSelector(".delete")).click();
		Thread.sleep(2000);
        driver.switchTo().alert();
        driver.switchTo().alert().accept();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Asset successfully deleted']")));
		Reporter.log("Successfully Deleted asset with id = " +uploadedasset, true);
	}
}