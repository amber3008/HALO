package com.amdocs.halo.automation.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

//import com.amdocs.halo.automation.main.TriggerFulfimentComplete;

public class LoggerGen 
{
	public static final String LOG_FILE="log4j.properties";
	public static Logger logger = null;
	 
	public static Logger logGen(String className) throws FileNotFoundException, IOException
	{
		logger= Logger.getLogger(className);
	    Properties prop= new Properties();
		prop.load(new FileInputStream(LOG_FILE));
		PropertyConfigurator.configure(prop);
		return logger;
	}
}
