
import java.io.*;
import java.util.*;
import java.lang.*;
import operators.*;

public class Main {
	public static void main (String[]args){
		File file = new File("input"); 
		File filesList[] = file.listFiles(); //List of all files and directories
		if (filesList != null || filesList.length != 0) { 
			for(File myfile : filesList) {
				(new Thread( new FileCalculator(myfile))).start();
			}
		}
		if (filesList == null || filesList.length == 0) {
			File myFile = new File("input.txt" );
			(new Thread( new FileCalculator(myFile))).start(); 	
		}
	}   
}
