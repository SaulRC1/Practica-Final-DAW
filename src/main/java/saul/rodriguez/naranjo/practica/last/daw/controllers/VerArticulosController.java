/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package saul.rodriguez.naranjo.practica.last.daw.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import saul.rodriguez.naranjo.practica.last.daw.models.Articulo;
import saul.rodriguez.naranjo.practica.last.daw.models.Categoria;
import saul.rodriguez.naranjo.practica.last.daw.persistence.dao.ArticuloDAO;
import saul.rodriguez.naranjo.practica.last.daw.persistence.dao.CategoriaDAO;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@WebServlet(urlPatterns = {"/ver-articulos"})
public class VerArticulosController extends HttpServlet {

    private ArticuloDAO articuloDAO = new ArticuloDAO();

    private CategoriaDAO categoriaDAO = new CategoriaDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Categoria> listaCategorias = categoriaDAO.findAll();

        req.getSession().setAttribute("listaCategorias", listaCategorias);

        List<Articulo> listaDeArticulos = queryGetRequest(req);

        req.getSession().setAttribute("listaArticulos", listaDeArticulos);

        for (Articulo articulo : listaDeArticulos) {
            System.out.println("Articulo: " + articulo.getNombre());
        }

        req.getRequestDispatcher("./jsp/ver-articulos.jsp").forward(req, resp);
    }

    private List<Articulo> queryGetRequest(HttpServletRequest request) {
        
        if(!existeQuery(request)) {
            return articuloDAO.findAll();
        }

        String sqlQuery = "";

        String codigoPostal = request.getParameter("codigoPostal");

        System.out.println("Codigo Postal: " + codigoPostal);

        if (codigoPostal == null) {
            sqlQuery = construirQueryCodigoPostalNulo("SELECT * FROM ARTICULO WHERE ", request);
        } else {
            sqlQuery = "SELECT ARTICULO.id_articulo, ARTICULO.id_usuario, ARTICULO.id_categoria, ARTICULO.nombre, ARTICULO.descripcion, "
                    + "ARTICULO.estado, ARTICULO.anio_adquisicion, ARTICULO.precio_venta, ARTICULO.ruta_imagen, ARTICULO.fecha_de_publicacion "
                    + "FROM ARTICULO INNER JOIN usuario ON usuario.id_usuario = ARTICULO.id_usuario WHERE usuario.codigo_postal like '" + codigoPostal + "'";
            
            sqlQuery = construirQueryCodigoPostalValido(sqlQuery, request);
        }
        
        System.out.println("SQL QUERY: " + sqlQuery);

        List<Articulo> articulos = articuloDAO.findByQuery(sqlQuery);
        
        if(articulos != null) {
            return articulos;
        }
        
        return new ArrayList<Articulo>(); 
    }
    
    private String construirQueryCodigoPostalNulo(String sqlQueryStart, HttpServletRequest request) {
        
        String sqlQuery = sqlQueryStart;
        
        long idCategoria;

        if (request.getParameter("idCategoria") != null) {

            idCategoria = Long.parseLong(request.getParameter("idCategoria"));
            System.out.println("Id Categoria: " + idCategoria);
            
            sqlQuery += "ARTICULO.id_categoria = " + idCategoria;
        }

        int precioMinimo;

        if (request.getParameter("precioMinimo") != null) {

            precioMinimo = Integer.parseInt(request.getParameter("precioMinimo"));

            System.out.println("Precio Minimo: " + precioMinimo);          
                
            if(request.getParameter("idCategoria") != null) {
                sqlQuery += " AND ARTICULO.precio_venta >= " + precioMinimo;
            } else {
                sqlQuery += " ARTICULO.precio_venta >= " + precioMinimo;
            }
            
            
        }

        int precioMaximo;

        if (request.getParameter("precioMaximo") != null) {

            precioMaximo = Integer.parseInt(request.getParameter("precioMaximo"));

            System.out.println("Precio Maximo: " + precioMaximo);
            
            if(request.getParameter("precioMinimo") != null || request.getParameter("idCategoria") != null) {
                
                sqlQuery += " AND ARTICULO.precio_venta <= " + precioMaximo;
                
            } else {
                
                sqlQuery += " ARTICULO.precio_venta <= " + precioMaximo;
                
            }
        }
        
        return sqlQuery;
    }
    
    private String construirQueryCodigoPostalValido(String sqlQuery, HttpServletRequest request) {
        long idCategoria;

        if (request.getParameter("idCategoria") != null) {

            idCategoria = Long.parseLong(request.getParameter("idCategoria"));
            System.out.println("Id Categoria: " + idCategoria);
            
            sqlQuery += " AND ARTICULO.id_categoria = " + idCategoria;
        }

        int precioMinimo;

        if (request.getParameter("precioMinimo") != null) {

            precioMinimo = Integer.parseInt(request.getParameter("precioMinimo"));

            System.out.println("Precio Minimo: " + precioMinimo);
            
            sqlQuery += " AND ARTICULO.precio_venta >= " + precioMinimo;
        }

        int precioMaximo;

        if (request.getParameter("precioMaximo") != null) {

            precioMaximo = Integer.parseInt(request.getParameter("precioMaximo"));

            System.out.println("Precio Maximo: " + precioMaximo);
            
            if(precioMaximo > 0) {
                sqlQuery += "AND ARTICULO.precio_venta <= " + precioMaximo;
            }
        }
        
        return sqlQuery;
    }

    private boolean existeQuery(HttpServletRequest request) {
        
        String codigoPostal = request.getParameter("codigoPostal");
        
        String categoria = request.getParameter("idCategoria");
        
        String precioMinimo = request.getParameter("precioMinimo");
        
        String precioMaximo = request.getParameter("precioMaximo");
        
        if(codigoPostal == null && categoria == null && precioMinimo == null 
                && precioMaximo == null) {
            return false;
        }
        
        return true;
    }

}
