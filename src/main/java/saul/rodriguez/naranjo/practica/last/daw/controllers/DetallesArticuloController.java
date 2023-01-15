package saul.rodriguez.naranjo.practica.last.daw.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import saul.rodriguez.naranjo.practica.last.daw.models.Articulo;
import saul.rodriguez.naranjo.practica.last.daw.persistence.dao.ArticuloDAO;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@WebServlet("/articulo")
public class DetallesArticuloController extends HttpServlet {
    
    private ArticuloDAO articuloDAO = new ArticuloDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        if(req.getParameter("idArticulo") != null) {
            
            long idArticulo = Long.parseLong(req.getParameter("idArticulo"));
            
            Articulo articulo = articuloDAO.findById(idArticulo);
            
            req.getSession().setAttribute("articulo", articulo);
            
            req.getRequestDispatcher("./jsp/detalles-articulo.jsp").forward(req, resp);
        }
    }
    
}
