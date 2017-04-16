package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;



@Entity
public class Home {

	private int id;
	private String adresse;
	private String ville;
	private int rooms;
	private int surface;
	private List<IntelligentDevice> devices = new ArrayList<IntelligentDevice>();
	private List<Person>owners = new ArrayList<Person>();
	
	public Home (){
	}

	public Home(String ad, String vi,int ro,int su) {
		adresse = ad;
		ville = vi;
		rooms = ro;
		surface = su;
	}

	@Id
	@GeneratedValue (strategy= GenerationType.AUTO)
	@Column(name="HOME_ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	@Column (name ="ville")
	public String getVille() {
		return ville;
	}
	public void setVille(String town) {
		ville = town;
	}
	
	@Column (name ="rooms")
	public int getRooms() {
		return rooms;
	}

	public void setRooms(int room) {
		this.rooms = room;
	}


	
	@Column (name ="surf")
	public int getSurface() {
		return surface;
	}

	public void setSurface(int surface) {
		this.surface = surface;
	}

	@OneToMany(cascade = CascadeType.ALL)
	public List<IntelligentDevice> getDevices() {
		return devices;
	}

	public void setDevices(List<IntelligentDevice> devices) {
		this.devices = devices;
	}
	
	public void addDevice(IntelligentDevice dev){
		this.devices.add(dev);
	}

	@Override
	public String toString() {
		return "Home [id=" + id + ", address=" + adresse + ", Town=" + ville + ", room=" + rooms + ", surface=" + surface
			 + "]";
	}

	@XmlTransient
	@ManyToMany(mappedBy="homes")
	public List<Person> getOwners() {
		return owners;
	}


	public void setOwners(List<Person> owners) {
		this.owners = owners;
	}
	
	public void addOwner(Person own) {
		this.owners.add(own);
	}
	
	
	
}
