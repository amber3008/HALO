package com.amdocs.halo.automation.main;


	
	import java.io.IOException;
import java.util.HashMap;
	import java.util.Map;
	import java.util.Properties;
	import javax.mail.BodyPart;
	import javax.mail.Message;
	import javax.mail.MessagingException;
	import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
	import javax.mail.Transport;
	import javax.mail.internet.InternetAddress;
	import javax.mail.internet.MimeBodyPart;
	import javax.mail.internet.MimeMessage;
	import javax.mail.internet.MimeMultipart;
	/*
	* @author LAVANK
	* @version 01
	* {@docRoot} This class will send an email for telco sanity with status  
	 */
	public class Email_Resport {
	                public static Boolean SendEmail(String logfilename,String recipient,Map<String, String> OrderData,Map<String, Boolean> OrderStatus,String env) throws IOException, InterruptedException, MessagingException {    
	                                Thread.sleep(5000);
	                                Boolean isSuccess = false;
	                              //  String date = GenRandomAttr.getCTDateTime();
	                                
	                  //  String un = System.getProperty("user.name");
	                  //  LogGen.getLogger("Testing").info("Got user name to send an email: " + un);
	                    String sender = "imran.shaikh@amdocs.com";
	                    String ccrecipient1 ="amber.bhatnagar@amdocs.com";
	                    String ccrecipient2 = "sachin.gurav@amdocs.com";    
	                    //LogGen.getLogger("Trackerupdate").info("Got Sender email to prepare email: " + sender);
	                    
	                    //LogGen.getLogger("Trackerupdate").info("Preparing email to CC Recipients : " + ccrecipient1 +"AND: "+ccrecipient2); 
	                    //LogGen.getLogger("Trackerupdate").info("Order Status: " +OrderStatus);
	                    //LogGen.getLogger("Trackerupdate").info("Orders are: "+OrderData);
	                    String Ipagrolloutstatus;if(OrderStatus.get("IPAG1")) {Ipagrolloutstatus = "<td bgcolor='0DF54E'><b> PASSED </b></td>";} else {Ipagrolloutstatus = "<td bgcolor='F3C458'><b> FAILED </b></td>";}
	                    String IPAG1 =OrderData.get("IPAG1");
	                    String NTErolloutstatus; if(OrderStatus.get("NTE")) {NTErolloutstatus = "<td bgcolor='0DF54E'><b> PASSED </b></td>";} else {NTErolloutstatus = "<td bgcolor='F3C458'><b> FAILED </b></td>";}
	                    String NTE =OrderData.get("NTE CLLI");
	                    String EMUXrolloutstatus; if(OrderStatus.get("EMUX")) {EMUXrolloutstatus = "<td bgcolor='0DF54E'><b> PASSED </b></td>";} else {EMUXrolloutstatus = "<td bgcolor='F3C458'><b> FAILED </b></td>";}
	                    String EMUX =OrderData.get("EMUX CLLI");
	                    String TA5Krolloutstatus; if(OrderStatus.get("TA5K")) {TA5Krolloutstatus = "<td bgcolor='0DF54E'><b> PASSED </b></td>";} else {TA5Krolloutstatus = "<td bgcolor='F3C458'><b> FAILED </b></td>";}
	                    String TA5K =OrderData.get("TA5K");
	                    String Ipaglagstatus; if(OrderStatus.get("IPAGLAG")) {Ipaglagstatus = "<td bgcolor='0DF54E'><b> PASSED </b></td>";} else {Ipaglagstatus = "<td bgcolor='F3C458'><b> FAILED </b></td>";}
	                    String IPAGLAGPO =OrderData.get("IPAG LAG");
	                    String Emuxlagstatus; if(OrderStatus.get("EMUXLAG")) {Emuxlagstatus = "<td bgcolor='0DF54E'><b> PASSED </b></td>";} else {Emuxlagstatus = "<td bgcolor='F3C458'><b> FAILED </b></td>";}
	                    String EMUXLAGPO =OrderData.get("EMUX LAG");
	                    String Cnlstatus; if(OrderStatus.get("CNL")) {Cnlstatus = "<td bgcolor='0DF54E'><b> PASSED </b></td>";} else {Cnlstatus = "<td bgcolor='F3C458'><b> FAILED </b></td>";}
	                    String CNLPO =OrderData.get("STANDALONE CNL");
	                 //   LogGen.getLogger("Trackerupdate").info("Middle ");
	                    String Simpleunistatus; if(OrderStatus.get("SUNI")) {Simpleunistatus = "<td bgcolor='0DF54E'><b> PASSED </b></td>";} else {Simpleunistatus = "<td bgcolor='F3C458'><b> FAILED </b></td>";}
	                    String SIMPLEUNIPO =OrderData.get("SIMPLE UNI");
	                    String ComplexUnistatus; if(OrderStatus.get("CUNI")) {ComplexUnistatus = "<td bgcolor='0DF54E'><b> PASSED </b></td>";} else {ComplexUnistatus = "<td bgcolor='F3C458'><b> FAILED </b></td>";}
	                    String COMPLEXUNIPO =OrderData.get("COMPLEX UNI");
	                    String Mptevcstatus; if(OrderStatus.get("MPTEVC")) {Mptevcstatus = "<td bgcolor='0DF54E'><b> PASSED </b></td>";} else {Mptevcstatus = "<td bgcolor='F3C458'><b> FAILED </b></td>";}
	                    String MPTEVCPO =OrderData.get("MPT EVC");
	                    String Ptpevcstatus;if(OrderStatus.get("P2PEVC")) {Ptpevcstatus = "<td bgcolor='0DF54E'><b> PASSED </b></td>";} else {Ptpevcstatus = "<td bgcolor='F3C458'><b> FAILED </b></td>";}
	                    String PTPEVCPO =OrderData.get("P2P EVC");
	               //     LogGen.getLogger("Trackerupdate").info("Preparing email to Recipient : " + recipient);
	                    Properties props = System.getProperties();
	                    props.put("mail.smtp.host", "smtp.gmail.com");

	            		// set the port of socket factory 
	            		props.put("mail.smtp.socketFactory.port", "587");

	            		// set socket factory
	            		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

	            		// set the authentication to true
	            		props.put("mail.smtp.auth", "true");

	            		// set the port of SMTP server
	            		props.put("mail.smtp.port", "587");

	            		// This will handle the complete authentication
	            		Session session = Session.getDefaultInstance(props,

	            				new javax.mail.Authenticator() {

	            					protected PasswordAuthentication getPasswordAuthentication() {

	            					return new PasswordAuthentication("imrshaik@amdocs.com", "Enter the passward");

	            					}

	            				});
	                    props.put("mail.smtp.port", "587");
	                    props.put("mail.smtp.host", "Inpnqrmail.corp.amdocs.com");
	                     
	                    
	                 //   Session session = Session.getDefaultInstance(props, null);
	                    /*String emaireportpath = ".\\test-output\\emailable-report.html";*/
	                 
	                                String emailContent = " Please find below sanity status,  \t\n                                    "
	                                                                + " NOTE: This Sanity Executed with Backend, So please find the sanity orders and status , <br>         \t\n "
	                                                                + "<table width='50%' border='1' align='center'> <th colspan=4 bgcolor='C2EAFA'>"+env+" SANITY STATUS </th>"																	
																	+ "<tr align='center' bgcolor='BAB8BC'><td> <b> Type </b></td> <td><b> Device/Order </b></td> <td><b> Status </b></td><td><b> Order_ID </b></td></tr>"
																	+ "<th colspan=4 bgcolor='C2EAFA'> ADX </th>"
	                                                                + "<tr align='center'><td rowspan =4><b> B2CSP_ASEOD_PR </b></td> <td><b> Client_Order </b></td>"+Ipagrolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
	                                                                + "<tr align='center'><td><b> Primary_HOST </b></td>"+NTErolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
	                                                                + "<tr align='center'><td><b> Secondary_HOST </b></td>"+EMUXrolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"			
	                                                                + "<tr align='center'><td><b> VPN_order </b></td>"+TA5Krolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
	                                                                + "<tr align='center'><td rowspan =4><b> B2CSP_MTPEVC_PR </b></td><td><b> Client_Order </b></td>"+Ipagrolloutstatus+"<td> "+IPAG1+"</td></tr>"
	                                                                + "<tr align='center'><td><b> Primary_HOST </b></td>"+NTErolloutstatus+"<td> "+NTE+"</td></tr>"
	                                                                + "<tr align='center'><td><b> Secondary_HOST </b></td>"+EMUXrolloutstatus+"<td> "+EMUX+"</td></tr>"			
	                                                                + "<tr align='center'><td><b> VPN_order </b></td>"+TA5Krolloutstatus+"<td> "+TA5K+"</td></tr>"
	                                                                + "<tr align='center'><td rowspan =2><b> B2B_PR </b></td> <td><b> Client_Order </b></td>"+Simpleunistatus+"<td>"+SIMPLEUNIPO+"</td></tr>"
	                                                                + "<tr align='center'><td><b> VPN_order </b></td>"+ComplexUnistatus+"<td>"+COMPLEXUNIPO+"</td></tr>"
																	+ "<tr align='center'><td rowspan =4><b> AVPN2CSP_PR </b></td> <td><b> Client_Order </b></td>"+Ipagrolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
	                                                                + "<tr align='center'><td><b> Primary_HOST </b></td>"+NTErolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
	                                                                + "<tr align='center'><td><b> Secondary_HOST </b></td>"+EMUXrolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"			
	                                                                + "<tr align='center'><td><b> VPN_order </b></td>"+TA5Krolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
																	+ "<tr align='center'><td rowspan =4><b> Multiple_SCMC_PR </b></td> <td><b> 1ST_SCMC </b></td>"+Ipagrolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
	                                                                + "<tr align='center'><td><b> 2ND_SCMC </b></td>"+NTErolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
	                                                                + "<tr align='center'><td><b> 3RD_SCMC </b></td>"+EMUXrolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"			
	                                                                + "<tr align='center'><td><b> 4TH_SCMC </b></td>"+TA5Krolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
																	+ "<tr align='center'><td rowspan =1><b> APORT_PR </b></td> <td><b> APORTOrder </b></td>"+Ipagrolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
																	+ "<tr align='center'><td rowspan =4><b> B2CSP_ASEOD_CANCEL </b></td> <td><b> Client_Order </b></td>"+Ipagrolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
	                                                                + "<tr align='center'><td><b> Primary_HOST </b></td>"+NTErolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
	                                                                + "<tr align='center'><td><b> Secondary_HOST </b></td>"+EMUXrolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"			
	                                                                + "<tr align='center'><td><b> VPN_order </b></td>"+TA5Krolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
	                                                                + "<tr align='center'><td rowspan =4><b> B2CSP_MTPEVC_CANCEL </b></td><td><b> Client_Order </b></td>"+Ipagrolloutstatus+"<td> "+IPAG1+"</td></tr>"
	                                                                + "<tr align='center'><td><b> Primary_HOST </b></td>"+NTErolloutstatus+"<td> "+NTE+"</td></tr>"
	                                                                + "<tr align='center'><td><b> Secondary_HOST </b></td>"+EMUXrolloutstatus+"<td> "+EMUX+"</td></tr>"			
	                                                                + "<tr align='center'><td><b> VPN_order </b></td>"+TA5Krolloutstatus+"<td> "+TA5K+"</td></tr>"
	                                                                + "<tr align='center'><td rowspan =2><b> B2B_CANCEL </b></td> <td><b> Client_Order </b></td>"+Simpleunistatus+"<td>"+SIMPLEUNIPO+"</td></tr>"
	                                                                + "<tr align='center'><td><b> VPN_order </b></td>"+ComplexUnistatus+"<td>"+COMPLEXUNIPO+"</td></tr>"
																	+ "<tr align='center'><td rowspan =4><b> AVPN2CSP_CANCEL </b></td> <td><b> Client_Order </b></td>"+Ipagrolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
	                                                                + "<tr align='center'><td><b> Primary_HOST </b></td>"+NTErolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
	                                                                + "<tr align='center'><td><b> Secondary_HOST </b></td>"+EMUXrolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"			
	                                                                + "<tr align='center'><td><b> VPN_order </b></td>"+TA5Krolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
																	+ "<tr align='center'><td rowspan =4><b> Multiple_SCMC_CANCEL </b></td> <td><b> 1ST_SCMC </b></td>"+Ipagrolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
	                                                                + "<tr align='center'><td><b> 2ND_SCMC </b></td>"+NTErolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
	                                                                + "<tr align='center'><td><b> 3RD_SCMC </b></td>"+EMUXrolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"			
	                                                                + "<tr align='center'><td><b> 4TH_SCMC </b></td>"+TA5Krolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
																	+ "<tr align='center'><td rowspan =1><b> APORT_CANCEL </b></td> <td><b> APORTOrder </b></td>"+Ipagrolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
																	+ "<tr align='center'><td rowspan =4><b> B2CSP_ASEOD_CEASE </b></td> <td><b> Client_Order </b></td>"+Ipagrolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
	                                                                + "<tr align='center'><td><b> Primary_HOST </b></td>"+NTErolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
	                                                                + "<tr align='center'><td><b> Secondary_HOST </b></td>"+EMUXrolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"			
	                                                                + "<tr align='center'><td><b> VPN_order </b></td>"+TA5Krolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
	                                                                + "<tr align='center'><td rowspan =4><b> B2CSP_MTPEVC_CEASE </b></td><td><b> Client_Order </b></td>"+Ipagrolloutstatus+"<td> "+IPAG1+"</td></tr>"
	                                                                + "<tr align='center'><td><b> Primary_HOST </b></td>"+NTErolloutstatus+"<td> "+NTE+"</td></tr>"
	                                                                + "<tr align='center'><td><b> Secondary_HOST </b></td>"+EMUXrolloutstatus+"<td> "+EMUX+"</td></tr>"			
	                                                                + "<tr align='center'><td><b> VPN_order </b></td>"+TA5Krolloutstatus+"<td> "+TA5K+"</td></tr>"
	                                                                + "<tr align='center'><td rowspan =2><b> B2B_CEASE </b></td> <td><b> Client_Order </b></td>"+Simpleunistatus+"<td>"+SIMPLEUNIPO+"</td></tr>"
	                                                                + "<tr align='center'><td><b> VPN_order </b></td>"+ComplexUnistatus+"<td>"+COMPLEXUNIPO+"</td></tr>"
																	+ "<tr align='center'><td rowspan =4><b> AVPN2CSP_CEASE </b></td> <td><b> Client_Order </b></td>"+Ipagrolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
	                                                                + "<tr align='center'><td><b> Primary_HOST </b></td>"+NTErolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
	                                                                + "<tr align='center'><td><b> Secondary_HOST </b></td>"+EMUXrolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"			
	                                                                + "<tr align='center'><td><b> VPN_order </b></td>"+TA5Krolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
																	+ "<tr align='center'><td rowspan =4><b> Multiple_SCMC_CEASE </b></td> <td><b> 1ST_SCMC </b></td>"+Ipagrolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
	                                                                + "<tr align='center'><td><b> 2ND_SCMC </b></td>"+NTErolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
	                                                                + "<tr align='center'><td><b> 3RD_SCMC </b></td>"+EMUXrolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"			
	                                                                + "<tr align='center'><td><b> 4TH_SCMC </b></td>"+TA5Krolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
																	+ "<tr align='center'><td rowspan =1><b> APORT_CEASE </b></td> <td><b> APORTOrder </b></td>"+Ipagrolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
																	+ "<tr align='center'><td rowspan =5><b> B2CSP_ASEOD_MACD </b></td> <td><b> Client_Order </b></td>"+Ipagrolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
	                                                                + "<tr align='center'><td><b> Primary_HOST </b></td>"+NTErolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
	                                                                + "<tr align='center'><td><b> Secondary_HOST </b></td>"+EMUXrolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"			
	                                                                + "<tr align='center'><td><b> VPN_order </b></td>"+TA5Krolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
																	+ "<tr align='center'><td><b> MACDOrder </b></td>"+TA5Krolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
	                                                                + "<tr align='center'><td rowspan =5><b> B2CSP_MTPEVC_MACD </b></td><td><b> Client_Order </b></td>"+Ipagrolloutstatus+"<td> "+IPAG1+"</td></tr>"
	                                                                + "<tr align='center'><td><b> Primary_HOST </b></td>"+NTErolloutstatus+"<td> "+NTE+"</td></tr>"
	                                                                + "<tr align='center'><td><b> Secondary_HOST </b></td>"+EMUXrolloutstatus+"<td> "+EMUX+"</td></tr>"			
	                                                                + "<tr align='center'><td><b> VPN_order </b></td>"+TA5Krolloutstatus+"<td> "+TA5K+"</td></tr>"
																	+ "<tr align='center'><td><b> MACDOrder </b></td>"+TA5Krolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
	                                                                + "<tr align='center'><td rowspan =3><b> B2B_MACD </b></td> <td><b> Client_Order </b></td>"+Simpleunistatus+"<td>"+SIMPLEUNIPO+"</td></tr>"
	                                                                + "<tr align='center'><td><b> VPN_order </b></td>"+ComplexUnistatus+"<td>"+COMPLEXUNIPO+"</td></tr>"
																	+ "<tr align='center'><td><b> MACDOrder </b></td>"+TA5Krolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
																	+ "<tr align='center'><td rowspan =5><b> AVPN2CSP_MACD </b></td> <td><b> Client_Order </b></td>"+Ipagrolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
	                                                                + "<tr align='center'><td><b> Primary_HOST </b></td>"+NTErolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
	                                                                + "<tr align='center'><td><b> Secondary_HOST </b></td>"+EMUXrolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"			
	                                                                + "<tr align='center'><td><b> VPN_order </b></td>"+TA5Krolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
																	+ "<tr align='center'><td><b> MACDOrder </b></td>"+TA5Krolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
																	+ "<tr align='center'><td rowspan =5><b> Multiple_SCMC_CEASE </b></td> <td><b> 1ST_SCMC </b></td>"+Ipagrolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
	                                                                + "<tr align='center'><td><b> 2ND_SCMC </b></td>"+NTErolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
	                                                                + "<tr align='center'><td><b> 3RD_SCMC </b></td>"+EMUXrolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"			
	                                                                + "<tr align='center'><td><b> 4TH_SCMC </b></td>"+TA5Krolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
																	+ "<tr align='center'><td><b> MACDOrder </b></td>"+TA5Krolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
																	+ "<tr align='center'><td rowspan =2><b> APORT_MACD </b></td> <td><b> APORTOrder </b></td>"+Ipagrolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
																	+ "<tr align='center'><td><b> MACDOrder </b></td>"+TA5Krolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
																	+ "<tr align='center'><td rowspan =5><b> B2CSP_ASEOD_AMEND </b></td> <td><b> Client_Order </b></td>"+Ipagrolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
																	+ "<tr align='center'><td><b> Primary_HOST </b></td>"+NTErolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
																	+ "<tr align='center'><td><b> Secondary_HOST </b></td>"+EMUXrolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"			
																	+ "<tr align='center'><td><b> VPN_order </b></td>"+TA5Krolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
																	+ "<tr align='center'><td><b> AMENDOrder </b></td>"+TA5Krolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
																	+ "<tr align='center'><td rowspan =5><b> B2CSP_MTPEVC_AMEND </b></td><td><b> Client_Order </b></td>"+Ipagrolloutstatus+"<td> "+IPAG1+"</td></tr>"
																	+ "<tr align='center'><td><b> Primary_HOST </b></td>"+NTErolloutstatus+"<td> "+NTE+"</td></tr>"
																	+ "<tr align='center'><td><b> Secondary_HOST </b></td>"+EMUXrolloutstatus+"<td> "+EMUX+"</td></tr>"			
																	+ "<tr align='center'><td><b> VPN_order </b></td>"+TA5Krolloutstatus+"<td> "+TA5K+"</td></tr>"
																	+ "<tr align='center'><td><b> AMENDOrder </b></td>"+TA5Krolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
																	+ "<tr align='center'><td rowspan =3><b> B2B_AMEND </b></td> <td><b> Client_Order </b></td>"+Simpleunistatus+"<td>"+SIMPLEUNIPO+"</td></tr>"
																	+ "<tr align='center'><td><b> VPN_order </b></td>"+ComplexUnistatus+"<td>"+COMPLEXUNIPO+"</td></tr>"
																	+ "<tr align='center'><td><b> AMENDOrder </b></td>"+TA5Krolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
																	+ "<tr align='center'><td rowspan =5><b> AVPN2CSP_AMEND </b></td> <td><b> Client_Order </b></td>"+Ipagrolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
																	+ "<tr align='center'><td><b> Primary_HOST </b></td>"+NTErolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
																	+ "<tr align='center'><td><b> Secondary_HOST </b></td>"+EMUXrolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"			
																	+ "<tr align='center'><td><b> VPN_order </b></td>"+TA5Krolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
																	+ "<tr align='center'><td><b> AMENDOrder </b></td>"+TA5Krolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
																	+ "<tr align='center'><td rowspan =5><b> Multiple_SCMC_CEASE </b></td> <td><b> 1ST_SCMC </b></td>"+Ipagrolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
																	+ "<tr align='center'><td><b> 2ND_SCMC </b></td>"+NTErolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
																	+ "<tr align='center'><td><b> 3RD_SCMC </b></td>"+EMUXrolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"			
																	+ "<tr align='center'><td><b> 4TH_SCMC </b></td>"+TA5Krolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
																	+ "<tr align='center'><td><b> AMENDOrder </b></td>"+TA5Krolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
																	+ "<tr align='center'><td rowspan =2><b> APORT_AMEND </b></td> <td><b> APORTOrder </b></td>"+Ipagrolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
																	+ "<tr align='center'><td><b> AMENDOrder </b></td>"+TA5Krolloutstatus+"<td> "+SIMPLEUNIPO+"</td></tr>"
																	+"<th colspan=4 bgcolor='C2EAFA'>HALO</th>"
                                                                    + "<tr align='center'><td rowspan =3><b> MIS_OldArch_PR </b></td>" 
                                                                    + "<tr align='center'><td><b> MIS </b></td>"+NTErolloutstatus+"<td> "+NTE+"</td></tr>"
                                                                    + "<tr align='center'><td><b> MIS_Access </b></td>"+EMUXrolloutstatus+"<td> "+EMUX+"</td></tr>"
																	+ "<tr align='center'><td rowspan =3><b> MIS_OldArch_AMEND </b></td>" 
                                                                    + "<tr align='center'><td><b> MIS </b></td>"+NTErolloutstatus+"<td> "+NTE+"</td></tr>"
                                                                    + "<tr align='center'><td><b> MIS_Access </b></td>"+EMUXrolloutstatus+"<td> "+EMUX+"</td></tr>"
																	+ "<tr align='center'><td rowspan =3><b> MIS_OldArch_CANCEL </b></td>" 
                                                                    + "<tr align='center'><td><b> MIS </b></td>"+NTErolloutstatus+"<td> "+NTE+"</td></tr>"
                                                                    + "<tr align='center'><td><b> MIS_Access </b></td>"+EMUXrolloutstatus+"<td> "+EMUX+"</td></tr>"
																	+ "<tr align='center'><td rowspan =4><b> IPFLEX_OldArch_PR </b></td>"
                                                                    + "<tr align='center'><td><b> MIS </b></td>"+NTErolloutstatus+"<td> "+NTE+"</td></tr>"
                                                                    + "<tr align='center'><td><b> MIS_Access </b></td>"+EMUXrolloutstatus+"<td> "+EMUX+"</td></tr>"      
                                                                    + "<tr align='center'><td><b> IPFLEX </b></td>"+EMUXrolloutstatus+"<td> "+EMUX+"</td></tr>"
																	+ "<tr align='center'><td rowspan =4><b> IPFLEX_OldArch_AMEND </b></td>"
                                                                    + "<tr align='center'><td><b> MIS </b></td>"+NTErolloutstatus+"<td> "+NTE+"</td></tr>"
                                                                    + "<tr align='center'><td><b> MIS_Access</b></td>"+EMUXrolloutstatus+"<td> "+EMUX+"</td></tr>"      
                                                                    + "<tr align='center'><td><b> IPFLEX </b></td>"+EMUXrolloutstatus+"<td> "+EMUX+"</td></tr>"
																	+ "<tr align='center'><td rowspan =4><b> IPFLEX_OldArch_CANCEL </b></td>"
                                                                    + "<tr align='center'><td><b> MIS </b></td>"+NTErolloutstatus+"<td> "+NTE+"</td></tr>"
                                                                    + "<tr align='center'><td><b> MIS_Access </b></td>"+EMUXrolloutstatus+"<td> "+EMUX+"</td></tr>"      
                                                                    + "<tr align='center'><td><b> IPFLEX </b></td>"+EMUXrolloutstatus+"<td> "+EMUX+"</td></tr>"
																    + "<tr align='center'><td rowspan =6><b> MIS_NewArch_PR </b></td>"
                                                                    + "<tr align='center'><td><b> Site_Config </b></td>"+NTErolloutstatus+"<td> "+NTE+"</td></tr>"
                                                                    + "<tr align='center'><td><b> Access </b></td>"+EMUXrolloutstatus+"<td> "+EMUX+"</td></tr>"      
                                                                    + "<tr align='center'><td><b> PORT </b></td>"+EMUXrolloutstatus+"<td> "+EMUX+"</td></tr>"
																    + "<tr align='center'><td><b> Service_Connection </b></td>"+TA5Krolloutstatus+"<td> "+TA5K+"</td></tr>"
																    + "<tr align='center'><td><b> CPE </b></td>"+TA5Krolloutstatus+"<td> "+TA5K+"</td></tr>"
																	+ "<tr align='center'><td rowspan =6><b> MIS_NewArch_AMEND </b></td>"
                                                                    + "<tr align='center'><td><b> Site_Config </b></td>"+NTErolloutstatus+"<td> "+NTE+"</td></tr>"
                                                                    + "<tr align='center'><td><b> Access </b></td>"+EMUXrolloutstatus+"<td> "+EMUX+"</td></tr>"      
                                                                    + "<tr align='center'><td><b> PORT </b></td>"+EMUXrolloutstatus+"<td> "+EMUX+"</td></tr>"
																    + "<tr align='center'><td><b> Service_Connection </b></td>"+TA5Krolloutstatus+"<td> "+TA5K+"</td></tr>"
																    + "<tr align='center'><td><b> CPE </b></td>"+TA5Krolloutstatus+"<td> "+TA5K+"</td></tr>"
																	+ "<tr align='center'><td rowspan =6><b> MIS_NewArch_CANCEL </b></td>"
                                                                    + "<tr align='center'><td><b> Site_Config </b></td>"+NTErolloutstatus+"<td> "+NTE+"</td></tr>"
                                                                    + "<tr align='center'><td><b> Access </b></td>"+EMUXrolloutstatus+"<td> "+EMUX+"</td></tr>"      
                                                                    + "<tr align='center'><td><b> PORT </b></td>"+EMUXrolloutstatus+"<td> "+EMUX+"</td></tr>"
																    + "<tr align='center'><td><b> Service_Connection </b></td>"+TA5Krolloutstatus+"<td> "+TA5K+"</td></tr>"
																    + "<tr align='center'><td><b> CPE </b></td>"+TA5Krolloutstatus+"<td> "+TA5K+"</td></tr>"
																    + "<tr align='center'><td rowspan =7><b> IPFLEX_NewArch_PR </b></td>" 
                                                                    + "<tr align='center'><td><b> Site_Config </b></td>"+NTErolloutstatus+"<td> "+NTE+"</td></tr>"
                                                                    + "<tr align='center'><td><b> Access </b></td>"+EMUXrolloutstatus+"<td> "+EMUX+"</td></tr>"      
                                                                    + "<tr align='center'><td><b> PORT </b></td>"+EMUXrolloutstatus+"<td> "+EMUX+"</td></tr>"
																    + "<tr align='center'><td><b> Service_Connection </b></td>"+TA5Krolloutstatus+"<td> "+TA5K+"</td></tr>"
																    + "<tr align='center'><td><b> CPE </b></td>"+TA5Krolloutstatus+"<td> "+TA5K+"</td></tr>"
																    + "<tr align='center'><td><b> IPFLEX </b></td>"+TA5Krolloutstatus+"<td> "+TA5K+"</td></tr>"
																	+ "<tr align='center'><td rowspan =7><b> IPFLEX_NewArch_AMEND </b></td>" 
                                                                    + "<tr align='center'><td><b> Site_Config </b></td>"+NTErolloutstatus+"<td> "+NTE+"</td></tr>"
                                                                    + "<tr align='center'><td><b> Access </b></td>"+EMUXrolloutstatus+"<td> "+EMUX+"</td></tr>"      
                                                                    + "<tr align='center'><td><b> PORT </b></td>"+EMUXrolloutstatus+"<td> "+EMUX+"</td></tr>"
																    + "<tr align='center'><td><b> Service_Connection </b></td>"+TA5Krolloutstatus+"<td> "+TA5K+"</td></tr>"
																    + "<tr align='center'><td><b> CPE </b></td>"+TA5Krolloutstatus+"<td> "+TA5K+"</td></tr>"
																    + "<tr align='center'><td><b> IPFLEX </b></td>"+TA5Krolloutstatus+"<td> "+TA5K+"</td></tr>"
																	+ "<tr align='center'><td rowspan =7><b> IPFLEX_NewArch_CANCEL </b></td>" 
                                                                    + "<tr align='center'><td><b> Site_Config </b></td>"+NTErolloutstatus+"<td> "+NTE+"</td></tr>"
                                                                    + "<tr align='center'><td><b> Access </b></td>"+EMUXrolloutstatus+"<td> "+EMUX+"</td></tr>"      
                                                                    + "<tr align='center'><td><b> PORT </b></td>"+EMUXrolloutstatus+"<td> "+EMUX+"</td></tr>"
																    + "<tr align='center'><td><b> Service_Connection </b></td>"+TA5Krolloutstatus+"<td> "+TA5K+"</td></tr>"
																    + "<tr align='center'><td><b> CPE </b></td>"+TA5Krolloutstatus+"<td> "+TA5K+"</td></tr>"
																    + "<tr align='center'><td><b> IPFLEX </b></td>"+TA5Krolloutstatus+"<td> "+TA5K+"</td></tr>"
																    + "<tr align='center'><td rowspan =5><b> AVPN_PR </b></td>"
                                                                    + "<tr align='center'><td><b> Site_Config </b></td>"+NTErolloutstatus+"<td> "+NTE+"</td></tr>"
                                                                    + "<tr align='center'><td><b> Access </b></td>"+EMUXrolloutstatus+"<td> "+EMUX+"</td></tr>"      
                                                                    + "<tr align='center'><td><b> PORT </b></td>"+EMUXrolloutstatus+"<td> "+EMUX+"</td></tr>"
																    + "<tr align='center'><td><b> Service_Connection </b></td>"+TA5Krolloutstatus+"<td> "+TA5K+"</td></tr>"
																	+ "<tr align='center'><td rowspan =5><b> AVPN_AMEND </b></td>"
                                                                    + "<tr align='center'><td><b> Site_Config </b></td>"+NTErolloutstatus+"<td> "+NTE+"</td></tr>"
                                                                    + "<tr align='center'><td><b> Access </b></td>"+EMUXrolloutstatus+"<td> "+EMUX+"</td></tr>"      
                                                                    + "<tr align='center'><td><b> PORT </b></td>"+EMUXrolloutstatus+"<td> "+EMUX+"</td></tr>"
																    + "<tr align='center'><td><b> Service_Connection </b></td>"+TA5Krolloutstatus+"<td> "+TA5K+"</td></tr>"
																	+ "<tr align='center'><td rowspan =5><b> AVPN_CANCEL </b></td>"
                                                                    + "<tr align='center'><td><b> Site_Config </b></td>"+NTErolloutstatus+"<td> "+NTE+"</td></tr>"
                                                                    + "<tr align='center'><td><b> Access </b></td>"+EMUXrolloutstatus+"<td> "+EMUX+"</td></tr>"      
                                                                    + "<tr align='center'><td><b> PORT </b></td>"+EMUXrolloutstatus+"<td> "+EMUX+"</td></tr>"
																    + "<tr align='center'><td><b> Service_Connection </b></td>"+TA5Krolloutstatus+"<td> "+TA5K+"</td></tr>"
																	+ "<tr align='center'><td rowspan =2><b> BOE_PR </b></td>"
                                                                    + "<tr align='center'><td><b> IPFLEX </b></td>"+NTErolloutstatus+"<td> "+NTE+"</td></tr>"
																	+ "<tr align='center'><td rowspan =2><b> BOE_AMEND </b></td>"
                                                                    + "<tr align='center'><td><b> IPFLEX </b></td>"+NTErolloutstatus+"<td> "+NTE+"</td></tr>"
																	+ "<tr align='center'><td rowspan =2><b> BOE_CANCEL </b></td>"
                                                                    + "<tr align='center'><td><b> IPFLEX </b></td>"+NTErolloutstatus+"<td> "+NTE+"</td></tr>"
																	+ "<tr align='center'><td rowspan =7><b> MACD_ChangePortSpeed </b></td>" 
                                                                    + "<tr align='center'><td><b> Site_Config_v0 </b></td>"+NTErolloutstatus+"<td> "+NTE+"</td></tr>"
                                                                    + "<tr align='center'><td><b> Access_v0 </b></td>"+EMUXrolloutstatus+"<td> "+EMUX+"</td></tr>"      
                                                                    + "<tr align='center'><td><b> PORT_v0 </b></td>"+EMUXrolloutstatus+"<td> "+EMUX+"</td></tr>"
																    + "<tr align='center'><td><b> Service_Connection_v0 </b></td>"+TA5Krolloutstatus+"<td> "+TA5K+"</td></tr>"
																	+ "<tr align='center'><td><b> Site_Config_v1 </b></td>"+NTErolloutstatus+"<td> "+NTE+"</td></tr>"
                                                                    + "<tr align='center'><td><b> PORT_v1 </b></td>"+EMUXrolloutstatus+"<td> "+EMUX+"</td></tr></table"
	                                                                + "\t\n <br> Thanks and Regards, \t\n<br>  Imran Shaikh.";
	                                System.out.println(emailContent);
	                             //   LogGen.getLogger("Trackerupdate").info("Preparing email with given input: " + date + " ,");
	                                MimeMessage message = new MimeMessage(session);
	                                message.setFrom(new InternetAddress(sender));
	                                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
	                                message.addRecipient(Message.RecipientType.CC, new InternetAddress(ccrecipient1));
	                                message.addRecipient(Message.RecipientType.CC, new InternetAddress(ccrecipient2));
	                                message.setSubject("CANOPI:AUTOMATION: "+env+" :Hallo Gamma Status Report -- "+" EST");
	                                
	                                BodyPart messageBodyPart1 = new MimeBodyPart();  
	                                messageBodyPart1.setContent(emailContent,"text/html"); 
	                                
	                                //MimeBodyPart messageBodyPart2 = new MimeBodyPart(); 
	                                
	                                /*String filename = ".\\logs\\"+logfilename;//change accordingly  
	                        DataSource source = new FileDataSource(filename);  
	                        messageBodyPart2.setDataHandler(new DataHandler(source));  
	                        messageBodyPart2.setFileName(filename);*/
	                                
	                        Multipart multipart = new MimeMultipart();  
	                        multipart.addBodyPart(messageBodyPart1);  
	                        //multipart.addBodyPart(messageBodyPart2);  
	                        
	                        message.setContent(multipart); 
	                        Transport.send(message);
	                                isSuccess = Boolean.valueOf(true);
	                               // LogGen.getLogger("Trackerupdate").info("Mail successfully sent to: " + recipient);
	                               
	                    return isSuccess;
	                  }
	                	@org.testng.annotations.Test
	                  public static void test() throws IOException, InterruptedException, MessagingException {
	                                  Map<String, String> OrderData =new HashMap();
	                                  OrderData.put("LOCATION", "LocationClli");OrderData.put("IPAG1", "IpagDeviceClli1");OrderData.put("IPAG2", "IpagDeviceClli2");
	                                                OrderData.put("NTE CLLI","NteDeviceClli");OrderData.put("EMUX CLLI","EmuxDeviceClli");OrderData.put("TA5K","AdtranDeviceClli");
	                                                OrderData.put("IPAG LAG","IPAGLAGProjectOrder");OrderData.put("EMUX LAG","EMUXLAGProjectOrder");
	                                                OrderData.put("STANDALONE CNL","CNLProjectOrder");OrderData.put("EMUX LAG","EMUXLAGProjectOrder");
	                                                OrderData.put("SIMPLE UNI","SimpleUniProjectOrder");OrderData.put("COMPLEX UNI","ComplexUniProjectOrder");
	                                                OrderData.put("MPT EVC","mptEvcPO");OrderData.put("P2P EVC","p2pEvcPO");
	                                                
	                                                Map<String, Boolean> OrderStatus =new HashMap();
	                                                OrderStatus.put("IPAG1", true); OrderStatus.put("IPAG2", true); OrderStatus.put("NTE", true);
	                                                OrderStatus.put("EMUX", false); OrderStatus.put("TA5K", false);
	                                                OrderStatus.put("IPAGLAG", true); OrderStatus.put("EMUXLAG", false);
	                                                OrderStatus.put("CNL", true); OrderStatus.put("SUNI", true);
	                                                OrderStatus.put("CUNI", true); OrderStatus.put("MPTEVC", true);OrderStatus.put("P2PEVC", true);
	                                                
	                                  SendEmail("SanityTest_ST2.log","imrshaik@amdocs.com",OrderData,OrderStatus,"IT9");
	                  }
	                }



