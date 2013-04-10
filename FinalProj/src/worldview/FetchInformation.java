package worldview;



import iceworld.given.*;

import java.awt.Point;
import java.awt.image.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.Set;

import javax.imageio.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class FetchInformation {
	 final String domain =  "http://iceworld.sls-atl.com/api/&cmd=states";
	 final String domain2 = "http://iceworld.sls-atl.com/api/&cmd=gresources&uid=";
	 final String domain3 =  "http://iceworld.sls-atl.com/api/&cmd=gurl&gid=";
	 final String domain4 = "http://iceworld.sls-atl.com/";
	  
	 
	 
	  String cmd;
	  Set keys;
	  JSONParser parser;
	  JSONObject state;
	  JSONObject data;
	  JSONObject icetizen ;
	  JSONObject weather;
	  Long timeWeatherLastChange;
	  String weatherCondition;
	  String head;
	  String body;
	  String weapon;
	  String shirt;
	  long type;
	  //linkedlist of NullIcetizen
		LinkedList<NullIcetizen> list = new LinkedList <NullIcetizen>();
	  
	public FetchInformation() {
		try {

			URL iceworld = new URL(domain);
			URLConnection con = iceworld.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String json = in.readLine();

			parser = new JSONParser();
			state = (JSONObject) parser.parse(json);
			data = (JSONObject) state.get("data");

			icetizen = (JSONObject) data.get("icetizen");

			// weather condition
			weather = (JSONObject) data.get("weather");
			timeWeatherLastChange = (long) weather.get("last_change");
			weatherCondition = (String) weather.get("condition");
			// end of weather condition

			for (Object key : keys) {

				IcetizenLook look = new IcetizenLook();
				JSONObject userid = (JSONObject) icetizen.get(key);
				JSONObject user = (JSONObject) userid.get("user");
				String id = (String) key;
				String username = (String) user.get("username");
				int pid = (int) user.get("pid");
				String ip = (String) user.get("ip");
				type = (long) user.get("type");

				// position and timestamp
				JSONObject last_known_destinantion = (JSONObject) userid
						.get("last_known_destination");

				try { // loop2
					if (!last_known_destinantion.get("timestamp").equals(null)
							&& !last_known_destinantion.get("position").equals(
									null)) {

						long timestamp = (long) last_known_destinantion
								.get("timestamp");
						String stringPosition = (String) last_known_destinantion
								.get("position");
						int beginIndex = 1;
						int endIndex = stringPosition.indexOf(",");
						int beginIndex2 = endIndex + 1;
						int endIndex2 = stringPosition.indexOf(")");
						int x = Integer.parseInt(stringPosition.substring(
								beginIndex, endIndex));
						int y = Integer.parseInt(stringPosition.substring(
								beginIndex2, endIndex2));

						int listeningPort = Integer.parseInt((String) user
								.get("port"));

						Point position = new Point(x, y);
						Point destination = new Point(x, y);

						// Doing icetizenLook
						// object key ---> userid
						// domain2 =
						// "http://iceworld.sls-atl.com/api/&cmd=gresources&uid=";
						// domain3 =
						// "http://iceworld.sls-atl.com/api/&cmd=gurl&gid=";
						// domain4 = "http://iceworld.sls-atl.com/";
						//
						try {
							String userLook = domain2 + key.toString();
							URL userLookurl = new URL(userLook);
							URLConnection lookCon = userLookurl
									.openConnection();
							BufferedReader lookBuff = new BufferedReader(
									new InputStreamReader(
											lookCon.getInputStream()));
							String jcloth = lookBuff.readLine();
							System.out.println(jcloth);

							JSONParser dream = new JSONParser();
							JSONObject gresource = (JSONObject) dream
									.parse(jcloth);
							Set keyInResource = gresource.keySet();

							JSONArray cloth = (JSONArray) gresource.get("data"); // cloth
																					// =
																					// data
																					// is
																					// a
																					// array

							String clothString = cloth.toJSONString(); // cloth
																		// in
																		// string
							clothString = clothString.substring(1,
									clothString.length() - 1);// [ ] to cut out
																// the bracket
							JSONParser clothparser = new JSONParser();
							JSONObject clothSon = (JSONObject) clothparser
									.parse(clothString);

							weapon = clothSon.get("W").toString();
							body = clothSon.get("B").toString();
							shirt = clothSon.get("S").toString();
							head = clothSon.get("H").toString();

							// set the look!?!?!!??!??
							look.gidB = body;
							look.gidS = shirt;
							look.gidH = head;
							look.gidW = weapon;

							list.add(new NullIcetizen(id, username, pid,listeningPort, look, ip, type, timestamp,position, destination));

						} catch (Exception e) {
							System.out
									.println("error during get the look:" + e);
						}

					} else {
						// do nothing
					}
				} catch (Exception e) {
					System.out
							.println("Cannot fetch the information from the server");
					System.out.println("Error in try catch2:" + e.toString());
				}
			}

		} catch (Exception e) {
			// error happen
			System.out.println("Cannot fetch the information from the server");
			System.out.println("Error:" + e.toString());

		}
		System.out.println("Fetching OK");
	}
	  
	  
	  public  BufferedImage loadImageFromRemote(String uri){
		  BufferedImage image = null;

		  try {
		   image = ImageIO.read(new URL(uri));
		  } catch (Exception e) {
		   System.out.println("Error from trying to get the image:"+e);
		  } 
		  return image;

		 }
	  
	  
	  public long  getTimeWeatherLastChange(){
		  return  timeWeatherLastChange;
	  }
	  
	  public String getWeatherCondition(){
		  return weatherCondition;
	  }
}
