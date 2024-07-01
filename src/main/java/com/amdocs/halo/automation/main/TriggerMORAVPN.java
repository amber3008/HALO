package com.amdocs.halo.automation.main;

import java.io.IOException;
//import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
//import java.util.Random;

import javax.xml.transform.TransformerException;

//import org.w3c.dom.Document;

import com.amdocs.halo.automation.base.TriggerXmls;
import com.amdocs.halo.automation.utils.GetURLFromExcel;

public class TriggerMORAVPN 
{
	public static String getURL;
	public static TriggerMOR_OALevel tmor_oa;
	public static TriggerNotifyRequest tnr;
	public static TriggerMOR_SGLevel tmor;
	public static TriggerXmls tg;
	public static byte[] encoded;
	public static String xmlFileForDoc;
	public static  String[] sorData= new String[6];
	
	public static void triggerMoRequest_SiteSG(String env, String testingType) throws IOException, TransformerException, InterruptedException, InstantiationException, IllegalAccessException
	{
		getURL=GetURLFromExcel.readURLFromExcel("MANAGE_ORDER_REQUEST",env);
		System.out.println("-------------------------Pushing AVPN SITELESS ManageOrder Request for SG---------------------");
		encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/manageOrderRequestAVPNSITE_NS_SG.xml"));
		xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/manageOrderRequestAVPNSITE_NS_SG.xml";
		System.out.println("URL for MOR for AVPN is : "+getURL);
		tmor= new TriggerMOR_SGLevel();
		tmor.pushRequest(getURL, encoded, xmlFileForDoc,testingType);
		Thread.sleep(20000);
	}
	
		
	public static void triggerMoRequest_SiteOA(String env) throws IOException, TransformerException, InterruptedException
	{	
		//	Document doc;
		getURL=GetURLFromExcel.readURLFromExcel("MANAGE_ORDER_REQUEST",env);
		System.out.println("-------------------------Pushing AVPN SITE ManageOrder Request for OA---------------------");
		encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/manageOrderRequestAVPNSITE_NS_OA.xml"));
		xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/manageOrderRequestAVPNSITE_NS_OA.xml";
		tmor_oa= new TriggerMOR_OALevel();
		System.out.println("URL is: "+getURL);
	    System.out.println("XML is: "+xmlFileForDoc);
	   // doc= tg.readInputXmls(xmlFileForDoc);				
		//String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
	      // System.out.println("Testing Type is: "+testingType);
	    //System.out.println("Request is: "+request);
		tmor_oa.pushRequest(getURL,encoded,xmlFileForDoc);
	}
	
	public static void main(String args[]) throws IOException, InstantiationException, IllegalAccessException, TransformerException, InterruptedException
	{
		getURL=GetURLFromExcel.readURLFromExcel("MANAGE_ORDER_REQUEST","ST3");
		System.out.println(getURL);
		triggerMoRequest_SiteSG("ST3","ST");
	}
}
