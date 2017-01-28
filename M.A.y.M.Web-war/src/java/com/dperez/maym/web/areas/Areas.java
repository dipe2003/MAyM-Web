/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.areas;

import com.dperez.maymweb.area.Area;
import com.dperez.maymweb.facades.FacadeAdministrador;
import com.dperez.maymweb.facades.FacadeLectura;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class Areas implements Serializable {
    @Inject
    private FacadeAdministrador fAdmin;
    @Inject
    private FacadeLectura fLectura;
    
    private String NombreArea;
    private String CorreoArea;
    private Map<Integer, Area> ListaAreas;
    
    
    //  Getters
    
    public String getNombreArea() {return NombreArea;}
    public String getCorreoArea() {return CorreoArea;}
    public Map<Integer, Area> getListaAreas() {return ListaAreas;}
    
    //  Setters
    
    public void setNombreArea(String NombreArea) {this.NombreArea = NombreArea;}
    public void setCorreoArea(String CorreoArea) {this.CorreoArea = CorreoArea;}
    public void setListaAreas(Map<Integer, Area> ListaAreas) {this.ListaAreas = ListaAreas;}
    
    //  Metodos
    
    /**
     * Carga de propiedades al inicio
     */
    @PostConstruct
    public void init(){
        //  Codificaciones
        ListaAreas = new HashMap<>();
        List<Area> tmpAreas = fLectura.ListarAreasSectores();
        for(Area area:tmpAreas){
            ListaAreas.put(area.getId(), area);
        }
    }
    
    /**
     * Crea el area con los datos ingresados.
     * Muestra un mensaje de error si no se creo.
     * Se agrega a la lista de areas del bean.
     */
    public void crearArea(){
        Area area;
        if((area = fAdmin.NuevaArea(NombreArea, CorreoArea)) != null){
            this.ListaAreas.put(area.getId(), area);
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_ERROR, "No se pudo crear el area", "No se pudo crear el area" ));
            FacesContext.getCurrentInstance().renderResponse();
        }
    }
    
    /**
     * Actualiza el area con lo datos ingresados.
     * Muestra un mensaje de errror si no se actualizo.
     * Agrega los cambios al area de la lista del bean.
     * @param IdAreaSeleccionada
     */
    public void editarArea(int IdAreaSeleccionada){
        if((fAdmin.EditarArea(IdAreaSeleccionada, NombreArea, CorreoArea))!=-1){
            ListaAreas.get(IdAreaSeleccionada).setNombre(NombreArea);
            ListaAreas.get(IdAreaSeleccionada).setCorreo(CorreoArea);
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_ERROR, "No se pudo editar el area", "No se pudo editar el area" ));
            FacesContext.getCurrentInstance().renderResponse();
        }
    }
    
    /**
     * Eliminina el area indicada.
     * Muestra un mensaje de error si no se pudo eliminar.
     * Remueve el area de la lista del bean.
     * @param IdAreaSeleccionada
     */
    public void eliminarArea(int IdAreaSeleccionada){
        if(fAdmin.EliminarArea(IdAreaSeleccionada)!=-1){
            ListaAreas.remove(IdAreaSeleccionada);
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_ERROR, "No se pudo eliminar el area", "No se pudo eliminar el area" ));
            FacesContext.getCurrentInstance().renderResponse();
        }
    }
    
}