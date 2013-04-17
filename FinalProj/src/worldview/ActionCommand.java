package worldview;

import java.awt.Point;

public class ActionCommand {
	String uid;
	Point walkDest;
	String talkMsg;
	String yellMsg;
	
	public ActionCommand(String uid){
		this.uid = uid;
	}
}
