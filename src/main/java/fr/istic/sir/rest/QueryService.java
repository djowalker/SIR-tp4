package fr.istic.sir.rest;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import domain.ElectronicDevice;
import domain.Heater;
import domain.Home;
import domain.IntelligentDevice;
import domain.Person;

public class QueryService {
	
	private static EntityManager em;
	private static CriteriaBuilder cb;
	
	public QueryService(EntityManager entityManager) {
		em = entityManager;
		cb = em.getCriteriaBuilder();
	}

	public  static void addPerson(String prenom,String nom,String mail){
		Person p = new Person(prenom,nom,mail);
		em.persist(p);	
	}

	public  static void addHome(String addr,String ville,int rooms,int sur,String preown,String nomown){
		Person own = getPerson(preown,nomown);
		Home h = new Home(addr,ville,rooms,sur);
		own.getHomes().add(h);
		em.persist(h);
	}
	
	public  void addDeviceHeater(String ref,int con,String source,String add){
			Home h = getHomeByAdd(add);
			IntelligentDevice dev = new Heater(ref,h,con,source);
			h.getDevices().add(dev);
			em.persist(dev);
	}
	
	public  void addDeviceElectronic(String ref,int con,String name,String add){
		Home h = getHomeByAdd(add);
		IntelligentDevice dev = new ElectronicDevice(ref,h,con,name);
		h.getDevices().add(dev);
		em.persist(dev);
	}

	
	public Home getHomeByAdd(String add) {
//		return em.createQuery("Select h From Home h where h.adresse=:a ", Home.class).
//				setParameter("a", add).getSingleResult();
		 CriteriaQuery<Home> q = cb.createQuery(Home.class);
		 Root<Home> c = q.from(Home.class);
		 Expression<String>path = c.get("adresse");
		 Predicate condition = cb.like(path, add);
		 q.where(condition);
		 TypedQuery<Home> qT = em.createQuery(q);
		 return qT.getSingleResult();
	}
	
	public static int numberOfPerson() {
		 CriteriaQuery<Person> q = cb.createQuery(Person.class);
		 Root<Person> c = q.from(Person.class);
		 q.select(c);
		 TypedQuery<Person> qT = em.createQuery(q);
		 return qT.getResultList().size();
	}
	
	public  static List<Person> getAllPerson() {
//		return em.createQuery("Select a From Person a", Person.class).getResultList();
		 CriteriaQuery<Person> q = cb.createQuery(Person.class);
		 Root<Person> c = q.from(Person.class);
		 q.select(c);
		 TypedQuery<Person> qT = em.createQuery(q);
		 return qT.getResultList();
	}

	public static  Person getPerson(String prenom,String nom){
//		return em.createQuery("Select a From Person a where a.name=:p and a.surname=:n", Person.class).
		 CriteriaQuery<Person> q = cb.createQuery(Person.class);
		 Root<Person> c = q.from(Person.class);
		 ParameterExpression<String> n = cb.parameter(String.class);
		 ParameterExpression<String> p = cb.parameter(String.class);
		 Expression<String>pathP = c.get("name");
		 Expression<String>pathN = c.get("surname");
		 Predicate condition = cb.like(pathN, n);
		 Predicate condition2 = cb.like(pathP,p);
		 q.select(c).where(condition,condition2);
		 TypedQuery<Person> qT = em.createQuery(q);
		 return qT.setParameter(n, nom).setParameter(p, prenom).getSingleResult();
	}
	
	public static List<Home> getHomes(int id) {
//		return em.createQuery("SELECT h From Person p Join p.homes h Where p.id=:longId").setParameter("longId", id).getResultList();
		 CriteriaQuery<Home> q = cb.createQuery(Home.class);
		 Metamodel m = em.getMetamodel();
		 EntityType<Person> Person_ = m.entity(Person.class);

		 Root<Person> c = q.from(Person.class);
		 Join<Person, Home> homes = c.joinList("homes");
		 ParameterExpression<Integer> p = cb.parameter(Integer.class);
		 Expression<Integer>pathP = c.get("id");
		 Predicate condition = cb.equal(pathP,p);
		 q.select(homes).where(condition);
		 TypedQuery<Home> qT = em.createQuery(q);
		 return qT.setParameter(p, id).getResultList();
	}
	
	public  static List<IntelligentDevice> getDevices(int id) {
		List<IntelligentDevice> result = new ArrayList<IntelligentDevice>();
		List<Home> listHome = getHomes(id);
		for (Home hom : listHome) {
			List<IntelligentDevice> listDev = hom.getDevices();
			for (IntelligentDevice dev : listDev) {
				result.add(dev);
			}
		}
		return result;
	}

}
