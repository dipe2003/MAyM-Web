/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.acciones.correctivas;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.empresa.Empresa;
import com.dperez.maymweb.facades.FacadeLectura;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
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
public class ListarCorrectivas implements Serializable{
    @Inject
    private FacadeLectura fLectura;
    
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
        List<Accion> acciones = fLectura.ListarAccionesCorrectivas();
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
        FacesContext.getCurrentInstance().getExternalContext().redirect(url+"/Views/Main/Main.xhtml?id="+IdAccionSeleccionada);
    }
}
