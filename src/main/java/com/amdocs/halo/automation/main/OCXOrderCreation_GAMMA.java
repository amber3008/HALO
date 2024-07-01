package com.amdocs.halo.automation.main;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//import java.util.Random;

import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
//import org.apache.poi.util.SystemOutLogger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.amdocs.halo.automation.base.LoggerGen;
import com.amdocs.halo.automation.base.ParseXmls;
import com.amdocs.halo.automation.base.TriggerXmls;
import com.amdocs.halo.automation.base.UpdateXMLs_HALO;
//import com.amdocs.halo.automation.utils.AutoConstants;
import com.amdocs.halo.automation.utils.GetURLFromExcel;
//import com.amdocs.halo.automation.utils.Log;

public class OCXOrderCreation_GAMMA 
{
	public static String getURL;
	public static int numberOfTagName;
	public static String xmlFileForDoc;
	public static String[] data;
	public static Logger logger = null;
	public static String typeOfOrder;
	public static HashMap<String,List<String>> circuitIds= new HashMap<String,List<String>>();
	public static HashMap<String,List<String>> allIdsGlobal = new HashMap<String,List<String>>();
	HashMap<String,List<String>> productOrderId= new HashMap<String,List<String>>();
	HashMap<String,List<String>> oaIds= new HashMap<String,List<String>>();
	HashMap<String,List<String>> productNames= new HashMap<String,List<String>>();
	HashMap<String,List<String>> circuitIdA= new HashMap<String,List<String>>();
	public String[][] allOrders = new String[20][9];
	public static int i=2;
	
	public static String getClassName()
	 {
		 return OCXOrderCreation_GAMMA.class.getName();
	 }
	
	public static String triggerGammaOrder(String env, String orderType, String stateRegion, String isFttbNeeded, String isADX, String isTSP, ArrayList<String> listOfCktIds, String whichUniAdxHost) throws Exception
	{
		String response=null;
		typeOfOrder=orderType;
		if(orderType.equalsIgnoreCase("UNI"))
			response=triggerRequestUni(env, stateRegion, isFttbNeeded, isADX, isTSP);
		else if(orderType.equalsIgnoreCase("MPT") || orderType.equalsIgnoreCase("PTP"))
			response=triggerRequestEvc(env, isADX, listOfCktIds, whichUniAdxHost);
		else if(orderType.equalsIgnoreCase("AVPN"))
			response=triggerRequestAVPN(env);
		//else if(orderType.equalsIgnoreCase("B2CSP"))
			//response=triggerRequestB2CSP(env, isADX);
		
		circuitIds=getElementFromXml(response,"productServiceId");
		//		String circuitId= circuitIds.get("productServiceId").get(0);
		return response;
	}
	
	public static String triggerRequestUni(String env, String stateRegion, String isFttbNeeded, String isADX, String isTSP) throws IOException, TransformerException
	{
		logger=LoggerGen.logGen(getClassName());
		TriggerXmls tg= new TriggerXmls();
		getURL=GetURLFromExcel.readURLFromExcel("CNOD_REQUEST",env);
		logger.info("URL to push for CNOD is: "+getURL);
		Document doc = null;
		xmlFileForDoc=getXmlDoc("UNI");
		logger.info("Thefile after  update is : "+xmlFileForDoc);
		doc= tg.readInputXmls(xmlFileForDoc);

		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
		logger.info("URL is:"+getURL);
		String cnod = UpdateXMLs_HALO.updateXmlUNI(request,stateRegion,isFttbNeeded, isADX, isTSP );
		logger.info(cnod);
		byte[] encoded1 =cnod.getBytes();
				
				//Push xmls
		String response=tg.pushXml(encoded1, getURL);
		logger.info("Response in format is:"+tg.formatResponseXml(response));
		return response;
	}
		   
	public static String triggerRequestEvc(String env, String isADX, ArrayList<String> listOfCktIds, String whichUniAdxHost) throws IOException, TransformerException
	{
		logger=LoggerGen.logGen(getClassName());
		TriggerXmls tg= new TriggerXmls();
		getURL=GetURLFromExcel.readURLFromExcel("CNOD_REQUEST",env);
		logger.info("URL to push for CNOD is: "+getURL);
		Document doc = null;
		if(typeOfOrder.equalsIgnoreCase("MPT"))
			xmlFileForDoc=getXmlDoc("MPT");
		if(typeOfOrder.equalsIgnoreCase("PTP"))
			xmlFileForDoc=getXmlDoc("PTP");

		doc= tg.readInputXmls(xmlFileForDoc);

		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
		logger.info("URL is:"+getURL);
		String cnod = null;
		
		if(typeOfOrder.equalsIgnoreCase("MPT"))
			cnod = UpdateXMLs_HALO.updateXmlEVC("MPT",isADX, request,listOfCktIds, whichUniAdxHost);
		if(typeOfOrder.equalsIgnoreCase("PTP"))
			cnod = UpdateXMLs_HALO.updateXmlEVC("PTP",isADX,request,listOfCktIds, whichUniAdxHost);
		
		logger.info(cnod);
		byte[] encoded1 =cnod.getBytes();
				
				//Push xmls
		String response=tg.pushXml(encoded1, getURL);
		logger.info("Response in format is:"+tg.formatResponseXml(response));
		return response;
	}
	
