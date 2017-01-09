/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.accion.activades.correctivas;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.accion.acciones.Correctiva;
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
public class ActividadesAC implements Serializable {
    @Inject
    private FacadeDatos fDatos;
    @Inject
    private FacadeLectura fLectura;
    
    private Accion AccionCorrectiva;
    
    private Map<Integer, Usuario> ListaUsuariosEmpresa;
    
    private Map<Integer, Actividad> ListaMedidasCorrectivas;
    private int MedidaCorrectivaSeleccionada;
    
    private Map<Integer, Actividad> ListaMedidasPreventivas;
    private int MedidaPreventivaSeleccionada;
    
    private Date FechaEstimadaImplementacionMedidaCorrectiva;
    private String DescripcionMedidaCorrectiva;
    private int ResponsableMedidaCorrectiva;
    
    private Date FechaEstimadaImplementacionMedidaPreventiva;
    private String DescripcionMedidaPreventiva;
    private int ResponsableMedidaPreventiva;
    
    //  Getters
    public Map<Integer, Usuario> getListaUsuariosEmpresa() {return ListaUsuariosEmpresa;}
    public Map<Integer, Actividad> getListaMedidasCorrectivas() {return ListaMedidasCorrectivas;}
    public int getMedidaCorrectivaSeleccionada() {return MedidaCorrectivaSeleccionada;}
    public Map<Integer, Actividad> getListaMedidasPreventivas() {return ListaMedidasPreventivas;}
    public int getMedidaPreventivaSeleccionada() {return MedidaPreventivaSeleccionada;}
    public Date getFechaEstimadaImplementacionMedidaCorrectiva() {return FechaEstimadaImplementacionMedidaCorrectiva;}
    public String getDescripcionMedidaCorrectiva() {return DescripcionMedidaCorrectiva;}
    public int getResponsableMedidaCorrectiva() {return ResponsableMedidaCorrectiva;}
    public Date getFechaEstimadaImplementacionMedidaPreventiva() {return FechaEstimadaImplementacionMedidaPreventiva;}
    public String getDescripcionMedidaPreventiva() {return DescripcionMedidaPreventiva;}
    public int getResponsableMedidaPreventiva() {return ResponsableMedidaPreventiva;}
    
    //  Setters
    public void setListaUsuariosEmpresa(Map<Integer, Usuario> ListaUsuariosEmpresa) {this.ListaUsuariosEmpresa = ListaUsuariosEmpresa;}
    public void setListaMedidasCorrectivas(Map<Integer, Actividad> ListaMedidasCorrectivas) {this.ListaMedidasCorrectivas = ListaMedidasCorrectivas;}
    public void setMedidaCorrectivaSeleccionada(int MedidaCorrectivaSeleccionada) {this.MedidaCorrectivaSeleccionada = MedidaCorrectivaSeleccionada;}
    public void setListaMedidasPreventivas(Map<Integer, Actividad> ListaMedidasPreventivas) {this.ListaMedidasPreventivas = ListaMedidasPreventivas;}
    public void setMedidaPreventivaSeleccionada(int MedidaPreventivaSeleccionada) {this.MedidaPreventivaSeleccionada = MedidaPreventivaSeleccionada;}
    public void setFechaEstimadaImplementacionMedidaCorrectiva(Date FechaEstimadaImplementacionMedidaCorrectiva) {this.FechaEstimadaImplementacionMedidaCorrectiva = FechaEstimadaImplementacionMedidaCorrectiva;}
    public void setDescripcionMedidaCorrectiva(String DescripcionMedidaCorrectiva) {this.DescripcionMedidaCorrectiva = DescripcionMedidaCorrectiva;}
    public void setResponsableMedidaCorrectiva(int ResponsableMedidaCorrectiva) {this.ResponsableMedidaCorrectiva = ResponsableMedidaCorrectiva;}
    public void setFechaEstimadaImplementacionMedidaPreventiva(Date FechaEstimadaImplementacionMedidaPreventiva) {this.FechaEstimadaImplementacionMedidaPreventiva = FechaEstimadaImplementacionMedidaPreventiva;}
    public void setDescripcionMedidaPreventiva(String DescripcionMedidaPreventiva) {this.DescripcionMedidaPreventiva = DescripcionMedidaPreventiva;}
    public void setResponsableMedidaPreventiva(int ResponsableMedidaPreventiva) {this.ResponsableMedidaPreventiva = ResponsableMedidaPreventiva;}
    
