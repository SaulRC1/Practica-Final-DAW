package saul.rodriguez.naranjo.practica.last.daw.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Saúl Rodríguez Naranjo
 */
@Entity
@Table(name = "CATEGORIA")
public class Categoria {

    @Id
    @Column(name = "id_categoria")
    private long idCategoria;
    
    @Column(name = "nombre_categoria")
    private String nombreCategoria;

    public Categoria() {
    }

    public long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (int) (this.idCategoria ^ (this.idCategoria >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Categoria other = (Categoria) obj;
        return this.idCategoria == other.idCategoria;
    }

    @Override
    public String toString() {
        return "Categoria{" + "idCategoria=" + idCategoria + ", nombreCategoria=" + nombreCategoria + '}';
    }
    
    
}
