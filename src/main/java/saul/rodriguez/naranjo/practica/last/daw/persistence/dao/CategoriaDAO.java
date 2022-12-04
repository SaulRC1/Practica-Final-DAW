package saul.rodriguez.naranjo.practica.last.daw.persistence.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import saul.rodriguez.naranjo.practica.last.daw.models.Categoria;
import saul.rodriguez.naranjo.practica.last.daw.persistence.DataBaseSessionFactory;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
public class CategoriaDAO implements DataAccessObject<Categoria>{

    private SessionFactory sessionFactory;

    public CategoriaDAO() {
        
        sessionFactory = DataBaseSessionFactory.getDataBaseSessionFactoryInstance().getSessionFactory();
        
    }
    
    @Override
    public Categoria findById(long identifier) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Categoria> findAll() {
        List<Categoria> listaDeCategorias = null;

        Session session = sessionFactory.openSession();

        String sqlQuery = "SELECT * FROM CATEGORIA";

        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();

            SQLQuery query = session.createSQLQuery(sqlQuery);
            query.addEntity(Categoria.class);

            listaDeCategorias = query.list();

            transaction.commit();

        } catch (Exception e) {
            
            if(transaction != null) {
                transaction.rollback();
            }
            
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            
        } finally {
            
            session.close();
            
        }

        return listaDeCategorias;
    }

    @Override
    public void save(Categoria object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Categoria object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Categoria object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public static void main(String[] args) {
        
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        
        List<Categoria> categorias = categoriaDAO.findAll();
        
        for (Categoria categoria : categorias) {
            System.out.println(categoria);
        }
        
    }

}
