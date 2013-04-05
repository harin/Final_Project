package sample;

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
	  
	  
	  try{
	  URL iceworld = new URL(domain);
	  URLConnection con = iceworld.openConnection();
	  BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	  String json = in.readLine();
	  
	  JSONParser parser = new JSONParser();
	  
	  JSONObject state = (JSONObject) parser.parse(json);
	  System.out.println(state);
	  
	  JSONObject data = (JSONObject) state.get("data");
	  JSONObject icetizen = (JSONObject) data.get("icetizen");
	  System.out.println(icetizen);
	  
	  Set keys = icetizen.keySet();
	  
	  for(Object key:keys){
	   JSONObject userid = (JSONObject) icetizen.get(key);
	   JSONObject user   = (JSONObject) userid.get("user");
	   System.out.println("==========================");
	   System.out.println("username:" + user.get("username"));
	   System.out.println("type:" + user.get("type"));
	   System.out.println("ip:" + user.get("ip"));
	   System.out.println("port:" + user.get("port"));
	   System.out.println("pid:" + user.get("pid"));
	  }
	}catch(Exception e){
		System.out.println(e);
	}
	}
}
