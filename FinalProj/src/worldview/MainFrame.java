package worldview;


import help__dialog.NonModal;
import iceworld.given.ICEWorldImmigration;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;


public class MainFrame extends JFrame {
	private JMenuBar menuBar;
	private JMenu menuMenu;
	private JMenuItem about,help,setting,quit;
	public JPanel worldView;
	private LoginPage loginPage;
	private NullIcetizen activeIcetizen;
	private ICEWorldImmigration immigration;
	
	public MainFrame(){	
		activeIcetizen = new NullIcetizen();
		immigration = new ICEWorldImmigration(activeIcetizen);
		
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
		about=new JMenuItem("About");
		about.addActionListener(new AboutEvent());
		menuMenu.add(about);
		about.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.Event.CTRL_MASK));
		
		//help menu
		help=new JMenuItem("Help");
		help.addActionListener(new HelpEvent());
		menuMenu.add(help);
		help.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1,0));	

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
		loginPage = new LoginPage();
		add(loginPage);
	}
	
	public void switchToWorldView(){
		worldView = new WorldView(this.WIDTH, this.HEIGHT, immigration);
		worldView.setLocation(0, 0);
		this.setContentPane(worldView);
		revalidate();
	}
	
	public class AboutEvent implements ActionListener {
		public void actionPerformed (ActionEvent e){
			System.out.println("about called");
			showAuthorDialog();
		}
	} 
	public class HelpEvent implements ActionListener {
		 public void actionPerformed(ActionEvent e) {	
			 NonModal.main(null);
		} 
	}
	
	public class SettingEvent implements ActionListener {
		 public void actionPerformed(ActionEvent e) {	
			System.out.println("setting called");
//			settingDialog();
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
		private JPasswordField passwordField;
		private String [] userHistory;
		private String username;
		private String password;
		private JComboBox<String> usernameBox;	

		/**
		 * Create the panel.
		 */
		public LoginPage() {
			setLayout(null);
			
			JLabel lblUsername = new JLabel("Username");
			lblUsername.setBounds(122, 88, 72, 16);
			add(lblUsername);
			
			JLabel lblPassword = new JLabel("Password");
			lblPassword.setBounds(122, 129, 61, 16);
			add(lblPassword);
			
			passwordField = new JPasswordField();
			passwordField.setBounds(206, 123, 134, 28);
//			passwordField.addActionListener(new ActionListener(){
//
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					// TODO Auto-generated method stub
//					JTextField f = (JTextField) e.getSource();
//					password = f.getText();
//				}
//				
//			});
			add(passwordField);
			passwordField.setColumns(10);
			
			
			
			usernameBox = new JComboBox<String>();
			usernameBox.setBounds(206, 84, 134, 27);
			usernameBox.setEditable(true);
			usernameBox.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
						JComboBox<String> cb = (JComboBox<String>)e.getSource();
						username = (String) cb.getSelectedItem();
						activeIcetizen.setUsername(username) ;
						usernameBox.addItem(username);
						System.out.println("username:"+activeIcetizen.getUsername());
				}
			});
			add(usernameBox);
			
			
			JButton loginBut = new JButton("Login");
			loginBut.setBounds(216, 166, 117, 29);
			loginBut.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					password = new String(passwordField.getPassword());
					
					if(immigration.login(password)){
						System.out.println("Login OK");
						switchToWorldView();
						
					}else{
						System.out.println("Login failed");
					}
				}
			});
			add(loginBut);
			
			JButton loginAlienBut = new JButton("Alien Login");
			loginAlienBut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (immigration.loginAlien()){
						System.out.println("Login Alien OK");
					}
				}
			});
			loginAlienBut.setBounds(98, 166, 117, 29);
			add(loginAlienBut);
		}
	}

	//
	// Author dialog
	//
	public void showAuthorDialog(){
		Image img = null;
		final JDialog authorDialog = new JDialog(this,"Team members");
		authorDialog.setLayout(new BorderLayout());
		URL imgURL = getClass().getResource("about.jpg");
		try {
			img = ImageIO.read(imgURL);
		} catch (IOException e) {
			e.printStackTrace();
		}

		JLabel imgLabel = new JLabel(new ImageIcon(img));

		authorDialog.getContentPane().add(imgLabel, BorderLayout.CENTER);
		authorDialog.setModal(true);
		authorDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		authorDialog.setSize(800,600);
		authorDialog.setLocationRelativeTo(null);
		authorDialog.pack();
		authorDialog.setVisible(true);		
	}
	//
	//
	//
}

