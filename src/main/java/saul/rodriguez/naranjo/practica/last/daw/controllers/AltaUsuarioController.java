/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saul.rodriguez.naranjo.practica.last.daw.controllers;

import java.io.IOException;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import saul.rodriguez.naranjo.practica.last.daw.models.Usuario;
import saul.rodriguez.naranjo.practica.last.daw.persistence.dao.UsuarioDAO;

/**
 *
 * @author SaulRC1
 */
@WebServlet(urlPatterns = {"/alta-usuario"})
@MultipartConfig
public class AltaUsuarioController extends HttpServlet {
   
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        validateUser(req);
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("./jsp/alta-usuario.jsp").forward(req, resp);
    }
    
    private boolean validateUser(HttpServletRequest request) {
        
        String correoElectronico = request.getParameter("correoElectronico");
        
        System.out.println("Correo Electronico: " + correoElectronico);
        
        if(!validarCorreoElectronico(correoElectronico)) {
            return false;
        }
        
        String password = request.getParameter("clave");
        
        System.out.println("Password: " + password);
        
        String repetirPassword = request.getParameter("repetirClave");
        
        System.out.println("Repetir Password: " + repetirPassword);
        
        if(!validarPassword(password, repetirPassword)) {
            return false;
        }
        
        String nombre = request.getParameter("nombre");
        
        System.out.println("Nombre: " + nombre);
        
        String codigoPostal = request.getParameter("codigoPostal");
        
        System.out.println("Codigo Postal: " + codigoPostal);
        
        if(!validarCodigoPostal(codigoPostal)) {
            return false;
        }
        
        String facebook = request.getParameter("facebook");
        
        System.out.println("Facebook: " + facebook);
        
        if(!validarURL(facebook)) {
            return false;
        }
        
        String twitter = request.getParameter("twitter");
        
        System.out.println("Twitter: " + twitter);
        
        if(!validarURL(twitter)) {
            return false;
        }
        
        String telefonoDeContacto = request.getParameter("telefono");
        
        System.out.println("Telefono: " + telefonoDeContacto);
        
        String prefijoTelefono = request.getParameter("prefijo-movil");
        
        System.out.println("Prefijo Telefono: " + prefijoTelefono);
        
        return true;
    }
    
    private boolean validarCorreoElectronico(String correoElectronico) {
        
        if(correoElectronico == null || correoElectronico.trim().isEmpty()) {
            return false;
        }
        
        if(!Pattern.matches(Usuario.EMAIL_REGEX, correoElectronico)) {
            return false;
        }
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        if(usuarioDAO.findByCorreoElectronico(correoElectronico) != null) {
            return false;
        }
        
        return true;
    }
    
    private boolean validarPassword(String password, String repetirPassword) {
        
        if(password == null || password.trim().isEmpty()) {
            return false;
        }
        
        if(repetirPassword == null || repetirPassword.trim().isEmpty()) {
            return false;
        }
        
        if(!password.equals(repetirPassword)) {
            return false;
        }
        
        if(!Pattern.matches(Usuario.PASSWORD_REGEX, password)) {
            return false;
        }
        
        return true;
    }
    
    private boolean validarCodigoPostal(String codigoPostal) {
        
        if(codigoPostal == null || codigoPostal.trim().isEmpty()) {
            return false;
        }
        
        if(!Pattern.matches(Usuario.CODIGO_POSTAL_REGEX, codigoPostal)) {
            return false;
        }
        
        return true;
        
    }
    
    private boolean validarURL(String url) {
        
        if(url == null || url.trim().isEmpty()) {
            return true;
        }
        
        if(Pattern.matches(Usuario.URL_REGEX, url)) {
            return true;
        }
        
        return false;
        
    }
    
    private boolean validarTelefono(String telefono) {
        
        return true;
    }
    
    
    
}
