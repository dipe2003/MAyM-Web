/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dperez.maym.web.empresa;

import com.dperez.maymweb.persistencia.Empresa;
import com.dperez.maymweb.persistencia.ManejadorEmpresa;
import com.dperez.maymweb.usuario.ControladorUsuario;
import com.dperez.maymweb.usuario.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Diego
 */

@Named
@ViewScoped
public class ListarEmpresas implements Serializable {
    @Inject
    private ManejadorEmpresa mEmp;
    @Inject
    private ControladorUsuario cUsr ;
    
    private Map<String, Integer> MapEmpresas;
    private int EmpresaSeleccionada;
    
    private Map<String, Integer> MapUsuarios;
    private int UsuarioSeleccionado;

 
    public Map<String, Integer> getMapEmpresas() {return MapEmpresas;}    
    public int getEmpresaSeleccionada(){return this.EmpresaSeleccionada;}

    public Map<String, Integer> getMapUsuarios() {return MapUsuarios;}
    public int getUsuarioSeleccionado() {return UsuarioSeleccionado;}
    
    
    public void setMapEmpresas(Map<String, Integer> MapEmpresas) {this.MapEmpresas = MapEmpresas;}    
    public void setEmpresaSeleccionada(int EmpresaSeleccionada){
        this.EmpresaSeleccionada = EmpresaSeleccionada;        
    }

    public void setMapUsuarios(Map<String, Integer> MapUsuarios) {this.MapUsuarios = MapUsuarios;}
    public void setUsuarioSeleccionado(int UsuarioSeleccionado) {this.UsuarioSeleccionado = UsuarioSeleccionado;}    
    
    public void ingresar() throws IOException{
        String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        FacesContext.getCurrentInstance().getExternalContext().redirect(url+"/Views/Main/Main.xhtml");
    }
    
    public void actualizarListaUsuario(){
        List<Usuario> listaUsuario = cUsr.GetUsuariosEmpresa(EmpresaSeleccionada);
    }
    
    @PostConstruct
    private void init(){
//        cUsr = new ControladorUsuario();
        MapEmpresas = new HashMap<String, Integer>();
        List<Empresa> lista = mEmp.ListarEmpresas();
        MapEmpresas.put("--- Selecciona Una Empresa ---", 0);
        for(Empresa emp: lista){
            MapEmpresas.put(emp.getNombreEmpresa(), emp.getId());
        }
        MapUsuarios = new HashMap<String, Integer>();
        MapUsuarios.put("---Selecciona Un Usuario---", 0);
    }
    
    
}
