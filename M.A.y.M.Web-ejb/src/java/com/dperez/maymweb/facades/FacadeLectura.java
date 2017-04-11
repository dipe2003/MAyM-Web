/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.facades;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.accion.acciones.EnumAccion;
import com.dperez.maymweb.area.Area;
import com.dperez.maymweb.codificacion.Codificacion;
import com.dperez.maymweb.controlador.registro.ControladorVistaRegistros;
import com.dperez.maymweb.deteccion.Deteccion;
import com.dperez.maymweb.empresa.Empresa;
import com.dperez.maymweb.fortaleza.Fortaleza;
import com.dperez.maymweb.usuario.Usuario;
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
public class FacadeLectura  {
    
    @Inject
    private ControladorVistaRegistros cVista;
    
    public Accion GetAccion(int IdAccion){
        return cVista.GetAccion(IdAccion);
    }
    
    public Usuario GetUsuario(int IdUsuario){
        return cVista.GetUsuario(IdUsuario);
    }
    
    /**
     * Devuelve los usuarios de la empresa especificada segun su fecha de baja.
     * @param Vigente True: si no fueron dados de baja (FechaBaja == null).
     * @param IdEmpresa -1 para todas las empresas.
     * @return Lista de Usuarios.
     */
    public List<Usuario> GetUsuariosEmpresa(boolean Vigente, int IdEmpresa){
        return cVista.GetUsuariosEmpresa(Vigente, IdEmpresa);
    }
    
    public List<Empresa> ListaEmpresasRegistradas(){
        return cVista.ListarEmpresasRegistradas();
    }
    
    public Empresa GetEmpresa(int IdEmpresa){
        return cVista.GetEmpresa(IdEmpresa);
    }
    
    public List<Deteccion> ListarDetecciones(){
        return cVista.GetDetecciones();
    }
    
    /**
     * Devuelve las codificaciones.
     * @param IdEmpresa -1 para todas las empresas
     * @return Lista de codificaciones.
     */
    public List<Codificacion> ListarCodificaciones(int IdEmpresa){
        return cVista.GetCodificaciones(IdEmpresa);
    }
    
    /**
     * Devuelve una todas las areas de una empresa.
     * @param IdEmpresa -1 para todas las empresas
     * @return lista de areas.
     */
    public List<Area> ListarAreasSectores(int IdEmpresa){
        return cVista.GetAreasEmpresa(IdEmpresa);
    }
    
    public List<Accion> ListarAccionesPreventivas(){
        return cVista.ListarAccionesSegunEstado(null, EnumAccion.PREVENTIVA);
    }
    public List<Accion> ListarAccionesCorrectivas(){
        return cVista.ListarAccionesSegunEstado(null, EnumAccion.CORRECTIVA);
    }
    public List<Accion> ListarAccionesMejoras(){
        return cVista.ListarAccionesSegunEstado(null, EnumAccion.MEJORA);
    }
    
    public Fortaleza GetFotaleza(int IdFortaleza){
        return cVista.GetFortaleza(IdFortaleza);
    }
    
    public List<Fortaleza> ListarFortalezas(){
        return cVista.ListarFortalezas();
    }
}
