package worldview;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;

public class IsometricPlane extends JPanel{
	public static void drawBoardTile(Graphics g,int x,int y, int size, int tileSide){
		for(int i =0 ; i<size; i++){ // x multiplier
			for(int j=0; j<size; j++){// y multiplier
				drawTile(g,x + i*(tileSide) - j*(tileSide/2),y + j*(tileSide/2) ,tileSide);
			}
		}
	}

	public static void drawTile(Graphics g, int x, int y, int side){
		int [] xCor = {x, x+side, x + side/2, x-side/2};
		int [] yCor = {y, y, y + side/2 , y+side/2};
		g.setColor(Color.BLACK);
		g.drawPolygon(xCor, yCor, 4);
	}
	
}
