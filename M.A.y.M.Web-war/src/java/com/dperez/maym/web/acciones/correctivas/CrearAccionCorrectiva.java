/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.acciones.correctivas;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.accion.acciones.EnumAccion;
import com.dperez.maymweb.accion.acciones.EnumTipoDesvio;
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
public class CrearAccionCorrectiva implements Serializable {
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
    private EnumTipoDeteccion TipoNuevaDeteccion;
    private String NombreNuevaDeteccion;
    private Map<Integer, String> ListaDetecciones;
    private Integer DeteccionSeleccionada;
    
    private EnumTipoDesvio[] TiposDesvios;
    private EnumTipoDesvio TipoDesvioSeleccionado;
    
    private Map<Integer, Area> ListaAreasSectores;
    private Integer AreaSectorAccionSeleccionada;
    
    private Map<Integer, String> ListaCodificaciones;
    private Integer CodificacionSeleccionada;
    
    private boolean hayProductoAfectado;
    private Map<String, String> ListaProductosAfectados;
    private String NombreProductoAfectado;
    private String DatosProductoAfectado;
    
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
    public EnumTipoDeteccion getTipoNuevaDeteccion() {return TipoNuevaDeteccion;}
    
    public EnumTipoDesvio[] getTiposDesvios(){return this.TiposDesvios;}
    public EnumTipoDesvio getTipoDesvioSeleccionado(){return this.TipoDesvioSeleccionado;}
    
    public Map<Integer, Area> getListaAreasSectores(){return this.ListaAreasSectores;}
    public Integer getAreaSectorAccionSeleccionada() {return AreaSectorAccionSeleccionada;}
    
    public boolean isHayProductoAfectado() {return hayProductoAfectado;}
    public Map<String, String> getListaProductosAfectados(){return this.ListaProductosAfectados;}
    public String getNombreProductoAfectado(){return this.NombreProductoAfectado;}
    public String getDatosProductoAfectado(){return this.DatosProductoAfectado;}
    
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
    public void setTipoNuevaDeteccion(EnumTipoDeteccion TipoNuevaDeteccion) {this.TipoNuevaDeteccion = TipoNuevaDeteccion;}
    
    public void setTiposDesvios(EnumTipoDesvio[] TiposDesvios){this.TiposDesvios = TiposDesvios;}
    public void setTipoDesvioSeleccionado(EnumTipoDesvio TipoDesvioSeleccionado){this.TipoDesvioSeleccionado = TipoDesvioSeleccionado;}
    
    public void setListaAreaSectores(Map<Integer, Area> ListaAreasSectores){this.ListaAreasSectores = ListaAreasSectores;}
    public void setAreaSectorAccionSeleccionada(Integer AreaSectorAccionSeleccionada) {this.AreaSectorAccionSeleccionada = AreaSectorAccionSeleccionada;}
    
    public void setHayProductoAfectado(boolean hayProductoAfectado){
        this.hayProductoAfectado = hayProductoAfectado;
        if(hayProductoAfectado) this.ListaProductosAfectados = new HashMap<>();
    }
    public void setListaProductosAfectados(Map<String, String> ListaProductosAfectados) {this.ListaProductosAfectados = ListaProductosAfectados;}
    public void setNombreProductoAfectado(String NombreProductoAfectado){this.NombreProductoAfectado = NombreProductoAfectado;}
    public void setDatosProductoAfectado(String DatosProductoAfectado){this.DatosProductoAfectado = DatosProductoAfectado;}
    
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
        this.ListaDetecciones.put(0, " --- Nueva Deteccion --- ");
        List<Deteccion> tmpDetecciones = fLectura.ListarDetecciones();
        for(Deteccion deteccion:tmpDetecciones){
            if (deteccion.getTipo().equals(EnumTipoDeteccion.INTERNA)){
                ListaDetecciones.put(deteccion.getId(), deteccion.getNombre());
            }
        }
        
        //  Tipo de desvios
        TiposDesvios = EnumTipoDesvio.values();
        TipoDesvioSeleccionado = EnumTipoDesvio.NC_MENOR_OBS;
        
        // Areas Sectores
        ListaAreasSectores = new HashMap<>();
        List<Area> tmpAreas = fLectura.ListarAreasSectores();
        for(Area area:tmpAreas){
            this.ListaAreasSectores.put(area.getId(), area);
        }
    }
    
    /**
     * Actualiza la lista de detecciones segun la seleccion de tipo de deteccion.
     */
    public void actualizarDeteccion(){
        List<Deteccion> tmpDetecciones = fLectura.ListarDetecciones();
        this.ListaDetecciones.clear();
        this.ListaDetecciones.put(0, " --- Nueva Deteccion --- ");
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
            Deteccion det = fAdmin.NuevaDeteccion(NombreNuevaDeteccion, TipoDeDeteccionSeleccionada);
            if(det != null){
                actualizarDeteccion();
                this.DeteccionSeleccionada = det.getId();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_INFO, "Se agrego nueva deteccion", "Se agrego nueva deteccion" ));
                FacesContext.getCurrentInstance().renderResponse();
            }else{
                System.out.println("Error>");
            }
        }
    }
    
    /**
     * Agrega un nuevo producto afectado a la lista de productos afectados para ser persistidos durante la creacion de la accion correctiva.
     * Si el nombre del producto ya existe se sustituye
     * Si el nombre o el datos estan vacios no se crea y se muestra el mensaje correspondiente.
     */
    public void nuevoProductoAfectado(){
        if(NombreProductoAfectado.isEmpty() || DatosProductoAfectado.isEmpty()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_FATAL, "No se pudo agregar producto", "No se pudo agregar producto" ));
            FacesContext.getCurrentInstance().renderResponse();
        }else{
            this.ListaProductosAfectados.put(NombreProductoAfectado, DatosProductoAfectado);
            this.NombreProductoAfectado = new String();
            this.DatosProductoAfectado = new String();
        }
    }
    
    /**
     * Quita el producto seleccionado de la lista de productos afectados.
     * @param NombreProducto
     */
    public void quitarProducto(String NombreProducto){
        this.ListaProductosAfectados.remove(NombreProducto);
    }
    
    public void editarProducto(String NombreProducto){
        this.NombreProductoAfectado = NombreProducto;
        this.DatosProductoAfectado = this.ListaProductosAfectados.get(NombreProducto);
        this.ListaProductosAfectados.remove(NombreProducto);
    }
    
    /**
     * Crea la accion correctiva con los datos ingresados.
     * Agrega los productos involucrados si existen {hayProductoAfectado = True}
     * Si no se creo se muestra mensaje de error.
     * Si se creo se redirige a la pagina de listado de acciones.
     * @throws java.io.IOException
     */
    public void crearAccionCorrectiva() throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Empresa empresa = (Empresa)request.getSession().getAttribute("Empresa");
        Accion accion = fDatos.NuevaAccion(EnumAccion.CORRECTIVA, FechaDeteccion,
                Descripcion, TipoDesvioSeleccionado, AreaSectorAccionSeleccionada, DeteccionSeleccionada, CodificacionSeleccionada, empresa.getId());
        
        if(accion != null){
            // agrega los productos afectados
            if(hayProductoAfectado) fDatos.AgregarProductoInvolucrado(accion.getId(), ListaProductosAfectados);
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
                // redirigir a la lista de las acciones correctivas.
                String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
                FacesContext.getCurrentInstance().getExternalContext().redirect(url+"/Views/Main/Main.xhtml");
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_ERROR, "No se pudo crear la Accion", "No se pudo crear la Accion" ));
                FacesContext.getCurrentInstance().renderResponse();
            }
        }
    }
    
}
