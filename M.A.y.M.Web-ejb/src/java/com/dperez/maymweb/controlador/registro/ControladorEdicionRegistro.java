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
import com.dperez.maymweb.area.Area;
import com.dperez.maymweb.area.ManejadorArea;
import com.dperez.maymweb.codificacion.Codificacion;
import com.dperez.maymweb.codificacion.ManejadorCodificacion;
import com.dperez.maymweb.deteccion.Deteccion;
import com.dperez.maymweb.deteccion.ManejadorDeteccion;
import com.dperez.maymweb.estado.EnumEstado;
import com.dperez.maymweb.usuario.ControladorSeguridad;
import com.dperez.maymweb.usuario.Credencial;
import com.dperez.maymweb.usuario.ManejadorUsuario;
import com.dperez.maymweb.usuario.Usuario;
import com.dperez.maymweb.usuario.permiso.EnumPermiso;
import java.util.Date;
import javax.inject.Inject;

/**
 *
 * @author Diego
 */
public class ControladorEdicionRegistro {
    @Inject
    private ManejadorAccion mAccion;
    @Inject
    private ManejadorArea mArea;
    @Inject
    private ManejadorDeteccion mDeteccion;
    @Inject
    private ManejadorCodificacion mCodificacion;
    @Inject
    private ManejadorUsuario mUsuario;
    @Inject
    private ControladorSeguridad cSeg;
    
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
        Accion accion = mAccion.GetAccion(IdAccion);
        //  Comprobar si hay cambio en el valor para "ahorrar" llamada a la base de datos.
        if(accion.getAreaSectorAccion().getId()!=IdAreaSector){
            Area areaSector = mArea.GetArea(IdAreaSector);
            accion.setAreaSectorAccion(areaSector);
        }
        if(accion.getGeneradaPor().getId()!=IdDeteccion){
            Deteccion deteccion = mDeteccion.GetDeteccion(IdDeteccion);
            accion.setGeneradaPor(deteccion);
        }
        if(accion.getCodificacionAccion().getId()!=IdCodificacion){
            Codificacion codificacion = mCodificacion.GetCodificacion(IdCodificacion);
            accion.setCodificacionAccion(codificacion);
        }
        accion.setFechaDeteccion(FechaDeteccion);
        accion.setDescripcion(Descripcion);
        if(TipoAccion == EnumAccion.CORRECTIVA){
            ((Correctiva)accion).setTipo(TipoDesvio);
        }
        return mAccion.ActualizarAccion(accion);
    }
    
    /**
     * Setea el analisis de causa de la desviacion y actualiza la base de datos.
     * @param AnalisisDeCausa
     * @param IdAccion
     * @return -1 si no se actualizo.
     */
    public int SetAnalisisDeCausa(String AnalisisDeCausa, int IdAccion){
        Accion accion = mAccion.GetAccion(IdAccion);
        accion.setAnalisisCausa(AnalisisDeCausa);
        return mAccion.ActualizarAccion(accion);
    }
    
    /**
     * Setea el estado de la accion como desestimada y actualiza la base de datos.
     * @param Observaciones
     * @param IdAccion
     * @return 
     */
    public int DesestimarAccion(String Observaciones, int IdAccion){
        Accion accion = mAccion.GetAccion(IdAccion);
        accion.setEstadoAccion(EnumEstado.DESESTIMADA);
        return mAccion.ActualizarAccion(accion);
    }
    
    /**
     * Setea los datos del usuario y actualiza la base de datos. No se realiza comprobacion de password.
     * Para cambiar password utilizar metodo cambiarPasswordUsuario().
     * @param IdUsuario
     * @param NombreUsuario
     * @param ApellidoUsuario
     * @param CorreoUsuario
     * @param PermisoUsuario
     * @param RecibeAlertas
     * @return -1 si no se actualizo.
     */
    public int CambiarDatosUsuario(int IdUsuario, String NombreUsuario, String ApellidoUsuario, String CorreoUsuario, 
            EnumPermiso PermisoUsuario, boolean RecibeAlertas){
        Usuario usuario = mUsuario.GetUsuario(IdUsuario);
        usuario.setNombre(NombreUsuario);
        usuario.setApellido(ApellidoUsuario);
        usuario.setCorreo(CorreoUsuario);
        usuario.setPermisoUsuario(PermisoUsuario);
        usuario.setRecibeAlertas(RecibeAlertas);
        return mUsuario.ActualizarUsuario(usuario);
    }
}
