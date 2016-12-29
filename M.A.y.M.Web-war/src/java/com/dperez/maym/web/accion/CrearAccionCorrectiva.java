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
import com.dperez.maymweb.deteccion.TipoDeteccion;
import com.dperez.maymweb.empresa.Empresa;
import com.dperez.maymweb.facades.FacadeAdministrador;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;



@Named
@ViewScoped
public class CrearAccionCorrectiva implements Serializable {
    @Inject
    private FacadeAdministrador fAdmin;
    
    private Date FechaDeteccion;
    private String Descripcion;
    private String AnalisisCausa;
    
    private List<Adjunto> Adjuntos;
    
    private List<TipoDeteccion> TiposDeteccion;
    
    private Deteccion GeneradaPor;
    
    private Area AreaSectorAccion;
    
    private Codificacion CodificacionAccion;
    
    private Empresa EmpresaAccion;
    
    
    //  Getters
    public Date getFechaDeteccion() {return FechaDeteccion;}
    public String getDescripcion() {return Descripcion;}
    public String getAnalisisCausa() {return AnalisisCausa;}
    public List<Adjunto> getAdjuntos() {return Adjuntos;}
    public List<TipoDeteccion> getTiposDeteccion() {return TiposDeteccion;}
    public Deteccion getGeneradaPor() {return GeneradaPor;}
    public Area getAreaSectorAccion() {return AreaSectorAccion;}
    public Codificacion getCodificacionAccion() {return CodificacionAccion;}
    public Empresa getEmpresaAccion() {return EmpresaAccion;}
    
    //  Setters
    
    public void setFechaDeteccion(Date FechaDeteccion) {this.FechaDeteccion = FechaDeteccion;}    
    public void setDescripcion(String Descripcion) {this.Descripcion = Descripcion;}    
    public void setAnalisisCausa(String AnalisisCausa) {this.AnalisisCausa = AnalisisCausa;}    
    public void setAdjuntos(List<Adjunto> Adjuntos) {this.Adjuntos = Adjuntos;}    
    public void setTiposDeteccion(List<TipoDeteccion> TiposDeteccion) {this.TiposDeteccion = TiposDeteccion;}    
    public void setGeneradaPor(Deteccion GeneradaPor) {this.GeneradaPor = GeneradaPor;}    
    public void setAreaSectorAccion(Area AreaSectorAccion) {this.AreaSectorAccion = AreaSectorAccion;}    
    public void setCodificacionAccion(Codificacion CodificacionAccion) {this.CodificacionAccion = CodificacionAccion;}    
    public void setEmpresaAccion(Empresa EmpresaAccion) {this.EmpresaAccion = EmpresaAccion;}
    
    //  Metodos
    
    
    @PostConstruct
    public void init(){
        this.Adjuntos = new ArrayList<>();
        this.TiposDeteccion = new ArrayList<>();
    }
    
    
    
}
