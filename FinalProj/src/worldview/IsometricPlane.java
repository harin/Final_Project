package worldview;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class IsometricPlane{
	public static void drawBoardTile(Graphics g,int x,int y, int size, int tileSide, Point[][] tileCoord){
		for(int i =0 ; i<size; i++){ // x multiplier
			for(int j=0; j<size; j++){// y multiplier
				int tileX = x + i*(tileSide) - j*(tileSide/2);
				int tileY = y + j*(tileSide/2);
				drawTile(g,tileX,tileY ,tileSide);
				tileCoord[i][j] = new Point(tileX, tileY);
				//System.out.println("tile["+i+"]["+j+"]=("+tileX+","+tileY+")");
			}
		}
	}
	public static  void drawTile(Graphics g, int x, int y, int side){
		int [] xCor = {x, x+side, x + side/2, x-side/2};
		int [] yCor = {y, y, y + side/2 , y+side/2};
		g.drawPolygon(xCor, yCor, 4);
	}
	public static void fillTile(Graphics g, int x, int y, int side){
		int [] xCor = {x, x+side, x + side/2, x-side/2};
		int [] yCor = {y, y, y + side/2 , y+side/2};
		g.fillPolygon(xCor, yCor, 4);
	}
	
	
}
