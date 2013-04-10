package worldview;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.*;

@SuppressWarnings("serial")
public class TextChatBox extends JFrame implements ActionListener{
	
	private final String newline = "\n"	;
	private JTextField textField;
	private JTextArea textArea;
	private JScrollPane textAreaScroll;
	
	
	public TextChatBox(String s){
		super(s);
		this.setPreferredSize(new Dimension(500, 350));
		setGUI();
	}
	
	public static void createAndShowGUI(){
		TextChatBox console = new TextChatBox("Chat Box");
		console.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		textArea.setEditable(false);
		textAreaScroll = new JScrollPane(textArea);
		
		this.add(textField, BorderLayout.SOUTH);
		this.add(textAreaScroll, BorderLayout.CENTER);
	}
	
	public void actionPerformed(ActionEvent evt){
		
		String text = textField.getText();
		String clear = "/clear";
		textArea.append(">> "+text + newline);
		textField.setText("");
		try{
			textArea.append(fetch(text) + newline);
		}catch (Exception e){}
		//Make sure the new text is visible, event if there
		//was a selection in the textArea
		textArea.setCaretPosition(textArea.getDocument().getLength());
		
		if(text.equals(clear)){
			textArea.setText("");
		}
		
		
		
	}
	
	public String fetch(String s) throws Exception{
		URL iceworld = new URL(s);
		URLConnection con = iceworld.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String ret = in.readLine();
		return ret;
	}
}
//--------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------

//	public static void main(String [] args){
//		//create a new thread which calls this class and run it.
//		javax.swing.SwingUtilities.invokeLater(new Runnable(){
//			public void run(){
//				TextChatBox.createAndShowGUI();
//			}
//		});
//	}
//}
