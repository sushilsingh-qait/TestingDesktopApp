package com.test.skytap;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;

import com.test.util.PropFileHandler;

public class G_Public_IP extends BaseFixture {

	String pip = "";
		public void fetchPIP() throws InterruptedException 
		{
			Reporter.log(" *** Fetching Public IP ***", true);
			driver.get(PropFileHandler.readProperty("url") +"/ips");
			driver.findElement(By.cssSelector(".add-public-ip-address")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#MB_content>div>p")));
			driver.findElement(By.xpath("//label[text()='"+PropFileHandler.readProperty("region")+"']")).click();
			driver.findElement(By.id("add_public_ip_submit")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[contains(text(),'Added public IP address')]")));
			pip = driver.findElement(By.xpath("//span[contains(text(),'Added public IP address')]")).getText();
			Reporter.log("Public IP "+pip +" Successfully fetched", true);
		}
		
		public void releasePIP() throws InterruptedException
		{
			Reporter.log(" *** Releasing PIP ***", true);
			driver.get(PropFileHandler.readProperty("url") +"/ips");
			driver.findElement(By.xpath("//td[contains(text(),'"+PropFileHandler.readProperty("region")+"')]/../td[@class='action_button']/a")).click();
			Thread.sleep(2000);
	        driver.switchTo().alert();
	        driver.switchTo().alert().accept();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Public IP Address successfully released']")));
			Reporter.log("Successfully released PIP " +pip, true);
		}
	}