/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package saul.rodriguez.naranjo.practica.last.daw.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import saul.rodriguez.naranjo.practica.last.daw.models.Articulo;
import saul.rodriguez.naranjo.practica.last.daw.persistence.dao.ArticuloDAO;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@WebServlet(urlPatterns = {"/ver-articulos"})
public class VerArticulosController extends HttpServlet {
    
    private ArticuloDAO articuloDAO = new ArticuloDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        List<Articulo> listaDeArticulos = articuloDAO.findAll();
        
        req.getSession().setAttribute("listaArticulos", listaDeArticulos);
        
        for (Articulo articulo : listaDeArticulos) {
            System.out.println("Articulo: " + articulo.getNombre());
        }
        
        req.getRequestDispatcher("./jsp/ver-articulos.jsp").forward(req, resp);
    }

}
