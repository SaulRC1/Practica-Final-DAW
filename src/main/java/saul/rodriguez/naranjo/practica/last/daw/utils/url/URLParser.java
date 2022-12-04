/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package saul.rodriguez.naranjo.practica.last.daw.utils.url;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
public class URLParser {
    
    /**
     * Devuelve el ultimo segmento de la url pasada por parametro.<br><br>
     * 
     * Por ejemplo, si se nos pasa /tienda/usuario/Manuel, el metodo nos devolvera
     * solo la seccion "Manuel";
     * 
     * @param url
     * @return El ultimo segmento.
     */
    public static String getLastURLSegment(String url) {
        
        String[] urlSegments = url.split("/");
        
        //Devolvemos el ultimo segmento tan solo
        return urlSegments[urlSegments.length - 1];
        
    }
    
    public static String getUsernameFromURL(String url) {
        
        String username = getLastURLSegment(url);
        
        if(username.contains("%20")) {
            
            String[] usernameParts = username.split("%20");
            
            String formattedUsername = "";
            
            for (int i = 0; i < usernameParts.length; i++) {
                formattedUsername += usernameParts[i] + " ";
            }
            
            try {
                return URLDecoder.decode(formattedUsername.trim(), "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(URLParser.class.getName()).log(Level.SEVERE, null, ex);
                return formattedUsername.trim();
            }
        }
        
        try {
            return URLDecoder.decode(username, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(URLParser.class.getName()).log(Level.SEVERE, null, ex);
            return username;
        }
        
    }
    
    public static String getMainURL(String url) {
        return null;
    }

}
