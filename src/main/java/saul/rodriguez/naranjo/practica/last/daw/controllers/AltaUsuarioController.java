package saul.rodriguez.naranjo.practica.last.daw.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import saul.rodriguez.naranjo.practica.last.daw.models.Usuario;
import saul.rodriguez.naranjo.practica.last.daw.models.error.processing.RegisterFormError;
import saul.rodriguez.naranjo.practica.last.daw.persistence.dao.UsuarioDAO;
import saul.rodriguez.naranjo.practica.last.daw.utils.configuration.ServerConfig;
import saul.rodriguez.naranjo.practica.last.daw.utils.security.PasswordHashing;
import saul.rodriguez.naranjo.practica.last.daw.utils.storage.UserDirectoryBuilder;

/**
 *
 * @author SaulRC1
 */
@WebServlet(urlPatterns = {"/alta-usuario"})
@MultipartConfig
public class AltaUsuarioController extends HttpServlet {

    private static final String EMAIL_ERROR_MESSAGE = "El email introducido no es válido";

    private static final String PASSWORD_ERROR_MESSAGE = "La contraseña y la contraseña repetida deben ser iguales,"
            + " y contener como mínimo 10 caractéres y máximo 40";

    private static final String CODIGO_POSTAL_ERROR_MESSAGE = "El codigo postal introducido no es valido";

    private static final String URL_ERROR_MESSAGE = "El enlace introducido parece no ser válido";

    private static final String TELEFONO_ERROR_MESSAGE = "El telefono introducido no es válido";

    private static final String IMAGE_ERROR_MESSAGE = "El archivo o imagen subida no es valido, "
            + "la imagen debe ser .png o .jpg/jpeg y no superar los 200 megabytes de tamaño";

    private static final String JPG_JPEG_MIME_TYPE = "image/jpeg";

    private static final String PNG_MIME_TYPE = "image/png";

    private static final int CONVERSION_RATE_MEGABYTES_TO_BYTES = 1024 * 1024;

    private static final int MAXIMUM_IMAGE_MB_SIZE = 200;
    
    private UserDirectoryBuilder userDirectoryBuilder = new UserDirectoryBuilder();
    
    private ServerConfig serverConfig = ServerConfig.getServerConfig();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Limpiara la sesion de los errores previos
        resetFormErrors(req.getSession());

        if (validateUser(req)) {

            Usuario usuario = buildUsuarioFromRequest(req);

        } else {
            resp.sendRedirect(req.getContextPath() + "/alta-usuario");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("./jsp/alta-usuario.jsp").forward(req, resp);
    }

