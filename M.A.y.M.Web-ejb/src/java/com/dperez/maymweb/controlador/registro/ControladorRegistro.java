/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.controlador.registro;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.accion.ManejadorAccion;
import com.dperez.maymweb.accion.acciones.Correctiva;
import com.dperez.maymweb.accion.acciones.EnumAccion;
import com.dperez.maymweb.accion.acciones.EnumTipoDesvio;
import com.dperez.maymweb.accion.acciones.Mejora;
import com.dperez.maymweb.accion.acciones.Preventiva;
import com.dperez.maymweb.area.Area;
import com.dperez.maymweb.area.ManejadorArea;
import com.dperez.maymweb.codificacion.Codificacion;
import com.dperez.maymweb.codificacion.ManejadorCodificacion;
import com.dperez.maymweb.deteccion.Deteccion;
import com.dperez.maymweb.deteccion.ManejadorDeteccion;
import com.dperez.maymweb.estado.ManejadorEstado;
import java.util.Date;
import javax.inject.Inject;

/**
 *
 * @author Diego
 */
public class ControladorRegistro {
    @Inject
    private ManejadorAccion mAccion;
    @Inject
    private ManejadorArea mArea;
    @Inject
    private ManejadorEstado mEstado;
    @Inject
    private ManejadorCodificacion mCodificacion;
    @Inject
    private ManejadorDeteccion mDeteccion;
    
    /**
     * Crea una nueva accion y la persiste en la base de datos.
     * @param TipoAccion
     * @param FechaDeteccion
     * @param Descripcion
     * @param TipoDesvio Null cuando no corresponde
     * @param IdAreaSector
     * @param IdDeteccion
     * @param IdCodificacion
     * @return Null: si no se creo.
     */
    public Accion NuevaAccionCorrectiva(EnumAccion TipoAccion, Date FechaDeteccion, String Descripcion, EnumTipoDesvio TipoDesvio, 
            int IdAreaSector, int IdDeteccion, int IdCodificacion){
        Accion accion = null;
        switch(TipoAccion){
            case CORRECTIVA:
                accion = new Correctiva(FechaDeteccion, Descripcion, TipoDesvio);
                break;
                
            case PREVENTIVA:
                accion = new Preventiva(FechaDeteccion, Descripcion);
                break;
                
            case MEJORA:
                accion = new Mejora(FechaDeteccion, Descripcion);
                break;                
        }
        if(accion!=null){
            Area area = mArea.GetArea(IdAreaSector);
            accion.setAreaSectorAccion(area);
            Deteccion deteccion = mDeteccion.GetDeteccion(IdDeteccion);
            accion.setGeneradaPor(deteccion);
            Codificacion codificacion = mCodificacion.GetCodificacion(IdCodificacion);
            accion.setCodificacionAccion(codificacion);
            // Estado Pendiente
            accion.setId(mAccion.CrearAccion(accion));
        }
        if(accion.getId()!=-1){
            return accion;
        }else{
            return null;
        }
    }
    
}
