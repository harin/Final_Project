package peek;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class ICEWorldPeek {
	public static void main(String[] args)throws Exception{
		final String domain =  "http://iceworld.sls-atl.com/api/&cmd=";
		String domain2 = "http://www.google.com";
		String cmd;
		//check connection
		System.out.println("Testing connection...");
		if(stillConnected(domain)) {
			System.out.println("Connection successful.");
			
			Scanner sc = new Scanner(System.in);
			while(stillConnected(domain)){
				System.out.println("Enter a command:");
				cmd = sc.next();
				System.out.println(cmd);
				
					URL iceworld = new URL(domain + cmd);
					System.out.println(iceworld);
					URLConnection con = iceworld.openConnection();
					BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
					System.out.println(in.readLine());
			}
		}
		else System.out.println("Connection failed.");
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
		}
		return isCon;
	}
	
	public static boolean retry(){
		return false;
	}
	
	//shortkey
	
	
}
