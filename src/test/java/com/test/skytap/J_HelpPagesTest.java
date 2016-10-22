package com.test.skytap;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class J_HelpPagesTest {
	BaseFixture session;
	J_Help_Pages Help;

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		session = new BaseFixture();
		Help = new J_Help_Pages();
		BaseFixture.readbrowser();
		BaseFixture.superLogin();
	}

	@Test
	public void superTab() throws InterruptedException {
		Help.superTab();
	}
	
	@Test
	public void adminTab() throws InterruptedException {
		Help.adminTab();
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