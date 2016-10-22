package com.test.skytap;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;

public class L_Published_Services extends BaseFixture
{
	String publicAddressByName = "";
	String internalPortByName = "";
	String publicAddressByPort = "";
	String internalPortByPort = "";
	
	public void addByName() throws InterruptedException 
	{
		Reporter.log(" *** Starting Published Services Test *** ",true);
		driver.get(createdenvironment);
		driver.findElement(By.xpath("//a[@class='vms-settings']")).click();
		driver.findElement(By.cssSelector(".icon_smalladd.add_published_service")).click();
		driver.findElement(By.cssSelector("#add_published_service_submit")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".show_published_services.linkexpando.icon_expandoclosed")));
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".show_published_services.linkexpando.icon_expandoclosed")).click();
		Thread.sleep(2000);
		publicAddressByName = driver.findElement(By.xpath("//td[@class='public_address']/a")).getText();
		internalPortByName = driver.findElement(By.xpath("//td[@class='internal_port']")).getText();
		driver.findElement(By.xpath("//div[@class='remove_service']//a")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='This network adapter has no published services.']")));
		Reporter.log("Environment Used for PS Testing " +createdenvironment,true);
		Reporter.log("Publiched Service address = " +publicAddressByName,true);
		Reporter.log(" Attached Port No. " +internalPortByName,true);
	}
	
	public void addByPort() throws InterruptedException 
	{
		driver.get(createdenvironment);
		driver.findElement(By.xpath("//a[@class='vms-settings']")).click();
		driver.findElement(By.cssSelector(".icon_smalladd.add_published_service")).click();
		driver.findElement(By.cssSelector("#by_port")).click();
		driver.findElement(By.cssSelector("#internal_port")).sendKeys("8080");
		driver.findElement(By.cssSelector("#add_published_service_submit")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".show_published_services.linkexpando.icon_expandoclosed")));
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".show_published_services.linkexpando.icon_expandoclosed")).click();
		Thread.sleep(2000);
		publicAddressByPort = driver.findElement(By.xpath("//td[@class='public_address']/span")).getText();
		internalPortByPort = driver.findElement(By.xpath("//td[@class='internal_port']")).getText();
		driver.findElement(By.xpath("//div[@class='remove_service']//a")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='This network adapter has no published services.']")));
		Reporter.log("Publiched Service address " +publicAddressByPort,true);
		Reporter.log(" Attached Port No. " +internalPortByPort,true);
	}
}