/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.accion;

import com.dperez.maymweb.accion.adjunto.Adjunto;
import com.dperez.maymweb.area.Area;
import com.dperez.maymweb.codificacion.Codificacion;
import com.dperez.maymweb.deteccion.Deteccion;
import com.dperez.maymweb.deteccion.EnumTipoDeteccion;
import com.dperez.maymweb.empresa.Empresa;
import com.dperez.maymweb.facades.FacadeAdministrador;
import com.dperez.maymweb.facades.FacadeLectura;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;



@Named
@ViewScoped
public class CrearAccionCorrectiva implements Serializable {
    @Inject
    private FacadeAdministrador fAdmin;
    @Inject
    private FacadeLectura fLectura;
    
    private Date FechaDeteccion;
    private String Descripcion;
    private String AnalisisCausa;
    
    private List<Adjunto> Adjuntos;
        
    private EnumTipoDeteccion[] TipoDeteccion;
    private EnumTipoDeteccion TipoDeDeteccionSeleccionada;
    
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
    public List<Adjunto> getAdjuntos() {return Adjuntos;}    
    
    public Map<Integer, String> getListaCodificaciones(){return this.ListaCodificaciones;}
    public Integer getCodificacionSeleccionada() {return CodificacionSeleccionada;}
    
    public EnumTipoDeteccion getTipoDeteccion(){return this.TipoDeDeteccionSeleccionada;}
    public Map<Integer, String> getListaDetecciones(){return this.ListaDetecciones;}
    public Integer getDeteccionSeleccionada(){return this.DeteccionSeleccionada;}
    
    public Map<Integer, String> getListaAreasSectores(){return this.ListaAreasSectores;}
    public Integer getAreaSectorAccionSeleccionada() {return AreaSectorAccionSeleccionada;}
    
    //  Setters
    
    public void setFechaDeteccion(Date FechaDeteccion) {this.FechaDeteccion = FechaDeteccion;}    
    public void setDescripcion(String Descripcion) {this.Descripcion = Descripcion;}    
    public void setAnalisisCausa(String AnalisisCausa) {this.AnalisisCausa = AnalisisCausa;}    
    public void setAdjuntos(List<Adjunto> Adjuntos) {this.Adjuntos = Adjuntos;}
    
    public void setListaCodificaciones(Map<Integer, String> ListaCodificaciones){this.ListaCodificaciones = ListaCodificaciones;}
    public void setCodificacionSeleccionada(Integer CodificacionSeleccionada) {this.CodificacionSeleccionada = CodificacionSeleccionada;}    
    
    public void setTipoDeteccion(EnumTipoDeteccion TipoDeteccion){this.TipoDeDeteccionSeleccionada = TipoDeteccion;}
    public void setListaDetecciones(Map<Integer, String> ListaDetecciones){this.ListaDetecciones = ListaDetecciones;}
    public void setDeteccionSeleccionada(Integer DeteccionSeleccionada){this.DeteccionSeleccionada = DeteccionSeleccionada;}
    
    public void setListaAreaSectores(Map<Integer, String> ListaAreasSectores){this.ListaAreasSectores = ListaAreasSectores;}
    public void setAreaSectorAccionSeleccionada(Integer AreaSectorAccionSeleccionada) {this.AreaSectorAccionSeleccionada = AreaSectorAccionSeleccionada;}  
    
    //  Metodos   
    
    @PostConstruct
    public void init(){
        //  Codificaciones
        ListaCodificaciones = new HashMap<>();
        List<Codificacion> tmpCodificaciones = fLectura.ListarCodificaciones();
        for(Codificacion codificacion:tmpCodificaciones){
            ListaCodificaciones.put(codificacion.getId(), codificacion.getNombre());
        }
        
        //  Detecciones
        TipoDeteccion = EnumTipoDeteccion.values();
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
    
    
    
}
