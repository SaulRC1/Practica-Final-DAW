/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saul.rodriguez.naranjo.practica.last.daw.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SaulRC1
 */
@WebServlet(urlPatterns = {"/alta-usuario"})
@MultipartConfig
public class AltaUsuarioController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String correoElectronico = req.getParameter("correoElectronico");
        
        String password;
        
        String repetirPassword;
        
        String nombre;
        
        String codigoPostal;
        
        String facebook;
        
        String twitter;
        
        String telefonoDeContacto;
        
        System.out.println("Correo Electronico: " + correoElectronico);
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("./jsp/alta-usuario.jsp").forward(req, resp);
    }
    
    public boolean validateUser(HttpServletRequest request) {
        return false;
    }
    
    
    
}
