package com.bridge.are.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.bridge.are.business.AREBillingStruct;
import com.bridge.are.literals.ARELiterals;
import com.yantra.yfc.core.YFCObject;
import com.yantra.yfc.log.YFCLogCategory;
import com.yantra.yfc.util.YFCDate;




/**
 * Class Which generate the CSV for the recorded Activity
 * @author Ezhilarasan
 *
 */
public class CsvFileGenerator {
	
	private static YFCLogCategory logger = YFCLogCategory.instance(CsvFileGenerator.class);
	
	private CsvFileGenerator() {
		
	}
	
	/**
	 * Static method to generate CSV for Activity
	 * @param fileName
	 * @param oActivityStructList
	 * @param isStorage
	 * @throws IOException
	 */
	public static void genertorActivityCsv(String fileName, List oActivityStructList, boolean isStorage) throws IOException {
		

		String sFileHeader = "";
		String sReferenceHdr="";
		
		
		try(FileWriter fileWriter = new FileWriter(fileName)) {
			
	        
				if (! oActivityStructList.isEmpty()) {
					
				AREBillingStruct oActivity = (AREBillingStruct) oActivityStructList.get(0);
				
				sReferenceHdr = ((YFCObject.isVoid(oActivity.getStructReference1()))?("RENT_Interval"):(oActivity.getStructReference1()));
				
				sFileHeader = "NODE, ENTERPRISE_CODE, SELLER_ORG, " + sReferenceHdr + ", CODE, Quantity, Cost";
				//Write the CSV file header
				fileWriter.append(sFileHeader);
				
				//Add a new line separator after the header
				fileWriter.append(ARELiterals.ARE_NEW_LINE_IDENTIFIER);
				
				//Write a new student object list to the CSV file
				Iterator itr = oActivityStructList.iterator();
				while(itr.hasNext()){
					
					AREBillingStruct ostruct = (AREBillingStruct) itr.next();
		
		             Iterator itrChild = ostruct.getChildStructList().iterator();
		             
		             while(itrChild.hasNext()){
		            	 
		            	             	 
		            	 AREBillingStruct ostructChild = (AREBillingStruct) itrChild.next();
		            	 
		            	 fileWriter.append(ostruct.getNode());
		            	 fileWriter.append(ARELiterals.ARE_MULTI_VALUE_TOKEN);
		            	 
		            	 fileWriter.append(ostruct.getEnterpriseCode());
		            	 fileWriter.append(ARELiterals.ARE_MULTI_VALUE_TOKEN);
		            	 
		            	 if(isStorage) {
		            		 
		            		 fileWriter.append(ostructChild.getVendor());
			            	 fileWriter.append(ARELiterals.ARE_MULTI_VALUE_TOKEN);
			            	 
			            	 //fileWriter.append(String.valueOf(""));
			            	 fileWriter.append(ostruct.getActivityGrpKeyData());
			            	 fileWriter.append(ARELiterals.ARE_MULTI_VALUE_TOKEN);
			            	 
			            	 
		            	 }else{
		            		 
		            		 fileWriter.append(ostruct.getVendor());
			            	 fileWriter.append(ARELiterals.ARE_MULTI_VALUE_TOKEN);
			            	 
			            	 //fileWriter.append(ostruct.getStructReference2());
			            	 fileWriter.append(ostruct.getActivityGrpKeyData());
			            	 fileWriter.append(ARELiterals.ARE_MULTI_VALUE_TOKEN);
			            	 
			            	 
		            	 }
		            	 
		            	 
		            	 
		            	 fileWriter.append(ostructChild.getActivityCode());
		            	 fileWriter.append(ARELiterals.ARE_MULTI_VALUE_TOKEN);
		            	 
		            	 fileWriter.append(String.valueOf(ostructChild.getQuantity()));
		            	 fileWriter.append(ARELiterals.ARE_MULTI_VALUE_TOKEN);
		            	 
		            	 fileWriter.append(String.valueOf(ostructChild.getARECost()));
		            	 fileWriter.append(ARELiterals.ARE_NEW_LINE_IDENTIFIER);
		            	 
		             }
		             
				}

				logger.debug("CSV file was created successfully !!!");

				
			} else {
				logger.debug("No Data to publish");
			}
			
						
		} 

  }
	
	
    /**
     * Method to generate the Rent Detail CSV
     * @param fileName
     * @param oRentInvStructList
     * @throws IOException
     */
	public static void genertorRentDtlCsv(String fileName, List oRentInvStructList) throws IOException {
		
		String sFileHeader = "";
		
		
		try(FileWriter fileWriter = new FileWriter(fileName)) {
			
			if (!oRentInvStructList.isEmpty()) {
			
				
		        
				sFileHeader = "NODE, EnterpriseCode, Vendor, SerialNo, ItemId, Age, InwardDate, OutWardDate";
				//Write the CSV file header
				fileWriter.append(sFileHeader);
				
				//Add a new line separator after the header
				fileWriter.append(ARELiterals.ARE_NEW_LINE_IDENTIFIER);
				
				//Write a new student object list to the CSV file
				Iterator itr = oRentInvStructList.iterator();
				while(itr.hasNext()){
					
					AREBillingStruct ostruct = (AREBillingStruct) itr.next();
		            
		        	 fileWriter.append(ostruct.getNode());
	            	 fileWriter.append(ARELiterals.ARE_MULTI_VALUE_TOKEN);
	            	 
	            	 fileWriter.append(ostruct.getEnterpriseCode());
	            	 fileWriter.append(ARELiterals.ARE_MULTI_VALUE_TOKEN);
	            	 
	            	 fileWriter.append(ostruct.getVendor());
	            	 fileWriter.append(ARELiterals.ARE_MULTI_VALUE_TOKEN);
	            	 
	            	 fileWriter.append(ostruct.getSerialNo());
	            	 fileWriter.append(ARELiterals.ARE_MULTI_VALUE_TOKEN);
	            	 
	            	 fileWriter.append(ostruct.getItemId());
	            	 fileWriter.append(ARELiterals.ARE_MULTI_VALUE_TOKEN);
	            	 
	            	 fileWriter.append(String.valueOf(ostruct.getInvAge()));
	            	 fileWriter.append(ARELiterals.ARE_MULTI_VALUE_TOKEN);
	            	 
	            	 if(! YFCObject.isNull(ostruct.getReceiptDate()))
	            		 fileWriter.append(ostruct.getReceiptDate().getString(YFCDate.ISO_DATETIME_FORMAT));
		             else
		            	 fileWriter.append(String.valueOf(ostruct.getReceiptDate()));
	            	 fileWriter.append(ARELiterals.ARE_MULTI_VALUE_TOKEN);
	            	 
	            	 if(! YFCObject.isNull(ostruct.getShippedDate()))
	            	    fileWriter.append(ostruct.getShippedDate().getString(YFCDate.ISO_DATETIME_FORMAT));
	            	 else
	            		 fileWriter.append(String.valueOf(ostruct.getShippedDate()));
	            	 fileWriter.append(ARELiterals.ARE_NEW_LINE_IDENTIFIER);
	            
		             
				}

				logger.debug("CSV file was created successfully !!!");

				
			} else {
				logger.debug("No Data to publish");
			}
			
						
		} 

  }

}