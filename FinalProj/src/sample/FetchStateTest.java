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
		
		System.out.println("weather:"+weather); //{"last_change":1365011760,"condition":"Sunny"}
		System.out.println("icetizen:" +icetizen); 
		
		
		
		//increasing part
		Object weatherCondition =   weather.get("condition");
		Object status = array.get("status"); // not know yet what can it do
		
		JSONObject user =(JSONObject) icetizen.get("user");
		
		
	
		System.out.println(weatherCondition);

		System.out.println(status);
		System.out.println(user);
		
		//*****
		//{"1004":{"last_known_destination":{"timestamp":null,"position":null},"user":{"port":"0","username":"Badstrongbad","pid":"99","type":0,"ip":"161.200.80.247"}},"1008":
		//{"last_known_destination":{"timestamp":"1364441726","position":"(9,4)"},"user":{"port":"0","username":"Babeltarotank","pid":"246","type":0,"ip":"161.200.80.85"}},"1005":
		//{"last_known_destination":{"timestamp":"1363690603","position":"(9,4)"},"user":{"port":"0","username":"Freakhawkxy","pid":"99","type":0,"ip":"161.200.80.247"}},"1006":{"last_known_destination":{"timestamp":"1363858452","position":"(9,4)"},"user":{"port":"0","username":"Sillywuzz","pid":"99","type":0,"ip":"161.200.81.21"}},"1017":{"last_known_destination":{"timestamp":"1364958809","position":"(9,4)"},"user":{"port":"0","username":"Roosrider","pid":"246","type":0,"ip":"161.200.80.85"}},"1016":{"last_known_destination":{"timestamp":"1364958795","position":"(9,4)"},"user":{"port":"0","username":"Terranpepsi","pid":"246","type":0,"ip":"161.200.80.85"}},"1019":{"last_known_destination":{"timestamp":"1364958893","position":"(9,4)"},"user":{"port":"0","username":"Applemoonduke","pid":"246","type":0,"ip":"161.200.80.85"}},"1009":{"last_known_destination":{"timestamp":"1364828156","position":"(9,4)"},"user":{"port":"0","username":"Yankeeprince","pid":"257","type":0,"ip":"171.101.79.64"}},"1018":{"last_known_destination":{"timestamp":"1364958819","position":"(9,4)"},"user":{"port":"0","username":"Shoprock","pid":"246","type":0,"ip":"161.200.80.85"}},"1013":{"last_known_destination":{"timestamp":"1364958637","position":"(9,4)"},"user":{"port":"0","username":"Queenfuji","pid":"246","type":0,"ip":"161.200.80.85"}},"1012":{"last_known_destination":{"timestamp":"1364958632","position":"(9,4)"},"user":{"port":"0","username":"Cannonhotcool","pid":"246","type":0,"ip":"161.200.80.85"}},"1015":{"last_known_destination":{"timestamp":"1364958789","position":"(9,4)"},"user":{"port":"0","username":"Goerskinful","pid":"246","type":0,"ip":"161.200.80.85"}},"1014":{"last_known_destination":{"timestamp":"1364958781","position":"(9,4)"},"user":{"port":"0","username":"Saberdragmoon","pid":"246","type":0,"ip":"161.200.80.85"}},"1020":{"last_known_destination":{"timestamp":"1364958926","position":"(9,4)"},"user":{"port":"0","username":"Ramaspiritsphere","pid":"246","type":0,"ip":"161.200.80.85"}},"1021":{"last_known_destination":{"timestamp":"1365011689","position":"(9,4)"},"user":{"port":"0","username":"Drinkerpimp","pid":"246","type":0,"ip":"58.9.195.234"}},"1":{"last_known_destination":{"timestamp":"1363674665","position":"(20,30)"},"user":{"port":"0","username":"Atiwong.S","pid":"99","type":1,"ip":"161.200.81.21"}},"1022":{"last_known_destination":{"timestamp":"1365011700","position":"(9,4)"},"user":{"port":"0","username":"Bitchdof","pid":"246","type":0,"ip":"58.9.195.234"}},"1011":{"last_known_destination":{"timestamp":"1364958627","position":"(9,4)"},"user":{"port":"0","username":"Marurocksaurus","pid":"246","type":0,"ip":"161.200.80.85"}},"1010":{"last_known_destination":{"timestamp":"1364958601","position":"(9,4)"},"user":{"port":"0","username":"Dockbrewtrue","pid":"246","type":0,"ip":"161.200.80.85"}}}
		
		
		//end part
		
		
		//what do we have now: 
		//weatherCondition, status
		
		//**what left
		//icetizen field "1" json fields
		// username, type, ip, port, pid
		//icetizen field "last_known_destination"
		// postion, timestamp
		//last updated 4/04/2013
	}
}

