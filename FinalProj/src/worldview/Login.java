import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.applet.Applet;


public class Login extends JPanel{
	JPanel loginPanel;
	JLabel id, pw;
	JButton loginAlien, loginIce;
	JTextArea idArea, pwArea;
	
	public Login(){
		//Label
		id=new JLabel("Username");
		pw=new JLabel("Password");
		
		//Login button
		loginAlien=new JButton("Login as Alien");
		loginIce=new JButton("Login as Ice-tizen");
		
		//Text Area
		idArea=new JTextArea();
		pwArea=new JTextArea();
		
		//Add Component
		loginPanel.setLayout(new GridLayout(6,1,2,2));
		loginPanel.add(id);
		loginPanel.add(idArea);
		loginPanel.add(pw);
		loginPanel.add(pwArea);
		loginPanel.add(loginAlien);
		loginPanel.add(loginIce);	
		
		//Listener
		username x=new username();
		password y=new password();
		alien w=new alien();
		ice z=new ice();
	}
	
	public class username extends Applet implements ActionListener {
		public void actionPerformed(ActionEvent e){
			
		}
	}
	
	public class password extends Applet implements ActionListener {
		public void actionPerformed(ActionEvent e){
			
		}
	}
	
	public class alien extends Applet implements ActionListener {
		public void actionPerformed(ActionEvent e){
			
		}
	}
	
	public class ice extends Applet implements ActionListener {
		public void actionPerformed(ActionEvent e){
			
		}
	}
	
}
