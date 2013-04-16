 package worldview;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import javax.imageio.ImageIO;

import iceworld.given.*;

public class NullIcetizen implements MyIcetizen{
	private long timestamp;
	private String userid;
	private String username;
	private int icePortId;
	private IcetizenLook look;
	private int listeningPort;
	private BufferedImage lookImage;
	private static InetAddress ip;
	private String ipAddress;
	private long type; //0 for alien, 1 for icetizen
	private Point destination;//store destination tile coordinate
	private Point pos;//store current tile coordinate
	private Point2D.Double pixelPos;// store pixel coordinate - use for walking smoothly in between tiles
	
	
	
	BufferedImage bodyImage;
	BufferedImage weaponImage;
	BufferedImage headImage;
	BufferedImage shirtImage;
	private final int talkDelay = 100; // 4 second for 25 fps
	private final int yellDelay = 125; // requirement 5 second
	private int talkCountdown;
	private String talkMsg;
	private int yellCountdown;
	private String yellMsg;
	
	private Image scaleImage;
	
	
	
	
	public static String inputIpAddress(){
		//use the ip address of host
		  try {
			  
				ip = InetAddress.getLocalHost();
				//System.out.println("Current IP address : " + ip.getHostAddress());
			  } catch (Exception e) {
		 
		 
			  }
		  return ip.getHostAddress().toString();
	}
	
	

	
	public NullIcetizen() throws IOException{
//		String body ="blue.png";
//		String shirt = "t_ice.png";
//		String head = "crown1.png";
//		String weapon = "sword2.png";
//		BufferedImage bodyImg = ImageIO.read(new File("blue.png"));
//		BufferedImage shirtImg = ImageIO.read(new File("t_ice.png"));
//		BufferedImage headImg = ImageIO.read(new File("crown1.png"));
//		BufferedImage weaponImg = ImageIO.read(new File("sword2.png"));
//		
		this("SupremeID","Octopi", 246, 800, new IcetizenLook(),  inputIpAddress(),1,1365328730,new Point(0,0), new Point(0,0),ImageIO.read(new File("crown1.png")),ImageIO.read(new File("blue.png")),ImageIO.read(new File("t_ice.png")),ImageIO.read(new File("sword2.png")));
	}
	
	public NullIcetizen(String userid,String username, int portId, int listeningPort, IcetizenLook look,String ipAddress,long type,long timestamp,Point position,Point destination,BufferedImage headImage,BufferedImage bodyImage,BufferedImage shirtImage,BufferedImage weaponImage){
		this.userid = userid;
		this.username = username;
		this.icePortId = portId;
		this.listeningPort = listeningPort;
		this.look = look;
		this.ipAddress = ipAddress;
		this.type = type;
		this.timestamp = timestamp;
		
		//talk properties
		this.talkCountdown = 0;
		this.talkMsg ="";
		
		//location properties
		this.pos = position;	
		this.destination = destination;
		
		pixelPos = null;
		
		//look properties
		this.headImage=headImage;
		this.bodyImage=bodyImage;
		this.shirtImage=shirtImage;
		this.weaponImage=weaponImage;
		lookImage = null;
		scaleImage= null;
		prepareLookImage();
		
		//yell propreties
		this.yellCountdown = 0;
		this.yellMsg = "";
	}
	
	
	
	public void prepareLookImage(){
//		String body ="blue.png";
//		String shirt = "t_ice.png";
//		String head = "crown1.png";
//		String weapon = "sword2.png";
		
		try{
			BufferedImage bodyImg = bodyImage;
			BufferedImage shirtImg = shirtImage;
			BufferedImage headImg = headImage;
			BufferedImage weaponImg = weaponImage;
			lookImage = new BufferedImage(bodyImg.getWidth(),bodyImg.getHeight(),BufferedImage.TYPE_INT_ARGB);
			
			
			
			Graphics g = lookImage.getGraphics();
			g.drawImage(bodyImg,0,0,null);
			g.drawImage(shirtImg,0,0,null);
			g.drawImage(headImg,0,0,null);
			g.drawImage(weaponImg,0,0,null);

		} catch (Exception e){
			System.err.println("Failed to load image");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void move(int x, int y){
		pos.x = x;
		pos.y = y;
	}
	
	public void rescale(){
		scaleImage = null;
	}
	//---------------------------------------------------------------------------------
	//Getter
	//---------------------------------------------------------------------------------
	public Point2D.Double getPixelPos(){
		return pixelPos;
	}
	public BufferedImage getLookImage(){
		return lookImage;
	}
	public Point getPos(){
		return pos;
	}
	public Point getDestination(){
		return destination;
	}
	
	@Override
	public int getIcePortID() {
		return icePortId;
	}

	@Override
	public IcetizenLook getIcetizenLook() {
		return look;
	}
	
	@Override
	public int getListeningPort() {
		return listeningPort;
	}
	public String getUserid(){
		return userid;
	}
	@Override
	public String getUsername() {
		return username;
	}
	public Image getScale(){
		return scaleImage;
	}
	public int getTalkSecondLeft(){
		return this.talkCountdown;
	}
	
	public String getTalkMsg(){
		
		this.talkCountdown--;
		return this.talkMsg;
	}
	
	//yell
	public int getYellSecondLeft(){
		return this.talkCountdown;
	}
	
	public String getYellMsg(){
		
		this.yellCountdown--;
		return this.yellMsg;
	}
	
	//---------------------------------------------------------------------------------
	//Setter
	//---------------------------------------------------------------------------------
	public void setPixelPos(Point2D.Double p){
		pixelPos = p;
	}
	@Override
	public void setIcePortID(int arg0) {
		icePortId = arg0;
	}

	@Override
	public void setIcetizenLook(IcetizenLook arg0) {
		look =arg0;
	}

	@Override
	public void setListeningPort(int arg0) {
		listeningPort = arg0;
	}

	@Override
	public void setUsername(String arg0) {
		username = arg0;
		
	}
	
	public void setPosition(Point p){
		pos = p;
	}
	
	public void setDestination(Point p){
		destination = p;
	}
	
	public void setScale(Image s){
		scaleImage = s;
	}
	
	public void setTalkMsg(String s){
		this.talkMsg = s;
		talkCountdown = talkDelay;
	}

	public void setYellMsg(String s){
		this.yellMsg = s;
		yellCountdown = yellDelay;
	}
	
	
	
	//compare NullIcetizen "Is he the same person"
	public boolean compare(NullIcetizen a, NullIcetizen b){
		
		if(a.getUserid().equalsIgnoreCase(b.getUserid())){
			//same person
		return true;
		
		}else{ 
			//not sameperson
			return false;		
		}
	}

	
	
	
}
