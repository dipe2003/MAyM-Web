/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.codificaciones;

import com.dperez.maymweb.codificacion.Codificacion;
import com.dperez.maymweb.empresa.Empresa;
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
import javax.servlet.http.HttpServletRequest;



@Named
@ViewScoped
public class Codificaciones implements Serializable {
    @Inject
    private FacadeAdministrador fAdmin;
    @Inject
    private FacadeLectura fLectura;
    
    private Empresa EmpresaLogueada;
    
    private String NombreCodificacion;
    private String DescripcionCodificacion;   
    private Map<Integer, Codificacion> ListaCodificaciones;
    
    private Map<Integer, Codificacion> ListaCodificacionesEmpresa;
    
    //  Getters
    
    public String getNombreCodificacion() {return NombreCodificacion;}
    public String getDescripcionCodificacion() {return DescripcionCodificacion;}
    public Map<Integer, Codificacion> getListaCodificaciones() {return ListaCodificaciones;}
    
    //  Setters
    
    public void setNombreCodificacion(String NombreCodificacion) {this.NombreCodificacion = NombreCodificacion;}
    public void setDescripcionCodificacion(String DescripcionCodificacion) {this.DescripcionCodificacion = DescripcionCodificacion;}
    public void setListaCodificaciones(Map<Integer, Codificacion> ListaCodificaciones) {this.ListaCodificaciones = ListaCodificaciones;}
    
    //  Metodos
    
    /**
     * Carga de propiedades al inicio
     */
    @PostConstruct
    public void init(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        EmpresaLogueada = (Empresa)request.getSession().getAttribute("Empresa");
        
        //  Codificaciones
        ListaCodificaciones = new HashMap<>();
        ListaCodificacionesEmpresa = new HashMap<>();
        List<Codificacion> tmpCodificaciones = fLectura.ListarCodificaciones();
        for(Codificacion codificacion:tmpCodificaciones){
            ListaCodificaciones.put(codificacion.getId(), codificacion);
            if(codificacion.getEmpresasCodificacion().contains(EmpresaLogueada)){
                ListaCodificacionesEmpresa.put(codificacion.getId(), codificacion);
            }
        }
    }
    
    /**
     * Crea la codificacion con los datos ingresados.
     * Muestra un mensaje de error si no se creo.
     * Se agrega a la lista de codificaciones del bean.
     */
    public void crearCodificacion(){
        Codificacion codificacion;
        if((codificacion = fAdmin.NuevaCodificacion(NombreCodificacion, DescripcionCodificacion)) != null){
            this.ListaCodificaciones.put(codificacion.getId(), codificacion);
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_ERROR, "No se pudo crear la Codificacion", "No se pudo crear la Codificacion" ));
            FacesContext.getCurrentInstance().renderResponse();
        }
    }
    
    /**
     * Actualiza la codificacion con lo datos ingresados.
     * Muestra un mensaje de errror si no se actualizo.
     * Agrega los cambios a la codificacion de la lista del bean.
     * @param IdCodificacionSeleccionada 
     */
    public void editarCodificacion(int IdCodificacionSeleccionada){
        if((fAdmin.EditarCodificacion(IdCodificacionSeleccionada, NombreCodificacion, DescripcionCodificacion))!=-1){
            ListaCodificaciones.get(IdCodificacionSeleccionada).setNombre(NombreCodificacion);
            ListaCodificaciones.get(IdCodificacionSeleccionada).setDescripcion(DescripcionCodificacion);
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_ERROR, "No se pudo editar la codificacion", "No se pudo editar la codificacion" ));
            FacesContext.getCurrentInstance().renderResponse();
        }
    }
    
    /**
     * Eliminina la codificacion indicada.
     * Muestra un mensaje de error si no se pudo eliminar.
     * Remueve la codificacion de la lista del bean.
     * @param IdCodificacionSeleccionada 
     */
    public void eliminarCodificacion(int IdCodificacionSeleccionada){
        if(fAdmin.EliminarCodificacion(IdCodificacionSeleccionada)!=-1){
            ListaCodificaciones.remove(IdCodificacionSeleccionada);
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_ERROR, "No se pudo eliminar la codificacion", "No se pudo eliminar la codificacion" ));
            FacesContext.getCurrentInstance().renderResponse();
        }
    }
    
    public boolean contieneEmpresa(int IdCodificacion){
        if(ListaCodificacionesEmpresa.containsKey(IdCodificacion)){
            return true;
        }else{
            return false;
        }
    }
    
}