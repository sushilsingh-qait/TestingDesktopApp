package com.test.skytap;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class R_ConnectivityTest {
	BaseFixture session;
	R_Connectivity_Tool connectivity;

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		session = new BaseFixture();
		connectivity = new R_Connectivity_Tool();
		BaseFixture.readbrowser();
		BaseFixture.superLogin();
	}
	
	@AfterMethod
	public void fail(ITestResult result)
	{
		BaseFixture.takescreenshot(result);
	}
	
	@Test
	public void connectivity() throws InterruptedException {
		connectivity.connectivity();
	}
	
	@AfterClass
	public void afterClass() {
		session.quitBrowser();
	}
}