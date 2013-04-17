package worldview;

import iceworld.given.ICEWorldImmigration;

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
	private JCheckBox yell;
	private JPanel textingPane;
	private JTextField textField;
	private JTextArea textArea;
	private JScrollPane textAreaScroll;
	private static NullIcetizen active;
	private static ICEWorldImmigration immigration;
	
	
	 public TextChatBox(NullIcetizen a,ICEWorldImmigration im){
		
		immigration = im;
		active = a;
		this.setPreferredSize(new Dimension(500, 350));
		this.setEnabled(true);
		setGUI();
		
	}
	
	public static void createAndShowGUI(){
		TextChatBox console = new TextChatBox(active,immigration);
		console.pack();
		console.setVisible(true);
	}
	
	public void setGUI(){
		
		LayoutManager manager = new BorderLayout();
		this.setLayout(manager);
		textingPane = new JPanel();
		textingPane.setLayout(new BoxLayout(textingPane, BoxLayout.LINE_AXIS));
		
		yell=new JCheckBox("YELL");
				
		textField = new JTextField();
		textField.addActionListener(this);

		textArea = new JTextArea();
		textArea.setLineWrap(true);	
		textArea.setEditable(false);
		textArea.setForeground(Color.BLUE);
		textAreaScroll = new JScrollPane(textArea);
		
		
		textingPane.add(textField);
		textingPane.add(yell);
		

		
		this.add(textingPane, BorderLayout.SOUTH);
		this.add(textAreaScroll, BorderLayout.CENTER);
	}
	
	public void actionPerformed(ActionEvent evt){
		
		String text;
		String clear = "/clear";
		
		
		if(yell.isSelected()){
				
			text = textField.getText();
			
			if(immigration.yell(text)){
				System.out.println("Sent yell to server:"+text);
			}
			
			
			textArea.append(">>YELLED : "+text + newline);
			textField.setText("");
			try{
				textArea.append(fetch(text) + newline);
			}catch (Exception e){}
			//Make sure the new text is visible, event if there
			//was a selection in the textArea
			textArea.setCaretPosition(textArea.getDocument().getLength());
			
			active.setYellMsg(text);
			
			if(text.equals(clear)){
				textArea.setText("");
			}
		}
		else {
		
			text = textField.getText();
			
			if(immigration.talk(text)){
				System.out.println("Sent talk to server:"+text);
			}
			
			textArea.append(">> "+text + newline);
			textField.setText("");
			try{
				textArea.append(fetch(text) + newline);
			}catch (Exception e){}
			//Make sure the new text is visible, event if there
			//was a selection in the textArea
			textArea.setCaretPosition(textArea.getDocument().getLength());
			
			active.setTalkMsg(text);
			
			if(text.equals(clear)){
				textArea.setText("");
			}
			
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
