/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.controlador.registro;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.accion.ManejadorAccion;
import com.dperez.maymweb.accion.acciones.EnumAccion;
import com.dperez.maymweb.estado.EnumEstado;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Diego
 */
public class ControladorVistaRegistros {
    @Inject
    private ManejadorAccion mAccion;
    
    /**
     * Devuelve una lista con todas las acciones registradas que se encuentran en el estado especificado.
     * Si EstadoAccion es null se devuelven todas las acciones registradas.
     * @param EstadoAccion
     * @return
     */
    public List<Accion> ListarAccionesSegunEstado(EnumEstado EstadoAccion){
        List<Accion> acciones = new ArrayList<>();
        acciones = mAccion.ListarAcciones();
        if(EstadoAccion!=null){
            Iterator it = acciones.iterator();
            while(it.hasNext()){
                if(((Accion)it.next()).getEstadoAccion() != EstadoAccion)
                    it.remove();
            }
        }
        return acciones;
    }
    
    /**
     * Devuelve una lista con todas las acciones del tipo especificado registradas que se encuentran en el estado indicado.
     * Si EstadoAccion es null se devuelven todas las acciones registradas del tipo indicado.
     * Si EstadoAccion es null y TipoAccion es null se devuelven todas las accines registradas.
     * @param EstadoAccion
     * @param TipoAccion
     * @return
     */
    public List<Accion> ListarAccionesSegunEstado(EnumEstado EstadoAccion, EnumAccion TipoAccion){
        List<Accion> acciones = new ArrayList<>();
        acciones = mAccion.ListarAcciones();
        if(EstadoAccion!=null && TipoAccion !=null){
            Iterator it = acciones.iterator();
            while(it.hasNext()){
                if(((Accion)it.next()).getEstadoAccion() != EstadoAccion && !((Accion)it.next()).getClass().getName().equals(TipoAccion.toString()))
                    it.remove();
            }
        }else{
            if(EstadoAccion == null && TipoAccion != null){
                Iterator it = acciones.iterator();
                while(it.hasNext()){
                    if(!((Accion)it.next()).getClass().getName().equals(TipoAccion.toString()))
                        it.remove();
                }
            }
        }
        return acciones;
    }
    
}
