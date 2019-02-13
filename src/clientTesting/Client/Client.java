package clientTesting;
// A Java program for a Client 
import java.net.*;
import java.util.Timer;
import java.io.*; 
  
public class Client 
{ 
	static Timer timer = new Timer();
	/*
    // initialize socket and input output streams 
    private Socket socket = null; 
    private DataOutputStream out = null; 
  
    // constructor to put ip address and port 
    public Client(String address, int port) throws IOException 
    { 
        // establish a connection 
        try
        { 
            socket = new Socket(address, port); 
            System.out.println("Connected"); 
  
            // takes input from terminal 
  
            // sends output to the socket 
            out = new DataOutputStream(socket.getOutputStream()); 
        } 
        catch(UnknownHostException u) 
        { 
            System.out.println(u); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        }
        
        float xDis = 50, yDis = 70, angle = 3;
        
        out.writeBytes(xDis + " " + yDis + " " + angle + "\n");
        
        // close the connection 
        try
        { 
            out.close(); 
            socket.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
    */
  
    public static void main(String args[]) throws IOException 
    { 
        //Client client = new Client("127.0.0.1", 12345); 
        Socket socket = null; 
        DataOutputStream out = null; 
        // establish a connection 
        try
        { 
            socket = new Socket("127.0.0.1", 12345); 
            System.out.println("Connected"); 
            
            // sends output to the socket 
            out = new DataOutputStream(socket.getOutputStream()); 
        } 
        catch(UnknownHostException u) 
        { 
            System.out.println(u); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        }
        
        int j = 0;
        float xDis = 50, yDis = 70, angle = 45;
        while (j < 9999999)
        {
	        
	        
	        long time = System.currentTimeMillis();
	        while (System.currentTimeMillis() - time < 1000) {}
	        out.writeBytes(xDis + " " + yDis + " " + angle + "\n");
	        xDis+=5;
	        j++;
        }
        
        System.out.println("ended");
        // close the connection 
        try
        { 
            out.close(); 
            socket.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
} 