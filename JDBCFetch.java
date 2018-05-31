package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCFetch {
  
   static final String JDBC_DRIVER = "com.ibm.db2.jcc.DB2Driver";  
   static final String DB_URL = "jdbc:db2://10.1.17.222:50000/ABOFOMS";

  
   static final String USER = "abofoms";
   static final String PASS = "MFv4yU2bSm";
   
   public static void main(String[] args) {
   Connection conn = null;
   Statement stmt = null;
   try{
    
	   
	   Class.forName("com.ibm.db2.jcc.DB2Driver");

      
      System.out.println("Connecting to a selected database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      System.out.println("Connected database successfully...");
      
      
      System.out.println("Creating statement...");
      stmt = conn.createStatement();

      String sql = "SELECT DISTINCT  YS.SHIPMENT_KEY, YS.SHIPMENT_NO,  YSL.ORDER_NO FROM YFS_SHIPMENT YS,YFS_SHIPMENT_LINE YSL WHERE YS.STATUS != '9000' AND YS.STATUS >='1300' AND YS.SHIPMENT_KEY like '201711%' AND YS.DOCUMENT_TYPE='0001' AND YS.SHIPMENT_KEY = YSL.SHIPMENT_KEY";
     // String sql ="SELECT * from YFS_HEARTBEAT";
      ResultSet rs = stmt.executeQuery(sql);
      System.out.println("hi");
      
  	File outputFile = new File("D:\\2017_Nov.txt");

	// if file doesnt exists, then create it
	if (!outputFile.exists()) {
		outputFile.createNewFile();
	}
	
	
	
      FileWriter fw = new FileWriter(outputFile.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write("<MultiApi>");
		
		
      while(rs.next()){
         
         String shipment_key  = (rs.getString("SHIPMENT_KEY")).trim();
         String shipment_no = (rs.getString("SHIPMENT_NO")).trim();
         String order_no = rs.getString("ORDER_NO");
     
     if (order_no != null){
         bw.write("\n\n");
			bw.write("<API FlowName=\"Test_InvoicePrint\" >");
			bw.write("\n");
			bw.write("<Input>");
			bw.write("\n");
			String inputXml="<Shipment  ShipmentKey=\""+shipment_key+"\" FileName=\""+order_no+"_BLR-"+shipment_no+"\" Directory=\"/opt/IBM/2017_Nov/\"/>";
			bw.write(inputXml);
			bw.write("\n");
			bw.write("</Input>\n");
			bw.write("</API>"); 
         
         System.out.print("SHIPMENT_KEY: " + shipment_key);
         System.out.print(", SHIPMENT_NO: " + shipment_no);
         System.out.print("\n");
     }
      }
      bw.write("</MultiApi>");
		bw.close();
      rs.close();
   }catch(SQLException se){
      se.printStackTrace();
   }catch(Exception e){
      
      e.printStackTrace();
   }finally{
      
      try{
         if(stmt!=null)
            conn.close();
      }catch(SQLException se){
      }
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }
   }
   System.out.println("\nGoodbye!");
}
}