package com.test.skytap;

import java.awt.AWTException;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.util.PropFileHandler;

public class H_QuotaTest extends BaseFixture{
	BaseFixture session;
	H_Quota_Validation quota;

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		session = new BaseFixture();
		quota = new H_Quota_Validation();
		BaseFixture.readbrowser();
		BaseFixture.adminLogin();
		removeuserlimits();
		removeCustomerlimits();
	}

	@Test
	public void createQuotaEnvironment() throws InterruptedException, AWTException {
		quota.createQuotaEnvironment();
	}

	@Test
	public void customerSvmQuotaOnVmRun() throws InterruptedException, AWTException {
		quota.customerSvmQuotaOnVmRun();		
	}
	
	@Test
	public void customerStorageQuotaAddTempToEnv() throws InterruptedException, AWTException {
		quota.customerStorageQuotaAddTempToEnv();
	}
	
	@Test
	public void customerStorageQuotaCreateTempFromEnv() throws InterruptedException, AWTException {		
		quota.customerStorageQuotaCreateTempFromEnv();
	}

	@Test
	public void customerStorageQuotaAssetUpload() throws InterruptedException, AWTException {
		quota.customerStorageQuotaAssetUpload();
	}

	@Test
	public void customerStorageQuotaAddVM() throws InterruptedException, AWTException {
		quota.customerStorageQuotaAddVM();
	}

	@Test
	public void customerSvmHourVmAutoSuspend() throws InterruptedException, AWTException {
		quota.customerSvmHourVmAutoSuspend();
	}

	@Test
	public void customerSvmHourQuotaOnVmRun() throws InterruptedException, AWTException {
		quota.customerSvmHourQuotaOnVmRun();
	}

	@Test
	public void userStorageQuotaAddTempToEnv() throws InterruptedException, AWTException {
		quota.userStorageQuotaAddTempToEnv();		
	}

	@Test
	public void userSvmHourQuotaOnVmRun() throws InterruptedException, AWTException {
		quota.userSvmHourQuotaOnVmRun();		
	}

	@Test
	public void userSvmQuotaOnVmRun() throws InterruptedException, AWTException {
		quota.userSvmQuotaOnVmRun();		
	}

	@Test
	public void userStorageQuotaAssetUpload() throws InterruptedException, AWTException {
		quota.userStorageQuotaAssetUpload();		
	}

	@Test
	public void userStorageQuotaAddVM() throws InterruptedException, AWTException {
		quota.userStorageQuotaAddVM();		
	}

	@Test
	public void userSvmHourVmAutoSuspend() throws InterruptedException, AWTException {
		quota.userSvmHourVmAutoSuspend();		
	}

	@AfterMethod
	public void fail(ITestResult result)
	{
		BaseFixture.takescreenshot(result);
	}
	
	@AfterMethod
	public void removeLimits() throws InterruptedException
	{
		removeuserlimits();
		removeCustomerlimits();
	}
	
	@AfterClass
	public void afterClass() throws InterruptedException {
		removeuserlimits();
		removeCustomerlimits();
		session.quitBrowser();
	}
}
