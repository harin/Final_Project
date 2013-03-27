package sample;

import java.applet.Applet;
import java.awt.*;
import worldview.IsometricPlane;

public class IsometricPlaneAppletSample extends Applet {
	public IsometricPlaneAppletSample(){
		super();
		this.setPreferredSize(new Dimension(600,600));
	}
	public void paint(Graphics g){
		//draw plane of 15*15 tiles with size 20 at (0,300)
		IsometricPlane.drawBoardTile(g, 0, 300, 15, 20);
		//draw plane of 5*5 tiles with size 30 at (0,100)
		IsometricPlane.drawBoardTile(g, 0, 100, 5, 30);
	}
}
