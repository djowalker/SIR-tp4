package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Friendship {
	
	private int shipId;
	private int meId;
	private int friendId;
	
	public Friendship(){
		
	}
	
	public Friendship(int me, int friend){
		meId = me;
		friendId = friend;
	}

	@Id
	@GeneratedValue (strategy= GenerationType.AUTO)
	public int getShipId(){
		return shipId;
	}
	
	public void setShipId(int shipId) {
		this.shipId = shipId;
	}
	
	public int getMeId() {
		return meId;
	}

	public void setMeId(int meId) {
		this.meId = meId;
	}

	public int getFriendId() {
		return friendId;
	}

	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}



	
	@Override
	public String toString() {
		return "Friendship ["+meId+" et "+ friendId + "]";
	}
}
