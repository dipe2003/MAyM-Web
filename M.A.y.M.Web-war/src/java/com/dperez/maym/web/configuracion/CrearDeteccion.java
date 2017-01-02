/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dperez.maym.web.configuracion;

import com.dperez.maymweb.deteccion.EnumTipoDeteccion;
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
public class CrearDeteccion implements Serializable{
    @Inject
    private FacadeAdministrador fAdmin;
    
    private String NombreDeteccion;
    private EnumTipoDeteccion[] TiposDeteccion;
    private EnumTipoDeteccion TipoDeteccionSeleccionado;
    
    //  Getters
    public String getNombreDeteccion(){return this.NombreDeteccion;}
    public EnumTipoDeteccion[] getTiposDeteccion(){return this.TiposDeteccion;}
    public EnumTipoDeteccion getTipoDeteccionSeleccionado(){return this.TipoDeteccionSeleccionado;}
    
    //  Setters
    public void setNombreDeteccion(String NombreDeteccion){this.NombreDeteccion = NombreDeteccion;}
    public void setTiposDeteccion(EnumTipoDeteccion[] TiposDeteccion){this.TiposDeteccion = TiposDeteccion;}
    public void setTipoDeteccionSeleccionado(EnumTipoDeteccion TipoDeteccionSeleccionado){this.TipoDeteccionSeleccionado = TipoDeteccionSeleccionado;}
    
    // Metodos
    
    /**
     * Crea la deteccion especificada.
     * Muestra un mensaje si no se pudo crear. Redirige a la lista de detecciones si se creo.
     * @throws IOException 
     */
    public void crearDeteccion() throws IOException{
        if (fAdmin.NuevaDeteccion(NombreDeteccion, TipoDeteccionSeleccionado) != null){
            String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(url+"/Views/Main/Main.xhtml");
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_WARN, "No se pudo crear", "No se pudo crear." ));
            FacesContext.getCurrentInstance().renderResponse();
        }
    }
    
}
