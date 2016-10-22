package com.test.skytap;

import java.awt.AWTException;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Q_ImportExportTest {
	BaseFixture session;
	Q_Import_Export ImportExport;
	
	@BeforeClass
	public void beforeClass() throws InterruptedException {
		session = new BaseFixture();
		ImportExport = new Q_Import_Export();
		BaseFixture.readbrowser();
		BaseFixture.adminLogin();
	}

	@Test
	public void importVM() throws InterruptedException, AWTException {
		ImportExport.importVM();
	}

	@Test
	public void exportVM() throws InterruptedException {
		ImportExport.exportVM();
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