	public static String triggerRequestAVPN(String env) throws IOException, TransformerException
	{
		logger=LoggerGen.logGen(getClassName());
		TriggerXmls tg= new TriggerXmls();
		getURL=GetURLFromExcel.readURLFromExcel("CNOD_REQUEST",env);
		logger.info("URL to push for CNOD is: "+getURL);
		Document doc = null;
		
		xmlFileForDoc=getXmlDoc("AVPN");
		
			

		doc= tg.readInputXmls(xmlFileForDoc);

		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
		/*logger.info("URL is:"+getURL);
		String cnod = null;
		
		
			cnod = UpdateXMLs.updateXmlEVC("MPT",isADX, request,listOfCktIds, whichUniAdxHost);
		
		*/
		logger.info(request);
		byte[] encoded1 =request.getBytes();
				
				//Push xmls
		String response=tg.pushXml(encoded1, getURL);
		logger.info("Response in format is:"+tg.formatResponseXml(response));
		return response;
	}
	
	/*public static String triggerRequestB2CSP(String env,String isADX) throws IOException, TransformerException
	{
		logger=LoggerGen.logGen(getClassName());
		TriggerXmls tg= new TriggerXmls();
		getURL=GetURLFromExcel.readURLFromExcel("CNOD_REQUEST",env);
		logger.info("URL to push for CNOD is: "+getURL);
		Document doc = null;
		xmlFileForDoc=getXmlDoc("UNI");
		logger.info("Thefile after  update is : "+xmlFileForDoc);
		doc= tg.readInputXmls(xmlFileForDoc);

		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
		logger.info("URL is:"+getURL);
		String cnod = UpdateXMLs.updateXmlUNI(request,stateRegion,isFttbNeeded, isADX, isTSP );
		logger.info(cnod);
		byte[] encoded1 =cnod.getBytes();
				
				//Push xmls
		String response=tg.pushXml(encoded1, getURL);
		logger.info("Response in format is:"+tg.formatResponseXml(response));
		return response;
	}*/
	
	public static String getXmlDoc(String orderType) throws IOException, TransformerException 
	{
		logger=LoggerGen.logGen(getClassName());
		TriggerXmls tg= new TriggerXmls();
		String xmlInputFormatted = "null";
		String xmlFileForDoc="null";
		String xmlInput= "null";
		//	Document doc = null;
		byte[] encoded=null;
		
		if(orderType.equalsIgnoreCase("UNI"))
		{
			encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/CNOD_UNI.xml"));
			xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/CNOD_UNI.xml";
		}
		
		if(orderType.equalsIgnoreCase("MPT"))
		{
			encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/CNOD_MPT.xml"));
			xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/CNOD_MPT.xml";
		}
		if(orderType.equalsIgnoreCase("PTP"))
		{
			encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/CNOD_PTP.xml"));
			xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/CNOD_PTP.xml";
		}
		
		if(orderType.equalsIgnoreCase("AVPN"))
		{
			encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/CNOD_APORT_AVLAN.xml"));
			xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/CNOD_APORT_AVLAN.xml";
		}
		   	
		   	   
		   	 //Only for formatting
			   xmlInput = new String(encoded, StandardCharsets.UTF_8);
			   xmlInputFormatted = tg.formatResponseXml(xmlInput);
		   logger.info("Input in format is:"+xmlInputFormatted);

		return xmlFileForDoc;
		
	}
		   
