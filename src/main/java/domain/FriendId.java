package domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class FriendId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name="ME_ID", nullable=false, updatable=false)
    private Integer meId;

    @Column(name="MY_FRIEND_ID", nullable=false, updatable=false)
    private Integer myFriendId;

    public Integer getMeId() {
		return meId;
	}

	public void setMeId(Integer meId) {
		this.meId = meId;
	}

	public Integer getMyFriendId() {
		return myFriendId;
	}

	public void setMyFriendId(Integer myFriendId) {
		this.myFriendId = myFriendId;
	}

	@Override
	public boolean equals(Object o) {
        if(o == null)
            return false;

        if(!(o instanceof FriendId))
            return false;

        FriendId other = (FriendId) o;
        if(!(other.getMeId().equals(getMeId())))
            return false;

        if(!(other.getMyFriendId().equals(getMyFriendId())))
            return false;

        return true;
    }



}
