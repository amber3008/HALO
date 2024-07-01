package com.amdocs.halo.automation.base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;

public class UpdateXMLs_GAMMA 
{
	public static Logger logger = null;
	
	public static String getClassName()
	 {
		 return UpdateXMLs_GAMMA.class.getName();
	 }
	
	 public static String updateXML_B2CSP(String request,String apId) throws InterruptedException, FileNotFoundException, IOException
	 {
		 logger=LoggerGen.logGen(getClassName());
		 request=request.replace("{$apId$}",apId);
		 String updateXML=null;
		 updateXML=request; 
		 logger.info("Updated XML is : \n"+updateXML); 
		 Thread.sleep(6000); 
		 return updateXML;
	 }
	 public static String updateXmls(String UpdatedXml, String projId) throws FileNotFoundException, IOException 
	   {
		 logger=LoggerGen.logGen(getClassName());
			UpdatedXml = UpdatedXml.replace("{$projectId$}", projId);
			logger.info("Request After update is :"+UpdatedXml);
			return UpdatedXml;
	   }
	 
	 public static String updateNotifyRequest(String request,String orderType) throws InterruptedException, FileNotFoundException, IOException
	 {
		 logger=LoggerGen.logGen(getClassName());
		 request=request.replace("{$orderType$}",orderType);
		 String updateXML=null;
		 updateXML=request; 
		 logger.info("Updated XML is : \n"+updateXML); 
		 Thread.sleep(6000); 
		 return updateXML;
	 }
	 
	 public static String updateEVCRequest(String request,String ctkID) throws InterruptedException, FileNotFoundException, IOException
	 {
		 logger=LoggerGen.logGen(getClassName());
		 request=request.replace("{$ctkID$}",ctkID);
		 String updateXML=null;
		 updateXML=request; 
		 logger.info("Updated XML is : \n"+updateXML); 
		 Thread.sleep(6000); 
		 return updateXML;
	 }
	 
	 public static String updateBTOCSPRequest(String request,String cktID,String ptl) throws InterruptedException, FileNotFoundException, IOException
	 {
		 logger=LoggerGen.logGen(getClassName());
		 String request1=request.replace("{$ctkID$}",cktID);
		 String request2=request1.replace("{$ptl$}",ptl);
		 String updateXML=null;
		 updateXML=request2; 
		 logger.info("Updated XML is : \n"+updateXML);  
		 Thread.sleep(6000); 
		 return updateXML;
	 }
	 public static String updateRequestStartOrderProcessing(String request,String sop) throws InterruptedException, FileNotFoundException, IOException
	 {
		 logger=LoggerGen.logGen(getClassName());
		 request=request.replace("{$poId$}",sop);
		 String updateXML=null;
		 updateXML=request; 
		 logger.info("Updated XML is : \n"+updateXML); 
		 Thread.sleep(6000); 
		 return updateXML;
	 }
	 
	 public static String updateXML_CIR_REQ(String request,String apId) throws InterruptedException, FileNotFoundException, IOException
	 {
		 logger=LoggerGen.logGen(getClassName());
		 request=request.replace("{$apId$}",apId);
		 String updateXML=null;
		 updateXML=request; 
		 logger.info("Updated XML is : \n"+updateXML); 
		 Thread.sleep(6000); 
		 return updateXML;
	 }
	 
	 public static String updateXML_Disconnect_VPN(String request,String apId,String apId1,String apId2,String apId3) throws InterruptedException, FileNotFoundException, IOException
	 {
		 logger=LoggerGen.logGen(getClassName());
		 String request1=request.replace("{$apId$}",apId);
		 String request2=request1.replace("{$apId1$}",apId1);
		 String request3=request2.replace("{$apId2$}",apId2);
		 String request4=request3.replace("{$apId3$}",apId3);
		 String updateXML=null;
		 updateXML=request4; 
		 logger.info("Updated XML is : \n"+updateXML); 
		 Thread.sleep(6000); 
		 return updateXML;
	 }
	 public static String updateXML_Disconnect_TO_API(String request,String apId,String apId1) throws InterruptedException, FileNotFoundException, IOException
	 {
		 logger=LoggerGen.logGen(getClassName());
		 String request1=request.replace("{$apId$}",apId);
		 String request2=request1.replace("{$apId1$}",apId1);
		 String updateXML=null;
		 updateXML=request2; 
		 logger.info("Updated XML is : \n"+updateXML); 
		 Thread.sleep(6000); 
		 return updateXML;
	 }
	 public static String updateXML_Cancel_REQ(String request,String poId) throws InterruptedException, FileNotFoundException, IOException
	 {
		 logger=LoggerGen.logGen(getClassName());
		 request=request.replace("{$poId$}",poId);
		 String updateXML=null;
		 updateXML=request; 
		 logger.info("Updated XML is : \n"+updateXML); 
		 Thread.sleep(6000); 
		 return updateXML;
	 }
	 
