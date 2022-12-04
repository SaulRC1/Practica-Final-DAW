package saul.rodriguez.naranjo.practica.last.daw.persistence.dao;

import java.util.List;

/**
 * Interfaz que recoge todos los metodos que debera implementar como minimo un
 * DataAccessObject.
 * 
 * @author Saul Rodriguez Naranjo
 * @param <T> El tipo de objeto con el que se trabajará
 */
public interface DataAccessObject<T> {
    
    /**
     * Encuentra el objeto deseado en funcion de su ID
     * 
     * @param identifier Clave Primaria en la BD
     * @return El objeto que tenga esa ID
     */
    public T findById(long identifier);
    
    /**
     * Devuelve todos los elementos de la base de datos
     * 
     * @return una lista con todos los elementos
     */
    public List<T> findAll();
    
    /**
     * Guarda el objeto en la base de datos.
     * 
     * @param object El objeto que se requiere guardar
     */
    public void save(T object);
    
    /**
     * Actualiza el objeto pasado por parametro en la base de datos
     * 
     * @param object El objeto que se requiere actualizar.
     */
    public void update(T object);
    
    /**
     * Borra el objeto pasado por parametro en la base de datos
     * 
     * @param object El objeto que se requiere borrar
     */
    public void delete(T object);
    
}
