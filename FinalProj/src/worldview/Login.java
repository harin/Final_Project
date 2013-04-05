package worldview;

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
	JTextField idArea, pwArea;
	String username;
	String password;
	
	public Login(){
		//Label
		id=new JLabel("Username");
		pw=new JLabel("Password");
		
		//Login button
		loginAlien=new JButton("Login as Alien");
		loginIce=new JButton("Login as Ice-tizen");
		
		//Text Area
		idArea=new JTextField();
		pwArea=new JTextField();
		
		//Add Component
		setLayout(new GridLayout(6,1,2,2));
		add(id);
		add(idArea);
		add(pw);
		add(pwArea);
		add(loginAlien);
		add(loginIce);	
	}
	

	
}
