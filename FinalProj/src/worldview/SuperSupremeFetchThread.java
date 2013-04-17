package worldview;

import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;

public class SuperSupremeFetchThread extends Thread {
	private LinkedList<ActionCommand> commands;
	
	public void run(){
		commands = new LinkedList<ActionCommand>();
		FetchInformation f = new FetchInformation();
		f.setAction(5);
		LinkedList<NullAction> a = f.getAction();
		/**
		 * ActionType
		 * 1 = walk
		 * 2 = talk
		 * 3 = yell
		 */
			for( NullAction n: a){
				boolean found = false;
				String uid = n.getUserid();
				int actionType = n.getActiontype();
				ActionCommand toUpdate = null;
				//find if uid already exist
				for( ActionCommand ac: commands){
					if(ac.uid.equals(uid)){
						found = true;
						toUpdate = ac;
						break;
					}
				}
				
				//if doesn't make new one
				if(!found){
					toUpdate = new ActionCommand(uid);
					commands.add(toUpdate);
				}
				
				//update action commands
				if(toUpdate != null){
					switch(actionType){
						case 1:
							String detail = n.getDetail();
							String xs = detail.substring(detail.indexOf("(")+1, detail.indexOf(","));
							int x = Integer.parseInt(xs);
							String ys = detail.substring(detail.indexOf(",")+1, n.getDetail().indexOf(")"));
							int y = Integer.parseInt(ys);
							System.out.println(uid +" Set walk to " +x+","+y);
							toUpdate.walkDest = new Point(x,y);
							break;
						case 2:
							toUpdate.talkMsg = n.getDetail();
							System.out.println(uid+ "\t Set talk Msg to" + n.getDetail());
							break;
						case 3:
							toUpdate.yellMsg = n.getDetail();
							System.out.println(uid+ " Set yell Msg to" + n.getDetail());

							break;
					}
				}
			}
			for(ActionCommand ac1: commands){
				System.out.println(ac1.uid +" talk:"+ac1.talkMsg +
						"\t yell:"+ ac1.yellMsg +
						"\t walkDest:" +ac1.walkDest);
			}
			

	}
	
	public static void main(String [] args){
		new SuperSupremeFetchThread().start();
	}
}
