package com.test.skytap;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class D_ProjectsTest {
	BaseFixture session;
	D_Projects projects;

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		session = new BaseFixture();
		projects = new D_Projects();
		BaseFixture.readbrowser();
		BaseFixture.adminLogin();
	}

	@Test
	public void createProject() throws InterruptedException {
		projects.createProject();
	}
	
	@Test
	public void addTemplateToProject() throws InterruptedException {
		projects.addTemplateToProject();
	}

	@Test
	public void addEnvironmentToProject() throws InterruptedException {
		projects.addEnvironmentToProject();
	}
	
	@Test
	public void addAssetToProject() throws InterruptedException {
		projects.addAssetToProject();
	}
	
	@Test
	public void addUserToProject() throws InterruptedException {
		projects.addUserToProject();
	}
	
	@Test
	public void deleteProject() throws InterruptedException {
		projects.deleteProject();
	}
	
	@AfterMethod
	public void fail(ITestResult result)
	{
		BaseFixture.takescreenshot(result);
	}
	
	@AfterClass
	public void afterClass() 
	{
		session.quitBrowser();
	}

}
