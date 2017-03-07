/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.acciones.mejoras;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.accion.acciones.Mejora;
import com.dperez.maymweb.accion.actividad.Actividad;
import com.dperez.maymweb.empresa.Empresa;
import com.dperez.maymweb.facades.FacadeDatos;
import com.dperez.maymweb.facades.FacadeLectura;
import com.dperez.maymweb.usuario.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
public class ActividadesAM implements Serializable {
    @Inject
    private FacadeDatos fDatos;
    @Inject
    private FacadeLectura fLectura;
    
    private Accion AccionSeleccionada;
    private int IdActividadEditar;
    
    private Map<Integer, Usuario> ListaUsuariosEmpresa;
    
    private Date FechaEstimadaImplementacionActividad;
    private String StrFechaEstimada;
    private String DescripcionActividad;
    private int ResponsableActividad;
    
    //  Getters
    public Map<Integer, Usuario> getListaUsuariosEmpresa() {return ListaUsuariosEmpresa;}
    public Date getFechaEstimadaImplementacionActividad() {return FechaEstimadaImplementacionActividad;}
    public String getStrFechaEstimada(){
        SimpleDateFormat fDate = new SimpleDateFormat("dd/MM/yyyy");
        if (FechaEstimadaImplementacionActividad == null) {
            return this.StrFechaEstimada;
        }else{
            return fDate.format(FechaEstimadaImplementacionActividad);
        }
    }
    public String getDescripcionActividad() {return DescripcionActividad;}
    public int getResponsableActividad() {return ResponsableActividad;}
    
    public Accion getAccionSeleccionada() {return AccionSeleccionada;}
    
    public int getIdActividadEditar() {return IdActividadEditar;}
    
    //  Setters
    public void setListaUsuariosEmpresa(Map<Integer, Usuario> ListaUsuariosEmpresa) {this.ListaUsuariosEmpresa = ListaUsuariosEmpresa;}
    public void setFechaEstimadaImplementacionActividad(Date FechaEstimadaImplementacionActividad) {this.FechaEstimadaImplementacionActividad = FechaEstimadaImplementacionActividad;}
    public void setStrFechaEstimada(String StrFechaEstimada) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try{
            cal.setTime(sdf.parse(StrFechaEstimada));
        }catch(ParseException ex){}
        this.StrFechaEstimada = StrFechaEstimada;
        this.FechaEstimadaImplementacionActividad = cal.getTime();
    }
    public void setDescripcionActividad(String DescripcionActividad) {this.DescripcionActividad = DescripcionActividad;}
    public void setResponsableActividad(int ResponsableActividad) {this.ResponsableActividad = ResponsableActividad;}
    
    public void setAccionSeleccionada(Accion AccionSeleccionada) {this.AccionSeleccionada = AccionSeleccionada;}
    
    public void setIdActividadEditar(int IdActividadEditar) {this.IdActividadEditar = IdActividadEditar;}
    
    //  Metodos
    /**
     * Agrega una actividad a la accion de mejora.
     * Se persisten los cambios.
     * Muestra un mensaje de error si no se pudo completar correctamente.
     * @throws java.io.IOException
     */
    public void agregarActividad() throws IOException{
        if(fDatos.AgregarActividadMejora(AccionSeleccionada.getId(), FechaEstimadaImplementacionActividad, DescripcionActividad,
                ResponsableActividad)<0){
            FacesContext.getCurrentInstance().addMessage("form_agregar_actividades:btn_agregar_actividad", new FacesMessage(SEVERITY_FATAL, "No se pudo agregar Actividad", "No se pudo agregar Actividad" ));
            FacesContext.getCurrentInstance().renderResponse();
        }else{
            // redirigir a la edicion de la accion correctiva.
            String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(url+"/Views/Acciones/Mejoras/EditarAccionMejora.xhtml?id="+AccionSeleccionada.getId());
        }
    }
    
    /**
     * Remueve la actividad de la accion de mejora.
     * Se persisten los cambios.
     * Muestra un mensaje de error si no se pudo completar correctamente.
     * @throws java.io.IOException
     */
    public void removerActividad() throws IOException{
        if(fDatos.RemoverActividadMejora(AccionSeleccionada.getId(), IdActividadEditar)< 0){
            FacesContext.getCurrentInstance().addMessage("form_agregar_actividades:btn_remover_actividad", new FacesMessage(SEVERITY_FATAL, "No se pudo quitar la Actividad", "No se pudo quitar la Actividad" ));
            FacesContext.getCurrentInstance().renderResponse();
        }else{
            // redirigir a la edicion de la accion de mejora.
            String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(url+"/Views/Acciones/Mejoras/EditarAccionMejora.xhtml?id="+AccionSeleccionada.getId());
        }
    }
    
    /**
     * Guarda los cambios realizados en la medida correctiva y los persiste.
     * Se redirige a la vista de editar la accion correctiva correspondiente si se guardo, de lo contrario muestra un mensaje.
     * @throws IOException
     */
    public void guardarActividad() throws IOException{
        if(fDatos.EditarActividad(IdActividadEditar, DescripcionActividad, ResponsableActividad, FechaEstimadaImplementacionActividad) < 0){
            FacesContext.getCurrentInstance().addMessage("form_agregar_actividades:btn_guardar_actividad", new FacesMessage(SEVERITY_FATAL, "No se pudo guardar Actividad", "No se pudo guardar Actividad" ));
            FacesContext.getCurrentInstance().renderResponse();
        }else{
            // redirigir a la edicion de la accion correctiva.
            String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(url+"/Views/Acciones/Mejoras/EditarAccionMejora.xhtml?id="+AccionSeleccionada.getId());
        }
    }
    
    //  Inicializacion del bean
    @PostConstruct
    public void init(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        // recuperar el id para llenar datos de la accion de mejora y el resto de las propiedades.
        int idAccion = 0;
        idAccion = Integer.parseInt(request.getParameter("id"));
        try{
            IdActividadEditar = Integer.parseInt(request.getParameter("editar"));
        }catch(Exception ex){
            IdActividadEditar = 0;
        }
        if(idAccion != 0){
            AccionSeleccionada = (Mejora) fLectura.GetAccion(idAccion);
            if(IdActividadEditar > 0){
                //  Actividades de Mejora
                if(!((Mejora)AccionSeleccionada).getActividades().isEmpty()){
                    List<Actividad> actividades = ((Mejora)AccionSeleccionada).getActividades();
                    for(Actividad actividad: actividades ){
                        if(actividad.getIdActividad() == IdActividadEditar){
                            FechaEstimadaImplementacionActividad = actividad.getFechaEstimadaImplementacion();
                            DescripcionActividad =  actividad.getDescripcion();
                            ResponsableActividad = actividad.getResponsableImplementacion().getId();
                        }
                    }
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
