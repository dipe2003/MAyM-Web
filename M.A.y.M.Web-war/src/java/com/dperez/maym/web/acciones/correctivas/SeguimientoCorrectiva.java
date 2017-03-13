/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.acciones.correctivas;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.accion.EnumComprobacion;
import com.dperez.maymweb.accion.acciones.Correctiva;
import com.dperez.maymweb.accion.acciones.EnumTipoDesvio;
import com.dperez.maymweb.accion.actividad.Actividad;
import com.dperez.maymweb.empresa.Empresa;
import com.dperez.maymweb.facades.FacadeDatos;
import com.dperez.maymweb.facades.FacadeLectura;
import com.dperez.maymweb.facades.FacadeVerificador;
import com.dperez.maymweb.usuario.Usuario;
import java.io.IOException;
import java.io.Serializable;
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
 * @author dipe2
 */
@Named
@ViewScoped
public class SeguimientoCorrectiva implements Serializable {
    @Inject
    private FacadeLectura fLectura;
    @Inject
    private FacadeDatos fDatos;
    @Inject
    private FacadeVerificador fVerif;
    
    private EnumTipoDesvio TipoDesvio;
    
    private Map<Integer, Actividad> MedidasCorrectivas;
    private Map<Integer, Actividad> MedidasPreventivas;
    
    private Date FechaImplementacion;
    
    private String ObservacionesComprobacion;
    private Date FechaComprobacion;
    private EnumComprobacion[] Comprobaciones;
    private EnumComprobacion ComprobacionSeleccionada;
    
    private Map<Integer, Usuario> ListaUsuarios;
    private int IdUsuarioSeleccionado;
    
    private Accion AccionSeleccionada;
    
    
    //  Getters

    public EnumTipoDesvio getTipoDesvio() {return TipoDesvio;}    
    
    public Map<Integer, Actividad> getMedidasCorrectivas() {return MedidasCorrectivas;}
    public Map<Integer, Actividad> getMedidasPreventivas() {return MedidasPreventivas;}
    public Date getFechaImplementacion(){return this.FechaImplementacion;}
    public String getObservacionesComprobacion() {return ObservacionesComprobacion;}
    public Date getFechaComprobacion() {return FechaComprobacion;}
    public EnumComprobacion[] getComprobaciones() {return Comprobaciones;}
    public EnumComprobacion getComprobacionSeleccionada() {return ComprobacionSeleccionada;}
    public Accion getAccionSeleccionada() {return AccionSeleccionada;}
    
    public Map<Integer, Usuario> getListaUsuarios(){return this.ListaUsuarios;}
    public int getIdUsuarioSeleccionado(){return this.IdUsuarioSeleccionado;}
    
    
    //  Setters

    public void setTipoDesvio(EnumTipoDesvio TipoDesvio) {this.TipoDesvio = TipoDesvio;}    
    
    public void setMedidasCorrectivas(Map<Integer, Actividad> MedidasCorrectivas) {this.MedidasCorrectivas = MedidasCorrectivas;}
    public void setMedidasPreventivas(Map<Integer, Actividad> MedidasPreventivas) {this.MedidasPreventivas = MedidasPreventivas;}
    public void setFechaImplementacion(Date FechaImplementacion){this.FechaImplementacion = FechaImplementacion;}
    public void setObservacionesComprobacion(String ObservacionesComprobacion) {this.ObservacionesComprobacion = ObservacionesComprobacion;}
    public void setFechaComprobacion(Date FechaComprobacion) {this.FechaComprobacion = FechaComprobacion;}
    public void setComprobaciones(EnumComprobacion[] Comprobaciones) {this.Comprobaciones = Comprobaciones;}
    public void setComprobacionSeleccionada(EnumComprobacion ComprobacionSeleccionada) {this.ComprobacionSeleccionada = ComprobacionSeleccionada;}
    public void setAccionSeleccionada(Accion AccionSeleccionada) {this.AccionSeleccionada = AccionSeleccionada;}
    
    public void setListaUsuarios(Map<Integer, Usuario> ListaUsuarios){this.ListaUsuarios = ListaUsuarios;}
    public void setIdUsuarioSeleccionado(int IdUsuarioSeleccionado){this.IdUsuarioSeleccionado = IdUsuarioSeleccionado;}
    
    
    //  Metodos
    
