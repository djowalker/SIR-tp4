package servlet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class EntitySingleton {
	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static boolean bool;
	
	public static EntityManagerFactory getFactory() {
		return factory;
	}
	public static void setFactory(EntityManagerFactory factory) {
		EntitySingleton.factory = factory;
	}
	public static EntityManager getManager() {
		return manager;
	}
	public static void setManager(EntityManager manager) {
		EntitySingleton.manager = manager;
	}
	public static boolean isBool() {
		return bool;
	}
	public static void setBool(boolean bool) {
		EntitySingleton.bool = bool;
	}
	
	

}
