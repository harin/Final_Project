package worldview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FetchThread extends Thread {
	long refresh_time = 5000;
	FetchInformation fetcher;
	
	
	
	
	public FetchThread(){
		refresh_time = 5000;
		fetcher = new FetchInformation(refresh_time/1000);

	}
	
	public void run(){
		try {

		while(true){	
			
			if(fetcher.isConnected()){
				
				fetcher = new FetchInformation(refresh_time/1000);
				
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
