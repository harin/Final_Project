package worldview;
import java.awt.*;
import java.util.*;

public class Weather {
	private static int rainHeight = 5;
	private static Random random = new Random();
	public static void sunny(Graphics g, int width, int height){
		
	}
	public static void sunnyBackground(Graphics g, int width, int height){
		g.setColor(new Color(135,206,250));
		g.fillRect(0,0,width,height);
	}
	
	public static void cloudy(Graphics g, int width, int height){
		g.setColor(new Color(0,0,0,80));
		g.fillRect(0,0,width,height);
	}
	
	public static void raining(Graphics g, int width, int height){
		g.setColor(Color.BLACK);
		int i=0;
		int x=0;
		int y=0;
		while(i<100){
			i++;
			x= random.nextInt(width);
			y= random.nextInt(height);
			g.drawLine(x, y, x, y+rainHeight);
		}
	}
	public static void snowing(Graphics g, int width, int height){
		g.setColor(Color.WHITE);
		int i=0;
		int x=0;
		int y=0;
		while(i<100){
			i++;
			x= random.nextInt(width);
			y= random.nextInt(height);
			g.drawLine(x, y, x, y);
		}
	}
	public static void rainingBackground(Graphics g, int width, int height){
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, width, height);
	}

}