	 public static String updateAmendRequest(String request,String oID,String apID) throws InterruptedException, FileNotFoundException, IOException
	 {
		 logger=LoggerGen.logGen(getClassName());
		 String request1=request.replace("{$oID$}",oID);
		 String request2=request1.replace("{$aID$}",apID);
		 String updateXML=null;
		 updateXML=request2; 
		 logger.info("Updated XML is : \n"+updateXML);  
		 Thread.sleep(6000); 
		 return updateXML;
	 }
	 
	 public static String updateXML_Disconnct_Single(String request,String apId) throws InterruptedException, FileNotFoundException, IOException
	 {
		 logger=LoggerGen.logGen(getClassName());
		 request=request.replace("{$apId$}",apId);
		 String updateXML=null;
		 updateXML=request; 
		 logger.info("Updated XML is : \n"+updateXML); 
		 Thread.sleep(6000); 
		 return updateXML;
	 }
	 
	 public static String updateXML_GOD(String request,String oID) throws InterruptedException, FileNotFoundException, IOException
	 {
		 logger=LoggerGen.logGen(getClassName());
		 request=request.replace("{$oID$}",oID);
		 String updateXML=null;
		 updateXML=request; 
		 logger.info("Updated XML is : \n"+updateXML); 
		 Thread.sleep(6000); 
		 return updateXML;
	 }
	 
	 public static String updateXML_APORTCH(String request,String apId) throws InterruptedException, FileNotFoundException, IOException
	 {
		 logger=LoggerGen.logGen(getClassName());
		 logger.info(apId);
		 request=request.replace("{$apId$}",apId);
		// logger.info(request);
		 String updateXML=null;
		 updateXML=request; 
		 logger.info("Updated XML is : \n"+updateXML); 
		 Thread.sleep(6000); 
		 return updateXML;
	 }
	 
	 public static String updateXML_RPOD(String request,String orderId) throws InterruptedException, FileNotFoundException, IOException
	 {
		 logger=LoggerGen.logGen(getClassName());
		 System.out.println(orderId);
		 
		 request=request.replace("{$orderId$}",orderId);
		//logger.info(request);
		 String updateXML=null;
		 updateXML=request; 
		 logger.info("Updated XML is : \n"+updateXML); 
		 Thread.sleep(6000); 
		 return updateXML;
	 }
	 
	 public static String updateXML_RCPCREQ(String request,String catalogOfferID) throws InterruptedException, FileNotFoundException, IOException
	 {
		 logger=LoggerGen.logGen(getClassName());
		 logger.info(catalogOfferID);
		 request=request.replace("{$catalogOfferID$}",catalogOfferID);
		// logger.info(request);
		 String updateXML=null;
		 updateXML=request; 
		 logger.info("Updated XML is : \n"+updateXML); 
		 Thread.sleep(6000); 
		 return updateXML;
	 }
	 
	 
	 public static String updateBTOCSPRequest_test(String request,String request1) throws InterruptedException, FileNotFoundException, IOException
	 {
		 logger=LoggerGen.logGen(getClassName());
		  request=request.replace("<!-- to change client -->",request1);
		
		 String updateXML=null;
		 updateXML=request; 
		 logger.info("Updated XML is : \n"+updateXML);  
		 Thread.sleep(6000); 
		 return updateXML;
	 }
	 
