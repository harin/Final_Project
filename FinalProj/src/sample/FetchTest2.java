package sample;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.LinkedList;


import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import worldview.MainFrame;
import worldview.NullIcetizen;
import worldview.WorldView;


public class FetchTest2 {
	public static void main(String[] args){
	  final String domain =  "http://iceworld.sls-atl.com/api/&cmd=states";
	  String domain2 = "http://www.google.com";
	  String cmd;
	  //check connection
	  int testing =1;

	  
	  try{
		  System.out.println("Test of keys and necessary information");
		  URL iceworld = new URL(domain);
		  URLConnection con = iceworld.openConnection();
		  BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		  String json = in.readLine();
	  
		  JSONParser parser = new JSONParser();
	  
		  JSONObject state = (JSONObject) parser.parse(json);
		  System.out.println("Priting State!!!!!!!!!!!");
		  System.out.println(state);
		  Set keyInState = state.keySet();
		  System.out.println("key in state:"+keyInState);
		  System.out.println("********************************************");

	  
		  JSONObject data = (JSONObject) state.get("data");
		  Set keyInData = data.keySet();
		  System.out.println("Priting data!!!!!!!!!!!");
		  System.out.println(data);
		  System.out.println("key in data:"+keyInData);
		  System.out.println("********************************************");
	  

		  JSONObject icetizen = (JSONObject) data.get("icetizen");
		  System.out.println("Priting Icetizen!!!!!!!!!!!");
		  System.out.println(icetizen);
		  Set keys = icetizen.keySet();
		  System.out.println("Key in icetizen"+keys);
		  System.out.println("********************************************");

	  
		  JSONObject weather = (JSONObject) data.get("weather");
		  Set weatherKey = weather.keySet();
		  System.out.println("Key in weather is:"+weatherKey);
		  Long lastWeatherChange = (long) weather.get("last_change");
		  System.out.println("Last weather time change: "+lastWeatherChange);
		  String weatherConditon = (String) weather.get("condition");
		  System.out.println("weather condition: "+weatherConditon);
		  System.out.println();
		  System.out.println();

		  int countError =1;
	  
	  
		  for(Object key:keys){
		  
		  		System.out.println("############################");
		  		System.out.println("Right now you working with:"+ Integer.parseInt((String) key));
		  		System.out.println("#########################");



		  		JSONObject userid = (JSONObject) icetizen.get(key);
	   
		  		JSONObject last_known_destinantion = (JSONObject) userid.get("last_known_destination");
		
	   
	  
	//   if(!last_known_destinantion.get("timestamp").equals(null) && !last_known_destinantion.get("position").equals(null) )  {


			   try{
				   
			  
				   if(!last_known_destinantion.get("timestamp").equals(null) && !last_known_destinantion.get("position").equals(null)){
				   
					   String timestampString = ""+last_known_destinantion.get("timestamp"); 
					   String stringPosition = (String) last_known_destinantion.get("position");
					   long timestamp = Long.parseLong(timestampString);
					   
					   int beginIndex = 1;
					   int endIndex = stringPosition.indexOf(",");
					   int beginIndex2 = endIndex+1;
					   int endIndex2 = stringPosition.indexOf(")");
				
				
					   int x = Integer.parseInt(stringPosition.substring(beginIndex, endIndex));
					   
					   int y = Integer.parseInt(stringPosition.substring(beginIndex2, endIndex2));
					   
					   
					   Point position = new Point( x , y);
					   JSONObject user   = (JSONObject) userid.get("user");
					   System.out.println("==========================");
					   System.out.println(stringPosition);
				
					   System.out.println("UserID:"+ Integer.parseInt((String) key));
					   System.out.println("username:" + user.get("username"));
					   System.out.println("type:" + user.get("type"));
					   System.out.println("ip:" + user.get("ip"));
					   System.out.println("port:" + user.get("port"));
					   System.out.println("pid:" + user.get("pid"));
					   System.out.println("stringpositon:"+stringPosition);
					   System.out.println("postion:"+position);
					   System.out.println("timestamp:"+timestamp);
					   System.out.println("Testing Subject:"+testing);
						}
				   
				   else{
						
						System.out.println("Input error"+countError++);
						// do nothing
						}
			   }catch(Exception e){}
			   
				  testing++;
		
				}
			  
			   System.out.println("Testing:"+testing);
		
		
		}catch(Exception e){
			 System.out.println(e);
		}
		  
	   System.out.println("********************************");

	  System.out.println("Total peole(included null positon):"+--testing);
	}
	//sample
//	public void test(){
//
//			super.paintComponent(g);
//			GetCharacterImage("http://iceworld.sls-atl.com/graphics/head/"+headnum[headcount]+".png","http://iceworld.sls-atl.com/graphics/body/"+bodynum[bodycount]+".png","http://iceworld.sls-atl.com/graphics/shirt/"+shirtnum[shirtcount]+".png","http://iceworld.sls-atl.com/graphics/weapon/"+weaponnum[weaponcount]+".png","null");
//
//			g.drawImage(background, 40,20, this);
//
//			g.drawImage(body, 50,25, this);
//			g.drawImage(shirt, 50,25, this);
//			g.drawImage(head, 50,25, this);
//			g.drawImage(weapon,50,25,this);
//			}
//			
//	
}
