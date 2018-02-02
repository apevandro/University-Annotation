package br.com.university.utils;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final String PATH = "src/main/resources/hibernate.cfg.xml";

	private static File configurationFile;
	
	private static SessionFactory sessionFactory = null;

    private HibernateUtil() {}

    public static synchronized SessionFactory getSessionFactory() {

	    if (sessionFactory == null) {
	    	configurationFile = new File(PATH);
		    Configuration configuration = new Configuration().configure(configurationFile);
	        sessionFactory = configuration.buildSessionFactory();
        }

        return sessionFactory;
   }

}