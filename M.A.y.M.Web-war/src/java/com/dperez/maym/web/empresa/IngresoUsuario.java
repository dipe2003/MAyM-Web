/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.empresa;

import com.dperez.maymweb.persistencia.ControladorAdministrador;
import com.dperez.maymweb.persistencia.ManejadorEmpresa;
import com.dperez.maymweb.usuario.ControladorUsuario;
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
    @Inject
    private ManejadorEmpresa mEmp;
    @Inject
    private ControladorUsuario cUsr ;
    @Inject
    private ControladorAdministrador cAdmin;
    
    private String UsuarioSeleccionado;
    private String NombreEmpresa;
    
    public String getUsuarioSeleccionado() {return UsuarioSeleccionado;}

    public String getNombreEmpresa() {
        return NombreEmpresa;
    }
    
    public void setUsuarioSeleccionado(String UsuarioSeleccionado) {this.UsuarioSeleccionado = UsuarioSeleccionado;}

    public void setNombreEmpresa(String NombreEmpresa) {
        this.NombreEmpresa = NombreEmpresa;
    }
    
    public void ingresar() throws IOException{
        String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        FacesContext.getCurrentInstance().getExternalContext().redirect(url+"/Views/Main/Main.xhtml");
    }
    
    public void ComprobarUsuario(String EmpresaUsuario){
        String[] res = EmpresaUsuario.split("-");
        String nombreEmpresa = res[0];
        String nombreUsuario = res[1];
        if(cAdmin.ExisteEmpresa(nombreEmpresa)){
            if(cUsr.ExisteUsuario(nombreUsuario)){
                FacesContext.getCurrentInstance().addMessage("frmIngreso:inputUsuario", new FacesMessage("Existe"));
                FacesContext.getCurrentInstance().renderResponse();
            }
        }else{
            FacesContext.getCurrentInstance().addMessage("frmIngreso:inputUsuario", new FacesMessage("No Existe"));
            FacesContext.getCurrentInstance().renderResponse();
        }
    }
    
    public void CrearBaseDatos(){
        int res = cAdmin.CrearBaseDeDatos(NombreEmpresa);
        if(res>0){
            FacesContext.getCurrentInstance().addMessage("frmIngreso:inputUsuario", new FacesMessage("Base de Datos y Empresa Creadas"));
            FacesContext.getCurrentInstance().renderResponse();
        }
        if(res==-1){
            FacesContext.getCurrentInstance().addMessage("frmIngreso:inputUsuario", new FacesMessage("No Creadas"));
            FacesContext.getCurrentInstance().renderResponse();
        }
    }
    
}
