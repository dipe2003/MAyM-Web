/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.inicio;

import com.dperez.maymweb.empresa.Empresa;
import com.dperez.maymweb.facades.FacadeLectura;
import com.dperez.maymweb.facades.FacadeMain;
import com.dperez.maymweb.usuario.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import static javax.faces.application.FacesMessage.SEVERITY_FATAL;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author dperez
 */
@Named("sesionUsuario")
@ManagedBean
@SessionScoped
public class SesionUsuario implements Serializable {
    @Inject
    private FacadeMain facadeMain ;
    @Inject
    private FacadeLectura fLectura;
    
    private String UsuarioSeleccionado;
    private String PasswordUsuario;
    
    private Empresa EmpresaSeleccionada;
    private String NombreUsuario;
    private Map<Integer, Usuario> Usuarios;
    
    private Usuario UsuarioLogueado;
    private Empresa EmpresaLogueado;
    
    // Sesion
    // Geters
    public Usuario getUsuarioLogueado(){return UsuarioLogueado;}
    public Empresa getEmpresaLogueado(){return EmpresaLogueado;}
    // Setters
    public void setUsuarioLogueado(Usuario UsuarioLogueado){this.UsuarioLogueado = UsuarioLogueado;}
    public void setEmpresaLogueado(Empresa EmpresaLogueado){this.EmpresaLogueado = EmpresaLogueado;}
    // Metodos
    public Date getFechaActual(){return new Date();}
    
    // Login
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
    @PostConstruct
    public void init(){
        this.Usuarios = new HashMap<>();
        List<Usuario> usuarios = fLectura.GetUsuarios();
        for(Usuario usr:usuarios){
            this.Usuarios.put(usr.getId(), usr);
        }
    }
    public void ingresar() throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String url = context.getExternalContext().getRequestContextPath();
        if(facadeMain.ComprobarValidezPassword(Integer.valueOf(UsuarioSeleccionado),PasswordUsuario)){
            Usuario usuario = fLectura.GetUsuario(Integer.valueOf(UsuarioSeleccionado));
            request.getSession().setAttribute("Usuario", usuario);
            
            request.getSession().setAttribute("Empresa", EmpresaSeleccionada);
            this.UsuarioLogueado = usuario;
            this.EmpresaLogueado = EmpresaSeleccionada;
            
            this.PasswordUsuario = new String();
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
            this.EmpresaLogueado = null;
            this.UsuarioLogueado = null;
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/index.xhtml");
        }catch(Exception ex){
            System.out.println("Error en logout: " + ex.getMessage());
        }
    }
    
    /**
     * Comprueba la existencia del usuario y devuelve si existe.
     */
    public void comprobarIdUsuario(){
        int id = 0;
        try{
            id = Integer.valueOf(UsuarioSeleccionado);
            if(id!=0 && facadeMain.ExisteUsuario(id)){
                Usuario usuario = Usuarios.get(id);
                NombreUsuario = usuario.GetNombreCompleto();
                EmpresaSeleccionada = usuario.getEmpresaUsuario();
            }
        }catch(NumberFormatException ex){
            System.out.println("Error: " +ex.getLocalizedMessage());
            NombreUsuario = "";
        }
    }
}
