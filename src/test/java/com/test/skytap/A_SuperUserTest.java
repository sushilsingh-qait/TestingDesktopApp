package com.test.skytap;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class A_SuperUserTest {
	BaseFixture session;
	static A_SuperUser superuser;

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		session = new BaseFixture();
		superuser = new A_SuperUser();
		BaseFixture.readbrowser();
		BaseFixture.superLogin();
	}
	
	@AfterMethod
	public void fail(ITestResult result)
	{
		BaseFixture.takescreenshot(result);
	}

	@Test
	public static void createCustomer() throws InterruptedException, AWTException {
		superuser.createCustomer();
	}

	@Test
	public void createAdminUser() throws InterruptedException, IOException {
		superuser.createAdminUser();
	}
	
	@AfterClass
	public void afterClass() {
		session.quitBrowser();
	}
}
