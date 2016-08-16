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
import com.dperez.maymweb.accion.medida.ManejadorMedida;
import com.dperez.maymweb.accion.medida.Medida;
import com.dperez.maymweb.accion.medida.medidas.ActividadMejora;
import com.dperez.maymweb.accion.medida.medidas.ActividadPreventiva;
import com.dperez.maymweb.accion.medida.medidas.MedidaCorrectiva;
import com.dperez.maymweb.accion.medida.medidas.MedidaPreventiva;
import com.dperez.maymweb.area.Area;
import com.dperez.maymweb.area.ManejadorArea;
import com.dperez.maymweb.codificacion.Codificacion;
import com.dperez.maymweb.codificacion.ManejadorCodificacion;
import com.dperez.maymweb.deteccion.Deteccion;
import com.dperez.maymweb.deteccion.ManejadorDeteccion;
import java.security.InvalidParameterException;
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
    private ManejadorCodificacion mCodificacion;
    @Inject
    private ManejadorDeteccion mDeteccion;
    @Inject
    private ManejadorMedida mMedida;
    
    /**
     * Crea una nueva accion en estado pendiente y la persiste en la base de datos.
     * @param TipoAccion
     * @param FechaDeteccion
     * @param Descripcion
     * @param TipoDesvio Null cuando no corresponde
     * @param IdAreaSector
     * @param IdDeteccion
     * @param IdCodificacion
     * @return Null: si no se creo.
     */
    public Accion NuevaAccion(EnumAccion TipoAccion, Date FechaDeteccion, String Descripcion, EnumTipoDesvio TipoDesvio, 
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
            accion.setId(mAccion.CrearAccion(accion));
        }
        if(accion.getId()!=-1){
            return accion;
        }else{
            return null;
        }
    }
    
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
    
    /**
     * Crea una nueva actividad de mejora, la persiste en la base de datos y se asocia a la mejora.
     * @param IdAccion
     * @param FechaEstimadaImplementacion
     * @param Descripcion
     * @return -1 si no se creo.
     */
    public int AgregarActividadMejora(int IdAccion, Date FechaEstimadaImplementacion, String Descripcion ){
        Medida medida = new ActividadMejora(FechaEstimadaImplementacion, Descripcion);
        medida.setId(mMedida.CrearMedida(medida));
        Accion accion = mAccion.GetAccion(IdAccion);
        if((accion.getClass())!= Mejora.class) throw new InvalidParameterException("El id no corresponde con una mejora");
        ((Mejora)accion).addActividad((ActividadMejora)medida);
        int res = mAccion.ActualizarAccion(accion);
        return res;
    }
    
    /**
     * Crea una nueva actividad preventiva, la persiste en la base de datos y se asocia a la accion preventiva.
     * @param IdAccion
     * @param FechaEstimadaImplementacion
     * @param Descripcion
     * @return -1 si no se creo.
     */
    public int AgregarActividadPreventiva(int IdAccion, Date FechaEstimadaImplementacion, String Descripcion ){
        Medida medida = new ActividadPreventiva(FechaEstimadaImplementacion, Descripcion);
        medida.setId(mMedida.CrearMedida(medida));
        Accion accion = mAccion.GetAccion(IdAccion);
        if((accion.getClass())!= Preventiva.class) throw new InvalidParameterException("El id no corresponde con una Preventiva");
        ((Preventiva)accion).addActividad((ActividadPreventiva)medida);
        int res = mAccion.ActualizarAccion(accion);
        return res;
    }
    
    /**
     * Crea una nueva medida preventiva, la persiste en la base de datos y se asocia a la accion correctiva.
     * @param IdAccion
     * @param FechaEstimadaImplementacion
     * @param Descripcion
     * @return -1 si no se creo.
     */
    public int AgregarMedidaPreventiva(int IdAccion, Date FechaEstimadaImplementacion, String Descripcion ){
        Medida medida = new MedidaPreventiva(FechaEstimadaImplementacion, Descripcion);
        medida.setId(mMedida.CrearMedida(medida));
        Accion accion = mAccion.GetAccion(IdAccion);
        if((accion.getClass())!= Correctiva.class) throw new InvalidParameterException("El id no corresponde con una Correctiva");
        ((Correctiva)accion).addMedidaPreventiva((MedidaPreventiva)medida);
        int res = mAccion.ActualizarAccion(accion);
        return res;
    }
    
    /**
     * Crea una nueva medida correctiva, la persiste en la base de datos y se asocia a la accion correctiva.
     * @param IdAccion
     * @param FechaEstimadaImplementacion
     * @param Descripcion
     * @return -1 si no se creo.
     */
    public int AgregarMedidaCorrectiva(int IdAccion, Date FechaEstimadaImplementacion, String Descripcion ){
        Medida medida = new MedidaCorrectiva(FechaEstimadaImplementacion, Descripcion);
        medida.setId(mMedida.CrearMedida(medida));
        Accion accion = mAccion.GetAccion(IdAccion);
        if((accion.getClass())!= Correctiva.class) throw new InvalidParameterException("El id no corresponde con una Correctiva");
        ((Correctiva)accion).addMedidaCorrectiva((MedidaCorrectiva)medida);
        int res = mAccion.ActualizarAccion(accion);
        return res;
    }
    
    public int AgregarFechaImplementacionCorrectiva(Date FechaImplementacion, int IdMedidaCorrectiva){
        Medida medida = mMedida.GetMedidaCorrectiva(IdMedidaCorrectiva);
        medida.setFechaImplementacion(FechaImplementacion);
        int res = mMedida.ActualizarMedida(medida);
        if(res!=-1){
            
        }
    }
}
