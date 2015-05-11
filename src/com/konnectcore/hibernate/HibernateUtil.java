package com.konnectcore.hibernate;

	import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

	public class HibernateUtil {

	   private static ServiceRegistry serviceRegistry;
	   private static final ThreadLocal<Session> threadLocal = new ThreadLocal();
	  
	   private static SessionFactory sessionFactory = buildSessionFactory();

	    private static SessionFactory buildSessionFactory() {
	        try {
	              Configuration configuration = new Configuration();
	              return configuration.configure()
	                                  .buildSessionFactory(
	                                       new StandardServiceRegistryBuilder()  
	                                          .applySettings(configuration.getProperties())
	                                          .build());
	        } catch (Throwable ex) {
	            System.err.println("Initial SessionFactory creation failed." + ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	    }


	  

	  private HibernateUtil() {
	  }

	  public static SessionFactory getSessionFactory() {
	    return sessionFactory;
	  }

	  public static Session getSession() throws HibernateException {
	    Session session = threadLocal.get();

	    if (session == null || !session.isOpen()) {
	      if (sessionFactory == null) {
	        rebuildSessionFactory();
	      }
	      session = (sessionFactory != null) ? sessionFactory.openSession() : null;
	      threadLocal.set(session);
	    }

	    return session;
	  }

	  public static void rebuildSessionFactory() {
		  synchronized(HibernateUtil.class) {
			    if (sessionFactory == null) {
			        rebuildSessionFactory();
			    }
			}
	  }

	  public static void closeSession() throws HibernateException {
	    Session session = (Session) threadLocal.get();
	    threadLocal.set(null);

	    if (session != null) {
	      session.close();
	    }
	  }
}

