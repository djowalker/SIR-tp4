package domain;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

import org.glassfish.jersey.server.JSONP;
import org.hibernate.annotations.OptimisticLock;
import org.hibernate.annotations.Proxy;



@Entity
@Table(name="person")
public class Person {
	
	private int id;
	private String name;
	private String surname;
	private String mail;
	private List<Friendship> friends = new ArrayList<Friendship>();
	private Collection<Home> homes = new ArrayList<Home>();
	
	public Person(){
	}
	
	public Person(String name, String surname,String m) {
		this.name = name;
		this.surname = surname;
		this.mail = m;
	}

	
	@Id
    @GeneratedValue
    @Column(name="PERSON_ID")
    public int getId() {
        return id;
    }

	public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@OneToMany(cascade = {CascadeType.ALL})
	public List<Friendship> getFriends() {
		return friends;
	}

	
	public void setFriends(List<Friendship>  friend) {
		this.friends = friend;
	}
	
	public void addFriend(Person friend){
		this.friends.add(new Friendship(this.getId(),friend.getId()));
	}
	
	@ManyToMany
    @JoinTable(name = "PERS_HOME", joinColumns = @JoinColumn(name="PERSON_ID", referencedColumnName="PERSON_ID"),
            inverseJoinColumns = @JoinColumn(name="HOME_ID", referencedColumnName="HOME_ID"))
	public Collection<Home> getHomes() {
		return homes;
	}

	public void setHomes(Collection<Home> homes) {
		this.homes = homes;
	}
	
	public void addHome(Home home){
		this.homes.add(home);
	}
	
	@Override
    public String toString() {
        return "Person [ " + this.id+ " : "+ this.name + "  " + this.surname + " , " + this.mail 
        		+ " , nombre de maison : " + this.homes.size() + " , nombre d'amis :"+this.friends.size()
        		+" ]";
    }
}
