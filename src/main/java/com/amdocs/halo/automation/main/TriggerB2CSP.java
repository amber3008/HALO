package com.amdocs.halo.automation.main;

//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

//import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import com.amdocs.halo.automation.base.LoggerGen;
import com.amdocs.halo.automation.base.TriggerXmls;
import com.amdocs.halo.automation.base.UpdateXMLs_HALO;
import com.amdocs.halo.automation.utils.GetURLFromExcel;

public class TriggerB2CSP 
{
	public static Logger logger = null;
	
	
	public static String getClassName()
	{
		return TriggerB2CSP.class.getName();
	}
	public static String triggerB2CSPRequest(String env, String orderType, String isAdx) throws Exception
	{
		HashMap<String,List<String>> orderInfo= new HashMap<String,List<String>>();
		logger=LoggerGen.logGen(getClassName());
		String getURL=GetURLFromExcel.readURLFromExcel("CNOD_REQUEST",env);
		Document doc = null;
		TriggerXmls tg= new TriggerXmls();
	//	byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/CNOD_B2CSP_NS.xml"));
		
		//Read xml for Document Class 
		String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/CNOD_B2CSP_NS.xml";
		doc= tg.readInputXmls(xmlFileForDoc);
				
				
		//doc=tg.readXmls(xmlInputFormatted);
		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
		logger.info("URL is:"+getURL);
				
		String data= UpdateXMLs_HALO.updateXmlB2CSP(request, isAdx);
				
		byte[] encoded1 =data.getBytes();
				
		//Push xmls
		String response=tg.pushXml(encoded1, getURL);
		logger.info("Response in format is:"+tg.formatResponseXml(response));
		System.out.println(orderInfo);
		 return response;
	}
	
	/*public static void main(String args[]) throws Exception
	{
		triggerB2CSPRequest("ST3","B2CSP","N");
	}*/
}
