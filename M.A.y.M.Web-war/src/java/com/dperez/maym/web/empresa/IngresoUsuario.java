/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.empresa;

import com.dperez.maymweb.facades.FacadeMain;
import com.dperez.maymweb.persistencia.ControladorEmpresa;
import com.dperez.maymwebdbadmin.administrador.Administrador;
import com.dperez.maymwebdbadmin.administrador.ControladorAdministrador;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;



@Named
@ViewScoped
public class IngresoUsuario implements Serializable {
    
    private FacadeMain facadeMain ;
    @Inject
    private ControladorEmpresa cEmpresa;
    @Inject
    private ControladorAdministrador cAdmin;
    
    private String UsuarioSeleccionado;
    private String PasswordUsuario;
    private boolean EsAdministrador;
    
    
    //  Getters
    public String getUsuarioSeleccionado() {return UsuarioSeleccionado;}
    public void setPasswordUsuario(String PasswordUsuario) {this.PasswordUsuario = PasswordUsuario;}
    public boolean isEsAdministrador() {return EsAdministrador;}
    
    //  Setters
    public void setUsuarioSeleccionado(String UsuarioSeleccionado) {this.UsuarioSeleccionado = UsuarioSeleccionado;}
    public String getPasswordUsuario() {return PasswordUsuario;}
    public void setEsAdministrador(boolean EsAdministrador) {this.EsAdministrador = EsAdministrador;}
    
    //  Metodos
    public void ingresar() throws IOException{
        String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        FacesContext.getCurrentInstance().getExternalContext().redirect(url+"/Views/Main/Main.xhtml");
    }
    
    public void Ingresar() throws IOException{
        String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        if(!EsAdministrador){
            String[] res = UsuarioSeleccionado.split("-");
            String nombreEmpresa = res[0];
            String nickname = res[1];
            if(cEmpresa.ExisteEmpresa(nombreEmpresa)){
                if(cEmpresa.ExisteUsuario(nickname)){
                    FacesContext.getCurrentInstance().addMessage("frmLogin:inputUsuario", new FacesMessage("Existe"));
                    FacesContext.getCurrentInstance().renderResponse();
                    FacesContext.getCurrentInstance().getExternalContext().redirect(url+"/Views/Main/Main.xhtml");
                }
            }else{
                FacesContext.getCurrentInstance().addMessage("frmLogin:inputUsuario", new FacesMessage("No Existe"));
                FacesContext.getCurrentInstance().renderResponse();
            }
        }else{
            Administrador admin = cAdmin.GetAdministrador(UsuarioSeleccionado, PasswordUsuario);
            if(admin!=null){
                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();                
                request.getSession().setAttribute("Usuario", admin);
                FacesContext.getCurrentInstance().getExternalContext().redirect(url+"/Views/Main/Administrador.xhtml");
            }else{
                FacesContext.getCurrentInstance().addMessage("frmLogin:inputUsuario", new FacesMessage("No Existe"));
                FacesContext.getCurrentInstance().renderResponse();
            }
        }
    }
    
//    public void CrearBaseDatos(){
//        int res = cEmpresa.CrearBaseDeDatos(NombreEmpresa);
//        if(res>0){
//            FacesContext.getCurrentInstance().addMessage("frmIngreso:inputUsuario", new FacesMessage("Base de Datos y Empresa Creadas"));
//            FacesContext.getCurrentInstance().renderResponse();
//        }
//        if(res==-1){
//            FacesContext.getCurrentInstance().addMessage("frmIngreso:inputUsuario", new FacesMessage("No Creadas"));
//            FacesContext.getCurrentInstance().renderResponse();
//        }
//    }
    
}
