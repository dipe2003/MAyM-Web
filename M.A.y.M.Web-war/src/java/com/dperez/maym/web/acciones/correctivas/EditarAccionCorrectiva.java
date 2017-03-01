/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.acciones.correctivas;

import com.dperez.maym.web.herramientas.CargarArchivo;
import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.accion.acciones.Correctiva;
import com.dperez.maymweb.accion.acciones.EnumAccion;
import com.dperez.maymweb.accion.acciones.EnumTipoDesvio;
import com.dperez.maymweb.accion.adjunto.Adjunto;
import com.dperez.maymweb.accion.adjunto.EnumTipoAdjunto;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import javax.servlet.http.Part;



@Named
@ViewScoped
public class EditarAccionCorrectiva implements Serializable {
    @Inject
    private FacadeAdministrador fAdmin;
    @Inject
    private FacadeLectura fLectura;
    @Inject
    private FacadeDatos fDatos;
    @Inject
    private CargarArchivo cArchivo;
    
    private int IdAccionSeleccionada;
    
    private Date FechaDeteccion;
    private String strFechaDeteccion;
    private String Descripcion;
    private String AnalisisCausa;
    
    private String TituloAdjunto;
    private Map<Integer, Adjunto> MapAdjuntos;
    private Part ArchivoAdjunto;
    
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
    private String NombreNuevaCodificacion;
    private String DescripcionNuevaCodificacion;
    
    private boolean hayProductoAfectado;
    private Map<String, String> ListaProductosAfectados;
    private String NombreProductoAfectado;
    private String DatosProductoAfectado;
    
    //  Getters
    public int getIdAccionSeleccionada(){return IdAccionSeleccionada;}
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
    public String getAnalisisCausa() {return AnalisisCausa;}
    
    public String getTituloAdjunto(){return this.TituloAdjunto;}
    public Part getArchivoAdjunto() {return ArchivoAdjunto;}
    public Map<Integer, Adjunto> getMapAdjuntos() {return MapAdjuntos;}
    
    public Map<Integer, String> getListaCodificaciones(){return this.ListaCodificaciones;}
    public Integer getCodificacionSeleccionada() {return CodificacionSeleccionada;}
    public String getNombreNuevaCodificacion() {return NombreNuevaCodificacion;}
    public String getDescripcionNuevaCodificacion() {return DescripcionNuevaCodificacion;}
    
    public EnumTipoDeteccion getTipoDeDeteccionSeleccionada(){return this.TipoDeDeteccionSeleccionada;}
    public EnumTipoDeteccion[] getTiposDeteccion(){return this.TiposDeteccion;}
    public Map<Integer, String> getListaDetecciones(){return this.ListaDetecciones;}
    public String getNombreNuevaDeteccion(){return this.NombreNuevaDeteccion;}
    public EnumTipoDeteccion getTipoNuevaDeteccion() {return TipoNuevaDeteccion;}
    public Integer getDeteccionSeleccionada(){return this.DeteccionSeleccionada;}
    
    public EnumTipoDesvio[] getTiposDesvios(){return this.TiposDesvios;}
    public EnumTipoDesvio getTipoDesvioSeleccionado(){return this.TipoDesvioSeleccionado;}
    
    public Map<Integer, Area> getListaAreasSectores(){return this.ListaAreasSectores;}
    public Integer getAreaSectorAccionSeleccionada() {return AreaSectorAccionSeleccionada;}
    
    public boolean isHayProductoAfectado() {return hayProductoAfectado;}
    public Map<String, String> getListaProductosAfectados(){return this.ListaProductosAfectados;}
    public String getNombreProductoAfectado(){return this.NombreProductoAfectado;}
    public String getDatosProductoAfectado(){return this.DatosProductoAfectado;}
    
    //  Setters
    public void setIdAccionSeleccionada(int IdAccionSeleccionada) {this.IdAccionSeleccionada = IdAccionSeleccionada;}
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
    public void setAnalisisCausa(String AnalisisCausa) {this.AnalisisCausa = AnalisisCausa;}
    
    public void setTituloAdjunto(String TituloAdjunto){this.TituloAdjunto = TituloAdjunto;}
    public void setArchivoAdjunto(Part ArchivoAdjunto) {this.ArchivoAdjunto = ArchivoAdjunto;}
    public void setMapAdjuntos(Map<Integer, Adjunto> MapAdjuntos) {this.MapAdjuntos = MapAdjuntos;}
    
