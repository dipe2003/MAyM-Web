/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.acciones.preventivas;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.accion.acciones.EnumAccion;
import com.dperez.maymweb.accion.adjunto.CargarArchivo;
import com.dperez.maymweb.area.Area;
import com.dperez.maymweb.codificacion.Codificacion;
import com.dperez.maymweb.deteccion.Deteccion;
import com.dperez.maymweb.deteccion.EnumTipoDeteccion;
import com.dperez.maymweb.empresa.Empresa;
import com.dperez.maymweb.facades.FacadeAdministrador;
import com.dperez.maymweb.facades.FacadeDatos;
import com.dperez.maymweb.facades.FacadeLectura;
import java.io.IOException;
import java.io.Serializable;
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
import javax.servlet.http.Part;



@Named
@ViewScoped
public class CrearAccionPreventiva implements Serializable {
    @Inject
    private FacadeAdministrador fAdmin;
    @Inject
    private FacadeLectura fLectura;
    @Inject
    private FacadeDatos fDatos;
    @Inject
    private CargarArchivo cargaArchivos;
    
    private Date FechaDeteccion;
    private String Descripcion;
    private String AnalisisCausa;
    
    private String TituloAdjunto;
    private String UbicacionAdjunto;
    private Map<String, String> ListaAdjuntos;
    private Part ArchivoAdjunto;
    private Map<String, Part> ArchivosAdjuntos;
    
    private EnumTipoDeteccion[] TiposDeteccion;
    private EnumTipoDeteccion TipoDeDeteccionSeleccionada;
    private String NombreNuevaDeteccion;
    private Map<Integer, String> ListaDetecciones;
    private Integer DeteccionSeleccionada;
    
    private Map<Integer, String> ListaAreasSectores;
    private Integer AreaSectorAccionSeleccionada;
    
    private Map<Integer, String> ListaCodificaciones;
    private Integer CodificacionSeleccionada;
    
    //  Getters
    
    public Date getFechaDeteccion() {return FechaDeteccion;}
    public String getDescripcion() {return Descripcion;}
    public String getAnalisisCausa() {return AnalisisCausa;}
    public String getTituloAdjunto(){return this.TituloAdjunto;}
    public String getUbicacionAdjunto(){return this.UbicacionAdjunto;}
    public Map<String, String> getAdjuntos() {return ListaAdjuntos;}
    public Part getArchivoAdjunto() {return ArchivoAdjunto;}
    public Map<String, Part> getArchivosAdjuntos() {return ArchivosAdjuntos;}
    
    public Map<Integer, String> getListaCodificaciones(){return this.ListaCodificaciones;}
    public Integer getCodificacionSeleccionada() {return CodificacionSeleccionada;}
    
    public EnumTipoDeteccion getTipoDeDeteccionSeleccionada(){return this.TipoDeDeteccionSeleccionada;}
    public EnumTipoDeteccion[] getTiposDeteccion(){return this.TiposDeteccion;}
    public Map<Integer, String> getListaDetecciones(){return this.ListaDetecciones;}
    public String getNombreNuevaDeteccion(){return this.NombreNuevaDeteccion;}
    public Integer getDeteccionSeleccionada(){return this.DeteccionSeleccionada;}
    
    public Map<Integer, String> getListaAreasSectores(){return this.ListaAreasSectores;}
    public Integer getAreaSectorAccionSeleccionada() {return AreaSectorAccionSeleccionada;}
    
//  Setters
    
    public void setFechaDeteccion(Date FechaDeteccion) {this.FechaDeteccion = FechaDeteccion;}
    public void setDescripcion(String Descripcion) {this.Descripcion = Descripcion;}
    public void setAnalisisCausa(String AnalisisCausa) {this.AnalisisCausa = AnalisisCausa;}
    public void setTituloAdjunto(String TituloAdjunto){this.TituloAdjunto = TituloAdjunto;}
    public void setUbicacionAdjunto(String UbicacionAdjunto){this.UbicacionAdjunto = UbicacionAdjunto;}
    public void setAdjuntos(Map<String, String> Adjuntos) {this.ListaAdjuntos = Adjuntos;}
    public void setArchivoAdjunto(Part ArchivoAdjunto) {this.ArchivoAdjunto = ArchivoAdjunto;}
    public void setArchivosAdjuntos(Map<String, Part> ArchivosAdjuntos) {this.ArchivosAdjuntos = ArchivosAdjuntos;}
    
    public void setListaCodificaciones(Map<Integer, String> ListaCodificaciones){this.ListaCodificaciones = ListaCodificaciones;}
    public void setCodificacionSeleccionada(Integer CodificacionSeleccionada) {this.CodificacionSeleccionada = CodificacionSeleccionada;}
    
    public void setTipoDeDeteccionSeleccionada(EnumTipoDeteccion TipoDeteccion){this.TipoDeDeteccionSeleccionada = TipoDeteccion;}
    public void setTiposDeteccion(EnumTipoDeteccion[] TiposDeteccion){this.TiposDeteccion = TiposDeteccion;}
    public void setListaDetecciones(Map<Integer, String> ListaDetecciones){this.ListaDetecciones = ListaDetecciones;}
    public void setNombreNuevaDeteccion(String NombreNuevaDeteccion){this.NombreNuevaDeteccion = NombreNuevaDeteccion;}
    public void setDeteccionSeleccionada(Integer DeteccionSeleccionada){this.DeteccionSeleccionada = DeteccionSeleccionada;}
    
