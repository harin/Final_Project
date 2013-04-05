package help__dialog;

import javax.swing.JFrame;
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
    	
        tabbedPane.addTab(logging, null,loggingPanel,"logging in/out description");
        tabbedPane.addTab(components, null,componentsPanel,"Map/ Message Typing / Video Control"); 
        tabbedPane.addTab(viewing, null,viewingPanel,"Zooming Methods(pan, drag) zoom short keys"); 
        tabbedPane.addTab(tranferring, null,tranferringPanel,"Transfer files to and from");
        tabbedPane.addTab(custom, null,	customPanel,"Avartar/Sounds"); 
        tabbedPane.addTab(features, null,featuresPanel,"Chat/yell/walk/special questions"); 
 
        add(tabbedPane, BorderLayout.CENTER);
    }
    
    private JPanel createLoggingDialogBox() {
    	JPanel panel = new JPanel();
		panel.setLayout( null );
    	
    	return panel;
    }
    private JPanel createComponentsDialogBox() {
    	JPanel panel = new JPanel();
		panel.setLayout( null );
    	
    	return panel;
    }
    private JPanel createViewingDialogBox() {
    	JPanel panel = new JPanel();
		panel.setLayout( null );
    	
    	return panel;
    }
    private JPanel createTransferringDialogBox() {
    	JPanel panel = new JPanel();
		panel.setLayout( null );
    	
    	return panel;
    }
    private JPanel createCustomDialogBox() {
    	JPanel panel = new JPanel();
		panel.setLayout( null );
    	
    	return panel;
    }
    private JPanel createFeaturesDialogBox() {
    	JPanel panel = new JPanel();
		panel.setLayout( null );
    	
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
        JFrame frame = new JFrame("You clicked Help, I'm here!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
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