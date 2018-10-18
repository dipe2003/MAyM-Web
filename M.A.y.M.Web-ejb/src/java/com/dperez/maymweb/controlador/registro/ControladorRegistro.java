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
import com.dperez.maymweb.accion.adjunto.Adjunto;
import com.dperez.maymweb.accion.actividad.ManejadorActividad;
import com.dperez.maymweb.accion.actividad.Actividad;
import com.dperez.maymweb.accion.adjunto.EnumTipoAdjunto;
import com.dperez.maymweb.accion.adjunto.ManejadorAdjunto;
import com.dperez.maymweb.area.Area;
import com.dperez.maymweb.area.ManejadorArea;
import com.dperez.maymweb.codificacion.Codificacion;
import com.dperez.maymweb.codificacion.ManejadorCodificacion;
import com.dperez.maymweb.deteccion.Deteccion;
import com.dperez.maymweb.deteccion.ManejadorDeteccion;
import com.dperez.maymweb.empresa.Empresa;
import com.dperez.maymweb.empresa.ManejadorEmpresa;
import com.dperez.maymweb.fortaleza.Fortaleza;
import com.dperez.maymweb.fortaleza.ManejadorFortaleza;
import com.dperez.maymweb.producto.ManejadorProducto;
import com.dperez.maymweb.producto.Producto;
import com.dperez.maymweb.usuario.ManejadorUsuario;
import com.dperez.maymweb.usuario.Usuario;
import java.security.InvalidParameterException;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Diego
 */
@Named
@Stateless
public class ControladorRegistro {
    @Inject
    private ManejadorAccion mAccion;
    @Inject
    private ManejadorArea mArea;
    @Inject
    private ManejadorAdjunto mAdjunto;
    @Inject
    private ManejadorDeteccion mDeteccion;
    @Inject
    private ManejadorActividad mMedida;
    @Inject
    private ManejadorUsuario mUsuario;
    @Inject
    private ManejadorEmpresa mEmpresa;
    @Inject
    private ManejadorFortaleza mFortaleza;
    @Inject
    private ManejadorCodificacion mCodificacion;
    @Inject
    private ManejadorProducto mProducto;
    
    //  Constructores
    public ControladorRegistro(){}
    
    /**
     * Crea una nueva accion en estado pendiente y la persiste en la base de datos.
     * Se codifica automaticamente la accion como 'Sin Codificar'. Si la codificacon no existe se crea.
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
        Accion accion = null;
        switch(TipoAccion){
            case CORRECTIVA:
                accion = new Correctiva(FechaDeteccion, Descripcion, TipoDesvio);
                break;
                
            case MEJORA:
                accion = new Mejora(FechaDeteccion, Descripcion);
                break;
        }
        if(accion!=null){
            Empresa empresa = mEmpresa.GetEmpresa(IdEmpresa);
            Area area = mArea.GetArea(IdAreaSector);
            accion.setAreaSectorAccion(area);
            Deteccion deteccion = mDeteccion.GetDeteccion(IdDeteccion);
            accion.setGeneradaPor(deteccion);
            accion.setEmpresaAccion(empresa);
            // codificacion
            List<Codificacion> codificaciones = mCodificacion.ListarCodificaciones();
            int existe = 0;
            existe = codificaciones.stream()
                    .filter(codificacion -> codificacion.getNombre().equalsIgnoreCase("Sin Codificar"))
                    .findFirst()
                    .get().getId();
            if(existe==0){
                Codificacion codificacion = new Codificacion("Sin Codificar", "No se ha asignado codificacion aun.");
                codificacion.setId(mCodificacion.CrearCodificacion(codificacion));
                empresa.addCodificacionEmpresa(codificacion);
                mEmpresa.ActualizarEmpresa(empresa);
                accion.setCodificacionAccion(codificacion);
            }else{
                Codificacion codificacion = mCodificacion.GetCodificacion(existe);
                accion.setCodificacionAccion(codificacion);
            }
            accion.setId(mAccion.CrearAccion(accion));
        }
        if(accion.getId()!=-1){
            return accion;
        }else{
            return null;
        }
    }
    
    /**
     * Crea el/los productos involucrados en el desvio, los agrega a la accion correctiva y actualiza la base de datos.
     * @param AccionCorrectiva
     * @param NombreProducto
     * @param DatosProducto
     * @return -1 si no se creo.
     */
    public int AgregarProductoInvolucrado(int AccionCorrectiva, String NombreProducto, String DatosProducto){
        Correctiva correctiva = (Correctiva) mAccion.GetAccion(AccionCorrectiva);
        Producto producto = correctiva.addProductoAfectado(NombreProducto, DatosProducto);
        if(mProducto.CrearProducto(producto)!=-1){
            return mAccion.ActualizarAccion(correctiva);
        }
        return -1;
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
        Accion accion = mAccion.GetAccion(IdAccion);
        Adjunto adjunto = new Adjunto(TituloAdjunto, UbicacionAdjunto, TipoAdjunto);
        if(mAdjunto.CrearAdjunto(adjunto)!=-1){
            accion.addAdjunto(adjunto);
        }
        return mAccion.ActualizarAccion(accion);
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
        Actividad medida = new Actividad(FechaEstimadaImplementacion, Descripcion);
        Usuario usuario = mUsuario.GetUsuario(IdUsuarioResponsable);
        medida.setResponsableImplementacion(usuario);
        medida.setIdActividad(mMedida.CrearActividad(medida));
        Accion accion = mAccion.GetAccion(IdAccion);
        if((accion.getClass())!= Mejora.class) throw new InvalidParameterException("El id no corresponde con una mejora");
        ((Mejora)accion).addActividadMejora((Actividad)medida);
        accion.CambiarEstado();
        mAccion.ActualizarAccion(accion);
        return medida.getIdActividad();
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
        Accion accion = mAccion.GetAccion(IdAccion);
        if((accion.getClass())!= Correctiva.class) {
            throw new InvalidParameterException("El id no corresponde con una Correctiva");
        }else{
            Actividad medida = ((Correctiva)accion).addMedidaPreventiva(FechaEstimadaImplementacion, Descripcion);
            Usuario usuario = mUsuario.GetUsuario(IdUsuarioResponsable);
            medida.setResponsableImplementacion(usuario);
            accion.CambiarEstado();
            mMedida.CrearActividad(medida);
            if(mAccion.ActualizarAccion(accion)!=-1){
                return medida.getIdActividad();
            }
            return -1;
        }
    }
    
