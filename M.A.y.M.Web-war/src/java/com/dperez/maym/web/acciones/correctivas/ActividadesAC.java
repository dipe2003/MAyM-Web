/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.acciones.correctivas;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.accion.acciones.Correctiva;
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
public class ActividadesAC implements Serializable {
    @Inject
    private FacadeDatos fDatos;
    @Inject
    private FacadeLectura fLectura;
    
    private Accion AccionSeleccionada;
    private String TipoActividad;
    private int IdActividadEditar;
    
    private Map<Integer, Usuario> ListaUsuariosEmpresa;    
    
    private Date FechaEstimadaImplementacionMedidaCorrectiva;
    private String strFechaEstimadaCorrectiva;    
    private String DescripcionMedidaCorrectiva;
    private int ResponsableMedidaCorrectiva;
    
    private Date FechaEstimadaImplementacionMedidaPreventiva;
    private String strFechaEstimadaPreventiva;
    private String DescripcionMedidaPreventiva;
    private int ResponsableMedidaPreventiva;
    
    //  Getters
    public String getTipoActividad() {return TipoActividad;}
    public Accion getAccionSeleccionada() {return AccionSeleccionada;}
    
    public int getIdActividadEditar() {return IdActividadEditar;}
    
    public Map<Integer, Usuario> getListaUsuariosEmpresa() {return ListaUsuariosEmpresa;}
    public Date getFechaEstimadaImplementacionMedidaCorrectiva() {return FechaEstimadaImplementacionMedidaCorrectiva;}
    public String getStrFechaEstimadaCorrectiva(){
        SimpleDateFormat fDate = new SimpleDateFormat("dd/MM/yyyy");
        if (FechaEstimadaImplementacionMedidaCorrectiva == null) {
            return this.strFechaEstimadaCorrectiva;
        }else{
            return fDate.format(FechaEstimadaImplementacionMedidaCorrectiva);
        }
    }
    public String getDescripcionMedidaCorrectiva() {return DescripcionMedidaCorrectiva;}
    public int getResponsableMedidaCorrectiva() {return ResponsableMedidaCorrectiva;}
    public Date getFechaEstimadaImplementacionMedidaPreventiva() {return FechaEstimadaImplementacionMedidaPreventiva;}
    public String getStrFechaEstimadaPreventiva(){
        SimpleDateFormat fDate = new SimpleDateFormat("dd/MM/yyyy");
        if (FechaEstimadaImplementacionMedidaPreventiva == null) {
            return this.strFechaEstimadaPreventiva;
        }else{
            return fDate.format(FechaEstimadaImplementacionMedidaPreventiva);
        }
    }
    public String getDescripcionMedidaPreventiva() {return DescripcionMedidaPreventiva;}
    public int getResponsableMedidaPreventiva() {return ResponsableMedidaPreventiva;}
    
    //  Setters
    public void setTipoActividad(String TipoActividad) {this.TipoActividad = TipoActividad;}
    public void setAccionSeleccionada(Accion AccionSeleccionada) {this.AccionSeleccionada = AccionSeleccionada;}
    
    public void setIdActividadEditar(int IdActividadEditar) {this.IdActividadEditar = IdActividadEditar;}
    
