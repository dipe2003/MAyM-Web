/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.controlador.registro;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.accion.Comprobacion;
import com.dperez.maymweb.accion.EnumComprobacion;
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
import com.dperez.maymweb.usuario.ManejadorUsuario;
import com.dperez.maymweb.usuario.Usuario;
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
    @Inject
    private ManejadorUsuario mUsuario;
    
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
    
    /***
     * Setea la fecha de implementacion de la Medida correctiva, cambia el estado de la accion (si corresponde) y persiste los cambios en la base de deatos.
     * @param FechaImplementacion
     * @param IdMedidaCorrectiva
     * @return -1 si no se pudo actualizar.
     */
    public int SetFechaImplementacionMedidaCorrectiva(Date FechaImplementacion, int IdMedidaCorrectiva){
        Medida medida = mMedida.GetMedidaCorrectiva(IdMedidaCorrectiva);
        medida.setFechaImplementacion(FechaImplementacion);
        int res = mMedida.ActualizarMedida(medida);
        if(res!=-1){
            Accion AccionCorrectiva = mAccion.GetAccion(((MedidaCorrectiva)medida).getAccionCorrectivaMedidaCorrectiva().getId());
            AccionCorrectiva.CambiarEstado();
            res = mAccion.ActualizarAccion(AccionCorrectiva);
        }
        return res;
    }
    
    /***
     * Setea la fecha de implementacion de la Medida Preventiva, cambia el estado de la accion (si corresponde) y persiste los cambios en la base de deatos.
     * @param FechaImplementacion
     * @param IdMedidaPreventiva
     * @return -1 si no se pudo actualizar.
     */
    public int SetFechaImplementacionMedidaPreventiva(Date FechaImplementacion, int IdMedidaPreventiva){
        Medida medida = mMedida.GetMedidaPreventiva(IdMedidaPreventiva);
        medida.setFechaImplementacion(FechaImplementacion);
        int res = mMedida.ActualizarMedida(medida);
        if(res!=-1){
            Accion AccionCorrectiva = mAccion.GetAccion(((MedidaPreventiva)medida).getAccionCorrectivaMedidaPreventiva().getId());
            AccionCorrectiva.CambiarEstado();
            res = mAccion.ActualizarAccion(AccionCorrectiva);
        }
        return res;
    }
    
    /***
     * Setea la fecha de implementacion de la Actividad Preventiva, cambia el estado de la accion (si corresponde) y persiste los cambios en la base de deatos.
     * @param FechaImplementacion
     * @param IdActividadPreventiva
     * @return -1 si no se pudo actualizar.
     */
    public int SetFechaImplementacionActividadPreventiva(Date FechaImplementacion, int IdActividadPreventiva){
        Medida medida = mMedida.GetActividadPreventiva(IdActividadPreventiva);
        medida.setFechaImplementacion(FechaImplementacion);
        int res = mMedida.ActualizarMedida(medida);
        if(res!=-1){
            Accion AccionPreventiva = mAccion.GetAccion(((ActividadPreventiva)medida).getAccionPreventiva().getId());
            AccionPreventiva.CambiarEstado();
            res = mAccion.ActualizarAccion(AccionPreventiva);
        }
        return res;
    }
    
    /***
     * Setea la fecha de implementacion de la Actividad de Mejora, cambia el estado de la accion (si corresponde) y persiste los cambios en la base de deatos.
     * @param FechaImplementacion
     * @param IdActividadMejora
     * @return -1 si no se pudo actualizar.
     */
    public int SetFechaImplementacionActividadMejora(Date FechaImplementacion, int IdActividadMejora){
        Medida medida = mMedida.GetActividadMejora(IdActividadMejora);
        medida.setFechaImplementacion(FechaImplementacion);
        int res = mMedida.ActualizarMedida(medida);
        if(res!=-1){
            Accion AccionMejora = mAccion.GetAccion(((ActividadMejora)medida).getAccionMejora().getId());
            AccionMejora.CambiarEstado();
            res = mAccion.ActualizarAccion(AccionMejora);
        }
        return res;
    }
    
    /***
     * Setea la comprobacion de implementacion estimada con responsable y fecha estimada. Actualiza la base de datos.
     * @param FechaEstimada
     * @param IdUsuarioResponsableImplementacion
     * @param IdAccion
     * @return -1 si no se actualizo. IdAccion si correcto.
     */
    public int SetEstimadoComprobacionImplementacion(Date FechaEstimada, int IdUsuarioResponsableImplementacion, int IdAccion){
        Usuario usuario = mUsuario.GetUsuario(IdUsuarioResponsableImplementacion);
        Comprobacion comprobacionImplementacion = new Comprobacion(FechaEstimada, usuario);
        Accion accion = mAccion.GetAccion(IdAccion);
        accion.setComprobacionImplantacion(comprobacionImplementacion);
        return mAccion.ActualizarAccion(accion);
    }
    
    /***
     * Setea la comprobacion de eficacia estimada con responsable y fecha estimada. Actualiza la base de datos.
     * @param FechaEstimada
     * @param IdUsuarioResponsableComprobacion
     * @param IdAccion
     * @return -1 si no se actualizo. IdAccion si correcto.
     */
    public int SetEstimadoComprobacionEficacia(Date FechaEstimada, int IdUsuarioResponsableComprobacion, int IdAccion){
        Usuario usuario = mUsuario.GetUsuario(IdUsuarioResponsableComprobacion);
        Comprobacion comprobacionEficacia = new Comprobacion(FechaEstimada, usuario);
        Accion accion = mAccion.GetAccion(IdAccion);
        accion.setComprobacionImplantacion(comprobacionEficacia);
        return mAccion.ActualizarAccion(accion);
    }
    
    /***
     * Setea la comprobacion de implementacion de la accion, cambia el estado segun corresponda y actualiza la base de datos.
     * @param FechaComprobacionImplementacion
     * @param ComentariosImplementacion
     * @param Comprobacion
     * @param IdAccion
     * @return -1 si no se actualizo
     */
    public int SetComprobacionImplementacionAccion(Date FechaComprobacionImplementacion, String ComentariosImplementacion, EnumComprobacion Comprobacion,
            int IdAccion){
        Accion accion = mAccion.GetAccion(IdAccion);
        accion.getComprobacionImplantacion().setFechaComprobacion(FechaComprobacionImplementacion);
        accion.getComprobacionImplantacion().setObservaciones(ComentariosImplementacion);
        accion.getComprobacionImplantacion().setResultado(Comprobacion);
        accion.CambiarEstado();
        return mAccion.ActualizarAccion(accion);
    }
    
    /***
     * Setea la verificacion de eficacia de la accion, deja en estado cerrado y actualiza la base de datos.
     * @param FechaVerificacionEficacia
     * @param ComentariosVerificacion
     * @param Comprobacion
     * @param IdAccion
     * @return -1 si no se actualizo
     */
    public int SetVerificacionEficaciaAccion(Date FechaVerificacionEficacia, String ComentariosVerificacion, EnumComprobacion Comprobacion,
            int IdAccion){
        Accion accion = mAccion.GetAccion(IdAccion);
        accion.getComprobacionEficacia().setFechaComprobacion(FechaVerificacionEficacia);
        accion.getComprobacionEficacia().setObservaciones(ComentariosVerificacion);
        accion.getComprobacionEficacia().setResultado(Comprobacion);
        accion.CambiarEstado();
        return mAccion.ActualizarAccion(accion);
    }
    
}
