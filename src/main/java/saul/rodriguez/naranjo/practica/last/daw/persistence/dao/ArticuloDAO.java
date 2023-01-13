package saul.rodriguez.naranjo.practica.last.daw.persistence.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import saul.rodriguez.naranjo.practica.last.daw.models.Articulo;
import saul.rodriguez.naranjo.practica.last.daw.models.Usuario;
import saul.rodriguez.naranjo.practica.last.daw.persistence.DataBaseSessionFactory;

/**
 *
 * @author Saul Rodriguez Naranjo
 */
public class ArticuloDAO implements DataAccessObject<Articulo>{

    private final SessionFactory sessionFactory;
    
    public ArticuloDAO() {
        sessionFactory = DataBaseSessionFactory.getDataBaseSessionFactoryInstance().getSessionFactory();
    }
    
    @Override
    public Articulo findById(long identifier) {
        Articulo articulo = null;

        Session session = sessionFactory.openSession();

        String sqlQuery = "SELECT * FROM ARTICULO WHERE id_articulo = :identifier";

        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();

            SQLQuery query = session.createSQLQuery(sqlQuery);
            query.setLong("identifier", identifier);
            query.addEntity(Articulo.class);

            articulo = (Articulo) query.list().get(0);

            transaction.commit();

        } catch (Exception e) {
            
            if(transaction != null) {
                transaction.rollback();
            }
            
            Logger.getLogger(ArticuloDAO.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            
        } finally {
            
            session.close();
            
        }

        return articulo;
    }
    
    public List<Articulo> findByUser(Usuario usuario) {
        
        if(usuario == null) {
            return null;
        }
        
        long idUsuario = usuario.getIdUsuario();
        
        List<Articulo> listaDeArticulos = null;
        
        Session session = sessionFactory.openSession();

        String sqlQuery = "SELECT * FROM ARTICULO WHERE id_usuario = :identifier";

        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();

            SQLQuery query = session.createSQLQuery(sqlQuery);
            query.setLong("identifier", idUsuario);
            query.addEntity(Articulo.class);

            listaDeArticulos = query.list();

            transaction.commit();

        } catch (Exception e) {
            
            if(transaction != null) {
                transaction.rollback();
            }
            
            Logger.getLogger(ArticuloDAO.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            
        } finally {
            
            session.close();
            
        }
        
        return listaDeArticulos;
        
    }

    @Override
    public List<Articulo> findAll() {
        
        List<Articulo> listaDeArticulos = null;
        
        Session session = sessionFactory.openSession();

        String sqlQuery = "SELECT * FROM ARTICULO";

        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();

            SQLQuery query = session.createSQLQuery(sqlQuery);
            query.addEntity(Articulo.class);

            listaDeArticulos = query.list();

            transaction.commit();

        } catch (Exception e) {
            
            if(transaction != null) {
                transaction.rollback();
            }
            
            Logger.getLogger(ArticuloDAO.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            
        } finally {
            
            session.close();
            
        }
        
        return listaDeArticulos;
        
    }

    @Override
    public void save(Articulo articulo) {
        
        Session session = sessionFactory.openSession();
        
        Transaction transaction = null;
        
        try {
            
            transaction = session.beginTransaction();
            
            session.save(articulo);
            
            transaction.commit();
            
        } catch (Exception e) {
            
            if(transaction != null) {
                transaction.rollback();
            }
            
            Logger.getLogger(ArticuloDAO.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            
        } finally {
            
            session.close();
            
        }
        
    }

    @Override
    public void update(Articulo articulo) {
        
        Session session = sessionFactory.openSession();
        
        Transaction transaction = null;
        
        try {
            
            transaction = session.beginTransaction();
            
            session.update(articulo);
            
            transaction.commit();
            
        } catch (Exception e) {
            
            if(transaction != null) {
                transaction.rollback();
            }
            
            Logger.getLogger(ArticuloDAO.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            
        } finally {
            
            session.close();
            
        }
        
    }

    @Override
    public void delete(Articulo articulo) {
        
        Session session = sessionFactory.openSession();
        
        Transaction transaction = null;
        
        try {
            
            transaction = session.beginTransaction();
            
            session.delete(articulo);
            
            transaction.commit();
            
        } catch (Exception e) {
            
            if(transaction != null) {
                transaction.rollback();
            }
            
            Logger.getLogger(ArticuloDAO.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            
        } finally {
            
            session.close();
            
        }
        
    }
    
    public List<Articulo> findByQuery(String customQuery) {
        List<Articulo> articulos = null;

        Session session = sessionFactory.openSession();

        String sqlQuery = customQuery;

        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();

            SQLQuery query = session.createSQLQuery(sqlQuery);
            
            query.addEntity(Articulo.class);

            articulos = query.list();

            transaction.commit();

        } catch (Exception e) {
            
            if(transaction != null) {
                transaction.rollback();
            }
            
            Logger.getLogger(ArticuloDAO.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            
        } finally {
            
            session.close();
            
        }

        return articulos;
    }
    
    public List<Articulo> findLatestArticles() {
        
        List<Articulo> listaDeArticulos = null;
        
        Session session = sessionFactory.openSession();

        String sqlQuery = "SELECT * FROM ARTICULO ORDER BY fecha_de_publicacion DESC LIMIT 10";

        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();

            SQLQuery query = session.createSQLQuery(sqlQuery);
            query.addEntity(Articulo.class);

            listaDeArticulos = query.list();

            transaction.commit();

        } catch (Exception e) {
            
            if(transaction != null) {
                transaction.rollback();
            }
            
            Logger.getLogger(ArticuloDAO.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            
        } finally {
            
            session.close();
            
        }
        
        return listaDeArticulos;
    }
    
    public static void main(String[] args) {
        
        ArticuloDAO articuloDAO = new ArticuloDAO();
        
        Articulo articulo = articuloDAO.findById(1);
        
        //System.out.println("Fecha: " + articulo.getFechaDePublicacion().getDayOfMonth() + "/" + articulo.getFechaDePublicacion().getMonthValue() + "/" + articulo.getFechaDePublicacion().getYear());
        
    }
    
}
