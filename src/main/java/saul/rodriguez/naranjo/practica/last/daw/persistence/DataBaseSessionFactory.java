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
import saul.rodriguez.naranjo.practica.last.daw.models.Articulo;
import saul.rodriguez.naranjo.practica.last.daw.models.Categoria;
import saul.rodriguez.naranjo.practica.last.daw.models.Comentario;
import saul.rodriguez.naranjo.practica.last.daw.models.Usuario;

/**
 *
 * @author Saul Rodriguez Naranjo
 */
public class DataBaseSessionFactory {

    //SessionFactory de donde sacaremos las sesiones para acceder a la base de datos
    private SessionFactory sessionFactory;

    //Tan solo ha de existir uno por aplicacion
    private StandardServiceRegistry serviceRegistry;

    //Se utiliza para configurar algunas propiedades adicionales de Hibernate
    private MetadataSources metadataSources;

    //sigue el patron singleton, sera el objeto de acceso al exterior
    private static DataBaseSessionFactory dataBaseSessionFactory = new DataBaseSessionFactory();

    private DataBaseSessionFactory() {

        //creamos uno por aplicacion
        serviceRegistry = new StandardServiceRegistryBuilder()
                .configure().build();

        //Inicializamos las MetadataSources para establecer la configuracion adicional
        initializeMetadataSources();

        addAnnotatedClasses();

        try {

            Logger.getLogger(DataBaseSessionFactory.class.getName())
                    .log(Logger.Level.INFO, "Setting up database session factory");

            sessionFactory = metadataSources.buildMetadata().buildSessionFactory();

            Logger.getLogger(DataBaseSessionFactory.class.getName())
                    .log(Logger.Level.INFO, "Database session factory successfully created");

        } catch (Exception e) {

            StandardServiceRegistryBuilder.destroy(serviceRegistry);
            
            System.out.println("Exception building session factory: " + e.getMessage());

        }

    }

    /**
     * Inicializa las MetadataSources en funcion de nuestro
     * StandardServiceRegistry.<br><br>
     *
     * Es importante que este metodo se ejecute antes de instanciar la
     * {@link SessionFactory}.
     */
    private void initializeMetadataSources() {
        metadataSources = new MetadataSources(serviceRegistry);
    }

    /**
     * Proporciona la {@link SessionFactory} ya inicializada para acceso directo
     * a la base de datos.
     *
     * @return la {@link SessionFactory} para acceder a la base de datos
     */
    public synchronized SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Proporciona la DataBaseSessionFactory, que es un objeto de acceso a
     * varias utilidades para maniobrar con Hibernate y la base de
     * datos.<br><br>
     *
     * Este objeto es unico por cada aplicación.
     *
     * @return La instancia de DataBaseSessionFactory
     */
    public synchronized static DataBaseSessionFactory getDataBaseSessionFactoryInstance() {
        return dataBaseSessionFactory;
    }

    public void addAnnotatedClasses() {
        //metadataSources.addPackage("saul.rodriguez.naranjo.practica.last.daw.models");
        metadataSources.addAnnotatedClass(Usuario.class);
        metadataSources.addAnnotatedClass(Articulo.class);
        metadataSources.addAnnotatedClass(Comentario.class);
        metadataSources.addAnnotatedClass(Categoria.class);
    }

    /**
     * Destruye la session factory utilizada para las transacciones con la base
     * de datos.<br><br>
     *
     * SOLO UTILIZAR CUANDO SE DESTRUYA EL CONTEXTO DE LA APLICACION WEB.
     */
    public boolean destroySessionFactory() {

        boolean sessionFactorySuccesfullyDestroyed = true;

        if (sessionFactory != null && !sessionFactory.isClosed()) {
            try {

                sessionFactory.close();
                sessionFactory = null;
                StandardServiceRegistryBuilder.destroy(serviceRegistry);

            } catch (HibernateException e) {
                sessionFactorySuccesfullyDestroyed = false;
            }

        }

        return sessionFactorySuccesfullyDestroyed;
    }

}
