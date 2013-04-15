package setting;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


@SuppressWarnings("serial")
public class Setting extends JPanel implements ActionListener{
	LoadVisualResources avatar;
	
	
	JTabbedPane tabbedPane = new JTabbedPane();
    JFrame frame;

    public Setting(JFrame frame) throws IOException{
    	
    	super(new BorderLayout());
    	this.frame = frame;
    	
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
    	
    	bodyBox.addActionListener(this);
    	headBox.addActionListener(this);
    	shirtBox.addActionListener(this);
    	weaponBox.addActionListener(this);
      	
    	LeftPanel.add(bodyLabel);
    	LeftPanel.add(bodyBox);
    	LeftPanel.add(headLabel);
    	LeftPanel.add(headBox);
    	LeftPanel.add(shirtLabel);
    	LeftPanel.add(shirtBox);
    	LeftPanel.add(weaponLabel);
    	LeftPanel.add(weaponBox);
    	
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
        Setting newContentPane = new Setting(frame);
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
    	
        if(idResource.charAt(0)=='B'){
        	System.out.println("body select");
        	int b = 0;
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
        	int h = 0;
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
        	int s = 0;
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
        	int w = 0;
        	for(int i=0;i<avatar.weaponS.length;i++){
        		if(avatar.weaponS[i]!=idResource){
        			w++;
        		}else{
        			break;
        		}
        	}System.out.println("index:"+w);
        	avatar.weaponcount= w;
        	avatar.repaint();
        }else{
        	System.err.print("ERROR");
        }             
    }  	
}