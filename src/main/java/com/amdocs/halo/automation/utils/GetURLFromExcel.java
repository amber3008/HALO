package com.amdocs.halo.automation.utils;

//import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GetURLFromExcel 
{
	public static XSSFRow row;
	public static XSSFCell cell;
	public static String cellValue;
	public static XSSFSheet sheet;
	
	public static XSSFSheet readXLSXFile0() throws IOException
    {
        InputStream ExcelFileToRead = new FileInputStream(AutoConstants.PATH_EXCEL_ENV_OMX);
        XSSFWorkbook  wb = new XSSFWorkbook(ExcelFileToRead);
        sheet = wb.getSheetAt(0);
        wb.close();
        return sheet;
     }  
	
	public static XSSFSheet readXLSXFile1() throws IOException
    {
        InputStream ExcelFileToRead = new FileInputStream(AutoConstants.PATH_EXCEL_ENV_DB);
        XSSFWorkbook  wb = new XSSFWorkbook(ExcelFileToRead); 
        sheet = wb.getSheetAt(0);
        wb.close();
        return sheet;
     } 

	 public static int readReqColFromExcelURL0(String requestName) throws IOException
     {
		sheet= GetURLFromExcel.readXLSXFile0();
	    Iterator<Row> rows=sheet.rowIterator();
	    int i=0;
        while (rows.hasNext())
        {
            row=(XSSFRow) rows.next();
            Iterator<Cell> cells = row.cellIterator();
            
            while (cells.hasNext())
            {
                cell=(XSSFCell) cells.next();   
                if (cell.getStringCellValue().equals(requestName))
                {
                	i=cell.getColumnIndex();
                }
            }   
	     }
        return i;
	  } 
	 
	 public static int readReqColFromExcelURL1(String name) throws IOException
     {
		sheet= GetURLFromExcel.readXLSXFile1();
	    Iterator<Row> rows=sheet.rowIterator();
	    int i=0;
        while (rows.hasNext())
        {
            row=(XSSFRow) rows.next();
            Iterator<Cell> cells = row.cellIterator();
            
            while (cells.hasNext())
            {
                cell=(XSSFCell) cells.next(); 
                if (cell.getStringCellValue().equals(name))
                {
                	i=cell.getColumnIndex();
                }
            }   
	     }
        return i;
	  }
	 
	 public static int readEnvRowFromExcelURL0(String env) throws IOException
     {
		sheet= GetURLFromExcel.readXLSXFile0();
		Iterator<Row> rows=sheet.rowIterator();
	    int j=0;
        while (rows.hasNext())
        {
            row=(XSSFRow) rows.next();
            Iterator<Cell> cells = row.cellIterator();
            while (cells.hasNext())
            {
                cell=(XSSFCell) cells.next();   
                if (cell.getStringCellValue().equals(env))
        		{
        			j=cell.getRowIndex();
        		}
            }    
	     }
        return j;
	  }
	 
	 public static int readEnvRowFromExcelURL1(String env) throws IOException
     {
		sheet= GetURLFromExcel.readXLSXFile1();
		Iterator<Row> rows=sheet.rowIterator();
	    int j=0;
        while (rows.hasNext())
        {
            row=(XSSFRow) rows.next();
            Iterator<Cell> cells = row.cellIterator();
            while (cells.hasNext())
            {
                cell=(XSSFCell) cells.next();   
                if (cell.getStringCellValue().equals(env))
        		{
        			j=cell.getRowIndex();
        		}
            }    
	     }
        return j;
	  }
	 
	  public static String readURLFromExcel(String requestName, String env) throws IOException
	  {
		  int colummnNo= readReqColFromExcelURL0(requestName);
		  int rowNo= readEnvRowFromExcelURL0(env);
		  sheet= GetURLFromExcel.readXLSXFile0();
		  row=sheet.getRow(rowNo);
		  cell=row.getCell(colummnNo);
		  String cellValue=cell.getStringCellValue(); 
		  return cellValue;
	  }
	  
	  public static String readURLFromExcel1(String dbNames, String env) throws IOException
	  {
		  int colummnNo= readReqColFromExcelURL1(dbNames);
		  int rowNo= readEnvRowFromExcelURL1(env);
		  sheet= GetURLFromExcel.readXLSXFile1();
		  row=sheet.getRow(rowNo);
		  cell=row.getCell(colummnNo);
		  String cellValue=cell.getStringCellValue(); 
		  return cellValue;
	  }
	  
	  public static String[][] write_xlsx(String[][] input,String filename,String tabname,int col) 
	  {
	       System.out.println("input data length"+input.length);

	       try {
	          // File excel = new File("E:\\output.xlsx");
	         //  System.out.print("\t"+filename+"\t"+tabname+"\t"+col+input.length+"col"+col+"\n");
	           XSSFWorkbook workbook = new XSSFWorkbook();
	           XSSFSheet sheet= workbook.createSheet();

	           // XSSFCell myCell = null;
	            String excelData[][] = input;
	            System.out.println("length excel data"+excelData.length);
	            XSSFRow myRow1 = sheet.createRow(input.length);

	            /*for (int cellNum = 0; cellNum < col; cellNum++) {
	                myCell = myRow1.createCell(cellNum);
	                myCell.setCellValue(excelData[0][cellNum]);

	            }*/
	            XSSFCell myCell = myRow1.createCell(col);
	        //    myCell.setCellValue(value)

	            for (int i = 0; i < input.length; i++) {
	                for (int j = 0; j < col; j++) {
	                    // = myRow1.createCell(i);
	                    myCell.setCellValue(input[i][j]);
	                    System.out.println(">>"+excelData[i][j]);
	                }
	            }
	                FileOutputStream out = new FileOutputStream(filename);
	                workbook.write(out);
	                out.close();
	                workbook.close();
	              //  System.out.println("report generated..");
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        return null;
	   }
	/*  
	 public static void main(String args[]) throws IOException
	 {
		 System.out.println(GetURLFromExcel.readURLFromExcel1("HOST","QA14"));
	 }*/
}
