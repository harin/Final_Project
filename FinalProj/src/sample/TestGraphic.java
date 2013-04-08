package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TestGraphic {
	public static void main(String[] args){
		
		 final String domain =  "http://iceworld.sls-atl.com/api/&cmd=states";
		 final String domain2 = "http://iceworld.sls-atl.com/api/&cmd=gresources&uid=";
		 final String domain3 =  "http://iceworld.sls-atl.com/api/&cmd=gurl&gid=";
		 final String domain4 = "http://iceworld.sls-atl.com/";
		 final String test0 = "0";
		 String tester = domain2+test0;
		 try{
			 URL iceworld = new URL(tester);
			 URLConnection con = iceworld.openConnection();
			 BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			 String json = in.readLine();
			 System.out.println(json);
		  
			 JSONParser parser = new JSONParser();
			 JSONObject gresource = (JSONObject) parser.parse(json);
			  Set keyInResource 	= gresource.keySet();
			  System.out.println("key in resource:"+keyInResource);
			  //
	
			  JSONArray data = (JSONArray) gresource.get("data"); //data is a array
			 System.out.println("=======================");
			 System.out.println("Data:"+data);
			 String s= (String) data.get(0);
			 System.out.println(s);
			 //next we gonna try to fetch the "link" graphic		 
			 
			 try{ //2
				 //this is only one locaition
				 
				 String narak = domain3+data.get(0);
				 URL graphic = new URL(narak);
				 URLConnection kum = graphic.openConnection();
				 BufferedReader what = new BufferedReader(new InputStreamReader(kum.getInputStream()));
				 String omg = what.readLine();
				 System.out.println("gurl:" +omg);
				 
				 JSONParser parser2 = new JSONParser();
				 JSONObject gurl = (JSONObject) parser2.parse(omg);
				 Set keyInurl = gurl.keySet();
				 System.out.println("Key in url:"+keyInurl);
				 
				 
				 JSONObject dataurl = (JSONObject) gurl.get("data");
				 System.out.println("data in url:"+dataurl);
				 Set keyDataurl = dataurl.keySet();
				 System.out.println("Key in data of url:"+keyDataurl);
				 
				 
				 String locationPic = (String) dataurl.get("location");
				 System.out.println("Location of the picture is: "+locationPic);
				 
				 
				 
				 try{//3
					 //check the pic
				 		
			 		 	String picString = domain4+locationPic;
			 		 	URL picUrl = new URL(tester);
						URLConnection conPic = picUrl.openConnection();
						BufferedReader pic = new BufferedReader(new InputStreamReader(conPic.getInputStream()));
						
			 		
				 }catch(Exception e){
				 		System.out.println("Error in heck the pic:"+e);
				 }
			 		 
			 		 
			 }catch(Exception e){
				 System.out.println("Error at Grapic:"+e);
			 }
			 
			 
			 	
			 		
			 		
			 		
			 		
			 		
			 
			 	
			 
			 
		 }catch(Exception e){
			 System.out.println(e);
		 }
		 
		
		 
	}
	
	
}
