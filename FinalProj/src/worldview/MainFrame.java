package worldview;


import help__dialog.NonModal;
import setting.Setting;
import iceworld.given.ICEWorldImmigration;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class MainFrame extends JFrame {
	private JMenuBar menuBar;
	private JMenu menuMenu;
	private JMenuItem about,help,setting,logout,quit;
	public WorldView worldView;
	private LoginPage loginPage;
	private NullIcetizen activeIcetizen;
	private ICEWorldImmigration immigration;
	private JPanel worldViewPanel;
	private final int WIDTH = 900;
	private final int HEIGHT = 800;
	
//	JMenuItem sound;//
//	JSlider BGSound;//
	//Audioapp song;//
//	Sound song;
	
	public MainFrame(){
		try{
//		String songName = "BGSong.wav";
//		//song=new Audioapp(songName);//
//		song = new Sound(songName);
//		song.playSound();//
		activeIcetizen = new NullIcetizen();
		immigration = new ICEWorldImmigration(activeIcetizen);
		
		setTitle("The Null");
		setSize(WIDTH,HEIGHT);
		setLocationRelativeTo(null);
		setJMenuBar(makeMenuBar());
		setGUI();
		}catch(Exception e){
			System.out.println("Sound in mainframe :   "+e);
		}
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
		setting=new JMenuItem("Setting");
		setting.addActionListener(new SettingEvent());
		menuMenu.add(setting);
		setting.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.Event.CTRL_MASK));	
		
		//sound						
//		SoundEvent s= new SoundEvent();
//		sound=new JMenuItem("Sound");
//		sound.addActionListener(s);
//		menuMenu.add(sound);		
		
		//logout menu
		logout=new JMenuItem("Log Out");
		logout.addActionListener(new LogOutEvent());
		menuMenu.add(logout);
				
		
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
		
		JLayeredPane lp = this.getLayeredPane();
		
		worldViewPanel = new JPanel();
		worldViewPanel.setSize(this.WIDTH,this.HEIGHT);
		worldViewPanel.setLayout(null);
		worldViewPanel.setBackground(Color.BLACK);

		//zoom in
		JButton zoomIn = new JButton("+");
		int zoomWidth = 50;
		int zoomHeight = 50;
		zoomIn.setBounds(this.WIDTH - zoomWidth -10	,this.HEIGHT - zoomHeight*3,zoomWidth,zoomHeight);
		zoomIn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				worldView.zoomIn();
			}
		});
		lp.add(zoomIn,new Integer(100));
		
		//zoom out
		JButton zoomOut = new JButton("-");
		zoomOut.setBounds(this.WIDTH - zoomWidth -10	,this.HEIGHT - zoomHeight*2,zoomWidth,zoomHeight);
		zoomOut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				worldView.zoomOut();
			}
		});
		lp.add(zoomOut,new Integer(100));
		
		
		worldView = new WorldView(WIDTH,HEIGHT, immigration);

		TextChatBox.createAndShowGUI();
		
		worldViewPanel.add(worldView);
		
		this.setContentPane(worldViewPanel);
		revalidate();
	}
	
	public void activeIcetizenTalk(String s){
		this.activeIcetizen.setTalkMsg(s);
	}
	
	//--------------------------------------------------------------------------------
	// Author dialog
	//--------------------------------------------------------------------------------
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

	
	
//----------------------------------------------------------------------
//Action Listeners
//------------------------------------------------------------------------
	public class AboutEvent implements ActionListener {
		public void actionPerformed (ActionEvent e){
			System.out.println("about called");
			showAuthorDialog();
		}
	} 
	public class HelpEvent implements ActionListener {
		 public void actionPerformed(ActionEvent e) {	
			 System.out.println("help called");
			 NonModal.main(null);
		} 
	}
	
	public class SettingEvent implements ActionListener {
		 public void actionPerformed(ActionEvent e) {	
			System.out.println("setting called");
			Setting.main(null);
		 } 
	}
	public class LogOutEvent implements ActionListener{
		 public void actionPerformed(ActionEvent e){
			 
		 }
	}
	public class QuitEvent implements ActionListener{
		 public void actionPerformed(ActionEvent e){
			 System.exit(0);
		 }
	}
	 
//	public class SoundEvent implements ActionListener{//
//		public void actionPerformed(ActionEvent e){
//			JDialog dialog=new JDialog();
//			BGSound=new JSlider(SwingConstants.HORIZONTAL, 0, 100, 50);
//			BGSound.setMajorTickSpacing(10);
//			BGSound.setPaintTicks(true);
//			dialog.add(BGSound);
//			
//			SoundControlEvent slide=new SoundControlEvent();
//			BGSound.addChangeListener(slide);
//			
//			dialog.setLocationRelativeTo(null);
//			dialog.pack();
//			dialog.setVisible(true);	
//		}
//	}

//	public class SoundControlEvent implements ChangeListener{//
//		public void stateChanged(ChangeEvent e){
//			int value=BGSound.getValue();
//			if(value<50){
//				song.increase();
//			}else{
//				song.decrease();
//			}
//		}
//	}
	
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
						switchToWorldView();
					} else{
						System.out.println("Login failed");
					}
				}
			});
			loginAlienBut.setBounds(98, 166, 117, 29);
			add(loginAlienBut);
		}
	}
}

