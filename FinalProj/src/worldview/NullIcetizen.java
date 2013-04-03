package worldview;
import java.awt.Point;
import java.awt.geom.Point2D;

import iceworld.given.*;

public class NullIcetizen implements MyIcetizen{
	private String username;
	private int icePortId;
	private IcetizenLook look;
	private int listeningPort;
	private Point pos;
	
	public NullIcetizen(){
		this("Octopi", 246, 800, new IcetizenLook());
	}
	
	public NullIcetizen(String username, int portId, int listeningPort, IcetizenLook look){
		this.username = username;
		this.icePortId = portId;
		this.listeningPort = listeningPort;
		this.look = look;
		pos = new Point(0,0);
	}
	
	public void move(int x, int y){
		pos.x = x;
		pos.y = y;
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
