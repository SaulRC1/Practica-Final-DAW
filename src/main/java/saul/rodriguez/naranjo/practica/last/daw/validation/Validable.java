/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package saul.rodriguez.naranjo.practica.last.daw.validation;

/**
 * Interfaz que debera implementar toda clase que necesite de una validacion
 * 
 * @author SaulRC1
 */
public interface Validable {
    
    /**
     * Valida los campos de la clase 
     * @return True - Si la validación es exitosa.<br>
     *         False - Si hay un error en la validación. 
     */
    public boolean validate();
    
}
