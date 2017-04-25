/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.acciones.correctivas;

import com.dperez.maym.web.herramientas.CargarArchivo;
import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.accion.Comprobacion;
import com.dperez.maymweb.accion.acciones.Correctiva;
import com.dperez.maymweb.accion.acciones.EnumAccion;
import com.dperez.maymweb.accion.acciones.EnumTipoDesvio;
import com.dperez.maymweb.accion.actividad.Actividad;
import com.dperez.maymweb.accion.adjunto.Adjunto;
import com.dperez.maymweb.accion.adjunto.EnumTipoAdjunto;
import com.dperez.maymweb.area.Area;
import com.dperez.maymweb.codificacion.Codificacion;
import com.dperez.maymweb.deteccion.Deteccion;
import com.dperez.maymweb.deteccion.EnumTipoDeteccion;
import com.dperez.maymweb.empresa.Empresa;
import com.dperez.maymweb.facades.FacadeAdministrador;
import com.dperez.maymweb.facades.FacadeDatos;
import com.dperez.maymweb.facades.FacadeLectura;
import com.dperez.maymweb.producto.Producto;
import com.dperez.maymweb.usuario.Usuario;
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
import java.util.TreeMap;
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
    private Accion AccionSeleccionada;
    private Empresa EmpresaLogueada;
    
    private Empresa EmpresaAccion;
    
    private Date FechaDeteccion;
    private String strFechaDeteccion;
    private String Descripcion;
    private String AnalisisCausa;
    
    private String TituloAdjunto;
    private Map<Integer, Adjunto> MapAdjuntos;
    private Part ArchivoAdjunto;
    
    private EnumTipoDeteccion[] TiposDeteccion;
    private EnumTipoDeteccion TipoDeDeteccionSeleccionada;
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
    
    private Map<Integer, Actividad> MedidasCorrectivas;
    private Map<Integer, Actividad> MedidasPreventivas;
    
    //comprobaciones
    private List<Usuario> ListaUsuarios;
    private int ResponsableImplementacion;
    private int ResponsableEficacia;
    private Date FechaEstimadaImplementacion;
    private String strFechaEstimadaImplementacion;
    private Date FechaEstimadaVerificacion;
    private String strFechaEstimadaVerificacion;
    
    private Comprobacion ComprobacionImplementacion;
    private Comprobacion ComprobacionEficacia;
    
    //  Getters
    public Accion getAccionSeleccionada(){return AccionSeleccionada;}
    public int getIdAccionSeleccionada() {return IdAccionSeleccionada;    }
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
    
    public Empresa getEmpresaAccion(){return this.EmpresaAccion;}
    
    public EnumTipoDeteccion getTipoDeDeteccionSeleccionada(){return this.TipoDeDeteccionSeleccionada;}
    public EnumTipoDeteccion[] getTiposDeteccion(){return this.TiposDeteccion;}
    public Map<Integer, String> getListaDetecciones(){return this.ListaDetecciones;}
    public String getNombreNuevaDeteccion(){return this.NombreNuevaDeteccion;}
    public Integer getDeteccionSeleccionada(){return this.DeteccionSeleccionada;}
    
    public EnumTipoDesvio[] getTiposDesvios(){return this.TiposDesvios;}
    public EnumTipoDesvio getTipoDesvioSeleccionado(){return this.TipoDesvioSeleccionado;}
    
    public Map<Integer, Area> getListaAreasSectores(){return this.ListaAreasSectores;}
    public Integer getAreaSectorAccionSeleccionada() {return AreaSectorAccionSeleccionada;}
    
    public boolean isHayProductoAfectado() {return hayProductoAfectado;}
    public Map<String, String> getListaProductosAfectados(){return this.ListaProductosAfectados;}
    public String getNombreProductoAfectado(){return this.NombreProductoAfectado;}
    public String getDatosProductoAfectado(){return this.DatosProductoAfectado;}
    
    public Map<Integer, Actividad> getMedidasCorrectivas() {return MedidasCorrectivas;}
    public Map<Integer, Actividad> getMedidasPreventivas() {return MedidasPreventivas;}
    
    //comprobaciones
    
    public List<Usuario> getListaUsuarios() {return ListaUsuarios;}
    public int getResponsableImplementacion() {return ResponsableImplementacion;}
    public int getResponsableEficacia() {return ResponsableEficacia;}
    public Date getFechaEstimadaImplementacion() {return FechaEstimadaImplementacion;}
    public String getStrFechaEstimadaImplementacion() {
        SimpleDateFormat fDate = new SimpleDateFormat("dd/MM/yyyy");
        if (FechaEstimadaImplementacion == null) {
            return this.strFechaEstimadaImplementacion;
        }else{
            return fDate.format(FechaEstimadaImplementacion);
        }
    }
    public Date getFechaEstimadaVerificacion() {return FechaEstimadaVerificacion;}
    public String getStrFechaEstimadaVerificacion() {
        SimpleDateFormat fDate = new SimpleDateFormat("dd/MM/yyyy");
        if (FechaEstimadaVerificacion == null) {
            return this.strFechaEstimadaVerificacion;
        }else{
            return fDate.format(FechaEstimadaVerificacion);
        }
    }
    public Comprobacion getComprobacionImplementacion() {return ComprobacionImplementacion;}
    public Comprobacion getComprobacionEficacia() {return ComprobacionEficacia;}
    
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
    
    public void setMedidasCorrectivas(Map<Integer, Actividad> MedidasCorrectivas) {this.MedidasCorrectivas = MedidasCorrectivas;}
    public void setMedidasPreventivas(Map<Integer, Actividad> MedidasPreventivas) {this.MedidasPreventivas = MedidasPreventivas;}
    
    // comprobaciones
    public void setListaUsuarios(List<Usuario> ListaUsuarios) {this.ListaUsuarios = ListaUsuarios;}
    public void setResponsableEficacia(int ResponsableEficacia) {this.ResponsableEficacia = ResponsableEficacia;}
    public void setResponsableImplementacion(int ResponsableImplementacion) {this.ResponsableImplementacion = ResponsableImplementacion;}
    public void setFechaEstimadaImplementacion(Date FechaEstimadaImplementacion) {this.FechaEstimadaImplementacion = FechaEstimadaImplementacion;}
    public void setStrFechaEstimadaImplementacion(String strFechaEstimadaImplementacion) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try{
            cal.setTime(sdf.parse(strFechaEstimadaImplementacion));
        }catch(ParseException ex){}
        this.strFechaEstimadaImplementacion = strFechaEstimadaImplementacion;
        this.FechaEstimadaImplementacion = cal.getTime();
    }
    public void setFechaEstimadaVerificacion(Date FechaEstimadaVerificacion) {this.FechaEstimadaVerificacion = FechaEstimadaVerificacion;}
    public void setStrFechaEstimadaVerificacion(String strFechaEstimadaVerificacion) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try{
            cal.setTime(sdf.parse(strFechaEstimadaVerificacion));
        }catch(ParseException ex){}
        this.strFechaEstimadaVerificacion = strFechaEstimadaVerificacion;
        this.FechaEstimadaVerificacion = cal.getTime();
    }
    public void setComprobacionImplementacion(Comprobacion ComprobacionImplementacion) {this.ComprobacionImplementacion = ComprobacionImplementacion;}
    public void setComprobacionEficacia(Comprobacion ComprobacionEficacia) {this.ComprobacionEficacia = ComprobacionEficacia;}
    
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
        // recuperar el id para llenar datos de la accion correctiva y el resto de las propiedades.
        IdAccionSeleccionada = Integer.parseInt(request.getParameter("id"));
        if(IdAccionSeleccionada != 0){            
            AccionSeleccionada = (Correctiva) fLectura.GetAccion(IdAccionSeleccionada);
            FechaDeteccion = AccionSeleccionada.getFechaDeteccion();
            Descripcion = AccionSeleccionada.getDescripcion();
            AnalisisCausa = AccionSeleccionada.getAnalisisCausa();
            
            EmpresaAccion = AccionSeleccionada.getEmpresaAccion();
            
            //  Codificaciones
            ListaCodificaciones = new HashMap<>();
            List<Codificacion> tmpCodificaciones = fLectura.ListarCodificaciones(EmpresaLogueada.getId());
            for(Codificacion codificacion:tmpCodificaciones){
                ListaCodificaciones.put(codificacion.getId(), codificacion.getNombre());
            }
            ListaCodificaciones = new TreeMap<>(ListaCodificaciones);
            CodificacionSeleccionada = AccionSeleccionada.getCodificacionAccion().getId();
            
            //  Detecciones
            TiposDeteccion = EnumTipoDeteccion.values();
            TipoDeDeteccionSeleccionada = AccionSeleccionada.getGeneradaPor().getTipo();
            
            this.ListaDetecciones = new HashMap<>();
            List<Deteccion> tmpDetecciones = fLectura.ListarDetecciones();
            for(Deteccion deteccion:tmpDetecciones){
                if (deteccion.getTipo().equals(TipoDeDeteccionSeleccionada)){
                    ListaDetecciones.put(deteccion.getId(), deteccion.getNombre());
                }
            }
            ListaDetecciones = new TreeMap<>(ListaDetecciones);
            DeteccionSeleccionada = AccionSeleccionada.getGeneradaPor().getId();
            
            //  Tipo de desvios
            TiposDesvios = EnumTipoDesvio.values();
            TipoDesvioSeleccionado = ((Correctiva)AccionSeleccionada).getTipo();
            
            // Actividades: Medidas Correctivas y Preventivas
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
            
            // Areas Sectores
            ListaAreasSectores = new HashMap<>();
            List<Area> tmpAreas = fLectura.ListarAreasSectores(EmpresaLogueada.getId());
            for(Area area:tmpAreas){
                this.ListaAreasSectores.put(area.getId(), area);
            }
            ListaAreasSectores = new TreeMap<>(ListaAreasSectores);
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
            MapAdjuntos = new HashMap<>();
            
            // Comprobaciones            
            // llenar la lista de usuarios de la empresa que no se hayan dado de baja.
            ListaUsuarios = fLectura.GetUsuariosEmpresa(true, EmpresaLogueada.getId());
            
            ComprobacionImplementacion = AccionSeleccionada.getComprobacionImplementacion();
            ComprobacionEficacia = AccionSeleccionada.getComprobacionEficacia();
            
            // para editar
            if(ComprobacionImplementacion != null && ComprobacionImplementacion.getFechaEstimada() != null){
                this.setFechaEstimadaImplementacion(ComprobacionImplementacion.getFechaEstimada());
            }
            if(ComprobacionImplementacion != null &&  ComprobacionImplementacion.getResponsable() != null){
                this.ResponsableImplementacion = ComprobacionImplementacion.getResponsable().getId();
            }
            if(ComprobacionEficacia != null && ComprobacionEficacia.getFechaEstimada() != null){
                this.setFechaEstimadaVerificacion(ComprobacionEficacia.getFechaEstimada());
            }
            if(ComprobacionEficacia != null && ComprobacionEficacia.getResponsable() != null){
                this.ResponsableEficacia = ComprobacionEficacia.getResponsable().getId();
            }
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
        // Crear Nueva Deteccion y actualizar lista
        Deteccion det = fAdmin.NuevaDeteccion(NombreNuevaDeteccion, TipoDeDeteccionSeleccionada);
        if(det != null){
            actualizarDeteccion();            
            this.DeteccionSeleccionada = det.getId();
            this.NombreNuevaDeteccion = new String();
            this.TipoDeDeteccionSeleccionada = det.getTipo();
            FacesContext.getCurrentInstance().addMessage("form_editar_accion:btn_nueva_deteccion", new FacesMessage(SEVERITY_INFO, det.getNombre() + " fue creada.", det.getNombre() + " fue creada."));
            FacesContext.getCurrentInstance().renderResponse();
        }else{
            FacesContext.getCurrentInstance().addMessage("form_editar_accion:btn_nueva_deteccion", new FacesMessage(SEVERITY_FATAL, "No se pudo crear nueva deteccion", "No se pudo crear nueva deteccion" ));
            FacesContext.getCurrentInstance().renderResponse();
        }
    }
    
    /**
     * Crea nueva codificacion.
     * SSino se crea se muestra un mensaje
     */
    public void nuevaCodificacion(){
        // Crear Nueva Codificacion y actualizar lista
        Codificacion cod = fAdmin.NuevaCodificacion(NombreNuevaCodificacion, DescripcionNuevaCodificacion);
        if(cod != null){
            fAdmin.ModificarEmpresaCodificacion(cod.getId(), true, EmpresaLogueada.getId());
            actualizarCodificacion();
            this.CodificacionSeleccionada = cod.getId();
            this.NombreNuevaCodificacion = new String();
            this.DescripcionNuevaCodificacion = new String();
            FacesContext.getCurrentInstance().addMessage("form_editar_accion:btn_nueva_codificacion", new FacesMessage(SEVERITY_INFO, cod.getNombre() + " fue creada.", cod.getNombre() + " fue creada."));
            FacesContext.getCurrentInstance().renderResponse();
        }else{
            FacesContext.getCurrentInstance().addMessage("form_editar_accion:btn_nueva_codificacion", new FacesMessage(SEVERITY_FATAL, "No se pudo crear nueva codificacion", "No se pudo crear nueva codificacion" ));
            FacesContext.getCurrentInstance().renderResponse();
        }
    }
    
    /**
     * Actualiza la lista de codificaciones.
     */
    public void actualizarCodificacion(){
        List<Codificacion> tmpCodificacion = fLectura.ListarCodificaciones(EmpresaLogueada.getId());
        this.ListaCodificaciones.clear();
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
     * Si se muestra mensaje de confirmacion.
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
            if(!this.MedidasCorrectivas.isEmpty() || !this.MedidasPreventivas.isEmpty()){
                if(AccionSeleccionada.getComprobacionImplementacion() != null){
                    if(AccionSeleccionada.getComprobacionImplementacion().getFechaEstimada() != this.FechaEstimadaImplementacion
                            || AccionSeleccionada.getComprobacionImplementacion().getResponsable().getId() != this.ResponsableImplementacion){
                        fDatos.SetEstimadoComprobacionImplementacion(this.FechaEstimadaImplementacion, this.ResponsableImplementacion, IdAccionSeleccionada);
                    }
                }else{
                    fDatos.SetEstimadoComprobacionImplementacion(this.FechaEstimadaImplementacion, this.ResponsableImplementacion, IdAccionSeleccionada);
                }
                if(AccionSeleccionada.getComprobacionEficacia()!= null){
                    if(AccionSeleccionada.getComprobacionEficacia().getFechaEstimada() != this.FechaEstimadaVerificacion
                            || AccionSeleccionada.getComprobacionEficacia().getResponsable().getId() != this.ResponsableEficacia){
                        fDatos.SetEstimadoComprobacionEficacia(this.FechaEstimadaVerificacion, this.ResponsableEficacia, IdAccionSeleccionada);
                    }
                }else{
                    fDatos.SetEstimadoComprobacionEficacia(this.FechaEstimadaVerificacion, this.ResponsableEficacia, IdAccionSeleccionada);
                }
            }
            // Si la actualizacion se realizo correctamente redirige a lista de acciones.
            FacesContext.getCurrentInstance().addMessage("form_editar_accion:guardar_accion", new FacesMessage(SEVERITY_INFO, "Los datos se guardaron.", "Los datos se guardaron." ));
            FacesContext.getCurrentInstance().renderResponse();
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
            if(!MapAdjuntos.isEmpty()){
                for(Adjunto adjunto: MapAdjuntos.values()){
                    cArchivo.BorrarArchivo(adjunto.getUbicacion());
                }
            }
            // Si la eliminacion se realizo correctamente redirige a lista de acciones.
            String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(url+"/Views/Acciones/Correctivas/ListarCorrectivas.xhtml");
        }
    }
    
}
