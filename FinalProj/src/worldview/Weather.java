package worldview;
import java.awt.*;
import java.util.*;

public class Weather {
	private static int rainHeight = 5;
	private static Random random = new Random();
	public static void sunny(Graphics g){
		
	}
	public static void cloudy(Graphics g){
		
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

}
