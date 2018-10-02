/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.acciones.filtros;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.area.Area;
import com.dperez.maymweb.codificacion.Codificacion;
import com.dperez.maymweb.deteccion.Deteccion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dipe2
 */

public class DatosFiltros implements Serializable {
    
    //  Metodos
    public List<Area> ExtraerAreas(List<Accion> acciones){
        List<Area> areas = new ArrayList<>();
        for(Accion accion:acciones){
            if(!areas.contains(accion.getAreaSectorAccion())){
                areas.add(accion.getAreaSectorAccion());
            }
        }
        return areas;
    }
    
    public List<Codificacion> ExtraerCodificaciones(List<Accion> acciones){
        List<Codificacion> codificaciones = new ArrayList<>();
        for(Accion accion:acciones){
            if(!codificaciones.contains(accion.getCodificacionAccion())){
                codificaciones.add(accion.getCodificacionAccion());
            }
        }
        return codificaciones;
    }
    
      public List<Deteccion> ExtraerDetecciones(List<Accion> acciones){
        List<Deteccion> detecciones = new ArrayList<>();
        for(Accion accion:acciones){
            if(!detecciones.contains(accion.getGeneradaPor())){
                detecciones.add(accion.getGeneradaPor());
            }
        }
        return detecciones;
    }
      
    public List<Accion> FiltrarAccionesPorArea(List<Accion> acciones, int idArea){
         List<Accion> accionesFiltradas = new ArrayList<>();
        for(Accion accion:acciones){
            if(accion.getAreaSectorAccion().getId()== idArea){
                accionesFiltradas.add(accion);
            }
        }
        return accionesFiltradas;
    }
    
}
