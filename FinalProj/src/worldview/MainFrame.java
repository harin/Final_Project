package worldview;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MainFrame extends JFrame {
	private JMenuBar menuBar;
	private JMenu menuMenu;
	private JMenuItem about,help,setting,quit;
	public JPanel worldView;
	private LoginPage loginPage;
	
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
		
		loginPage = new LoginPage();
		add(loginPage);
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
	
//----------------------------------------------------------------------------------------
	//login page class
	
	public class LoginPage extends JPanel {
		private JTextField passwordField;
		private String [] userHistory;
		private String username;
		private String password;
		private JComboBox usernameBox;	

		/**
		 * Create the panel.
		 */
		public LoginPage() {
			setLayout(null);
			
			passwordField = new JTextField();
			passwordField.setBounds(206, 123, 134, 28);
			add(passwordField);
			passwordField.setColumns(10);
			
			JLabel lblUsername = new JLabel("Username");
			lblUsername.setBounds(122, 88, 72, 16);
			add(lblUsername);
			
			JLabel lblPassword = new JLabel("Password");
			lblPassword.setBounds(122, 129, 61, 16);
			add(lblPassword);
			
			usernameBox = new JComboBox();
			usernameBox.setBounds(206, 84, 134, 27);
			usernameBox.setEditable(true);
			usernameBox.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
						JComboBox cb = (JComboBox)e.getSource();
						String username = (String) cb.getSelectedItem();
						usernameBox.addItem(username);
						System.out.println("username:"+username);
				}
			});
			add(usernameBox);
			
			
			JButton LoginBut = new JButton("Login");
			LoginBut.setBounds(216, 166, 117, 29);
			add(LoginBut);
			
			JButton LoginAlienBut = new JButton("Alien Login");
			LoginAlienBut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			LoginAlienBut.setBounds(98, 166, 117, 29);
			add(LoginAlienBut);
		}
	}

}
	
