package com.amdocs.halo.automation.base;

//import static io.restassured.RestAssured.given;

//import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
//import java.io.DataOutputStream;
//import java.io.File;
import java.io.IOException;
//import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Paths;
import java.security.SecureRandom;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.regex.*;
//import java.util.Map;
import java.util.Random;

import javax.xml.soap.AttachmentPart;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
//import javax.xml.soap.Node;
//import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
//import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

//import org.apache.commons.io.FileUtils;
//import org.json.JSONObject;
//import org.w3c.dom.Document;
//import org.w3c.dom.NodeList;

//import com.amdocs.halo.automation.main.GetProjectId;
import com.amdocs.halo.automation.main.TriggerNotifyRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

//import io.restassured.http.ContentType;


/**
 * @author Amber Bhatnagar
 * @since 2020
 * @version 1
 * 
 * A  class to push SOAP xml in a URL 
 *  
 */

// getElementFromXml is present in GetProjectId

public class TriggerXmls extends ParseXmls
{
	public static final Random generator = new Random(); 
	public static final String SOR = "ABCD"; 
	public static SecureRandom secureRnd = new SecureRandom();

	
	public String pushXml(byte[] encoded, String url) throws TransformerException
	{
		//StringBuffer response=null;
		String strMsg=null;
		try
		{			 
		    SOAPConnectionFactory soapConnectionFactory =SOAPConnectionFactory.newInstance();
		    SOAPConnection connection = soapConnectionFactory.createConnection();
		    MessageFactory factory = MessageFactory.newInstance();
		    SOAPMessage message = factory.createMessage(new MimeHeaders(), new ByteArrayInputStream(encoded));
		    AttachmentPart attachment = message.createAttachmentPart();
		    attachment.setContent("sm_content", "text/plain");
		    attachment.setContentId("1.9f910338bf0cac0e783bfdec7e53be9237684caa8f8f4e6d@apache.org");
		    message.addAttachmentPart(attachment);
		    SOAPMessage response1 = connection.call(message, url);
		    ByteArrayOutputStream out = new ByteArrayOutputStream();
		    response1.writeTo(out);
		    strMsg = new String(out.toByteArray());
		    }
		    catch (Exception e) 
			{
				 System.out.println(e);
			}
		return strMsg;
	}	
		  
	public String formatResponseXml(String xml) throws TransformerException  
	{	    
		    Source xmlInput = new StreamSource(new StringReader(xml));
		    StringWriter stringWriter = new StringWriter();
		    StreamResult xmlOutput = new StreamResult(stringWriter);
		    TransformerFactory transformerFactory = TransformerFactory.newInstance();
		   
		    Transformer transformer = transformerFactory.newTransformer();
		    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		    transformer.transform(xmlInput, xmlOutput);
		    String xmlString = xmlOutput.getWriter().toString();
		    return xmlString;
	}
	
	public String formatResponseJson(String json)
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		JsonParser jp= new JsonParser();
		JsonElement je = jp.parse(json);
		String prettyJsonString = gson.toJson(je); 
		return prettyJsonString;
		
	}
	
	public static String randomString() 
	{ 
		Random random = new Random();
		StringBuilder sb = new StringBuilder(7); 
		int ranNo= random.nextInt(10000);
		String ranN= Integer.toString(ranNo);
		for (int i = 0; i < 5; i++) 
			sb.append(SOR.toUpperCase().charAt(secureRnd.nextInt(SOR.length()))); 
			return sb.toString()+ranN; 
	}
	
	public static String randomStringOldArch() 
	{ 
		Random random = new Random();
		int ranNo=0;
		StringBuilder sb = new StringBuilder(3); 
		if(TriggerNotifyRequest.typeOfOrder.equalsIgnoreCase("AVPN") || TriggerNotifyRequest.typeOfOrder.equalsIgnoreCase("MIS"))
			ranNo= random.nextInt(10000);
		else
			ranNo= random.nextInt(100);
		String ranN= Integer.toString(ranNo);
		for (int i = 0; i < 3; i++) 
			sb.append(SOR.toUpperCase().charAt(secureRnd.nextInt(SOR.length())));
		System.out.println(sb.toString()+ranN);
			return sb.toString()+ranN; 
	}
	
	public static String randomString10Digit()
	{
		Random random = new Random();
		long r= random.nextLong();
		String s= Long.toString(r);
		String a=java.util.Arrays.toString(s.split("(?<=\\G..........)"));
		System.out.println(a);
		return a;
	}
	
	public static void main(String args[]) throws IOException
	{
		
		TriggerXmls.randomString10Digit();
	}	
	
}
