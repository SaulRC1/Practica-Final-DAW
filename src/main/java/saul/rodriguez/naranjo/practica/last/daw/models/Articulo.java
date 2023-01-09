package saul.rodriguez.naranjo.practica.last.daw.models;

import java.time.LocalDate;
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
import javax.persistence.PostLoad;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import saul.rodriguez.naranjo.practica.last.daw.utils.configuration.ServerConfig;

/**
 *
 * @author Saul Rodriguez Naranjo
 */
@Entity
@Table(name = "ARTICULO")
public class Articulo {
    
    @Transient
    public static final String ESTADO_NUEVO = "NUEVO";
    
    @Transient
    public static final String ESTADO_SEMINUEVO = "SEMINUEVO";
    
    @Transient
    public static final String ESTADO_DETERIORADO = "DETERIORADO";
    
    @Transient
    public static final String ESTADO_ANTIGUO = "ANTIGUO";
    
    @Transient
    public static final String YEAR_ADQUISICION_REGEX = "^\\d{4}$";
    
    @Transient
    public static final String PRECIO_VENTA_REGEX = "^\\d{1,4}(\\.\\d{1,2})?$";
    
    @Transient
    public static final int MAXIMUM_PROFILE_PICTURE_SIZE_MB = 200;
    
    @Id
    @SequenceGenerator(allocationSize = 1, initialValue = 100, name = "articulo_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator="articulo_sequence")
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
    
    @Column(name = "fecha_de_publicacion", columnDefinition = "DATE")
    private LocalDate fechaDePublicacion;
    
    @Transient
    private boolean tieneImagenDeArticulo;
    
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

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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
        return "Articulo{" + "idArticulo=" + idArticulo + ", usuario=" + usuario.getNombre() + ", categoria=" + categoria.getNombreCategoria() + ", nombre=" + nombre + ", descripcion=" + descripcion + ", estado=" + estado + ", anioAdquisicion=" + anioAdquisicion + ", rutaImagen=" + rutaImagen + ", precioVenta=" + precioVenta + ", fechaDePublicacion=" + fechaDePublicacion + '}';
    }

    public LocalDate getFechaDePublicacion() {
        return fechaDePublicacion;
    }

    public void setFechaDePublicacion(LocalDate fechaDePublicacion) {
        this.fechaDePublicacion = fechaDePublicacion;
    }

    public boolean isTieneImagenDeArticulo() {
        return tieneImagenDeArticulo;
    }

    public void setTieneImagenDeArticulo(boolean tieneImagenDeArticulo) {
        this.tieneImagenDeArticulo = tieneImagenDeArticulo;
    }

    @PostLoad
    public void determinarSiTieneImagenDeArticulo() {
        //Obtenemos el directorio raiz de la app
        String rootDirectory = ServerConfig.getServerConfig().getRootDirectory();
        
        if(this.rutaImagen.contains(rootDirectory)) {
            this.tieneImagenDeArticulo = true;
        } else {
            this.tieneImagenDeArticulo = false;
        }
    }
}
