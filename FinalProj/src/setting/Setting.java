package setting;

import iceworld.given.IcetizenLook;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

import worldview.NullIcetizen;
import worldview.MainFrame.LogOutEvent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


@SuppressWarnings("serial")
public class Setting extends JPanel implements ActionListener{
	LoadVisualResources avatar;
	
	private static NullIcetizen active;
	IcetizenLook look;
	
	JTabbedPane tabbedPane = new JTabbedPane();
    JFrame frame;

    public Setting(JFrame frame,NullIcetizen a) throws IOException{
    	
    	super(new BorderLayout());
    	this.frame = frame;
    	active = a;
     	JPanel setting = settingPanel();
        JPanel custom = customPanel();

    	
        tabbedPane.addTab("Setting", null,setting);
        tabbedPane.addTab("Customization", null,custom); 

 
        add(tabbedPane, BorderLayout.CENTER);
    }
    
    private JPanel settingPanel() {
    	JPanel panel = new JPanel();
    	JLabel label = new JLabel("BGM/soundFX");
    	
    	
    	
    	
    	
		panel.add(label);
    	
    	return panel;
    }
    
    private JPanel customPanel() throws IOException {
    	LoadVisualResources.init();
    	avatar= new LoadVisualResources();
    	
    	JPanel panel = new JPanel(new GridLayout(1,2,2,2));
    	JPanel LeftPanel = new JPanel(new GridLayout(9,1,2,2));
    	JPanel RightPanel = new JPanel(new GridLayout(1,1,4,4));
    	LeftPanel.setBorder(BorderFactory.createTitledBorder("Avatar"));

    	JLabel bodyLabel = new JLabel("Body");
    	JLabel headLabel = new JLabel("Head");
    	JLabel shirtLabel = new JLabel("Shirt");
    	JLabel weaponLabel = new JLabel("Weapon");
    	 
//    	String[] bodyStrings = {"B001","B002","B003","B004","B005","B006"};
//    	String[] headStrings = {"H001","H002","H003","H004","H005","H006"};
//    	String[] shirtStrings = {"S001","S002","S003","S004","S005","S006"};
//    	String[] weaponStrings = {"W001","W002","W003","W004","W005","W006"};
    	
    	
    	JComboBox bodyBox = new JComboBox(avatar.bodyS);
    	JComboBox headBox = new JComboBox(avatar.headS);
    	JComboBox shirtBox = new JComboBox(avatar.shirtS);
    	JComboBox weaponBox = new JComboBox(avatar.weaponS);
    	JButton select = new JButton("Select");
    	select.setActionCommand("select");
    	
    	bodyBox.addActionListener(this);
    	headBox.addActionListener(this);
    	shirtBox.addActionListener(this);
    	weaponBox.addActionListener(this);
      	select.addActionListener(new SelectEvent());
    	
    	
    	LeftPanel.add(bodyLabel);
    	LeftPanel.add(bodyBox);
    	LeftPanel.add(headLabel);
    	LeftPanel.add(headBox);
    	LeftPanel.add(shirtLabel);
    	LeftPanel.add(shirtBox);
    	LeftPanel.add(weaponLabel);
    	LeftPanel.add(weaponBox);
    	LeftPanel.add(select);
    	    	
    	RightPanel.add(avatar);
    	
    	panel.add(LeftPanel);
		panel.add(RightPanel);
		add(panel);
    	
    	return panel;
    }
      
    
  //  
  // 
  //  
  //Application start point   
    public static void main(String[] args) {
    	//Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
     javax.swing.SwingUtilities.invokeLater(new Runnable(){
         public void run(){
             try {
				createAndShowGUI();
			} catch (IOException e) {
				e.printStackTrace();
			}          
         }
     });
    }
    private static void createAndShowGUI() throws IOException {
        //Create and set up the window.
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JDialog dialog = new JDialog(frame, "Setting & Custom");
        //Create and set up the content pane.
        Setting newContentPane = new Setting(frame,active);
        newContentPane.setOpaque(true); //content panes must be opaque
        dialog.setContentPane(newContentPane);
        
        //Display the window.
        dialog.setModal(true);
        dialog.setSize(600, 500);
        dialog.setVisible(true);
        dialog.setLocation(400,400);
    }
    //
    //
    //action listener
    public void actionPerformed(ActionEvent e) {
    	JComboBox cb = (JComboBox)e.getSource();
        String idResource = (String)cb.getSelectedItem();
        int b = 0;
        int h = 0;
        int s = 0;
        int w = 0;
        
        if(idResource.charAt(0)=='B'){
        	System.out.println("body select");
        	for(int i=0;i<avatar.bodyS.length;i++){
        		if(avatar.bodyS[i]!=idResource){
        			b++;
        		}else{
        			break;
        		}
        	}System.out.println("index:"+b);
        	avatar.bodycount= b;
        	avatar.repaint();
        }else if(idResource.charAt(0)=='H'){
        	System.out.println("head select");
        	
        	for(int i=0;i<avatar.headS.length;i++){
        		if(avatar.headS[i]!=idResource){
        			h++;
        		}else{
        			break;
        		}
        	}System.out.println("index:"+h);
        	avatar.headcount= h;
        	avatar.repaint();
        }else if(idResource.charAt(0)=='S'){
        	System.out.println("shirt select");
        	
        	for(int i=0;i<avatar.shirtS.length;i++){
        		if(avatar.shirtS[i]!=idResource){
        			s++;
        		}else{
        			break;
        		}
        	}System.out.println("index:"+s);
        	avatar.shirtcount= s;
        	avatar.repaint();
        }else if(idResource.charAt(0)=='W'){
        	System.out.println("weapon select");
        	
        	for(int i=0;i<avatar.weaponS.length;i++){
        		if(avatar.weaponS[i]!=idResource){
        			w++;
        		}else{
        			break;
        		}
        	}System.out.println("index:"+w);
        	avatar.weaponcount= w;
        	avatar.repaint();
        }else {
        	System.err.print("this is wrong");
        }             
    }  	
    public class SelectEvent implements ActionListener{
		 public void actionPerformed(ActionEvent e){
			 System.out.println("Select called");
		     look = new IcetizenLook();
		     try {
		     	look.gidH = "http://iceworld.sls-atl.com/"+LoadVisualResources.getHead(avatar.headcount);
		     	look.gidB = "http://iceworld.sls-atl.com/"+LoadVisualResources.getBody(avatar.bodycount);
		     	look.gidS = "http://iceworld.sls-atl.com/"+LoadVisualResources.getShirt(avatar.shirtcount);
		     	look.gidW = "http://iceworld.sls-atl.com/"+LoadVisualResources.getWeapon(avatar.weaponcount);
			} catch (IOException e1) {
			e1.printStackTrace();
			}
		    active.setIcetizenLook(look);
		    active.prepareLookImage();
		 }
	}
        
}