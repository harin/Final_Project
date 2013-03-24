package sample;

import iceworld.given.IcetizenLook;
import iceworld.given.MyIcetizen;

public class TestMyIcetizen implements MyIcetizen {

	private String username;
	private int icePortId;
	private IcetizenLook look;
	private int listeningPort;
	
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
