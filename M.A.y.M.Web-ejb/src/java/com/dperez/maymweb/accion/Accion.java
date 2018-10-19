/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.accion;

import com.dperez.maymweb.accion.actividad.Actividad;
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
import com.dperez.maymweb.empresa.Empresa;
import com.dperez.maymweb.estado.EnumEstado;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author dperez
 */
@Entity
@Table(name = "Acciones")
public abstract class Accion implements Serializable, Comparable<Accion>{
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    protected int Id;
    @Temporal(TemporalType.DATE)
    protected Date FechaDeteccion;
    protected String Descripcion  = new String();
    protected String AnalisisCausa = new String();
    protected EnumEstado EstadoAccion;
    
    @OneToMany(mappedBy = "AccionAdjunto",orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    protected List<Adjunto> Adjuntos;
    
    @ManyToOne
    protected Deteccion GeneradaPor;
    
    @ManyToOne
    protected Area AreaSectorAccion;
    
    @ManyToOne
    protected Codificacion CodificacionAccion;
    
    @OneToOne(cascade = CascadeType.REMOVE)
    protected Comprobacion ComprobacionEficacia;
    
    @OneToOne(cascade = CascadeType.REMOVE)
    protected Comprobacion ComprobacionImplementacion;
    
    @ManyToOne
    protected Empresa EmpresaAccion;
    
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
    public Comprobacion getComprobacionImplementacion() {return ComprobacionImplementacion;}
    
    public Empresa getEmpresaAccion(){return this.EmpresaAccion;}
    
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
    
    public void setComprobacionImplementacion(Comprobacion ComprobacionImplementacion) {        
        if(this.ComprobacionImplementacion != null && ComprobacionImplementacion != null){
            ComprobacionImplementacion.setAccionImplementacion(this);
        }
        this.ComprobacionImplementacion = ComprobacionImplementacion;
    }
    
    public void setEmpresaAccion(Empresa EmpresaAccion){this.EmpresaAccion = EmpresaAccion;}
    
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
    
    /**
     * Comprueba que todas las actividades de la lista esten implementadas.
     * @param actividades lista de actividades a comprobar.
     * @return
     */
    protected boolean EstanImplementadasActividades(List<Actividad> actividades){
        return actividades.stream()
                .allMatch(actividad->actividad.EstaImplementada());
    }
    
    /**
     * Comprueba si existe alguna actividad implementada en la lsita.
     * @param actividades lista de actividades a comprobar
     * @return 
     */
    protected boolean ExisteAlgunaActividadImplementada(List<Actividad> actividades){
        return actividades.stream()
                .anyMatch(actividad->actividad.EstaImplementada());
    }
    
    public boolean SeComproboImplementacion(){
        return ComprobacionImplementacion != null && ComprobacionImplementacion.getFechaComprobacion() != null;
    }
    
    public boolean SeComproboEficacia(){
        return ComprobacionEficacia != null && ComprobacionEficacia.getFechaComprobacion() != null;
    }
    
    @Override
    public int compareTo(Accion OtraAccion) {
        return OtraAccion.getFechaDeteccion().compareTo(this.FechaDeteccion);
    }
    /***
     * Chequea las Medidas y cambia el estado de la accion de acuerdo a su implementacion.
     */
    public abstract void CambiarEstado();
    
    /***
     * Comprueba que todas actividades esten implementadas.
     * @return 
     */
    public abstract boolean EstanImplementadaActividades();
    
    /**
     * Devuelve la actividad especificada.
     * @param IdActividad
     * @return Null si no se encuentra.
     */
    public abstract Actividad GetActividad(int IdActividad);
    
}
