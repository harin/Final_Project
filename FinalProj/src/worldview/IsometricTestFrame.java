package worldview;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class IsometricTestFrame extends JFrame{
	private WorldView view;
	private JButton zoomInBut, zoomOutBut;
	
	public IsometricTestFrame(){
		this.setPreferredSize(new Dimension(1000,800));
		this.setLayout(new BorderLayout());
		GUIsetup();
		this.pack();
		this.setVisible(true);
	}
	
	public void GUIsetup(){
		view = new WorldView(this.WIDTH,this.HEIGHT);
		add(view);
		
		zoomInBut = new JButton("+");
		zoomOutBut = new JButton("-");
		ZoomButtonHandler zbh = new ZoomButtonHandler();
		zoomInBut.addActionListener(zbh);
		zoomOutBut.addActionListener(zbh);
		
		JPanel zoomPanel = new JPanel();
		zoomPanel.setLayout(new FlowLayout());
		zoomPanel.add(zoomInBut);
		zoomPanel.add(zoomOutBut);
		
		add(zoomPanel, BorderLayout.SOUTH);
	}
	
	class ZoomButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JButton but = (JButton) e.getSource();
			if(but.getText().equals("+")){
				view.zoomIn();
			}else{
				view.zoomOut();
			}
		}
	}
	
	public static void main(String [] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				new IsometricTestFrame();
			}
		});
	}
}
