/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package saul.rodriguez.naranjo.practica.last.daw.controllers.article;

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
import saul.rodriguez.naranjo.practica.last.daw.models.Articulo;
import saul.rodriguez.naranjo.practica.last.daw.models.Usuario;
import saul.rodriguez.naranjo.practica.last.daw.persistence.dao.ArticuloDAO;
import saul.rodriguez.naranjo.practica.last.daw.persistence.dao.UsuarioDAO;
import saul.rodriguez.naranjo.practica.last.daw.utils.configuration.ServerConfig;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@WebServlet("/imagen-articulo")
public class GetArticleImageController extends HttpServlet {

    private ArticuloDAO articuloDAO = new ArticuloDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Parametros: " + req.getParameter("id-articulo"));

        long idArticulo = Long.parseLong(req.getParameter("id-articulo"));

        Articulo articulo = articuloDAO.findById(idArticulo);

        if (articulo != null) {

            String extensionImagen = determinarMIMEImagen(articulo.getRutaImagen());

            resp.setContentType(extensionImagen);

            ServletOutputStream out = resp.getOutputStream();

            FileInputStream imagenDeArticuloStream = null;

            BufferedInputStream bin = null;
            
            BufferedOutputStream bout = null;

            try {

                imagenDeArticuloStream = new FileInputStream(articulo.getRutaImagen());

                bin = new BufferedInputStream(imagenDeArticuloStream);

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

                if(imagenDeArticuloStream != null) {
                    imagenDeArticuloStream.close();
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

    private String determinarMIMEImagen(String rutaImagen) {

        if (rutaImagen.contains(".jpg")) {
            return "image/jpeg";
        }

        return "image/png";
    }
}
