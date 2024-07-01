package com.amdocs.halo.automation.base;

import java.io.FileNotFoundException;
import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.xml.transform.TransformerException;

import com.amdocs.halo.automation.main.OCXOrderStatus;
import com.amdocs.halo.automation.main.OrderCreationInOCX;
import com.amdocs.halo.automation.main.TriggerNotifyRequest;
//import com.amdocs.halo.automation.steps.GAMMAOrderCreation_Steps;

import org.apache.log4j.Logger;
//import org.w3c.dom.Document;

public class UpdateXMLs_HALO extends ParseXmls 
{
	 public static String str1[]= new String[6];
	 public static String typeOfOrder;
	 public static String sorData;
	 public static Logger logger = null;
	 public static HashMap<String,String> keysAndIds= new HashMap<String,String>();
	 public static String icoreSiteId;
	 public static String icorePvcId;
	 public static String icoreVpnId;
	 public static String eteVpnKey;
	 public static String etePortKey;
	 public static String eteCpeKey;
	 public static String eteSiteKey;
	 public static String eteAccessKey;
	 public static String eteSCKey;
	 
	//From TriggerNotifyRequest --> Done
	
	public static String[] updateXml(String UpdatedXml, String orderType) throws FileNotFoundException, IOException 
	{	
		logger=LoggerGen.logGen(getClassName());
		String str1[]= new String[3];
	   	Random random = new Random();
		//String sorId= "1-"+TriggerXmls.randomString();
		//logger.info("SOR ID is:"+sorId);
		
		String sorId=" ";
		String typeOrder="";
	   	if(orderType.equals("MIS"))
	   	{	
	   		typeOrder="ME";
	   		sorId= "1-"+typeOrder+TriggerXmls.randomStringOldArch();
	   	}
	   	if(orderType.equals("IPFLEXMIS")) 
	   	{	
	   		typeOrder="TNRSCPAB";
	   		//typeOrder="CPAM";
	   		sorId= "1-"+typeOrder+TriggerXmls.randomStringOldArch();
	   	}
	   		
	   	if(orderType.equals("AVPN"))
	   	{	
	   		typeOrder="AVPN";
	   		sorId= "1-"+typeOrder+TriggerXmls.randomStringOldArch();
	   	}
	   		
	   	if(orderType.equals("BOE"))
	   	{	
	   		typeOrder="BOE";
	   		sorId= "1-"+typeOrder+TriggerXmls.randomStringOldArch();
	   	}
	   		
		
	   	logger.info("SOR ID is:"+sorId);
		
		int projId1= random.nextInt(1000000);
		String projId= Integer.toString(projId1);
		projId= typeOrder+projId;
		if(orderType.equalsIgnoreCase("MIS") || orderType.equalsIgnoreCase("AVPN"))
		{
			UpdatedXml = UpdatedXml.replace("{$sorId$}", sorId);
			UpdatedXml = UpdatedXml.replace("{$projId$}", projId);
			UpdatedXml = UpdatedXml.replace("{$orderType$}", orderType);
		}
		else if(orderType.equalsIgnoreCase("IPFLEXMIS"))
		{
			UpdatedXml = UpdatedXml.replace("{$sorId$}", sorId);
			UpdatedXml = UpdatedXml.replace("{$projId$}", projId);
			UpdatedXml = UpdatedXml.replace("{$orderType$}", "MIS");
			UpdatedXml = UpdatedXml.replace("<!--<nxen:serviceName>BVOIP</nxen:serviceName>-->", "<nxen:serviceName>BVOIP</nxen:serviceName>");
		}
		else if(orderType.equalsIgnoreCase("BOE"))
		{
			UpdatedXml = UpdatedXml.replace("{$sorId$}", sorId);
			UpdatedXml = UpdatedXml.replace("{$projId$}", projId);
			UpdatedXml = UpdatedXml.replace("<!--<nxen:serviceName>BVOIP</nxen:serviceName>-->", "<nxen:serviceName>BVOIP</nxen:serviceName>");
			UpdatedXml = UpdatedXml.replace("<nxen:serviceName>{$orderType$}</nxen:serviceName>","<!--<nxen:serviceName>{$orderType$}</nxen:serviceName>-->");
		}
		logger.info("Request After update is :"+UpdatedXml);
		str1[0]=UpdatedXml;
		str1[1]=sorId;
		str1[2]=projId;
		return str1;
	}
	
