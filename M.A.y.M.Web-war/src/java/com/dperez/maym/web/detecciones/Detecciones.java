/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.detecciones;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.deteccion.Deteccion;
import com.dperez.maymweb.deteccion.EnumTipoDeteccion;
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
import static javax.faces.application.FacesMessage.SEVERITY_FATAL;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;



@Named
@ViewScoped
public class Detecciones implements Serializable {
    @Inject
    private FacadeAdministrador fAdmin;
    @Inject
    private FacadeLectura fLectura;
    
    private Empresa EmpresaLogueada;
    
    private int IdDeteccionSeleccionada;
    private String NombreDeteccion;
    private Map<Integer, Deteccion> ListaDetecciones;
    
    private EnumTipoDeteccion[] TiposDeteccion;
    private EnumTipoDeteccion TipoDeDeteccionSeleccionada;
    
    
    //  Getters    
    public String getNombreDeteccion() {return NombreDeteccion;}
    public Map<Integer, Deteccion> getListaDetecciones() {return ListaDetecciones;}
    public EnumTipoDeteccion getTipoDeDeteccionSeleccionada(){return this.TipoDeDeteccionSeleccionada;}
    public EnumTipoDeteccion[] getTiposDeteccion(){return this.TiposDeteccion;}
    public Empresa getEmpresaLogueada() {return EmpresaLogueada;}
    
    //  Setters    
    public void setNombreDeteccion(String NombreDeteccion) {this.NombreDeteccion = NombreDeteccion;}
    public void setListaDetecciones(Map<Integer, Deteccion> ListaDetecciones) {this.ListaDetecciones = ListaDetecciones;}
    public void setTipoDeDeteccionSeleccionada(EnumTipoDeteccion TipoDeteccion){this.TipoDeDeteccionSeleccionada = TipoDeteccion;}
    public void setTiposDeteccion(EnumTipoDeteccion[] TiposDeteccion){this.TiposDeteccion = TiposDeteccion;}
    
    //  Metodos
    
    /**
     * Carga de propiedades al inicio
     */
    @PostConstruct
    public void init(){
        //  Detecciones
        ListaDetecciones = new HashMap<>();
        List<Deteccion> tmpDeteccion = fLectura.ListarDetecciones();
        for(Deteccion deteccion:tmpDeteccion){
            ListaDetecciones.put(deteccion.getId(), deteccion);
        }
    }
    
    /**
     * Crea nueva deteccion con el tipo interna/externa seleccionado.
     * Si no se crea se muestra un mensaje
     */
    public void nuevaDeteccion(){
        // Crear Nueva Deteccion y actualizar lista
        Deteccion deteccion;
        if((deteccion = fAdmin.NuevaDeteccion(NombreDeteccion, TipoDeDeteccionSeleccionada))!=null){
            ListaDetecciones.put(deteccion.getId(), deteccion);
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_FATAL, "No se pudo crear nueva deteccion", "No se pudo crear nueva deteccion" ));
            FacesContext.getCurrentInstance().renderResponse();
        }
    }
    
    
    /**
     * Actualiza la deteccion con lo datos ingresados.
     * Muestra un mensaje de errror si no se actualizo.
     * Agrega los cambios en la deteccion de la lista del bean.
     * @param IdDeteccionSeleccionada
     */
    public void editarDeteccion(int IdDeteccionSeleccionada){
        if((fAdmin.EditarDeteccion(IdDeteccionSeleccionada, NombreDeteccion, TipoDeDeteccionSeleccionada))!=-1){
            ListaDetecciones.get(IdDeteccionSeleccionada).setNombre(NombreDeteccion);
            ListaDetecciones.get(IdDeteccionSeleccionada).setTipo(TipoDeDeteccionSeleccionada);
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_ERROR, "No se pudo editar la deteccion", "No se pudo editar la deteccion" ));
            FacesContext.getCurrentInstance().renderResponse();
        }
    }
    
    /**
     * Eliminina la deteccion indicada.
     * Muestra un mensaje de error si no se pudo eliminar.
     * Remueve la deteccion de la lista del bean.
     * @param IdDeteccionSeleccionada
     */
    public void eliminarDeteccion(int IdDeteccionSeleccionada){
        if(fAdmin.EliminarDeteccion(IdDeteccionSeleccionada)!=-1){
            ListaDetecciones.remove(IdDeteccionSeleccionada);
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_ERROR, "No se pudo eliminar la deteccion", "No se pudo eliminar la deteccion" ));
            FacesContext.getCurrentInstance().renderResponse();
        }
    }
    
    public void cargarDatos(int IdDeteccion){
        if(IdDeteccion < 0 ){
            this.NombreDeteccion = new String();
            this.TipoDeDeteccionSeleccionada = EnumTipoDeteccion.INTERNA;
            this.IdDeteccionSeleccionada = -1;
        }else{
            this.NombreDeteccion = ListaDetecciones.get(IdDeteccion).getNombre();
            this.TipoDeDeteccionSeleccionada = ListaDetecciones.get(IdDeteccion).getTipo();
            this.IdDeteccionSeleccionada = IdDeteccion;
            
            // verifica que no tenga acciones con deteccion seleccioada para esta empresa
            // al encontrar la primer accion que pertenezca a la deteccion y empresa ContieneAcciones = true
                Deteccion deteccion = ListaDetecciones.get(IdDeteccion);
                boolean ContieneAcciones = false;
                for(Accion accion: deteccion.getAccionesDetectadas()){
                    if(accion.getEmpresaAccion().getId() == EmpresaLogueada.getId()){
                        ContieneAcciones = true;
                        break;
                    }
                }
            }

    }
    
}