	 public static String updateXml1(String UpdatedXml, String sgId) throws FileNotFoundException, IOException 
	   {	
		 logger=LoggerGen.logGen(getClassName());
			UpdatedXml = UpdatedXml.replace("{$sgId$}", sgId);
			logger.info("Request After update is :"+UpdatedXml);
			return UpdatedXml;
	   }
	 
	
	 public static String updateXML_NotifyOn(String request, String orderActionId) throws IOException, TransformerException, InterruptedException
	 {	 
		 logger=LoggerGen.logGen(getClassName());
			 String updateXml=null;
			
			 request = request.replace("{$orderActionId$}",orderActionId);
			 
			 updateXml=request;
			 logger.info("Updated XML is : \n"+updateXml);
			 Thread.sleep(6000);
		return updateXml;	 
	 
}
	 public static String updateXML_DISCONNECT(String request, String assignProductId) throws IOException, TransformerException, InterruptedException
	 {	 
		 logger=LoggerGen.logGen(getClassName());
			 String updateXml=null;
			
			 request = request.replace("{$assignProductId$}",assignProductId);
			 
			 updateXml=request;
			 logger.info("Updated XML is : \n"+updateXml);
			 Thread.sleep(6000);
		return updateXml;	 
	 
      }
	 public static String updateXML_Cancel(String request, String productOrderItemId) throws IOException, TransformerException, InterruptedException
	 {	 
		 logger=LoggerGen.logGen(getClassName());
			 String updateXml=null;
			
			 request = request.replace("{$productOrderItemId$}",productOrderItemId);
			 
			 updateXml=request;
			 logger.info("Updated XML is : \n"+updateXml);
			 Thread.sleep(6000);
		return updateXml;	 
	 
      }
	
	 public static String updateSAREA_Amend(String request,String productOrderItemId, String assignedProductId) throws IOException, TransformerException, InterruptedException
	 {	 logger=LoggerGen.logGen(getClassName());
			 
		 String updateXml=null;
		String request1=null;
		 request = request.replace("{$productOrderItemId$}",productOrderItemId);
		request1=request.replace("{$assignProductId$}", assignedProductId);
		 updateXml=request1;
		 System.out.println("Updated XML is : \n"+updateXml);
		 //updateXml=request1;
		 Thread.sleep(6000);
	return updateXml;	 	 
	 
	 }
	 
	 public static String updateXmlUNI(String request, String stateRegion) throws IOException, TransformerException
	 {
		 logger=LoggerGen.logGen(getClassName());
		 String updateXml=null;
		
		 request = request.replace("{$stateRegion$}",stateRegion);
		
		 updateXml=request;
		 System.out.println("Updated XML is : \n"+updateXml);
		
	      return updateXml;	
		
	 }
	 
	 public static String updateEVC_Amend(String request,String productOrderItemId, String assignedProductId) throws IOException, TransformerException, InterruptedException
	 {	 logger=LoggerGen.logGen(getClassName());
			 
		 String updateXml=null;
		String request1=null;
		 request = request.replace("{$productOrderItemId$}",productOrderItemId);
		request1=request.replace("{$assignProductId$}", assignedProductId);
		 updateXml=request1;
		 System.out.println("Updated XML is : \n"+updateXml);
		 //updateXml=request1;
		 Thread.sleep(6000);
	return updateXml;	 	 
	 
	 }
	 
	 public static String update_MTP(String request, String productServiceId1, String productServiceId2, String productServiceId3) throws Throwable 
	 {	 
		logger=LoggerGen.logGen(getClassName());
	    String updateXml=null;
		String request1=null;
		
		request = request.replace("{$productServiceId1$}",productServiceId1);
		
		request=request.replace("{$productServiceId2$}", productServiceId2);
		
		String request2=null;
		request2=request.replace("{$productServiceId3$}", productServiceId3);
		
		 updateXml=request2;
		 System.out.println("Updated XML is : \n"+updateXml);
		 //updateXml=request1;
		 Thread.sleep(6000);
	      return updateXml;
	 
	 }
	 