	//From TriggerMOR_SGLevel --> Done
	 public static String[] updateXmlMorSG(String UpdatedXml, String sorId, String projId, String typeOfOrder) throws FileNotFoundException, IOException 
	   {	
		 logger=LoggerGen.logGen(getClassName());
		   	Random random = new Random();
			logger.info("SOR ID is:"+sorId);
			int sgId1=random.nextInt(10000000);
			String sgId= Integer.toString(sgId1);
			int oaId1= sgId1+1;
			String oaId= Integer.toString(oaId1);
			UpdatedXml = UpdatedXml.replace("{$sorId$}", sorId);
			if(typeOfOrder.equalsIgnoreCase("MIS"))
				UpdatedXml = UpdatedXml.replace("{$projId$}", projId);
			
			UpdatedXml = UpdatedXml.replace("{$sgId$}", sgId);
			UpdatedXml = UpdatedXml.replace("{$oaId$}", oaId);
			logger.info("Request After update is :"+UpdatedXml);
			str1[0]=UpdatedXml;
			str1[1]=sgId;
			str1[2]=oaId;
			str1[3]=sorId;
			str1[5]=typeOfOrder;
			if(typeOfOrder.equalsIgnoreCase("MIS"))
				str1[4]=projId;
			logger.info(str1);
			return str1;
	   }
	 
	 
	 public static String[] updateXmlMorSGIST(String UpdatedXml, String sorId, String projId, String typeOfOrder) throws InstantiationException, IllegalAccessException, IOException, InterruptedException 
	   {	
		 logger=LoggerGen.logGen(getClassName());
		 logger.info("SOR ID is:"+sorId);
			UpdatedXml = UpdatedXml.replace("{$sorId$}", sorId);
			if(typeOfOrder.equalsIgnoreCase("MIS") || typeOfOrder.equalsIgnoreCase("IPFLEXMIS") || typeOfOrder.equalsIgnoreCase("BOE"))
				UpdatedXml = UpdatedXml.replace("{$projId$}", projId);
			
			if(TriggerNotifyRequest.archType.equalsIgnoreCase("NEW"))
			{
				ArrayList<List<String>> ordrDetailsAvpn=OrderCreationInOCX.retreiveCASOOrdrDetails();
			
			//	String ordrStatus=ordrDetailsAvpn.get(3).get(0);
				String oaId=ordrDetailsAvpn.get(2).get(0);
				String sgId=ordrDetailsAvpn.get(0).get(0);
			//	String srviceTyp=ordrDetailsAvpn.get(1).get(0);
			
				
				UpdatedXml = UpdatedXml.replace("{$sgId$}", sgId);
				UpdatedXml = UpdatedXml.replace("{$oaId$}", oaId);
				logger.info("Request After update is :"+UpdatedXml);

				str1[1]=sgId;
				str1[2]=oaId;

				if(typeOfOrder.equalsIgnoreCase("MIS"))
					str1[4]=projId;
			
			}
			
			else if(TriggerNotifyRequest.archType.equalsIgnoreCase("OLD"))
			{
				ArrayList<String> ar=OCXOrderStatus.getOcxOrderStatusUrl(sorId, TriggerNotifyRequest.envName,"OLD");
				String sgId= ar.get(2);
				String oaId= ar.get(1);
				String acmsOAId= ar.get(3);
				String ipflexOAId=ar.get(4);
				UpdatedXml = UpdatedXml.replace("{$sgId$}", sgId);
				UpdatedXml = UpdatedXml.replace("{$oaId$}", oaId);
				UpdatedXml = UpdatedXml.replace("{$oaIdAc$}", acmsOAId);
				if(!TriggerNotifyRequest.typeOfOrder.equalsIgnoreCase("MIS"))
					UpdatedXml = UpdatedXml.replace("{$oaIdBvoip$}", ipflexOAId);
				logger.info("Request After update is :"+UpdatedXml);
				str1[1]=sgId;
				str1[2]=oaId;
			}
			str1[0]=UpdatedXml;
			str1[3]=sorId;
			str1[5]=typeOfOrder;
			logger.info(str1);
			return str1;
	   }
	 
