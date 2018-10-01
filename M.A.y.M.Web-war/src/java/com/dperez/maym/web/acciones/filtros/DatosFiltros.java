/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package com.dperez.maym.web.acciones.filtros;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.area.Area;
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

}