    //  Metodos
    /**
     * Agrega una nueva medida correctiva a la accion correctiva.
     * Se actualiza la lista de medidas correctivas del bean.
     * Se persisten los cambios.
     * Muestra un mensaje de error si no se pudo completar correctamente.
     */
    public void agregarMedidaCorrectiva(){
        if(!DescripcionMedidaCorrectiva.isEmpty() && FechaEstimadaImplementacionMedidaCorrectiva != null && ResponsableMedidaCorrectiva != 0){
            Actividad Medida  = new Actividad();
            Usuario responsable = ListaUsuariosEmpresa.get(ResponsableMedidaCorrectiva);
            Medida.setDescripcion(DescripcionMedidaCorrectiva);
            Medida.setFechaEstimadaImplementacion(FechaEstimadaImplementacionMedidaCorrectiva);
            Medida.setResponsableImplementacion(responsable);
            int id;
            if((id = fDatos.AgregarMedidaCorrectiva(AccionCorrectiva.getId(), Medida.getFechaEstimadaImplementacion(), Medida.getDescripcion(),
                    Medida.getResponsableImplementacion().getId()))<0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_FATAL, "No se pudo agregar Medida Correctiva", "No se pudo agregar Medida Correctiva" ));
                FacesContext.getCurrentInstance().renderResponse();
            }else{
                // agregar a la lista de medidas del bean
                Medida.setId(id);
                ListaMedidasCorrectivas.put(Medida.getId(), Medida);
            }
        }
    }
    
    /**
     * Agrega una nueva medida preventiva a la accion correctiva.
     * Se actualiza la lista de medidas preventivas del bean.
     * Se persisten los cambios.
     * Muestra un mensaje de error si no se pudo completar correctamente.
     */
    public void agregarMedidaPreventiva(){
        if(!DescripcionMedidaPreventiva.isEmpty() && FechaEstimadaImplementacionMedidaPreventiva != null && ResponsableMedidaPreventiva != 0){
            Actividad Medida  = new Actividad();
            Usuario responsable = ListaUsuariosEmpresa.get(ResponsableMedidaPreventiva);
            Medida.setDescripcion(DescripcionMedidaPreventiva);
            Medida.setFechaEstimadaImplementacion(FechaEstimadaImplementacionMedidaPreventiva);
            Medida.setResponsableImplementacion(responsable);
            int id;
            if((id = fDatos.AgregarMedidaPreventiva(AccionCorrectiva.getId(), Medida.getFechaEstimadaImplementacion(), Medida.getDescripcion(),
                    Medida.getResponsableImplementacion().getId()))<0){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_FATAL, "No se pudo agregar Medida Preventiva", "No se pudo agregar Medida Preventiva" ));
                FacesContext.getCurrentInstance().renderResponse();
            }else{
                // agregar a la lista de medidas del bean
                Medida.setId(id);
                ListaMedidasCorrectivas.put(Medida.getId(), Medida);
            }
        }
    }
    
    /**
     * Remueve la medida correctiva de la lista de medidas correctivas si la fecha de implementacion no esta definida.
     * Remueve la medida correctiva de la accion correctiva.
     * Se persisten los cambios.
     * Muestra un mensaje de error si no se pudo completar correctamente.
     * @param IdMedidaCorrectiva
     */
    public void removerMedidaCorrectiva(int IdMedidaCorrectiva){
        if(IdMedidaCorrectiva!=0 && ListaMedidasCorrectivas.get(IdMedidaCorrectiva).getFechaImplementacion() == null){
            int res;
            if((res = fDatos.RemoverMedidaCorrectiva(AccionCorrectiva.getId(), IdMedidaCorrectiva))> 0){
                ListaMedidasCorrectivas.remove(res);
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_FATAL, "No se pudo quitar Medida Correctiva", "No se pudo quitar Medida Correctiva" ));
                FacesContext.getCurrentInstance().renderResponse();
            }
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_FATAL, "No se puede quitar porque ya fue implementada", "No se puede quitar porque ya fue implementada" ));
            FacesContext.getCurrentInstance().renderResponse();
        }
    }
    
    /**
     * Remueve la medida preventiva de la lista de medidas preventivas si la fecha de implementacion no esta definida.
     * Remueve la medida Preventiva de la accion correctiva.
     * Se persisten los cambios.
     * Muestra un mensaje de error si no se pudo completar correctamente.
     * @param IdMedidaPreventiva
     */
    public void removerMedidaPreventiva(int IdMedidaPreventiva){
        if(IdMedidaPreventiva!=0 && ListaMedidasPreventivas.get(IdMedidaPreventiva).getFechaImplementacion() == null){
            int res;
            if((res = fDatos.RemoverMedidaPreventiva(AccionCorrectiva.getId(), IdMedidaPreventiva))> 0){
                ListaMedidasPreventivas.remove(res);
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_FATAL, "No se pudo quitar Medida Preventiva", "No se pudo quitar Medida Preventiva" ));
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
        // recuperar el id para llenar datos de la accion correctiva y el resto de las propiedades.
        int idAccion = 0;
        idAccion = Integer.parseInt(request.getParameter("id"));
        if(idAccion != 0){
            AccionCorrectiva = (Correctiva) fLectura.GetAccion(idAccion);
            //  Medidas Correctivas
            ListaMedidasCorrectivas = new HashMap<>();
            if(!((Correctiva)AccionCorrectiva).getMedidasCorrectivas().isEmpty()){
                List<Actividad> medidas = ((Correctiva)AccionCorrectiva).getMedidasCorrectivas();
                for(Actividad medida: medidas ){
                    ListaMedidasCorrectivas.put(medida.getId(), medida);
                }
            }
            //  Medidas Preventivas
            ListaMedidasPreventivas = new HashMap<>();
            if(!((Correctiva)AccionCorrectiva).getMedidasPreventivas().isEmpty()){
                List<Actividad> medidas = ((Correctiva)AccionCorrectiva).getMedidasPreventivas();
                for(Actividad medida: medidas ){
                    ListaMedidasPreventivas.put(medida.getId(), medida);
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
