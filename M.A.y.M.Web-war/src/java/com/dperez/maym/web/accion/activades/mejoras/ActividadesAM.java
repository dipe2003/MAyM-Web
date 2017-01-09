/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.accion.activades.mejoras;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.accion.acciones.Mejora;
import com.dperez.maymweb.accion.actividad.Actividad;
import com.dperez.maymweb.empresa.Empresa;
import com.dperez.maymweb.facades.FacadeDatos;
import com.dperez.maymweb.facades.FacadeLectura;
import com.dperez.maymweb.usuario.Usuario;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class ActividadesAM implements Serializable {
    @Inject
    private FacadeDatos fDatos;
    @Inject
    private FacadeLectura fLectura;
    
    private Accion AccionMejora;
    
    private Map<Integer, Usuario> ListaUsuariosEmpresa;
    
    private Map<Integer, Actividad> ListaActividades;
    private int ActividadSeleccionada;
    
    private Date FechaEstimadaImplementacionActividad;
    private String DescripcionActividad;
    private int ResponsableActividad;
        
    //  Getters
    public Map<Integer, Usuario> getListaUsuariosEmpresa() {return ListaUsuariosEmpresa;}
    public Map<Integer, Actividad> getListaActividades() {return ListaActividades;}
    public int getActividadSeleccionada() {return ActividadSeleccionada;}
    public Date getFechaEstimadaImplementacionActividad() {return FechaEstimadaImplementacionActividad;}
    public String getDescripcionActividad() {return DescripcionActividad;}
    public int getResponsableActividad() {return ResponsableActividad;}
    
    //  Setters
    public void setListaUsuariosEmpresa(Map<Integer, Usuario> ListaUsuariosEmpresa) {this.ListaUsuariosEmpresa = ListaUsuariosEmpresa;}
    public void setListaActividades(Map<Integer, Actividad> ListaActividades) {this.ListaActividades = ListaActividades;}
    public void setActividadSeleccionada(int ActividadSeleccionada) {this.ActividadSeleccionada = ActividadSeleccionada;}
    public void setFechaEstimadaImplementacionActividad(Date FechaEstimadaImplementacionActividad) {this.FechaEstimadaImplementacionActividad = FechaEstimadaImplementacionActividad;}
    public void setDescripcionActividad(String DescripcionActividad) {this.DescripcionActividad = DescripcionActividad;}
    public void setResponsableActividad(int ResponsableActividad) {this.ResponsableActividad = ResponsableActividad;}
    
    //  Metodos
    /**
     * Agrega una actividad a la accion de mejora.
     * Se actualiza la lista de actividades del bean.
     * Se persisten los cambios.
     * Muestra un mensaje de error si no se pudo completar correctamente.
     */
    public void agregarActividadMejora(){
        if(!DescripcionActividad.isEmpty() && FechaEstimadaImplementacionActividad != null && ResponsableActividad != 0){
            Actividad actividad  = new Actividad();
            Usuario responsable = ListaUsuariosEmpresa.get(ResponsableActividad);
            actividad.setDescripcion(DescripcionActividad);
            actividad.setFechaEstimadaImplementacion(FechaEstimadaImplementacionActividad);
            actividad.setResponsableImplementacion(responsable);
            int id;
            if((id = fDatos.AgregarActividadMejora(AccionMejora.getId(), actividad.getFechaEstimadaImplementacion(), actividad.getDescripcion(),
                    actividad.getResponsableImplementacion().getId()))<0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_FATAL, "No se pudo agregar Actividad", "No se pudo agregar Actividad" ));
                FacesContext.getCurrentInstance().renderResponse();
            }else{
                // agregar a la lista de actividades del bean
                actividad.setId(id);
                ListaActividades.put(actividad.getId(), actividad);
            }
        }
    }    
    
    /**
     * Remueve la actividad de mejora de la lista de actividades si la fecha de implementacion no esta definida.
     * Remueve la actividad de la accion de mejora.
     * Se persisten los cambios.
     * Muestra un mensaje de error si no se pudo completar correctamente.
     * @param IdActividadMejora
     */
    public void removerActividadMejora(int IdActividadMejora){
        if(IdActividadMejora!=0 && ListaActividades.get(IdActividadMejora).getFechaImplementacion() == null){
            int res;
            if((res = fDatos.RemoverActividadMejora(AccionMejora.getId(), IdActividadMejora))> 0){
                ListaActividades.remove(res);
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_FATAL, "No se pudo quitar la Actividad", "No se pudo quitar la Actividad" ));
                FacesContext.getCurrentInstance().renderResponse();
            }
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_FATAL, "No se puede quitar porque ya fue implementada", "No se puede quitar porque ya fue implementada" ));
            FacesContext.getCurrentInstance().renderResponse();
        }
    }
    
       
    //  Inicializacion del bean
    public void init(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        // recuperar el id para llenar datos de la accion de mejora y el resto de las propiedades.
        int idAccion = 0;
        idAccion = Integer.parseInt(request.getParameter("id"));
        if(idAccion != 0){
            AccionMejora = (Mejora) fLectura.GetAccion(idAccion);
            //  Actividades de Mejora
            ListaActividades = new HashMap<>();
            if(!((Mejora)AccionMejora).getActividades().isEmpty()){
                List<Actividad> actividades = ((Mejora)AccionMejora).getActividades();
                for(Actividad actividad: actividades ){
                    ListaActividades.put(actividad.getId(), actividad);
                }
            }
                        
            //  Usuarios
            this.ListaUsuariosEmpresa = new HashMap<>();
            Empresa empresa = (Empresa) request.getSession().getAttribute("Empresa");
            // llenar lista de usuarios para responsables de implementacion.
            if(empresa!=null) {
                List<Usuario> tmpUsuarios = fLectura.GetUsuariosEmpresa(empresa.getId());
                for(Usuario usuario: tmpUsuarios){
                    ListaUsuariosEmpresa.put(usuario.getId(), usuario);
                }
            }
        }
    }
}
