package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import org.json.simple.*;

public class FetchStateTest {
	public static void main(String[] args)throws Exception{
		final String domain =  "http://iceworld.sls-atl.com/api/&cmd=states";
		String domain2 = "http://www.google.com";
		String cmd;
		//check connection
		URL iceworld = new URL(domain);
		URLConnection con = iceworld.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String json = in.readLine();
		System.out.println(json);
		
		Object obj=JSONValue.parse(json);
		JSONObject array=(JSONObject)obj;
		System.out.println(array);
		System.out.println(array.get("status"));
		System.out.println(array.get("data"));
		JSONObject data = (JSONObject)array.get("data");
		System.out.println(data.get("icetizen"));
		JSONObject weather = (JSONObject) data.get("weather");
		JSONObject icetizen = (JSONObject) data.get("icetizen");
		
		System.out.println("weather:"+weather);
		System.out.println("icetizen:" +icetizen);
		//icetizen field "1" json fields
		// username, type, ip, port, pid
		//icetizen field "last_known_destination"
		// postion, timestamp
		
	}
}

