package worldview;


import help__dialog.NonModal;
import setting.BGSound;
import setting.EffectSound;
import setting.Setting;
import worldview.FetchThread.decrementEvent;
import worldview.FetchThread.incrementEvent;
import iceworld.given.ICEWorldImmigration;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

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
	LinkedList<NullIcetizen> icetizens;
	private ICEWorldImmigration immigration;
	private JPanel worldViewPanel;
	private final int WIDTH = 900;
	private final int HEIGHT = 800;
	private FetchThread fetcher;
	SplashScreen sp;	
	TextChatBox chat;
	
	public static BGSound music=new BGSound();
	public static EffectSound effect=new EffectSound();
	private int kodhod =0;
	
	
	public MainFrame(){
		try{	
			sp=new SplashScreen();
			activeIcetizen = new NullIcetizen();
			immigration = new ICEWorldImmigration(activeIcetizen);
			
			setTitle("The Null");
			setSize(WIDTH,HEIGHT);
			setLocationRelativeTo(null);
			setJMenuBar(makeMenuBar());
			setGUI();
		}catch(Exception e){
			e.printStackTrace();
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
	
	
	public void increaseKodhod(){
		kodhod++;
	}
	
	
	public void switchToWorldView() throws IOException{
		
		Dimension d = this.getSize();
		
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
				effect.play();
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
		
		FetchInformation fetcher = new FetchInformation();
		fetcher.setFetchState();
		icetizens = fetcher.getCitizen();
		
		//minimap
		MiniMap map = new MiniMap(icetizens, activeIcetizen);
		map.setLocation(d.width - map.getWidth()- 50 , 50);
		lp.add(map, new Integer(100));
		
		//worldview
		worldView = new WorldView(WIDTH,HEIGHT, immigration, icetizens, activeIcetizen);
		
		SuperSupremeFetchThread ssft = new SuperSupremeFetchThread(icetizens,this);
		ssft.start();
		
		chat = new TextChatBox(activeIcetizen,immigration);
		chat.createAndShowGUI();
		
		worldViewPanel.add(worldView);	
		music.start();////////////start BGsound////////
		
		this.setContentPane(worldViewPanel);
		revalidate();
	}
	public void setupData() throws IOException{
//		FetchInformation fetcher = new FetchInformation();
//		icetizens = fetcher.getCitizen();
	}
	
	public void setIcetizens( LinkedList<NullIcetizen> icetizens){
		this.icetizens= icetizens;
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
			effect.play();
			System.out.println("about called");
			showAuthorDialog();
			
		}
	} 
	public class HelpEvent implements ActionListener {
		 public void actionPerformed(ActionEvent e) {	
			 effect.play();
			 System.out.println("help called");
			 NonModal.main(null);
		} 
	}
	
	public class SettingEvent implements ActionListener {
		 public void actionPerformed(ActionEvent e) {
			 effect.play();
			System.out.println("setting called");
			Setting.main(null);
		 } 
	}
	public class LogOutEvent implements ActionListener{
		 public void actionPerformed(ActionEvent e){
			 effect.play();
			 Object[] options = { "OK","CANCEL"};
			 int respond = JOptionPane.showOptionDialog(null, "Are you sure you want to log out?", "T_T",JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
			 if(respond==0){
				 if(immigration.logout()){
					 System.out.println("Log out OK");
						JOptionPane.showMessageDialog(new JPanel(), "Log out complete!");
						//System.exit(0);
						dispose();
						fetcher.stop();
						chat.dispose();
						main(null);
						
				 }else{
					 System.out.println("Log out failed");
				 }
			}
		 }
	}
	public class QuitEvent implements ActionListener{
		 public void actionPerformed(ActionEvent e){
			 effect.play();
//			 JOptionPane.showMessageDialog(new JPanel(), "QuitProgram");
//			 immigration.logout();
//			 System.exit(0);
			 Object[] options = { "OK","CANCEL"};
			 int respond = JOptionPane.showOptionDialog(null, "Are you sure you want to quit?", "Bye bye",JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
			if(respond==0){
				immigration.logout(); 
				System.exit(0); 
			}
			
		 
		 }
		 
	}
	
	protected void processQuitEvent(WindowEvent e) {

        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            int exit = JOptionPane.showConfirmDialog(this, "Confirm Exit?");
            if (exit == JOptionPane.YES_OPTION) {
       		 immigration.logout();
                System.exit(0);
            }
        } else {
            super.processWindowEvent(e);
        }
    }
	
	
	
	protected void processWindowEvent(WindowEvent e) {

        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            int exit = JOptionPane.showConfirmDialog(this, "Confirm Exit?");
            if (exit == JOptionPane.YES_OPTION) {
       		 immigration.logout();
                System.exit(0);
            }
        } else {
            super.processWindowEvent(e);
        }
    }
	
	//update icetizens after fetch
	public void updateIcetizens(LinkedList<NullIcetizen> n){
		System.out.println("Updating icetizens in worldview");

		worldView.updateIcetizens(n);
	}
	public void updateWeather(String s){
		worldView.updateWeather(s);
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
		private LinkedList<String> userHistory;
		private String username;
		private String password;
		private JComboBox<String> usernameBox;
		private String history = "usernameHistory.txt";

		/**
		 * Create the panel.
		 */
		public LoginPage() {
			setLayout(null); 
			userHistory = new LinkedList<String>();

			//load userHistory
			try{
				Scanner in = new Scanner(new File(history));
				String temp;
				while(in.hasNext()){
					temp = in.nextLine();
					if(!userHistory.contains(temp))
						userHistory.add(temp);
				}
				System.out.print("userHistory="+userHistory);
			}catch (Exception e){
				e.printStackTrace();
			}
			
			
			
			JLabel lblUsername = new JLabel("Username");
			lblUsername.setBounds(122, 88, 72, 16);
			add(lblUsername);
			
			JLabel lblPassword = new JLabel("Password");
			lblPassword.setBounds(122, 129, 61, 16);
			add(lblPassword);
			
			passwordField = new JPasswordField();
			passwordField.setBounds(206, 123, 134, 28);

			add(passwordField);
			passwordField.setColumns(10);

			
			usernameBox = new JComboBox<String>();
			for(String s: userHistory){
				usernameBox.addItem(s);
			}
			usernameBox.setBounds(206, 84, 134, 27);
			usernameBox.setEditable(true);
			usernameBox.setSelectedItem("type username here");
			usernameBox.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					effect.play();
						JComboBox<String> cb = (JComboBox<String>)e.getSource();
						username = (String) cb.getSelectedItem();
						activeIcetizen.setUsername(username);
						System.out.println("username:"+activeIcetizen.getUsername());
						
						if(!userHistory.contains(username)){
							System.out.println("New username:"+username);
							usernameBox.addItem(username);
							userHistory.add(username);
						}
						System.out.println(userHistory);
				}
			});
			add(usernameBox);
			
			
			JButton loginBut = new JButton("Login");
			loginBut.setBounds(216, 166, 117, 29); 
			loginBut.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					effect.play();
					password = new String(passwordField.getPassword());
					
					if(immigration.login(password)){
						System.out.println("Login OK");
						try {
							BufferedWriter out = new BufferedWriter(new FileWriter(history));
							System.out.print("writing to file:");
							for(String s: userHistory){
								System.out.print(s);
								out.write(s+"\n");
							}
							System.out.println("");
							out.close();
							System.out.println("Switching to worldView");
							switchToWorldView();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}else{
						System.out.println("Login failed");
					}
				}
			});
			add(loginBut);
			
			JButton loginAlienBut = new JButton("Alien Login");
			loginAlienBut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					effect.play();
					if (immigration.loginAlien()){
						System.out.println("Login Alien OK");
						try {
							BufferedWriter out = new BufferedWriter(new FileWriter(history));
							System.out.print("writing to file:");
							for(String s: userHistory){
								System.out.print(s);
								out.write(s+"\n");
							}
							System.out.println("");
							out.close();
							switchToWorldView();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
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

