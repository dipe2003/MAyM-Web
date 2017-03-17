/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.codificaciones;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.codificacion.Codificacion;
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
    private int IdCodificacionSeleccionada;
    private boolean AplicaEmpresa;
    private boolean ContieneAcciones;
    private Map<Integer, Codificacion> ListaCodificaciones;
    
    private Map<Integer, Codificacion> ListaCodificacionesEmpresa;
    
    //  Getters
    
    public String getNombreCodificacion() {return NombreCodificacion;}
    public String getDescripcionCodificacion() {return DescripcionCodificacion;}
    public boolean isAplicaEmpresa() {return AplicaEmpresa;}
    public Map<Integer, Codificacion> getListaCodificaciones() {return ListaCodificaciones;}
    public boolean isContieneAcciones() {return ContieneAcciones;}
    public Empresa getEmpresaLogueada(){return this.EmpresaLogueada;}
    
    //  Setters
    
    public void setNombreCodificacion(String NombreCodificacion) {this.NombreCodificacion = NombreCodificacion;}
    public void setDescripcionCodificacion(String DescripcionCodificacion) {this.DescripcionCodificacion = DescripcionCodificacion;}
    public void setAplicaEmpresa(boolean AplicaEmpresa) {this.AplicaEmpresa = AplicaEmpresa;}
    public void setListaCodificaciones(Map<Integer, Codificacion> ListaCodificaciones) {this.ListaCodificaciones = ListaCodificaciones;}
    public void setContieneAcciones(boolean ContieneAcciones) {this.ContieneAcciones = ContieneAcciones;}
    
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
     * Muestra un mensaje de errror si no se creo, se agrega la codificacion a la empres logueada y redirige a la misma pagina para ver los resultados.
     * @throws java.io.IOException
     */
    public void nuevaCodificacion() throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        if(comprobarNombreCodificacion(NombreCodificacion)){
            context.addMessage("form_codificaciones:btn_nueva_codificacion", new FacesMessage(SEVERITY_ERROR, "Ya existe una codificacion con ese nombre", "Ya existe una codificacion con ese nombre" ));
            context.renderResponse();
        }else{
            Codificacion cod;
            if((cod = fAdmin.NuevaCodificacion(NombreCodificacion, DescripcionCodificacion)) != null){
                if(cod != null){
                    fAdmin.ModificarEmpresaCodificacion(cod.getId(), true, EmpresaLogueada.getId());
                }
                context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/Views/Configuraciones/Codificaciones.xhtml");
            }else{
                context.addMessage("form_codificaciones:btn_nueva_codificacion", new FacesMessage(SEVERITY_ERROR, "No se pudo crear la Codificacion", "No se pudo crear la Codificacion" ));
                context.renderResponse();
            }
        }
    }
    
    /**
     * Actualiza la codificacion con lo datos ingresados.
     * Se agrega o quita relacion con empresa.
     * Muestra un mensaje de errror si no se actualizo, redirige a la misma pagina para ver los resultados.
     * @throws java.io.IOException
     */
    public void editarCodificacion() throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        if((fAdmin.EditarCodificacion(IdCodificacionSeleccionada, NombreCodificacion, DescripcionCodificacion))!=-1){
            boolean contiene = ListaCodificaciones.get(IdCodificacionSeleccionada).contieneEmpresa(EmpresaLogueada.getId());
            if(contiene != this.AplicaEmpresa){
                if((fAdmin.ModificarEmpresaCodificacion(IdCodificacionSeleccionada, AplicaEmpresa, EmpresaLogueada.getId()))==-1){
                    context.addMessage("form_codificaciones:btn_editar_codificacion", new FacesMessage(SEVERITY_ERROR, "No se pudo modificar empresa", "No se pudo modificar empresa" ));
                    context.renderResponse();
                }
            }
            context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/Views/Configuraciones/Codificaciones.xhtml");
        }else{
            context.addMessage("form_codificaciones:btn_editar_codificacion", new FacesMessage(SEVERITY_ERROR, "No se pudo editar la codificacion", "No se pudo editar la codificacion" ));
            context.renderResponse();
        }
    }
    
    private boolean comprobarNombreCodificacion(String NombreCodificacion){
        for(Map.Entry entry: this.ListaCodificaciones.entrySet()){
            if (((Codificacion)entry.getValue()).getNombre().equalsIgnoreCase(NombreCodificacion)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Eliminina la codificacion indicada.
     * Muestra un mensaje de errror si no se actualizo, redirige a la misma pagina para ver los resultados.
     * @param IdCodificacionSeleccionada
     * @throws java.io.IOException
     */
    public void eliminarCodificacion(int IdCodificacionSeleccionada) throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        // primero se comprueba que pertenezca a la empresa del usuario logueado y que no aplique a otra empresa
        Codificacion cod = ListaCodificaciones.get(IdCodificacionSeleccionada);
        if(cod.contieneEmpresa(EmpresaLogueada.getId()) && cod.getEmpresasCodificacion().size()==1){
            if(fAdmin.EliminarCodificacion(IdCodificacionSeleccionada, EmpresaLogueada.getId())!=-1){
                context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/Views/Configuraciones/Codificaciones.xhtml");
            }else{
                context.addMessage("form_codificaciones:btn_eliminar_"+IdCodificacionSeleccionada, new FacesMessage(SEVERITY_ERROR, "No se pudo eliminar la codificacion", "No se pudo eliminar la codificacion" ));
                context.renderResponse();
            }
        }else{
            context.addMessage("form_codificaciones:btn_eliminar_"+IdCodificacionSeleccionada, new FacesMessage(SEVERITY_ERROR, "No se pudo eliminar la codificacion, esta utilizada por otra empresa", "No se pudo eliminar la codificacion, esta utilizada por otra empresa" ));
            context.renderResponse();
        }
    }
    
    public boolean contieneEmpresa(int IdCodificacion){
        if(ListaCodificacionesEmpresa.containsKey(IdCodificacion)){
            return true;
        }else{
            return false;
        }
    }
    
    public void cargarDatos(int IdCodificacion){
        if(IdCodificacion < 0 ){
            this.NombreCodificacion = new String();
            this.DescripcionCodificacion = new String();
            this.IdCodificacionSeleccionada = -1;
        }else{
            this.NombreCodificacion = ListaCodificaciones.get(IdCodificacion).getNombre();
            this.DescripcionCodificacion = ListaCodificaciones.get(IdCodificacion).getDescripcion();
            this.IdCodificacionSeleccionada = IdCodificacion;
            
            // verifica si pertenece a la empresa del usuario logueado
            Codificacion codificacion =  ListaCodificaciones.get(IdCodificacion);
            if(codificacion.contieneEmpresa(EmpresaLogueada.getId())){
                this.AplicaEmpresa = true;
            }else{
                this.AplicaEmpresa = false;
            }
            
            // verifica que no tenga acciones codificadas para esta emrpesa
            // al encontrar la primer accion que pertenezca a la codificacion y empresa ContieneAcciones = true
            if(this.AplicaEmpresa == true){
                ContieneAcciones = false;
                for(Accion accion: codificacion.getAccionesConCodificacion()){
                    if(accion.getEmpresaAccion().getId() == EmpresaLogueada.getId()){
                        ContieneAcciones = true;
                        break;
                    }
                }
            }
        }
    }
    
}