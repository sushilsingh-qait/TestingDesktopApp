package com.test.skytap;

import java.awt.AWTException;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class X_SupportRequestTest {
	BaseFixture session;
	X_Support_Request_Form support;

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		session = new BaseFixture();
		support = new X_Support_Request_Form();
		BaseFixture.readbrowser();
		BaseFixture.adminLogin();
	}

	@Test
	public void clickSupportLink() throws InterruptedException, AWTException {
		support.clickSupportLink();
	}

	@Test
	public void submitForm() throws InterruptedException, AWTException {
		support.submitForm();
	}
	
	@Test
	public void caseGenerateVerify() throws InterruptedException, AWTException {
		support.caseGenerateVerify();
	}

	@AfterMethod
	public void fail(ITestResult result)
	{
		BaseFixture.takescreenshot(result);
	}
	
	@AfterClass
	public void afterClass() throws InterruptedException {
		session.quitBrowser();
	}
}