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
    
    private String UsuarioSeleccionado;
    private String PasswordUsuario;
    private Map<String, Integer> ListaEmpresa;
    private int EmpresaSeleccionada;
    private String NombreUsuario;
    private Map<Integer, String> Usuarios;
    
    //  Getters
    public String getUsuarioSeleccionado() {return UsuarioSeleccionado;}
    public void setPasswordUsuario(String PasswordUsuario) {this.PasswordUsuario = PasswordUsuario;}
    public Map<String, Integer> getListaEmpresa(){return this.ListaEmpresa;}
    public int getEmpresaSeleccionada(){return this.EmpresaSeleccionada;}
    public String getNombreUsuario(){return this.NombreUsuario;}
    
    //  Setters
    public void setUsuarioSeleccionado(String UsuarioSeleccionado) {this.UsuarioSeleccionado = UsuarioSeleccionado;}
    public String getPasswordUsuario() {return PasswordUsuario;}
    public void setListaEmpresa(Map<String, Integer> ListaEmpresa){this.ListaEmpresa = ListaEmpresa;}
    public void setEmpresaSeleccionada(int EmpresaSeleccionada){this.EmpresaSeleccionada = EmpresaSeleccionada;}
    public void setNombreUsuario(String NombreUsuario){this.NombreUsuario = NombreUsuario;}
    
    // inicializacion
    @PostConstruct
    public void init(){
        this.ListaEmpresa = new HashMap<>();
        List<Empresa> empresas = fLectura.ListaEmpresasRegistradas();
        for(Empresa empresa: empresas){
            ListaEmpresa.put(empresa.getNombreEmpresa(), empresa.getId());
        }
    }
    
    //  Metodos
    public void Ingresar() throws IOException{
        String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        if(facadeMain.ComprobarValidezPassword(Integer.valueOf(UsuarioSeleccionado),PasswordUsuario)){
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            Usuario usuario = fLectura.GetUsuario(Integer.valueOf(UsuarioSeleccionado));
            request.getSession().setAttribute("Usuario", usuario);
            Empresa empresa = fLectura.GetEmpresa(EmpresaSeleccionada);
            request.getSession().setAttribute("Empresa", empresa);
            FacesContext.getCurrentInstance().getExternalContext().redirect(url+"/Views/Main/Main.xhtml");
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_FATAL, "No Existe Usuario", "Los datos del usuario no son correctos"));
            FacesContext.getCurrentInstance().renderResponse();
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
                NombreUsuario = fLectura.GetUsuario(Integer.valueOf(UsuarioSeleccionado)).GetNombreCompleto();
            }
        }catch(NumberFormatException ex){
            System.out.println("Error: " +ex.getLocalizedMessage());
            NombreUsuario = "";
        }
    }
    
}
