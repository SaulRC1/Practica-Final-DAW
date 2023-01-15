/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package saul.rodriguez.naranjo.practica.last.daw.controllers.user.profile;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import saul.rodriguez.naranjo.practica.last.daw.models.Articulo;
import saul.rodriguez.naranjo.practica.last.daw.models.Usuario;
import saul.rodriguez.naranjo.practica.last.daw.persistence.dao.ArticuloDAO;
import saul.rodriguez.naranjo.practica.last.daw.persistence.dao.UsuarioDAO;
import saul.rodriguez.naranjo.practica.last.daw.utils.url.URLParser;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@WebServlet(urlPatterns = {"/usuario/publico/*"})
public class PublicUserProfileController extends HttpServlet {
    
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    private ArticuloDAO articuloDAO = new ArticuloDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String requestURL = req.getRequestURI();
        
        String nombreDeUsuario = URLParser.getUsernameFromURL(requestURL);
        
        System.out.println("Nombre de Usuario: " + nombreDeUsuario);
        
        Usuario usuarioPublico = usuarioDAO.findByName(nombreDeUsuario);
        
        if(usuarioPublico != null) {
            req.getSession().setAttribute("usuarioPublico", usuarioPublico);
            
            List<Articulo> listaArticulos = articuloDAO.findByUser(usuarioPublico);
            
            if(listaArticulos != null) {
                
                req.getSession().setAttribute("listaArticulos", listaArticulos);
                
            } else {
                
                req.getSession().setAttribute("listaArticulos", new ArrayList<Articulo>());
                
            }
            
            req.getRequestDispatcher("/jsp/usuario/perfil/perfil-usuario-publico.jsp").forward(req, resp);
        }
        
        
    }

    
    
}
