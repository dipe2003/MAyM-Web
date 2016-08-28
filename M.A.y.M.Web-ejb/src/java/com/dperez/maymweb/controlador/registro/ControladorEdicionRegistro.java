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
import com.dperez.maymweb.accion.adjunto.Adjunto;
import com.dperez.maymweb.accion.adjunto.ManejadorAdjunto;
import com.dperez.maymweb.accion.medida.ManejadorMedida;
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
import com.dperez.maymweb.deteccion.ManejadorTipoDeteccion;
import com.dperez.maymweb.deteccion.TipoDeteccion;
import com.dperez.maymweb.estado.EnumEstado;
import com.dperez.maymweb.producto.ManejadorProducto;
import com.dperez.maymweb.producto.Producto;
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
    private ManejadorTipoDeteccion mTipoDeteccion;
    @Inject
    private ManejadorCodificacion mCodificacion;
    @Inject
    private ManejadorUsuario mUsuario;
    @Inject
    private ControladorSeguridad cSeg;
    @Inject
    private ManejadorProducto mProducto;
    @Inject
    private ManejadorAdjunto mAdjunto;
    @Inject
    private ManejadorMedida mMedida;
    
    
    /*
    ACCION
    */
    
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
     * Remueve el producto involucrado de la accion seleccionada y actualiza la base de datos.
     * Elimina el producto de la base de datos.
     * @param IdAccionCorrectiva
     * @param IdProducto
     * @return Retorna -1 si no se actualizo. Retorna IdAccion si se actualizo.
     */
    public int RemoverProductoInvolucrado(int IdAccionCorrectiva, int IdProducto){
        Accion accion = mAccion.GetAccion(IdAccionCorrectiva);
        ((Correctiva)accion).removeProducoAfectado(IdProducto);
        int res = mAccion.ActualizarAccion(accion);
        if(res!=-1){
            Producto producto = mProducto.GetProducto(IdProducto);
            mProducto.BorrarProducto(producto);
        }
        return res;
    }
    
    /**
     * Remueve el archivo adjunto de la accion seleccionada y actualiza la base de datos.
     * Elimina el adjunto de la base de datos.
     * @param IdAccion
     * @param IdAdjunto
     * @return Retorna -1 si no se actualizo. Retorna IdAccion si se actualizo.
     */
    public int RemoverArchivoAdjunto(int IdAccion, int IdAdjunto){
        Accion accion = mAccion.GetAccion(IdAccion);
        accion.removeAdjunto(IdAdjunto);
        int res = mAccion.ActualizarAccion(accion);
        if(res!=-1){
            Adjunto adjunto = mAdjunto.GetAdjunto(IdAdjunto);
            mAdjunto.BorrarAdjunto(adjunto);
        }
        return res;
    }
    
    /**
     * Remueve la actividad de mejora de la accion de mejora seleccionada y actualiza la base de datos.
     * Elimina la actividad de mejora de la base de datos.
     * @param IdAccion
     * @param IdActividadMejora
     * @return Retorna -1 si se actualizo. Retorna el IdAccion si no se elimino.
     */
    public int RemoverActividadMejora(int IdAccion, int IdActividadMejora){
        Accion accion = mAccion.GetAccion(IdAccion);
        ((Mejora)accion).removeActividad(IdActividadMejora);
        ((Mejora)accion).CambiarEstado();
        int res = mAccion.ActualizarAccion(accion);
        if(res!=-1){
            ActividadMejora actividad = mMedida.GetActividadMejora(IdActividadMejora);
            mMedida.BorrarMedida(actividad);
        }
        return res;
    }
    
    /**
     * Remueve la actividad preventiva de la accion preventiva selaccionada y actualiza la base de datos.
     * Elimina la actividad preventiva de la base de datos.
     * @param IdAccion
     * @param IdActividadPreventiva
     * @return Retorna -1 si se actualizo. Retorna el IdAccion si no se elimino.
     */
    public int RemoverActividadPreventiva(int IdAccion, int IdActividadPreventiva){
        Accion accion = mAccion.GetAccion(IdAccion);
        ((Preventiva)accion).removeActividad(IdAccion);
        ((Preventiva)accion).CambiarEstado();
        int res = mAccion.ActualizarAccion(accion);
        if(res!=-1){
            ActividadPreventiva actividad = mMedida.GetActividadPreventiva(IdActividadPreventiva);
            mMedida.BorrarMedida(actividad);
        }
        return res;
    }
    
    /**
     * Remueve la medida preventiva de la accion correctiva seleccionada y actualiza la base de datos.
     * Elimina la medida preventiva de la base de datos.
     * @param IdAccion
     * @param IdMedidaPreventiva
     * @return Retorna -1 si se actualizo. Retorna el IdAccion si no se elimino.
     */
    public int RemoverMedidaPreventiva(int IdAccion, int IdMedidaPreventiva){
        Accion accion = mAccion.GetAccion(IdAccion);
        ((Correctiva)accion).removeMedidaPreventiva(IdMedidaPreventiva);
        ((Correctiva)accion).CambiarEstado();
        int res = mAccion.ActualizarAccion(accion);
        if(res!=-1){
            MedidaPreventiva medida = mMedida.GetMedidaPreventiva(IdMedidaPreventiva);
            mMedida.BorrarMedida(medida);
        }
        return res;
    }
    
    /**
     * Remueve la medida correctiva de la accion correctiva seleccionada y actualiza la base de datos.
     * Elimina la medida correctiva de la base de datos.
     * @param IdAccion
     * @param IdMedidaCorrectiva
     * @return Retorna -1 si se actualizo. Retorna el IdAccion si no se elimino.
     */
    public int RemoverMedidaCorrectiva(int IdAccion, int IdMedidaCorrectiva){
        Accion accion = mAccion.GetAccion(IdAccion);
        ((Correctiva)accion).removeMedidaCorrectiva(IdMedidaCorrectiva);
        ((Correctiva)accion).CambiarEstado();
        int res = mAccion.ActualizarAccion(accion);
        if(res!=-1){
            MedidaCorrectiva medida = mMedida.GetMedidaCorrectiva(IdMedidaCorrectiva);
            mMedida.BorrarMedida(medida);
        }
        return res;
    }
    
    /*
    USUARIO
    */
    
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
    
    /**
     * Comprueba la validez del password ingresado con el correspondiente del usuario en la base de datos.
     * @param IdUsuario
     * @param Password
     * @return True si es valido | Fales si no es valido.
     */
    public boolean ComprobarValidezPassword(int IdUsuario, String Password){
        Usuario usuario = mUsuario.GetUsuario(IdUsuario);
        String PasswordStored = cSeg.getPasswordSeguro(Password, usuario.getCredencialUsuario().getPasswordKey());
        return PasswordStored.equals(Password);
    }
    
    /**
     * Cambia la credencial (password y password key) del usuario especificado y actualiza la base de datos.
     * Si el password ingresado no coincide con el guardado no se actualiza.
     * @param IdUsuario
     * @param OldPassword: password del usuario guardado en la base de datos.
     * @param NewPassword: nuevo password para el usuario.
     * @return Retorna la credencial del usuario actualizada. Retorna Null si no se pudo actualizar.
     */
    public Credencial CambiarCredencialUsuario(int IdUsuario, String OldPassword, String NewPassword){
        int res = -1;
        Usuario usuario = mUsuario.GetUsuario(IdUsuario);
        String PasswordStored = cSeg.getPasswordSeguro(OldPassword, usuario.getCredencialUsuario().getPasswordKey());
        if(PasswordStored.equals(OldPassword)){
            String[] nuevaCredencial = cSeg.getPasswordSeguro(NewPassword);
            usuario.getCredencialUsuario().setPassword(nuevaCredencial[1]);
            usuario.getCredencialUsuario().setPasswordKey(nuevaCredencial[0]);
            res = mUsuario.ActualizarUsuario(usuario);
        }
        if(res!=-1){
            return usuario.getCredencialUsuario();
        }else{
            return null;
        }
    }
    
    /*
    AREA
    */
    
    /**
     * Cambia los valores de area y actualiza la base de datos.
     * @param IdArea
     * @param NombreArea
     * @param CorreoArea
     * @return Retorna -1 si no se actualizo. Retorna el IdArea si se actualizo.
     */
    public int EditarArea(int IdArea, String NombreArea, String CorreoArea){
        Area area = mArea.GetArea(IdArea);
        area.setNombre(NombreArea);
        area.setCorreo(CorreoArea);
        return mArea.ActualizarArea(area);
    }
    
    /*
    CODIFICACION
    */
    
    /**
     * Cambia los valores de Codificacion y actualiza la base de datos.
     * @param IdCodificacion
     * @param NombreCodificacion
     * @return Retorna -1 si no se actualizo. Retorna el IdCodificacion si se actualizo.
     */
    public int EditarCodificacion(int IdCodificacion, String NombreCodificacion){
        Codificacion codificacion = mCodificacion.GetCodificacion(IdCodificacion);
        codificacion.setNombre(NombreCodificacion);
        return mCodificacion.ActualizarCodificacion(codificacion);
    }
    
    /*
    DETECCION Y TIPO DE DETECCION
    */
    
    /**
     * Cambia los valores de Deteccion y actualiza la base de datos.
     * @param IdDteccion
     * @param NombreDeteccion
     * @param IdTipoDeteccion
     * @return Retorna -1 si no se actualizo. Retorna el IdDeteccion si se actualizo.
     */
    public int EditarDeteccion(int IdDteccion, String NombreDeteccion, int IdTipoDeteccion){
        Deteccion deteccion = mDeteccion.GetDeteccion(IdDteccion);
        // comprobar si se cambia el tipo de deteccion para 'ahorrar' llamada a la base de datos.
        if(deteccion.getTipo().getId()!= IdTipoDeteccion){
            deteccion.setTipo(mTipoDeteccion.GetTipoDeteccion(IdTipoDeteccion));
        }
        deteccion.setNombre(NombreDeteccion);
        return mDeteccion.ActualizarDeteccion(deteccion);
    }
    
    /**
     * Cambia los valores de TipoDeteccin y actualiza la base de datos.
     * @param IdTipoDeteccion
     * @param NombreTipoDeteccion
     * @return Retorna -1 si no se actualizo. Retorna el IdTipoDeteccion si se actualizo.
     */
    public int EditarTipoDeteccion(int IdTipoDeteccion, String NombreTipoDeteccion){
        TipoDeteccion tipo = mTipoDeteccion.GetTipoDeteccion(IdTipoDeteccion);
        tipo.setNombre(NombreTipoDeteccion);
        return mTipoDeteccion.ActualizarTipoDeteccion(tipo);
    }
    
    /*
    PRODUCTO
    */
    
    /**
     * Cambia los valores de Producto y actualiza la base de datos.
     * @param IdProducto
     * @param NombreProducto
     * @param DatosProducto
     * @return Retorna -1 si no se actualizo. Retorna el IdProducto si se actualizo.
     */
    public int EditarProducto(int IdProducto, String NombreProducto, String DatosProducto){
        Producto producto = mProducto.GetProducto(IdProducto);
        producto.setDatos(DatosProducto);
        producto.setNombre(NombreProducto);
        return mProducto.ActualizarProducto(producto);
    }
}
