package com.test.skytap;

import java.awt.AWTException;

import org.openqa.selenium.Alert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class K_SharingPortalTest extends BaseFixture{
	BaseFixture session;
	K_Sharing_Portal portal;

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		session = new BaseFixture();
		portal = new K_Sharing_Portal();
		BaseFixture.readbrowser();
		BaseFixture.adminLogin();
	}

	@Test
	public void createSharingPortal() throws InterruptedException, AWTException {
		portal.createSharingPortal();
	}

	@Test
	public void noPasswordPortalSingleVM() throws InterruptedException, AWTException {
		portal.noPasswordPortalSingleVM();
	}
	
	@Test
	public void passwordPortalSingleVM() throws InterruptedException, AWTException {
		portal.passwordPortalSingleVM();
	}
	
	@Test
	public void AccessGMTSingleVM() throws InterruptedException, AWTException {
		portal.AccessGMTSingleVM();
	}

	@Test
	public void AccessISTSingleVM() throws InterruptedException, AWTException {
		portal.AccessISTSingleVM();
	}

	@Test
	public void powerActionsSingleVM() throws InterruptedException, AWTException {
		portal.powerActionsSingleVM();
	}

	@Test
	public void noPasswordPortalMultipleVM() throws InterruptedException, AWTException {
		portal.noPasswordPortalMultipleVM();
	}

	@Test
	public void passwordPortalMultipleVM() throws InterruptedException, AWTException {
		portal.passwordPortalMultipleVM();
	}

	@Test
	public void AccessGMTMultipleVM() throws InterruptedException, AWTException {
		portal.AccessGMTMultipleVM();
	}

	@Test
	public void AccessISTMultipleVM() throws InterruptedException, AWTException {
		portal.AccessISTMultipleVM();
	}

	@Test
	public void powerActionsMultipleVM() throws InterruptedException, AWTException {
		portal.powerActionsMultipleVM();
	}

	@AfterMethod
	public void fail(ITestResult result)
	{
		BaseFixture.takescreenshot(result);
	}
	
	@AfterMethod
	public void clickLeavePageOnPopUp()
	{
		try{Alert alert = driver.switchTo().alert();
		alert.accept();}catch(Exception E){}
	}
	
	@AfterClass
	public void afterClass() throws InterruptedException {
		session.quitBrowser();
	}
}