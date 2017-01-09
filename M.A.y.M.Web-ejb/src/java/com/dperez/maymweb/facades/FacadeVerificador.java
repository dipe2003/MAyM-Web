/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.facades;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.accion.EnumComprobacion;
import com.dperez.maymweb.accion.acciones.EnumAccion;
import com.dperez.maymweb.accion.acciones.EnumTipoDesvio;
import com.dperez.maymweb.controlador.registro.ControladorEdicionRegistro;
import com.dperez.maymweb.controlador.registro.ControladorRegistro;
import java.util.Date;
import java.util.Map;
import javax.inject.Inject;

/**
 * Contiene los metodos exclusivos para el permiso de verificador ademas de el ingreso de datos.
 * @author Diego
 */
public class FacadeVerificador {
    @Inject
    private ControladorRegistro cReg;
    @Inject
    private ControladorEdicionRegistro cEdicion;
    
    //  Constructores
    public FacadeVerificador(){}
    
    /**
     * Setea la comprobacion de implementacion de la accion, cambia el estado segun corresponda y actualiza la base de datos.
     * @param FechaComprobacionImplementacion
     * @param ComentariosImplementacion
     * @param Comprobacion
     * @param IdAccion
     * @return -1 si no se actualizo
     */
    public int SetComprobacionImplementacionAccion(Date FechaComprobacionImplementacion, String ComentariosImplementacion, EnumComprobacion Comprobacion,
            int IdAccion){
        return cReg.SetComprobacionImplementacionAccion(FechaComprobacionImplementacion, ComentariosImplementacion, EnumComprobacion.NO_COMPROBADA, IdAccion);
    }
    
    /**
     * Setea la verificacion de eficacia de la accion, deja en estado cerrado y actualiza la base de datos.
     * @param FechaVerificacionEficacia
     * @param ComentariosVerificacion
     * @param Comprobacion
     * @param IdAccion
     * @return -1 si no se actualizo
     */
    public int SetVerificacionEficaciaAccion(Date FechaVerificacionEficacia, String ComentariosVerificacion, EnumComprobacion Comprobacion,
            int IdAccion){
        return cReg.SetVerificacionEficaciaAccion(FechaVerificacionEficacia, ComentariosVerificacion, Comprobacion, IdAccion);
    }
    
    /**
     * Setea el estado de la accion como desestimada y actualiza la base de datos.
     * @param Observaciones
     * @param IdAccion
     * @return
     */
    public int DesestimarAccion(String Observaciones, int IdAccion){
        return cEdicion.DesestimarAccion(Observaciones, IdAccion);
    }
    
