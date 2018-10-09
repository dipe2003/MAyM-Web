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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author dipe2
 */

public class DatosFiltros implements Serializable {
    
    //**********************************************************************
    // Metodos de filtro de Areas
    //**********************************************************************
    
    /**
     * Devuelve una lista de areas que pertenezcan a las acciones.
     * @param acciones
     * @return
     */
    public Map<String, Area> ExtraerAreas(List<Accion> acciones){
        Map<String, Area> areas = new HashMap<>();
        for(Accion accion:acciones){
            if(!areas.containsKey(String.valueOf(accion.getAreaSectorAccion().getId()))){
                areas.put(String.valueOf(accion.getAreaSectorAccion().getId()), accion.getAreaSectorAccion());
            }
        }
        return new TreeMap<>(areas);
    }
    
    /**
     * Devuelve una lista de acciones que pertenezcan a las areas indicadas.
     * @param acciones
     * @param areas
     * @return
     */
    public List<Accion> FiltrarAccionesPorArea(List<Accion> acciones, List<Integer> areas){
        List<Accion> accionesFiltradas = new ArrayList<>();
        for(Accion accion:acciones){
            if(areas.contains(accion.getAreaSectorAccion().getId())){
                accionesFiltradas.add(accion);
            }
        }
        return accionesFiltradas;
    }
    
    //**********************************************************************
    // Metodos de filtro de Deteccion
    //**********************************************************************
    
    /**
     * Devuelve una lista de Detecciones que pertenezcan a las acciones.
     * @param acciones
     * @return
     */
    public Map<String, Deteccion> ExtraerDetecciones(List<Accion> acciones){
        Map<String, Deteccion> detecciones = new HashMap<>();
        for(Accion accion:acciones){
            if(!detecciones.containsKey(String.valueOf(accion.getGeneradaPor().getId()))){
                detecciones.put(String.valueOf(accion.getGeneradaPor().getId()), accion.getGeneradaPor());
            }
        }
        return new TreeMap<>(detecciones);
    }
    
    /**
     * Devuelve una lista de acciones que contengan la misma deteccion (generada por).
     * @param acciones
     * @param detecciones
     * @return
     */
    public List<Accion> FiltrarAccionesPorDeteccion(List<Accion> acciones, List<Integer> detecciones){
        List<Accion> accionesFiltradas = new ArrayList<>();
        for(Accion accion:acciones){
            if(detecciones.contains(accion.getGeneradaPor().getId())){
                accionesFiltradas.add(accion);
            }
        }
        return accionesFiltradas;
    }
    
    //**********************************************************************
    // Metodos de filtro de Codificacion
    //**********************************************************************
    
    /**
     * Devuelve una lista de codificaciones que pertenezcan a las acciones.
     * @param acciones
     * @return
     */
    public Map<String, Codificacion> ExtraerCodificaciones(List<Accion> acciones){
        Map<String, Codificacion> codificaciones = new HashMap<>();
        for(Accion accion:acciones){
            if(!codificaciones.containsKey(String.valueOf(accion.getCodificacionAccion().getId()))){
                codificaciones.put(String.valueOf(accion.getAreaSectorAccion().getId()), accion.getCodificacionAccion());
            }
        }
        return new TreeMap<>(codificaciones);
    }
    
}