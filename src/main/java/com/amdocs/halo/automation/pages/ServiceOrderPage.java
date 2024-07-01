package com.amdocs.halo.automation.pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amdocs.halo.automation.base.LoggerGen;
import com.amdocs.halo.automation.base.SeleniumBase;

public class ServiceOrderPage 
{
	Actions action;	
	WebDriverWait wait;
	public static Logger logger = null;
	
	public ServiceOrderPage(WebDriver driver) throws FileNotFoundException, IOException 
	{
		SeleniumBase.driver = driver;
		PageFactory.initElements(driver, this);
		action = new Actions(driver);
		logger=LoggerGen.logGen(ServiceOrderPage.class.getName());
	}
	
	@FindBy(xpath = "//span[contains(text(),'Order Details')]")
	public WebElement orderDetailstab;
	
	@FindBy(xpath = "//p[contains(text(),'CLCI')]")
	public WebElement clciElement;
	
	@FindBy(xpath = "//p[contains(text(),'CLCI')]//ancestor::td//following-sibling::td//child::p")
	public WebElement clciValue;
	
	
	public void getClciValue() throws InterruptedException
	{
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.id("clciElement")));
		orderDetailstab.click();
		Thread.sleep(4000);
		action.moveToElement(clciElement).build().perform();
		Thread.sleep(6000);
		logger.info(clciValue.getText().toString());
	}
}
