package com.amdocs.halo.automation.steps;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.amdocs.halo.automation.base.LoggerGen;
import com.amdocs.halo.automation.base.SeleniumBase;
import com.amdocs.halo.automation.main.OCXOrderStatus;
import com.amdocs.halo.automation.main.TriggerNotifyRequest;
import com.amdocs.halo.automation.pages.HomePage;
import com.amdocs.halo.automation.pages.LoginPage;
import com.amdocs.halo.automation.pages.ServiceOrderPage;

//import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OrderSearchSeleniumSteps 
{
	WebDriver driver;
   public static String envName;
	public static Logger logger = null;
	HomePage hp;
	SeleniumBase sb;
	LoginPage lp;
	ServiceOrderPage sop;
	int hasIpflex1 =0;
	int hasIpflex2 =0;
	int hasIpflex3 =0;
	int hasIpflex4 =0;
	int hasIpflex5 =0;
	int isAvpnSite=0;
	
	public OrderSearchSeleniumSteps() throws FileNotFoundException, IOException
	{
		logger=LoggerGen.logGen(OrderSearchSeleniumSteps.class.getName());	
	}
	
	@Given("^user opens browser \"([^\"]*)\"$")
	public void user_opens_browser(String env) throws Exception 
	{
		envName=System.getProperty("enviroment");
		if(hasIpflex1==0)
		{
			logger.info("=========================Opening Browser==============================");
			sb= new SeleniumBase();
			driver=sb.initialization(envName);
			logger.info("=========================Opened Browser==============================");
			hasIpflex1++;
		}
		else if(hasIpflex1==1 && TriggerNotifyRequest.typeOfOrder.equalsIgnoreCase("IPFLEXMIS"))
		{
			logger.info("=========================Opening Browser==============================");
			sb= new SeleniumBase();
			driver=sb.initialization(envName);
			logger.info("=========================Opened Browser==============================");
		}
		else if(hasIpflex1==1 && TriggerNotifyRequest.typeOfOrder.equalsIgnoreCase("AVPN"))
		{
			logger.info("=========================Opening Browser==============================");
			sb= new SeleniumBase();
			driver=sb.initialization(envName);
			logger.info("=========================Opened Browser==============================");
		}
		
	}

	@Given("^user is on login page$")
	public void user_is_on_login_page() throws Exception 
	{
		if(hasIpflex2==0)
		{
			lp= new LoginPage(driver);
			lp.openLoginPage();
			hasIpflex2++;
		}
		else if(hasIpflex2==1 && TriggerNotifyRequest.typeOfOrder.equalsIgnoreCase("IPFLEXMIS"))
		{
			lp= new LoginPage(driver);
			lp.openLoginPage();
		}
		else if(hasIpflex2==1 && TriggerNotifyRequest.typeOfOrder.equalsIgnoreCase("AVPN"))
		{
			lp= new LoginPage(driver);
			lp.openLoginPage();
		}
	}

	@Given("^user login into HALO OMX GUI$")
	public void user_login_into_HALO_OMX_GUI() throws Exception 
	{
		
	}

	@Given("^user is on Home Page$")
	public void user_is_on_Home_Page() throws Exception 
	{
		
		if(hasIpflex3==0)
		{
			hp=new HomePage(driver); 
			hp = lp.toHomePage();
			hasIpflex3++;
		}
		else if(hasIpflex3==1 && TriggerNotifyRequest.typeOfOrder.equalsIgnoreCase("IPFLEXMIS"))
		{
			hp=new HomePage(driver); 
			hp = lp.toHomePage();
		}
		else if(hasIpflex3==1 && TriggerNotifyRequest.typeOfOrder.equalsIgnoreCase("AVPN"))
		{
			hp=new HomePage(driver); 
			hp = lp.toHomePage();
		}
	}

	@When("^user clicks on Search dropdown in Order Search Tab$")
	public void user_clicks_on_Search_dropdown_in_Order_Search_Tab() throws Exception 
	{
		sop= new ServiceOrderPage(driver);
		String misOrderId="";
		if(hasIpflex4==0)
		{
			if((TriggerNotifyRequest.typeOfOrder.equalsIgnoreCase("MIS")) || (TriggerNotifyRequest.typeOfOrder.equalsIgnoreCase("IPFLEXMIS")))
			{
				if(TriggerNotifyRequest.archType.equalsIgnoreCase("OLD"))
				{	
					misOrderId= OCXOrderStatus.returnOrderId("MISM");
				}
				else
				{
					misOrderId= OCXOrderStatus.returnOrderId("SCM");
				}
				logger.info("MIS "+TriggerNotifyRequest.archType+ " arch Order ID to be searched in GUI is : "+misOrderId);
			}
			if(TriggerNotifyRequest.typeOfOrder.equalsIgnoreCase("AVPN"))
			{
				misOrderId= OCXOrderStatus.returnOrderId("SLCM");
				isAvpnSite++;
				logger.info("AVPN SL "+TriggerNotifyRequest.archType+ " arch Order ID to be searched in GUI is : "+misOrderId);
			}
			Thread.sleep(3000);
			
			sop=hp.searchOrderNumber(misOrderId);
			Thread.sleep(3000);
			sop.getClciValue();
			hasIpflex4++;
		}
		else if(hasIpflex4==1 || TriggerNotifyRequest.typeOfOrder.equalsIgnoreCase("IPFLEXMIS") || TriggerNotifyRequest.typeOfOrder.equalsIgnoreCase("AVPN"))
		{
			if(TriggerNotifyRequest.typeOfOrder.equalsIgnoreCase("IPFLEXMIS"))
			{
				String bvoipOrderId= OCXOrderStatus.returnOrderId("BVOIP");			
				Thread.sleep(3000);
				sop=hp.searchOrderNumber(bvoipOrderId);
				Thread.sleep(3000);
				sop.getClciValue();
				Thread.sleep(20000);
				logger.info("BVOIP "+TriggerNotifyRequest.archType+ " arch Order ID to be searched in GUI is : "+bvoipOrderId);
			}
			if(TriggerNotifyRequest.typeOfOrder.equalsIgnoreCase("AVPN") && isAvpnSite==1)
			{
				String avpnOrderId= OCXOrderStatus.returnOrderId("SCM");			
				Thread.sleep(3000);
				sop=hp.searchOrderNumber(avpnOrderId);
				Thread.sleep(3000);
				sop.getClciValue();
				Thread.sleep(20000);
				hasIpflex4++;
				isAvpnSite++;
				logger.info("AVPN Site "+TriggerNotifyRequest.archType+ " arch Order ID to be searched in GUI is : "+avpnOrderId);
			}
		}
		else if(TriggerNotifyRequest.typeOfOrder.equalsIgnoreCase("IPFLEXAVPN") && isAvpnSite==2 && hasIpflex4==2)
		{
			String bvoipOrderId= OCXOrderStatus.returnOrderId("BVOIP");			
			Thread.sleep(3000);
			sop=hp.searchOrderNumber(bvoipOrderId);
			Thread.sleep(3000);
			sop.getClciValue();
			Thread.sleep(20000);
			hasIpflex4++;
			isAvpnSite++;
			logger.info("IPFLEX Over AVPN "+TriggerNotifyRequest.archType+ " arch Order ID to be searched in GUI is : "+bvoipOrderId);
		}
	
	}

	@When("^User Searches the order$")
	public void user_Searches_the_order() throws Exception 
	{
		
	}

	@Then("^Order id is displayed$")
	public void order_id_is_displayed() throws Exception 
	{
		if(hasIpflex5==0)
		{
			sb.closeDriver();
			hasIpflex5++;
		}
		else if(hasIpflex5==1 && TriggerNotifyRequest.typeOfOrder.equalsIgnoreCase("IPFLEXMIS"))
		{
			sb.closeDriver();
		}
		else if(hasIpflex5==1 && TriggerNotifyRequest.typeOfOrder.equalsIgnoreCase("AVPN"))
		{
			sb.closeDriver();
		}
		else if(hasIpflex5==1 && TriggerNotifyRequest.typeOfOrder.equalsIgnoreCase("IPFLEXAVPN"))
		{
			sb.closeDriver();
		}
		logger.info("=========================GUI Testing Completed==============================");
	}
	
	
	
	@Given("^user open browser\"([^\"]*)\"$")
	public void user_open_browser(String env) throws Exception 
	{
		System.out.println(envName);
	}
	
	@Given("^user open browser$")
	public void user_open_browser() throws Exception 
	{
	 
	}

	@When("^user logs in to the browser$")
	public void user_logs_in_to_the_browser() throws Exception 
	{
	   
	}

	@Then("^URL opens$")
	public void url_opens() throws Exception 
	{

	}
}