    /**
     * Agrega fecha de implementacion de la actividad seleccionada.
     * Muestra un mensaje si no se pudo agregar fecha de lo contrario agrega la fecha a la actividad de la lista del bean.
     * @param IdActividad
     */
    public void setFechaImplementacionActividad(int IdActividad){
        FechaImplementacion = new Date();
        if((fDatos.SetFechaImplementacionMedidaCorrectiva(FechaImplementacion, IdActividad, AccionSeleccionada.getId()))== -1){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_FATAL, "No se pudo agregar fecha", "No se pudo agregar fecha" ));
            FacesContext.getCurrentInstance().renderResponse();
        }else{
            MedidasCorrectivas.get(IdActividad).setFechaImplementacion(FechaImplementacion);
        }
    }
    
    /**
     * Agrega una comprobacion de implementacion de la accion seleccionada.
     * Se debe comprobar que el estado sea proceso de Implementacion y todas las actividades hayan sido implementadas.
     * Muestra un mensaje si no se pudo agregar, de lo contrario se regresa a la lista de acciones.
     * @throws java.io.IOException
     */
    public void comprobarImplementacionAccion() throws IOException{
        if((fVerif.SetComprobacionImplementacionAccion(FechaImplementacion, ObservacionesComprobacion, ComprobacionSeleccionada, AccionSeleccionada.getId()))== -1){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_FATAL, "No se pudo comprobar implementacion", "No se pudo comprobar implementacion" ));
            FacesContext.getCurrentInstance().renderResponse();
        }else{
            // regresar a la pagina listar acciones
            String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(url+"/Views/Main/Main.xhtml");
        }
    }
    
    /**
     * Agrega una comprobacion de eficacia de la accion seleccionada.
     * Se debe comprobar que el estado sea proceso de verificacion.
     * Muestra un mensaje si no se pudo agregar, de lo contrario se regresa a la lista de acciones.
     */
    public void comprobarEficaciaAccion() throws IOException{
        if((fVerif.SetVerificacionEficaciaAccion(FechaImplementacion, ObservacionesComprobacion, ComprobacionSeleccionada, AccionSeleccionada.getId()))== -1){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_FATAL, "No se pudo comprobar eficacia", "No se pudo comprobar eficacia" ));
            FacesContext.getCurrentInstance().renderResponse();
        }else{
            // regresar a la pagina listar acciones preventivas
            String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(url+"/Views/Main/Main.xhtml");
        }
    }
    
    /**
     * Cambia el estado de la accion a desestimada y registra el motivo.
     * Se muestra un mensaje si no se pudo desestimar, de lo contrario regrega a la lista de acciones.
     * @throws IOException 
     */
    public void desestimarAccion() throws IOException{
        if(fVerif.DesestimarAccion(ObservacionesComprobacion, AccionSeleccionada.getId())== -1){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_FATAL, "No se pudo desestimar la accion", "No se pudo desestimar la accion" ));
            FacesContext.getCurrentInstance().renderResponse();
        }else{
            // regresar a la pagina listar acciones
            String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(url+"/Views/Main/Main.xhtml");
        }
    }
    
    
    //  Inicializcion
    @PostConstruct
    public void init(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        // recuperar el id para llenar datos de la accion y el resto de las propiedades.
        int IdAccion = Integer.parseInt(request.getParameter("id"));
        if(IdAccion != 0){
            AccionSeleccionada = (Correctiva) fLectura.GetAccion(IdAccion);
            List<Actividad> actividades = ((Correctiva)AccionSeleccionada).getMedidasCorrectivas();
            MedidasCorrectivas = new HashMap<>();
            for(Actividad act: actividades){
                MedidasCorrectivas.put(act.getIdActividad(), act);
            }
            actividades.clear();
            actividades = ((Correctiva)AccionSeleccionada).getMedidasPreventivas();
            MedidasPreventivas = new HashMap<>();
            for(Actividad act: actividades){
                MedidasPreventivas.put(act.getIdActividad(), act);
            }
            //  Usuarios
            this.ListaUsuarios = new HashMap<>();
            Empresa empresa = (Empresa) request.getSession().getAttribute("Empresa");
            // llenar lista de usuarios para responsables de implementacion.
            if(empresa!=null) {
                List<Usuario> tmpUsuarios = fLectura.GetUsuariosEmpresa(empresa.getId());
                for(Usuario usuario: tmpUsuarios){
                    ListaUsuarios.put(usuario.getId(), usuario);
                }
            }
            TipoDesvio = ((Correctiva)AccionSeleccionada).getTipo();
        }
        
        //  Resultado de comprobaciones
        Comprobaciones = EnumComprobacion.values();
        ComprobacionSeleccionada = EnumComprobacion.NO_COMPROBADA;
    }
    
    
}
