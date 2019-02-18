package clientTesting;
// A Java program for a Client 
import java.net.*;
import java.util.Timer;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
  
public class Client 
{ 
	static Timer timer = new Timer();

    
  
    public static void main(String args[]) throws IOException 
    { 
        //Client client = new Client("127.0.0.1", 12345); 

        //Create text file to represent all the errors that occur during this run of the program. Includes date, time, and name
        String fileName = "errors.txt";
        File errors = new File(fileName);
        FileWriter errorWriter = new FileWriter(fileName, true);
        BufferedWriter errorBuffered =  new BufferedWriter(errorWriter);
        PrintWriter errorPrinter = new PrintWriter(errorBuffered);

        //Write current date and time to the file. 
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        errorPrinter.println(dateFormat.format(date) + "\n\n");

        
        Socket socket = null; 
        DataOutputStream out = null; 
        String IP = "127.0.0.1";
        int port = 12345;

        // establish a connection 
        try
        { 
            socket = new Socket(IP, port); 
            System.out.println("Connected"); 
            
            // sends output to the socket 
            out = new DataOutputStream(socket.getOutputStream()); 
        } 
        catch(UnknownHostException u) 
        { 
            System.out.println(u); 
            errorPrinter.println("Tried to create socket object and sent data output stream to it, UnknownHostException occurred.");
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
            errorPrinter.println("Tried to create socket object and send data output stream to it, IOException occurred.");
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
            System.err.println(i); 
            errorPrinter.println("Tried to close the data output stream and socket, IOException occurred");
        } 

        //Close the PrintWrite Object
        errorPrinter.close();

        //write the messages to a single text file. 
        
        
    } 
} 