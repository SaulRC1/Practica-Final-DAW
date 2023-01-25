package saul.rodriguez.naranjo.practica.last.daw.controllers;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import saul.rodriguez.naranjo.practica.last.daw.models.Articulo;
import saul.rodriguez.naranjo.practica.last.daw.models.Usuario;
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

        if (req.getParameter("idArticulo") != null) {

            long idArticulo = Long.parseLong(req.getParameter("idArticulo"));

            Articulo articulo = articuloDAO.findById(idArticulo);

            req.getSession().setAttribute("articulo", articulo);

            req.getRequestDispatcher("./jsp/detalles-articulo.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");

        if (usuario != null) {
            long idArticuloFavorito = Long.parseLong(req.getParameter("idArticuloFavorito"));
            
            System.out.println("ID Articulo: " + idArticuloFavorito);
            
            Articulo articuloFavorito = articuloDAO.findById(idArticuloFavorito);

            ArrayList<Articulo> articulosFavoritos = (ArrayList<Articulo>) req.getSession().getAttribute("articulosFavoritos");

            if (articulosFavoritos == null) {

                articulosFavoritos = new ArrayList<>();
                
                req.getSession().setAttribute("articulosFavoritos", articulosFavoritos);
                
            }
            
            if(!articulosFavoritos.contains(articuloFavorito)) {
                articulosFavoritos.add(articuloFavorito);
            } 
        }

    }

}
