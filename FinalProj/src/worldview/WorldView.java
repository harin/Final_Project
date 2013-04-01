package worldview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class WorldView extends JPanel {
		private final int size = 20;
		private int xOrigin = 300;
		private int yOrigin = 50;
		private int tileSide = 50;
		private Point[][] tileCoord;
		private Point highlightTile;
		private Point destination;
		private NullIcetizen icetizen;
		
		public WorldView(int width, int height){
			super();
			tileCoord = new Point[size][size];
			this.setSize(width,height);
			
			highlightTile = new Point(-1,-1);
			destination = new Point(0,0);
			icetizen = new NullIcetizen();	
			MouseHandler mh = new MouseHandler();
			this.addMouseMotionListener(mh);
			this.addMouseListener(mh);
		}
		public WorldView(){
			this(500,500);
		}
		
		public void paintComponent(Graphics g){
			//draw tile
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(Color.WHITE);
			IsometricPlane.drawBoardTile(g, xOrigin, yOrigin, size, tileSide, tileCoord );
			
			//highlight tile
			if(highlightTile.x >= 0 && highlightTile.y >= 0
					&& highlightTile.x < size && highlightTile.y < size){
				//System.out.println("highlighting");
				g.setColor(Color.RED);
				Point tile = tileCoord[highlightTile.x][highlightTile.y];
				IsometricPlane.fillTile(g,tile.x,  tile.y, tileSide);
			}
			
			//draw icetizen
			drawIcetizen(g,0,0);
			
		}
		
//--------------------------------------------------------------------------------------------
//		navigation methods
//--------------------------------------------------------------------------------------------
		public void zoomIn(){
			tileSide+=5;
			repaint();
		}
		
		public void zoomOut(){
			tileSide-=5;
			repaint();
		}
		
		public void moveOrigin(int x,int y){
			System.out.print("Change origin from "+xOrigin+","+yOrigin+" to ");
			xOrigin += x;
			yOrigin += y;
			System.out.println(xOrigin+","+yOrigin);
			repaint();
		}
//--------------------------------------------------------------------------------------------
//		Drawing Icetizen methods
//--------------------------------------------------------------------------------------------

		public void drawIcetizen(Graphics g, int x, int y){
			String loc ="blue.png";
			BufferedImage img = null;
			try{
				img = ImageIO.read(new File(loc));
			} catch (Exception e){
				System.out.println("Failed to load image");
			}
			if(img!=null) {
				Image scale = scaleToTile(img);
			
				if(!icetizen.getPos().equals(destination)){
					//System.out.println("animating");
					int xMove = destination.x - icetizen.getPos().x;
					int yMove = destination.y - icetizen.getPos().y;
					if(xMove>0) icetizen.getPos().x++;
					 else if (xMove<0) icetizen.getPos().x--;
					 else {}//do nothing
					
					if(yMove>0) icetizen.getPos().y++;
					else if (yMove<0) icetizen.getPos().y--;
					else {}//do nothing
				}
				
				int xCoord = icetizen.getPos().x;
				int yCoord = icetizen.getPos().y;
				int yPos = tileCoord[xCoord][yCoord].y - scale.getHeight(null) + tileSide/4;
				int xPos = tileCoord[xCoord][yCoord].x - scale.getWidth(null)/2 + tileSide/2 - scale.getWidth(null)/5;
				g.drawImage(scale, xPos, yPos ,null);
				if(!icetizen.getPos().equals(destination)) repaint();
			}
		}
		
		public Image scaleToTile(BufferedImage img){
			Image scale = img.getScaledInstance(tileSide, -1, Image.SCALE_SMOOTH);
			return scale;
		}
		
//--------------------------------------------------------------------------------------------
//		Handler class
//--------------------------------------------------------------------------------------------			
		class MouseHandler extends MouseAdapter implements MouseMotionListener{
			private Point lastPress = new Point(-1,-1);
			public void mouseMoved(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int x = arg0.getX();
				int y = arg0.getY();
				//System.out.print("("+x+","+y+")=>");
				
				//trying to calculate which tile the mouse is over.
				
				int coordY = (y - yOrigin)/ (tileSide/2);
				int coordX =(int) Math.round((x - xOrigin + (y-yOrigin)) /tileSide );
				//System.out.println("("+coordX+","+coordY+")");
				if(coordX>=0 && coordY>=0 && 
						coordX < size && coordY < size){
					highlightTile.x = coordX;
					highlightTile.y = coordY;
					repaint();
				}
			}
			public void mouseDragged(MouseEvent e){
				//Find the difference between the last mouse point and this point
				//then make the origin of the isometric plane move by the same amount
				int x = e.getX();
				int y = e.getY();
				int xMoved = x-lastPress.x;
				int yMoved = y-lastPress.y;
				System.out.println("moved "+xMoved+","+yMoved);
				lastPress.x = x;
				lastPress.y = y;
				moveOrigin(xMoved,yMoved);
			}
			
			public void mousePressed(MouseEvent e){
				//To ensure that lastPress is initialized properly for the mouseDragged
				lastPress.x = e.getX();
				lastPress.y = e.getY();
			}
			
			public void mouseClicked(MouseEvent e){
				//icetizen.move(highlightTile.x, highlightTile.y);
				destination.x = highlightTile.x;
				destination.y = highlightTile.y;
				System.out.println("move to:"+ highlightTile.x +","+highlightTile.y);
				repaint();
			}
	
		}
		
		
	}