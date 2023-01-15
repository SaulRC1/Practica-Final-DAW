package saul.rodriguez.naranjo.practica.last.daw.web.services;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import saul.rodriguez.naranjo.practica.last.daw.models.Usuario;
import saul.rodriguez.naranjo.practica.last.daw.persistence.dao.UsuarioDAO;

/**
 * Esta clase se encarga de comprobar con la base de datos que exista un email o
 * no ya registrado.
 * 
 * Esta destinada a su uso mediante peticion AJAX
 *
 * @author Saúl Rodríguez Naranjo
 */
@WebServlet("/check-email")
public class CheckEmailService extends HttpServlet {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");

        resp.setCharacterEncoding("UTF-8");

        String correoElectronico = req.getParameter("correoElectronico");

        if (correoElectronico != null) {
            
            JSONObject correoElectronicoJson = new JSONObject();

            Usuario usuario = usuarioDAO.findByCorreoElectronico(correoElectronico);
        
            if(usuario != null) {
                correoElectronicoJson.put("correoElectronico", usuario.getCorreoElectronico());
                
                resp.getWriter().print(correoElectronicoJson);
                resp.getWriter().flush();
            }
            
        }

    }

}
