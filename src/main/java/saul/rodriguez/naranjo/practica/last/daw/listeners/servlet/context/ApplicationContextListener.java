package saul.rodriguez.naranjo.practica.last.daw.listeners.servlet.context;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.jboss.logging.Logger;
import saul.rodriguez.naranjo.practica.last.daw.persistence.DataBaseSessionFactory;

/**
 * Esta clase se encargara de escuchar cuando la aplicacion se inicializa o se cierra
 * dentro del servidor de aplicaciones.
 * 
 * @author SaulRC1
 */
@WebListener
public class ApplicationContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
        destroyDataBaseSessionFactory();
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Logger.getLogger(ApplicationContextListener.class.getName())
                .log(Logger.Level.INFO, "Context Initialized - Application Context Listener");
    }
    
    /**
     * Destruye la session factory utilizada para acceder a la base de datos
     */
    private void destroyDataBaseSessionFactory() {
        
        DataBaseSessionFactory dataBaseSessionFactory 
                = DataBaseSessionFactory.getDataBaseSessionFactoryInstance();
        
        Logger.getLogger(ApplicationContextListener.class.getName())
                .log(Logger.Level.INFO, "Destruyendo session factory de la base de datos...");
        
        if(dataBaseSessionFactory.destroySessionFactory()) {
            Logger.getLogger(ApplicationContextListener.class.getName())
                .log(Logger.Level.INFO, "Session Factory destruida correctamente");
        } else {
            Logger.getLogger(ApplicationContextListener.class.getName())
                .log(Logger.Level.ERROR, "Error al destruir Session Factory");
        }
        
        
    }
    
}
