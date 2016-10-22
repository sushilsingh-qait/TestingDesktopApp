package com.test.skytap;

import java.awt.AWTException;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class C_AssetsTest {
	BaseFixture session;
	C_Assets Assets;

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		session = new BaseFixture();
		Assets = new C_Assets();
		BaseFixture.readbrowser();
		BaseFixture.adminLogin();
	}

	@Test
	public void uploadAsset() throws InterruptedException, AWTException {
		Assets.uploadAsset();
	}

	@Test
	public void navigatehome() throws InterruptedException {
		Assets.navigatehome();
	}
	
	@Test
	public void deleteAsset() throws InterruptedException {
		Assets.deleteAsset();
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
