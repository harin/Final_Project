package worldview;

import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;



public class MiniMap extends JPanel {
	private LinkedList<NullIcetizen> icetizens;
	private NullIcetizen active;
	
	public MiniMap(LinkedList<NullIcetizen> i,NullIcetizen a ){
		icetizens = i;
		active = a;
		this.setSize(new Dimension(102,102));
		this.setBackground(Color.WHITE);
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.BLACK);
		for(NullIcetizen n: icetizens){
			if(n.getPixelPos() != null){
				g.drawRect((int)n.getPixelPos().x, (int)n.getPixelPos().y, 1,1);
			}else{
				g.drawRect(n.getPos().x, n.getPos().y, 1, 1);
			}
		}
		g.setColor(Color.RED);
		if(active.getPixelPos() != null){
			g.drawRect((int)active.getPixelPos().x, (int)active.getPixelPos().y, 1,1);
		}else{
			g.drawRect(active.getPos().x, active.getPos().y, 1, 1);
		}
		
	}
	
	public void paintBorder(Graphics g){
		g.drawRect(0, 0, 100, 100);
	}
}
