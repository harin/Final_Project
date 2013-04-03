package worldview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.*;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class WorldView extends JPanel {
		private final int size = 100;
		private int xOrigin = 300;
		private int yOrigin = 50;
		private int tileSide = 50;
		private Point[][] tileCoord;
		private Point highlightTile;
		private Point destination;
		private NullIcetizen activeIcetizen;
		private NullIcetizen [] icetizens;
		private Timer timer;
		private int delay = 40;
		
		public WorldView(int width, int height){
			super();
			tileCoord = new Point[size][size];
			this.setSize(width,height);
			
			highlightTile = new Point(-1,-1);
			destination = new Point(0,0);
			activeIcetizen = new NullIcetizen();	
			MouseHandler mh = new MouseHandler();
			this.addMouseMotionListener(mh);
			this.addMouseListener(mh);
			
			//generated nullicetizen for test
			icetizens = new NullIcetizen[50];
			for(int i =0; i< 50; i++){
				icetizens[i] = new NullIcetizen();
				icetizens[i].setPosition(new Point(i+1,i+1));
				icetizens[i].setDestination(new Point(i+1,i+1));
			}
			
			timer = new Timer(delay, new ActionListener(){
				public void actionPerformed(ActionEvent e){
					repaint();
				}
			});
			timer.start();
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
			//show FPS
			g.setColor(Color.WHITE);
			g.drawString(1.0/delay * 1000.0 +" FPS", 10, 20);
			//highlight tile
			if(highlightTile.x >= 0 && highlightTile.y >= 0
					&& highlightTile.x < size && highlightTile.y < size){
				g.setColor(Color.RED);
				Point tile = tileCoord[highlightTile.x][highlightTile.y];
				IsometricPlane.fillTile(g,tile.x,  tile.y, tileSide);
			}
			
			//draw icetizen
			drawActiveIcetizen(g,0,0);
			drawIcetizen(g);
		}
		
//--------------------------------------------------------------------------------------------
//		navigation methods
//--------------------------------------------------------------------------------------------
		public void zoomIn(){
			tileSide+=5;
			activeIcetizen.rescale();
			for(NullIcetizen n: icetizens){
				n.rescale();
			}
			repaint();
			
		}
		
		public void zoomOut(){
			tileSide-=5;
			activeIcetizen.rescale();
			for(NullIcetizen n: icetizens){
				n.rescale();
			}
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
		public void drawIcetizen(Graphics g){
			for(NullIcetizen n: icetizens){
				Image scale = n.getScale();
				if(scale == null){
					BufferedImage img = n.getLookImage();
					n.setScale(scaleToTile(img));
					scale = n.getScale();
				}
				if(scale!=null){
				
//					if(!activeIcetizen.getPos().equals(destination)){
//						//System.out.println("animating");
//						int xMove = destination.x - activeIcetizen.getPos().x;
//						int yMove = destination.y - activeIcetizen.getPos().y;
//						if(xMove>0) activeIcetizen.getPos().x++;
//						 else if (xMove<0) activeIcetizen.getPos().x--;
//						 else {}//do nothing
//						
//						if(yMove>0) n.getPos().y++;
//						else if (yMove<0) n.getPos().y--;
//						else {}//do nothing
//					}
					
					int xCoord = n.getPos().x;
					int yCoord = n.getPos().y;
					int yPos = tileCoord[xCoord][yCoord].y - scale.getHeight(null) + tileSide/4;
					int xPos = tileCoord[xCoord][yCoord].x - scale.getWidth(null)/2 + tileSide/2 - scale.getWidth(null)/5;
					g.drawImage(scale, xPos, yPos ,null);
					//if(!activeIcetizen.getPos().equals(destination)) repaint();
				}else{
					System.out.println("Drawing active Icetizen failed");
				}
			}
		}
		public void drawActiveIcetizen(Graphics g, int x, int y){

			Image scale = activeIcetizen.getScale();
			if(scale == null){
				BufferedImage img = activeIcetizen.getLookImage();
				activeIcetizen.setScale(scaleToTile(img));
				scale = activeIcetizen.getScale();
			}
			if(scale!=null){
			
				if(!activeIcetizen.getPos().equals(destination)){
					//System.out.println("animating");
					int xMove = destination.x - activeIcetizen.getPos().x;
					int yMove = destination.y - activeIcetizen.getPos().y;
					if(xMove>0) activeIcetizen.getPos().x++;
					 else if (xMove<0) activeIcetizen.getPos().x--;
					 else {}//do nothing
					
					if(yMove>0) activeIcetizen.getPos().y++;
					else if (yMove<0) activeIcetizen.getPos().y--;
					else {}//do nothing
				}
				
				int xCoord = activeIcetizen.getPos().x;
				int yCoord = activeIcetizen.getPos().y;
				int yPos = tileCoord[xCoord][yCoord].y - scale.getHeight(null) + tileSide/4;
				int xPos = tileCoord[xCoord][yCoord].x - scale.getWidth(null)/2 + tileSide/2 - scale.getWidth(null)/5;
				g.drawImage(scale, xPos, yPos ,null);
				//if(!activeIcetizen.getPos().equals(destination)) repaint();
			}else{
				System.out.println("Drawing active Icetizen failed");
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
					//repaint();
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
				//repaint();
			}
	
		}
		
		
	}