    public void setListaCodificaciones(Map<Integer, String> ListaCodificaciones){this.ListaCodificaciones = ListaCodificaciones;}
    public void setCodificacionSeleccionada(Integer CodificacionSeleccionada) {this.CodificacionSeleccionada = CodificacionSeleccionada;}
    public void setNombreNuevaCodificacion(String NombreNuevaCodificacion) {this.NombreNuevaCodificacion = NombreNuevaCodificacion;}
    public void setDescripcionNuevaCodificacion(String DescripcionNuevaCodificacion) {this.DescripcionNuevaCodificacion = DescripcionNuevaCodificacion;}
    
    public void setTipoDeDeteccionSeleccionada(EnumTipoDeteccion TipoDeteccion){this.TipoDeDeteccionSeleccionada = TipoDeteccion;}
    public void setTiposDeteccion(EnumTipoDeteccion[] TiposDeteccion){this.TiposDeteccion = TiposDeteccion;}
    public void setListaDetecciones(Map<Integer, String> ListaDetecciones){this.ListaDetecciones = ListaDetecciones;}
    public void setNombreNuevaDeteccion(String NombreNuevaDeteccion){this.NombreNuevaDeteccion = NombreNuevaDeteccion;}
    public void setTipoNuevaDeteccion(EnumTipoDeteccion TipoNuevaDeteccion) {this.TipoNuevaDeteccion = TipoNuevaDeteccion;}
    public void setDeteccionSeleccionada(Integer DeteccionSeleccionada){this.DeteccionSeleccionada = DeteccionSeleccionada;}
    
    public void setTiposDesvios(EnumTipoDesvio[] TiposDesvios){this.TiposDesvios = TiposDesvios;}
    public void setTipoDesvioSeleccionado(EnumTipoDesvio TipoDesvioSeleccionado){this.TipoDesvioSeleccionado = TipoDesvioSeleccionado;}
    
