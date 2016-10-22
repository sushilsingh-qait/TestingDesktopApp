package com.test.skytap;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;

import com.test.util.PropFileHandler;

public class J_Help_Pages extends BaseFixture 
{
	String helplink = "";
	
	public void superTab() throws InterruptedException 
	{	
		helplink = driver.findElement(By.xpath("//a[@title='Get help with this page']")).getAttribute("href");
		driver.get(helplink);
		x = driver.findElement(By.xpath("//*[@class='homesearch']/h1")).getText();
		y = "How can we help?";
		Assert.assertEquals(x,y);
		Reporter.log("Help Page Accessible", true);
	}
	
	public void adminTab() throws InterruptedException 
	{
		driver.get(PropFileHandler.readProperty("url") +"/login");
		BaseFixture.adminLogin();
		helplink = driver.findElement(By.xpath("//a[@title='Get help with this page']")).getAttribute("href");
		driver.get(helplink);
		x = driver.findElement(By.xpath("//*[@class='homesearch']/h1")).getText();
		y = "How can we help?";
		Assert.assertEquals(x,y);
		Reporter.log("Help Page Accessible", true);

	}
}