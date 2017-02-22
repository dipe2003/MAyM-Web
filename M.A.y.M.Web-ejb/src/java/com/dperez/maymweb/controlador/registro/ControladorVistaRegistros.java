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
     * Devuelve todos los usuarios registrados.
     * @return 
     */
    public List<Usuario> GetUsuarios(){
        return mUsuario.ListarUsuarios();
    }
    
    /**
     * Devuelve todos usuarios registrados que pertenecen a la empresa indicada.
     * @param IdEmpresa Id de la empresa de los usuarios
     * @return
     */
    public List<Usuario> GetUsuariosEmpresa(int IdEmpresa){
        List<Usuario> usuarios = mUsuario.ListarUsuarios();
        Iterator<Usuario> it = usuarios.iterator();
        while(it.hasNext()){
            Usuario usuario = it.next();
            if(usuario.getEmpresaUsuario().getId() != IdEmpresa) it.remove();
        }
        return usuarios;
    }
    
    /**
     * Devuelve una lista de detecciones.
     * @return
     */
    public List<Deteccion> GetDetecciones(){
        return mDeteccion.ListarDetecciones();
    }
    
    /**
     * Devuelve una lista de codificaciones.
     * @return
     */
    public List<Codificacion> GetCodificaciones(){
        return mCodificaciones.ListarCodificaciones();
    }
    
    /**
     * Devuelve una lista de areas.
     * @return 
     */
    public List<Area> GetAreas(){
        return mArea.ListarAreas();
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
     * @return 
     */
    public List<Fortaleza> ListarFortalezas(){
        return mFortaleza.ListarFortalezas();
    }
    
}
