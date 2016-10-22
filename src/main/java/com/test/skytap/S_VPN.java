package com.test.skytap;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.test.util.PropFileHandler;

public class S_VPN extends BaseFixture
{	
	String pip1 = "";
	String pip2 = "";
	
	String env1 = "";
	String env2 = "";
	
	String env1ip = "";
	String env2ip = "";
	
	String vpn1url = "";
	String vpn2url = "";
	
	String vpn1name = "";
	String vpn2name = "";
	
	String env1name = "";
	String env2name = "";
	
	String Phase1result = "";
	String Phase2result = "";
	String ping = "";

	public void fetchPIP() throws InterruptedException
	{	removeuserlimits();
		Reporter.log(" *** Starting with Fetching Public IPs for VPN TEST ***", true);
		driver.get(PropFileHandler.readProperty("url") +"/ips");
		driver.findElement(By.cssSelector(".add-public-ip-address")).click();
		driver.findElement(By.cssSelector("#MB_content>div>p")).click();
		driver.findElement(By.xpath("//label[text()='"+PropFileHandler.readProperty("VPN1Region")+"']")).click();
		driver.findElement(By.id("add_public_ip_submit")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[contains(text(),'Added public IP address')]")));
		if(driver.findElement(By.xpath("//span[contains(text(),'Added public IP address')]")).isDisplayed())
			Reporter.log("IP Address successfully fetched for "+PropFileHandler.readProperty("VPN1Region"), true);
		else
			Reporter.log("Failed to Fetch IP Address for "+PropFileHandler.readProperty("VPN1Region"), true);
		pip1 = driver.findElement(By.xpath("//td[text()='Unattached']/../td[2][contains(.,'"+PropFileHandler.readProperty("VPN1Region")+"')]/../td[1]")).getText();
		Reporter.log(pip1, true);
		
		driver.get(PropFileHandler.readProperty("url") +"/ips");
		driver.findElement(By.cssSelector(".add-public-ip-address")).click();
		driver.findElement(By.cssSelector("#MB_content>div>p")).click();
		driver.findElement(By.xpath("//label[text()='"+PropFileHandler.readProperty("VPN2Region")+"']")).click();
		driver.findElement(By.id("add_public_ip_submit")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[contains(text(),'Added public IP address')]")));
		if(driver.findElement(By.xpath("//span[contains(text(),'Added public IP address')]")).isDisplayed())
			Reporter.log("IP Address successfully fetched for "+PropFileHandler.readProperty("VPN2Region"), true);
		else
			Reporter.log("Failed to Fetch IP Address for "+PropFileHandler.readProperty("VPN2Region"), true);
		pip2 = driver.findElement(By.xpath("//td[text()='Unattached']/../td[2][contains(.,'"+PropFileHandler.readProperty("VPN2Region")+"')]/../td[1]")).getText();
		Reporter.log(pip2, true);
	}
	
	public void createRunEnviornments() throws InterruptedException 
	{	
		Reporter.log(" *** Starting with Creating Environments for VPN TEST ***", true);
		driver.get(PropFileHandler.readProperty("VPN1template"));
		driver.findElement(By.cssSelector(".configuration")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".vm-screenshot")));
		driver.findElement(By.cssSelector(".edit-environment-name")).click();
		env1name = PropFileHandler.readProperty("VPN1Region") +" Environment " +random;
		driver.findElement(By.cssSelector(".name-field")).clear();
		driver.findElement(By.cssSelector(".name-field")).sendKeys(env1name);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text()='Details successfully updated for this environment.']")));
		driver.findElement(By.cssSelector(".run-configuration")).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(".network-settings")).click();
		driver.findElement(By.cssSelector(".show_vms")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".styled_table")));
		env1ip = driver.findElement(By.xpath("//tbody/tr/td[4]")).getText();
		Reporter.log("IP of - " +env1name +"- is " +env1ip, true);
		driver.findElement(By.cssSelector(".primary-option.back")).click();
		env1 = driver.getCurrentUrl();
		Reporter.log("Successfuly created environment for Region " +PropFileHandler.readProperty("VPN1Region") +"with id " +env1, true);
		
		driver.get(PropFileHandler.readProperty("VPN2template"));
		driver.findElement(By.cssSelector(".configuration")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".vm-screenshot")));
		driver.findElement(By.cssSelector(".edit-environment-name")).click();
		env2name = PropFileHandler.readProperty("VPN2Region") +" Environment " +random;
		driver.findElement(By.cssSelector(".name-field")).clear();
		driver.findElement(By.cssSelector(".name-field")).sendKeys(env1name);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text()='Details successfully updated for this environment.']")));
		driver.findElement(By.cssSelector(".run-configuration")).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(".network-settings")).click();
		driver.findElement(By.cssSelector(".show_vms")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".styled_table")));
		env2ip = driver.findElement(By.xpath("//tbody/tr/td[4]")).getText();
		Reporter.log("IP of - " +env2name +" - is " +env2ip, true);
		driver.findElement(By.cssSelector(".primary-option.back")).click();
		env2 = driver.getCurrentUrl();
		Reporter.log("Successfuly created environment for Region " +PropFileHandler.readProperty("VPN2Region") +" with id " +env2, true);
	}
			
	public void createVPN1() throws InterruptedException 
	{
		Reporter.log(" *** Starting with creation of VPN for " +PropFileHandler.readProperty("VPN1Region") +" ***", true);
		driver.get(PropFileHandler.readProperty("url") +"/vpns/new");
		vpn1name = PropFileHandler.readProperty("VPN1Region") +" VPN " +random;
		driver.findElement(By.cssSelector("#name")).sendKeys(vpn1name);
		driver.findElement(By.cssSelector("#remotePeerIp")).sendKeys(pip2);
		Select selectregion = new Select(driver.findElement(By.cssSelector("#region")));
        selectregion.selectByValue(PropFileHandler.readProperty("VPN1Region"));
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("#localSubnet")).clear();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#localSubnet")).sendKeys(env1ip+"/16");
		driver.findElement(By.cssSelector("#phase1PreSharedKey")).sendKeys("123");
		Thread.sleep(1000);
		driver.findElement(By.xpath(".//input[@type='submit']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".inline-field")));
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@type='text']")).sendKeys(env2ip+"/16");
		driver.findElement(By.xpath(".//input[@type='submit']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".remove-subnet")));
		vpn1url=driver.getCurrentUrl();
		Reporter.log("Successfully created VPN with name = "+vpn1name +" and URL = " +vpn1url, true);
	}
	
	public void createVPN2() throws InterruptedException 
	{
		Reporter.log(" *** Starting with creation of VPN for " +PropFileHandler.readProperty("VPN2Region") +" ***", true);
		driver.get(PropFileHandler.readProperty("url") +"/vpns/new");
		vpn2name = PropFileHandler.readProperty("VPN2Region") +" VPN " +random;
		driver.findElement(By.cssSelector("#name")).sendKeys(vpn2name);
		driver.findElement(By.cssSelector("#remotePeerIp")).sendKeys(pip1);
		Select selectregion = new Select(driver.findElement(By.cssSelector("#region")));
        selectregion.selectByValue(PropFileHandler.readProperty("VPN2Region"));
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("#localSubnet")).clear();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#localSubnet")).sendKeys(env2ip+"/16");
		driver.findElement(By.cssSelector("#phase1PreSharedKey")).sendKeys("123");
		Thread.sleep(1000);
		driver.findElement(By.xpath(".//input[@type='submit']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".inline-field")));
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@type='text']")).sendKeys(env1ip+"/16");
		driver.findElement(By.xpath(".//input[@type='submit']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".remove-subnet")));
		driver.findElement(By.cssSelector(".enable-vpn")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".disable-vpn")));
		vpn2url=driver.getCurrentUrl();
		Reporter.log("Successfully created VPN with name = "+vpn2name +" and URL = " +vpn2url, true);
	}
	
	public void connectVPNs() throws InterruptedException 
	{
		Reporter.log(" *** Starting with connecting VPNs to Environment ***", true);
		driver.get(env1);
		driver.findElement(By.cssSelector(".network-settings")).click();
		driver.findElement(By.cssSelector(".attach_vpn")).click();
		driver.findElement(By.xpath("//td[contains(text(),'"+random+"')]/../td[@class='action']/span[@class='attach_to_vpn']/a")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'"+random+"')]/../td[@class='action']/span[@class='attach_to_vpn']/a[@class='detach']")));
		driver.findElement(By.xpath("//td[contains(text(),'"+random+"')]/../td[@class='action']/span[@class='connect_to_vpn']/a[@class='connect']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'"+random+"')]/../td[@class='action']/span[@class='connect_to_vpn']/a[@class='disconnect']")));
		driver.findElement(By.cssSelector(".simplemodal-close")).click();
		Reporter.log("Successfully connected VPN - " +vpn1name + " - to environment - "+env1name, true);
		
		driver.get(env2);
		driver.findElement(By.cssSelector(".network-settings")).click();
		driver.findElement(By.cssSelector(".attach_vpn")).click();
		driver.findElement(By.xpath("//td[contains(text(),'"+random+"')]/../td[@class='action']/span[@class='attach_to_vpn']/a[@class='attach']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'"+random+"')]/../td[@class='action']/span[@class='attach_to_vpn']/a[@class='detach']")));
		driver.findElement(By.xpath("//td[contains(text(),'"+random+"')]/../td[@class='action']/span[@class='connect_to_vpn']/a[@class='connect']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'"+random+"')]/../td[@class='action']/span[@class='connect_to_vpn']/a[@class='disconnect']")));
		driver.findElement(By.cssSelector(".simplemodal-close")).click();
		Reporter.log("Successfully connected VPN- " +vpn2name + " - to environment - "+env2name, true);
	}
	
	public void testVPN1() throws InterruptedException 
	{
		Reporter.log(" *** Testing VPN - " +vpn1name +" ***", true);
		driver.get(vpn1url);
		driver.findElement(By.cssSelector(".action-button.quiet.test-vpn")).click();
		driver.findElement(By.cssSelector("#vpnTestAddress")).sendKeys(env2ip);
		driver.findElement(By.xpath(".//*[@value='Test Connection']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Result']")));
		Phase1result = driver.findElement(By.xpath("//*[text()='Phase 1:']/../td[2]")).getText();
		Phase2result = driver.findElement(By.xpath("//*[text()='Phase 2:']/../td[2]")).getText();
		ping = driver.findElement(By.xpath("//*[text()='Ping remote address:']/../td[2]")).getText();
		Reporter.log(PropFileHandler.readProperty("VPN1Region")+ " VPN Results:", true);
		Reporter.log("Phase 1 result " +Phase1result, true);
		Reporter.log("Phase 2 result " +Phase2result, true);
		Reporter.log("Remote Ping Address " +ping, true);
		driver.findElement(By.cssSelector(".cancel")).click();
		driver.findElement(By.cssSelector(".enable-vpn")).click();
	}
		
	public void testVPN2() throws InterruptedException 
	{
		Reporter.log(" *** Testing VPN - " +vpn2name +" ***", true);
		driver.get(vpn2url);
		driver.findElement(By.cssSelector(".disable-vpn")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='action-button quiet test-vpn']")));
		driver.findElement(By.cssSelector(".test-vpn")).click();
		driver.findElement(By.cssSelector("#vpnTestAddress")).sendKeys(env1ip);
		driver.findElement(By.xpath(".//*[@value='Test Connection']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Result']")));
		Phase1result = driver.findElement(By.xpath("//*[text()='Phase 1:']/../td[2]")).getText();
		Phase2result = driver.findElement(By.xpath("//*[text()='Phase 2:']/../td[2]")).getText();
		ping = driver.findElement(By.xpath("//*[text()='Ping remote address:']/../td[2]")).getText();
		Reporter.log(PropFileHandler.readProperty("VPN2Region") + " VPN Results:", true);
		Reporter.log("Phase 1 result " +Phase1result, true);
		Reporter.log("Phase 2 result " +Phase2result, true);
		Reporter.log("Remote Ping Address " +ping, true);
		driver.findElement(By.cssSelector(".cancel")).click();
	}
	
	public void deleteVPN() throws InterruptedException 
	{
		Reporter.log(" *** Deleting VPNs ***", true);
		driver.get(vpn1url +"?view=networks");
		driver.findElement(By.cssSelector(".detach")).click();
		driver.findElement(By.cssSelector(".submit-ok")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".no-resource-description")));
		driver.navigate().refresh();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".delete-vpn")));
		driver.findElement(By.cssSelector(".delete-vpn")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@value='Delete VPN']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".msg.block.success.hideable")));
		driver.findElement(By.cssSelector(".msg.block.success.hideable>a")).click();
		Reporter.log("Successfully Deleted VPN - " +vpn1name, true);
		
		driver.get(vpn2url +"?view=networks");
		driver.findElement(By.cssSelector(".detach")).click();
		driver.findElement(By.cssSelector(".submit-ok")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".no-resource-description")));
		driver.navigate().refresh();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".delete-vpn")));
		driver.findElement(By.cssSelector(".delete-vpn")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@value='Delete VPN']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".msg.block.success.hideable")));
		driver.findElement(By.cssSelector(".msg.block.success.hideable>a")).click();
		Reporter.log("Successfully Deleted VPN - " +vpn2name, true);
	}
	
	public void deleteEnvironment() throws InterruptedException 
	{
		Reporter.log(" *** Deleting Environments Linked to VPNs ***", true);
		driver.get(env1);
		driver.findElement(By.cssSelector(".delete")).click();
		driver.findElement(By.xpath("//input[@value='Delete Environment']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".msg.block.success.hideable")));
		driver.findElement(By.cssSelector(".msg.block.success.hideable>a")).click();
		Reporter.log("Successfully deleted environment - " +env1name, true);
		
		driver.get(env2);
		driver.findElement(By.cssSelector(".delete")).click();
		driver.findElement(By.xpath("//input[@value='Delete Environment']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".msg.block.success.hideable")));
		driver.findElement(By.cssSelector(".msg.block.success.hideable>a")).click();
		Reporter.log("Successfully deleted environment - " +env2name, true);
	}
	
	public void releasePIP() throws InterruptedException
	{
		Reporter.log(" *** Releasing PIPs ***", true);
		driver.get(PropFileHandler.readProperty("url") +"/ips");
		driver.findElement(By.xpath("//strong[text()='"+pip1+"']/../../td[@class='action_button']/a/img")).click();
		Thread.sleep(2000);
        driver.switchTo().alert();
        driver.switchTo().alert().accept();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Public IP Address successfully released']")));
		Reporter.log("Successfully released PIP "+pip1 +" for " +PropFileHandler.readProperty("VPN1Region") +" Region", true);

		driver.get(PropFileHandler.readProperty("url") +"/ips");
		driver.findElement(By.xpath("//strong[text()='"+pip2+"']/../../td[@class='action_button']/a/img")).click();
		Thread.sleep(2000);
        driver.switchTo().alert();
        driver.switchTo().alert().accept();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Public IP Address successfully released']")));
		Reporter.log("Successfully released PIP " +pip2 +" for " +PropFileHandler.readProperty("VPN2Region") +" Region", true);
	}	
}