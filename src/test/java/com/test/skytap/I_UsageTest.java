package com.test.skytap;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class I_UsageTest extends BaseFixture{
	BaseFixture session;
	I_Usage usage;

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		session = new BaseFixture();
		usage = new I_Usage();
		BaseFixture.readbrowser();
		BaseFixture.adminLogin();
		removeuserlimits();
	}

	@Test
	public void svmUsage() throws InterruptedException {
		usage.svmUsage();
	}

	@Test
	public void storageUsage() throws InterruptedException {
		usage.storageUsage();
	}
	
	@Test
	public void svmNoGroupingCurrentMonth() throws InterruptedException {
		usage.svmNoGroupingCurrentMonth();
	}
	
	@Test
	public void storageNoGroupingCurrentMonth() throws InterruptedException {
		usage.storageNoGroupingCurrentMonth();
	}
	
	@Test
	public void svmNoGroupingLastMonth() throws InterruptedException {
		usage.svmNoGroupingLastMonth();
	}
	
	
	@Test
	public void storageNoGroupingLastMonth() throws InterruptedException {
		usage.storageNoGroupingLastMonth();
	}
	
	@Test
	public void svmUsageSuper() throws InterruptedException {
		usage.svmUsageSuper();
	}
	
	@Test
	public void storageUsageSuper() throws InterruptedException {
		usage.storageUsageSuper();
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