package com.test.skytap;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.test.util.PropFileHandler;

public class X_Support_Request_Form extends BaseFixture 

{
	public void clickSupportLink() throws InterruptedException 
	{	
		driver.get(PropFileHandler.readProperty("url") +"/support");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Get Support']")));
	}
	
	public void submitForm() throws InterruptedException 
	{
	
	}
	
	public void caseGenerateVerify() throws InterruptedException 
	{
	
	}
}
