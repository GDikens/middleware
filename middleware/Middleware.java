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
    
    PrintWriter s_out = null; //used to write for the service
    BufferedReader s_in = null; //used to get data from the service
    
    String host = "localhost";
    int port = 9999;
    
    public Middleware(){

        this.serviceLookUp("addService");
        
        try{
            this.connect();
        }catch(Exception e){
            System.out.println("Error Connecting");
        }

    }

    private void serviceLookUp(String service){

        directory nwDir = new directory();
        this.service_info = nwDir.SERVICE_DIRECTORY.get(service);

    }

    private void connect() throws IOException{
        if(this.service_info!=null){
            String host = service_info[0];
            int port = Integer.parseInt(service_info[1]);
            socket = new Socket();
            
            try{
                socket.connect(new InetSocketAddress(host , port));
                //System.out.println("Connected");
                
                //writer for socket
                s_out = new PrintWriter( socket.getOutputStream(), true);
                //reader for socket
                s_in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                
                isConnected = true;
            }catch (UnknownHostException e){
                //Host not found
                isConnected = false;
                System.err.println("Don't know about host : " + host);
                System.exit(1);
            }
        }
    }

    public int getParameters(int num1, int num2) throws Exception{
        int response;
        try{
            response = Integer.parseInt(requestServer("addservice", Integer.toString(num1), Integer.toString(num2)));
        }catch(Exception e){
            throw new Exception("Serive Error");
        }
        return response;
    }

    private String requestServer(String service, String... params) throws IOException{
        
        //request for addservice
        if(service.equals("addservice")){
            //Send message to server
            String message = formatMessage(service, params);
            
            
            s_out.println( message );
            //System.out.println("Message send");
                
                
            //Get response from server
            String response = "";
            String resp;
            while ((resp = s_in.readLine()) != null){
                response = response + resp;
                //System.out.println( resp );
            }
            return response;
        }
        
        
        return "";
        
    }

    private String formatMessage(String service, String... params){
        if(service.equals("addservice")){
            String msg = "GET / HTTP/1.1\r\n\r\n";
            for(int i=0;i<params.length;i++){
                if(i>0){
                    msg = msg+"&";
                }   
                msg = msg+"param"+i+"="+params[i];
            }
            return msg;
        }
        return "";
        
    }
    
    
}
