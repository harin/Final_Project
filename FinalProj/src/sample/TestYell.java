package sample;
import java.awt.*;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestYell extends JPanel {

	 public void paint(Graphics g) {
		    Dimension d = this.getPreferredSize();
		    int fontSize = 20;
		 //   URL picture = new URL("http://iceworld.sls-atl.com/graphics/body/blue.png");
		    g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
		     
		    g.setColor(Color.red);
		    String test = "www.java2s.com";
		    g.drawString("www.java2s.com", 10, 20);
		  }

		  public static void main(String[] args) {
		    JFrame frame = new JFrame();
		    frame.getContentPane().add(new TestYell());

		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.setSize(200,200);
		    frame.setVisible(true);
		  }
		}

