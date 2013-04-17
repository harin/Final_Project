package worldview;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

public class IsometricPlane{
	private static Image grassTile;

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
	
	public static void drawBoardTileImage(Graphics g,int x,int y, int size, int tileSide, Image tile){
		
		
		for(int i =0 ; i<size; i++){ // x multiplier
			for(int j=0; j<size; j++){// y multiplier
				int tileX = x + i*(tileSide) - j*(tileSide/2);
				int tileY = y + j*(tileSide/2);
				g.drawImage(tile, tileX-tileSide/2, tileY, null);
				
				//System.out.println("tile["+i+"]["+j+"]=("+tileX+","+tileY+")");
			}
		}
	}
	public static  void drawTile(Graphics g, int x, int y, int side){
		int [] xCor = {x, x+side, x + side/2, x-side/2};
		int [] yCor = {y, y, y + side/2 , y+side/2};
		
		g.setColor(Color.GREEN);
		g.fillPolygon(xCor, yCor, 4);
		g.setColor(Color.BLACK);
		g.drawPolygon(xCor, yCor, 4);
	}
	public static void fillTile(Graphics g, int x, int y, int side){
		int [] xCor = {x, x+side, x + side/2, x-side/2};
		int [] yCor = {y, y, y + side/2 , y+side/2};
		g.fillPolygon(xCor, yCor, 4);
	}
	
	public static void fillPlane(Graphics g, int x, int y, int side){
		System.out.println("filling plane");
		g.setColor(Color.GREEN);
		int [] xCor = {x, x+side*100, x + (side/2)*100, x-(side/2)*100};
		int [] yCor = {y*100, y*100, y + (side/2)*100 , y+(side/2)*100};
		g.fillPolygon(xCor, yCor, 4);
	}
	
	
}
