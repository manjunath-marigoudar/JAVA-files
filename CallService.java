package utils;

import java.io.File;
import java.util.Scanner;

public class CallService {
	private static String apiname="Test_InvoicePrint";
	private static String isService="Y";
    public static  String userId="admin";
    public static  String password="ABOF@Prod";
    public static  String httpurl = "http://10.1.9.14:9080/smcfs/interop/InteropHttpServlet";
    //public static  String input="<Shipment ShipmentKey=\"201711281306311072318314\" FileName=\"test1235\"  Directory=\"/opt/IBM/\"  />";
    public static  String input="";
    public  String template="";
	public static String getApiname() {
		return apiname;
	}
	public static void setApiname(String apiname) {
		CallService.apiname = apiname;
	}
	public static String getIsService() {
		return isService;
	}
	public static void setIsService(String isService) {
		CallService.isService = isService;
	}
	
	public static void main(String[] args) throws Exception {
		
		String entireFileText = new Scanner(new File("D:\\2016_Mar.txt")).useDelimiter("\\A").next();
	input=entireFileText;	
	//System.out.println("Out:"+entireFileText);
	TriggerAgentUtil TriggerObject = new TriggerAgentUtil(httpurl,userId,password);
	String out = TriggerObject.callApi("multiApi","N",input,"");
	System.out.println("\nOut:"+out);
	
	}
	

}
