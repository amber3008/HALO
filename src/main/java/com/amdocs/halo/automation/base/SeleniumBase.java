package com.amdocs.halo.automation.base;


import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
//import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
//import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.support.events.EventFiringWebDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;

//import com.amdocs.halo.automation.main.OCXOrderCreation_GAMMA;
//import com.amdocs.halo.automation.steps.ISTOrderCreation_HALO;
import com.amdocs.halo.automation.utils.GetURLFromExcel;


public class SeleniumBase 
{
	public static WebDriver driver;
	public Properties prop;
	public static Logger logger = null;
	//public EventFiringWebDriver e_driver;
	//public WebDriverWait wait;
	
	
	public SeleniumBase()
	{
		try 
		{
			prop = new Properties();
			FileInputStream fis = new FileInputStream("src/main/java/com/amdocs/halo/automation/utils/config.properties");
			prop.load(fis);
			logger=LoggerGen.logGen(SeleniumBase.class.getName());
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}	
	}
	
	
	
	
	public WebDriver initialization(String env) throws InterruptedException, IOException 
	{
		String browserName = prop.getProperty("browser");
		String url =GetURLFromExcel.readURLFromExcel("OMXUI_URL",env);
		
		if(browserName.equalsIgnoreCase("chrome")) 
		{
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
		logger.info("Chrome Browser Initialized");
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "src/main/resources/msedgedriver.exe");
			driver = new EdgeDriver();
			logger.info("Edge Browser Initialized");
		}
		/*else if(browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			driver = new FirefoxDriver();
			logger.info("Firefox Browser Initialized");
		}
		else if(browserName.equalsIgnoreCase("ineternetexplorer")) {
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			driver = new InternetExplorerDriver();
			logger.info("Internet Explorer Browser Initialized");
		}*/
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	 			 
		driver.get(url);
		logger.info("Launched given url");
		Thread.sleep(7000);
	    
	    return driver;
		
	}
	
	public void closeDriver()
	{
		driver.quit();
	}
	
	/*public static void main(String args[]) throws InterruptedException
	{
		SeleniumBase sb= new SeleniumBase();
		sb.initialization();
	}
*/
}

