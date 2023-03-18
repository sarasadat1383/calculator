package repos.calculator.myCalculator.client;
import java.net.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import repos.calculator.myCalculator.*;
public class Client { 
	public static void main (String[]args){
		ServerSocket serverSocket=null;
		Socket clientSocket= null ; 
		try {
			clientSocket = new Socket("localhost", 5000); 
			InputStreamReader inputClient = new InputStreamReader(clientSocket.getInputStream());  
            OutputStreamWriter outputClient = new OutputStreamWriter(clientSocket.getOutputStream());  
            BufferedReader bufferedReader = new BufferedReader(inputClient);  
            BufferedWriter bufferedWriter = new BufferedWriter(outputClient);
			serverSocket.close();  
            inputClient.close();  
            outputClient.close();  
            bufferedReader.close();  
            bufferedWriter.close(); 
		} 
		catch (IOException e) {  
			e.printStackTrace();  
        }
	}
}	

		