	 // From TriggerMOR_OALevel --> Done
	 //Used for MACD also
	 public static String updateXml(String UpdatedXml, String sgId, String oaId, String sorId) throws FileNotFoundException, IOException 
	   {	
		 	logger=LoggerGen.logGen(getClassName());
			String str1;
			logger.info("SOR ID is:"+sorId);
			UpdatedXml = UpdatedXml.replace("{$sorId$}", sorId);
			//UpdatedXml = UpdatedXml.replace("{$projId$}", projId);
			UpdatedXml = UpdatedXml.replace("{$sgId$}", sgId);
			UpdatedXml = UpdatedXml.replace("{$oaId$}", oaId);
			logger.info("Request After update is :"+UpdatedXml);
			str1=UpdatedXml;
			return str1;
		}
	 
	 // From TriggerDecomposeSalesOrderRequest_MIS --> Done
	 public static String[] updateXmlDecompose(String UpdatedXml,String sorId, String projId) throws FileNotFoundException, IOException 
	   {	
		 logger=LoggerGen.logGen(getClassName());
			String str1[]= new String[3];
		//   	Random random = new Random();
		   
			logger.info("SOR ID is:"+sorId);
			
			UpdatedXml = UpdatedXml.replace("{$sorId$}", sorId);
			UpdatedXml = UpdatedXml.replace("{$projId$}", projId);
			logger.info("Request After update is :"+UpdatedXml);
			str1[0]=UpdatedXml;
			str1[1]=sorId;
			str1[2]=projId;
			return str1;
		}
	 
	 public static String updateUnidata(String request, String stateRegion , String isADX, String orderType, String isFttbNeeded, String isTSP)
	 {
		 request = request.replace("{$stateRegion$}", stateRegion);
		 if(isADX.equalsIgnoreCase("Y") && (orderType.equalsIgnoreCase("UNI")))
		 {	 request = request.replace("<!--adxValue-->","<characteristicValues>\r\n" + 
			 				"                           <characteristicDefinition>\r\n" + 
			 				"                              <attributeCode>fisArrangement</attributeCode>\r\n" + 
			 				"                              <attributeId>6040405</attributeId>\r\n" + 
			 				"                           </characteristicDefinition>\r\n" + 
			 				"                           <value>ADX</value>\r\n" + 
			 				"                          </characteristicValues>");
		 }
		 if(isFttbNeeded.equalsIgnoreCase("Y") && (orderType.equalsIgnoreCase("UNI")))
		 {	 request = request.replace("<!--fttbValue-->","<characteristicValues>\r\n" + 
			 				"                              <characteristicDefinition>\r\n" + 
			 				"                                 <attributeCode>fttbCode</attributeCode>\r\n" + 
			 				"                                 <attributeId>29129</attributeId>\r\n" + 
			 				"                              </characteristicDefinition>\r\n" + 
			 				"                              <value>NearNet-15</value>\r\n" + 
			 				"                           </characteristicValues>");
		 }
		 
		 if(isTSP.equalsIgnoreCase("Y") && (orderType.equalsIgnoreCase("UNI")))
		 {	 request = request.replace("<!-- tspvalue-->","<characteristicValues>\r\n" + 
			 				"                              <characteristicDefinition>\r\n" + 
			 				"                                 <attributeCode>tspAuthorizationCode</attributeCode>\r\n" + 
			 				"                                 <attributeId>67543</attributeId>\r\n" + 
			 				"                              </characteristicDefinition>\r\n" + 
			 				"                              <value>TSP-TEST-555</value>\r\n" + 
			 				"                           </characteristicValues>");
		 }
	/*	 if(isTSP.equalsIgnoreCase("Y") && (orderType.equalsIgnoreCase("UNI")))
		 {	 request = request.replace("<!-- B2CSP Info -->","<characteristicValues>\r\n" + 
			 				"                              <characteristicDefinition>\r\n" + 
			 				"                                 <attributeCode>tspAuthorizationCode</attributeCode>\r\n" + 
			 				"                                 <attributeId>67543</attributeId>\r\n" + 
			 				"                              </characteristicDefinition>\r\n" + 
			 				"                              <value>TSP-TEST-555</value>\r\n" + 
			 				"                           </characteristicValues>");
		 }
*/		 return request;
	 }
	 
