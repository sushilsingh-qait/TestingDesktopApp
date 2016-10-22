package com.test.skytap;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import com.test.util.PropFileHandler;

public class B_AdminUser extends BaseFixture 
{
	public void createAdminUser() throws InterruptedException 
	{
		fetchTemplate();userinfo();removeuserlimits();
		Reporter.log(" *** Creating Admin User ***", true);
		driver.get(PropFileHandler.readProperty("url") +"/users/new");
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
		createdadminuser = driver.getCurrentUrl();
		Reporter.log("Created New Admin User with Name = " +username +" and id = "  +createdadminuser, true);
		activationurl();
		activateUser(activationURL);
		Reporter.log(" Successfully activated Admin User (Created Via Admin) with id = " +createdadminuser,true);
		driver.get(PropFileHandler.readProperty("url") +"/login");
		BaseFixture.adminLogin();
	}
	
	public void createNonAdminUser() throws InterruptedException 
	{
		Reporter.log(" *** Creating Non-Admin User ***", true);
		driver.get(PropFileHandler.readProperty("url") +"/users/new");
		driver.findElement(By.cssSelector("#user_email")).clear();
		driver.findElement(By.cssSelector("#user_email")).sendKeys("automation.skytap@gmail.com");
		driver.findElement(By.cssSelector("#user_login_name")).clear();
        String timestamp = Long.toString(System.currentTimeMillis());
		driver.findElement(By.cssSelector("#user_login_name")).sendKeys("NonAdmin" +timestamp);
		Select role = new Select(driver.findElement(By.cssSelector("#role_selector")));
        role.selectByVisibleText("Restricted user");
		driver.findElement(By.xpath("//input[@value='Create New User']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Login name:']/../div[@class='item']")));
		Thread.sleep(5000);
		String username = driver.findElement(By.xpath("//div[text()='Login name:']/../div[@class='item']")).getText();
		creatednonadminuser = driver.getCurrentUrl();
		Reporter.log("Created Non-Admin User with Name = " +username +" and id = "  +creatednonadminuser, true);
		activationurl();
		passwordValidation();
		activateUser(activationURL);
		Reporter.log(" Successfully activated Non - Admin User (Created Via Admin) with id = " +creatednonadminuser,true);
		driver.get(PropFileHandler.readProperty("url") +"/login");
		BaseFixture.adminLogin();
	}
	
	public void passwordValidation() throws InterruptedException 
	{
		Reporter.log(" *** Activating User with short Password ***", true);
		driver.get(activationURL);
		driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("qai");
		driver.findElement(By.xpath("//input[@id='confirm_password']")).sendKeys("qai");
		driver.findElement(By.cssSelector(".action-button")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text(),'Login password must be at least 6 characters')]")));
	}
	
	public void deleteNonAdminUser() throws InterruptedException 
	{
		Reporter.log(" *** Deleting Non-Admin User ***", true);
		driver.get(creatednonadminuser);
		driver.findElement(By.cssSelector(".delete")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		Reporter.log("Successfully deleted Non admin user with id = " +creatednonadminuser, true);
	}	
	
	public void deleteAdminUser() throws InterruptedException 
	{
		Reporter.log(" *** Deleting Admin User ***", true);
		driver.get(createdadminuser);
		driver.findElement(By.cssSelector(".delete")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		Reporter.log("Successfully deleted admin user with id = " +createdadminuser, true);
	}
	
	public void deleteCustomer() throws InterruptedException 
	{
		driver.get(PropFileHandler.readProperty("url") +"/login");
		BaseFixture.superLogin();
		driver.get(newCustomerurl);
		Select status = new Select(driver.findElement(By.cssSelector("#customer_activity_status")));
		status.selectByVisibleText("Inactive");
		driver.findElement(By.xpath("//input[@value='Update']")).click();
		driver.findElement(By.cssSelector(".primary-option.delete")).click();
		driver.findElement(By.xpath("//input[@value='Delete Customer and all resources']")).click();
		Reporter.log("Successfully deleted customer with name = " +customername +"   and id = " +newCustomerurl, true);
	}
}