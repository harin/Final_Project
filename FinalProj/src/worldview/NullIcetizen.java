package worldview;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import iceworld.given.*;

public class NullIcetizen implements MyIcetizen{
	private String username;
	private int icePortId;
	private IcetizenLook look;
	private int listeningPort;
	private Point pos;
	private BufferedImage lookImage;
	
	public NullIcetizen(){
		this("Octopi", 246, 800, new IcetizenLook());
	}
	
	public NullIcetizen(String username, int portId, int listeningPort, IcetizenLook look){
		this.username = username;
		this.icePortId = portId;
		this.listeningPort = listeningPort;
		this.look = look;
		pos = new Point(0,0);
		lookImage = null;
		prepareLookImage();
	}
	public void prepareLookImage(){
		String body ="blue.png";
		String shirt = "t_ice.png";
		String head = "crown1.png";
		String weapon = "sword2.png";
		
		try{
			BufferedImage bodyImg = ImageIO.read(new File(body));
			BufferedImage shirtImg = ImageIO.read(new File(shirt));
			BufferedImage headImg = ImageIO.read(new File(head));
			BufferedImage weaponImg = ImageIO.read(new File(weapon));
			lookImage = new BufferedImage(bodyImg.getWidth(),
					bodyImg.getHeight(),
					BufferedImage.TYPE_INT_ARGB);
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
	//---------------------------------------------------------------------------------
	//Getter
	//---------------------------------------------------------------------------------
	public BufferedImage getLookImage(){
		return lookImage;
	}
	public Point getPos(){
		return pos;
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

	@Override
	public String getUsername() {
		return username;
	}
	//---------------------------------------------------------------------------------
	//Setter
	//---------------------------------------------------------------------------------

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

}