	 public static String updateXmlUNI(String requestUni, String stateRegion,String isFttbNeeded,String isADX,String isTSP ) throws IOException, TransformerException
	 {
		 logger=LoggerGen.logGen(getClassName());
		 //int numberOfUnis= Integer.parseInt(uniNos);
		 String uniRequest="";
		  uniRequest = updateUnidata(requestUni, stateRegion , isADX, "UNI", isFttbNeeded, isTSP);
		 logger.info("Request after Updating UNI Data : "+uniRequest);
		 return uniRequest;
	 }
	 
	/* public static String updateXmlAvpnCnod(String requestUni, String stateRegion,String isFttbNeeded,String isADX,String isTSP ) throws IOException, TransformerException
	 {
		 logger=LoggerGen.logGen(getClassName());
		 //int numberOfUnis= Integer.parseInt(uniNos);
		 String uniRequest="";
		  uniRequest = updateUnidata(requestUni, stateRegion , isADX, "UNI", isFttbNeeded, isTSP);
		 logger.info("Request after Updating UNI Data : "+uniRequest);
		 return uniRequest;
	 }*/
	 
	 public static String updateXmlEVC(String orderType,String isADX,String request, ArrayList<String> listOfCktIds, String whichUniAdxHost) throws IOException, TransformerException
	 {	 
			 logger.info("Request before update is : \n"+request);
			
			 request = request.replace("{$circuitIdA$}",listOfCktIds.get(0));
			 request = request.replace("{$circuitIdB$}",listOfCktIds.get(1));
			 
			 if(isADX.equalsIgnoreCase("N"))
					 whichUniAdxHost="null"; 
			 
			 if(isADX.equalsIgnoreCase("Y"))
				 request = request.replace("<!--adxValue EVCV-->","<characteristicValues>\r\n" + 
			 		"                              <characteristicDefinition>\r\n" + 
			 		"                                 <attributeCode>fisArrangement</attributeCode>\r\n" + 
			 		"                                 <attributeId>6040405</attributeId>\r\n" + 
			 		"                              </characteristicDefinition>\r\n" + 
			 		"                              <value>ADX</value>\r\n" + 
			 		"                           </characteristicValues>");
			 	
				 
			  if(orderType.equalsIgnoreCase("MPT"))
			  {
				
				 request = request.replace("{$circuitIdC$}",listOfCktIds.get(2));
				 
				 if(whichUniAdxHost.equalsIgnoreCase("A"))
				 {
					 request = request.replace("<!--adxValue EVCA-->","<characteristicValues>\r\n" + 
					 		"   <characteristicDefinition>\r\n" + 
					 		"      <attributeCode>fisArrangement</attributeCode>\r\n" + 
					 		"      <attributeId>6040405</attributeId>\r\n" + 
					 		"   </characteristicDefinition>\r\n" + 
					 		"   <value>ADX-HOST</value>\r\n" + 
					 		"</characteristicValues>");
					 
					 request = request.replace("<!--adxValue EVCB-->","<characteristicValues>\r\n" + 
						 		"   <characteristicDefinition>\r\n" + 
						 		"      <attributeCode>fisArrangement</attributeCode>\r\n" + 
						 		"      <attributeId>6040405</attributeId>\r\n" + 
						 		"   </characteristicDefinition>\r\n" + 
						 		"   <value>ADX-CLIENT</value>\r\n" + 
						 		"</characteristicValues>");
					 
					 request = request.replace("<!--adxValue EVCC-->","<characteristicValues>\r\n" + 
						 		"   <characteristicDefinition>\r\n" + 
						 		"      <attributeCode>fisArrangement</attributeCode>\r\n" + 
						 		"      <attributeId>6040405</attributeId>\r\n" + 
						 		"   </characteristicDefinition>\r\n" + 
						 		"   <value>ADX-CLIENT</value>\r\n" + 
						 		"</characteristicValues>");
				 }
				 
				 if(whichUniAdxHost.equalsIgnoreCase("B"))
				 {
					 request = request.replace("<!--adxValue EVCB-->","<characteristicValues>\r\n" + 
					 		"   <characteristicDefinition>\r\n" + 
					 		"      <attributeCode>fisArrangement</attributeCode>\r\n" + 
					 		"      <attributeId>6040405</attributeId>\r\n" + 
					 		"   </characteristicDefinition>\r\n" + 
					 		"   <value>ADX-HOST</value>\r\n" + 
					 		"</characteristicValues>");
					 
					 request = request.replace("<!--adxValue EVCA-->","<characteristicValues>\r\n" + 
						 		"   <characteristicDefinition>\r\n" + 
						 		"      <attributeCode>fisArrangement</attributeCode>\r\n" + 
						 		"      <attributeId>6040405</attributeId>\r\n" + 
						 		"   </characteristicDefinition>\r\n" + 
						 		"   <value>ADX-CLIENT</value>\r\n" + 
						 		"</characteristicValues>");
					 
					 request = request.replace("<!--adxValue EVCC-->","<characteristicValues>\r\n" + 
						 		"   <characteristicDefinition>\r\n" + 
						 		"      <attributeCode>fisArrangement</attributeCode>\r\n" + 
						 		"      <attributeId>6040405</attributeId>\r\n" + 
						 		"   </characteristicDefinition>\r\n" + 
						 		"   <value>ADX-CLIENT</value>\r\n" + 
						 		"</characteristicValues>");
				 }
				 
				 if(whichUniAdxHost.equalsIgnoreCase("C"))
				 {
					 request = request.replace("<!--adxValue EVCA-->","<characteristicValues>\r\n" + 
					 		"   <characteristicDefinition>\r\n" + 
					 		"      <attributeCode>fisArrangement</attributeCode>\r\n" + 
					 		"      <attributeId>6040405</attributeId>\r\n" + 
					 		"   </characteristicDefinition>\r\n" + 
					 		"   <value>ADX-CLIENT</value>\r\n" + 
					 		"</characteristicValues>");
					 
					 request = request.replace("<!--adxValue EVCB-->","<characteristicValues>\r\n" + 
						 		"   <characteristicDefinition>\r\n" + 
						 		"      <attributeCode>fisArrangement</attributeCode>\r\n" + 
						 		"      <attributeId>6040405</attributeId>\r\n" + 
						 		"   </characteristicDefinition>\r\n" + 
						 		"   <value>ADX-CLIENT</value>\r\n" + 
						 		"</characteristicValues>");
					 
					 request = request.replace("<!--adxValue EVCC-->","<characteristicValues>\r\n" + 
						 		"   <characteristicDefinition>\r\n" + 
						 		"      <attributeCode>fisArrangement</attributeCode>\r\n" + 
						 		"      <attributeId>6040405</attributeId>\r\n" + 
						 		"   </characteristicDefinition>\r\n" + 
						 		"   <value>ADX-HOST</value>\r\n" + 
						 		"</characteristicValues>");
				 }
				 
				 if(whichUniAdxHost.equalsIgnoreCase("null"))
				 {
					 logger.info("No ADX changes needed");
				 }
			 }
			 	
			 	else if(orderType.equalsIgnoreCase("PTP"))
			 	{
			 		 if(whichUniAdxHost.equalsIgnoreCase("A"))
					 {
						 request = request.replace("<!--adxValue EVCA A-->","<characteristicValues>\r\n" + 
						 		"   <characteristicDefinition>\r\n" + 
						 		"      <attributeCode>fisArrangement</attributeCode>\r\n" + 
						 		"      <attributeId>6040405</attributeId>\r\n" + 
						 		"   </characteristicDefinition>\r\n" + 
						 		"   <value>ADX-HOST</value>\r\n" + 
						 		"</characteristicValues>");
						 
						 request = request.replace("<!--adxValue EVCA B-->","<characteristicValues>\r\n" + 
							 		"   <characteristicDefinition>\r\n" + 
							 		"      <attributeCode>fisArrangement</attributeCode>\r\n" + 
							 		"      <attributeId>6040405</attributeId>\r\n" + 
							 		"   </characteristicDefinition>\r\n" + 
							 		"   <value>ADX-CLIENT</value>\r\n" + 
							 		"</characteristicValues>");
					 }
					 
					 if(whichUniAdxHost.equalsIgnoreCase("B"))
					 {
						 request = request.replace("<!--adxValue EVCA A-->","<characteristicValues>\r\n" + 
						 		"   <characteristicDefinition>\r\n" + 
						 		"      <attributeCode>fisArrangement</attributeCode>\r\n" + 
						 		"      <attributeId>6040405</attributeId>\r\n" + 
						 		"   </characteristicDefinition>\r\n" + 
						 		"   <value>ADX-CLIENT</value>\r\n" + 
						 		"</characteristicValues>");
						 
						 request = request.replace("<!--adxValue EVCA B-->","<characteristicValues>\r\n" + 
							 		"   <characteristicDefinition>\r\n" + 
							 		"      <attributeCode>fisArrangement</attributeCode>\r\n" + 
							 		"      <attributeId>6040405</attributeId>\r\n" + 
							 		"   </characteristicDefinition>\r\n" + 
							 		"   <value>ADX-HOST</value>\r\n" + 
							 		"</characteristicValues>");
					 }
					 if(whichUniAdxHost.equalsIgnoreCase("null"))
					 {
						 logger.info("No ADX changes needed");
					 }
			 	 }	
			
			  logger.info("Request after update is : \n"+request);
			 return request;
		 
			// logger.info("CNOD Request of "+orderType+" after adding "+numberOfUnis+" UNI/s is: "+tg.formatResponseXml((data)));
		 }
		

	 
	 
