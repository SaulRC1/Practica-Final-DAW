/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saul.rodriguez.naranjo.practica.last.daw.controllers.user.authentication;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import saul.rodriguez.naranjo.practica.last.daw.models.Usuario;
import saul.rodriguez.naranjo.practica.last.daw.persistence.dao.UsuarioDAO;
import saul.rodriguez.naranjo.practica.last.daw.utils.security.PasswordHashing;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@WebServlet(urlPatterns = {"/iniciar-sesion"})
public class IniciarSesionController extends HttpServlet {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String correoElectronico = req.getParameter("email");

        String clave = req.getParameter("clave");

        Usuario usuario = usuarioDAO.findByCorreoElectronico(correoElectronico);

        if (usuario != null) {

            boolean coincideClave = PasswordHashing.matchesPassword(clave, usuario.getClave());

            if (coincideClave) {
                
                req.getSession().setAttribute("usuario", usuario);
                
                resp.sendRedirect(req.getContextPath() + "/inicio");
                
            } else {
                req.getSession().setAttribute("error", "Las credenciales son incorrectas");

                resp.sendRedirect(req.getContextPath() + "/iniciar-sesion");
            }

        } else {

            req.getSession().setAttribute("error", "El usuario no existe");

            resp.sendRedirect(req.getContextPath() + "/iniciar-sesion");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("./jsp/iniciar-sesion.jsp").forward(req, resp);
    }

}
