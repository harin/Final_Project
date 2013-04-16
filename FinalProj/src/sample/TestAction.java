package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TestAction {
	 final String domain =  " http://iceworld.sls-atl.com/api/&cmd=actions&from=";
	 final String domainTime = "http://iceworld.sls-atl.com/api/&cmd=time";
	 static long currentTime;
	 	
	 
	 public static void main(String[] args){
		  try{
				URL timeDomain = new URL("http://iceworld.sls-atl.com/api/&cmd=time");
				URLConnection timeCon = timeDomain.openConnection();
				BufferedReader in = new BufferedReader(new InputStreamReader(timeCon.getInputStream()));
				String json = in.readLine();

				JSONParser parser = new JSONParser();
				JSONObject jtime = (JSONObject) parser.parse(json);
				System.out.println(jtime);
				String timedata = (String) jtime.get("data");
				
				currentTime = Long.parseLong(""+timedata);
			  }catch(Exception e){
				  System.out.println("cannot get current time"+e);
				  e.printStackTrace();
			  }
		  System.out.println(currentTime);
		  System.out.println("============");
		  
		  
		  
		  
		  try{
			  	URL actionDomain = new URL("http://iceworld.sls-atl.com/api/&cmd=actions&from="+(currentTime-300));//300 second back
				URLConnection actionCon = actionDomain.openConnection();
				BufferedReader in = new BufferedReader(new InputStreamReader(actionCon.getInputStream()));
				String json = in.readLine();
				JSONParser parser = new JSONParser();
				JSONObject jaction = (JSONObject) parser.parse(json);
				
				//
				System.out.println(jaction);
				//
				
				
				Set actionKey = jaction.keySet();
				
				
				//
				System.out.println(actionKey);
				//
				
//				String dataAction =  jaction.get("data").toString();
//				System.out.println("Data action before substring:"+dataAction);
				
				JSONArray jdataAction = (JSONArray) jaction.get("data");
				System.out.println("jdataAction is"+jdataAction);

//				
//				dataAction =dataAction.substring(1, dataAction.length());
//				System.out.println("data action is:"+dataAction);
//				
//				JSONParser actionParser = new JSONParser();
//				JSONObject jdataAction = (JSONObject) actionParser.parse(dataAction);
//				
//				System.out.println("jdataAction is:"+jdataAction);
				
		  }catch(Exception e){
			  System.out.println("cannot get action"+e);
			  e.printStackTrace();
		  }
		  
	 }
}
