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

public class TriggerGOD 
{
	public static Logger logger = null;
	
	public static String getClassName()
	{
		return TriggerGOD.class.getName();
	}
	
	public static String triggerRequestGOD(String env,String oId) throws IOException, TransformerException, InterruptedException
	{
		HaloOrGammaOrder.setIsHaloGamma("GAMMA");
		logger=LoggerGen.logGen(getClassName());
		TriggerXmls tg= new TriggerXmls(); 
		String getURL=GetURLFromExcel.readURLFromExcel("GOD", env); 
		
		logger.info(getURL); 
		Document doc = null; 
		
		byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/GOD.xml")); 

		
		String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/GOD.xml"; 
		doc= tg.readInputXmls(xmlFileForDoc); 
 
		String[] orderId = oId.split("_");
		
		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
		String CNOD=UpdateXMLs_GAMMA.updateXML_GOD(request,orderId[0]);
		logger.info(CNOD);
		byte[] encoded1 =CNOD.getBytes(); 
		//Push xmls 
		String response=tg.pushXml(encoded1, getURL); 
		logger.info("Response in format is:"+tg.formatResponseXml(response)); 
		return response;
	}

}