	 // From GetActivitiesOfSG as updateXml --> Done
	 public static String updateXmls(String UpdatedXml, String projId) throws FileNotFoundException, IOException 
	   {
		 logger=LoggerGen.logGen(getClassName());
			UpdatedXml = UpdatedXml.replace("{$projectId$}", projId);
			logger.info("Request After update is :"+UpdatedXml);
			return UpdatedXml;
	   }
	 
	 // From GetProjectId as updateXml --> Done
	 public static String updateXml1(String UpdatedXml, String sgId) throws FileNotFoundException, IOException 
	   {	
		 logger=LoggerGen.logGen(getClassName());
			UpdatedXml = UpdatedXml.replace("{$sgId$}", sgId);
			logger.info("Request After update is :"+UpdatedXml);
			return UpdatedXml;
	   }
	 
	 
	 //UpdateActivityStatus_SG  as updateXml --> Done
	 public static String updateXml2(String UpdatedXml, String planId1, String activityIdName) throws FileNotFoundException, IOException 
	 {	
		 logger=LoggerGen.logGen(getClassName());
		UpdatedXml = UpdatedXml.replace("{$planId$}", planId1);
		UpdatedXml = UpdatedXml.replace("{$activityId$}", activityIdName);
		logger.info("Request After update is :"+UpdatedXml);
	 	return UpdatedXml;
    }

