package com.test.skytap;

import java.awt.AWTException;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class L_PublishedServicesTest {
	BaseFixture session;
	L_Published_Services PS;

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		session = new BaseFixture();
		PS = new L_Published_Services();
		BaseFixture.readbrowser();
		BaseFixture.adminLogin();
	}
	
	@AfterMethod
	public void fail(ITestResult result)
	{
		BaseFixture.takescreenshot(result);
	}

	@Test
	public void addByName() throws InterruptedException, AWTException {
		PS.addByName();
	}

	@Test
	public void addByPort() throws InterruptedException {
		PS.addByPort();
	}

	
	@AfterClass
	public void afterClass() {
		session.quitBrowser();
	}

}