    public void setListaAreaSectores(Map<Integer, String> ListaAreasSectores){this.ListaAreasSectores = ListaAreasSectores;}
    public void setAreaSectorAccionSeleccionada(Integer AreaSectorAccionSeleccionada) {this.AreaSectorAccionSeleccionada = AreaSectorAccionSeleccionada;}
    
//  Metodos
    
    /**
     * Carga de propiedades al inicio
     */
    @PostConstruct
    public void init(){
        //  Codificaciones
        ListaCodificaciones = new HashMap<>();
        List<Codificacion> tmpCodificaciones = fLectura.ListarCodificaciones();
        for(Codificacion codificacion:tmpCodificaciones){
            ListaCodificaciones.put(codificacion.getId(), codificacion.getNombre());
        }
        
        
        //  Detecciones
        TiposDeteccion = EnumTipoDeteccion.values();
        TipoDeDeteccionSeleccionada = EnumTipoDeteccion.INTERNA;
        this.ListaDetecciones = new HashMap<>();
        List<Deteccion> tmpDetecciones = fLectura.ListarDetecciones();
        ListaDetecciones.put(0, "--- Nueva ---");
        for(Deteccion deteccion:tmpDetecciones){
            if (deteccion.getTipo().equals(EnumTipoDeteccion.INTERNA)){
                ListaDetecciones.put(deteccion.getId(), deteccion.getNombre());
            }
        }
        
        // Areas Sectores
        ListaAreasSectores = new HashMap<>();
        List<Area> tmpAreas = fLectura.ListarAreasSectores();
        for(Area area:tmpAreas){
            this.ListaAreasSectores.put(area.getId(), area.getNombre());
        }
    }
    
    /**
     * Actualiza la lista de detecciones segun la seleccion de tipo de deteccion.
     */
    public void actualizarDeteccion(){
        List<Deteccion> tmpDetecciones = fLectura.ListarDetecciones();
        ListaDetecciones.put(0, "--- Nueva ---");
        for(Deteccion deteccion:tmpDetecciones){
            if (deteccion.getTipo().equals(TipoDeDeteccionSeleccionada)){
                ListaDetecciones.put(deteccion.getId(), deteccion.getNombre());
            }
        }
    }
    
    /**
     * Crea nueva deteccion con el tipo interna/externa seleccionado.
     * Se verifica que el nombre no sea vacio. Si es vacio no se crea y se muestra un mensaje
     */
    public void nuevaDeteccion(){
        if(NombreNuevaDeteccion.isEmpty()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_FATAL, "No se pudo crear nueva deteccion", "No se pudo crear nueva deteccion" ));
            FacesContext.getCurrentInstance().renderResponse();
        }else{
            // Crear Nueva Deteccion y actualizar lista
            fAdmin.NuevaDeteccion(NombreNuevaDeteccion, TipoDeDeteccionSeleccionada);
            actualizarDeteccion();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_INFO, "Se agrego nueva deteccion", "Se agrego nueva deteccion" ));
            FacesContext.getCurrentInstance().renderResponse();
        }
    }
    
    /**
     * Crea la accion Preventiva con los datos ingresados.
     * Si no se creo se muestra mensaje de error.
     * Si se creo se redirige a la pagina de listado de acciones.
     * @throws java.io.IOException
     */
    public void crearAccionPreventiva() throws IOException{
        Accion accion = fDatos.NuevaAccion(EnumAccion.PREVENTIVA, FechaDeteccion, Descripcion, null, AreaSectorAccionSeleccionada, DeteccionSeleccionada, CodificacionSeleccionada);
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Empresa empresa = (Empresa)request.getSession().getAttribute("Empresa");
        if(accion != null){
            // Crear los adjuntos y agregarlos a la accion preventiva
            if(!ArchivosAdjuntos.isEmpty()){
                for(Map.Entry entry: ArchivosAdjuntos.entrySet()){
                    String ubicacion = cargaArchivos.guardarArchivo("Preventiva_" + String.valueOf(accion.getId()), (Part)entry.getValue(),(String) entry.getKey(), empresa.getNombreEmpresa());
                    if(!ubicacion.isEmpty())ListaAdjuntos.put((String) entry.getKey(), ubicacion);
                }
                if(!ListaAdjuntos.isEmpty()) {
                    fDatos.AgregarArchivoAdjunto(accion.getId(), ListaAdjuntos);
                }else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_ERROR, "No se pudieron cargar todos los adjuntos", "No se pudieron cargar todos los adjuntos" ));
                    FacesContext.getCurrentInstance().renderResponse();
                }
                // redirigir a la lista de las acciones preventivas.
                String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
                FacesContext.getCurrentInstance().getExternalContext().redirect(url+"/Views/Main/Main.xhtml");
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_ERROR, "No se pudo crear la Accion", "No se pudo crear la Accion" ));
                FacesContext.getCurrentInstance().renderResponse();
            }
        }
    }
}
