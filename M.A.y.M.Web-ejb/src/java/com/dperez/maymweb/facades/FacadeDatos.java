/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.facades;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.accion.acciones.EnumAccion;
import com.dperez.maymweb.accion.acciones.EnumTipoDesvio;
import com.dperez.maymweb.accion.adjunto.EnumTipoAdjunto;
import com.dperez.maymweb.controlador.registro.ControladorEdicionRegistro;
import com.dperez.maymweb.controlador.registro.ControladorRegistro;
import com.dperez.maymweb.fortaleza.Fortaleza;
import java.util.Date;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Diego
 */
@Named
@Stateless
public class FacadeDatos {
    @Inject
    private ControladorRegistro cReg;
    @Inject
    private ControladorEdicionRegistro cEdicion;
    
    //  Constructores
    public FacadeDatos(){}
    
    /**
     * Crea una nueva accion en estado pendiente y la persiste en la base de datos.
     * @param TipoAccion
     * @param FechaDeteccion
     * @param Descripcion
     * @param TipoDesvio Null cuando no corresponde
     * @param IdAreaSector
     * @param IdDeteccion
     * @param IdEmpresa
     * @return Null: si no se creo.
     */
    public Accion NuevaAccion(EnumAccion TipoAccion, Date FechaDeteccion, String Descripcion, EnumTipoDesvio TipoDesvio,
            int IdAreaSector, int IdDeteccion, int IdEmpresa){
        return cReg.NuevaAccion(TipoAccion, FechaDeteccion, Descripcion, TipoDesvio, IdAreaSector, IdDeteccion, IdEmpresa);
    }
    
    /**
     * Crea el/los productos involucrados en el desvio, los agrega a la accion correctiva y actualiza la base de datos.
     * @param AccionCorrectiva
     * @param NombreProducto
     * @param DatosProducto
     * @return -1 si no se creo.
     */
    public int AgregarProductoInvolucrado(int AccionCorrectiva, String NombreProducto, String DatosProducto){
        return cReg.AgregarProductoInvolucrado(AccionCorrectiva, NombreProducto, DatosProducto);
    }
    
