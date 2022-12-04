package saul.rodriguez.naranjo.practica.last.daw.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Saul Rodriguez Naranjo
 */
@Entity
@Table(name = "ARTICULO")
public class Articulo {
    
    public static final String ESTADO_NUEVO = "NUEVO";
    
    public static final String ESTADO_SEMINUEVO = "SEMINUEVO";
    
    public static final String ESTADO_DETERIORADO = "DETERIORADO";
    
    public static final String ESTADO_ANTIGUO = "ANTIGUO";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_articulo")
    private long idArticulo;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario", foreignKey = @ForeignKey(name = "fk_usuario"))
    private Usuario usuario;
    
    @OneToOne
    @JoinColumn(name = "id_categoria", foreignKey = @ForeignKey(name = "fk_categoria"))
    private Categoria categoria;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "estado")
    private String estado;
    
    @Column(name = "anio_adquisicion")
    private String anioAdquisicion;
    
    @Column(name = "ruta_imagen")
    private String rutaImagen;
    
    @Column(name = "precio_venta")
    private float precioVenta;
    
    /*
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,
               fetch = FetchType.LAZY)
    private List<Comentario> comentariosDelArticulo;*/

    public Articulo() {
    }

    public long getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(long idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getAnioAdquisicion() {
        return anioAdquisicion;
    }

    public void setAnioAdquisicion(String anioAdquisicion) {
        this.anioAdquisicion = anioAdquisicion;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    /*
    public List<Comentario> getComentariosDelArticulo() {
        return comentariosDelArticulo;
    }
    
    public void addComentarioAlArticulo(Comentario comentario) {
        if(comentariosDelArticulo != null) {
            this.comentariosDelArticulo.add(comentario);
        } else {
            this.comentariosDelArticulo = new ArrayList<>();
            this.comentariosDelArticulo.add(comentario);
        }
        
    }*/

    @Override
    public String toString() {
        return "Articulo{" + "idArticulo=" + idArticulo + ", usuario=" + usuario + ", categoria=" + categoria + ", nombre=" + nombre + ", descripcion=" + descripcion + ", estado=" + estado + ", anioAdquisicion=" + anioAdquisicion + ", rutaImagen=" + rutaImagen + ", precioVenta=" + precioVenta + '}';
    }
    
}
