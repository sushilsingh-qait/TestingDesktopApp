package com.test.skytap;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class G_PIPTest {
	BaseFixture session;
	G_Public_IP PIP;

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		session = new BaseFixture();
		PIP = new G_Public_IP();
		BaseFixture.readbrowser();
		BaseFixture.adminLogin();
	}

	@Test
	public void fetchPIP() throws InterruptedException {
		PIP.fetchPIP();
	}
	
	@Test
	public void releasePIP() throws InterruptedException {
		PIP.releasePIP();
	}
	
	@AfterMethod
	public void fail(ITestResult result)
	{
		BaseFixture.takescreenshot(result);
	}

	@AfterClass
	public void afterClass() {
		session.quitBrowser();
	}

}
