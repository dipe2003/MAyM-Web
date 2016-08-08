/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dperez.maymweb.accion;

import com.dperez.maymweb.accion.acciones.Preventiva;
import java.util.Date;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Diego
 */
@Named
public class ControladorAccion {
    @Inject
    private ManejadorAccion mAccion;
    
    public Accion CrearAccionPreventiva(String Descripcion, Date FechaDeteccion, int IdArea){
        Accion AccionPreventiva = new Preventiva(Descripcion, FechaDeteccion);
        int id = mAccion.CrearAccion(AccionPreventiva);
        if( id !=-1){
            AccionPreventiva.setId(id);
            return AccionPreventiva;
        }
        return null;
    }    
    
}
