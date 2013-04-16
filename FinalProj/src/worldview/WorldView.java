package worldview;

import iceworld.given.ICEWorldImmigration;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class WorldView extends JPanel {
	

	
		Font talkFont = this.getFont();
		Font yellFont = talkFont.deriveFont((float) 120.0);
		private final int size = 100;
		private int xOrigin = 300;
		private int yOrigin = 50;
		private int tileSide = 50;
		private Point[][] tileCoord;
		private Point highlightTile;
		private Point destination;
		private NullIcetizen activeIcetizen;
		private LinkedList<NullIcetizen> icetizens;
		private ICEWorldImmigration immigration;
		private Timer timer;
		private int delay = 40;
		private double walkRateX, walkRateY;
		private String currentWeather ="Snowing";
		private Image grassTile;
		private BufferedImage indicator;
		private Image scaleIndicator;

		
		public WorldView(int width, int height, ICEWorldImmigration im, LinkedList<NullIcetizen> ni) throws IOException{
			super();
			//get fetch
			
			icetizens = ni;
			
			tileCoord = new Point[size][size];
			this.setSize(width,height);
			immigration = im;
			
			walkRateX = tileSide /2.0 / 40;
			walkRateY = walkRateX;//for now

			indicator = ImageIO.read(new File("arrow.png"));
			scaleIndicator = indicator.getScaledInstance(tileSide,-1, Image.SCALE_SMOOTH);
			
			highlightTile = new Point(-1,-1);
			destination = new Point(0,0);
			activeIcetizen = new NullIcetizen();	
			MouseHandler mh = new MouseHandler();
			this.addMouseMotionListener(mh);
			this.addMouseListener(mh);
			
			try{
				BufferedImage grass = ImageIO.read(new File("Grasstile.png"));
				grassTile = grass.getScaledInstance(-1, tileSide/2, Image.SCALE_SMOOTH);

			}catch (Exception e){
				System.out.println("load tile failed");
			}
			
			//setup timer (control frame per second)
			timer = new Timer(delay, new ActionListener(){
				public void actionPerformed(ActionEvent e){
					repaint();
				}
			});
			timer.start();
		}

		
		public void paintComponent(Graphics g){

			//bottom plane---------------------
			drawBackgroundWeather(g);
			
			//middle plane---------------------
			//draw tile
			g.setColor(Color.BLACK);
			IsometricPlane.drawBoardTile(g, xOrigin, yOrigin, size, tileSide, tileCoord );
			//IsometricPlane.drawBoardTileImage(g, xOrigin, yOrigin, size, tileSide, grassTile);
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
			drawActiveIcetizen(g);
			drawIcetizen(g);
			
			//top plane ------------------------
			//draw weather
			
			drawForegroundWeather(g);		
		}
		
		public void drawForegroundWeather(Graphics g){
			switch (currentWeather){
				case "Sunny":
					Weather.sunny(g, this.getWidth(), this.getHeight());
					break;
				case "Cloudy":
					System.out.println("It's cloudy");
					Weather.cloudy(g, this.getWidth(), this.getHeight());
					break;
				case "Raining":
					Weather.raining(g, this.getWidth(), this.getHeight());
					break;
				case "Snowing":
					Weather.snowing(g, this.getWidth(), this.getHeight());
					break;
				default:
					System.out.println("it's default");
					Weather.raining(g, this.getWidth(), this.getHeight());
				
			} 
		}
		public void drawBackgroundWeather(Graphics g){
			switch (currentWeather){
				case "Raining":
				case "Snowing":
					Weather.rainingBackground(g, this.getWidth(), this.getHeight());
				break;
				case "Cloudy":
				case "Sunny":
				default:
					Weather.sunnyBackground(g, this.getWidth(), this.getHeight());
				
			} 
		}
//--------------------------------------------------------------------------------------------
//		navigation methods
//--------------------------------------------------------------------------------------------
		public void zoomIn(){
			tileSide+=5;
			activeIcetizen.rescale();
			updateIndicator();
			for(NullIcetizen n: icetizens){
				n.rescale();
			}
			repaint();
			updateWalkRate();
		}
		
		public void zoomOut(){
			tileSide-=5;
			activeIcetizen.rescale();
			updateIndicator();

			for(NullIcetizen n: icetizens){
				n.rescale();
			}
			repaint();
			updateWalkRate();
			
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
				Point pos = n.getPos();
				Point posCoord = tileCoord[pos.x][pos.y];
				if(scale == null){
					BufferedImage img = n.getLookImage();
					n.setScale(scaleToTile(img));
					scale = n.getScale();
				}
				if(scale!=null){
					walk(g, n);
				}else{
					System.out.println("Drawing active Icetizen failed");
				}
				if(n.getTalkSecondLeft() >0){
					System.out.println("talking");
					talk(g, n, n.getTalkMsg());
				}
				if(n.getYellSecondLeft() >0){
					System.out.println("yelling");
					yell(g, n, n.getYellMsg());
				}
				//draw ip
				g.setFont(talkFont);
				g.drawString(n.getIP(), posCoord.x , posCoord.y + tileSide/3);
			}
			
		
		}
		public void drawActiveIcetizen(Graphics g){
			//get image
			Image scale = activeIcetizen.getScale();
			if(scale == null){
				BufferedImage img = activeIcetizen.getLookImage();
				activeIcetizen.setScale(scaleToTile(img));
				scale = activeIcetizen.getScale();
			}
			if(scale!=null){
				walk(g, activeIcetizen);
			}else{
				System.out.println("Drawing active Icetizen failed");
			}
			//draw indicator
			NullIcetizen n = activeIcetizen;
			int xPos, yPos;
			if(n.getPixelPos()!=null){
				yPos = ((int)Math.floor(n.getPixelPos().y)) - scale.getHeight(null) + tileSide/4; //calculate offset for drawing
				xPos = ((int)Math.floor(n.getPixelPos().x)) - scale.getWidth(null)/5; //calculate offset for drawing
			} else{
				int xCoord = n.getPos().x;
				int yCoord = n.getPos().y;
				yPos = tileCoord[xCoord][yCoord].y - scale.getHeight(null) + tileSide/4;
				xPos = tileCoord[xCoord][yCoord].x - scale.getWidth(null)/5;
			}
			
			g.drawImage(scaleIndicator, xPos,yPos - scale.getHeight(null),null);
			

			
			if(activeIcetizen.getTalkSecondLeft() >0){
				talk(g, activeIcetizen, activeIcetizen.getTalkMsg());
			}
			
			if(activeIcetizen.getYellSecondLeft() >0){
				yell(g, activeIcetizen, activeIcetizen.getYellMsg());
			}


		}
		
		public Image scaleToTile(BufferedImage img){
			Image scale = img.getScaledInstance(tileSide, -1, Image.SCALE_SMOOTH);
			return scale;
		}
		
		public void walk(Graphics g, NullIcetizen n){
			//walking 
			Point nDest = n.getDestination();
			Image scale = n.getScale();
			
			// if not at destination, walk
			if(!n.getPos().equals(nDest)){
				//System.out.println("trying to walk to dest");
				//Walking
				
				//pixelpos,intermediate pos, just for walking, if not walking will equal null
				if(n.getPixelPos() == null){
					//instantiate pixelPos for walking in between tiles
					Point2D.Double currentPixelPos = new Point2D.Double();
					currentPixelPos.x= tileCoord[n.getPos().x][n.getPos().y].x;
					currentPixelPos.y= tileCoord[n.getPos().x][n.getPos().y].y;
					n.setPixelPos(currentPixelPos);
					System.out.println("PixelPos set to :"+currentPixelPos);
				}
				//pixelPos already instantiate for walking - do the walking
				//pixelPos not there yet...
				Point DestPixel = tileCoord[nDest.x][nDest.y];
				double xMove = DestPixel.x - n.getPixelPos().x;
				double yMove = DestPixel.y - n.getPixelPos().y;
				System.out.println(xMove+","+yMove);
				
				if(Math.abs(xMove)< walkRateX){
					n.getPixelPos().x+=xMove;
				}else{
					if(xMove>0) n.getPixelPos().x+=walkRateX;
					else if (xMove<0) n.getPixelPos().x-=walkRateX;
					else {}//do nothing
				}
				
				if(Math.abs(yMove)< walkRateY){
					activeIcetizen.getPixelPos().y+=yMove;
				} else{
					if(yMove>0) n.getPixelPos().y+=walkRateY;
					else if (yMove<0) n.getPixelPos().y-=walkRateY;
					else {}//do nothing
				}
				
				int yPos = ((int)Math.floor(n.getPixelPos().y)) - scale.getHeight(null) + tileSide/4; //calculate offset for drawing
				int xPos = ((int)Math.floor(n.getPixelPos().x)) - scale.getWidth(null)/5; //calculate offset for drawing
				g.drawImage(scale, xPos, yPos ,null);
				
				
				//check if destination reached
				if(n.getPixelPos().equals(tileCoord[nDest.x][nDest.y])){
					n.getPos().x = nDest.x;
					n.getPos().y = nDest.y;
					n.setPixelPos(null);
				}	
			}else {
				//System.out.println("already at dest");
				//already at destination
				int xCoord = n.getPos().x;
				int yCoord = n.getPos().y;
				int yPos = tileCoord[xCoord][yCoord].y - scale.getHeight(null) + tileSide/4;
				int xPos = tileCoord[xCoord][yCoord].x - scale.getWidth(null)/5;
				g.drawImage(scale, xPos, yPos ,null);
				
			}
		}
