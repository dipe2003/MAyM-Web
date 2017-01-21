/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.acciones.correctivas;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.accion.acciones.Correctiva;
import com.dperez.maymweb.accion.acciones.EnumAccion;
import com.dperez.maymweb.accion.acciones.EnumTipoDesvio;
import com.dperez.maymweb.area.Area;
import com.dperez.maymweb.codificacion.Codificacion;
import com.dperez.maymweb.deteccion.Deteccion;
import com.dperez.maymweb.deteccion.EnumTipoDeteccion;
import com.dperez.maymweb.facades.FacadeAdministrador;
import com.dperez.maymweb.facades.FacadeDatos;
import com.dperez.maymweb.facades.FacadeLectura;
import com.dperez.maymweb.producto.Producto;
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



@Named
@ViewScoped
public class EditarAccionCorrectiva implements Serializable {
    @Inject
    private FacadeAdministrador fAdmin;
    @Inject
    private FacadeLectura fLectura;
    @Inject
    private FacadeDatos fDatos;
    
    private int IdAccionCorrectiva;
    
    private Date FechaDeteccion;
    private String Descripcion;
    private String AnalisisCausa;
    
    private String TituloAdjunto;
    private String UbicacionAdjunto;
    private Map<String, String> ListaAdjuntos;
    
    private EnumTipoDeteccion[] TiposDeteccion;
    private EnumTipoDeteccion TipoDeDeteccionSeleccionada;
    private String NombreNuevaDeteccion;
    private Map<Integer, String> ListaDetecciones;
    private Integer DeteccionSeleccionada;
    
    private EnumTipoDesvio[] TiposDesvios;
    private EnumTipoDesvio TipoDesvioSeleccionado;
    
    private Map<Integer, String> ListaAreasSectores;
    private Integer AreaSectorAccionSeleccionada;
    
    private Map<Integer, String> ListaCodificaciones;
    private Integer CodificacionSeleccionada;
    
    private boolean hayProductoAfectado;
    private Map<String, String> ListaProductosAfectados;
    private String NombreProductoAfectado;
    private String DatosProductoAfectado;
    
    //  Getters
    public int getIdAccionCorrectiva(){return IdAccionCorrectiva;}
    public Date getFechaDeteccion() {return FechaDeteccion;}
    public String getDescripcion() {return Descripcion;}
    public String getAnalisisCausa() {return AnalisisCausa;}
    public String getTituloAdjunto(){return this.TituloAdjunto;}
    public String getUbicacionAdjunto(){return this.UbicacionAdjunto;}
    public Map<String, String> getAdjuntos() {return ListaAdjuntos;}
    
    public Map<Integer, String> getListaCodificaciones(){return this.ListaCodificaciones;}
    public Integer getCodificacionSeleccionada() {return CodificacionSeleccionada;}
    
    public EnumTipoDeteccion getTipoDeDeteccionSeleccionada(){return this.TipoDeDeteccionSeleccionada;}
    public EnumTipoDeteccion[] getTiposDeteccion(){return this.TiposDeteccion;}
    public Map<Integer, String> getListaDetecciones(){return this.ListaDetecciones;}
    public String getNombreNuevaDeteccion(){return this.NombreNuevaDeteccion;}
    public Integer getDeteccionSeleccionada(){return this.DeteccionSeleccionada;}
    
    public EnumTipoDesvio[] getTiposDesvios(){return this.TiposDesvios;}
    public EnumTipoDesvio getTipoDesvioSeleccionado(){return this.TipoDesvioSeleccionado;}
    
    public Map<Integer, String> getListaAreasSectores(){return this.ListaAreasSectores;}
    public Integer getAreaSectorAccionSeleccionada() {return AreaSectorAccionSeleccionada;}
    
    public boolean hayProductoAfectado(){return this.hayProductoAfectado;}
    public Map<String, String> getListaProductosAfectados(){return this.ListaProductosAfectados;}
    public String getNombreProductoAfectado(){return this.NombreProductoAfectado;}
    public String getDatosProductoAfectado(){return this.DatosProductoAfectado;}
    
    
    //  Setters
    public void serIdAccionCorrectiva(int IdAccionCorrectiva){this.IdAccionCorrectiva = IdAccionCorrectiva;}
    public void setFechaDeteccion(Date FechaDeteccion) {this.FechaDeteccion = FechaDeteccion;}
    public void setDescripcion(String Descripcion) {this.Descripcion = Descripcion;}
    public void setAnalisisCausa(String AnalisisCausa) {this.AnalisisCausa = AnalisisCausa;}
    public void setTituloAdjunto(String TituloAdjunto){this.TituloAdjunto = TituloAdjunto;}
    public void setUbicacionAdjunto(String UbicacionAdjunto){this.UbicacionAdjunto = UbicacionAdjunto;}
    public void setAdjuntos(Map<String, String> Adjuntos) {this.ListaAdjuntos = Adjuntos;}
    
    public void setListaCodificaciones(Map<Integer, String> ListaCodificaciones){this.ListaCodificaciones = ListaCodificaciones;}
    public void setCodificacionSeleccionada(Integer CodificacionSeleccionada) {this.CodificacionSeleccionada = CodificacionSeleccionada;}
    
