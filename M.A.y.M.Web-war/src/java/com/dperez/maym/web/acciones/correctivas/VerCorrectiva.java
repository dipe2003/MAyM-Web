/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.acciones.correctivas;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.accion.Comprobacion;
import com.dperez.maymweb.accion.acciones.Correctiva;
import com.dperez.maymweb.accion.acciones.EnumTipoDesvio;
import com.dperez.maymweb.accion.actividad.Actividad;
import com.dperez.maymweb.area.Area;
import com.dperez.maymweb.deteccion.Deteccion;
import com.dperez.maymweb.empresa.Empresa;
import com.dperez.maymweb.estado.EnumEstado;
import com.dperez.maymweb.facades.FacadeDatos;
import com.dperez.maymweb.facades.FacadeLectura;
import com.dperez.maymweb.facades.FacadeVerificador;
import com.dperez.maymweb.producto.Producto;
import com.dperez.maymweb.usuario.Usuario;
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
public class VerCorrectiva implements Serializable {
    @Inject
    private FacadeLectura fLectura;
    @Inject
    private FacadeDatos fDatos;
    @Inject
    private FacadeVerificador fVerif;
    
    private Date FechaDeteccion;
    private String strFechaDeteccion;
    
    private Deteccion GeneradaPor;
    private Area AreaSector;
    private String Descripcion;
    private String AnalisisCausa;
    
    private List<Producto> ListaProductos;
    
    private String TipoDesvio;
    
    private Map<Integer, Actividad> MedidasCorrectivas;
    private Map<Integer, Actividad> MedidasPreventivas;
    
    private Date FechaImplementacion;
    
    private String ObservacionesComprobacion;
    private Date FechaComprobacion;
    
    private Comprobacion ComprobacionImplementacion;
    private Comprobacion ComprobacionEficacia;

    private Accion AccionSeleccionada;
    
    private Empresa EmpresaAccion;
    
    private EnumEstado Estado;
    
    //  Getters
    public Date getFechaDeteccion() {return FechaDeteccion;}
    public String getStrFechaDeteccion(){
        SimpleDateFormat fDate = new SimpleDateFormat("dd/MM/yyyy");
        if (FechaDeteccion == null) {
            return this.strFechaDeteccion;
        }else{
            return fDate.format(FechaDeteccion);
        }
    }
    public Deteccion getGeneradaPor() {return GeneradaPor;}
    public Area getAreaSector() {return AreaSector;}
    public String getDescripcion() {return Descripcion;}
    public String getAnalisisCausa() {return AnalisisCausa;}
    
    public List<Producto> getListaProductos() {return ListaProductos;}
    
    public String getTipoDesvio() {return TipoDesvio;}
    
    public Map<Integer, Actividad> getMedidasCorrectivas() {return MedidasCorrectivas;}
    public Map<Integer, Actividad> getMedidasPreventivas() {return MedidasPreventivas;}
    public Date getFechaImplementacion(){return this.FechaImplementacion;}
    public String getObservacionesComprobacion() {return ObservacionesComprobacion;}
    public Date getFechaComprobacion() {return FechaComprobacion;}
    public Accion getAccionSeleccionada() {return AccionSeleccionada;}
    public Empresa getEmpresaAccion(){return this.EmpresaAccion;}
    public EnumEstado getEstado() {return Estado;}
    public Comprobacion getComprobacionImplementacion() {return ComprobacionImplementacion;}
    public Comprobacion getComprobacionEficacia() {return ComprobacionEficacia;}
    
    //  Setters
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
    public void setGeneradaPor(Deteccion GeneradaPor) {this.GeneradaPor = GeneradaPor;}
    public void setAreaSector(Area AreaSector) {this.AreaSector = AreaSector;}
    public void setDescripcion(String Descripcion) {this.Descripcion = Descripcion;}
    public void setAnalisisCausa(String AnalisisCausa) {this.AnalisisCausa = AnalisisCausa;}
    
    public void setListaProductos(List<Producto> ListaProductos) {this.ListaProductos = ListaProductos;}
    
    public void setTipoDesvio(String TipoDesvio) {this.TipoDesvio = TipoDesvio;}

    public void setMedidasCorrectivas(Map<Integer, Actividad> MedidasCorrectivas) {this.MedidasCorrectivas = MedidasCorrectivas;}
    public void setMedidasPreventivas(Map<Integer, Actividad> MedidasPreventivas) {this.MedidasPreventivas = MedidasPreventivas;}
    public void setFechaImplementacion(Date FechaImplementacion){this.FechaImplementacion = FechaImplementacion;}
    public void setObservacionesComprobacion(String ObservacionesComprobacion) {this.ObservacionesComprobacion = ObservacionesComprobacion;}
    public void setFechaComprobacion(Date FechaComprobacion) {this.FechaComprobacion = FechaComprobacion;}
    public void setAccionSeleccionada(Accion AccionSeleccionada) {this.AccionSeleccionada = AccionSeleccionada;}
    public void setEmpresaAccion(Empresa EmpresaAccion){this.EmpresaAccion = EmpresaAccion;}
    public void setEstado(EnumEstado Estado) {this.Estado = Estado;}
    public void setComprobacionImplementacion(Comprobacion ComprobacionImplementacion) {this.ComprobacionImplementacion = ComprobacionImplementacion;}
    public void setComprobacionEficacia(Comprobacion ComprobacionEficacia) {this.ComprobacionEficacia = ComprobacionEficacia;}
    
    //  Metodos
    //  Inicializcion
    @PostConstruct
    public void init(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        // recuperar el id para llenar datos de la accion y el resto de las propiedades.
        int IdAccion = Integer.parseInt(request.getParameter("id"));
        if(IdAccion != 0){
            AccionSeleccionada = (Correctiva) fLectura.GetAccion(IdAccion);
            FechaDeteccion = AccionSeleccionada.getFechaDeteccion();
            GeneradaPor = AccionSeleccionada.getGeneradaPor();
            AreaSector = AccionSeleccionada.getAreaSectorAccion();
            Descripcion = AccionSeleccionada.getDescripcion();
            AnalisisCausa = AccionSeleccionada.getAnalisisCausa();
            Estado = AccionSeleccionada.getEstadoAccion();
            ComprobacionImplementacion = AccionSeleccionada.getComprobacionImplementacion();
            ComprobacionEficacia = AccionSeleccionada.getComprobacionEficacia();
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
            EmpresaAccion = (Empresa) request.getSession().getAttribute("Empresa");
            
            ListaProductos = new ArrayList<>();
            if(!((Correctiva)AccionSeleccionada).getProductosAfectados().isEmpty()){
                ListaProductos = ((Correctiva)AccionSeleccionada).getProductosAfectados();
            }
            
            EnumTipoDesvio tipo = ((Correctiva)AccionSeleccionada).getTipo();
            switch(tipo){
                case NC_MENOR_OBS:
                    TipoDesvio = "No Conformidad Menor / Observacion";
                    break;
                case NC_MAYOR:
                    TipoDesvio = "No Conformidad Mayor";
                    break;
                    
                case NC_CRITICA:
                    TipoDesvio = "No Conformidad Critica";
                    break;
                default: break;
            }
        }
    }
    
    
}
