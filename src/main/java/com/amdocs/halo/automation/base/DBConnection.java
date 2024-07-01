/*
 * package com.amdocs.halo.automation.base;
 * 
 * import java.io.IOException; import java.sql.*;
 * 
 * import org.apache.log4j.Logger;
 * 
 * import com.amdocs.halo.automation.main.TriggerNotifyRequest; import
 * com.amdocs.halo.automation.utils.GetURLFromExcel;
 * 
 * public class DBConnection { public static String host; public static String
 * port; public static String sid; public static String usrNme; public static
 * String pwd; public static Connection connection= null; public static
 * Statement st; public static ResultSet result; public static String
 * columnValue; public static String columnValueCth; public static Logger logger
 * = null; public static TriggerNotifyRequest tnr;
 * 
 * public static String executeQueryForOrderStatus(String orderid, String env)
 * throws ClassNotFoundException, SQLException, IOException {
 * logger=LoggerGen.logGen(getClassName()); connection=initConnection(env);
 * logger.info("Is the connection open: "+!connection.isClosed());
 * 
 * String sql=
 * "select assignment_idx, ctdb_cre_datetime, step_instance_id, state, form_id, display_name from tbassignment where order_action_id ='"
 * +orderid+"' order by assignment_idx desc";
 * logger.info("SQL need to run is :\n"+sql); st= connection.createStatement();
 * result=st.executeQuery(sql); //ResultSetMetaData rsmd = result.getMetaData();
 * int rowNumbers = result.getRow(); boolean areRows =result.next(); if(areRows)
 * { logger.
 * info("---------------Following rows are created in tbassignment------------------------"
 * ); while(result.next()) { for(int i=0;i<=rowNumbers;i++) {
 * 
 * columnValue = result.getString("FORM_ID"); logger.info(columnValue); }
 * 
 * } logger.
 * info("---------------SQL Execution End for tbassignment------------------------"
 * ); } else { logger.info("No Rows created"); }
 * 
 * closeConnection();
 * logger.info("Is the connection open: "+!connection.isClosed()); return
 * columnValue;
 * 
 * }
 * 
 * public static void executeTnrangeTnParameter(String env) throws
 * ClassNotFoundException, SQLException, IOException {
 * logger=LoggerGen.logGen(getClassName()); connection=initConnection(env);
 * logger.info("Is the connection open: "+!connection.isClosed());
 * 
 * String sql1=
 * "update tn_parameter set is_deleted =1 where range_start in ('8987677831') or range_end in ('8987677831')"
 * ; String sql2=
 * "update tn_range set status ='REL' where range_start in ('8987677831') or range_end in ('8987677831')"
 * ;
 * 
 * st= connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.
 * CONCUR_UPDATABLE); st.addBatch(sql2); st.addBatch(sql1); st.executeBatch();
 * 
 * logger.info("SQL executed successfully"); closeConnection();
 * logger.info("Is the connection open: "+!connection.isClosed());
 * 
 * }
 * 
 * 
 * public static String executeQueryForCTH(String sgId, String env) throws
 * ClassNotFoundException, SQLException, IOException, InterruptedException {
 * logger=LoggerGen.logGen(getClassName()); Thread.sleep(120000); logger.
 * info("==================================Checking if CTH created or not================================="
 * ); connection=initConnection(env);
 * logger.info("Is the connection open: "+!connection.isClosed());
 * if(tnr.archType.equalsIgnoreCase("OLD")) Thread.sleep(180000); String sql=
 * "select * from ORD9_TBCTH_ERROR_DETAILS where ORDER_ID='"+sgId+"'";
 * logger.info("SQL need to run is :\n"+sql); st= connection.createStatement();
 * result=st.executeQuery(sql); //ResultSetMetaData rsmd = result.getMetaData();
 * int rowNumbers = result.getRow(); boolean areRows =result.next(); if(areRows)
 * { logger.
 * info("---------------Following CTHs are created in ORD9_TBCTH_ERROR_DETAILS------------------------"
 * ); logger.info(""); while(result.next()) { for(int i=0;i<=rowNumbers;i++) {
 * columnValueCth = result.getString("ERROR_MESSAGE");
 * logger.info(columnValueCth); } } logger.info(""); logger.
 * info("---------------SQL Execution End for ORD9_TBCTH_ERROR_DETAILS------------------------"
 * ); } else { logger.info("No CTH created"); }
 * 
 * closeConnection();
 * logger.info("Is the connection open: "+!connection.isClosed()); return
 * columnValue;
 * 
 * }
 * 
 * 
 * public static Connection initConnection(String env) throws
 * ClassNotFoundException, SQLException, IOException {
 * logger=LoggerGen.logGen(getClassName());
 * Class.forName("oracle.jdbc.driver.OracleDriver");
 * 
 * host=GetURLFromExcel.readURLFromExcel1("HOST",env);
 * logger.info("Host :"+host);
 * port=GetURLFromExcel.readURLFromExcel1("PORT",env);
 * logger.info("Port :"+port); sid=GetURLFromExcel.readURLFromExcel1("SID",env);
 * logger.info("SID :"+sid);
 * usrNme=GetURLFromExcel.readURLFromExcel1("USRNME",env);
 * logger.info("UserName :"+usrNme);
 * pwd=GetURLFromExcel.readURLFromExcel1("PWD",env);
 * logger.info("Password :"+pwd); String url =
 * "jdbc:oracle:thin:@"+host+":"+port+":"+sid; DriverManager.registerDriver(new
 * oracle.jdbc.driver.OracleDriver());
 * 
 * Connection con= DriverManager.getConnection(url,usrNme,pwd); return con; }
 * 
 * public static String getClassName() { return DBConnection.class.getName(); }
 * 
 * public static void closeConnection() throws SQLException {
 * connection.close(); }
 * 
 * public static void main(String args[]) throws ClassNotFoundException,
 * SQLException, IOException {
 * //System.out.println(executeQueryForOrderStatus("302084660","ST5"));
 * executeTnrangeTnParameter("ST4"); } }
 */