package worldview;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class FetchInformation {
	 final String domain =  "http://iceworld.sls-atl.com/api/&cmd=states";
	  String cmd;
	  Set keys;
	  
	  JSONParser parser;
	  JSONObject state;
	  JSONObject data;
	  JSONObject icetizen ;
	  JSONObject weather;
	  Long timeWeatherLastChange;
	  String weatherCondition;
	  
	  //linkedlist of NullIcetizen
		LinkedList<NullIcetizen> list = new LinkedList <NullIcetizen>();
	  
	  public FetchInformation(){
		  try{
			  
			  
			  
			  URL iceworld = new URL(domain);
			  URLConnection con = iceworld.openConnection();
			  BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			  String json = in.readLine();
			  
			  parser = new JSONParser();
			  state = (JSONObject) parser.parse(json);
			  data = (JSONObject) state.get("data");
			  
			  
			  icetizen = (JSONObject) data.get("icetizen");
			  
			  //weather condition
			  weather = (JSONObject) data.get("weather");
			  timeWeatherLastChange = (long) weather.get("last_change");
			  weatherCondition = (String) weather.get("condition");
			  //end of weather condition
			  
			  
			  for(Object key:keys){
				   JSONObject userid = (JSONObject) icetizen.get(key);
				   JSONObject user   = (JSONObject) userid.get("user");
				   String id = (String) key;
				   String username =  (String) user.get("username");
				   int pid = (int) user.get("pid");
				   String ip = (String) user.get("ip");
				   
				   
				   //position and timestamp
				   JSONObject last_known_destinantion = (JSONObject) userid.get("last_known_destination");
				   
				   try{ //loop2
					   if(!last_known_destinantion.get("timestamp").equals(null) && !last_known_destinantion.get("position").equals(null)){
					   
						   IcetizenLook look =;
						   long timestamp = (long) last_known_destinantion.get("timestamp");   
						   String stringPosition = (String) last_known_destinantion.get("position");
						   int beginIndex = 1;
						   int endIndex = stringPosition.indexOf(",");
						   int beginIndex2 = endIndex+1;
						   int endIndex2 = stringPosition.indexOf(")");
						   int x = Integer.parseInt(stringPosition.substring(beginIndex, endIndex));		   
						   int y = Integer.parseInt(stringPosition.substring(beginIndex2, endIndex2));			   
					   
						   int listeningPort=  Integer.parseInt( (String) user.get("port") ) ;
		
					   
						   Point position = new Point( x , y);
						   Point destination = new Point(x,y);
					   
					   
					   
						   list.add(new NullIcetizen(id,username ,pid,   listeningPort,  look, ip, timestamp,position,destination ));
					   }
					   else{
						   //do nothing
					   }
				   }catch(Exception e){
					   System.out.println("Cannot fetch the information from the server");
					   System.out.println("Error in try catch2:"+e.toString());
				   }
			  }
			  
			  
		}catch(Exception e){
		//error happen
			System.out.println("Cannot fetch the information from the server");
			System.out.println("Error:"+e.toString());
		
		}
	  }
	  
	  
	  public long  getTimeWeatherLastChange(){
		  return  timeWeatherLastChange;
	  }
	  
	  public String getWeatherCondition(){
		  return weatherCondition;
	  }
}
