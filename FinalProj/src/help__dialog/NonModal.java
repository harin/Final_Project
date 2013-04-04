package help__dialog;

//This program shows two ways to make a modeless dialog.
//The first dialog box shown is make by combining components 
//with the JDialog class. (pressing OK brings up the second
//dialog box).
//The second example combines the functionality offered by the
//JOptionPane class with the JDialog class.

//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc..
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.Icon;
import java.awt.FlowLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;


public class NonModal extends JFrame
{
    private JDialog dialog;
    private JDialog optionPaneDialog;
    private JTextArea tracker;
    
    //Using a standard Java icon
    private Icon warningIcon = UIManager.getIcon("OptionPane.warningIcon");
    
    //Application start point   
    public static void main(String[] args) {
     //Use the event dispatch thread for Swing components
     EventQueue.invokeLater(new Runnable()
     {
         public void run()
         {
             //create GUI frame
             new NonModal().setVisible(true);          
         }
     });
              
    }
    
    public NonModal()
    {
        //Make sure the program exits when the frame closes
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Dialog Box Example");
        setSize(500,500);
        
        //This will center the JFrame in the middle of the screen
        setLocationRelativeTo(null);
        
        //Using a JTextArea object to highlight the button click events
        tracker = new JTextArea("Click Tracker:");
        add(tracker);
        
        //The dialog box is made up of the JDialog containing
        //two JLabels (one for the icon, one for message) and
        //two JButtons to make an OK/CANCEL selection
        dialog = new JDialog(this,"Help Dialog");    
        dialog.setSize(400,500);
        dialog.setLayout(new FlowLayout());
        dialog.setLocationRelativeTo(this);
        
        JLabel dialogIcon = new JLabel(warningIcon);
        
        //Using HTML to fudge the JLabel into being a multi-lined.
        JLabel dialogLabel = new JLabel("Click OK to see the other"  + "modeless dialog example..");
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        
        
        //Press OK and the dialog closes and the
        //JOptionPane dialog is shown.
        okButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                tracker.append("\n" + "OK clicked..");
                dialog.dispose();
                OptionJDialog();
            }
        });
            
        
        //Press Cancel and the dialog closes. Game Over.
        cancelButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                tracker.append("\n" + "Cancel clicked..");
                dialog.dispose();
                 
            }
        });
        
        dialog.add(dialogIcon);
        dialog.add(dialogLabel);
        dialog.add(cancelButton);
        dialog.add(okButton);       
        dialog.setVisible(true);  
        
    }
    
    //Example of using the JOptionPane with
    //the JDialog class
    public void OptionJDialog()
    {

        optionPaneDialog = new JDialog(this,"Click a button");
        
        //Note we are creating an instance of a JOptionPane
        //Normally it's just a call to a static method.
        JOptionPane optPane = new JOptionPane("Let JOptionPane do the work", 
                 JOptionPane.INFORMATION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
        
        //Listen for the JOptionPane button click. It comes through as property change 
        //event with the propety called "value". 
        optPane.addPropertyChangeListener(new PropertyChangeListener()
        {
            public void propertyChange(PropertyChangeEvent e)
            {
                if (e.getPropertyName().equals("value"))
                {
                        switch ((Integer)e.getNewValue())
                        {
                            case JOptionPane.OK_OPTION:
                                tracker.append("\n"+"JOptionPane OK clicked..");
                                break;
                            case JOptionPane.CANCEL_OPTION:
                                tracker.append("\n"+"JOptionPane Cancel clicked..");
                                break;
                            
                        }
                        optionPaneDialog.dispose();
                }
            }
        }
                );
        optionPaneDialog.setContentPane(optPane);
        
        //Let the JDialog figure out how big it needs to be
        //based on the size of JOptionPane by calling the 
        //pack() method
        optionPaneDialog.pack();
        optionPaneDialog.setLocationRelativeTo(this);
        optionPaneDialog.setVisible(true);
        
    }
    
}