	//UpdateActivityStatus_SG as updateXml --> Not needed
	public static String updateXml2(String UpdatedXml, String planId1, String activityIdName, String errorId) throws FileNotFoundException, IOException 
	 {	
		logger=LoggerGen.logGen(getClassName());
		UpdatedXml = UpdatedXml.replace("{$planId$}", planId1);
		UpdatedXml = UpdatedXml.replace("{$activityId$}", activityIdName);
		UpdatedXml = UpdatedXml.replace("{$errorId$}", errorId);
		logger.info("Request After update is :"+UpdatedXml);
	 	return UpdatedXml;
    }
	
	
	
// From OrderCreationInOCX as updateXml
	public static String[] updateXmlAVPN(String UpdatedXml) throws FileNotFoundException, IOException 
	{	
		logger=LoggerGen.logGen(getClassName());
		String data1[]= new String[3];
	   	Random random = new Random();
		String sorId= "1-AVP"+TriggerXmls.randomString();
		logger.info("SOR ID is:"+sorId);
		int projId1= random.nextInt(1000000);
		String projId= Integer.toString(projId1);
		logger.info("PROJ ID is:"+projId1);
		UpdatedXml = UpdatedXml.replace("{$sorId$}", sorId);
		UpdatedXml = UpdatedXml.replace("{$projId$}", projId);
		logger.info("Request After update is :"+UpdatedXml);
		data1[0]=UpdatedXml;
		data1[1]=sorId;
		data1[2]=projId;
		return data1;
	}
	
	
	public static String updateXmlIST(String UpdatedXml, String SorID, String ProjId) throws FileNotFoundException, IOException 
	{	
		String data1;
		logger=LoggerGen.logGen(getClassName());
		logger.info("SOR ID is:"+SorID);
		
		logger.info("PROJ ID is:"+ProjId);
		UpdatedXml = UpdatedXml.replace("{$sorId$}", SorID);
		UpdatedXml = UpdatedXml.replace("{$projId$}", ProjId);
		logger.info("Request After update is :"+UpdatedXml);
		data1=UpdatedXml;
		//data1[1]=SorID;
		//data1[2]=ProjId;
		return data1;
	}
	
