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
import java.io.IOException;


@SuppressWarnings("serial")
public class Setting extends JPanel{
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
     
}