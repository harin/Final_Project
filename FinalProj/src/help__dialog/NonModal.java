package help__dialog;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;


@SuppressWarnings("serial")
public class NonModal extends JPanel
{   JTabbedPane tabbedPane = new JTabbedPane();
    JFrame frame;

    String logging ="Logging In/Out";
    String components ="Program Components";
    String viewing ="Viewing ICE World";
    String tranferring ="Data Transferring";
    String custom = "Customization";
    String features = "Features";
    
        
    public NonModal(JFrame frame){
    	
    	super(new BorderLayout());
    	this.frame = frame;
    	
     	JPanel loggingPanel = createLoggingDialogBox();
        JPanel componentsPanel = createComponentsDialogBox();
        JPanel viewingPanel = createViewingDialogBox(); 	
        JPanel tranferringPanel = createTransferringDialogBox();
        JPanel customPanel = createCustomDialogBox();
        JPanel featuresPanel = createFeaturesDialogBox();
    	
        tabbedPane.addTab(logging, null,loggingPanel);
        tabbedPane.addTab(components, null,componentsPanel); 
        tabbedPane.addTab(viewing, null,viewingPanel); 
        tabbedPane.addTab(tranferring, null,tranferringPanel);
        tabbedPane.addTab(custom, null,	customPanel); 
        tabbedPane.addTab(features, null,featuresPanel); 
 
        add(tabbedPane, BorderLayout.CENTER);
    }
    
    private JPanel createLoggingDialogBox() {
    	JPanel panel = new JPanel();
    	JLabel label = new JLabel("logging in/out description");
    	
		panel.add(label);
    	
    	return panel;
    }
    private JPanel createComponentsDialogBox() {
    	JPanel panel = new JPanel();
    	JLabel label = new JLabel("Map/ Message Typing / Video Control");
    	
		panel.add(label);
    	
    	return panel;
    }
    private JPanel createViewingDialogBox() {
    	JPanel panel = new JPanel();
    	JLabel label = new JLabel("Zooming Methods(pan, drag) zoom short keys");
    	
		panel.add(label);
    	
    	return panel;
    }
    private JPanel createTransferringDialogBox() {
    	JPanel panel = new JPanel();
    	JLabel label = new JLabel("Transfer files to and from");
    	
		panel.add(label);
    	
    	return panel;
    }
    private JPanel createCustomDialogBox() {
    	JPanel panel = new JPanel();
    	JLabel label = new JLabel("Avartar/Sounds");
    	
		panel.add(label);
    	
    	return panel;
    }
    private JPanel createFeaturesDialogBox() {
    	JPanel panel = new JPanel();
    	JLabel label = new JLabel("Chat/yell/walk/special questions");
    	
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
        JFrame frame = new JFrame("You asked for Help, I'm here!");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 
        //Create and set up the content pane.
        NonModal newContentPane = new NonModal(frame);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.setSize(400, 500);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);//set it in the center of screen
    }
     
}