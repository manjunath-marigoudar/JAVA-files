package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import com.yantra.yfc.dom.YFCDocument;

public class TriggerAgentUtil {
    
    private static String apiname="";
    private static String isService="N";
    public  String userId="";
    public  String password="";
    public  String httpurl = "";
    private static String input="";
    private static String template="";
    
    public void setURL(String httpurl){
    this.httpurl=httpurl;
    }
    
    public void setuserId(String userId){
	this.userId=userId;
    }
    
    public void setpassword(String password){
	this.password=password;
       }
    
    
/*  public static void main(String[] args) throws Exception {
	  TriggerAgentUtil invoke=new TriggerAgentUtil();
      invoke.setURL("http://10.1.9.14:9080/smcfs/interop/InteropHttpServlet");
      invoke.setuserId("admin");
      invoke.setpassword("ABOF@Prod");
      //invoke.setEnviroment("http://wms01.dev.abof.com:9082/smcfs/interop/InteropHttpServlet","admin","dev");
      invoke.callApi("getServerList","N","<S />","<Servers><Server Id=''/></Servers>");
      //invoke.testInteropApi();
  } */
    
public TriggerAgentUtil(String url, String userId, String password) {
    this.httpurl=url;
    this.userId=userId;
    this.password=password;
    
}

public TriggerAgentUtil(String ip,String port,String sterlingContext, String userId, String password) {
    this.httpurl="http://"+ip+":"+port+"/"+sterlingContext+"/interop/InteropHttpServlet";
    this.userId=userId;
    this.password=password;
    
}

private String callInteropApi() throws Exception{
    String output="";
    URL url = new URL(httpurl);
    URLConnection conn = url.openConnection();
    
    conn.setDoOutput(true);
    conn.addRequestProperty("id", "frmInteropTest");
    conn.addRequestProperty("name", "frmInteropTest");
    OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
    
    writer.write("YFSEnvironment.progId=SterlingHttpTester&" +
    		"InteropApiName="+apiname+"&" +
    				"IsFlow="+isService+"&YFSEnvironment.userId="+userId+"&YFSEnvironment.password="+password+"&InteropApiData="+input+"&TemplateData="+template+"");
    writer.flush();
    String line;
    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    //System.out.println(apiname+" output :" );
    while ((line = reader.readLine()) != null) {
     // System.out.println(line);
      output=output+line;
    }
    writer.close();
    reader.close();
    
    //YFCDocument outxml=YFCDocument.parse(output);
    System.out.println("API Output:"+ output);
    return output;
  }

public void testInteropApi() throws Exception{
    
    if(null==this.userId ||this.userId.length()<=0){
	    throw new Exception("Set userId !!!");
	    
	 }
if(null==this.password ||this.password.length()<=0){
	    throw new Exception("Set password !!!");
	    
	 }

if(null==this.httpurl ||this.httpurl.length()<=0){
    throw new Exception("Set httpurl !!!");
    
 }
    
    URL url = new URL(httpurl);
    URLConnection conn = url.openConnection();
    
    conn.setDoOutput(true);
    conn.addRequestProperty("id", "frmInterop");
    conn.addRequestProperty("name", "frmInterop");
    OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
    
    writer.write("YFSEnvironment.progId=SterlingHttpTester&InteropApiName="+"getServerList"+"&IsFlow="+"N"+"&YFSEnvironment.userId="+userId+"&YFSEnvironment.password="+password+"&InteropApiData="+"<S />"+"&TemplateData="+""+"");
    writer.flush();
    String line;
    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    while ((line = reader.readLine()) != null) {
      System.out.println(line);
    }
    writer.close();
    reader.close();

  }

public String invokeYantraService(String api,String inXML) {
    try {
	return callApi(api,"Y",inXML,"");
    } catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	
    }
    return inXML;
   
}


	public String invokeYantraService(String api,YFCDocument inXML,YFCDocument tempXML) throws Exception{
    return callApi(api,"Y",inXML.toString(),tempXML.toString());
}


public String callApi(String apiname2, String isService2, String input2,
	String template2) throws Exception {
    
    apiname=apiname2;
    isService=isService2;
    input=input2;
    template=template2;
    
    if(null==apiname ||apiname.length()<=0){
    throw new Exception("Set API Name !!!");
    
    	}
    if(null==this.userId ||this.userId.length()<=0){
	    throw new Exception("Set userId !!!");
	    
	 }
    if(null==this.password ||this.password.length()<=0){
	    throw new Exception("Set password !!!");
	    
	 }
    if(null==isService ||isService.length()<=0){
	    throw new Exception("Set isService Flag !!!");
	    
	 }
    if(null==input ||input.length()<=0){
	    throw new Exception("Set input xml !!!");
	    
	 }
    if(null==this.httpurl ||this.httpurl.length()<=0){
	    throw new Exception("Set httpurl !!!");
	    
	 }
    if(null==this.template ){
	    throw new Exception("template cannot be nullified !!!");
	    
	 }
    
    return callInteropApi();
  }

public TriggerAgentUtil() {
    // TODO Auto-generated constructor stub
}
 
}