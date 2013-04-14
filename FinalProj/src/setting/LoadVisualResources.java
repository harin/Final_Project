package setting;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class LoadVisualResources extends JPanel{

	static BufferedImage head,body,shirt,weapon,background;
	int bodycount=0;
	int headcount=0;
	int shirtcount=0;
	int weaponcount=0;
	static String[] headS, bodyS, shirtS, weaponS;
	static JSONParser json = new JSONParser();
	static ContainerFactory containerFactory = new ContainerFactory() {
		public LinkedList creatArrayContainer() { return new LinkedList(); }
		public Map createObjectContainer() { return new LinkedHashMap(); }
	};

	public static void init() throws IOException{
		String inputLine;
		URL url = null;
		url= new URL("http://iceworld.sls-atl.com/api/&cmd=gresources&uid=0");
		URLConnection connection = url.openConnection();
		connection.connect();
		BufferedReader temp = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		int b=0,h=0,s=0,w=0;
		while ((inputLine = temp.readLine()) != null){
			String datArray = inputLine.substring(inputLine.lastIndexOf("["));
			for(int i=0;i<datArray.length();i++){
				if(datArray.charAt(i)=='B')
					b++;
				if(datArray.charAt(i)=='H')
					h++;
				if(datArray.charAt(i)=='S')
					s++;
				if(datArray.charAt(i)=='W')
					w++;
			}
			bodyS =new String[b];
			headS =new String[h];
			shirtS =new String[s];
			weaponS =new String[w];
			int bcount=0,hcount=0,scount=0,wcount=0;
			for(int i=0;i<datArray.length();i++){
				if(datArray.charAt(i)=='B'){
					bodyS[bcount]=datArray.substring(i, i+4);
					bcount++;
				}
				if(datArray.charAt(i)=='H'){
					headS[hcount]=datArray.substring(i, i+4);
					hcount++;
				}
				if(datArray.charAt(i)=='S'){
					shirtS[scount]=datArray.substring(i, i+4);
					scount++;
				}
				if(datArray.charAt(i)=='W'){
					weaponS[wcount]=datArray.substring(i, i+4);
					wcount++;
				}
			}
		}
	}
	public static void GetCharacterImage(String H,String B,String S,String W){
		try{
			head=ImageLoader.GetImageFromCloud(H);
			body=ImageLoader.GetImageFromCloud(B);
			shirt=ImageLoader.GetImageFromCloud(S);
			weapon=ImageLoader.GetImageFromCloud(W);
		}
		catch(Exception e){}
	}
	
	public static String getBody(int i) throws IOException{
		URL url = null;
		String breq="http://iceworld.sls-atl.com/api/&cmd=gurl&gid="+bodyS[i];
		String aa, linkToImage ="";;
		ImageIcon bodyimg;
		url= new URL(breq);
		URLConnection connection = url.openConnection();
		connection.connect();
		BufferedReader temp = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		while ((aa = temp.readLine()) != null){			
			try {
				Map jsonMap = (Map) json.parse(aa, containerFactory);
				Map jsonData = (Map)jsonMap.get("data");
				
				linkToImage = (String) jsonData.get("location");
			}catch (ParseException e) {
				e.printStackTrace();
			}
		}
		Image image = null;
		URL blink = new URL("http://iceworld.sls-atl.com/"+linkToImage);
		image = ImageIO.read(blink);
		bodyimg= new ImageIcon(image);

		return linkToImage;
	}
	public static String getHead(int i) throws IOException{
		URL url = null;
		String hreq="http://iceworld.sls-atl.com/api/&cmd=gurl&gid="+headS[i];
		String aa, linkToImage ="";;
		url= new URL (hreq);
		URLConnection connection = url.openConnection();
		connection.connect();
		BufferedReader temp = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		while ((aa = temp.readLine()) != null)
		{
			try {
				Map jsonMap = (Map) json.parse(aa, containerFactory);
				Map jsonData = (Map)jsonMap.get("data");
				
				linkToImage = (String) jsonData.get("location");
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return linkToImage;
	}
	public static String getShirt(int i) throws IOException{
		URL url = null;
		String sreq="http://iceworld.sls-atl.com/api/&cmd=gurl&gid="+shirtS[i];
		String aa, linkToImage ="";;
		url= new URL (sreq);
		URLConnection connection = url.openConnection();
		connection.connect();
		BufferedReader temp = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		while ((aa = temp.readLine()) != null)
		{
			
			try {
				Map jsonMap = (Map) json.parse(aa, containerFactory);
				Map jsonData = (Map)jsonMap.get("data");
				
				linkToImage = (String) jsonData.get("location");
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return linkToImage;
	}
	public static String getWeapon(int i) throws IOException{
		URL url = null;
		String wreq="http://iceworld.sls-atl.com/api/&cmd=gurl&gid="+weaponS[i];
		String aa, linkToImage ="";;
		url= new URL (wreq);
		URLConnection connection = url.openConnection();
		connection.connect();
		BufferedReader temp = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		while ((aa = temp.readLine()) != null){
			try {
				Map jsonMap = (Map) json.parse(aa, containerFactory);
				Map jsonData = (Map)jsonMap.get("data");
				linkToImage = (String) jsonData.get("location");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return linkToImage;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		try {
			GetCharacterImage("http://iceworld.sls-atl.com/"+getHead(headcount),"http://iceworld.sls-atl.com/"+getBody(bodycount),"http://iceworld.sls-atl.com/"+getShirt(shirtcount),"http://iceworld.sls-atl.com/"+getWeapon(weaponcount));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(background, 40,20, this);
		
		g.drawImage(body, 50,25, this);
		g.drawImage(shirt, 50,25, this);
		g.drawImage(head, 50,25, this);
		g.drawImage(weapon,50,25,this);
		this.setSize(300,250);
	}
}