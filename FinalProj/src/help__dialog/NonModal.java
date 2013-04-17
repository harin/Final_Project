package help__dialog;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import java.awt.BorderLayout;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


@SuppressWarnings("serial")
public class NonModal extends JPanel{   
	JTabbedPane tabbedPane = new JTabbedPane();
    JFrame frame;
    
    String logging ="Logging In/Out";
    String components ="Program Components";
    String viewing ="Viewing ICE World";
    String tranferring ="Data Transferring";
    String custom = "Customization";
    String features = "Features";
    
        
    public NonModal(JFrame frame) throws IOException{
    	
    	super(new BorderLayout());
    	this.frame = frame;
    	
     	JEditorPane loggingPanel = createLoggingDialogBox();
     	JEditorPane componentsPanel = createComponentsDialogBox();
     	JEditorPane viewingPanel = createViewingDialogBox(); 	
     	JEditorPane tranferringPanel = createTransferringDialogBox();
     	JEditorPane customPanel = createCustomDialogBox();
     	JEditorPane featuresPanel = createFeaturesDialogBox();
        
        JScrollPane loggingscrollPane = new JScrollPane(loggingPanel);
        JScrollPane componentsscrollPane = new JScrollPane(componentsPanel);
        JScrollPane viewingscrollPane = new JScrollPane(viewingPanel);
        JScrollPane tranferringscrollPane = new JScrollPane(tranferringPanel);
        JScrollPane customscrollPane = new JScrollPane(customPanel);
        JScrollPane featuresscrollPane = new JScrollPane(featuresPanel);
        
        
        tabbedPane.addTab(logging, null,loggingscrollPane);
        tabbedPane.addTab(components, null,componentsscrollPane); 
        tabbedPane.addTab(viewing, null,viewingscrollPane); 
        tabbedPane.addTab(tranferring, null,tranferringscrollPane);
        tabbedPane.addTab(custom, null,	customscrollPane); 
        tabbedPane.addTab(features, null,featuresscrollPane); 
 
        add(tabbedPane, BorderLayout.CENTER);
    }
    
    private JEditorPane createLoggingDialogBox() throws IOException {
    	JEditorPane htmlPane = new JEditorPane();
    	htmlPane.setEditable(false);
    	htmlPane.setPage(new URL("https://dl.dropboxusercontent.com/u/94849603/logging.html"));
    	
    	return htmlPane;
    }
    private JEditorPane createComponentsDialogBox() throws IOException {
    	JEditorPane htmlPane = new JEditorPane();
    	
    	htmlPane.setEditable(false);
    	htmlPane.setPage(new URL("https://dl.dropboxusercontent.com/u/94849603/components.html"));
    	
    	return htmlPane;
    }
    private JEditorPane createViewingDialogBox() throws IOException {
    	JEditorPane htmlPane = new JEditorPane();
    	
    	htmlPane.setEditable(false);
    	htmlPane.setPage(new URL("https://dl.dropboxusercontent.com/u/94849603/viewing.html"));
    	
    	return htmlPane;
    }
    private JEditorPane createTransferringDialogBox() throws IOException {
    	JEditorPane htmlPane = new JEditorPane();
    	
    	htmlPane.setEditable(false);
    	htmlPane.setPage(new URL("https://dl.dropboxusercontent.com/u/94849603/transferring.html"));
    	
    	return htmlPane;
    }
    private JEditorPane createCustomDialogBox() throws IOException {
    	JEditorPane htmlPane = new JEditorPane();
    	
    	htmlPane.setEditable(false);
    	htmlPane.setPage(new URL("https://dl.dropboxusercontent.com/u/94849603/custom.html"));
    	
    	return htmlPane;
    }
    private JEditorPane createFeaturesDialogBox() throws IOException {
    	JEditorPane htmlPane = new JEditorPane();
    	
    	htmlPane.setEditable(false);
    	htmlPane.setPage(new URL("https://dl.dropboxusercontent.com/u/94849603/features.html"));

    	return htmlPane;
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}          
         }
     });
    }
    private static void createAndShowGUI() throws IOException {
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