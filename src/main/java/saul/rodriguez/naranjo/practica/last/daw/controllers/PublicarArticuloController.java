package saul.rodriguez.naranjo.practica.last.daw.controllers;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import saul.rodriguez.naranjo.practica.last.daw.models.Articulo;
import saul.rodriguez.naranjo.practica.last.daw.models.Categoria;
import saul.rodriguez.naranjo.practica.last.daw.models.Usuario;
import saul.rodriguez.naranjo.practica.last.daw.persistence.dao.ArticuloDAO;
import saul.rodriguez.naranjo.practica.last.daw.persistence.dao.CategoriaDAO;
import saul.rodriguez.naranjo.practica.last.daw.utils.configuration.ServerConfig;
import saul.rodriguez.naranjo.practica.last.daw.utils.storage.UserDirectoryBuilder;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@WebServlet(urlPatterns = {"/publicar-articulo"})
@MultipartConfig
public class PublicarArticuloController extends HttpServlet {
    
    private CategoriaDAO categoriaDAO = new CategoriaDAO();
    
    private ArticuloDAO articuloDAO = new ArticuloDAO();
    
    private ServerConfig serverConfig = ServerConfig.getServerConfig();
    
    private UserDirectoryBuilder userDirectoryBuilder = new UserDirectoryBuilder();
    
    private static final String JPG_JPEG_MIME_TYPE = "image/jpeg";

    private static final String PNG_MIME_TYPE = "image/png";

    private static final int CONVERSION_RATE_MEGABYTES_TO_BYTES = 1024 * 1024;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        resetFormErrors(req.getSession());
        
        if(validateArticle(req)) {
            
            Articulo articulo = buildArticuloFromRequest(req);
            
            articuloDAO.save(articulo);
            
            resp.sendRedirect(req.getContextPath() + "/ver-articulos");
            
        } else {
            resp.sendRedirect(req.getContextPath() + "/publicar-articulo");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        List<Categoria> listaCategorias = categoriaDAO.findAll();
        
        req.getSession().setAttribute("listaCategorias", listaCategorias);
        
        req.getRequestDispatcher("./jsp/publicar-articulo.jsp").forward(req, resp);
    }
    
