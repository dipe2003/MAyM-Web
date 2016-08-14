/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.accion.acciones;

/**
 *
 * @author dperez
 */
public enum EnumAccion {
    CORRECTIVA ("Correctiva"),
    PREVENTIVA ("Preventiva"),
    MEJORA ("Mejora");
    
    private final String Descripcion;  
     
    EnumAccion(String descripcion){
        this.Descripcion = descripcion;
    }
    
}
