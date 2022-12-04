package saul.rodriguez.naranjo.practica.last.daw.persistence.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import saul.rodriguez.naranjo.practica.last.daw.models.Articulo;
import saul.rodriguez.naranjo.practica.last.daw.models.Comentario;
import saul.rodriguez.naranjo.practica.last.daw.models.Comentario;
import saul.rodriguez.naranjo.practica.last.daw.models.Usuario;
import saul.rodriguez.naranjo.practica.last.daw.persistence.DataBaseSessionFactory;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
public class ComentarioDAO implements DataAccessObject<Comentario>{

    private SessionFactory sessionFactory;
    
    public ComentarioDAO() {
        sessionFactory = DataBaseSessionFactory.getDataBaseSessionFactoryInstance().getSessionFactory();
    }
    
    @Override
    public Comentario findById(long identifier) {
        Comentario comentario = null;

        Session session = sessionFactory.openSession();

        String sqlQuery = "SELECT * FROM COMENTARIO WHERE id_comentario = :identifier";

        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();

            SQLQuery query = session.createSQLQuery(sqlQuery);
            query.setLong("identifier", identifier);
            query.addEntity(Comentario.class);

            comentario = (Comentario) query.list().get(0);

            transaction.commit();

        } catch (Exception e) {
            
            if(transaction != null) {
                transaction.rollback();
            }
            
            Logger.getLogger(ComentarioDAO.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            
        } finally {
            
            session.close();
            
        }

        return comentario;
    }

    @Override
    public List<Comentario> findAll() {
        List<Comentario> listaDeComentarios = null;

        Session session = sessionFactory.openSession();

        String sqlQuery = "SELECT * FROM COMENTARIO";

        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();

            SQLQuery query = session.createSQLQuery(sqlQuery);
            query.addEntity(Comentario.class);

            listaDeComentarios = query.list();

            transaction.commit();

        } catch (Exception e) {
            
            if(transaction != null) {
                transaction.rollback();
            }
            
            Logger.getLogger(ComentarioDAO.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            
        } finally {
            
            session.close();
            
        }

        return listaDeComentarios;
    }
    
    /**
     * Encuentra todos los comentarios pertenecientes a un articulo en concreto
     * 
     * @param articulo Articulo sobre el que se quiere buscar los comentarios
     * @return Los comentarios pertenecientes a ese articulo
     */
    public List<Comentario> findByArticle(Articulo articulo) {
        
        if(articulo == null) {
            return null;
        }
        
        long idArticulo = articulo.getIdArticulo();
        
        List<Comentario> listaDeComentarios = null;

        Session session = sessionFactory.openSession();

        String sqlQuery = "SELECT * FROM COMENTARIO WHERE id_articulo = :identifier";

        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();

            SQLQuery query = session.createSQLQuery(sqlQuery);
            query.setLong("identifier", idArticulo);
            query.addEntity(Comentario.class);

            listaDeComentarios = query.list();

            transaction.commit();

        } catch (Exception e) {
            
            if(transaction != null) {
                transaction.rollback();
            }
            
            Logger.getLogger(ComentarioDAO.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            
        } finally {
            
            session.close();
            
        }

        return listaDeComentarios;
    }
    
    /**
     * Encuentra todos los comentarios pertenecientes a un usuario.
     * 
     * @param usuario Usuario sobre el que se requiere buscar sus comentarios
     * @return Comentarios pertenecientes al usuario dado.
     */
    public List<Comentario> findByUser(Usuario usuario) {
        
        if(usuario == null) {
            return null;
        }
        
        long idUsuario = usuario.getIdUsuario();
        
        List<Comentario> listaDeComentarios = null;

        Session session = sessionFactory.openSession();

        String sqlQuery = "SELECT * FROM COMENTARIO WHERE id_usuario = :identifier";

        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();

            SQLQuery query = session.createSQLQuery(sqlQuery);
            query.setLong("identifier", idUsuario);
            query.addEntity(Comentario.class);

            listaDeComentarios = query.list();

            transaction.commit();

        } catch (Exception e) {
            
            if(transaction != null) {
                transaction.rollback();
            }
            
            Logger.getLogger(ComentarioDAO.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            
        } finally {
            
            session.close();
            
        }

        return listaDeComentarios;
    }

    @Override
    public void save(Comentario comentario) {

        Session session = sessionFactory.openSession();

        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();

            session.save(comentario);

            transaction.commit();

        } catch (Exception e) {
            
            if(transaction != null) {
                transaction.rollback();
            }
            
            Logger.getLogger(ComentarioDAO.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            
        } finally {
            
            session.close();
            
        }
        
    }

    @Override
    public void update(Comentario comentario) {
        
        Session session = sessionFactory.openSession();

        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();

            session.update(comentario);

            transaction.commit();

        } catch (Exception e) {
            
            if(transaction != null) {
                transaction.rollback();
            }
            
            Logger.getLogger(ComentarioDAO.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            
        } finally {
            
            session.close();
            
        }
        
    }

    @Override
    public void delete(Comentario comentario) {
        
        Session session = sessionFactory.openSession();

        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();

            session.delete(comentario);

            transaction.commit();

        } catch (Exception e) {
            
            if(transaction != null) {
                transaction.rollback();
            }
            
            Logger.getLogger(ComentarioDAO.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            
        } finally {
            
            session.close();
            
        }
        
    }

}
