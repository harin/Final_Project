package worldview;

import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;

public class SuperSupremeFetchThread extends Thread {
	private LinkedList<ActionCommand> commands;
	private LinkedList<NullIcetizen> icetizens;
	private MainFrameNoFetch mf;
	
	public SuperSupremeFetchThread(LinkedList<NullIcetizen> n, MainFrameNoFetch mf){
		this.icetizens = n;
		this.mf = mf;
	}
	
	public void run(){
		commands = new LinkedList<ActionCommand>();
		FetchInformation f = new FetchInformation();
		
		while(true){
			f.setTime();
			System.out.println("The time of server:=======>"+f.getTime());
			f.setAction(f.getTime());
			LinkedList<NullAction> a = f.getAction();
			System.out.println(a.size());
			f.setFetchState();
			String weather = f.getWeatherCondition();
			System.out.println("set weather to "+weather);
			mf.updateWeather(weather);
			
			/**
			 * ActionType
			 * 1 = walk
			 * 2 = talk
			 * 3 = yell
			 */
				for( NullAction n: a){
					System.out.println("Enter loop");
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
								//System.out.println(uid +" Set walk to " +x+","+y);
								toUpdate.walkDest = new Point(x,y);
								break;
							case 2:
								toUpdate.talkMsg = n.getDetail();
								//System.out.println(uid+ "\t Set talk Msg to" + n.getDetail());
								break;
							case 3:
								toUpdate.yellMsg = n.getDetail();
								//System.out.println(uid+ " Set yell Msg to" + n.getDetail());
	
								break;
						}
					}
				}
				for(ActionCommand ac1: commands){
					System.out.println(ac1.uid +" talk:"+ac1.talkMsg +
							"\t yell:"+ ac1.yellMsg +
							"\t walkDest:" +ac1.walkDest);
					for(NullIcetizen n:icetizens){
						if(n.getUserid().equals(ac1.uid)){
							System.out.println("Setting "+ ac1.uid + n.getUsername()+"walk to" +ac1.walkDest);
							n.setDestination((Point)ac1.walkDest.clone());
						}
					}
				}
				
				mf.updateIcetizens(icetizens);
				System.out.println("-----------------------------------------------------");

				
				try{
					Thread.sleep(10000);
	
				}catch(Exception e){
					e.printStackTrace();
				}
		}
	}
	

}
