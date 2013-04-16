package worldview;

public class NullAction {
	long timestamp;
	String id;
	int type; //1 for going to, 2 for talking, and 3 for yelling.
	String detail;
	long aid;
	
	
	public void setActionid(long aid){
		this.aid = aid;
	}
	
	public void setDetail(String detail){
		this.detail =detail;
	}
	
	public void setTimestamp(long timestamp){
		this.timestamp =timestamp;
	}
	
	public void setUserid(String id){
		this.id =id;
	}
	
	public void setType(int type){
		this.type =type;
	}
	
	public long getTimestamp(){
		return timestamp;
	}
	
	
	public String getUserid(){
		return id;
	}
	
	public int getActiontype(){
		return type;
	}
	
	public String getDetail(){
		return detail;
	}
	
	public long getActionid(){
		return aid;
	}
	
	
}
