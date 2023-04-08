
import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.*;
public class Client { 
	public static void main (String[]args){
		ServerSocket serverSocket=null;
		Socket clientSocket= null ; 
		try {
			clientSocket = new Socket("localhost", 65534); 
			InputStreamReader inputClient = new InputStreamReader(clientSocket.getInputStream());  
            OutputStreamWriter outputClient = new OutputStreamWriter(clientSocket.getOutputStream());  
            BufferedReader bufferedReader = new BufferedReader(inputClient);  
            BufferedWriter bufferedWriter = new BufferedWriter(outputClient); 
			String Line; 
			Main objectmainclass = new Main();
			String Result = objectmainclass.main();
			bufferedWriter.write(Result);
			while( (Line= bufferedReader.readLine())!= null){
				Result= bufferedWriter.write(Line);
			}
            bufferedReader.close();  
            bufferedWriter.close(); 
			clientSocket.close(); 
		} 
		catch (IOException e) {  
			e.printStackTrace();  
        }
	}
}	

		