package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class getUserTest {

	 
	 public static void main(String args[]){
		 final String domain =  "http://iceworld.sls-atl.com/api/&cmd=states";
		 final String domain2 = "http://iceworld.sls-atl.com/api/&cmd=gresources&uid=";
		 final String domain3 =  "http://iceworld.sls-atl.com/api/&cmd=gurl&gid=";
		 final String domain4 = "http://iceworld.sls-atl.com/";
		 final String test0 = "35"; //userid 35 
		 
		 String tester = domain2+test0;
		 try{
			 URL iceworld = new URL(tester);
			 URLConnection con = iceworld.openConnection();
			 BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			 String jcloth = in.readLine();
			 System.out.println(jcloth);
		  
			 JSONParser dream = new JSONParser();
			 JSONObject gresource = (JSONObject) dream.parse(jcloth);
			  Set keyInResource 	= gresource.keySet();
			  System.out.println("key in resource:"+keyInResource);
			  //
	
			 JSONArray cloth = (JSONArray) gresource.get("data"); //data is a array
			 
			 
			 
			 String clothString = cloth.toJSONString();
			 clothString = clothString.substring(1, clothString.length()-1);
			 JSONParser clothparser = new JSONParser();
			 JSONObject clothSon = (JSONObject) clothparser.parse(clothString);
			 
			 String w = clothSon.get("W").toString();
			 String b = clothSon.get("B").toString();
			 String s = clothSon.get("S").toString();
			 String h = clothSon.get("H").toString();


			 
			 
			 System.out.println("=======================");
			 System.out.println("Data:"+cloth);
			 System.out.println("cloth in string:"+clothString);
			 System.out.println("Cloth son:"+clothSon);
			 System.out.println("W is: "+w);
			 System.out.println("B is: "+b);
			 System.out.println("S is: "+s);
			 System.out.println("H is: "+h);

		//	 System.out.println("Head is:"+cloth.get("H"));
			 
			 
		 }catch(Exception e){
			 System.out.println("error:"+e);
		 }	 
		
	 }
}
