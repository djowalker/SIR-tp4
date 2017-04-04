package servlet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntitySingleton {
	private static EntityManager manager;
	private static EntityManagerFactory factory;
	private static EntitySingleton single = null;
	
	private EntitySingleton(){
		factory  = Persistence.createEntityManagerFactory("example");
		manager = factory.createEntityManager();
		single = this;
	}
	
	public static EntitySingleton getInstance() {
		if(single == null){
			return new EntitySingleton();
		}
		return single;
	}

	public static EntityManager getManager() {
		return manager;
	}

	public static void setFactory(EntityManagerFactory factory) {
		EntitySingleton.factory = factory;
	}

	

}
