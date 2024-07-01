package com.amdocs.halo.automation.pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.amdocs.halo.automation.base.SeleniumBase;

public class HomePage extends SeleniumBase
{
	Actions action;	
	
	@FindBy(xpath = "//*[contains(text(),'Quick Search')]")
	public WebElement quickSearchButton;
	
	@FindBy(xpath = "//input[@placeholder='Service Order ID']")
	WebElement orderID;
	
	@FindBy(xpath = "//button[contains(text(),'Search')]")
	WebElement searchBtn;
	
	@FindBy(xpath = "//tr[@class=\"tnoexport\"]//following-sibling::tr//child::td//child::span")
	WebElement orderBtn;
			
	public HomePage(WebDriver driver) 
	{
		SeleniumBase.driver = driver;
		PageFactory.initElements(driver, this);
		action = new Actions(driver);
	}
	

	public ServiceOrderPage searchOrderNumber(String orderId) throws InterruptedException, FileNotFoundException, IOException
	{
		quickSearchButton.click();
		Thread.sleep(5000);
		orderID.sendKeys(orderId);
		Thread.sleep(5000);
		searchBtn.click();
		Thread.sleep(7000);
		if(orderBtn.isDisplayed())
		{
			orderBtn.click();
		}
		return new ServiceOrderPage(driver); 
	}
}
