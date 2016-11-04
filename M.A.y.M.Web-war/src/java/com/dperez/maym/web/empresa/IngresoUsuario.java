/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.empresa;

import com.dperez.maymweb.facades.FacadeAdministrador;
import com.dperez.maymweb.facades.FacadeMain;
import com.dperez.maymweb.empresa.Empresa;
import com.dperez.maymweb.facades.FacadeLectura;
import com.dperez.maymweb.usuario.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;



@Named
@ViewScoped
public class IngresoUsuario implements Serializable {
    @Inject
    private FacadeMain facadeMain ;
    @Inject
    private FacadeLectura fLectura;
    @Inject
    private FacadeAdministrador fAdmin;
    
    private String UsuarioSeleccionado;
    private String PasswordUsuario;
    private boolean EsAdministrador;
    private Map<String, Empresa> ListaEmpresa;
    private String EmpresaSeleccionada;
    private String NombreUsuario;
    private Map<Integer, String> Usuarios;
    
    //  Getters
    public String getUsuarioSeleccionado() {return UsuarioSeleccionado;}
    public void setPasswordUsuario(String PasswordUsuario) {this.PasswordUsuario = PasswordUsuario;}
    public boolean isEsAdministrador() {return EsAdministrador;}
    public Map<String, Empresa> getListaEmpresa(){return this.ListaEmpresa;}
    public String getEmpresaSeleccionada(){return this.EmpresaSeleccionada;}
    public String getNombreUsuario(){return this.NombreUsuario;}
    
    //  Setters
    public void setUsuarioSeleccionado(String UsuarioSeleccionado) {this.UsuarioSeleccionado = UsuarioSeleccionado;}
    public String getPasswordUsuario() {return PasswordUsuario;}
    public void setEsAdministrador(boolean EsAdministrador) {this.EsAdministrador = EsAdministrador;}
    public void setListaEmpresa(Map<String, Empresa> ListaEmpresa){this.ListaEmpresa = ListaEmpresa;}
    public void setEmpresaSeleccionada(String EmpresaSeleccionada){this.EmpresaSeleccionada = EmpresaSeleccionada;}
    public void setNombreUsuario(String NombreUsuario){this.NombreUsuario = NombreUsuario;}
    
    // inicializacion
    @PostConstruct
    public void init(){
        this.ListaEmpresa = new HashMap<>();
        List<Empresa> empresas = fLectura.ListaEmpresasRegistradas();
        for(Empresa empresa: empresas){
            ListaEmpresa.put(empresa.getNombreEmpresa(), empresa);
        }
    }
    
    //  Metodos
    public void Ingresar() throws IOException{
        String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        /*
        if(facadeMain.ExisteEmpresa(ListaEmpresa.get(EmpresaSeleccionada).getId())){
            if(facadeMain.ExisteUsuario(UsuarioSeleccionado)){
                FacesContext.getCurrentInstance().addMessage("frmLogin:inputUsuario", new FacesMessage("Existe"));
                FacesContext.getCurrentInstance().renderResponse();
                FacesContext.getCurrentInstance().getExternalContext().redirect(url+"/Views/Main/Main.xhtml");
            }
        }else{
            FacesContext.getCurrentInstance().addMessage("frmLogin:inputUsuario", new FacesMessage("No Existe"));
            FacesContext.getCurrentInstance().renderResponse();
        }
        */
    }
    
    /**
     * Comprueba la existencia del usuario y devuelve si existe.
     */
    public void ComprobarIdUsuario(){
        if(facadeMain.ExisteUsuario(Integer.valueOf(UsuarioSeleccionado))){
            UsuarioSeleccionado = fLectura.GetUsuario(Integer.valueOf(UsuarioSeleccionado)).GetNombreCompleto();
        }else{
            UsuarioSeleccionado = "";
        }
    }
    
}
