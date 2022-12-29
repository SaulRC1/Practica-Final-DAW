package saul.rodriguez.naranjo.practica.last.daw.controllers.user.profile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import saul.rodriguez.naranjo.practica.last.daw.models.Usuario;
import saul.rodriguez.naranjo.practica.last.daw.persistence.dao.UsuarioDAO;
import saul.rodriguez.naranjo.practica.last.daw.utils.configuration.ServerConfig;

/**
 * Servlet para uso exclusivo de dispensar imagenes de perfil
 *
 * @author Saúl Rodríguez Naranjo
 */
@WebServlet(urlPatterns = {"/imagenes"})
public class GetUserProfilePictureController extends HttpServlet {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    private ServerConfig serverConfig = ServerConfig.getServerConfig();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Parametros: " + req.getParameter("usuario"));

        String nombreDeUsuario = req.getParameter("usuario");

        //Obtenemos el usuario pasado por parametro
        Usuario usuario = usuarioDAO.findByName(nombreDeUsuario);

        if (usuario != null) {

            String extensionImagen = determinarMIMEImagenDePerfil(usuario.getRutaImagen());

            resp.setContentType(extensionImagen);

            ServletOutputStream out = resp.getOutputStream();

            FileInputStream imagenDePerfilStream = null;

            BufferedInputStream bin = null;
            
            BufferedOutputStream bout = null;

            try {

                imagenDePerfilStream = new FileInputStream(usuario.getRutaImagen());

                bin = new BufferedInputStream(imagenDePerfilStream);

                bout = new BufferedOutputStream(out);

                int ch = 0;

                while ((ch = bin.read()) != -1) {
                    bout.write(ch);
                }

            } catch (Exception e) {

            } finally {

                if(bin != null) {
                    bin.close();
                }

                if(imagenDePerfilStream != null) {
                    imagenDePerfilStream.close();
                }
                
                if(bout != null) {
                    bout.close();
                }
                
                if(out != null) {
                    out.close();
                }
                
            }

        }

    }

    private String determinarMIMEImagenDePerfil(String rutaImagen) {

        if (rutaImagen.contains(".jpg")) {
            return "image/jpeg";
        }

        return "image/png";
    }

}
