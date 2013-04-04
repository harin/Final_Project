package peek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;


//last update 3 April
//Features: Checking Connection+Reconnect_Selection 

public class ICEWorldPeek {
	private static int makesure=0;
	public static void main(String[] args)throws Exception{
		
		
		final String domain =  "http://iceworld.sls-atl.com/api/&cmd=";
		String domain2 = "http://www.google.com";
		String cmd;
		Scanner sc = new Scanner(System.in);
		//check connection
		
		System.out.println("Testing connection...");
			
		
		try{
		//check to reconnect
		do{
			
		if(stillConnected(domain)) {
			System.out.println("Connection successful.");
			
		
			
			do{
				System.out.println("Enter a command:");
				cmd = sc.next();
				System.out.println(cmd);
				
					URL iceworld = new URL(domain + cmd);
					System.out.println(iceworld);
					URLConnection con = iceworld.openConnection();
					BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
					System.out.println(in.readLine());
			}while(stillConnected(domain));
		}
		else System.out.println("Connection failed.");
		
		}while(retry());
		} catch (Exception e){
			
		}
		}
	
	public static boolean stillConnected(String d){
		boolean isCon;
		//check connection
		try{
			URL testURL = new URL(d);
			URLConnection testConnection = testURL.openConnection();
			testConnection.setConnectTimeout(5000);
			testConnection.connect();
			isCon = true;
		} catch (Exception e){
			isCon = false;
			System.out.println("Lose connection");
		}
		return isCon;
	}
	
	public static boolean retry(){
		boolean check =false;
		Scanner sc = new Scanner(System.in);
		System.out.println("Do you want to reconnect? (Y/N)");
		String answer = sc.next();
		
		if(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes") ){
			
			makesure++;
			check = true;
		}
		if(makesure >= 11) {
			System.out.println("********You try to reconnect over 10 times********");
			System.out.println("********Maybe reboot your router might work********");
			System.out.println();
		}
		return check;
	}	
	
}
