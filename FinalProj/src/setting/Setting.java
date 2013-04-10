package setting;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;


@SuppressWarnings("serial")
public class Setting extends JPanel
{   JTabbedPane tabbedPane = new JTabbedPane();
    JFrame frame;

    public Setting(JFrame frame){
    	
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
    
    private JPanel customPanel() {
    	JPanel panel = new JPanel();
    	JLabel label = new JLabel("Avartar  Body  Shirt  Headgear  Weapon");
    	
		panel.add(label);
    	
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
             createAndShowGUI();          
         }
     });
    }
    private static void createAndShowGUI() {
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