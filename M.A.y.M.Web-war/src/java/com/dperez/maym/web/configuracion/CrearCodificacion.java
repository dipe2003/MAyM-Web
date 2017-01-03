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
public class CrearCodificacion implements Serializable{
    @Inject
    private FacadeAdministrador fAdmin;
    
    private String NombreCodificacion;
    private String DescripcionCodificacion;
    
    //  Getters
    public String getNombreCodificacion(){return this.NombreCodificacion;}
    public String getDescripcionCodificacion(){return this.DescripcionCodificacion;}
    
    //  Setters
    public void setNombreCodificacion(String NombreCodificacion){this.NombreCodificacion = NombreCodificacion;}
    public void setDescripcionCodificacion(String DescripcionCodificacion){this.DescripcionCodificacion = DescripcionCodificacion;}
    
    // Metodos
    
    /**
     * Crea la codificacion especificada.
     * Muestra un mensaje si no se pudo crear. Redirige a la lista de codificaciones si se creo.
     * @throws IOException 
     */
    public void crearCodificacion() throws IOException{
        if (fAdmin.NuevaCodificacion(NombreCodificacion, DescripcionCodificacion) != null){
            String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(url+"/Views/Main/Main.xhtml");
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_WARN, "No se pudo crear", "No se pudo crear." ));
            FacesContext.getCurrentInstance().renderResponse();
        }
    }
    
}