	public static void createKeysAndIds() throws InterruptedException
	{
		Random random = new Random();
		icoreSiteId=Integer.toString(random.nextInt(1000000));
		icorePvcId=Integer.toString(random.nextInt(1000000));
		icoreVpnId=Integer.toString(random.nextInt(1000000));
		eteVpnKey= "VPNH"+Integer.toString(random.nextInt(1000000));
		etePortKey= "PORTH"+Integer.toString(random.nextInt(1000000));
		eteSiteKey= "SITEH"+Integer.toString(random.nextInt(1000000));
		eteAccessKey= "PLIDH"+Integer.toString(random.nextInt(1000000));
		eteSCKey= "SCONH"+Integer.toString(random.nextInt(1000000));
		eteCpeKey= "CPEH"+Integer.toString(random.nextInt(1000000));
		
		keysAndIds.put("eteSiteKey", eteSiteKey);
		logger.info("ETE SITE KEY is : "+eteSiteKey);
		
		keysAndIds.put("etePortKey", etePortKey);
		logger.info("ETE PORT KEY is : "+etePortKey);
		
		keysAndIds.put("eteSCKey", eteSCKey);
		logger.info("ETE SC KEY is : "+eteSCKey);
		
		keysAndIds.put("eteAccessKey", eteAccessKey);
		logger.info("ETE ACCESS KEY is : "+eteAccessKey);
		
		keysAndIds.put("eteVpnKey", eteVpnKey);
		logger.info("ETE VPN KEY is : "+eteVpnKey);
		
		keysAndIds.put("icoreSiteId", icoreSiteId);
		logger.info("ICORE SITE ID is : "+icoreSiteId);
		
		keysAndIds.put("icorePvcId", icorePvcId);
		logger.info("ICORE PVC ID is : "+icorePvcId);
		
		keysAndIds.put("icoreVpnId", icoreVpnId);
		logger.info("ICORE VPN ID is : "+icoreVpnId);
		
		keysAndIds.put("eteCpeKey", eteCpeKey);
		logger.info("ETE CPE KEY is : "+eteCpeKey);
		
		Thread.sleep(30000);
	}
	
	public static HashMap<String,String> getKeysAndIds()
	{
		return keysAndIds;
	}
	