//--------------------------------------------------------------------------------------------
//		chat methods
//--------------------------------------------------------------------------------------------
		public void talk(Graphics g, NullIcetizen n, String msg){
			//get coordinate of the head
			this.setFont(talkFont);
			Point p;
			if(n.getPixelPos()!= null){//while walking
				p = new Point((int)n.getPixelPos().x, (int)n.getPixelPos().y);

			}else{//while stationary
				p = tileCoord[n.getPos().x][n.getPos().y];
			}
			int x = p.x;
			int y = p.y - n.getScale().getHeight(null);
			
			AffineTransform affinetransform = new AffineTransform();     
			FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
			Font font = this.getFont();			

			
			int textwidth = (int)(font.getStringBounds(msg, frc).getWidth());
			int textheight = (int)(font.getStringBounds(msg, frc).getHeight());
			g.setColor(Color.WHITE);
			g.fillRoundRect(x, y-textheight, textwidth+10, textheight+5, 5, 5);
			g.setColor(Color.BLACK);
			g.drawString(msg, x+5, y);	
		}


		
		
//--------------------------------------------------------------------------------------------
//		yell methods not checked for bug yet
//--------------------------------------------------------------------------------------------		
		public void yell(Graphics g, NullIcetizen n, String msg){
			//get coordinate of the head
			g.setFont(yellFont);
			Point p;
			if(n.getPixelPos()!= null){//while walking
				p = new Point((int)n.getPixelPos().x, (int)n.getPixelPos().y);

			}else{//while stationary
				p = tileCoord[n.getPos().x][n.getPos().y];
			}
			
			int x = p.x;
			int y = p.y - n.getScale().getHeight(null);
			
			AffineTransform affinetransform = new AffineTransform();     
			FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
			Font font = yellFont;
			
			int textwidth = (int)(font.getStringBounds(msg, frc).getWidth());
			int textheight = (int)(font.getStringBounds(msg, frc).getHeight());
			
			
			x = x-textwidth/2;			
			g.setColor(Color.YELLOW);
			g.fillRoundRect(x, y-textheight, textwidth+20, textheight+5, 50, 50);
			g.setColor(Color.RED);
			g.drawString(msg, x+5, y-textheight/4);	
		}
		
