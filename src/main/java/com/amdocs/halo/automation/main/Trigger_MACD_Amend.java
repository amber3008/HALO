package com.amdocs.halo.automation.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import com.amdocs.halo.automation.base.HaloOrGammaOrder;
import com.amdocs.halo.automation.base.LoggerGen;
import com.amdocs.halo.automation.base.TriggerXmls;
import com.amdocs.halo.automation.base.UpdateXMLs_GAMMA;
import com.amdocs.halo.automation.utils.GetURLFromExcel;

public class Trigger_MACD_Amend 
{
	public static Logger logger = null;
	public static String getClassName()
	{
		return Trigger_MACD_Amend.class.getName();
	}
	
	public static String triggerRequestMACHAmend(String env,String oID,String apID) throws IOException, InterruptedException, TransformerException
	{

		HaloOrGammaOrder.setIsHaloGamma("GAMMA");
		logger=LoggerGen.logGen(getClassName());
		TriggerXmls tg= new TriggerXmls();
		String getURL=GetURLFromExcel.readURLFromExcel("AMEND_API",env);
		logger.info(getURL);
		Document doc = null;
		byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/MACD_Amend.xml"));

		String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/MACD_Amend.xml";
		doc= tg.readInputXmls(xmlFileForDoc);
		Thread.sleep(6000);
		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
		String CNOD=UpdateXMLs_GAMMA.updateAmendRequest(request,oID,apID);

		byte[] encoded1 =CNOD.getBytes(); 
		//Push xmls 
		String response=tg.pushXml(encoded1, getURL); 
		logger.info("Response in format is:"+tg.formatResponseXml(response)); 
		return response;
	}

}
