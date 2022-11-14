/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saul.rodriguez.naranjo.practica.last.daw.persistence;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.jboss.logging.Logger;

/**
 *
 * @author SaulRC1
 */
public class DataBaseSessionFactory {
    
    private SessionFactory sessionFactory;
    
    //Tan solo ha de existir uno por aplicacion
    private StandardServiceRegistry serviceRegistry;
    
    private static DataBaseSessionFactory dataBaseSessionFactory = new DataBaseSessionFactory();
    
    private DataBaseSessionFactory() {
        
        //creamos uno por aplicacion
        serviceRegistry = new StandardServiceRegistryBuilder()
                .configure().build();
        
        try {
            Logger.getLogger(DataBaseSessionFactory.class.getName())
                    .log(Logger.Level.INFO, "Setting up database session factory");
            
            sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
            
            Logger.getLogger(DataBaseSessionFactory.class.getName())
                    .log(Logger.Level.INFO, "Database session factory successfully created");
        } catch (Exception e) {
            
            StandardServiceRegistryBuilder.destroy( serviceRegistry );
            
        }
    
    }

    public synchronized SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public synchronized static DataBaseSessionFactory getDataBaseSessionFactoryInstance() {
        return dataBaseSessionFactory;
    }
    
    public void addAnnotatedClasses() {
        
    }
    
    /**
     * Destruye la session factory utilizada para las transacciones con la base
     * de datos.<br><br>
     * 
     * SOLO UTILIZAR CUANDO SE DESTRUYA EL CONTEXTO DE LA APLICACION WEB.
     */
    public boolean destroySessionFactory() {
        
        boolean sessionFactorySuccesfullyDestroyed = true;
        
        if(sessionFactory != null && !sessionFactory.isClosed())
        {
            try {
                
               sessionFactory.close();
               sessionFactory = null;
               StandardServiceRegistryBuilder.destroy( serviceRegistry );
               
            } catch (HibernateException e) {
                sessionFactorySuccesfullyDestroyed = false;
            }
                        
        }
        
        return sessionFactorySuccesfullyDestroyed;  
    }
    
}
