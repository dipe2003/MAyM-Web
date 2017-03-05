/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.inicio;

import com.dperez.maymweb.facades.FacadeMain;
import com.dperez.maymweb.empresa.Empresa;
import com.dperez.maymweb.facades.FacadeLectura;
import com.dperez.maymweb.usuario.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import static javax.faces.application.FacesMessage.SEVERITY_FATAL;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;



@Named
@ViewScoped
public class IngresoUsuario implements Serializable {
    @Inject
    private FacadeMain facadeMain ;
    @Inject
    private FacadeLectura fLectura;
    @Inject
    private SesionUsuario sesion;
    
    private String UsuarioSeleccionado;
    private String PasswordUsuario;
    
    private Empresa EmpresaSeleccionada;
    private String NombreUsuario;
    private Map<Integer, String> Usuarios;
    
    //  Getters
    public String getUsuarioSeleccionado() {return UsuarioSeleccionado;}
    public void setPasswordUsuario(String PasswordUsuario) {this.PasswordUsuario = PasswordUsuario;}
    public Empresa getEmpresaSeleccionada(){return this.EmpresaSeleccionada;}
    public String getNombreUsuario(){return this.NombreUsuario;}
    
    //  Setters
    public void setUsuarioSeleccionado(String UsuarioSeleccionado) {this.UsuarioSeleccionado = UsuarioSeleccionado;}
    public String getPasswordUsuario() {return PasswordUsuario;}
    public void setNombreUsuario(String NombreUsuario){this.NombreUsuario = NombreUsuario;}
    public void setEmpresaSeleccionada(Empresa EmpresaSeleccionada){this.EmpresaSeleccionada = EmpresaSeleccionada;}
    
    //  Metodos
    public void ingresar() throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String url = context.getExternalContext().getRequestContextPath();
        if(facadeMain.ComprobarValidezPassword(Integer.valueOf(UsuarioSeleccionado),PasswordUsuario)){
            Usuario usuario = fLectura.GetUsuario(Integer.valueOf(UsuarioSeleccionado));
            request.getSession().setAttribute("Usuario", usuario);
            
            request.getSession().setAttribute("Empresa", EmpresaSeleccionada);
            sesion.setUsuarioLogueado(usuario);
            sesion.setEmpresaSeleccionada(EmpresaSeleccionada);
            context.getExternalContext().redirect(url);
        }else{
            context.addMessage("formlogin:pwd", new FacesMessage(SEVERITY_FATAL, "No Existe Usuario", "Los datos del usuario no son correctos"));
            context.renderResponse();
        }
    }
    
    public void logout(){
        try{
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            request.getSession().invalidate();
            sesion.setEmpresaSeleccionada(null);
            sesion.setUsuarioLogueado(null);
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/index.xhtml");
        }catch(Exception ex){}
    }
    
    /**
     * Comprueba la existencia del usuario y devuelve si existe.
     */
    public void comprobarIdUsuario(){
        int id = 0;
        try{
            id = Integer.valueOf(UsuarioSeleccionado);
            if(id!=0 && facadeMain.ExisteUsuario(id)){
                Usuario usuario = fLectura.GetUsuario(Integer.valueOf(UsuarioSeleccionado));
                NombreUsuario = usuario.GetNombreCompleto();
                EmpresaSeleccionada = usuario.getEmpresaUsuario();
            }
        }catch(NumberFormatException ex){
            System.out.println("Error: " +ex.getLocalizedMessage());
            NombreUsuario = "";
        }
    }
    
}
