/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.controlador.registro;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.accion.ManejadorAccion;
import com.dperez.maymweb.accion.acciones.EnumAccion;
import com.dperez.maymweb.area.Area;
import com.dperez.maymweb.area.ManejadorArea;
import com.dperez.maymweb.codificacion.Codificacion;
import com.dperez.maymweb.codificacion.ManejadorCodificacion;
import com.dperez.maymweb.deteccion.Deteccion;
import com.dperez.maymweb.deteccion.ManejadorDeteccion;
import com.dperez.maymweb.empresa.Empresa;
import com.dperez.maymweb.empresa.ManejadorEmpresa;
import com.dperez.maymweb.estado.EnumEstado;
import com.dperez.maymweb.fortaleza.Fortaleza;
import com.dperez.maymweb.fortaleza.ManejadorFortaleza;
import com.dperez.maymweb.usuario.ManejadorUsuario;
import com.dperez.maymweb.usuario.Usuario;
import java.util.Iterator;
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
public class ControladorVistaRegistros {
    @Inject
    private ManejadorAccion mAccion;
    @Inject
    private ManejadorUsuario mUsuario;
    @Inject
    private ManejadorEmpresa mEmpresa;
    @Inject
    private ManejadorDeteccion mDeteccion;
    @Inject
    private ManejadorCodificacion mCodificaciones;
    @Inject
    private ManejadorArea mArea;
    @Inject
    private ManejadorFortaleza mFortaleza;
    
    //  Constructores
    public ControladorVistaRegistros(){}
    
    /**
     * Devuelve una lista con todas las acciones registradas que se encuentran en el estado especificado.
     * Si EstadoAccion es null se devuelven todas las acciones registradas.
     * @param EstadoAccion
     * @return
     */
    public List<Accion> ListarAccionesSegunEstado(EnumEstado EstadoAccion){
        List<Accion> acciones;
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
        List<Accion> acciones;
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
                    Accion accion = (Accion)it.next();
                    if(!accion.getClass().getSimpleName().equalsIgnoreCase(TipoAccion.toString()))
                        it.remove();
                }
            }
        }
        return acciones;
    }
    
    
    /**
     * Devuelve la accion indicada por su id
     * @param IdAccion
     * @return 
     */
    public Accion GetAccion(int IdAccion){
        return mAccion.GetAccion(IdAccion);
    }
    
    /**
     * Devuelve el usuario especificado por su id
     * @param IdUsuario
     * @return Null si no existe el usuario.
     */
    public Usuario GetUsuario(int IdUsuario){
        return mUsuario.GetUsuario(IdUsuario);
    }
        
    /**
     * Devuelve los usuarios de la empresa especificada segun su fecha de baja.
     * @param Vigente True: si no fueron dados de baja (FechaBaja == null).
     * @param IdEmpresa -1 para todas las empresas.
     * @return Lista de Usuarios.
     */
    public List<Usuario> GetUsuariosEmpresa(boolean Vigente, int IdEmpresa){
        List<Usuario> lstUsuarios = mUsuario.ListarUsuarios();
        if(IdEmpresa != -1){
            Iterator it = lstUsuarios.iterator();
            while(it.hasNext()){
                Usuario usr = (Usuario) it.next();
                if(usr.getEmpresaUsuario().getId() != IdEmpresa){
                    it.remove();
                }else{
                    if(Vigente){
                        if(usr.getFechaBaja() != null){
                            it.remove();
                        }
                    }
                }
            }
        }
        return lstUsuarios;
    }
    
    /**
     * Devuelve una lista de detecciones.
     * @return
     */
    public List<Deteccion> GetDetecciones(){
        return mDeteccion.ListarDetecciones();
    }
    
    /**
     * Devuelve las codificaciones.
     * @param IdEmpresa -1 para todas las empresas
     * @return Lista de codificaciones.
     */
    public List<Codificacion> GetCodificaciones(int IdEmpresa){
        List<Codificacion> lstCodificaciones = mCodificaciones.ListarCodificaciones();
        if(IdEmpresa != -1){
            Iterator<Codificacion> it = lstCodificaciones.iterator();
            while(it.hasNext()){
                Codificacion cod = it.next();
                if(!cod.contieneEmpresa(IdEmpresa)){
                    it.remove();
                }
            }
        }
        return lstCodificaciones;
    }
    
    /**
     * Devuelve una todas las areas de una empresa.
     * @param IdEmpresa -1 para todas las empresas
     * @return lista de areas.
     */
    public List<Area> GetAreasEmpresa(int IdEmpresa){
        List<Area> lstAreas = mArea.ListarAreas();
        if(IdEmpresa != -1){
            Iterator<Area> it = lstAreas.iterator();
            while(it.hasNext()){
                Area area = it.next();
                if(!area.contieneEmpresa(IdEmpresa)){
                    it.remove();
                }
            }
        }
        return lstAreas;
    }
    
    /**
     * Lista todos los usuarios de la empresa seleccionada por id.
     * @param IdEmpresa
     * @return
     */
    public List<Usuario> ListarUsuarios(int IdEmpresa){
        List<Usuario> usuarios = mUsuario.ListarUsuarios();
        Iterator<Usuario> it = usuarios.iterator();
        while(it.hasNext()){
            Usuario usuario = it.next();
            if(usuario.getEmpresaUsuario().getId()!= IdEmpresa) it.remove();
        }
        return usuarios;
    }
    
    /**
     * Lista todas las empresas registradas.
     * @return
     */
    public List<Empresa> ListarEmpresasRegistradas(){
        return mEmpresa.ListarEmpresasRegistradas();
    }
    
    /**
     * Devuelve la empresa indicada por su id.
     * @param IdEmpresa
     * @return Retorne Null si no se encontro empresa.
     */
    public Empresa GetEmpresa(int IdEmpresa){
        return mEmpresa.GetEmpresa(IdEmpresa);
    }
    
    /**
     * Devuelve la fortaleza indicada por su id
     * @param IdFortaleza
     * @return Retorna null si no se encontro fortaleza.
     */
    public Fortaleza GetFortaleza(int IdFortaleza){
        return mFortaleza.GetFortaleza(IdFortaleza);
    }
    
    /**
     * Lista todas las fortalezas registradas
     * @param IdEmpresa -1 para todas las empresas
     * @return 
     */
    public List<Fortaleza> ListarFortalezas(int IdEmpresa){
        List<Fortaleza> lstFortalezas = mFortaleza.ListarFortalezas();
        if(IdEmpresa != -1){
            Iterator<Fortaleza> it = lstFortalezas.iterator();
            while(it.hasNext()){
                Fortaleza fortaleza = it.next();
                if(!(fortaleza.getEmpresaFortaleza().getId() == IdEmpresa)){
                    it.remove();
                }
            }
        }
        return lstFortalezas;
    }
    
}
