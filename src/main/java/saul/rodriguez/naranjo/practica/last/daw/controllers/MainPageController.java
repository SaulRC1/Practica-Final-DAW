package saul.rodriguez.naranjo.practica.last.daw.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import saul.rodriguez.naranjo.practica.last.daw.models.Articulo;
import saul.rodriguez.naranjo.practica.last.daw.persistence.dao.ArticuloDAO;

/**
 *
 * @author SaulRC1
 */
@WebServlet(urlPatterns = {"/inicio"})
public class MainPageController extends HttpServlet {
    
    private ArticuloDAO articuloDAO = new ArticuloDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        List<Articulo> listaArticulos = articuloDAO.findLatestArticles();
        
        req.getSession().setAttribute("listaArticulos", listaArticulos);
        
        req.getRequestDispatcher("./jsp/main-page.jsp").forward(req, resp);
        
    }
    
}