    private Articulo buildArticuloFromRequest(HttpServletRequest request) {
        
        Articulo articulo = new Articulo();
        
        Usuario usuarioQuePublicaElArticulo = (Usuario) request.getSession()
                .getAttribute("usuario");
        
        articulo.setUsuario(usuarioQuePublicaElArticulo);
        
        String nombreArticulo = request.getParameter("nombre-articulo");
        
        articulo.setNombre(nombreArticulo);
        
        String anioAdquisicion = request.getParameter("year-adquisicion");
        
        articulo.setAnioAdquisicion(anioAdquisicion);
        
        String estado = request.getParameter("estado");
        
        articulo.setEstado(estado);
        
        int idCategoria = Integer.parseInt(request.getParameter("categoria"));
        
        Categoria categoria = categoriaDAO.findById(idCategoria);
        
        articulo.setCategoria(categoria);
        
        float precioVenta = Float.parseFloat(request.getParameter("precio-venta"));
        
        articulo.setPrecioVenta(precioVenta);
        
        String descripcion = request.getParameter("descripcion");
        
        articulo.setDescripcion(descripcion);
        
        LocalDate fechaDePublicacion = LocalDate.now();
        
        articulo.setFechaDePublicacion(fechaDePublicacion);
        
        try {

            Part filePart = request.getPart("imagen-articulo");
            
            //Por defecto sera un png
                String fileName = nombreArticulo + ".png";
                
                if(filePart.getContentType().equals(JPG_JPEG_MIME_TYPE)) {
                    fileName = nombreArticulo + ".jpg";
                }
                
                String fullImagePath = serverConfig.getRootDirectory() 
                        + usuarioQuePublicaElArticulo.getCorreoElectronico() + File.separator + fileName;
                
                userDirectoryBuilder.storeArticleImage(fullImagePath, filePart);
                
                articulo.setRutaImagen(fullImagePath);
                
        } catch (IOException ex) {
            Logger.getLogger(PublicarArticuloController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } catch (ServletException ex) {
            Logger.getLogger(PublicarArticuloController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        
        return articulo;
    }
    
    private boolean validateArticle(HttpServletRequest request) {
        
        String nombreArticulo = request.getParameter("nombre-articulo");
        
        System.out.println("Nombre Articulo: " + nombreArticulo);
        
        String anioAdquisicion = request.getParameter("year-adquisicion");
        
        if(!validarAnioAdquisicion(anioAdquisicion)) {
            
            request.getSession().setAttribute("errorAnioAdquisicion",
                    "El año de adquisicion introducido no es válido");
            return false;
        }
        
        System.out.println("Año adquisicion: " + anioAdquisicion);
        
        String estado = request.getParameter("estado");
        
        System.out.println("Estado: " + estado);
        
        int idCategoria = Integer.parseInt(request.getParameter("categoria"));
        
        System.out.println("ID Categoria: " + idCategoria);
        
        //Categoria categoria = categoriaDAO.findById(idCategoria);
        
        String precioVenta = request.getParameter("precio-venta");
        
        System.out.println("Precio de venta: " + precioVenta);
        
        if(!validarPrecioVenta(precioVenta)) {
            
            request.getSession().setAttribute("errorPrecioVenta", "El precio introducido no es válido");
            
            return false;
        }
        
        String descripcion = request.getParameter("descripcion");
        
        System.out.println("Descripcion: " + descripcion);
        
        try {

            Part filePart = request.getPart("imagen-articulo");
            
            System.out.println("Imagen: " + filePart.getSubmittedFileName());

            if (!validarImagenDeArticulo(filePart)) {

                request.getSession().setAttribute("errorImagen", 
                        "LA IMAGEN INTRODUCIDA NO ES VÁLIDA, EL TAMAÑO MÁXIMO ACPETADO ES 200MB Y SOLO SE ADMITEN FORMATOS .PNG/.JPG/.JPEG");
                
                return false;
            }

        } catch (IOException ex) {
            Logger.getLogger(PublicarArticuloController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } catch (ServletException ex) {
            Logger.getLogger(PublicarArticuloController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        
        return true;
    }

    private boolean validarAnioAdquisicion(String anioAdquisicion) {
        if (anioAdquisicion == null || anioAdquisicion.trim().isEmpty()) {
            return false;
        }

        if (!Pattern.matches(Articulo.YEAR_ADQUISICION_REGEX, anioAdquisicion)) {
            return false;
        }

        return true;
    }

    private boolean validarPrecioVenta(String precioVenta) {
        
        if(precioVenta == null || precioVenta.trim().isEmpty()) {
            return false;
        }
        
        if(!Pattern.matches(Articulo.PRECIO_VENTA_REGEX, precioVenta)) {
            return false;
        }
        
        return true;
    }
    
    private boolean validarImagenDeArticulo(Part imagen) {
        if (!imagen.getSubmittedFileName().isEmpty()) {
            String fileMimeType = imagen.getContentType();

            if (!fileMimeType.equals(JPG_JPEG_MIME_TYPE) && !fileMimeType.equals(PNG_MIME_TYPE)) {
                return false;
            }

            long fileSizeMegaBytes = imagen.getSize() / (CONVERSION_RATE_MEGABYTES_TO_BYTES);

            if (fileSizeMegaBytes > Articulo.MAXIMUM_PROFILE_PICTURE_SIZE_MB) {
                return false;
            }
        }

        if(imagen.getSubmittedFileName().isEmpty()) {
            return false;
        }
        
        return true;
    }
    
    private void resetFormErrors(HttpSession session) {

        session.setAttribute("errorImagen", null);

        session.setAttribute("errorPrecioVenta", null);
        
        session.setAttribute("errorAnioAdquisicion", null);
    }
}