    /*
    Por Datos
    */
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
        return cReg.NuevaAccion(TipoAccion, FechaDeteccion, Descripcion, TipoDesvio, IdAreaSector, IdDeteccion, IdCodificacion);
    }
    
    /**
     * Crea el/los productos involucrados en el desvio, los agrega a la accion correctiva y actualiza la base de datos.
     * @param AccionCorrectiva
     * @param productos Map.Key = Nombre del producto | Map.Value = Datos del producto
     * @return -1 si no se creo.
     */
    public int AgregarProductoInvolucrado(int AccionCorrectiva, Map<String, String> productos){
        return cReg.AgregarProductoInvolucrado(AccionCorrectiva, productos);
    }
    
    /**
     * Crea el/los adjuntos, los agrega a la accion y actualiza la base de datos.
     * @param IdAccion
     * @param adjuntos Map.Key = Titulo del archivo Adjunto | Map.Value = Ubicacion del archivo adjunto
     * @return -1 si no se creo.
     */
    public int AgregarArchivoAdjunto(int IdAccion, Map<String, String> adjuntos){
        return cReg.AgregarArchivoAdjunto(IdAccion, adjuntos);
    }
    
    /**
     * Crea una nueva actividad de mejora, la persiste en la base de datos y se asocia a la mejora.
     * @param IdAccion
     * @param FechaEstimadaImplementacion
     * @param Descripcion
     * @param IdUsuarioResponsable
     * @return -1 si no se creo.
     */
    public int AgregarActividadMejora(int IdAccion, Date FechaEstimadaImplementacion, String Descripcion, int IdUsuarioResponsable){
        return cReg.AgregarActividadMejora(IdAccion, FechaEstimadaImplementacion, Descripcion, IdUsuarioResponsable);
    }
    
    /**
     * Crea una nueva actividad preventiva, la persiste en la base de datos y se asocia a la accion preventiva.
     * @param IdAccion
     * @param FechaEstimadaImplementacion
     * @param Descripcion
     * @param IdUsuarioResponsable
     * @return -1 si no se creo.
     */
    public int AgregarActividadPreventiva(int IdAccion, Date FechaEstimadaImplementacion, String Descripcion, int IdUsuarioResponsable){
        return cReg.AgregarActividadPreventiva(IdAccion, FechaEstimadaImplementacion, Descripcion, IdUsuarioResponsable);
    }
    
    /**
     * Crea una nueva medida preventiva, la persiste en la base de datos y se asocia a la accion correctiva.
     * @param IdAccion
     * @param FechaEstimadaImplementacion
     * @param Descripcion
     * @param IdUsuarioResponsable
     * @return -1 si no se creo.
     */
    public int AgregarMedidaPreventiva(int IdAccion, Date FechaEstimadaImplementacion, String Descripcion, int IdUsuarioResponsable ){
        return cReg.AgregarMedidaPreventiva(IdAccion, FechaEstimadaImplementacion, Descripcion, IdUsuarioResponsable);
    }
    
    /**
     * Crea una nueva medida correctiva, la persiste en la base de datos y se asocia a la accion correctiva.
     * @param IdAccion
     * @param FechaEstimadaImplementacion
     * @param Descripcion
     * @param IdUsuarioResponsable
     * @return -1 si no se creo.
     */
    public int AgregarMedidaCorrectiva(int IdAccion, Date FechaEstimadaImplementacion, String Descripcion, int IdUsuarioResponsable ){
        return cReg.AgregarMedidaCorrectiva(IdAccion, FechaEstimadaImplementacion, Descripcion, IdUsuarioResponsable);
    }
    
    /**
     * Setea la fecha de implementacion de la Medida correctiva, cambia el estado de la accion (si corresponde) y persiste los cambios en la base de deatos.
     * @param FechaImplementacion
     * @param IdMedidaCorrectiva
     * @return -1 si no se pudo actualizar.
     */
    public int SetFechaImplementacionMedidaCorrectiva(Date FechaImplementacion, int IdMedidaCorrectiva){
        return cReg.SetFechaImplementacionMedidaCorrectiva(FechaImplementacion, IdMedidaCorrectiva);
    }
    
    /**
     * Setea la fecha de implementacion de la Medida Preventiva, cambia el estado de la accion (si corresponde) y persiste los cambios en la base de deatos.
     * @param FechaImplementacion
     * @param IdMedidaPreventiva
     * @return -1 si no se pudo actualizar.
     */
    public int SetFechaImplementacionMedidaPreventiva(Date FechaImplementacion, int IdMedidaPreventiva){
        return cReg.SetFechaImplementacionMedidaPreventiva(FechaImplementacion, IdMedidaPreventiva);
    }
    
    /**
     * Setea la fecha de implementacion de la Actividad Preventiva, cambia el estado de la accion (si corresponde) y persiste los cambios en la base de deatos.
     * @param FechaImplementacion
     * @param IdActividadPreventiva
     * @return -1 si no se pudo actualizar.
     */
    public int SetFechaImplementacionActividadPreventiva(Date FechaImplementacion, int IdActividadPreventiva){
        return cReg.SetFechaImplementacionActividadPreventiva(FechaImplementacion, IdActividadPreventiva);
    }
    
    /**
     * Setea la fecha de implementacion de la Actividad de Mejora, cambia el estado de la accion (si corresponde) y persiste los cambios en la base de deatos.
     * @param FechaImplementacion
     * @param IdActividadMejora
     * @return -1 si no se pudo actualizar.
     */
    public int SetFechaImplementacionActividadMejora(Date FechaImplementacion, int IdActividadMejora){
        return cReg.SetFechaImplementacionActividadMejora(FechaImplementacion, IdActividadMejora);
    }
    
    /**
     * Setea la comprobacion de implementacion estimada con responsable y fecha estimada. Actualiza la base de datos.
     * @param FechaEstimada
     * @param IdUsuarioResponsableImplementacion
     * @param IdAccion
     * @return -1 si no se actualizo. IdAccion si correcto.
     */
    public int SetEstimadoComprobacionImplementacion(Date FechaEstimada, int IdUsuarioResponsableImplementacion, int IdAccion){
        return cReg.SetEstimadoComprobacionImplementacion(FechaEstimada, IdUsuarioResponsableImplementacion, IdAccion);
    }
    
    /**
     * Setea la comprobacion de eficacia estimada con responsable y fecha estimada. Actualiza la base de datos.
     * @param FechaEstimada
     * @param IdUsuarioResponsableComprobacion
     * @param IdAccion
     * @return -1 si no se actualizo. IdAccion si correcto.
     */
    public int SetEstimadoComprobacionEficacia(Date FechaEstimada, int IdUsuarioResponsableComprobacion, int IdAccion){
        return cReg.SetEstimadoComprobacionEficacia(FechaEstimada, IdUsuarioResponsableComprobacion, IdAccion);
    }
}
