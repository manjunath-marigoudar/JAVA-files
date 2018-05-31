package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JOptionPane;
public class CancelShipment {

	/**
	 * @param args
	 * @author Nagarjun.T
	 * How to use: Provide the inputs as comma separated values and ensure that the number 
	 * of comma separated values in each input are same.
	 * For Ex: If you are using 10 different itemID and SourceLocation, you should provide 10 values in 
	 * both itemID and SourceLocation
	 */
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		
		String ShipmentKey="";
		String array1[]=ShipmentKey.split(",");
		
		
		String ShipmentNo="";
		String array2[]=ShipmentNo.split(",");
		
		String OrderNo="";
		String array3[]=OrderNo.split(",");
		
		
		
		File outputFile = new File("C:\\CancelShipment.txt");

		// if file doesnt exists, then create it
		if (!outputFile.exists()) {
			outputFile.createNewFile();
		}

	
			
			
		FileWriter fw = new FileWriter(outputFile.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write("<MultiApi>");

		for(int i=0;i<array1.length;i++){
			
			bw.write("\n\n");
			bw.write("<API Name=\"changeShipment\" >");
			bw.write("\n");
			bw.write("<Input>");
			bw.write("\n");
			//String inputXml="<Shipment  ShipmentKey=\""+array1[i]+"\" FileName=\""+array2[i]+"\"/>";
			String inputXml="<Shipment ShipmentKey=\"201711281306311072318314\" FileName=\"(test1235\"  Directory=\"/opt/IBM/\"  />";
			bw.write(inputXml);
			bw.write("\n");
			bw.write("</Input>\n");
			bw.write("</API>\n\n"); 
		}
		bw.write("</MultiApi>");
		bw.close();
		System.out.println("Done");
		
		//JOptionPane.showInputDialog("Hi Iron Man");


	}
	

	

}
