package worldview;

import java.awt.Color;
import java.awt.Graphics;

public class IsometricPlane {
	
	//draw a isometric square plane with the left corner coordinate x and y
	//with 'size' box side of 'side' wide tiles.
	// ______ <- tile
	//|		|		}
	//|		|		} side variable refers to the side length of the tile
	//|_____|		}
	// size variable refer to the amount of tiles on the board's side.
	// *note: when the tile is tranform into a isometric projection, it's dimension will change
	public static void drawBoardTile(Graphics g,int x,int y, int size, int side){
		int yIncrement = side/2;
		int xIncrement = side*2;
		int xDiagIncrement = side;
		int yDiagIncrement = yIncrement;
		int run = size;
		int currentTileX, currentTileY;
		for(int i=0; i<run;i++){
			currentTileX = x+(xIncrement*i);
			currentTileY = y;
			drawTile(g, currentTileX, currentTileY, side );
			size--;
			tilePropUp(g,currentTileX + xDiagIncrement, currentTileY - yDiagIncrement, side, size);
			tilePropDown(g,currentTileX + xDiagIncrement,currentTileY + yDiagIncrement,side, size);	
		}
	}
	private static void tilePropUp(Graphics g,int x, int y, int side, int size){
		if(size>0){
			drawTile(g, x, y, side);
			tilePropUp(g,x + side,y - side/2, side, --size);
		}
	}
	private static void tilePropDown(Graphics g,int x, int y, int side, int size){
		if(size>0){
			drawTile(g, x, y, side);
			tilePropDown(g,x + side,y + side/2, side, --size);
		}
	}
	
	public static void drawTile(Graphics g, int x, int y, int side){
		int [] xCor = {x, x+side, x+side*2, x+side};
		int [] yCor = {y, y-side/2, y , y+side/2};
		g.setColor(Color.BLACK);
		g.drawPolygon(xCor, yCor, 4);
	}
}