		   public static HashMap<String,List<String>> getElementFromXml(String response, String ...tagNames) throws Exception 
			{
			   logger=LoggerGen.logGen(getClassName());
				Document xmlDoc = UpdateActivityStatus_OA.loadXMLString(response);
				HashMap<String,List<String>> allIds = new HashMap<String,List<String>>();
				int numberOfTagNames=tagNames.length;
				numberOfTagName=numberOfTagNames;
				for(int i=0;i<numberOfTagNames;++i)
				{	
					logger.info(tagNames[i]);
					NodeList nodeList = xmlDoc.getElementsByTagName(tagNames[i]);
					List<String> ids = new ArrayList<String>(nodeList.getLength());
					numberOfTagName=nodeList.getLength();
					for(int j=0;j<nodeList.getLength(); j++) 
					{
						Node x = nodeList.item(j);
						ids.add(x.getFirstChild().getNodeValue());    
						logger.info("The IDs for "+tagNames[i]+" is:"+ids.get(j));
					}
					allIds.put(tagNames[i],ids);
					
				}
				allIdsGlobal=allIds;
				return allIds;
			}

		   
		   public  void publishAllOrders() 
		   {
			 
			   
			   
			   allOrders[0][0]="Order Type/Attributes";
			   allOrders[0][1]="SG Id";
			   allOrders[0][2]="OAID";
			   allOrders[0][3]="Circuit ID";
			   allOrders[0][4]="ProductName";
			   allOrders[0][5]="isAdx";
			   allOrders[0][6]="stateRegion";
			   allOrders[0][7]="isFttb";
			   allOrders[0][8]="isTsp";
			   
			   
			  // allOrders[1][0]="MPT EVC Info";
			   //allOrders[8][0]="PTP EVC Info";
			  // allOrders[14][0]="GAMMA AVPN Info";
			   
			   
			   /*allOrders[2][0]="UNIA";
			   allOrders[3][0]="UNIB";
			   allOrders[4][0]="UNIC";
			   allOrders[5][0]="MPT EVCA";
			   allOrders[6][0]="MPT EVCV";
			   allOrders[7][0]="MPT EVCA";*/
			   
			   
			   allOrders[9][0]="UNIA";
			   allOrders[10][0]="UNIB";
			   allOrders[11][0]="PTP EVCA 1";
			   allOrders[12][0]="PTP EVCA 2";
			   allOrders[13][0]="PTP EVCV";
			   
			   
			   allOrders[15][0]="APORT";
			   allOrders[16][0]="AVLAN 1";
			   allOrders[17][0]="AVLAN 2";
			   allOrders[18][0]="AVLAN 3";
			   
			  for(String[] row: allOrders)
			  {
				  printRow(row);
			  }
			  
			  //GetURLFromExcel.write_xlsx(allOrders,AutoConstants.PATH_EXCEL_GAMMA,"Order Details",9);
   
		   }
		   
		   public void printRow(String[] row)
		   { 
			   for(String i : row) 
			   {
				  logger.info(i);
				  logger.info("\t\t");
			   }
			   logger.info("\n");
		   }
		   
		
		   
		   public void saveGammaOrderDetails(String orderType,String rsponse, String isAdxValue, String stateRegion, String isFttbNeeded, String isTSP, String evcCircuitId, String orderId) throws Exception
		   {
			   String [] uniDetails = new String[10];
			   logger.info("Order Type is: "+orderType);
			   logger.info("i = "+i);
			   uniDetails[0]=orderType;
			   allOrders[i][0]=uniDetails[0];
			   
				productOrderId=OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"productOrderId");		
				uniDetails[1]=productOrderId.get("productOrderId").get(0);
				allOrders[i][1]=uniDetails[1];
				
				if(orderType.contains("UNI"))
				{
					oaIds= OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"productOrderItemId");
					uniDetails[2]=oaIds.get("productOrderItemId").get(0);
					allOrders[i][2]=uniDetails[2];
					
					circuitIdA=OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"productServiceId");
					uniDetails[3]=circuitIdA.get("productServiceId").get(0);
					allOrders[i][3]=uniDetails[3];
					
					productNames=OCXOrderCreation_GAMMA.getElementFromXml(rsponse,"productName");
					uniDetails[4]=productNames.get("productName").get(0);
					allOrders[i][4]=uniDetails[4];
					
					uniDetails[5]=isAdxValue;
					allOrders[i][5]=isAdxValue;
					
					uniDetails[6]=stateRegion;
					allOrders[i][6]=stateRegion;
					
					uniDetails[7]=isFttbNeeded;
					allOrders[i][7]=isFttbNeeded;
					
					uniDetails[8]=isTSP;
					allOrders[i][8]=isTSP;
				}
				else
				{
					allOrders[i][2]=orderId;
					allOrders[i][3]=evcCircuitId;
					allOrders[i][4]=orderType;
					
					uniDetails[5]=isAdxValue;
					allOrders[i][5]=null;
					
					uniDetails[6]=stateRegion;
					allOrders[i][6]=null;
					
					uniDetails[7]=isFttbNeeded;
					allOrders[i][7]=null;
					
					uniDetails[8]=isTSP;
					allOrders[i][8]=null;
					
				}			
				i++;
			
		   }
}
