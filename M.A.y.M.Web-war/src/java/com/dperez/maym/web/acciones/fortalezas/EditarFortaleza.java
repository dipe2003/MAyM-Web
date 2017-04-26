/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.acciones.fortalezas;

import com.dperez.maymweb.area.Area;
import com.dperez.maymweb.deteccion.Deteccion;
import com.dperez.maymweb.deteccion.EnumTipoDeteccion;
import com.dperez.maymweb.empresa.Empresa;
import com.dperez.maymweb.facades.FacadeAdministrador;
import com.dperez.maymweb.facades.FacadeDatos;
import com.dperez.maymweb.facades.FacadeLectura;
import com.dperez.maymweb.fortaleza.Fortaleza;
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
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_FATAL;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;



@Named
@ViewScoped
public class EditarFortaleza implements Serializable {
    @Inject
    private FacadeAdministrador fAdmin;
    @Inject
    private FacadeLectura fLectura;
    @Inject
    private FacadeDatos fDatos;
    
    private Empresa EmpresaLogueada;
    
    private int IdFortaleza;
    
    private Date FechaDeteccion;
    private String strFechaDeteccion;
    private String Descripcion;
    
    private EnumTipoDeteccion[] TiposDeteccion;
    private EnumTipoDeteccion TipoDeDeteccionSeleccionada;
    private String NombreNuevaDeteccion;
    private Map<Integer, Deteccion> ListaDetecciones;
    private Integer DeteccionSeleccionada;
    
    private Map<Integer, Area> ListaAreasSectores;
    private Integer AreaSectorAccionSeleccionada;
    
    //  Getters
    public int getIdFortaleza(){return IdFortaleza;}
    public Date getFechaDeteccion() {return FechaDeteccion;}
    public String getStrFechaDeteccion(){
        SimpleDateFormat fDate = new SimpleDateFormat("dd/MM/yyyy");
        if (FechaDeteccion == null) {
            return this.strFechaDeteccion;
        }else{
            return fDate.format(FechaDeteccion);
        }
    }
    public String getDescripcion() {return Descripcion;}
    
    public EnumTipoDeteccion getTipoDeDeteccionSeleccionada(){return this.TipoDeDeteccionSeleccionada;}
    public EnumTipoDeteccion[] getTiposDeteccion(){return this.TiposDeteccion;}
    
    public Map<Integer, Deteccion> getListaDetecciones(){return this.ListaDetecciones;}
    public String getNombreNuevaDeteccion(){return this.NombreNuevaDeteccion;}
    public Integer getDeteccionSeleccionada(){return this.DeteccionSeleccionada;}
    
