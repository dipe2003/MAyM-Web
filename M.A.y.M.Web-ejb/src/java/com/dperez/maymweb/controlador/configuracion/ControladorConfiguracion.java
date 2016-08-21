/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.controlador.configuracion;

import com.dperez.maymweb.area.Area;
import com.dperez.maymweb.area.ManejadorArea;
import com.dperez.maymweb.codificacion.Codificacion;
import com.dperez.maymweb.codificacion.ManejadorCodificacion;
import javax.inject.Inject;

/**
 * Este controlador implementa los metodos necesarios para la creacion de los objetos que se modificarán como configuracion de la aplicacion.
 *
 * @author Diego
 */
public class ControladorConfiguracion {
    @Inject
    private ManejadorArea mArea;
    @Inject
    private ManejadorCodificacion mCodificacion;
    
    /***
     * Crea una nueva area/sector y la persiste en la base de datos.
     * @param NombreArea
     * @param CorreoArea
     * @return null si no se creo.
     */
    public Area NuevaArea(String NombreArea, String CorreoArea){
        Area area = new Area(NombreArea, CorreoArea);
        area.setId(mArea.CrearArea(area));
        if(area.getId()!=-1){
            return area;
        }else{
            return null;
        }
    }
    
    /***
     * Crea una nueva codificacion y la persiste en la base de datos.
     * @param NombreCodificacion
     * @return null si no se creo.
     */
    public Codificacion NuevaCodificacion(String NombreCodificacion){
        Codificacion codificacion = new Codificacion(NombreCodificacion);
        codificacion.setId(mCodificacion.CrearCodificacion(codificacion));
        if(codificacion.getId()!=-1){
            return codificacion;
        }else{
            return null;
        }
    }
}