    public void setListaAreasSectores(Map<Integer, Area> ListaAreasSectores) {this.ListaAreasSectores = ListaAreasSectores;}
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
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        // recuperar el id para llenar datos de la accion correctiva y el resto de las propiedades.
        IdAccionSeleccionada = Integer.parseInt(request.getParameter("id"));
        if(IdAccionSeleccionada != 0){
            Accion AccionSeleccionada = (Correctiva) fLectura.GetAccion(IdAccionSeleccionada);
            FechaDeteccion = AccionSeleccionada.getFechaDeteccion();
            Descripcion = AccionSeleccionada.getDescripcion();
            AnalisisCausa = AccionSeleccionada.getAnalisisCausa();
            
            //  Codificaciones
            ListaCodificaciones = new HashMap<>();
            List<Codificacion> tmpCodificaciones = fLectura.ListarCodificaciones();
            ListaCodificaciones.put(0, "--- Nueva ---");
            for(Codificacion codificacion:tmpCodificaciones){
                ListaCodificaciones.put(codificacion.getId(), codificacion.getNombre());
            }
            CodificacionSeleccionada = AccionSeleccionada.getCodificacionAccion().getId();
            
            //  Detecciones
            TiposDeteccion = EnumTipoDeteccion.values();
            TipoDeDeteccionSeleccionada = AccionSeleccionada.getGeneradaPor().getTipo();
            
            this.ListaDetecciones = new HashMap<>();
            List<Deteccion> tmpDetecciones = fLectura.ListarDetecciones();
            ListaDetecciones.put(0, "--- Nueva ---");
            for(Deteccion deteccion:tmpDetecciones){
                if (deteccion.getTipo().equals(TipoDeDeteccionSeleccionada)){
                    ListaDetecciones.put(deteccion.getId(), deteccion.getNombre());
                }
            }
            DeteccionSeleccionada = AccionSeleccionada.getGeneradaPor().getId();
            
            //  Tipo de desvios
            TiposDesvios = EnumTipoDesvio.values();
            TipoDesvioSeleccionado = ((Correctiva)AccionSeleccionada).getTipo();
            
            // Areas Sectores
            ListaAreasSectores = new HashMap<>();
            List<Area> tmpAreas = fLectura.ListarAreasSectores();
            for(Area area:tmpAreas){
                this.ListaAreasSectores.put(area.getId(), area);
            }
            AreaSectorAccionSeleccionada = AccionSeleccionada.getAreaSectorAccion().getId();
            ListaProductosAfectados = new HashMap<>();
            if(!((Correctiva)AccionSeleccionada).getProductosAfectados().isEmpty()){
                List<Producto> ProductosAfectados = ((Correctiva)AccionSeleccionada).getProductosAfectados();
                for(Producto producto: ProductosAfectados){
                    ListaProductosAfectados.put(producto.getNombre(), producto.getDatos());
                }
                hayProductoAfectado = true;
            }
            actualizarListaAdjuntos();
        }
    }
    
    private void actualizarListaAdjuntos(){
        Accion AccionCorrectiva = (Correctiva) fLectura.GetAccion(IdAccionSeleccionada);
        if(!AccionCorrectiva.getAdjuntos().isEmpty()){
            List<Adjunto> listAdjuntos = AccionCorrectiva.getAdjuntos();
            this.MapAdjuntos = new HashMap<>();
            for(Adjunto adjunto: listAdjuntos){
                this.MapAdjuntos.put(adjunto.getId(), adjunto);
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
            fAdmin.NuevaDeteccion(NombreNuevaDeteccion, TipoNuevaDeteccion);
            actualizarDeteccion();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_INFO, "Se agrego nueva deteccion", "Se agrego nueva deteccion" ));
            FacesContext.getCurrentInstance().renderResponse();
        }
    }
    
    /**
     * Crea nueva codificacion.
     * Se verifica que el nombre o la descripcion no esten vacios. Si estan vacios no se crea y se muestra un mensaje
     */
    public void nuevaCodificacion(){
        if(NombreNuevaCodificacion.isEmpty() || DescripcionNuevaCodificacion.isEmpty()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_FATAL, "No se pudo crear nueva codificacion", "No se pudo crear nueva codificacion" ));
            FacesContext.getCurrentInstance().renderResponse();
        }else{
            // Crear Nueva Codificacion y actualizar lista
            Codificacion cod = fAdmin.NuevaCodificacion(NombreNuevaCodificacion, DescripcionNuevaCodificacion);
            if(cod != null){
                actualizarCodificacion();
                this.CodificacionSeleccionada = cod.getId();
                this.NombreNuevaCodificacion = new String();
                this.DescripcionNuevaCodificacion = new String();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_INFO, "Se agrego nueva codificacion", "Se agrego nueva codificacion" ));
                FacesContext.getCurrentInstance().renderResponse();
            }else{
                System.out.println("Error");
            }
        }
    }
    
    /**
     * Actualiza la lista de codificaciones.
     */
    public void actualizarCodificacion(){
        List<Codificacion> tmpCodificacion = fLectura.ListarCodificaciones();
        this.ListaCodificaciones.clear();
        this.ListaCodificaciones.put(0, " --- Nueva Codificacion --- ");
        for(Codificacion codificacion:tmpCodificacion){
            ListaCodificaciones.put(codificacion.getId(), codificacion.getNombre());
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
            if((fDatos.AgregarProductoInvolucrado(IdAccionSeleccionada, NombreProductoAfectado, DatosProductoAfectado))!=-1){
                this.ListaProductosAfectados.put(NombreProductoAfectado, DatosProductoAfectado);
                this.NombreProductoAfectado = new String();
                this.DatosProductoAfectado = new String();
            }
        }
    }
    
    /**
     * Remueve el producto involucrado de la base de datos.
     * Se quita el producto de la lista de productos del bean.
     * Se muestra un mensaje de error si no se elimino.
     * @param NombreProducto
     */
    public void removerProductoAfectado(String NombreProducto){
        if(fDatos.RemoverProductoInvolucrado(IdAccionSeleccionada, NombreProducto)==-1){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_FATAL, "No se pudo quitar producto", "No se pudo quitar producto" ));
            FacesContext.getCurrentInstance().renderResponse();
        }else{
            this.ListaProductosAfectados.remove(NombreProductoAfectado, DatosProductoAfectado);
            this.NombreProductoAfectado = new String();
            this.DatosProductoAfectado = new String();
        }
    }
    
    /**
     * Carga los datos del producto en los campos del formulario.
     * Lo remueve de la lista.
     * @param NombreProducto
     */
    public void editarProducto(String NombreProducto){
        String nombre =NombreProducto;
        String datos = this.ListaProductosAfectados.get(NombreProducto);
        removerProductoAfectado(NombreProducto);
        this.NombreProductoAfectado = nombre;
        this.DatosProductoAfectado = datos;
    }
    
    /**
     * Carga el adjunto en la lista de adjuntos.
     * Deja vacios los campos para un nuevo adjunto.
     */
    public void agregarAdjunto(){
        String datosAdjunto[] = cArchivo.guardarArchivo("Correctiva_"+ String.valueOf(IdAccionSeleccionada), ArchivoAdjunto, TituloAdjunto, "Nombre Empresa");
        // datosAdjunto[0]: ubicacion | datosAdjunto[1]: extension
        if(!datosAdjunto[0].isEmpty()){
            EnumTipoAdjunto tipoAdjunto = EnumTipoAdjunto.IMAGEN;
            String extension = datosAdjunto[1];
            List<String> tipos = new ArrayList<>();
            tipos.add("jpeg");
            tipos.add("jpg");
            tipos.add("png");
            tipos.add("gif");
            if(!tipos.contains(extension.toLowerCase().trim())){
                tipoAdjunto = EnumTipoAdjunto.DOCUMENTO;
            }
            if((fDatos.AgregarArchivoAdjunto(IdAccionSeleccionada, TituloAdjunto, datosAdjunto[0], tipoAdjunto))!=-1){
                actualizarListaAdjuntos();
                this.TituloAdjunto = new String();
                this.ArchivoAdjunto =  null;
            }else{
                cArchivo.BorrarArchivo(datosAdjunto[0]);
            }
        }
    }
    
    /**
     * Quita el adjunto de la lista de adjuntos.
     * @param IdAdjunto
     * @throws IOException
     */
    public void quitarAdjunto(int IdAdjunto) throws IOException{
        if(cArchivo.BorrarArchivo(this.MapAdjuntos.get(IdAdjunto).getUbicacion())){
            if((fDatos.RemoverAdjunto(IdAccionSeleccionada, IdAdjunto))!=-1){
                this.MapAdjuntos.remove(IdAdjunto);
            }
        }
    }
    
    /**
     * Actualiza la accion correctiva con los datos nuevos.
     * Si no se actualizo se muestra mensaje de error.
     * Si se creo se redirige a la pagina de listado de acciones.
     * @throws java.io.IOException
     */
    public void editarAccion() throws IOException{
        // actualizar accion
        if(fDatos.EditarAccion(IdAccionSeleccionada, EnumAccion.CORRECTIVA, FechaDeteccion, Descripcion, AnalisisCausa, TipoDesvioSeleccionado,
                AreaSectorAccionSeleccionada, DeteccionSeleccionada, CodificacionSeleccionada) == -1){
            // Si no se actualizo muestra mensaje de error.
            FacesContext.getCurrentInstance().addMessage("form_editar_accion:guardar_accion", new FacesMessage(SEVERITY_ERROR, "No se pudo editar la Accion", "No se pudo editar la Accion" ));
            FacesContext.getCurrentInstance().renderResponse();
        }else{
            // Si la actualizacion se realizo correctamente redirige a lista de acciones.
            String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(url+"/Views/Acciones/Correctivas/ListarCorrectivas.xhtml");
        }
    }
    
    /**
     * Elimina la accion de la base de datos.
     * Se eliminan todos los datos relacionados (actividades, adjuntos, comprobaciones y productos)
     * @throws java.io.IOException
     */
    public void eliminarAccion() throws IOException{
        if(fAdmin.EliminarAccion(IdAccionSeleccionada)==-1){
            // Si no se elimino muestra mensaje de error.
            FacesContext.getCurrentInstance().addMessage("form_editar_accion:eliminar_accion", new FacesMessage(SEVERITY_ERROR, "No se pudo eliminar la Accion", "No se pudo eliminar la Accion" ));
            FacesContext.getCurrentInstance().renderResponse();
        }else{
            // Eliminar todos los archivos adjuntos del disco.
            for(Adjunto adjunto: MapAdjuntos.values()){
                cArchivo.BorrarArchivo(adjunto.getUbicacion());
            }
            // Si la eliminacion se realizo correctamente redirige a lista de acciones.
            String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(url+"/Views/Acciones/Correctivas/ListarCorrectivas.xhtml");
        }
    }
    
}
