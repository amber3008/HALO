package com.amdocs.halo.automation.base;

//import static io.restassured.RestAssured.given;

//import java.io.BufferedReader;
//import java.io.ByteArrayInputStream;
//import java.io.DataOutputStream;
import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileReader;
import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

//import io.restassured.http.ContentType;

/**
 * @author Amber Bhatnagar
 * @since 2020
 * @version 1
 * 
 * A base class to parse SOAP xml in a URL 
 *  
 */


public class ParseXmls
{
	public Document readInputXmls(String file)
	{
		File xmlFile = new File(file);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		Document doc = null;
		try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
		} catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
		return doc;
	}
	
	public static Document loadXMLString(String response) throws Exception
	{
	    DocumentBuilderFactory dbf =DocumentBuilderFactory.newInstance();
	    DocumentBuilder db = dbf.newDocumentBuilder();
	    InputSource is = new InputSource(new StringReader(response));

	    return db.parse(is);
	}
	
	public String retreiveXmlInputDocumentToXmlFile(Document xmlDocument)
	{
	    TransformerFactory tf = TransformerFactory.newInstance();
	    Transformer transformer;
	    String xmlString= null;
	    try 
	    {
	        transformer = tf.newTransformer();      
	        StringWriter writer = new StringWriter();
	        transformer.transform(new DOMSource(xmlDocument), new StreamResult(writer));
	        xmlString = writer.getBuffer().toString();   
	    } 
	    catch (TransformerException e) 
	    {
	        e.printStackTrace();
	    }
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
	    return xmlString;
	}
	
	
	
}
