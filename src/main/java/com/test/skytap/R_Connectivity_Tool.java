package com.test.skytap;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.test.util.PropFileHandler;

public class R_Connectivity_Tool extends BaseFixture 
{
	public void connectivity() throws InterruptedException 
	{	
		addVMIDToConnectivity();
		driver.get(PropFileHandler.readProperty("url") +"/login");
		BaseFixture.adminLogin();
		Reporter.log(" *** Connectivity Test Results ***", true);
		driver.get(PropFileHandler.readProperty("url") +"/connectivity");
		driver.findElement(By.xpath(".//*[@class='icon-dropdown icon-after icon']")).click();
		driver.findElement(By.xpath(".//*[text()='"+PropFileHandler.readProperty("region")+"']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@data-action='checkConnectivity']")));
		driver.findElement(By.xpath(".//*[@data-action='checkConnectivity']")).click();		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'This connectivity check is complete')]")));	
		String result1=driver.findElement(By.xpath(".//*[text()='Supported browser']/../div[2]")).getText(); 
		String result2=driver.findElement(By.xpath(".//*[text()='Client connection']/../div[2]")).getText();
		String result3=driver.findElement(By.xpath(".//*[text()='Latency']/../div[2]/span")).getText();
		Reporter.log("Supported browser = " +result1, true);
		Reporter.log("Client connection = " +result2, true);
		Reporter.log("Latency = " +result3, true);
	}
}