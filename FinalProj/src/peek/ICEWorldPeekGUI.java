package peek;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.*;

public class ICEWorldPeekGUI extends JFrame implements ActionListener{
	
	private final String newline = "\n"	;
	private JTextField textField;
	private JTextArea textArea;
	private JScrollPane textAreaScroll;
	
	
	public ICEWorldPeekGUI(String s){
		super(s);
		this.setPreferredSize(new Dimension(600, 400));
		setGUI();
	}
	
	public static void createAndShowGUI(){
		ICEWorldPeekGUI console = new ICEWorldPeekGUI("ICE World Peek");
		console.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		console.pack();
		console.setVisible(true);
	}
	
	public void setGUI(){
		
		LayoutManager manager = new BorderLayout();
		this.setLayout(manager);
		
		
		textField = new JTextField();
		textField.addActionListener(this);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textAreaScroll = new JScrollPane(textArea);
		
		this.add(textField, BorderLayout.SOUTH);
		this.add(textAreaScroll, BorderLayout.CENTER);
	}
	
	public void actionPerformed(ActionEvent evt){
		
		String text = textField.getText();
		textArea.append(">>> "+text + newline);
		textField.setText("");
		try{
			
			textArea.append(fetch(text) + newline);

		}catch (Exception e){}
		//Make sure the new text is visible, event if there
		//was a selection in the textArea
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}
	
	public String fetch(String s) throws Exception{
		URL iceworld = new URL(s);
		URLConnection con = iceworld.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String ret = in.readLine();
		return ret;
	}
//--------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------

	public static void main(String [] args){
		//create a new thread which calls this class and run it.
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				ICEWorldPeekGUI.createAndShowGUI();
			}
		});
	}
}
