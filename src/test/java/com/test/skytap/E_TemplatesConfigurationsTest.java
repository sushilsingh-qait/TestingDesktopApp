package com.test.skytap;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class E_TemplatesConfigurationsTest {
	BaseFixture session;
	E_TemplatesConfigurations tempconfig;

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		session = new BaseFixture();
		tempconfig = new E_TemplatesConfigurations();
		BaseFixture.readbrowser();
		BaseFixture.adminLogin();
	}

	@Test
	public void searchTemplates() throws InterruptedException {
		tempconfig.searchTemplates();
	}
	
	@Test
	public void createTemplate() throws InterruptedException {
		E_TemplatesConfigurations.createTemplate();
	}
	
	@Test
	public void createAndRunVmInEnvironment() throws InterruptedException {
		tempconfig.createAndRunVmInEnvironment();
	}

	@Test
	public void addTemplateToEnvironment() throws InterruptedException {
		tempconfig.addTemplateToEnvironment();
	}
	
	@Test
	public void insertISO() throws InterruptedException {
		tempconfig.insertISO();
	}
	
	@Test
	public void suspendVM() throws InterruptedException {
		tempconfig.suspendVM();
	}
	
	@Test
	public void shutDownVM() throws InterruptedException {
		tempconfig.shutDownVM();
	}
	
	@Test
	public void powerOffVM() throws InterruptedException {
		tempconfig.powerOffVM();
	}
	
	@Test
	public void runEnv() throws InterruptedException {
		tempconfig.runEnv();
	}
	
	@Test
	public void shutDownEnv() throws InterruptedException {
		tempconfig.shutDownEnv();
	}
	
	@Test
	public void suspendEnv() throws InterruptedException {
		tempconfig.suspendEnv();
	}
	
	@Test
	public void powerOffEnv() throws InterruptedException {
		tempconfig.powerOffEnv();
	}
	
	@Test
	public void publishVM() throws InterruptedException {
		tempconfig.publishVM();
	}
	
	@Test
	public void saveEnv() throws InterruptedException {
		tempconfig.saveEnv();
	}
	
	@Test
	public void deleteVmAndEnv() throws InterruptedException {
		tempconfig.deleteVmAndEnv();
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
