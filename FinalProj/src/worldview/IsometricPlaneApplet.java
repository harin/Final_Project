package worldview;



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//This applet is for testing purpose only
public class IsometricPlaneApplet extends JApplet{
	private WorldView wv;
	public void init(){
		wv = new WorldView();
		this.add(wv);
	}
	
	class WorldView extends JPanel{
		private Point[][] tileCoord;
		private Dimension d;
		private final int size = 5;
		private final int xOrigin = 200;
		private final int yOrigin = 50;
		private final int tileSide = 50;
		private Point highlightTile;
		
		public WorldView(){
			super();
			tileCoord = new Point[size][size];
			this.setSize(500,500);
			d = this.getSize();
			highlightTile = new Point(-1,-1);
			this.addMouseMotionListener(new MouseMotionListener(){

				@Override
				public void mouseDragged(MouseEvent arg0) {
					// TODO Auto-generated method stub			
				}

				@Override
				public void mouseMoved(MouseEvent arg0) {
					// TODO Auto-generated method stub
					int x = arg0.getX();
					int y = arg0.getY();
					System.out.print("("+x+","+y+")=>");
					
					//trying to calculate which tile the mouse is over.
					
					int coordY = (y - yOrigin)/ (tileSide/2);
					int coordX =(int) Math.round((x - xOrigin + (y-yOrigin)) /tileSide );
					System.out.println("("+coordX+","+coordY+")");
					if(coordX>=0 && coordY>=0){
						highlightTile.x = coordX;
						highlightTile.y = coordY;
						repaint();
					}
				}		
			});
		}

		
		public void paintComponent(Graphics g){

			g.setColor(Color.BLACK);
			g.fillRect(0, 0, d.width, d.height);
			g.setColor(Color.WHITE);
			drawBoardTile(g, xOrigin, yOrigin, size, tileSide );
			//if(highlightTile.x > xOrigin && highlightTile.y > yOrigin){
				System.out.println("highlighting");
				g.setColor(Color.RED);
				Point tile = tileCoord[highlightTile.x][highlightTile.y];
				fillTile(g,tile.x,  tile.y, tileSide);
			//}
			
		}
		
		public  void drawBoardTile(Graphics g,int x,int y, int size, int tileSide){
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
		public  void drawTile(Graphics g, int x, int y, int side){
			int [] xCor = {x, x+side, x + side/2, x-side/2};
			int [] yCor = {y, y, y + side/2 , y+side/2};
			g.drawPolygon(xCor, yCor, 4);
		}
		public  void fillTile(Graphics g, int x, int y, int side){
			int [] xCor = {x, x+side, x + side/2, x-side/2};
			int [] yCor = {y, y, y + side/2 , y+side/2};
			g.fillPolygon(xCor, yCor, 4);
		}
	}
}
