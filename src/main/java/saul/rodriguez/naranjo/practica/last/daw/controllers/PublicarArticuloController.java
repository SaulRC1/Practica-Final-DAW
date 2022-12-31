package saul.rodriguez.naranjo.practica.last.daw.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import saul.rodriguez.naranjo.practica.last.daw.models.Categoria;
import saul.rodriguez.naranjo.practica.last.daw.persistence.dao.CategoriaDAO;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@WebServlet(urlPatterns = {"/publicar-articulo"})
@MultipartConfig
public class PublicarArticuloController extends HttpServlet {
    
    private CategoriaDAO categoriaDAO = new CategoriaDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        validateArticle(req);
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        List<Categoria> listaCategorias = categoriaDAO.findAll();
        
        req.getSession().setAttribute("listaCategorias", listaCategorias);
        
        req.getRequestDispatcher("./jsp/publicar-articulo.jsp").forward(req, resp);
    }
    
    private boolean validateArticle(HttpServletRequest request) {
        
        String nombreArticulo = request.getParameter("nombre-articulo");
        
        System.out.println("Nombre Articulo: " + nombreArticulo);
        
        String anioAdquisicion = request.getParameter("year-adquisicion");
        
        System.out.println("Año adquisicion: " + anioAdquisicion);
        
        String estado = request.getParameter("estado");
        
        System.out.println("Estado: " + estado);
        
        int idCategoria = Integer.parseInt(request.getParameter("categoria"));
        
        System.out.println("ID Categoria: " + idCategoria);
        
        String precioVenta = request.getParameter("precio-venta");
        
        System.out.println("Precio de venta: " + precioVenta);
        
        String descripcion = request.getParameter("");
        
        System.out.println("Descripcion: " + descripcion);
        
        return true;
    }
    
}
