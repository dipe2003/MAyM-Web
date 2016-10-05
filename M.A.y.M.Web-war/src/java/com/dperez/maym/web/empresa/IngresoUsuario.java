/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.empresa;

import com.dperez.maymweb.facades.FacadeMain;
import com.dperez.maymweb.persistencia.ControladorAdministrador;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
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
public class IngresoUsuario implements Serializable {
   
    private FacadeMain facadeMain ;
    @Inject
    private ControladorAdministrador cAdmin;
    
    private String UsuarioSeleccionado;
    private String PasswordUsuario;
    
    
    //  Getters
    public String getUsuarioSeleccionado() {return UsuarioSeleccionado;}
    public void setPasswordUsuario(String PasswordUsuario) {this.PasswordUsuario = PasswordUsuario;}    
    
    //  Setters
    public void setUsuarioSeleccionado(String UsuarioSeleccionado) {this.UsuarioSeleccionado = UsuarioSeleccionado;}
    public String getPasswordUsuario() {return PasswordUsuario;}
    
    //  Metodos
    public void ingresar() throws IOException{
        String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        FacesContext.getCurrentInstance().getExternalContext().redirect(url+"/Views/Main/Main.xhtml");
    }
    
    public void ComprobarUsuario(String EmpresaUsuario){
        String[] res = EmpresaUsuario.split("-");
        String nombreEmpresa = res[0];
        String nickname = res[1];
        if(cAdmin.ExisteEmpresa(nombreEmpresa)){
            if(cAdmin.ExisteUsuario(nickname)){
                FacesContext.getCurrentInstance().addMessage("frmLogin:inputUsuario", new FacesMessage("Existe"));
                FacesContext.getCurrentInstance().renderResponse();
            }
        }else{
            FacesContext.getCurrentInstance().addMessage("frmLogin:inputUsuario", new FacesMessage("No Existe"));
            FacesContext.getCurrentInstance().renderResponse();
        }
    }
    
//    public void CrearBaseDatos(){
//        int res = cAdmin.CrearBaseDeDatos(NombreEmpresa);
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