    public void setListaUsuariosEmpresa(Map<Integer, Usuario> ListaUsuariosEmpresa) {this.ListaUsuariosEmpresa = ListaUsuariosEmpresa;}
    public void setFechaEstimadaImplementacionMedidaCorrectiva(Date FechaEstimadaImplementacionMedidaCorrectiva) {this.FechaEstimadaImplementacionMedidaCorrectiva = FechaEstimadaImplementacionMedidaCorrectiva;}
    public void setStrFechaEstimadaCorrectiva(String strFechaEstimadaCorrectiva) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try{
            cal.setTime(sdf.parse(strFechaEstimadaCorrectiva));
        }catch(ParseException ex){}
        this.strFechaEstimadaCorrectiva = strFechaEstimadaCorrectiva;
        this.FechaEstimadaImplementacionMedidaCorrectiva = cal.getTime();
    }
    public void setDescripcionMedidaCorrectiva(String DescripcionMedidaCorrectiva) {this.DescripcionMedidaCorrectiva = DescripcionMedidaCorrectiva;}
    public void setResponsableMedidaCorrectiva(int ResponsableMedidaCorrectiva) {this.ResponsableMedidaCorrectiva = ResponsableMedidaCorrectiva;}
    public void setFechaEstimadaImplementacionMedidaPreventiva(Date FechaEstimadaImplementacionMedidaPreventiva) {this.FechaEstimadaImplementacionMedidaPreventiva = FechaEstimadaImplementacionMedidaPreventiva;}
    public void setStrFechaEstimadaPreventiva(String strFechaEstimadaPreventiva) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try{
            cal.setTime(sdf.parse(strFechaEstimadaPreventiva));
        }catch(ParseException ex){}
        this.strFechaEstimadaPreventiva = strFechaEstimadaPreventiva;
        this.FechaEstimadaImplementacionMedidaPreventiva = cal.getTime();
    }
    public void setDescripcionMedidaPreventiva(String DescripcionMedidaPreventiva) {this.DescripcionMedidaPreventiva = DescripcionMedidaPreventiva;}
    public void setResponsableMedidaPreventiva(int ResponsableMedidaPreventiva) {this.ResponsableMedidaPreventiva = ResponsableMedidaPreventiva;}
    
    //  Metodos
    /**
     * Agrega una nueva medida correctiva a la accion correctiva.     *
     * Se persisten los cambios.
     * Se redirige a la vista de editar accion
     * Muestra un mensaje de error si no se pudo completar correctamente.
     * @throws java.io.IOException
     */
    public void agregarMedidaCorrectiva() throws IOException{
        if(fDatos.AgregarMedidaCorrectiva(AccionSeleccionada.getId(), FechaEstimadaImplementacionMedidaCorrectiva, DescripcionMedidaCorrectiva,
                ResponsableMedidaCorrectiva)<0){
            FacesContext.getCurrentInstance().addMessage("form_agregar_actividades:btn_agregar_medida_correctiva", new FacesMessage(SEVERITY_FATAL, "No se pudo agregar Medida Correctiva", "No se pudo agregar Medida Correctiva" ));
            FacesContext.getCurrentInstance().renderResponse();
        }else{
            // redirigir a la edicion de la accion correctiva.
            String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(url+"/Views/Acciones/Correctivas/EditarAccionCorrectiva.xhtml?id="+AccionSeleccionada.getId());
        }
    }
    
    /**
     * Agrega una nueva medida preventiva a la accion correctiva.
     * Se persisten los cambios.
     * Muestra un mensaje de error si no se pudo completar correctamente.
     * @throws java.io.IOException
     */
    public void agregarMedidaPreventiva() throws IOException{
        if((fDatos.AgregarMedidaPreventiva(AccionSeleccionada.getId(), FechaEstimadaImplementacionMedidaPreventiva, DescripcionMedidaPreventiva,
                ResponsableMedidaPreventiva))<0){
            FacesContext.getCurrentInstance().addMessage("form_agregar_actividades:btn_agregar_medida_preventiva", new FacesMessage(SEVERITY_FATAL, "No se pudo agregar Medida Preventiva", "No se pudo agregar Medida Preventiva" ));
            FacesContext.getCurrentInstance().renderResponse();
        }else{
            // redirigir a la edicion de la accion correctiva.
            String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(url+"/Views/Acciones/Correctivas/EditarAccionCorrectiva.xhtml?id="+AccionSeleccionada.getId());
        }
    }
    
    /**
     * Remueve la medida correctiva de la accion correctiva.
     * Se persisten los cambios.
     * Muestra un mensaje de error si no se pudo completar correctamente.
     * @throws java.io.IOException
     */
    public void removerMedidaCorrectiva() throws IOException{
        if(fDatos.RemoverMedidaCorrectiva(AccionSeleccionada.getId(), IdActividadEditar)<0){
            FacesContext.getCurrentInstance().addMessage("form_agregar_actividades:btn_remover_medida_correctiva", new FacesMessage(SEVERITY_FATAL, "No se pudo quitar Medida Correctiva", "No se pudo quitar Medida Correctiva" ));
            FacesContext.getCurrentInstance().renderResponse();
        }else{
            // redirigir a la edicion de la accion correctiva.
            String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(url+"/Views/Acciones/Correctivas/EditarAccionCorrectiva.xhtml?id="+AccionSeleccionada.getId());
        }
    }
    
    /**
     * Remueve la medida Preventiva de la accion correctiva.
     * Se persisten los cambios.
     * Muestra un mensaje de error si no se pudo completar correctamente.
     * @throws java.io.IOException
     */
    public void removerMedidaPreventiva() throws IOException{
        if(fDatos.RemoverMedidaPreventiva(AccionSeleccionada.getId(), IdActividadEditar)< 0){
            FacesContext.getCurrentInstance().addMessage("form_agregar_actividades:btn_remover_medida_preventiva", new FacesMessage(SEVERITY_FATAL, "No se pudo quitar Medida Preventiva", "No se pudo quitar Medida Preventiva" ));
            FacesContext.getCurrentInstance().renderResponse();
        }else{
            // redirigir a la edicion de la accion correctiva.
            String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(url+"/Views/Acciones/Correctivas/EditarAccionCorrectiva.xhtml?id="+AccionSeleccionada.getId());
        }
    }
    
    /**
     * Guarda los cambios realizados en la medida correctiva y los persiste.
     * Se redirige a la vista de editar la accion correctiva correspondiente si se guardo, de lo contrario muestra un mensaje.
     * @throws IOException
     */
    public void guardarMedidaCorrectiva() throws IOException{
        if(fDatos.EditarActividad(IdActividadEditar, DescripcionMedidaCorrectiva, ResponsableMedidaCorrectiva, FechaEstimadaImplementacionMedidaCorrectiva) < 0){
            FacesContext.getCurrentInstance().addMessage("form_agregar_actividades:btn_agregar_medida_correctiva", new FacesMessage(SEVERITY_FATAL, "No se pudo guardar Medida Correctiva", "No se pudo guardar Medida Correctiva" ));
            FacesContext.getCurrentInstance().renderResponse();
        }else{
            // redirigir a la edicion de la accion correctiva.
            String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(url+"/Views/Acciones/Correctivas/EditarAccionCorrectiva.xhtml?id="+AccionSeleccionada.getId());
        }
    }
    /**
     * Guarda los cambios realizados en la medida preventiva y los persiste.
     * Se redirige a la vista de editar la accion correctiva correspondiente si se guardo, de lo contrario muestra un mensaje.
     * @throws IOException
     */
    public void guardarMedidaPreventiva() throws IOException{
        if(fDatos.EditarActividad(IdActividadEditar, DescripcionMedidaPreventiva, ResponsableMedidaPreventiva, FechaEstimadaImplementacionMedidaPreventiva) < 0){
            FacesContext.getCurrentInstance().addMessage("form_agregar_actividades:btn_agregar_medida_preventiva", new FacesMessage(SEVERITY_FATAL, "No se pudo Guardar Medida Preventiva", "No se pudo Guardar Medida Preventiva" ));
            FacesContext.getCurrentInstance().renderResponse();
        }else{
            // redirigir a la edicion de la accion correctiva.
            String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(url+"/Views/Acciones/Correctivas/EditarAccionCorrectiva.xhtml?id="+AccionSeleccionada.getId());
        }
    }
    
    //  Inicializacion del bean
    @PostConstruct
    public void init(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        // recuperar el id para llenar datos de la accion y el resto de las propiedades.
        int idAccion = 0;
        idAccion = Integer.parseInt(request.getParameter("id"));
        TipoActividad = (String)request.getParameter("tipo");
        try{
            IdActividadEditar = Integer.parseInt(request.getParameter("editar"));
        }catch(Exception ex){
            IdActividadEditar = 0;
        }
        if(idAccion != 0){
            AccionSeleccionada = (Correctiva) fLectura.GetAccion(idAccion);
            if(IdActividadEditar > 0){
                if(TipoActividad.equalsIgnoreCase("correctiva")){
                    //  Medidas Correctivas
                    if(!((Correctiva)AccionSeleccionada).getMedidasCorrectivas().isEmpty()){
                        List<Actividad> medidas = ((Correctiva)AccionSeleccionada).getMedidasCorrectivas();
                        for(Actividad medida: medidas ){
                            if(medida.getIdActividad() == IdActividadEditar){
                                FechaEstimadaImplementacionMedidaCorrectiva = medida.getFechaEstimadaImplementacion();
                                DescripcionMedidaCorrectiva =  medida.getDescripcion();
                                ResponsableMedidaCorrectiva = medida.getResponsableImplementacion().getId();
                            }
                        }
                    }
                }else{
                    //  Medidas Preventivas
                    if(!((Correctiva)AccionSeleccionada).getMedidasPreventivas().isEmpty()){
                        List<Actividad> medidas = ((Correctiva)AccionSeleccionada).getMedidasPreventivas();
                        for(Actividad medida: medidas ){
                            FechaEstimadaImplementacionMedidaCorrectiva = medida.getFechaEstimadaImplementacion();
                            DescripcionMedidaCorrectiva =  medida.getDescripcion();
                            ResponsableMedidaCorrectiva = medida.getResponsableImplementacion().getId();
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
