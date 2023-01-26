/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package saul.rodriguez.naranjo.practica.last.daw.web.services;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import saul.rodriguez.naranjo.practica.last.daw.models.Articulo;
import saul.rodriguez.naranjo.practica.last.daw.models.Comentario;
import saul.rodriguez.naranjo.practica.last.daw.models.Usuario;
import saul.rodriguez.naranjo.practica.last.daw.persistence.dao.ArticuloDAO;
import saul.rodriguez.naranjo.practica.last.daw.persistence.dao.ComentarioDAO;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@WebServlet("/publicar-comentario")
public class PublicarComentarioService extends HttpServlet {
    
    private ArticuloDAO articuloDAO = new ArticuloDAO();
    
    private ComentarioDAO comentarioDAO = new ComentarioDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
        
        long idArticulo = Long.parseLong(req.getParameter("idArticulo"));
        
        Articulo articulo = articuloDAO.findById(idArticulo);
        
        String textoComentario = req.getParameter("publicar-comentario-text");
        
        String visibilidad = req.getParameter("publicar-comentario-visibilidad");
        
        Comentario comentario = new Comentario();
        
        comentario.setTextoComentario(textoComentario);
        comentario.setVisibilidad(visibilidad);
        comentario.setUsuario(usuario);
        comentario.setArticulo(articulo);
        
        comentarioDAO.save(comentario);
        
        String urlRedireccion = req.getParameter("urlActual");
        
        resp.sendRedirect(req.getContextPath() + urlRedireccion);
    }
    
    
    
}
