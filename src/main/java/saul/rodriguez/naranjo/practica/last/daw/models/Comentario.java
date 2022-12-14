/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saul.rodriguez.naranjo.practica.last.daw.models;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author SaulRC1
 */
@Entity
@Table(name = "COMENTARIO")
public class Comentario {
    
    public static final String VISIBILIDAD_PUBLICA = "PUBLICO";
    
    public static final String VISIBILIDAD_VENDEDOR = "VENDEDOR";
    
    public static final String VISIBILIDAD_PRIVADA = "PRIVADO";
    
    @Id
    @SequenceGenerator(allocationSize = 1, initialValue = 100, name = "comentario_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator="comentario_sequence")
    @Column(name = "id_comentario")
    private long idComentario;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name = "fk_usuario"))
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "id_articulo", foreignKey = @ForeignKey(name = "fk_articulo"))
    private Articulo articulo;
    
    @Column(name = "texto_comentario")
    private String textoComentario;
    
    @Column(name = "visibilidad")
    private String visibilidad;

    public Comentario() {
    }

    public long getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(long idComentario) {
        this.idComentario = idComentario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public String getTextoComentario() {
        return textoComentario;
    }

    public void setTextoComentario(String textoComentario) {
        this.textoComentario = textoComentario;
    }

    public String getVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(String visibilidad) {
        this.visibilidad = visibilidad;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (int) (this.idComentario ^ (this.idComentario >>> 32));
        hash = 47 * hash + Objects.hashCode(this.usuario);
        hash = 47 * hash + Objects.hashCode(this.articulo);
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
        final Comentario other = (Comentario) obj;
        if (this.idComentario != other.idComentario) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        return Objects.equals(this.articulo, other.articulo);
    }
    
    
}
