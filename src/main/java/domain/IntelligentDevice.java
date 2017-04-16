package domain;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="intelligent_devices")
public abstract class IntelligentDevice implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;

	private String reference;

	private Home home;
	
	private int consommationAvg;
	
	public IntelligentDevice(){
	}
	public IntelligentDevice(String ref,Home hom,int conso){
		this.reference = ref;
		this.home = hom;
		this.consommationAvg = conso;
	}
	
	public IntelligentDevice(String ref,int conso){
		this.reference = ref;
		this.consommationAvg = conso;
	}
	
	@Id
	@GeneratedValue (strategy= GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column (name ="ref")
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	
	@Column (name ="consoAvg")
	public int getConsommationAvg() {
		return consommationAvg;
	}
	public void setConsommationAvg(int consommationAvg) {
		this.consommationAvg = consommationAvg;
	}
	@XmlTransient
	@ManyToOne
	public Home getHome() {
		return home;
	}

	public void setHome(Home home) {
		this.home = home;
	}
}
