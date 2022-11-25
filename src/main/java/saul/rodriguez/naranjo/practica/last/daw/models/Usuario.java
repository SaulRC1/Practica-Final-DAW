package saul.rodriguez.naranjo.practica.last.daw.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Usuario de la aplicación.
 * 
 * @author Saul Rodriguez Naranjo
 */
@Entity
@Table(name = "USUARIO")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_usuario")
    private long idUsuario;
    
    @Column(name = "correo_electronico")
    private String correoElectronico;
    
    @Column(name = "clave")
    private String clave;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "direccion")
    private String direccion;
    
    @Column(name = "codigo_postal")
    private String codigoPostal;
    
    @Column(name = "facebook")
    private String facebook;
    
    @Column(name = "twitter")
    private String twitter;
    
    @Column(name = "telefono")
    private String telefono;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,
               fetch = FetchType.LAZY)
    private List<Comentario> comentariosPublicados;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,
               fetch = FetchType.LAZY)
    private List<Articulo> articulosPublicados;
    
    public Usuario() {
        
    }

    public Usuario(String correoElectronico, String clave, String nombre, 
                   String codigoPostal, String telefono) {
        this.correoElectronico = correoElectronico;
        this.clave = clave;
        this.nombre = nombre;
        this.codigoPostal = codigoPostal;
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public List<Comentario> getComentariosPublicados() {
        return comentariosPublicados;
    }

    public List<Articulo> getArticulosPublicados() {
        return articulosPublicados;
    }
    
    public void addComentarioPublicado(Comentario comentario) {
        
        if(comentariosPublicados != null) {
            
            comentariosPublicados.add(comentario);
            
        }  else {
            
            comentariosPublicados = new ArrayList<>();
            
            comentariosPublicados.add(comentario);
            
        }
        
    }
    
    public void addArticuloPublicado(Articulo articulo) {
        
        if(comentariosPublicados != null) {
            
            articulosPublicados.add(articulo);
            
        }  else {
            
            articulosPublicados = new ArrayList<>();
            
            articulosPublicados.add(articulo);
            
        }
        
    }
}
