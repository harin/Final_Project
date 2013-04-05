package worldview;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainFrame extends JFrame {
	JMenuBar menuBar;
	JMenu menuMenu;
	JMenuItem about,help,setting,quit;
	public JPanel worldView;
	
	public MainFrame(){	
		setTitle("The Null");
		setSize(900,800);
		setJMenuBar(makeMenuBar());
		setGUI();
	}
	
	
	public JMenuBar makeMenuBar(){
		menuBar=new JMenuBar();
		menuMenu=new JMenu("Menu");
		menuBar.add(menuMenu);
		
		//about menu
		AboutEvent x=new AboutEvent();
		about=new JMenuItem("About");
		about.addActionListener(x);
		menuMenu.add(about);
		about.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.Event.CTRL_MASK));
		
		//help menu
		HelpEvent y=new HelpEvent();
		help=new JMenuItem("Help");
		help.addActionListener(y);
		menuMenu.add(help);
		help.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.Event.CTRL_MASK));	

		//setting menu
		SettingEvent z=new SettingEvent();
		setting=new JMenuItem("Setting");
		setting.addActionListener(z);
		menuMenu.add(setting);
		setting.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.Event.CTRL_MASK));	
		
		//quit menu
		QuitEvent q=new QuitEvent();
		quit=new JMenuItem("Quit");
		quit.addActionListener(q);
		menuMenu.addSeparator();
		menuMenu.add(quit);
		quit.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.Event.CTRL_MASK));
		
		return menuBar;
	}
		
	public void setGUI(){
		worldView = new WorldView(this.WIDTH, this.HEIGHT);
		worldView.setLocation(0, 0);
		this.add(worldView);
	}
	
	public class AboutEvent implements ActionListener {
		public void actionPerformed (ActionEvent e){

		}
	} 
	
	
	public class HelpEvent implements ActionListener {
		 public void actionPerformed(ActionEvent e) {	
			 
		 } 
	}

	
	public class SettingEvent implements ActionListener {
		 public void actionPerformed(ActionEvent e) {	
			 
		 } 
	}
	
	
	public class QuitEvent implements ActionListener{
		 public void actionPerformed(ActionEvent e){
			 System.exit(0);
		 }
	}
	 
	
	public static void main (String[] args){
		MainFrame gui=new MainFrame();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setVisible(true);
		
	}

}
	
