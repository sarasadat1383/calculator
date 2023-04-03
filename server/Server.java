package myCalculator.server;
import myCalculator.Calculator;
import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;
public class Server {
	public static void main (String[]args){
		ServerSocket serverSocket = null;
		Socket clientSocket = null ; 
		String line;
		try {
            serverSocket = new ServerSocket(5000);
			clientSocket= serverSocket.accept();
			InputStreamReader inputServer = new InputStreamReader(clientSocket.getInputStream());  
            OutputStreamWriter outputServer = new OutputStreamWriter(clientSocket.getOutputStream());  
            BufferedReader bufferedReader = new BufferedReader(inputServer);  
            BufferedWriter bufferedWriter = new BufferedWriter(outputServer); 
			line = bufferedReader.readLine();
			String [] myExpressions = line.split("\n");
			for (String expression : myExpressions) {
                int result;
				result = Calculator.calculating(expression);
				System.out.println("result:" + result );
			}
			clientSocket.close();  
		}			
		catch (IOException e) {  
			e.printStackTrace();  
        }
	}
}		