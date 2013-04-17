package worldview;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import worldview.FetchInformation;


public class FetchThread extends Thread {
	long refresh_time;
	FetchInformation fetcher;
	JTextField text;
	MainFrame mainframe;

	public static void main(String[] args) throws MalformedURLException{

		FetchThread fetch = new FetchThread();
		fetch.setGUI();
		fetch.start();
	}
	
	
	public FetchThread(MainFrame mainframe){
		this.mainframe=mainframe;
		fetcher = new FetchInformation();		
	}
	
	public void setGUI(){
		JFrame gui = new JFrame();
		gui.setSize(150,150);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setBounds(100, 100, 40, 165);
		
		JPanel buttonPad = new JPanel();
		
		buttonPad.setPreferredSize(new Dimension(40,100));
		buttonPad.setLayout(new BorderLayout());
		gui.add(buttonPad);
		
		
		
		
		JPanel northPanel = new JPanel();
		buttonPad.add(northPanel,BorderLayout.NORTH);
		JButton increment = new JButton("+");
		northPanel.add(increment);
		incrementEvent i = new incrementEvent();
		increment.addActionListener(i);
		
		
		
		JPanel southPanel = new JPanel();
		buttonPad.add(southPanel,BorderLayout.SOUTH);
		JButton decrement = new JButton("-");
		southPanel.add(decrement);

		
		
		JPanel centerPanel = new JPanel();
		buttonPad.add(centerPanel,BorderLayout.CENTER);
		
		
		decrementEvent d = new decrementEvent();
		decrement.addActionListener(d);
		
		
		
		//rate of delay
		Font font1 = new Font("SansSerif", Font.BOLD, 20);
		text = new JTextField("5");
		text.setFont(font1);
		increment.setFont(font1);
		decrement.setFont(font1);
		text.setHorizontalAlignment(JTextField.CENTER);
		text.setPreferredSize( new Dimension(50, 30) );
		centerPanel.add(text);	
		
		gui.setVisible(true);
	}
	
	public FetchInformation getFetch(){
		return fetcher;
	}
	
	
	
	public FetchThread(){
		fetcher = new FetchInformation();

	}
	
	public void run(){
		try {

		while(true){	
			
			if(fetcher.isConnected()){
				
				fetcher.setRefreshTime(refresh_time);
				fetcher.setTime();
				fetcher.setFetchState();
				mainframe.increaseKodhod();
					
			}else{
				JOptionPane.showMessageDialog(new JPanel(), "ICE World cannot be reached due to the Internet Connection");
				System.exit(0);
			}
			
				this.sleep(refresh_time);}
		
			} catch (InterruptedException e) {
				System.out.println("Problem with Tread");
				e.printStackTrace();
			}
		
	}
	
	public class incrementEvent implements ActionListener{
		public void actionPerformed(ActionEvent e){

			if(refresh_time<10000){
				refresh_time+=1000;
				text.setText(""+(refresh_time)/1000);
			}
		}

	}
	
	public class decrementEvent implements ActionListener{
		public void actionPerformed(ActionEvent e){

			if(refresh_time>1000){
				refresh_time-=1000;
				text.setText(""+(refresh_time)/1000);
			}
		}

	}
	
}
