package com.amdocs.halo.automation.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;

import com.amdocs.halo.automation.base.TriggerXmls;
import com.amdocs.halo.automation.base.UpdateXMLs;
import com.amdocs.halo.automation.utils.GetURLFromExcel;

public class TriggerMOR_OALevel 
{
	 public static String getURL;
	 public static String getRequest;
	 public static String[] sorData= new String[6];
	 public static TriggerNotifyRequest tnr;
	 public static TriggerMOR_OALevel tmor_oa;
	 public static TriggerMOR_SGLevel tmor_sg;
	 public static String orderActionid;
	 public static byte[] encoded;
	 public static String xmlFileForDoc;
	 public static String typeOfOrder;
	   
	 public static String[] getWholeIds() throws IOException, TransformerException
	 {
		  return TriggerMOR_SGLevel.getWholeOrderIds();
	 }
	 public static void triggerMoRequest_OA(String env) throws IOException, TransformerException, InterruptedException
	 {
		   //TriggerXmls tg= new TriggerXmls();
		   tmor_oa= new TriggerMOR_OALevel();
		   sorData = TriggerMOR_OALevel.getWholeIds();
		  // Document doc = null;
		   getURL=GetURLFromExcel.readURLFromExcel("MANAGE_ORDER_REQUEST",env);
		   //if(sorData[5].equalsIgnoreCase("MIS"))
		   //{ 
			   encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/manageOrderRequestOA_NS.xml"));
			   xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/manageOrderRequestOA_NS.xml";
			   tmor_oa.pushRequest(getURL,encoded,xmlFileForDoc);
		  // }
		   /*if(sorData[5].equalsIgnoreCase("AVPN"))
		   {
			   System.out.println("-------------------------Pushing AVPN SL ManageOrder Request for OA---------------------");
			   
			   encoded = Files.readAllBytes(Paths.get("src/main/java/com/amdocs/halo/automation/xmls/manageOrderRequestAVPNSL_NS_OA.xml"));
			   xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/manageOrderRequestAVPNSL_NS_OA.xml";
			   tmor_oa.pushRequest(getURL,encoded,xmlFileForDoc);
			   Thread.sleep(20000);
		   }*/
			
	 }
	 public void pushRequest(String getURL,byte[] encoded, String xmlFileForDoc) throws TransformerException, InterruptedException, FileNotFoundException, IOException
	 {
		    Document doc= null;
		    TriggerXmls tg= new TriggerXmls();
			//Following used only for formatting
			String xmlInput = new String(encoded, StandardCharsets.UTF_8);
			String xmlInputFormatted = tg.formatResponseXml(xmlInput);
			//System.out.println("Input in format is:"+xmlInputFormatted);
			
			//Read xml for Document Class 
			//String xmlFileForDoc="src/main/java/com/amdocs/halo/automation/xmls/manageOrderRequestOA_NS.xml";
			doc= tg.readInputXmls(xmlFileForDoc);
			
			//doc=tg.readXmls(xmlInputFormatted);
			String request= tg.retreiveXmlInputDocumentToXmlFile(doc);
			System.out.println("URL is:"+getURL);
			/*str1[0]=UpdatedXml;
			str1[1]=sgId;
			str1[2]=oaId;
			str1[3]=sorId;
			str1[4]=projId;*/
			
			System.out.println("SORID and Project ID is:"+sorData[3]);
			System.out.println(sorData[4]);
			System.out.println("Request to be updated:"+request);
			orderActionid= sorData[2];
			String data= UpdateXMLs.updateXml(request,sorData[1],sorData[2],sorData[3]);
			String updatedRequest=data;
			byte[] encoded1 =updatedRequest.getBytes();
			//Push xmls
	 		String response=tg.pushXml(encoded1, getURL);
	 		if(sorData[5].equalsIgnoreCase("AVPN"))
	 		Thread.sleep(20000);
	 		System.out.println("Response in format is:"+tg.formatResponseXml(response));
	   }
	   
	  /* public static String updateXml(String UpdatedXml, String sgId, String oaId, String sorId) 
	   {	
			String str1;
			System.out.println("SOR ID is:"+sorId);
			UpdatedXml = UpdatedXml.replace("{$sorId$}", sorId);
			//UpdatedXml = UpdatedXml.replace("{$projId$}", projId);
			UpdatedXml = UpdatedXml.replace("{$sgId$}", sgId);
			UpdatedXml = UpdatedXml.replace("{$oaId$}", oaId);
			System.out.println("Request After update is :"+UpdatedXml);
			str1=UpdatedXml;
			return str1;
		}*/
	   
	   public static String[] checkActivityStatus() throws IOException, TransformerException
		 {
			  return TriggerNotifyRequest.getSorProjId();
		 }
	   
	  public static void main(String args[]) throws IOException, TransformerException, InterruptedException
	   {
		  triggerMoRequest_OA("ST3");
	   }

}
