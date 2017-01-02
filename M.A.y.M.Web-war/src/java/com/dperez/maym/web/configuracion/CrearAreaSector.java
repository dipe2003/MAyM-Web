/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dperez.maym.web.configuracion;

import com.dperez.maymweb.facades.FacadeAdministrador;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import static javax.faces.application.FacesMessage.SEVERITY_FATAL;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;
import static javax.faces.application.FacesMessage.SEVERITY_WARN;
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
public class CrearAreaSector implements Serializable{
    @Inject
    private FacadeAdministrador fAdmin;
    
    private String NombreArea;
    private String CorreoArea;
    
    //  Getters
    public String getNombreArea(){return this.NombreArea;}
    public String getCorreoArea(){return this.CorreoArea;}
    
    //  Setters
    public void setNombreArea(String NombreArea){this.NombreArea = NombreArea;}
    public void setCorreoArea(String CorreoArea){this.CorreoArea = CorreoArea;}
    
    // Metodos
    
    /**
     * Crea el Area especificada.
     * Muestra un mensaje si no se pudo crear. Redirige a la lista de Areas si se creo.
     * @throws IOException 
     */
    public void crearAreaSector() throws IOException{
        if (fAdmin.NuevaArea(NombreArea, CorreoArea) != null){
            String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(url+"/Views/Main/Main.xhtml");
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_WARN, "No se pudo crear", "No se pudo crear" ));
            FacesContext.getCurrentInstance().renderResponse();
        }
    }
    
}