	 public static String update_PTP(String request, String productServiceId1, String productServiceId2) throws Throwable 
	 {	 
		logger=LoggerGen.logGen(getClassName());
	    String updateXml=null;
		String request1=null;
	
		 request = request.replace("{$productServiceId1$}",productServiceId1);
		 request1=request.replace("{$productServiceId2$}", productServiceId2);
		 updateXml=request1;
		 System.out.println("Updated XML is : \n"+updateXml);
		 
		 Thread.sleep(6000);
	      return updateXml;
	 
	 }
	 
	 
	 public static String updateAVPNTOCSPRequest(String request) throws InterruptedException, FileNotFoundException, IOException
	 {
		 logger=LoggerGen.logGen(getClassName());
		 String request1=request.replace("<!-- top level -->","\"<topLevelProducts>/r/n\"+\r\n"
		 		+ "                     \"<associationSpec>/r/n\"+\r\n"
		 		+ "                        \"<productSpecsId>69103</productSpecsId>/r/n\"+\r\n"
		 		+ "                     \"</associationSpec>/r/n\"+\r\n"
		 		+ "                     \"<orderItem>/r/n\"+\r\n"
		 		+ "                        \"<contacts>/r/n\"+\r\n"
		 		+ "                           \"<contactType>CUS</contactType>/r/n\"+\r\n"
		 		+ "                           \"<email>hs1650@att.com</email>/r/n\"+\r\n"
		 		+ "                           \"<firstName>+Sagar</firstName>/r/n\"+\r\n"
		 		+ "                           \"<landLinePhone>7736878847</landLinePhone>/r/n\"+\r\n"
		 		+ "                           \"<lastName>/Patankar</lastName>/r/n\"+\r\n"
		 		+ "                           \"<mobilePhone>9856987747</mobilePhone>/r/n\"+\r\n"
		 		+ "                        \"</contacts>/r/n\"+\r\n"
		 		+ "                        \"<contacts>/r/n\"+\r\n"
		 		+ "                           \"<contactType>BUS</contactType>/r/n\"+\r\n"
		 		+ "                           \"<email>hs1650@att.com</email>/r/n\"+\r\n"
		 		+ "                           \"<firstName>Sagar</firstName>/r/n\"+\r\n"
		 		+ "                           \"<landLinePhone>7736878847</landLinePhone>/r/n\"+\r\n"
		 		+ "                           \"<lastName>Patankar</lastName>/r/n\"+\r\n"
		 		+ "                           \"<mobilePhone>9856987747</mobilePhone>/r/n\"+\r\n"
		 		+ "                        \"</contacts>/r/n\"+\r\n"
		 		+ "                        \"<contacts>/r/n\"+\r\n"
		 		+ "                           \"<contactType>ALT</contactType>/r/n\"+\r\n"
		 		+ "                           \"<email>hs1650@att.com</email>/r/n\"+\r\n"
		 		+ "                           \"<firstName>Sagar</firstName>/r/n\"+\r\n"
		 		+ "                           \"<landLinePhone>7736878847</landLinePhone>/r/n\"+\r\n"
		 		+ "                           \"<lastName>Patankar</lastName>/r/n\"+\r\n"
		 		+ "                           \"<mobilePhone>9856987747</mobilePhone>/r/n\"+\r\n"
		 		+ "                        \"</contacts>/r/n\"+\r\n"
		 		+ "                        \"<contacts>/r/n\"+\r\n"
		 		+ "                           \"<contactType>LCL</contactType>/r/n\"+\r\n"
		 		+ "                           \"<email>hs1650@att.com</email>/r/n\"+\r\n"
		 		+ "                           \"<firstName>Sagar</firstName>/r/n\"+\r\n"
		 		+ "                           \"<landLinePhone>7736878847</landLinePhone>/r/n\"+\r\n"
		 		+ "                           \"<lastName>Patankar</lastName>/r/n\"+\r\n"
		 		+ "                           \"<mobilePhone>9856987747</mobilePhone>/r/n\"+\r\n"
		 		+ "                        \"</contacts>/r/n\"+\r\n"
		 		+ "                        \"<PromoCode>/r/n\"+\r\n"
		 		+ "                           \"<promoCode>PRM112</promoCode>/r/n\"+\r\n"
		 		+ "                           \"<billingDiscountId>12345</billingDiscountId>/r/n\"+\r\n"
		 		+ "                           \"<rateId>RATE123</rateId>/r/n\"+\r\n"
		 		+ "                           \"<discountAmount>111</discountAmount>/r/n\"+\r\n"
		 		+ "                        \"</PromoCode>/r/n\"+\r\n"
		 		+ "                        \"<PromoCode>/r/n\"+\r\n"
		 		+ "                           \"<promoCode>PRM113</promoCode>/r/n\"+\r\n"
		 		+ "                           \"<billingDiscountId>12345</billingDiscountId>/r/n\"+\r\n"
		 		+ "                           \"<rateId>RATE124</rateId>/r/n\"+\r\n"
		 		+ "                           \"<discountAmount>222</discountAmount>/r/n\"+\r\n"
		 		+ "                        \"</PromoCode>/r/n\"+\r\n"
		 		+ "                        \"<PromoCode>/r/n\"+\r\n"
		 		+ "                           \"<promoCode>PRM114</promoCode>/r/n\"+\r\n"
		 		+ "                           \"<billingDiscountId>12645</billingDiscountId>/r/n\"+\r\n"
		 		+ "                           \"<rateId>RATE125</rateId>/r/n\"+\r\n"
		 		+ "                           \"<discountAmount>333</discountAmount>/r/n\"+\r\n"
		 		+ "                        \"</PromoCode>/r/n\"+\r\n"
		 		+ "                        \"<PromoCode>/r/n\"+\r\n"
		 		+ "                           \"<promoCode>PRM115</promoCode>/r/n\"+\r\n"
		 		+ "                           \"<billingDiscountId>12335</billingDiscountId>/r/n\"+\r\n"
		 		+ "                           \"<rateId>RATE114</rateId>/r/n\"+\r\n"
		 		+ "                           \"<discountAmount>444</discountAmount>/r/n\"+\r\n"
		 		+ "                        \"</PromoCode>/r/n\"+\r\n"
		 		+ "                        \"<orderActionType>PR</orderActionType>/r/n\"+\r\n"
		 		+ "                        \"<externalId/>/r/n\"+\r\n"
		 		+ "                        \"<serviceAdddress>/r/n\"+\r\n"
		 		+ "                           \"<locationId>GLID001</locationId>/r/n\"+\r\n"
		 		+ "                           \"<city>CHGO</city>/r/n\"+\r\n"
		 		+ "                           \"<country>US</country>/r/n\"+\r\n"
		 		+ "                           \"<houseNumber>554</houseNumber>/r/n\"+\r\n"
		 		+ "                           \"<isoCountryCode>IL</isoCountryCode>/r/n\"+\r\n"
		 		+ "                           \"<stateName>554</stateName>/r/n\"+\r\n"
		 		+ "                           \"<state>IL</state>/r/n\"+\r\n"
		 		+ "                           \"<streetName>Diversey</streetName>/r/n\"+\r\n"
		 		+ "                           \"<zipCode>60614-77</zipCode>/r/n\"+\r\n"
		 		+ "                           \"<clli8>CHCMILVWN00</clli8>/r/n\"+\r\n"
		 		+ "                        \"</serviceAdddress>/r/n\"+\r\n"
		 		+ "                        \"<orderItemAttributes>/r/n\"+\r\n"
		 		+ "                           \"<notes>PROVIDE</notes>/r/n\"+\r\n"
		 		+ "                           \"<timeZone>tz10849</timeZone>/r/n\"+\r\n"
		 		+ "                        \"</orderItemAttributes>/r/n\"+\r\n"
		 		+ "                        \"<serviceRequiredDate>2021-09-13T18:30:00.000+05:30</serviceRequiredDate>/r/n\"+\r\n"
		 		+ "                     \"</orderItem>/r/n\"+\r\n"
		 		+ "                     \"<topLevelProduct>/r/n\"+\r\n"
		 		+ "                        \"<containedProducts>/r/n\"+\r\n"
		 		+ "                           \"<action>CHANGE</action>/r/n\"+\r\n"
		 		+ "                           \"<associationSpecDefinition>/r/n\"+\r\n"
		 		+ "                              \"<componentServiceType>SCMC</componentServiceType>/r/n\"+\r\n"
		 		+ "                           \"</associationSpecDefinition>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>v4MulticastIndicator</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>70043</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>No</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>v6MulticastIndicator</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>70083</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>No</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>serviceConnectionType</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>70033</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>VPN</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>eteSiteKey</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>67823</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>ETESITEKEY0022</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>bvoipIndicator</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>70053</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>No</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>baseServiceType</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>68283</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>AVPN</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>serviceFlavor</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>67843</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>AVPN ADX</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>eteServiceConnectionKey</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>70093</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                               \"<value>10000001</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>serviceConnectionSpeed</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>71863</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>10000</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>serviceConnectionDesignation</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>70063</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>Hub</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>unitOfSpeed</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>9849</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>KBPS</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>TotalRC</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>8959</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>TOTALRC0022</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>TotalOC</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>8959</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>TOTALOC0022</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                        \"</containedProducts>/r/n\"+\r\n"
		 		+ "                        \"<containedProducts>/r/n\"+\r\n"
		 		+ "                           \"<action>CHANGE</action>/r/n\"+\r\n"
		 		+ "                           \"<associationSpecDefinition>/r/n\"+\r\n"
		 		+ "                              \"<componentServiceType>SCF</componentServiceType>/r/n\"+\r\n"
		 		+ "                           \"</associationSpecDefinition>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>fisArrangement</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>6040405</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>ADX-CLIENT</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>bidirectionalForwardingIndicator</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>73273</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>Yes</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>heartbeatInterval</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>5596295</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>300 ms</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>mnscManagedNetworkSolutionCenter</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>73383</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>Yes</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>dynamicHybridVPNFlag</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>5867415</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>true</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>serviceKey</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>6039705</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>SERVICEKEY0022</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                        \"</containedProducts>/r/n\"+\r\n"
		 		+ "                        \"<containedProducts>/r/n\"+\r\n"
		 		+ "                           \"<action>CHANGE</action>/r/n\"+\r\n"
		 		+ "                           \"<associationSpecDefinition>/r/n\"+\r\n"
		 		+ "                              \"<componentServiceType>ATRNS</componentServiceType>/r/n\"+\r\n"
		 		+ "                           \"</associationSpecDefinition>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>icorePVCID</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>5635135</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                               \"<value>10000001</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>icoreSiteID</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>5635285</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                               \"<value>ICORESITEID3000001</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>CustomerName</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>14809</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>ATest</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "										\"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "											\"<attributeCode>icoreVPNID</attributeCode>/r/n\"+\r\n"
		 		+ "											\"<attributeId>5635145</attributeId>/r/n\"+\r\n"
		 		+ "										\"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                               \"<value>20000001</value>/r/n\"+\r\n"
		 		+ "									\"</characteristicValues>/r/n\"+\r\n"
		 		+ "                        \"</containedProducts>/r/n\"+\r\n"
		 		+ "                        \"<containedProducts>/r/n\"+\r\n"
		 		+ "                           \"<action>ADD</action>/r/n\"+\r\n"
		 		+ "                           \"<associationSpecDefinition>/r/n\"+\r\n"
		 		+ "                              \"<componentServiceType>RTIF</componentServiceType>/r/n\"+\r\n"
		 		+ "                           \"</associationSpecDefinition>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>ipv4RoutingProtocol</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>72243</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>BGP4</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>ipv6RoutingProtocol</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>72253</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>BGP4</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                        \"</containedProducts>/r/n\"+\r\n"
		 		+ "                        \"<containedProducts>/r/n\"+\r\n"
		 		+ "                           \"<action>ADD</action>/r/n\"+\r\n"
		 		+ "                           \"<associationSpecDefinition>/r/n\"+\r\n"
		 		+ "                              \"<componentServiceType>CSPI</componentServiceType>/r/n\"+\r\n"
		 		+ "                           \"</associationSpecDefinition>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>VLANNumber</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>6050495</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>VLAN1234</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>cspInterconnectId</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>6050535</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>CSP1234</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>region</attributeCode>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<attributeId>6050585</attributeId>/r/n\"+\r\n"
		 		+ "                              \"<value>REG456</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>cspName</attributeCode>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<attributeId>6049595</attributeId>/r/n\"+\r\n"
		 		+ "                              \"<value>AZURE</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>accountNumber</attributeCode>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<attributeId>14819</attributeId>/r/n\"+\r\n"
		 		+ "                              \"<value>ACC2345</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                        \"</containedProducts>/r/n\"+\r\n"
		 		+ "                        \"<containedProducts>/r/n\"+\r\n"
		 		+ "                           \"<action>ADD</action>/r/n\"+\r\n"
		 		+ "                           \"<associationSpecDefinition>/r/n\"+\r\n"
		 		+ "                              \"<componentServiceType>HPEND</componentServiceType>/r/n\"+\r\n"
		 		+ "                           \"</associationSpecDefinition>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>icoreSiteID</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>5635285</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>800</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>cspName</attributeCode>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<attributeId>6049595</attributeId>/r/n\"+\r\n"
		 		+ "                              \"<value>AZURE</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                        \"</containedProducts>/r/n\"+\r\n"
		 		+ "                        \"<containedProducts>/r/n\"+\r\n"
		 		+ "                           \"<action>ADD</action>/r/n\"+\r\n"
		 		+ "                           \"<associationSpecDefinition>/r/n\"+\r\n"
		 		+ "                              \"<componentServiceType>VLDT</componentServiceType>/r/n\"+\r\n"
		 		+ "                           \"</associationSpecDefinition>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>vlanStacking</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>73133</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>Yes</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>customerV4Mtu</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>33033</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>1500</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>customerV6Mtu</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>33043</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>2000</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>customerVlanBottomTag</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>73283</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>0022</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>customerVlanTopTag</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>73293</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>0002</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                        \"</containedProducts>/r/n\"+\r\n"
		 		+ "                        \"<containedProducts>/r/n\"+\r\n"
		 		+ "                           \"<action>ADD</action>/r/n\"+\r\n"
		 		+ "                           \"<associationSpecDefinition>/r/n\"+\r\n"
		 		+ "                              \"<componentServiceType>PEND</componentServiceType>/r/n\"+\r\n"
		 		+ "                           \"</associationSpecDefinition>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>etePortKey</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>68593</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>AS/KRFN/106974/SW</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>portTypeLink</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>40353</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>AVPN</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>ppuAddress</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>6039715</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>Address1 207 Laurel Ave South, Address2 Room 1 Floor 2, City Middletown, State NJ, Zip 07748, PortId: AS/KRGN/213246/SW</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>AccountNumber</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>14819</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>ACOUNTNUMBER0022</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>portCircuitID</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>1931</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>AS/KRFN/106974/SW</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                        \"</containedProducts>/r/n\"+\r\n"
		 		+ "                        \"<containedProducts>/r/n\"+\r\n"
		 		+ "                           \"<action>ADD</action>/r/n\"+\r\n"
		 		+ "                           \"<associationSpecDefinition>/r/n\"+\r\n"
		 		+ "                              \"<componentServiceType>IPDT</componentServiceType>/r/n\"+\r\n"
		 		+ "                           \"</associationSpecDefinition>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>wanIpAddressVersion</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>75943</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>IP V4</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>wanIpv6AddressSource</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>74323</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>Customer</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                        \"</containedProducts>/r/n\"+\r\n"
		 		+ "                        \"<containedProducts>/r/n\"+\r\n"
		 		+ "                           \"<action>ADD</action>/r/n\"+\r\n"
		 		+ "                           \"<associationSpecDefinition>/r/n\"+\r\n"
		 		+ "                              \"<componentServiceType>PBGP</componentServiceType>/r/n\"+\r\n"
		 		+ "                           \"</associationSpecDefinition>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>asNumber</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>72913</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>20022</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>asOverrideIndicator</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>72943</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>No</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>localAsNumber</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>72963</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>70022</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>ipv4RestrictiveRoutingIndicator</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>72923</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>Yes</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>ipv6RestrictiveRoutingIndicator</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>72933</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>Yes</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>localAsReplaceAs</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>72953</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>Yes</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>removePrivateAsPeWan</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>72973</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>No</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>routeGroupName</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>85973</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>1</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>isServingSite</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>85983</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>No</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                        \"</containedProducts>/r/n\"+\r\n"
		 		+ "                        \"<containedProducts>/r/n\"+\r\n"
		 		+ "                           \"<action>ADD</action>/r/n\"+\r\n"
		 		+ "                           \"<associationSpecDefinition>/r/n\"+\r\n"
		 		+ "                              \"<componentServiceType>WV6</componentServiceType>/r/n\"+\r\n"
		 		+ "                           \"</associationSpecDefinition>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>v6WanIPAddress</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>73983</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>123.23.45.23</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>v6WanPrefix</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>73993</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>66</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>v6CerIpAddress</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>74003</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>123.23.45.24</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>v6PerIpAddress</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>74013</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>123.23.45.29</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                        \"</containedProducts>/r/n\"+\r\n"
		 		+ "                        \"<containedProducts>/r/n\"+\r\n"
		 		+ "                           \"<action>ADD</action>/r/n\"+\r\n"
		 		+ "                           \"<associationSpecDefinition>/r/n\"+\r\n"
		 		+ "                              \"<componentServiceType>WV4</componentServiceType>/r/n\"+\r\n"
		 		+ "                           \"</associationSpecDefinition>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>v4CerIpAddress</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>73953</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>123.23.45.26</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>v4PerIpAddress</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>73963</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>123.23.45.25</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>v4SubnetMask</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>73973</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>255.255.255.252</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                        \"</containedProducts>/r/n\"+\r\n"
		 		+ "                        \"<containedProducts>/r/n\"+\r\n"
		 		+ "                           \"<action>ADD</action>/r/n\"+\r\n"
		 		+ "                           \"<associationSpecDefinition>/r/n\"+\r\n"
		 		+ "                              \"<componentServiceType>MDA</componentServiceType>/r/n\"+\r\n"
		 		+ "                           \"</associationSpecDefinition>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>ipv4Md5Indicator</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>91093</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>Yes</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>ipv6Md5Indicator</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>91113</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>Yes</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>ipv4Md5Password</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>5602035</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>10</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>ipv6Md5Password</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>5602045</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>10</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                        \"</containedProducts>/r/n\"+\r\n"
		 		+ "                        \"<containedProducts>/r/n\"+\r\n"
		 		+ "                           \"<action>ADD</action>/r/n\"+\r\n"
		 		+ "                           \"<associationSpecDefinition>/r/n\"+\r\n"
		 		+ "                              \"<componentServiceType>COSD</componentServiceType>/r/n\"+\r\n"
		 		+ "                           \"</associationSpecDefinition>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>providerEdgeIngressProfileID</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>10129</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>21393</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>providerEdgeEgressProfileID</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>10119</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>23123</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>cosComplexityIndicator</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>74423</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>Yes</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>cosProfileModel</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>74493</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>6COS</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                        \"</containedProducts>/r/n\"+\r\n"
		 		+ "                        \"<containedProducts>/r/n\"+\r\n"
		 		+ "                           \"<action>ADD</action>/r/n\"+\r\n"
		 		+ "                           \"<action>ADD</action>/r/n\"+\r\n"
		 		+ "                           \"<associationSpecDefinition>/r/n\"+\r\n"
		 		+ "                              \"<componentServiceType>PCCOS</componentServiceType>/r/n\"+\r\n"
		 		+ "                           \"</associationSpecDefinition>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>providerEdgeIngressCos2VPolicing</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>87133</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>Drop</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>providerEdgeIngressCos2Policing</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>87143</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>Remark</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>providerEdgeIngressCos3Policing</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>87153</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>Remark</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>providerEdgeIngressCos5Policing</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>87163</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>Remark</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>providerEdgeEgressCos2VShaping</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>87173</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>Enable</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>providerEdgeEgressCos2Shaping</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>87183</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>Disable</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>providerEdgeEgressCos3Shaping</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>87193</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>Disable</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>providerEdgeEgressCos5Shaping</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>87203</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>Disable</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>providerEdgeEgressCos2VQueuing</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>87223</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>queue-limit</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>providerEdgeEgressCos2Queuing</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>87213</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>WRED</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>providerEdgeEgressCos3Queuing</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>87233</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>WRED</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>providerEdgeEgressCos5Queuing</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>87243</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>WRED</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                        \"</containedProducts>/r/n\"+\r\n"
		 		+ "                        \"<containedProducts>/r/n\"+\r\n"
		 		+ "                           \"<action>ADD</action>/r/n\"+\r\n"
		 		+ "                           \"<associationSpecDefinition>/r/n\"+\r\n"
		 		+ "                              \"<componentServiceType>COSP</componentServiceType>/r/n\"+\r\n"
		 		+ "                           \"</associationSpecDefinition>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>CosElement</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>86453</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>PE Ingress</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>Cos3Percentage</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>86493</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>40</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>Cos4Percentage</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>86503</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>50</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>Cos1Percentage</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>86463</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>10</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>Cos2Percentage</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>86483</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>30</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>/Cos2vPercentage</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>86473</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>20</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>Cos5Percentage</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>86513</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>60</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                        \"</containedProducts>/r/n\"+\r\n"
		 		+ "                        \"<containedProducts>/r/n\"+\r\n"
		 		+ "                           \"<action>ADD</action>/r/n\"+\r\n"
		 		+ "                           \"<associationSpecDefinition>/r/n\"+\r\n"
		 		+ "                              \"<componentServiceType>COSP</componentServiceType>/r/n\"+\r\n"
		 		+ "                           \"</associationSpecDefinition>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>CosElement</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>86453</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>PE Egress</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>Cos3Percentage</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>86493</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>44</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>Cos4Percentage</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>86503</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>55</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>Cos1Percentage</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>86463</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>11</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>Cos2Percentage</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>86483</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>33</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>Cos2vPercentage</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>86473</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>22</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                           \"<characteristicValues>/r/n\"+\r\n"
		 		+ "                              \"<characteristicDefinition>/r/n\"+\r\n"
		 		+ "                                 \"<attributeCode>Cos5Percentage</attributeCode>/r/n\"+\r\n"
		 		+ "                                 \"<attributeId>86513</attributeId>/r/n\"+\r\n"
		 		+ "                              \"</characteristicDefinition>/r/n\"+\r\n"
		 		+ "                              \"<value>66</value>/r/n\"+\r\n"
		 		+ "                           \"</characteristicValues>/r/n\"+\r\n"
		 		+ "                        \"</containedProducts>/r/n\"+\r\n"
		 		+ "                     \"</topLevelProduct>/r/n\"+\r\n"
		 		+ "                  \"</topLevelProducts>");
		
		 String updateXML=request1; 
		// logger.info("Updated XML is : \n"+updateXML);  
		 Thread.sleep(6000); 
		 return updateXML;
	 }
	 
	 
	 
}
