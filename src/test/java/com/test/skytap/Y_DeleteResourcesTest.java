package com.test.skytap;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Y_DeleteResourcesTest {
	BaseFixture session;
	Y_Delete_Unused_Resources delete;
	
	@BeforeClass
	public void beforeClass() throws InterruptedException {
		session = new BaseFixture();
		delete = new Y_Delete_Unused_Resources();
		BaseFixture.readbrowser();
		BaseFixture.adminLogin();
	}
	
	@AfterMethod
	public void fail(ITestResult result)
	{
		BaseFixture.takescreenshot(result);
	}
	
	@Test
	public void deleteAllExports() throws InterruptedException {
		delete.deleteAllExports();
	}
	
	@Test
	public void deleteAllImports() throws InterruptedException {
		delete.deleteAllImports();
	}

	@Test
	public void deleteAllEnvironments() throws InterruptedException {
		delete.deleteAllEnvironments();
	}

	@Test
	public void deleteAllTemplates() throws InterruptedException {
		delete.deleteAllTemplates();
	}
	
	@AfterClass
	public void afterClass() {
		session.quitBrowser();
	}
}