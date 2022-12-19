/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saul.rodriguez.naranjo.practica.last.daw.persistence.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import saul.rodriguez.naranjo.practica.last.daw.models.Usuario;
import saul.rodriguez.naranjo.practica.last.daw.persistence.DataBaseSessionFactory;

/**
 *
 * @author Saul Rodriguez Naranjo
 */
public class UsuarioDAO implements DataAccessObject<Usuario> {

    private SessionFactory sessionFactory;

    public UsuarioDAO() {
        DataBaseSessionFactory dataBaseSessionFactory = DataBaseSessionFactory.getDataBaseSessionFactoryInstance();
        this.sessionFactory = dataBaseSessionFactory.getSessionFactory();
    }

    @Override
    public Usuario findById(long identifier) {

        Usuario returnedUsuario = null;

        Session session = sessionFactory.openSession();

        String sqlQuery = "SELECT * FROM USUARIO WHERE id_usuario = :identifier";

        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();

            SQLQuery query = session.createSQLQuery(sqlQuery);
            query.setLong("identifier", identifier);
            query.addEntity(Usuario.class);

            returnedUsuario = (Usuario) query.list().get(0);

            transaction.commit();

        } catch (Exception e) {
            
            if(transaction != null) {
                transaction.rollback();
            }
            
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            
        } finally {
            
            session.close();
            
        }

        return returnedUsuario;
    }

    @Override
    public void save(Usuario usuario) {
        
        Session session = sessionFactory.openSession();
        
        Transaction transaction = null;
        
        try {
            
            transaction = session.beginTransaction();
            
            session.save(usuario);
            
            transaction.commit();
            
        } catch (Exception e) {
            
            if(transaction != null) {
                transaction.rollback();
            }
            
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            
        } finally {
            
            session.close();
            
        }
        
    }

    @Override
    public void update(Usuario usuario) {

        Session session = sessionFactory.openSession();
        
        Transaction transaction = null;
        
        try {
            
            transaction = session.beginTransaction();
            
            session.update(usuario);
            
            transaction.commit();
            
        } catch (Exception e) {
            
            if(transaction != null) {
                transaction.rollback();
            }
            
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            
        } finally {
            
            session.close();
            
        }
        
    }

    @Override
    public void delete(Usuario usuario) {

        Session session = sessionFactory.openSession();
        
        Transaction transaction = null;
        
        try {
            
            transaction = session.beginTransaction();
            
            session.delete(usuario);
            
            transaction.commit();
            
        } catch (Exception e) {
            
            if(transaction != null) {
                transaction.rollback();
            }
            
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            
        } finally {
            
            session.close();
            
        }
        
    }
    
    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        List<Usuario> listaDeUsuarios = usuarioDAO.findAll();
        
        Usuario usuario = usuarioDAO.findByCorreoElectronico("godot.cluster.blazar@gmail.com");
        
        System.out.println(usuario);
        
    }

    @Override
    public List<Usuario> findAll() {
        
        List<Usuario> listaDeUsuarios = null;

        Session session = sessionFactory.openSession();

        String sqlQuery = "SELECT * FROM USUARIO";

        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();

            SQLQuery query = session.createSQLQuery(sqlQuery);
            query.addEntity(Usuario.class);

            listaDeUsuarios = query.list();

            transaction.commit();

        } catch (Exception e) {
            
            if(transaction != null) {
                transaction.rollback();
            }
            
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            
        } finally {
            
            session.close();
            
        }

        return listaDeUsuarios;
        
    }
    
    
    public Usuario findByCorreoElectronico(String correoElectronico) {
        
        Usuario returnedUsuario = null;

        Session session = sessionFactory.openSession();

        String sqlQuery = "SELECT * FROM USUARIO WHERE correo_electronico = :correoElectronico";

        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();

            SQLQuery query = session.createSQLQuery(sqlQuery);
            query.setParameter("correoElectronico", correoElectronico);
            query.addEntity(Usuario.class);

            if(!query.list().isEmpty()) {
                returnedUsuario = (Usuario) query.list().get(0);
            }

            transaction.commit();

        } catch (Exception e) {
            
            if(transaction != null) {
                transaction.rollback();
            }
            
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            
        } finally {
            
            session.close();
            
        }

        return returnedUsuario;
        
    }
    
    public Usuario findByName(String name) {
        
        Usuario returnedUsuario = null;

        Session session = sessionFactory.openSession();

        String sqlQuery = "SELECT * FROM USUARIO WHERE nombre = :name";

        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();

            SQLQuery query = session.createSQLQuery(sqlQuery);
            query.setParameter("name", name);
            query.addEntity(Usuario.class);

            returnedUsuario = (Usuario) query.list().get(0);

            transaction.commit();

        } catch (Exception e) {
            
            if(transaction != null) {
                transaction.rollback();
            }
            
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            
        } finally {
            
            session.close();
            
        }

        return returnedUsuario;
        
    }

}
