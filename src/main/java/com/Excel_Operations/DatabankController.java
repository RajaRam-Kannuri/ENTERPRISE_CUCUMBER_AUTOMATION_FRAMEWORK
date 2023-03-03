package com.Excel_Operations;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DatabankController<K,V> extends HashMap<K,V>{
   /**
	 *@author RAJARAM KANNURI
	 */
   private static final long serialVersionUID = 1L;
   public static HashMap<String,String> storeValues = new HashMap<String,String>();
   private static List<DatabankController<String, String>> singleton = null;

	private DatabankController()
	{

	}
	public static List<DatabankController<String, String>> getInstance(String fileName,String sheetName)
	{
		if(singleton==null)
		{
			singleton = data(fileName,sheetName);

		}
		return singleton;
	}
   private static List<DatabankController<String,String>> data(String filepath,String sheetName)
   {
      List<DatabankController<String,String>> mydata = new ArrayList<>();
      try
      {
         FileInputStream fs = new FileInputStream(filepath);
         Workbook workbook = WorkbookFactory.create(fs);
         Sheet sheet = workbook.getSheet(sheetName);
         Row HeaderRow = sheet.getRow(0);
         for(int i=1;i<sheet.getPhysicalNumberOfRows();i++)
         {
            Row currentRow = sheet.getRow(i);
            DatabankController<String,String> currentHash = new DatabankController<String,String>();
            for(int j=0;j<currentRow.getPhysicalNumberOfCells();j++)
            {
               Cell currentCell = currentRow.getCell(j);
               switch (currentCell.getCellType())
               {
               case Cell.CELL_TYPE_STRING:
                  currentHash.put(HeaderRow.getCell(j).getStringCellValue().trim(), currentCell.getStringCellValue().trim());
                  break;
               case Cell.CELL_TYPE_NUMERIC:
                   currentHash.put(HeaderRow.getCell(j).getStringCellValue().trim(), new Integer((int) currentCell.getNumericCellValue()).toString().trim());
                   break;
               case Cell.CELL_TYPE_BLANK:
            	   currentHash.put("Blank", "Blank");
            	   break;
               }
            }
            mydata.add(currentHash);
         }
         fs.close();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return mydata;
   }
   public static DatabankController<String,String> getdataBankCounterRecord(String row,String fileName,String SheetName)
   {
	   List<DatabankController<String, String>> getValue = getInstance(System.getProperty("user.dir") + "\\src\\test\\resources\\Databanks\\"+fileName+".xlsx",SheetName);
	   DatabankController<String,String> dbcontroller = null;
	   boolean flag = false;
	   int i = 0;
	   for(DatabankController<String, String> h: getValue )
	   {
		   for(String h1:h.keySet())
		   {
			   if(h.get(h1).contains(row))
			   {
				   flag = true;
				   break;
			   }
		   }
		   System.out.println(i);
		   if(flag==true)
		   {
			   dbcontroller =  getValue.get(i);
			   break;
		   }
		   i++;
	   }
	   return dbcontroller;
   }
   
}
