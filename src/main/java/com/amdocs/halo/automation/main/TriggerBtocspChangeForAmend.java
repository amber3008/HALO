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

public class TriggerBtocspChangeForAmend 
{
	public static Logger logger = null;
	public static String getClassName()
	{
		return TriggerBtocspChangeForAmend.class.getName();
	}
	public static String triggerChangeForAmend(String env,String apID) throws IOException, InterruptedException, TransformerException
	{

		HaloOrGammaOrder.setIsHaloGamma("GAMMA");
		logger=LoggerGen.logGen(getClassName());
		TriggerXmls tg= new TriggerXmls();
		String getURL=GetURLFromExcel.readURLFromExcel("CNOD_REQUEST",env);
		logger.info(getURL);
		Document doc = null;
		byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/BTOCSP_Change_For _Amend.xml")); 
		String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/BTOCSP_Change_For _Amend.xml";
		doc= tg.readInputXmls(xmlFileForDoc);
		Thread.sleep(6000);
		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
		logger.info(apID);
		String CNOD=UpdateXMLs_GAMMA.updateXML_B2CSP(request,apID);
		byte[] encoded1 =CNOD.getBytes(); 
		//Push xmls 
		String response=tg.pushXml(encoded1, getURL); 
		logger.info("Response in format is:"+tg.formatResponseXml(response)); 
		return response;
	}

}
