/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.acciones.preventivas;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.empresa.Empresa;
import com.dperez.maymweb.facades.FacadeAdministrador;
import com.dperez.maymweb.facades.FacadeLectura;
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

/**
 *
 * @author Diego
 */
@Named
@ViewScoped
public class ListarPreventivas implements Serializable{
     @Inject
    private FacadeLectura fLectura;
    @Inject
    private FacadeAdministrador fAdmin;
    
    private Map<Integer, Accion> ListaAcciones;
    
    //  Getters    
    public Map<Integer, Accion> getListaAcciones() {return ListaAcciones;}
    
    //  Setters    
    public void setListaAcciones(Map<Integer, Accion> ListaAcciones) {this.ListaAcciones = ListaAcciones;}
    
    // Metodos
    
    //  Inicializacion
    @PostConstruct
    public void init(){
        // recuperar Empresa para filtrar las acciones
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Empresa empresa = (Empresa)request.getSession().getAttribute("Empresa");
        
        // llenar la lista de acciones
        ListaAcciones = new HashMap<>();
        List<Accion> acciones = fLectura.ListarAccionesPreventivas();
        for(Accion accion: acciones){
            if(accion.getEmpresaAccion().getId() == 100)
                ListaAcciones.put(accion.getId(), accion);
        }
    }
    
    /**
     * Redirige a la vista para realizar seguimiento de la accion seleccionada.
     * @param IdAccionSeleccionada
     * @throws java.io.IOException
     */
    public void abrirSeguimiento(int IdAccionSeleccionada) throws IOException{
        String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        FacesContext.getCurrentInstance().getExternalContext().redirect(url+"/Views/Main/Main.xhtml?id="+IdAccionSeleccionada);
    }
    /**
     * Redirige a la vista para realizar la edicion de la accion seleccionada.
     * @param IdAccionSeleccionada
     * @throws java.io.IOException
     */
    public void abrirEdicion(int IdAccionSeleccionada) throws IOException{
        String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        FacesContext.getCurrentInstance().getExternalContext().redirect(url+"/Views/Acciones/Preventiva/EditarAccionPreventiva.xhtml?id="+IdAccionSeleccionada);
    }
    
    public void eliminarAccion(int IdAccionSeleccoiondada) throws IOException{
        if((fAdmin.EliminarAccion(IdAccionSeleccoiondada))!= -1){
        String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        FacesContext.getCurrentInstance().getExternalContext().redirect(url+"/Views/Acciones/Preventivas/ListarPreventivas.xhtml");
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_FATAL, "No se pudo eliminar la accion", "No se pudo eliminar la accion" ));
            FacesContext.getCurrentInstance().renderResponse();
        }
    }
}
