package worldview;



import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

//This applet is for testing purpose only
public class IsometricPlaneApplet extends JApplet{
	private WorldView wv;
	public void init(){
		wv = new WorldView();
		this.add(wv);
	}
	
	class WorldView extends JPanel{
		private final int size = 5;
		private final int xOrigin = 200;
		private final int yOrigin = 50;
		private final int tileSide = 100;
		private Point[][] tileCoord;
		private Dimension d;
		private Point highlightTile;
		private Point destination;
		private NullIcetizen icetizen;
		
		public WorldView(){
			super();
			tileCoord = new Point[size][size];
			this.setSize(500,500);
			d = this.getSize();
			highlightTile = new Point(-1,-1);
			destination = new Point(0,0);
			icetizen = new NullIcetizen();	
			MouseHandler mh = new MouseHandler();
			this.addMouseMotionListener(mh);
			this.addMouseListener(mh);
		}
		
		public void paintComponent(Graphics g){
			//draw tile
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, d.width, d.height);
			g.setColor(Color.WHITE);
			drawBoardTile(g, xOrigin, yOrigin, size, tileSide );
			
			//highlight tile
			if(highlightTile.x >= 0 && highlightTile.y >= 0
					&& highlightTile.x < size && highlightTile.y < size){
				//System.out.println("highlighting");
				g.setColor(Color.RED);
				Point tile = tileCoord[highlightTile.x][highlightTile.y];
				fillTile(g,tile.x,  tile.y, tileSide);
			}
			
			//draw icetizen
			drawIcetizen(g,0,0);
			
		}
//--------------------------------------------------------------------------------------------
		
//--------------------------------------------------------------------------------------------

		public void drawIcetizen(Graphics g, int x, int y){
			String loc ="resource/blue.png";
			BufferedImage img = null;
			try{
				img = ImageIO.read(new File(loc));
			} catch (Exception e){
				System.out.println("image load failed");
			}
			if(img!=null) {
				Image scale = scaleToTile(img);
			
				if(!icetizen.getPos().equals(destination)){
					System.out.println("animating");
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
					int yPosFeet = tileCoord[xCoord][yCoord].y + tileSide/4;
					int yPos = tileCoord[xCoord][yCoord].y - scale.getHeight(null) + tileSide/4;
					int xPos = tileCoord[xCoord][yCoord].x - scale.getWidth(null)/2 
							+ tileSide/2 - scale.getWidth(null)/5;
					g.drawImage(scale, xPos, yPos ,null);
			}
		}
		
		public Image scaleToTile(BufferedImage img){
			Image scale = img.getScaledInstance(tileSide, -1, Image.SCALE_SMOOTH);
			return scale;
		}
//--------------------------------------------------------------------------------------------
		
//--------------------------------------------------------------------------------------------		
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
		
		class MouseHandler extends MouseAdapter implements MouseMotionListener{
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
			
			public void mouseClicked(MouseEvent e){
				//icetizen.move(highlightTile.x, highlightTile.y);
				destination.x = highlightTile.x;
				destination.y = highlightTile.y;
				System.out.println("move to:"+ highlightTile.x +","+highlightTile.y);
				repaint();
			}
	
		}
		
		
	}
	
	
}