    private boolean validateUser(HttpServletRequest request) {

        String correoElectronico = request.getParameter("correoElectronico");

        System.out.println("Correo Electronico: " + correoElectronico);

        if (!validarCorreoElectronico(correoElectronico)) {

            request.getSession().setAttribute("errorCorreoElectronico", EMAIL_ERROR_MESSAGE);

            return false;
        }

        String password = request.getParameter("clave");

        System.out.println("Password: " + password);

        String repetirPassword = request.getParameter("repetirClave");

        System.out.println("Repetir Password: " + repetirPassword);

        if (!validarPassword(password, repetirPassword)) {

            request.getSession().setAttribute("errorPassword", PASSWORD_ERROR_MESSAGE);

            return false;
        }

        String nombre = request.getParameter("nombre");

        System.out.println("Nombre: " + nombre);

        String codigoPostal = request.getParameter("codigoPostal");

        System.out.println("Codigo Postal: " + codigoPostal);

        if (!validarCodigoPostal(codigoPostal)) {

            request.getSession().setAttribute("errorCodigoPostal", CODIGO_POSTAL_ERROR_MESSAGE);

            return false;
        }

        String facebook = request.getParameter("facebook");

        System.out.println("Facebook: " + facebook);

        if (!validarURL(facebook)) {

            request.getSession().setAttribute("errorFacebook", URL_ERROR_MESSAGE);

            return false;
        }

        String twitter = request.getParameter("twitter");

        System.out.println("Twitter: " + twitter);

        if (!validarURL(twitter)) {

            request.getSession().setAttribute("errorTwitter", URL_ERROR_MESSAGE);

            return false;
        }

        String telefonoDeContacto = request.getParameter("telefono");

        System.out.println("Telefono: " + telefonoDeContacto);

        if (!validarTelefono(telefonoDeContacto)) {

            request.getSession().setAttribute("errorTelefono", TELEFONO_ERROR_MESSAGE);

            return false;
        }

        String prefijoTelefono = request.getParameter("prefijo-movil");

        System.out.println("Prefijo Telefono: " + prefijoTelefono);

        try {

            Part filePart = request.getPart("imagenDeUsuario");

            if (!validaImagen(filePart)) {

                request.getSession().setAttribute("errorImagen", IMAGE_ERROR_MESSAGE);

                return false;
            }

        } catch (IOException ex) {
            Logger.getLogger(AltaUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(AltaUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    private boolean validarCorreoElectronico(String correoElectronico) {

        if (correoElectronico == null || correoElectronico.trim().isEmpty()) {
            return false;
        }

        if (!Pattern.matches(Usuario.EMAIL_REGEX, correoElectronico)) {
            return false;
        }

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        if (usuarioDAO.findByCorreoElectronico(correoElectronico) != null) {
            return false;
        }

        return true;
    }

    private boolean validarPassword(String password, String repetirPassword) {

        if (password == null || password.trim().isEmpty()) {
            return false;
        }

        if (repetirPassword == null || repetirPassword.trim().isEmpty()) {
            return false;
        }

        if (!password.equals(repetirPassword)) {
            return false;
        }

        if (!Pattern.matches(Usuario.PASSWORD_REGEX, password)) {
            return false;
        }

        return true;
    }

    private boolean validarCodigoPostal(String codigoPostal) {

        if (codigoPostal == null || codigoPostal.trim().isEmpty()) {
            return false;
        }

        if (!Pattern.matches(Usuario.CODIGO_POSTAL_REGEX, codigoPostal)) {
            return false;
        }

        return true;

    }

    private boolean validarURL(String url) {

        if (url == null || url.trim().isEmpty()) {
            return true;
        }

        if (Pattern.matches(Usuario.URL_REGEX, url)) {
            return true;
        }

        return false;

    }

    private boolean validarTelefono(String telefono) {

        if (telefono == null || telefono.trim().isEmpty()) {
            return false;
        }

        if (!Pattern.matches(Usuario.TELEFONO_REGEX, telefono)) {
            return false;
        }

        return true;
    }

    private boolean validaImagen(Part filePart) {

        if (!filePart.getSubmittedFileName().isEmpty()) {
            String fileMimeType = filePart.getContentType();

            if (!fileMimeType.equals(JPG_JPEG_MIME_TYPE) && !fileMimeType.equals(PNG_MIME_TYPE)) {
                return false;
            }

            long fileSizeMegaBytes = filePart.getSize() / (CONVERSION_RATE_MEGABYTES_TO_BYTES);

            if (fileSizeMegaBytes > MAXIMUM_IMAGE_MB_SIZE) {
                return false;
            }
        }

        return true;
    }

    private void resetFormErrors(HttpSession session) {

        session.setAttribute("errorCorreoElectronico", null);

        session.setAttribute("errorPassword", null);

        session.setAttribute("errorCodigoPostal", null);

        session.setAttribute("errorFacebook", null);

        session.setAttribute("errorTwitter", null);

        session.setAttribute("errorTelefono", null);

        session.setAttribute("errorImagen", null);
    }

    private Usuario buildUsuarioFromRequest(HttpServletRequest request) {

        String correoElectronico = request.getParameter("correoElectronico");
        
        userDirectoryBuilder.createUserDirectory(correoElectronico);

        String password = request.getParameter("clave");
        
        String cipheredPassword = PasswordHashing.cipherPassword(password);

        String nombre = request.getParameter("nombre");

        String codigoPostal = request.getParameter("codigoPostal");

        String facebook = request.getParameter("facebook");

        String twitter = request.getParameter("twitter");

        String telefonoDeContacto = request.getParameter("telefono");

        String prefijoTelefono = request.getParameter("prefijo-movil");
        
        String direccion = request.getParameter("direccion");
        
        Usuario usuario = new Usuario();
        
        usuario.setCorreoElectronico(correoElectronico);
        
        usuario.setNombre(nombre);
        
        usuario.setClave(cipheredPassword);
        
        usuario.setCodigoPostal(codigoPostal);
        
        if(!facebook.trim().isEmpty()) {
            usuario.setFacebook(facebook);
        }
        
        if(!twitter.trim().isEmpty()) {
            usuario.setTwitter(twitter);
        }
        
        usuario.setTelefono(prefijoTelefono + " " + telefonoDeContacto);
        
        usuario.setDireccion(direccion);
        
        usuario.setRutaImagen(request.getContextPath() + "/images/usuarios/default.png");

        try {
            
            Part filePart = request.getPart("imagenDeUsuario");
            
            if(!filePart.getSubmittedFileName().isEmpty()) {
                
                //Por defecto sera un png
                String fileName = "profile.png";
                
                if(filePart.getContentType().equals(JPG_JPEG_MIME_TYPE)) {
                    fileName = "profile.jpg";
                }
                
                String fullImagePath = serverConfig.getRootDirectory() 
                        + correoElectronico + File.separator + fileName;
                
                userDirectoryBuilder.storeUserProfilePicture(fullImagePath, filePart);
                
                usuario.setRutaImagen(fullImagePath);
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(AltaUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(AltaUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
