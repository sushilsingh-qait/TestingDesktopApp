package com.test.skytap;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class S_VPNTest {
	BaseFixture session;
	S_VPN vpn;
	
	@BeforeClass
	public void beforeClass() throws InterruptedException {
		session = new BaseFixture();
		vpn = new S_VPN();
		BaseFixture.readbrowser();
		BaseFixture.adminLogin();
	}
	
	@AfterMethod
	public void fail(ITestResult result)
	{
		BaseFixture.takescreenshot(result);
	}

	@Test
	public void fetchPIP() throws InterruptedException {
		vpn.fetchPIP();
	}

	@Test
	public void createRunEnviornments() throws InterruptedException {
		vpn.createRunEnviornments();
	}

	@Test
	public void createVPN1() throws InterruptedException {
		vpn.createVPN1();
	
	}

	@Test
	public void createVPN2() throws InterruptedException {
		vpn.createVPN2();
	
	}

	@Test
	public void connectVPNs() throws InterruptedException {
		vpn.connectVPNs();
	
	}
	
	@Test
	public void testVPN1() throws InterruptedException {
		vpn.testVPN1();
	
	}

	@Test
	public void testVPN2() throws InterruptedException {
		vpn.testVPN2();
	
	}
	
	@Test
	public void deleteVPN() throws InterruptedException {
		vpn.deleteVPN();
	
	}
	
	@Test
	public void deleteEnvironment() throws InterruptedException {
		vpn.deleteEnvironment();
	
	}

	@Test
	public void releasePIP() throws InterruptedException {
		vpn.releasePIP();
	
	}

	
	
	@AfterClass
	public void afterClass() 
	{
		session.quitBrowser();
	}
}