package sample;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

import worldview.IsometricPlaneDiamond;

public class IsometricPlaneAppletSample extends Applet implements MouseMotionListener{
	private Graphics gs;
	public IsometricPlaneAppletSample(){
		super();
		this.setPreferredSize(new Dimension(600,600));
		this.addMouseMotionListener(this);
		
	}
	
	public void init(){
		gs = this.getGraphics();
	}
	public void paint(Graphics g){
//		//draw plane of 15*15 tiles with size 20 at (0,300)
//		IsometricPlane.drawBoardTile(g, 0, 300, 15, 20);
		//draw plane of 2*2 tiles with size 60 at (0,100)
		IsometricPlaneDiamond.drawBoardTile(g, 0, 100, 2,60);
		
//		//draw board with the width = width of applet
//		g.setColor(Color.RED)	;
//		Dimension d = this.getSize();
//		IsometricPlane.drawBoardTile(g, 0, d.height/2, d.width/(20*2), 20);
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("("+arg0.getX()+","+arg0.getY()+")");
		
	}
}
