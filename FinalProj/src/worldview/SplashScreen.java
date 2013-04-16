package worldview;



import javax.swing.JWindow;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


public class SplashScreen extends JWindow
{
//Get transparent image that will be use as splash screen image.
Image bi=Toolkit.getDefaultToolkit().getImage("SplashScreen.png");

ImageIcon ii=new ImageIcon(bi);

public SplashScreen()
{
try
{
setSize(ii.getIconWidth(),ii.getIconHeight());
setLocationRelativeTo(null);
show();
Thread.sleep(2500);
dispose();
}
catch(Exception exception)
{
exception.printStackTrace();
}
}

//Paint transparent image onto JWindow
public void paint(Graphics g)
{
g.drawImage(bi,0,0,this);
}

}
