/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dperez.maymweb.facades;

import com.dperez.maymweb.area.Area;
import com.dperez.maymweb.codificacion.Codificacion;
import com.dperez.maymweb.controlador.registro.ControladorVistaRegistros;
import com.dperez.maymweb.deteccion.Deteccion;
import com.dperez.maymweb.empresa.Empresa;
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
    
    public Usuario GetUsuario(int IdUsuario){
       return cVista.GetUsuario(IdUsuario);
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
    
    public List<Codificacion> ListarCodificaciones(){
        return cVista.GetCodificaciones();
    }
    
    public List<Area> ListarAreasSectores(){
        return cVista.GetAreas();
    }
    
    
}
