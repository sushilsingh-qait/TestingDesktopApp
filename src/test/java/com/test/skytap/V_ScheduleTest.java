package com.test.skytap;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class V_ScheduleTest {
	BaseFixture session;
	V_Scheduling Scheduling;
	
	@BeforeClass
	public void beforeClass() throws InterruptedException {
		session = new BaseFixture();
		Scheduling = new V_Scheduling();
		BaseFixture.readbrowser();
		BaseFixture.adminLogin();
	}

	@Test
	public void createScheduleAdmin() throws InterruptedException {
		Scheduling.createScheduleAdmin();
	}

	@Test
	public void createScheduleNonAdmin() throws InterruptedException {
		Scheduling.createScheduleNonAdmin();
	}
	
	@Test
	public void schedulesList() throws InterruptedException {
		Scheduling.schedulesList();
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