//--------------------------------------------------------------------------------------------
//		update methods
//--------------------------------------------------------------------------------------------

		public void updateWeather(String s){
			currentWeather = s;
		}
		
		public void updateIcetizens(LinkedList<NullIcetizen> n){
			icetizens = n;
		}
		
		public void updateWalkRate(){
			walkRateX = tileSide /2.0 / 40;
			walkRateY = walkRateX;//for now
		}
		
		public void updateIndicator(){
			scaleIndicator = indicator.getScaledInstance(tileSide,-1, Image.SCALE_SMOOTH);
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
				Point2D.Double pix = activeIcetizen.getPixelPos();
				if(pix!=null){ // is walking
					pix.x += xMoved;
					pix.y += yMoved;
				}
			}
			
			public void mousePressed(MouseEvent e){
				//To ensure that lastPress is initialized properly for the mouseDragged
				lastPress.x = e.getX();
				lastPress.y = e.getY();
			}
			
			public void mouseClicked(MouseEvent e){
				//icetizen.move(highlightTile.x, highlightTile.y);
				Point dest = new Point(highlightTile.x, highlightTile.y);
				activeIcetizen.setDestination(dest);
				if (immigration.walk(dest.x, dest.y)){
					System.out.println("Walk OK");
				}
				
				//activeIcetizen.setYellMsg("I'm Yelling");
				//activeIcetizen.setTalkMsg("I am walking");
				

				System.out.println("move to:"+ highlightTile.x +","+highlightTile.y);
				//repaint();
			}
		}
		
		
	}