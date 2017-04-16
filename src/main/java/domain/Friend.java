package domain;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Friend {

    @Id
    private FriendId id;

    @ManyToOne
    @JoinColumn(name="ME_ID", insertable=false, updatable=false)
    private Person me;

    @ManyToOne
    @JoinColumn(name="MY_FRIEND_ID", insertable=false, updatable=false)
    private Person myFriend;

}