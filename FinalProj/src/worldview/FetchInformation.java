package worldview;



import iceworld.given.*;

import java.awt.Image;

import java.awt.Point;
import java.awt.image.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.Set;

import javax.imageio.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class FetchInformation {
	 final String domain =  "http://iceworld.sls-atl.com/api/&cmd=states";
	 final String domain2 = "http://iceworld.sls-atl.com/api/&cmd=gresources&uid=";
	 final String domain3 =  "http://iceworld.sls-atl.com/api/&cmd=gurl&gid=";
	 final String domain4 = "http://iceworld.sls-atl.com/";
	  
	 
	  BufferedImage weaponImage;
	  BufferedImage bodyImage;
	  BufferedImage shirtImage;
	  BufferedImage headImage;
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
	  int listeningPort;
	  Point position;
	  Point destination;
	  long timestamp;
	  
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
			keys = icetizen.keySet();
			System.out.println(keys);
			
			for (Object key : keys) {

				IcetizenLook look = new IcetizenLook();
				JSONObject userid = (JSONObject) icetizen.get(key);
				JSONObject user = (JSONObject) userid.get("user");
				String id = (String) key;
				String username = (String) user.get("username");
				int pid = Integer.parseInt(user.get("pid").toString());
				String ip = (String) user.get("ip");
				type = (long) user.get("type");

				// position and timestamp
				JSONObject last_known_destinantion = (JSONObject) userid.get("last_known_destination");

				try { // loop2
					if (last_known_destinantion.get("timestamp").toString().equals(null)&& last_known_destinantion.get("position").toString().equals(null)) {
						position = new Point(0, 0);
						destination = new Point(0,0);
						timestamp =-1;
						System.out.println("IS IT WORK");
						System.out.println("ID:"+key);
					}
					else{
						System.out.println("======================");
						System.out.println("ID with no null:"+key);
						timestamp =  Long.parseLong( last_known_destinantion.get("timestamp").toString() );
						String stringPosition = (String) last_known_destinantion.get("position");
						System.out.println("Timestamp is:"+timestamp);
						int beginIndex = 1;
						int endIndex = stringPosition.indexOf(",");
						int beginIndex2 = endIndex + 1;
						int endIndex2 = stringPosition.indexOf(")");
						
						int x = Integer.parseInt(stringPosition.substring(beginIndex, endIndex));
							// walk out of bound
							if(x<0){
								x=0;
							}
						int y = Integer.parseInt(stringPosition.substring(beginIndex2, endIndex2));
							// walk out of bound
							if(y<0){
								y=0;
							}
						listeningPort = Integer.parseInt(user.get("port").toString());

						position = new Point(x, y);
						destination = new Point(x, y);
					}
						
						
						// Doing icetizenLook
						// object key ---> userid
						// domain2 =
						// "http://iceworld.sls-atl.com/api/&cmd=gresources&uid=";
						// domain3 =
						// "http://iceworld.sls-atl.com/api/&cmd=gurl&gid=";
						// domain4 = "http://iceworld.sls-atl.com/";
						//
						try {//3
							String userLook = domain2 + key.toString();
							URL userLookurl = new URL(userLook);
							URLConnection lookCon = userLookurl.openConnection();
							BufferedReader lookBuff = new BufferedReader(new InputStreamReader(lookCon.getInputStream()));
							String jcloth = lookBuff.readLine();
							System.out.println("GETTING SOMETHING###"+jcloth);

							JSONParser dream = new JSONParser();
							JSONObject gresource = (JSONObject) dream.parse(jcloth);
							Set keyInResource = gresource.keySet();

							JSONArray cloth = (JSONArray) gresource.get("data"); // cloth = data is a array
																				

							String clothString = cloth.toJSONString(); // cloth in string
																
							clothString = clothString.substring(1,clothString.length() - 1);// [ ] to cut out the bracket
																
							JSONParser clothparser = new JSONParser();
							JSONObject clothSon = (JSONObject) clothparser.parse(clothString);
							
							if(timestamp!=-1 && !id.equalsIgnoreCase("66") && !id.equalsIgnoreCase("77") ){
								System.out.println("ID:"+key+" =====case1");
								weapon = clothSon.get("W").toString();
								body = clothSon.get("B").toString();
								shirt = clothSon.get("S").toString();
								head = clothSon.get("H").toString();
							}else{
								System.out.println("ID:"+key+" =====case2");
								System.out.println("timestamp is:"+timestamp);
								weapon = "W075";
								body = "B001";
								shirt ="S019";
								head = "H110";
								
							}	
							String weaponURL =domain4+getURL(weapon);
							String bodyURL =  domain4 + getURL(body);
							String shirtURL = domain4 + getURL(shirt);
							String headURL =  domain4 + getURL(head);
							
							System.out.println("weaponURL is"+weaponURL);	
							
							weaponImage = fetchImageFromCloud(weaponURL);
							bodyImage = fetchImageFromCloud(bodyURL);
							shirtImage = fetchImageFromCloud(shirtURL);
							headImage = fetchImageFromCloud(headURL);
	
							// set the look!?!?!!??!??
								
							look.gidB = weapon;
							look.gidS = shirt;
							look.gidH = head;
							look.gidW = weapon;
							
						list.add(new NullIcetizen(id, username, pid,listeningPort, look, ip, type, timestamp,position, destination, headImage, bodyImage, shirtImage,weaponImage));

						} catch (Exception e) {
							System.out.println("error during get the look at loop3: " + e);
							e.printStackTrace();		
							}
						

						
				} catch (Exception e) {
					System.out.println("Cannot fetch the information from the server");
					System.out.println("Error in try catch2:" + e.toString());
					e.printStackTrace();		

				}
			}

		} catch (Exception e) {
			// error happen
			System.out.println("Cannot fetch the information from the server loop1");
			System.out.println("Error:" + e.toString());
			e.printStackTrace();		

		}
		System.out.println("Fetching OK");
	}
	  
	public BufferedImage fetchImageFromCloud(String url){
		BufferedImage image = null;
		System.out.println("URL is:"+url);
		try {
			image = ImageIO.read(new URL(url));
		} catch (IOException e) {
			e.printStackTrace();
		}	
	return image;
	}

	
	public String getURL(String serial){
		String URL =null ;
		try{ 
			
			
		URL thelink = new URL(domain3+serial);
		 System.out.println(thelink);
		 
		 URLConnection con2 = thelink.openConnection();
		 BufferedReader receive = new BufferedReader(new InputStreamReader(con2.getInputStream()));
		 String image = receive.readLine();
		 System.out.println("Image:"+image);
		 
		 JSONParser dream2 = new JSONParser();
		 JSONObject linkReturn = (JSONObject) dream2.parse(image);
		 		 
		 JSONObject findLink = (JSONObject) linkReturn.get("data");
		 System.out.println("FindLink:"+findLink);
		 URL = findLink.get("location").toString();
			
		}catch(Exception e){
			System.out.println("Cannot get the URL:"+e);
			e.printStackTrace();
		}
		
		return URL;
	}
	  
	  public long  getTimeWeatherLastChange(){
		  return  timeWeatherLastChange;
	  }
	  
	  public String getWeatherCondition(){
		  return weatherCondition;
	  }

	  
	  
	  public long getTime(){
		  return timestamp;
	  }
	  
	  public LinkedList<NullIcetizen> getCitizen(){
		  return list;
	  }
}