    public Map<Integer, Area> getListaAreasSectores(){return this.ListaAreasSectores;}
    public Integer getAreaSectorAccionSeleccionada() {return AreaSectorAccionSeleccionada;}
    
    
    //  Setters
    public void serIdAccionCorrectiva(int IdAccionCorrectiva){this.IdFortaleza = IdAccionCorrectiva;}
    public void setFechaDeteccion(Date FechaDeteccion) {this.FechaDeteccion = FechaDeteccion;}
    public void setStrFechaDeteccion(String strFechaDeteccion) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try{
            cal.setTime(sdf.parse(strFechaDeteccion));
        }catch(ParseException ex){}
        this.strFechaDeteccion = strFechaDeteccion;
        this.FechaDeteccion = cal.getTime();
    }
    public void setDescripcion(String Descripcion) {this.Descripcion = Descripcion;}
    
    public void setTipoDeDeteccionSeleccionada(EnumTipoDeteccion TipoDeteccion){this.TipoDeDeteccionSeleccionada = TipoDeteccion;}
    public void setTiposDeteccion(EnumTipoDeteccion[] TiposDeteccion){this.TiposDeteccion = TiposDeteccion;}
    
    public void setListaDetecciones(Map<Integer, Deteccion> ListaDetecciones){this.ListaDetecciones = ListaDetecciones;}
    public void setNombreNuevaDeteccion(String NombreNuevaDeteccion){this.NombreNuevaDeteccion = NombreNuevaDeteccion;}
    public void setDeteccionSeleccionada(Integer DeteccionSeleccionada){this.DeteccionSeleccionada = DeteccionSeleccionada;}
    
    public void setListaAreaSectores(Map<Integer, Area> ListaAreasSectores){this.ListaAreasSectores = ListaAreasSectores;}
    public void setAreaSectorAccionSeleccionada(Integer AreaSectorAccionSeleccionada) {this.AreaSectorAccionSeleccionada = AreaSectorAccionSeleccionada;}
    
    //  Metodos
    
    /**
     * Carga de propiedades al inicio
     */
    @PostConstruct
    public void init(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        // Empresa
        EmpresaLogueada = (Empresa) request.getSession().getAttribute("Empresa");
        // recuperar el id para llenar datos de la accion de mejora y el resto de las propiedades.
        IdFortaleza = Integer.parseInt(request.getParameter("id"));
        if(IdFortaleza != 0){
            Fortaleza FortalezaSeleccionada = fLectura.GetFotaleza(IdFortaleza);
            FechaDeteccion = FortalezaSeleccionada.getFechaDeteccion();
            Descripcion = FortalezaSeleccionada.getDescripcion();
            
            //  Detecciones
            TiposDeteccion = EnumTipoDeteccion.values();
            TipoDeDeteccionSeleccionada = EnumTipoDeteccion.INTERNA;
            this.ListaDetecciones = new HashMap<>();
            List<Deteccion> tmpDetecciones = fLectura.ListarDetecciones();
            for(Deteccion deteccion:tmpDetecciones){
                if (deteccion.getTipo().equals(EnumTipoDeteccion.INTERNA)){
                    ListaDetecciones.put(deteccion.getId(), deteccion);
                }
            }
            DeteccionSeleccionada = FortalezaSeleccionada.getGeneradaPor().getId();
            
            // Areas Sectores
            ListaAreasSectores = new HashMap<>();
            List<Area> tmpAreas = fLectura.ListarAreasSectores(EmpresaLogueada.getId());
            for(Area area:tmpAreas){
                this.ListaAreasSectores.put(area.getId(), area);
            }
            AreaSectorAccionSeleccionada = FortalezaSeleccionada.getAreaSectorFortaleza().getId();
        }
        
    }
    
    /**
     * Actualiza la lista de detecciones segun la seleccion de tipo de deteccion.
     */
    public void actualizarDeteccion(){
        List<Deteccion> tmpDetecciones = fLectura.ListarDetecciones();
        for(Deteccion deteccion:tmpDetecciones){
            if (deteccion.getTipo().equals(TipoDeDeteccionSeleccionada)){
                ListaDetecciones.put(deteccion.getId(), deteccion);
            }
        }
    }
    
    /**
     * Crea nueva deteccion con el tipo interna/externa seleccionado.
     * Se verifica que el nombre no sea vacio. Si es vacio no se crea y se muestra un mensaje
     */
    public void nuevaDeteccion(){
        // Crear Nueva Deteccion y actualizar lista
        Deteccion det = fAdmin.NuevaDeteccion(NombreNuevaDeteccion, TipoDeDeteccionSeleccionada);
        if(det != null){
            actualizarDeteccion();
            this.DeteccionSeleccionada = det.getId();
            this.NombreNuevaDeteccion = new String();
            this.TipoDeDeteccionSeleccionada = det.getTipo();
            FacesContext.getCurrentInstance().addMessage("form_editar_fortaleza_modal:btn_nueva_deteccion", new FacesMessage(SEVERITY_INFO, det.getNombre() + " fue creada.", det.getNombre() + " fue creada." ));
            FacesContext.getCurrentInstance().renderResponse();
        }else{
            FacesContext.getCurrentInstance().addMessage("form_editar_fortaleza_modal:btn_nueva_deteccion", new FacesMessage(SEVERITY_FATAL, "No se pudo crear nueva deteccion", "No se pudo crear nueva deteccion" ));
            FacesContext.getCurrentInstance().renderResponse();
        }
    }
    
    
    /**
     * Actualiza la fortaleza con los datos nuevos.
     * Si no se actualizo se muestra mensaje de error.
     * Si se creo se redirige a la pagina de listado de fortalezas.
     * @throws java.io.IOException
     */
    public void editarFortaleza() throws IOException{
        // actualizar fortaleza
        if(fDatos.EditarFortaleza(IdFortaleza, FechaDeteccion, Descripcion, DeteccionSeleccionada, AreaSectorAccionSeleccionada) == -1){
            // Si no se actualizo muestra mensaje de error.
            FacesContext.getCurrentInstance().addMessage("form_editar_fortaleza:guardar_fortaleza", new FacesMessage(SEVERITY_INFO, "No se pudo editar la fortaleza", "No se pudo editar la fortaleza" ));
            FacesContext.getCurrentInstance().renderResponse();
        }else{
            FacesContext.getCurrentInstance().addMessage("form_editar_fortaleza:guardar_fortaleza", new FacesMessage(SEVERITY_INFO, "Los datos se guardaron.", "Los datos se guardaron." ));
            FacesContext.getCurrentInstance().renderResponse();
        }
    }
    
    /**
     * Elimina la fortaleza de la base de datos.
     * @throws java.io.IOException
     */
    public void eliminarFortaleza() throws IOException{
        if(fAdmin.EliminarFortaleza(IdFortaleza)==-1){
            // Si no se elimino muestra mensaje de error.
            FacesContext.getCurrentInstance().addMessage("form_editar_fortaleza:eliminar_fortaleza", new FacesMessage(SEVERITY_ERROR, "No se pudo eliminar la Accion", "No se pudo eliminar la Accion" ));
            FacesContext.getCurrentInstance().renderResponse();
        }else{
            // Si la eliminacion se realizo correctamente redirige a lista de fortalezas.
            String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(url+"/Views/Acciones/Fortalezas/ListarFortalezas.xhtml?PAGINA");
        }
    }
    
    public void limpiarModalDeteccion(){
        this.NombreNuevaDeteccion = new String();
    }
    
}
