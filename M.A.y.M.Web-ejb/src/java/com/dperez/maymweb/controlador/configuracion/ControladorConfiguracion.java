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
import com.dperez.maymweb.deteccion.Deteccion;
import com.dperez.maymweb.deteccion.TipoDeteccion;
import com.dperez.maymweb.deteccion.ManejadorDeteccion;
import com.dperez.maymweb.deteccion.ManejadorTipoDeteccion;
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
    @Inject
    private ManejadorDeteccion mDeteccion;
    @Inject
    private ManejadorTipoDeteccion mTipoDeteccion;
    
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
    /***
     * Crea una nueva deteccion y la persiste en la base de datos.
     * @param NombreDeteccion
     * @param tipoDeteccion
     * @return null si no se creo.
     */
    public Deteccion NuevaDeteccion(String NombreDeteccion, TipoDeteccion tipoDeteccion){
        Deteccion deteccion = new Deteccion(NombreDeteccion, tipoDeteccion);
        deteccion.setId(mDeteccion.CrearDeteccion(deteccion));
        if(deteccion.getId()!=-1){
            return deteccion;
        }else{
            return null;
        }
    }
    
    /***
     * Crea una nueva deteccion y la persiste en la base de datos.
     * @param NombreTipoDeteccion
     * @param DescripcionTipo
     * @return null si no se creo.
     */
    public TipoDeteccion NuevoTipoDeteccion(String NombreTipoDeteccion, String DescripcionTipo){
        TipoDeteccion tipoDeteccion = new TipoDeteccion(NombreTipoDeteccion, DescripcionTipo);
        tipoDeteccion.setId(mTipoDeteccion.CrearTipoDeteccion(tipoDeteccion));
        if(tipoDeteccion.getId()!=-1){
            return tipoDeteccion;
        }else{
            return null;
        }
    }
}
