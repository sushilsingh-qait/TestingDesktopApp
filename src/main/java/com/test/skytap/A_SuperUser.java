 package com.test.skytap;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.test.util.PropFileHandler;

public class A_SuperUser extends BaseFixture 
{
	public void createCustomer() throws InterruptedException 
	{	
		getCustomerURL();		
		removeCustomerlimits();
		Reporter.log(" *** Creating a new customer ***", true);
		driver.get(PropFileHandler.readProperty("url") +"/customers");
		driver.findElement(By.cssSelector(".add")).click();
		String timestamp = Long.toString(System.currentTimeMillis());
		driver.findElement(By.cssSelector("#customer_name")).sendKeys("QAIT Customer " +timestamp);
		int total_regions=Integer.parseInt(PropFileHandler.readProperty("regioncount")); 
		for(int i=1;i<=total_regions;i++)
		 {		
			driver.findElement(By.xpath("(//span[@class='region-item']/input[@type='checkbox'])["+i+"]")).click();
		 }
		driver.findElement(By.cssSelector("#customer_ftp_username")).sendKeys("ftp_" +timestamp);		
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".primary-option.delete")));
		// Remove all customer level limits
        for(int i=1;i<=6;i++)
        {
        	driver.findElement(By.xpath("(.//*[@class='delete'])[1]")).click();
    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(.//*[@name='limit']/../a[text()='not set'])["+i+"]")));
        }
		driver.findElement(By.xpath("//input[@value='Update']")).click();
        customername = "QAIT Customer " +timestamp;
        newCustomerurl = driver.getCurrentUrl();
		Reporter.log("Successfully created a new customer with name = " +customername +"  and id = " +newCustomerurl, true);
	}

	public void createAdminUser() throws InterruptedException 
	{
		driver.get(newCustomerurl);
		driver.findElement(By.cssSelector(".primary-option.user")).click();
		driver.findElement(By.cssSelector(".icon-add")).click();
		driver.findElement(By.cssSelector("#user_email")).clear();
		driver.findElement(By.cssSelector("#user_email")).sendKeys("automation.skytap@gmail.com");
		driver.findElement(By.cssSelector("#user_login_name")).clear();
        String timestamp = Long.toString(System.currentTimeMillis());
		driver.findElement(By.cssSelector("#user_login_name")).sendKeys("Admin" +timestamp);
		Select role = new Select(driver.findElement(By.cssSelector("#role_selector")));
        role.selectByVisibleText("Administrator");
		driver.findElement(By.xpath("//input[@value='Create New User']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Login name:']/../div[@class='item']")));
		Thread.sleep(5000);
		String username = driver.findElement(By.xpath("//div[text()='Login name:']/../div[@class='item']")).getText();
		createdadminuserwithsuper = driver.getCurrentUrl();
		createdadminusernamewithsuper = "Admin" +timestamp;
		Reporter.log("Created New Admin User via Super with Name = " +username +" and id = "  +createdadminuserwithsuper, true);
		activationurl();
		activateUser(activationURL);
		Reporter.log("Successfully activated Admin User (Created Via Super) with id = " +createdadminuserwithsuper, true);
	}
}