    /**
     * Crea una nueva medida correctiva, la persiste en la base de datos y se asocia a la accion correctiva.
     * @param IdAccion
     * @param FechaEstimadaImplementacion
     * @param Descripcion
     * @param IdUsuarioResponsable
     * @return -1 si no se creo.
     */
    public int AgregarMedidaCorrectiva(int IdAccion, Date FechaEstimadaImplementacion, String Descripcion, int IdUsuarioResponsable){
        Accion accion = mAccion.GetAccion(IdAccion);
        if((accion.getClass())!= Correctiva.class) {
            throw new InvalidParameterException("El id no corresponde con una Correctiva");
        }else{
            Actividad medida = ((Correctiva)accion).addMedidaCorrectiva(FechaEstimadaImplementacion, Descripcion);
            Usuario usuario = mUsuario.GetUsuario(IdUsuarioResponsable);
            medida.setResponsableImplementacion(usuario);
            accion.CambiarEstado();
             mMedida.CrearActividad(medida);
            if(mAccion.ActualizarAccion(accion)!=-1){
                return medida.getIdActividad();
            }
            return -1;
        }
    }
    
    /**
     * Setea la fecha de implementacion de la Actividad correctiva, cambia el estado de la accion (si corresponde) y persiste los cambios en la base de deatos.
     * @param FechaImplementacion
     * @param IdMedidaCorrectiva
     * @param IdAccion
     * @return -1 si no se pudo actualizar.
     */
    public int SetFechaImplementacionMedidaCorrectiva(Date FechaImplementacion, int IdMedidaCorrectiva, int IdAccion){
        Actividad medida = mMedida.GetActividad(IdMedidaCorrectiva);
        medida.setFechaImplementacion(FechaImplementacion);
        int res = mMedida.ActualizarActividad(medida);
        if(res!=-1){
            Accion AccionCorrectiva = mAccion.GetAccion(IdAccion);
            AccionCorrectiva.CambiarEstado();
            res = mAccion.ActualizarAccion(AccionCorrectiva);
        }
        return res;
    }
    
    /**
     * Setea la fecha de implementacion de la Actividad Preventiva, cambia el estado de la accion (si corresponde) y persiste los cambios en la base de deatos.
     * @param FechaImplementacion
     * @param IdMedidaPreventiva
     * @param IdAccion
     * @return -1 si no se pudo actualizar.
     */
    public int SetFechaImplementacionMedidaPreventiva(Date FechaImplementacion, int IdMedidaPreventiva, int IdAccion){
        Actividad medida = mMedida.GetActividad(IdMedidaPreventiva);
        medida.setFechaImplementacion(FechaImplementacion);
        int res = mMedida.ActualizarActividad(medida);
        if(res!=-1){
            Accion AccionCorrectiva = mAccion.GetAccion(IdAccion);
            AccionCorrectiva.CambiarEstado();
            res = mAccion.ActualizarAccion(AccionCorrectiva);
        }
        return res;
    }
    
    /**
     * Setea la fecha de implementacion de la Actividad Preventiva, cambia el estado de la accion (si corresponde) y persiste los cambios en la base de deatos.
     * @param FechaImplementacion
     * @param IdActividadPreventiva
     * @param IdAccion
     * @return -1 si no se pudo actualizar.
     */
    public int SetFechaImplementacionActividadPreventiva(Date FechaImplementacion, int IdActividadPreventiva, int IdAccion){
        Actividad medida = mMedida.GetActividad(IdActividadPreventiva);
        medida.setFechaImplementacion(FechaImplementacion);
        int res = mMedida.ActualizarActividad(medida);
        if(res!=-1){
            Accion AccionPreventiva = mAccion.GetAccion(IdAccion);
            AccionPreventiva.CambiarEstado();
            res = mAccion.ActualizarAccion(AccionPreventiva);
        }
        return res;
    }
    
