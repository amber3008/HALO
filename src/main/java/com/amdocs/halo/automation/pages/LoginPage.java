package com.amdocs.halo.automation.pages;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
//
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.ExpectedCondition;
//import org.openqa.selenium.support.ui.ExpectedConditions;

import com.amdocs.halo.automation.base.LoggerGen;
import com.amdocs.halo.automation.base.SeleniumBase;


public class LoginPage extends SeleniumBase
{
	
	WebDriver driver;
	public static Logger logger = null;
	
	
	@FindBy(id = "GloATTUID")
	public WebElement username;
	
	@FindBy(id = "GloPassword")
	public WebElement password;
	
	@FindBy(id = "GloPasswordSubmit")
	public WebElement loginbtn;
	
	/*@FindBy(xpath = "//a[@id='reset_link']")
	public WebElement frgtpasswrd;
	
	@FindBy(xpath = "//div[@id='welcomeDivId']")
	WebElement welcmtxt;
	
	@FindBy(xpath = "//div[@id='successDivId']")
	WebElement loginsuccess; */
	
	@FindBy(xpath = "//input[@id='successButtonId']")
	WebElement continueButton;
	
	@FindBy(xpath = "/html/body/app-root/div/app-main-dashboard/div/div/div/div/p[1]")
	WebElement welcometxt2;
	
	public LoginPage(WebDriver driver) throws FileNotFoundException, IOException 
	{
		//this.di=di;
		this.driver = driver;
		PageFactory.initElements(driver, this);
		logger=LoggerGen.logGen(LoginPage.class.getName());
	}
	
	public String validateLoginPageTitle() 
	{
		System.out.println(driver);
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		return driver.getTitle();
	}
	
	public void openLoginPage() throws InterruptedException
	{
		String un =prop.getProperty("username");
		username.sendKeys(un);
		String pw = prop.getProperty("password");
		byte[] decodedBytes = Base64.decodeBase64(pw);		
		String pwd = new String(decodedBytes);
		password.sendKeys(pwd);
		loginbtn.click();
		Thread.sleep(4000);
		continueButton.click();
	}
	
	public HomePage toHomePage() 
	{		
		return new HomePage(driver);
	}
	

	


}
