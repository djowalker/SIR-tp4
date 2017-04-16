package servlet;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;

public class EntitySingleton {
	private static final EntityManagerFactory emf; 
	private static final ThreadLocal<EntityManager> threadLocal;
	private static final Logger logger;
	//private static final CriteriaBuilder cb;
	
	static {
		emf = Persistence.createEntityManagerFactory("example"); 		
		threadLocal = new ThreadLocal<EntityManager>();
		//cb = threadLocal.get().getCriteriaBuilder();
		logger = Logger.getLogger("example");
		logger.setLevel(Level.ALL);
	}
		
	public static EntityManager getManager() {
		EntityManager manager = threadLocal.get();		
		if (manager == null || !manager.isOpen()) {
			manager = emf.createEntityManager();
			threadLocal.set(manager);
		}
		return manager;
	}
	
	 public static void closeEntityManager() {
        EntityManager em = threadLocal.get();
        threadLocal.set(null);
        if (em != null) em.close();
    }
    
    public static void beginTransaction() {
    	getManager().getTransaction().begin();
    }
    
    public static void commit() {
    	getManager().getTransaction().commit();
    	closeEntityManager();
    }  
    
    public static void rollback() {
		if (!getManager().getTransaction().isActive()) {
			getManager().getTransaction().begin();
		}
    	getManager().getTransaction().rollback();
    	closeEntityManager();
    } 
	
    public static Query createQuery(String query) {
		return getManager().createQuery(query);
	}
    
	public static void log(String info, Level level, Throwable ex) {
    	logger.log(level, info, ex);
    }
}
