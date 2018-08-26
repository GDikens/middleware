package middleware;
//java socket client example
import java.io.*;
import java.net.*;
import java.util.Collections; 
import java.util.*;

import servicedir.directory;
 
public class Middleware{

    String[] service_info;

    Socket socket;
    boolean isConnected;
    
    PrintWriter out = null; //used to write for the service
    BufferedReader in = null; //used to get data from the service
    
    String host = "localhost";
    int port = 9999;

    private final String USER_AGENT = "Mozilla/5.0";

    

    private void serviceLookUp(String service){

        directory nwDir = new directory();
        this.service_info = nwDir.SERVICE_DIRECTORY.get(service);

    }

    private String connect(String service, String... params) throws IOException{

        String host = service_info[0];
        int port = Integer.parseInt(service_info[1]);

        String url = formatMessage(service, params);

        //String url = "http://www.google.com/search?q=mkyong";
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
        //System.out.println(response.toString());

        return response.toString();
        
    }

    public int getParameters(String service, int num1, int num2) throws Exception{
        int response;
        try{
            response = Integer.parseInt(requestServer(service, Integer.toString(num1), Integer.toString(num2)));
        }catch(Exception e){
            throw new Exception("Service Error");
        }
        return response;
    }

    private String requestServer(String service, String... params) throws IOException{

        if(service.equals("gcd")){

            serviceLookUp("gcdService");

            return connect("gcd", params);

            //Send message to server
            
            
        }
        
        //request for addservice
        if(service.equals("fibbonachi")){

            serviceLookUp("fibbonachiService");

            return connect("fibbonachi", params);

            
        }
        
        
        return "";
        
    }

    private String formatMessage(String service, String... params){

        //String url = "GET / HTTP/1.1\r\n\r\n";
        String url = "http://localhost:3000/"+ service +"?num1="+ params[0] + "&num2=" + params[1];
        //http://127.0.0.1:3000/gcd?num1=50&num2=10
        
        return url;

        
    }
    
    
}
