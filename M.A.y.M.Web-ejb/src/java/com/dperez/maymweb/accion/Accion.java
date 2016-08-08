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
import com.dperez.maymweb.estado.Estado;
import com.dperez.maymweb.usuario.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    @Temporal(TemporalType.DATE)
    private Date FechaEstimadaVerificacion;
    @Temporal(TemporalType.DATE)
    private Date FechaVerificacion;
    private String Descripcion  = new String();
    private String AnalisisCausa = new String();
    private String ObservacionVerificacion = new String();
    
    @OneToMany(mappedBy = "AccionAdjunto")
    private List<Adjunto> Adjuntos;
    
    @ManyToOne
    private Deteccion GeneradaPor;
    
    @ManyToOne
    private Area AreaSectorAccion;
    
    @ManyToOne
    private Codificacion CodificacionAccion;
    
    @ManyToOne
    private Estado EstadoActualAccion;
    
    @ManyToOne
    private Usuario ResponsableVerificacion;
    
    // Constructores
    public Accion(){this.Adjuntos = new ArrayList<>();}
    
    public Accion(Date FechaDeteccion, String Descripcion) {
        this.FechaDeteccion = FechaDeteccion;
        this.Descripcion = Descripcion;
        this.Adjuntos = new ArrayList<>();
    }    
    
    // Getters
    public int getId() {return this.Id;}
    public Date getFechaDeteccion() {return this.FechaDeteccion;}
    public Date getFechaEstimadaVerificacion() {return this.FechaEstimadaVerificacion;}
    public Date getFechaVerificacion() {return this.FechaVerificacion;}
    public String getDescripcion() {return this.Descripcion;}
    public String getAnalisisCausa() {return this.AnalisisCausa;}
    public String getObservacionVerificacion() {return this.ObservacionVerificacion;}
    
    public List<Adjunto> getAdjuntos() {return this.Adjuntos;}
    
    public Deteccion getGeneradaPor() {return this.GeneradaPor;}
    
    public Area getAreaSectorAccion() {return this.AreaSectorAccion;}
    
    public Codificacion getCodificacionAccion() {return CodificacionAccion;}
    
    public Estado getEstadoActualAccion() {return EstadoActualAccion;}
    
    public Usuario getResponsableVerificacion() {return ResponsableVerificacion;}
    
    // Setters
    public void setId(int Id) {this.Id = Id;}
    public void setFechaDeteccion(Date FechaDeteccion) {this.FechaDeteccion = FechaDeteccion;}
    public void setFechaEstimadaVerificacion(Date FechaEstimadaVerificacion) {this.FechaEstimadaVerificacion = FechaEstimadaVerificacion;}
    public void setFechaVerificacion(Date FechaVerificacion) {this.FechaVerificacion = FechaVerificacion;}
    public void setDescripcion(String Descripcion) {this.Descripcion = Descripcion;}
    public void setAnalisisCausa(String AnalisisCausa) {this.AnalisisCausa = AnalisisCausa;}
    public void setObservacionVerificacion(String ObservacionVerificacion) {this.ObservacionVerificacion = ObservacionVerificacion;}
    
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
    
    public void setEstadoActualAccion(Estado EstadoActualAccion) {
        if(EstadoActualAccion == null && this.EstadoActualAccion != null){
            this.EstadoActualAccion.getAccionesConEstado().remove(this);
            this.EstadoActualAccion = null;
        }else{
            if(EstadoActualAccion != null){
                this.EstadoActualAccion = EstadoActualAccion;
                if( !EstadoActualAccion.getAccionesConEstado().contains(this))
                    EstadoActualAccion.addAccionAEstado(this);
            }
        }
    }
    
    public void setResponsableVerificacion(Usuario ResponsableVerificacion) {
        if(ResponsableVerificacion == null && this.ResponsableVerificacion !=null){
            this.ResponsableVerificacion.getAccionesVerificacion().remove(this);
            this.ResponsableVerificacion = null;
        }else{
            if(ResponsableVerificacion != null){
                this.ResponsableVerificacion = ResponsableVerificacion;
                if(!ResponsableVerificacion.getAccionesVerificacion().contains(this))
                    ResponsableVerificacion.addAccionVerificacion(this);
            }
        }
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
    
}
