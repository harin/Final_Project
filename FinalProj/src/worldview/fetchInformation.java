package worldview;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class fetchInformation {
	 final String domain =  "http://iceworld.sls-atl.com/api/&cmd=states";
	  String domain2 = "http://www.google.com";
	  String cmd;
	  Set keys;
	  JSONParser parser;
	  JSONObject state;
	  JSONObject data;
	  JSONObject icetizen ;
	  JSONObject weather;
	  Long timeWeatherLastChange;
	  String weatherCondition;
	  
		LinkedList<NullIcetizen> list = new LinkedList <NullIcetizen>();
	  
	  public fetchInformation(){
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