    /**
     * Crea el/los adjuntos, los agrega a la accion y actualiza la base de datos.
     * @param IdAccion
     * @param TituloAdjunto
     * @param UbicacionAdjunto
     * @param TipoAdjunto
     * @return -1 si no se creo.
     */
    public int AgregarArchivoAdjunto(int IdAccion, String TituloAdjunto, String UbicacionAdjunto, EnumTipoAdjunto TipoAdjunto){
        return cReg.AgregarArchivoAdjunto(IdAccion, TituloAdjunto, UbicacionAdjunto, TipoAdjunto);
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
     * @param IdAccion
     * @return -1 si no se pudo actualizar.
     */
    public int SetFechaImplementacionMedidaCorrectiva(Date FechaImplementacion, int IdMedidaCorrectiva, int IdAccion){
        return cReg.SetFechaImplementacionMedidaCorrectiva(FechaImplementacion, IdMedidaCorrectiva, IdAccion);
    }
    
    /**
     * Setea la fecha de implementacion de la Medida Preventiva, cambia el estado de la accion (si corresponde) y persiste los cambios en la base de deatos.
     * @param FechaImplementacion
     * @param IdMedidaPreventiva
     * @param IdAccion
     * @return -1 si no se pudo actualizar.
     */
    public int SetFechaImplementacionMedidaPreventiva(Date FechaImplementacion, int IdMedidaPreventiva, int IdAccion){
        return cReg.SetFechaImplementacionMedidaPreventiva(FechaImplementacion, IdMedidaPreventiva, IdAccion);
    }
    
    /**
     * Setea la fecha de implementacion de la Actividad Preventiva, cambia el estado de la accion (si corresponde) y persiste los cambios en la base de deatos.
     * @param FechaImplementacion
     * @param IdActividadPreventiva
     * @param IdAccion
     * @return -1 si no se pudo actualizar.
     */
    public int SetFechaImplementacionActividadPreventiva(Date FechaImplementacion, int IdActividadPreventiva, int IdAccion){
        return cReg.SetFechaImplementacionActividadPreventiva(FechaImplementacion, IdActividadPreventiva, IdAccion);
    }
    
    /**
     * Setea la fecha de implementacion de la Actividad de Mejora, cambia el estado de la accion (si corresponde) y persiste los cambios en la base de deatos.
     * @param FechaImplementacion
     * @param IdActividadMejora
     * @param IdAccion
     * @return -1 si no se pudo actualizar.
     */
    public int SetFechaImplementacionActividadMejora(Date FechaImplementacion, int IdActividadMejora, int IdAccion){
        return cReg.SetFechaImplementacionActividadMejora(FechaImplementacion, IdActividadMejora, IdAccion);
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
    
    /**
     * Remueve la medida correctiva de la accion correctiva seleccionada y actualiza la base de datos.
     * Elimina la medida correctiva de la base de datos.
     * @param IdAccion
     * @param IdMedidaCorrectiva
     * @return Retorna -1 si se actualizo. Retorna el IdAccion si no se elimino.
     */
    public int RemoverMedidaCorrectiva(int IdAccion, int IdMedidaCorrectiva){
        return cEdicion.RemoverMedidaCorrectiva(IdAccion, IdMedidaCorrectiva);
    }
    
    /**
     * Remueve la medida preventiva de la accion correctiva seleccionada y actualiza la base de datos.
     * Elimina la medida preventiva de la base de datos.
     * @param IdAccion
     * @param IdMedidaPreventiva
     * @return Retorna -1 si se actualizo. Retorna el IdAccion si no se elimino.
     */
    public int RemoverMedidaPreventiva(int IdAccion, int IdMedidaPreventiva){
        return cEdicion.RemoverMedidaPreventiva(IdAccion, IdMedidaPreventiva);
    }
    
    /**
     * Remueve la actividad de mejora de la accion de mejora seleccionada y actualiza la base de datos.
     * Elimina la actividad de mejora de la base de datos.
     * @param IdAccion
     * @param IdActividadMejora
     * @return Retorna -1 si se actualizo. Retorna el IdAccion si no se elimino.
     */
    public int RemoverActividadMejora(int IdAccion, int IdActividadMejora){
        return cEdicion.RemoverActividadMejora(IdAccion, IdActividadMejora);
    }
    
    /**
     * Remueve la actividad preventiva de la accion preventiva selaccionada y actualiza la base de datos.
     * Elimina la actividad preventiva de la base de datos.
     * @param IdAccion
     * @param IdActividadPreventiva
     * @return Retorna -1 si se actualizo. Retorna el IdAccion si no se elimino.
     */
    public int RemoverActividadPreventiva(int IdAccion, int IdActividadPreventiva){
        return cEdicion.RemoverActividadPreventiva(IdAccion, IdActividadPreventiva);
    }
    
    /**
     * Remueve el archivo adjunto de la accion seleccionada y actualiza la base de datos.
     * Elimina el adjunto de la base de datos.
     * @param IdAccion
     * @param IdAdjunto
     * @return Retorna -1 si no se actualizo. Retorna IdAccion si se actualizo.
     */
    public int RemoverAdjunto(int IdAccion, int IdAdjunto){
        return cEdicion.RemoverArchivoAdjunto(IdAccion, IdAdjunto);
    }
    /**
     * Edita una accion con los mismos parametros que se creo. Actualiza la base de datos.
     * @param IdAccion
     * @param TipoAccion
     * @param FechaDeteccion
     * @param Descripcion
     * @param TipoDesvio
     * @param IdAreaSector
     * @param IdDeteccion
     * @param IdCodificacion
     * @return -1 si no se actualizo.
     */
    public int EditarAccion(int IdAccion, EnumAccion TipoAccion, Date FechaDeteccion, String Descripcion, EnumTipoDesvio TipoDesvio,
            int IdAreaSector, int IdDeteccion, int IdCodificacion){
        return cEdicion.EditarAccion(IdAccion, TipoAccion, FechaDeteccion, Descripcion, TipoDesvio, IdAreaSector, IdDeteccion, IdCodificacion);
    }
    
    /**
     * Actualiza la actividad en la base de datos.
     * @param IdActividad
     * @param Descripcion
     * @param ResponsableImplementacion
     * @param FechaImplementacion
     * @return Retorna -1 si no se actualizo. Retorna el IdActividad si se actualizo.
     */
    public int EditarActividad(int IdActividad, String Descripcion, int ResponsableImplementacion, Date FechaImplementacion){
        return cEdicion.EditarActividad(IdActividad, ResponsableImplementacion, FechaImplementacion,Descripcion);
    }
    
    /**
     * Remueve el producto involucrado de la accion seleccionada y actualiza la base de datos.
     * Elimina el producto de la base de datos.
     * @param IdAccionCorrectiva
     * @param NombreProducto
     * @return Retorna -1 si no se actualizo. Retorna IdAccion si se actualizo.
     */
    public int RemoverProductoInvolucrado(int IdAccionCorrectiva, String NombreProducto){
        return cEdicion.RemoverProductoInvolucrado(IdAccionCorrectiva, NombreProducto);
    }
    
    /**
     * Crea una nueva fortaleza y la persiste en la base de datos
     * @param FechaDeteccion
     * @param Descripcion
     * @param IdDeteccion
     * @param IdAreaSector
     * @param IdEmpresa
     * @return Null: si no se creo.
     */
    public Fortaleza NuevaFortaleza(Date FechaDeteccion, String Descripcion, int IdDeteccion, int IdAreaSector, int IdEmpresa){
        return cReg.NuevaFortaleza(FechaDeteccion, Descripcion, IdDeteccion, IdAreaSector, IdEmpresa);
    }
    
    /**
     * Actualiza la fortaleza especificada por su id en la base de datos.
     * @param IdFortaleza
     * @param FechaDeteccion
     * @param Descripcion
     * @param IdDeteccion
     * @param IdAreaSector
     * @return Retorna el id de la fortaleza si se actualizo, de lo contrario retorna -1.
     */
    public int EditarFortaleza(int IdFortaleza, Date FechaDeteccion, String Descripcion, int IdDeteccion, int IdAreaSector){
        return cEdicion.EditarFortaleza(IdFortaleza, FechaDeteccion, Descripcion, IdDeteccion, IdAreaSector);
    }
    
    
}
