/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package saul.rodriguez.naranjo.practica.last.daw.filters.security;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import saul.rodriguez.naranjo.practica.last.daw.models.Usuario;
import saul.rodriguez.naranjo.practica.last.daw.utils.url.URLParser;

/**
 * Este filtro determinara si se muestra un perfil public o privado
 * 
 * @author Saúl Rodríguez Naranjo
 */
@WebFilter(urlPatterns = {"/usuario/privado/*"})
public class PrivateUserProfileFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        
        HttpSession session = servletRequest.getSession();
        
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        
        String requestURL = servletRequest.getRequestURI();
        
        String requestUserProfile = URLParser.getLastURLSegment(requestURL);
        
        if(usuario == null) {
            servletResponse.sendRedirect(servletRequest.getContextPath() + "/usuario/publico/" + requestUserProfile);
        }
        
        //String requestURL = servletRequest.getRequestURI();
        
        //System.out.println("Usuario en la request: " + URLParser.getLastURLSegment(requestURL));
    }

}