    /**
     * Setea la fecha de implementacion de la Actividad de Mejora, cambia el estado de la accion (si corresponde) y persiste los cambios en la base de deatos.
     * @param FechaImplementacion
     * @param IdActividadMejora
     * @param IdAccion
     * @return -1 si no se pudo actualizar.
     */
    public int SetFechaImplementacionActividadMejora(Date FechaImplementacion, int IdActividadMejora, int IdAccion){
        Actividad medida = mMedida.GetActividad(IdActividadMejora);
        medida.setFechaImplementacion(FechaImplementacion);
        int res = mMedida.ActualizarActividad(medida);
        if(res!=-1){
            Accion AccionMejora = mAccion.GetAccion(IdAccion);
            AccionMejora.CambiarEstado();
            res = mAccion.ActualizarAccion(AccionMejora);
        }
        return res;
    }
    
    /**
     * Setea la comprobacion de implementacion estimada con responsable y fecha estimada. Actualiza la base de datos.
     * @param FechaEstimada
     * @param IdUsuarioResponsableImplementacion
     * @param IdAccion
     * @return -1 si no se actualizo. IdAccion si correcto.
     */
    public int SetEstimadoComprobacionImplementacion(Date FechaEstimada, int IdUsuarioResponsableImplementacion, int IdAccion){
        Usuario usuario = mUsuario.GetUsuario(IdUsuarioResponsableImplementacion);
        Comprobacion comprobacionImplementacion = new Comprobacion(FechaEstimada, usuario);
        comprobacionImplementacion.setId(mAccion.crearComprobacion(comprobacionImplementacion));
        Accion accion = mAccion.GetAccion(IdAccion);
        accion.setComprobacionImplementacion(comprobacionImplementacion);
        return mAccion.ActualizarAccion(accion);
    }
    
    /**
     * Setea la comprobacion de eficacia estimada con responsable y fecha estimada. Actualiza la base de datos.
     * @param FechaEstimada
     * @param IdUsuarioResponsableComprobacion
     * @param IdAccion
     * @return -1 si no se actualizo. IdAccion si correcto.
     */
    public int SetEstimadoComprobacionEficacia(Date FechaEstimada, int IdUsuarioResponsableComprobacion, int IdAccion){
        Usuario usuario = mUsuario.GetUsuario(IdUsuarioResponsableComprobacion);
        Comprobacion comprobacionEficacia = new Comprobacion(FechaEstimada, usuario);
        comprobacionEficacia.setId(mAccion.crearComprobacion(comprobacionEficacia));
        Accion accion = mAccion.GetAccion(IdAccion);
        accion.setComprobacionEficacia(comprobacionEficacia);
        return mAccion.ActualizarAccion(accion);
    }
    
    /**
     * Setea la comprobacion de implementacion de la accion, cambia el estado segun corresponda y actualiza la base de datos.
     * PRE: la accion debe tener comprobacion != null (seteada fecha estimada de comprobacion de implementacion)
     * @param FechaComprobacionImplementacion
     * @param ComentariosImplementacion
     * @param Comprobacion
     * @param IdAccion
     * @return -1 si no se actualizo
     */
    public int SetComprobacionImplementacionAccion(Date FechaComprobacionImplementacion, String ComentariosImplementacion, EnumComprobacion Comprobacion,
            int IdAccion){
        Accion accion = mAccion.GetAccion(IdAccion);
        accion.getComprobacionImplementacion().setFechaComprobacion(FechaComprobacionImplementacion);
        accion.getComprobacionImplementacion().setObservaciones(ComentariosImplementacion);
        accion.getComprobacionImplementacion().setResultado(Comprobacion);
        accion.CambiarEstado();
        return mAccion.ActualizarAccion(accion);
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
        Accion accion = mAccion.GetAccion(IdAccion);
        accion.getComprobacionEficacia().setFechaComprobacion(FechaVerificacionEficacia);
        accion.getComprobacionEficacia().setObservaciones(ComentariosVerificacion);
        accion.getComprobacionEficacia().setResultado(Comprobacion);
        accion.CambiarEstado();
        return mAccion.ActualizarAccion(accion);
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
        Fortaleza fortaleza = new Fortaleza(FechaDeteccion, Descripcion);
        
        Area area = mArea.GetArea(IdAreaSector);
        Deteccion deteccion = mDeteccion.GetDeteccion(IdDeteccion);
        Empresa empresa = mEmpresa.GetEmpresa(IdEmpresa);
        fortaleza.setAreaSectorFortaleza(area);
        fortaleza.setEmpresaFortaleza(empresa);
        fortaleza.setGeneradaPor(deteccion);
        fortaleza.setId(mFortaleza.CrearFortaleza(fortaleza));
        
        if(fortaleza.getId()== -1){
            return null;
        }
        return fortaleza;
    }
    
}
