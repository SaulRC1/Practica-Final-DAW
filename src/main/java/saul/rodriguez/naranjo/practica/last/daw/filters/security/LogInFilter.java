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
import saul.rodriguez.naranjo.practica.last.daw.models.Usuario;

/**
 * Este filtro prohibe el acceso al formulario de inicio de sesion, si el usuario
 * ya ha iniciado sesion satisfactoriamente.
 * 
 * @author Saúl Rodríguez Naranjo
 */
@WebFilter(urlPatterns = {"/iniciar-sesion"})
public class LogInFilter implements Filter{

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) sr;
        HttpServletResponse response = (HttpServletResponse) sr1;
        
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        
        if(usuario != null) {
            
            response.sendRedirect(request.getContextPath() + "/inicio");
            return;
            
        }
        
        fc.doFilter(sr, sr1);
    }

}
