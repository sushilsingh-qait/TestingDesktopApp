package com.test.skytap;

import java.awt.AWTException;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class B_AdminUserTest {
	BaseFixture session;
	static B_AdminUser admin;

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		session = new BaseFixture();
		admin = new B_AdminUser();
		BaseFixture.readbrowser();
		BaseFixture.adminLogin();
	}

	@Test
	public static void createAdminUser() throws InterruptedException, AWTException {
		admin.createAdminUser();
	}

	@Test
	public void createNonAdminUser() throws InterruptedException {
		admin.createNonAdminUser();
	}

	@Test
	public void deleteNonAdminUser() throws InterruptedException {
		admin.deleteNonAdminUser();
	}
	
	@Test
	public void deleteAdminUser() throws InterruptedException {
		admin.deleteAdminUser();
	}
	

	@Test
	public void deleteCustomer() throws InterruptedException {
		admin.deleteCustomer();
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