	public static String updateXmlKeys(String UpdatedXml, String typeOfChange) throws FileNotFoundException, IOException 
	{	
		String data1;
		logger.info("Updating SITE Keys and IDs");
		logger=LoggerGen.logGen(getClassName());
		
		HashMap<String,String> keyAndId=getKeysAndIds();
		String xml;
		
		if(typeOfChange.equalsIgnoreCase("CHV0") || typeOfChange.equalsIgnoreCase("NS"))
		{
			xml="null";
			xml=UpdatedXml;
			xml = xml.replace("{$eteSiteKey$}", keyAndId.get("eteSiteKey").toString());
		
			xml = xml.replace("{$etePortKey$}", keyAndId.get("etePortKey").toString());
		
			xml = xml.replace("{$eteSCKey$}", keyAndId.get("eteSCKey").toString());
		
			xml = xml.replace("{$eteAccessKey$}", keyAndId.get("eteAccessKey").toString());
		
			xml = xml.replace("{$eteVpnKey$}", keyAndId.get("eteVpnKey").toString());
		
			xml = xml.replace("{$icoreSiteId$}", keyAndId.get("icoreSiteId").toString());
		
			xml = xml.replace("{$icorePvcId$}", keyAndId.get("icorePvcId").toString());
		
			xml = xml.replace("{$icoreVpnId$}", keyAndId.get("icoreVpnId").toString());
			
			if(OrderCreationInOCX.typeOfOrder.equalsIgnoreCase("MIS"))
				xml = xml.replace("{$eteCpeKey$}", keyAndId.get("eteCpeKey").toString());
			
			UpdatedXml=xml;
			
			xml="null";
		}
		
		else if(typeOfChange.equalsIgnoreCase("CHANGE PORT SPEED"))
		{
			xml="null";
			
			xml=UpdatedXml;
			
			xml = xml.replace("{$eteSiteKey$}", keyAndId.get("eteSiteKey").toString());
			
			xml = xml.replace("{$etePortKey$}", keyAndId.get("etePortKey").toString());
		
			//UpdatedXml = UpdatedXml.replace("{$eteSCKey$}", keyAndId.get("eteSCKey").toString());
		
			xml = xml.replace("{$eteAccessKey$}", keyAndId.get("eteAccessKey").toString());
		
			//UpdatedXml = UpdatedXml.replace("{$eteVpnKey$}", keyAndId.get("eteVpnKey").toString());
		
			xml = xml.replace("{$icoreSiteId$}", keyAndId.get("icoreSiteId").toString());
			
			if(OrderCreationInOCX.typeOfOrder.equalsIgnoreCase("MIS"))
				xml = xml.replace("{$eteCpeKey$}", keyAndId.get("eteCpeKey").toString());
		
			//UpdatedXml = UpdatedXml.replace("{$icorePvcId$}", keyAndId.get("icorePvcId").toString());
		
			//UpdatedXml = UpdatedXml.replace("{$icoreVpnId$}", keyAndId.get("icoreVpnId").toString());
			
			UpdatedXml=xml;
			
			xml="null";
		}
		
		
		
		
		logger.info("Request After updating SITE Keys and IDs for "+typeOfChange+" is :"+UpdatedXml);
		
		data1=UpdatedXml;

		//data1[1]=SorID;
		//data1[2]=ProjId;
		return data1;
	}
	
	public static String updateXmlFulfilment(String UpdatedXml, String orderID, String sgId) throws FileNotFoundException, IOException 
	{	
		String data1;
		logger=LoggerGen.logGen(getClassName());
		UpdatedXml = UpdatedXml.replace("{$orderId$}", orderID);
		UpdatedXml = UpdatedXml.replace("{$sgId$}", sgId);
		logger.info("Request After update is :"+UpdatedXml);
		data1=UpdatedXml;
		return data1;
	}
	
	public static String updateXmlB2CSP(String UpdatedXml, String isAdx) throws FileNotFoundException, IOException 
	{	
		String data1;
		logger=LoggerGen.logGen(getClassName());
		if(isAdx.equalsIgnoreCase("Y"))
		{
			UpdatedXml = UpdatedXml.replace("{$adxCl$}", "ADX-CLIENT");
			UpdatedXml = UpdatedXml.replace("{$adxHost$}","ADX-HOST");
		}
		else
		{
			UpdatedXml = UpdatedXml.replace("{$adxCl$}", "");
			UpdatedXml = UpdatedXml.replace("{$adxHost$}","");
		}
		logger.info("Request After update is :"+UpdatedXml);
		data1=UpdatedXml;
		return data1;
	}
	
	 public static String getClassName()
	 {
		 return UpdateXMLs_HALO.class.getName();
	 }
	 
	/*public static void main(String args[]) throws IOException
	{
		byte[] encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/AddUNI.xml"));
	   	 String  xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/AddUNI.xml";
	   	TriggerXmls tg= new TriggerXmls();
	   	//String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/notifyRequest.xml";
		Document doc= tg.readInputXmls(xmlFileForDoc);
		
		
		//data= new String(Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/AddUNI.txt")));
		//logger.info(data);
		//doc=tg.readXmls(xmlInputFormatted);
		String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
		logger.info(updateXmlASEOD("UNI",request,"2","N","N","Y","Y"));
		//updateXmlASEOD(String orderType,String request, String uniNos ,String stateRegion,String isFttbNeeded,String isADX,String isTSP )
	}*/
}



