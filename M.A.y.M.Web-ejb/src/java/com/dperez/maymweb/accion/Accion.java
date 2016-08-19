/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.accion;

import com.dperez.maymweb.accion.adjunto.Adjunto;
import com.dperez.maymweb.area.Area;
import com.dperez.maymweb.codificacion.Codificacion;
import java.util.Date;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.dperez.maymweb.deteccion.Deteccion;
import com.dperez.maymweb.estado.EnumEstado;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author dperez
 */
@Entity
@Table(name = "Acciones")
public abstract class Accion implements Serializable{
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    @Temporal(TemporalType.DATE)
    private Date FechaDeteccion;
    private String Descripcion  = new String();
    private String AnalisisCausa = new String();
    private EnumEstado EstadoAccion;
    
    @OneToMany(mappedBy = "AccionAdjunto")
    private List<Adjunto> Adjuntos;
    
    @ManyToOne
    private Deteccion GeneradaPor;
    
    @ManyToOne
    private Area AreaSectorAccion;
    
    @ManyToOne
    private Codificacion CodificacionAccion;
    
    @OneToOne
    private Comprobacion ComprobacionEficacia;
    
    @OneToOne
    private Comprobacion ComprobacionImplantacion;
    
    // Constructores
    public Accion(){
        this.EstadoAccion = EnumEstado.PENDIENTE;
        this.Adjuntos = new ArrayList<>();
    }
    
    public Accion(Date FechaDeteccion, String Descripcion) {
        this.EstadoAccion = EnumEstado.PENDIENTE;
        this.FechaDeteccion = FechaDeteccion;
        this.Descripcion = Descripcion;
        this.Adjuntos = new ArrayList<>();
    }
    
    // Getters
    public int getId() {return this.Id;}
    public Date getFechaDeteccion() {return this.FechaDeteccion;}
    public String getDescripcion() {return this.Descripcion;}
    public String getAnalisisCausa() {return this.AnalisisCausa;}
    public EnumEstado getEstadoAccion() {return EstadoAccion;}
    
    public List<Adjunto> getAdjuntos() {return this.Adjuntos;}
    
    public Deteccion getGeneradaPor() {return this.GeneradaPor;}
    
    public Area getAreaSectorAccion() {return this.AreaSectorAccion;}
    
    public Codificacion getCodificacionAccion() {return CodificacionAccion;}
    
    public Comprobacion getComprobacionEficacia() {return ComprobacionEficacia;}
    public Comprobacion getComprobacionImplantacion() {return ComprobacionImplantacion;}
    
    // Setters
    public void setId(int Id) {this.Id = Id;}
    public void setFechaDeteccion(Date FechaDeteccion) {this.FechaDeteccion = FechaDeteccion;}
    public void setDescripcion(String Descripcion) {this.Descripcion = Descripcion;}
    public void setAnalisisCausa(String AnalisisCausa) {this.AnalisisCausa = AnalisisCausa;}
    public void setEstadoAccion(EnumEstado EstadoAccion) {this.EstadoAccion = EstadoAccion;}
    
    public void setAdjuntos(List<Adjunto> Adjuntos) {
        this.Adjuntos = Adjuntos;
        for(Adjunto adj: this.Adjuntos){
            adj.setAccionAdjunto(this);
        }
    }
    
    public void setGeneradaPor(Deteccion GeneradaPor) {
        if(GeneradaPor==null && this.GeneradaPor !=null){
            this.GeneradaPor.getAccionesDetectadas().remove(this);
            this.GeneradaPor = null;
        }else{
            if(GeneradaPor != null){
                this.GeneradaPor = GeneradaPor;
                if(!GeneradaPor.getAccionesDetectadas().contains(this))
                    GeneradaPor.addAccionDetectada(this);
            }
        }
    }
    
    public void setAreaSectorAccion(Area AreaSectorAccion) {
        if(AreaSectorAccion==null && this.AreaSectorAccion !=null){
            this.AreaSectorAccion.getAccionesEnAreaSector().remove(this);
            this.AreaSectorAccion = null;
        }else{
            if(AreaSectorAccion != null){
                this.AreaSectorAccion = AreaSectorAccion;
                if(!AreaSectorAccion.getAccionesEnAreaSector().contains(this))
                    AreaSectorAccion.addAccionEnAreaSector(this);
            }
        }
    }
    
    public void setCodificacionAccion(Codificacion CodificacionAccion) {
        if(CodificacionAccion == null && this.CodificacionAccion !=null){
            this.CodificacionAccion.getAccionesConCodificacion().remove(this);
            this.CodificacionAccion = null;
        }else{
            if(CodificacionAccion != null){
                this.CodificacionAccion = CodificacionAccion;
                if(!CodificacionAccion.getAccionesConCodificacion().contains(this))
                    CodificacionAccion.addAccionCodificacion(this);
            }
        }
    }
    
    public void setComprobacionEficacia(Comprobacion ComprobacionEficacia) {        
        if(ComprobacionEficacia != null){
            ComprobacionEficacia.setAccionEficacia(this);
        }
        this.ComprobacionEficacia = ComprobacionEficacia;
    }
    
    public void setComprobacionImplantacion(Comprobacion ComprobacionImplantacion) {        
        if(this.ComprobacionImplantacion != null && ComprobacionImplantacion != null){
            ComprobacionImplantacion.setAccionImplantacion(this);
        }
        this.ComprobacionImplantacion = ComprobacionImplantacion;
    }
    
    // Listas
    public void addAdjunto(Adjunto ArchivoAdjunto){
        this.Adjuntos.add(ArchivoAdjunto);
        if(ArchivoAdjunto.getAccionAdjunto() == null || !ArchivoAdjunto.getAccionAdjunto().equals(this))
            ArchivoAdjunto.setAccionAdjunto(this);
    }
    
    public void removeAdjunto(Adjunto ArchivoAdjunto){
        this.Adjuntos.remove(ArchivoAdjunto);
        if(ArchivoAdjunto.getAccionAdjunto()!=null && ArchivoAdjunto.getAccionAdjunto().equals(this))
            ArchivoAdjunto.setAccionAdjunto(null);
    }
    
    public void removeAdjunto(int IdArchivoAdjunto){
        Iterator<Adjunto> it = this.Adjuntos.iterator() ;
        while(it.hasNext()){
            Adjunto a = it.next();
            if(a.getId()==IdArchivoAdjunto){
                it.remove();
                if(a.getAccionAdjunto()!=null && a.getAccionAdjunto().equals(this))
                    a.setAccionAdjunto(null);
            }
        }
    }
    
    /***
     * Chequea las Medidas y cambia el estado de la accion de acuerdo a su implementacion.
     */
    public abstract void CambiarEstado();
    
}