    public void setTipoDeDeteccionSeleccionada(EnumTipoDeteccion TipoDeteccion){this.TipoDeDeteccionSeleccionada = TipoDeteccion;}
    public void setTiposDeteccion(EnumTipoDeteccion[] TiposDeteccion){this.TiposDeteccion = TiposDeteccion;}
    public void setListaDetecciones(Map<Integer, String> ListaDetecciones){this.ListaDetecciones = ListaDetecciones;}
    public void setNombreNuevaDeteccion(String NombreNuevaDeteccion){this.NombreNuevaDeteccion = NombreNuevaDeteccion;}
    public void setDeteccionSeleccionada(Integer DeteccionSeleccionada){this.DeteccionSeleccionada = DeteccionSeleccionada;}
    
    public void setTiposDesvios(EnumTipoDesvio[] TiposDesvios){this.TiposDesvios = TiposDesvios;}
    public void setTipoDesvioSeleccionado(EnumTipoDesvio TipoDesvioSeleccionado){this.TipoDesvioSeleccionado = TipoDesvioSeleccionado;}
    
    public void setListaAreaSectores(Map<Integer, String> ListaAreasSectores){this.ListaAreasSectores = ListaAreasSectores;}
    public void setAreaSectorAccionSeleccionada(Integer AreaSectorAccionSeleccionada) {this.AreaSectorAccionSeleccionada = AreaSectorAccionSeleccionada;}
    
    public void setHayProductoAfectado(boolean hayProductoAfectado){this.hayProductoAfectado = hayProductoAfectado;}
    public void setListaProductosAfectados(Map<String, String> ListaProductosAfectados) {this.ListaProductosAfectados = ListaProductosAfectados;}
    public void setNombreProductoAfectado(String NombreProductoAfectado){this.NombreProductoAfectado = NombreProductoAfectado;}
    public void setDatosProductoAfectado(String DatosProductoAfectado){this.DatosProductoAfectado = DatosProductoAfectado;}
    
    //  Metodos
    
    /**
     * Carga de propiedades al inicio
     */
    @PostConstruct
    public void init(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        // recuperar el id para llenar datos de la accion correctiva y el resto de las propiedades.
        int idAccion = 0;
        idAccion = Integer.parseInt(request.getParameter("id"));
        if(idAccion != 0){
            Accion AccionCorrectiva = (Correctiva) fLectura.GetAccion(idAccion);
            FechaDeteccion = AccionCorrectiva.getFechaDeteccion();
            Descripcion = AccionCorrectiva.getDescripcion();
            AnalisisCausa = AccionCorrectiva.getAnalisisCausa();
            
            //  Codificaciones
            ListaCodificaciones = new HashMap<>();
            List<Codificacion> tmpCodificaciones = fLectura.ListarCodificaciones();
            for(Codificacion codificacion:tmpCodificaciones){
                ListaCodificaciones.put(codificacion.getId(), codificacion.getNombre());
            }
            CodificacionSeleccionada = AccionCorrectiva.getCodificacionAccion().getId();
            
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
            DeteccionSeleccionada = AccionCorrectiva.getGeneradaPor().getId();
            
            //  Tipo de desvios
            TiposDesvios = EnumTipoDesvio.values();
            TipoDesvioSeleccionado = ((Correctiva)AccionCorrectiva).getTipo();
            
            // Areas Sectores
            ListaAreasSectores = new HashMap<>();
            List<Area> tmpAreas = fLectura.ListarAreasSectores();
            for(Area area:tmpAreas){
                this.ListaAreasSectores.put(area.getId(), area.getNombre());
            }
            AreaSectorAccionSeleccionada = AccionCorrectiva.getAreaSectorAccion().getId();
            
            if(!((Correctiva)AccionCorrectiva).getProductosAfectados().isEmpty()){
                List<Producto> ProductosAfectados = ((Correctiva)AccionCorrectiva).getProductosAfectados();
                for(Producto producto: ProductosAfectados){
                    ListaProductosAfectados.put(producto.getNombre(), producto.getDatos());
                }
                hayProductoAfectado = true;
            }
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
        }
    }
    
    /**
     * Actualiza la accion correctiva con los datos nuevos.
     * Si no se actualizo se muestra mensaje de error.
     * Si se creo se redirige a la pagina de listado de acciones.
     * @throws java.io.IOException
     */
    public void editarAccionCorrectiva() throws IOException{
        // actualizar accion
        if(fDatos.EditarAccion(IdAccionCorrectiva, EnumAccion.CORRECTIVA, FechaDeteccion, Descripcion, TipoDesvioSeleccionado, 
                AreaSectorAccionSeleccionada, DeteccionSeleccionada, CodificacionSeleccionada) != -1){
            // Si no se actualizo muestra mensaje de error.
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_ERROR, "No se pudo editar la Accion", "No se pudo editar la Accion" ));
        FacesContext.getCurrentInstance().renderResponse();
        }else{
            // Si la actualizacion se realizo correctamente redirige a lista de acciones.
        String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        FacesContext.getCurrentInstance().getExternalContext().redirect(url+"/Views/Main/Main.xhtml");
        }
    }
    
        /**
     * Elimina la accion de la base de datos.
     * Se eliminan todos los datos relacionados (actividades, adjuntos, comprobaciones y productos)
     * @throws java.io.IOException
     */
    public void eliminarAccionPreventiva() throws IOException{
        if(fAdmin.EliminarAccion(IdAccionCorrectiva)!=-1){
            // Si no se elimino muestra mensaje de error.
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_ERROR, "No se pudo eliminar la Accion", "No se pudo eliminar la Accion" ));
            FacesContext.getCurrentInstance().renderResponse();
        }else{
            // Si la eliminacion se realizo correctamente redirige a lista de acciones.
            String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(url+"/Views/Main/Main.xhtml");            
        }
    }
    
}
