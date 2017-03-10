/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.acciones.fortalezas;

import com.dperez.maymweb.empresa.Empresa;
import com.dperez.maymweb.facades.FacadeLectura;
import com.dperez.maymweb.fortaleza.Fortaleza;
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
public class ListarFortalezas implements Serializable{
    @Inject
    private FacadeLectura fLectura;
    
    private Map<Integer, Fortaleza> ListaFortalezas;
    
    //  Getters
    
    public Map<Integer, Fortaleza> getListaFortalezas() {return ListaFortalezas;}
    
    //  Setters
    
    public void setListaFortalezas(Map<Integer, Fortaleza> ListaFortalezas) {this.ListaFortalezas = ListaFortalezas;}
    
    // Metodos
    
    //  Inicializacion
    @PostConstruct
    public void init(){
        // recuperar Empresa para filtrar las fortalezas
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Empresa empresa = (Empresa)request.getSession().getAttribute("Empresa");
        
        // llenar la lista de fortalezas
        ListaFortalezas = new HashMap<>();
        List<Fortaleza> fortalezas = fLectura.ListarFortalezas();
        for(Fortaleza fortaleza: fortalezas){
            if(empresa == null){
                ListaFortalezas.put(fortaleza.getId(), fortaleza);
            }else{
                if(fortaleza.getEmpresaFortaleza().getId() == empresa.getId())
                    ListaFortalezas.put(fortaleza.getId(), fortaleza);
            }
        }
    }
    
    /**
     * Redirige a la vista para realizar la edicion de la accion seleccionada.
     * @param IdFortalezaSeleccionada
     * @throws java.io.IOException
     */
    public void abrirEdicion(int IdFortalezaSeleccionada) throws IOException{
        String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        FacesContext.getCurrentInstance().getExternalContext().redirect(url+"/Views/Main/Main.xhtml?id="+IdFortalezaSeleccionada);
    }
}
