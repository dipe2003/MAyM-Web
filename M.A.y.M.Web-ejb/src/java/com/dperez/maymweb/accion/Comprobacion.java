/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.accion;

import com.dperez.maymweb.usuario.Usuario;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Diego
 */
@Entity
@Table(name="Comprobaciones")
public class Comprobacion implements Serializable{
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    @Temporal(TemporalType.DATE)
    private Date FechaEstimada;
    @Temporal(TemporalType.DATE)
    private Date FechaComprobacion;
    private EnumComprobacion Resultado;
    private String Observaciones = new String();
    
    @OneToOne
    private Accion AccionEficacia;
    
    @OneToOne
    private Accion AccionImplementacion;
    
    @ManyToOne
    private Usuario Responsable;
    
    //  Constructores
    public Comprobacion(){
        this.Resultado = EnumComprobacion.NO_COMPROBADA;
    }
    public Comprobacion(Date FechaEstimada, Usuario ResponsableComprobacion){
        this.Resultado = EnumComprobacion.NO_COMPROBADA;
        this.Responsable = ResponsableComprobacion;
        this.FechaEstimada = FechaEstimada;
    }
    
    //  Getters
    
    public int getId() {return Id;}
    public Date getFechaComprobacion() {return FechaComprobacion;}
    public EnumComprobacion getResultado() {return Resultado;}
    public String getObservaciones() {return Observaciones;}
    public Usuario getResponsable() {return Responsable;}
    public Date getFechaEstimada() {return FechaEstimada;}
    
    public Accion getAccionEficacia() {return AccionEficacia;}
    public Accion getAccionImplementacion() {return AccionImplementacion;}
    
    //  Setters
    
    public void setId(int Id) {this.Id = Id;}
    public void setFechaComprobacion(Date FechaComprobacion) {this.FechaComprobacion = FechaComprobacion;}
    public void setResultado(EnumComprobacion Resultado) {this.Resultado = Resultado;}
    public void setObservaciones(String Observaciones) {this.Observaciones = Observaciones;}
    public void setResponsable(Usuario Responsable) {this.Responsable = Responsable;}
    public void setFechaEstimada(Date FechaEstimada) {this.FechaEstimada = FechaEstimada;}
    
    public void setAccionEficacia(Accion AccionEficacia) {
        this.AccionEficacia = AccionEficacia;
        if(AccionEficacia != null){
            if(!this.AccionEficacia.equals(AccionEficacia))
                AccionEficacia.setComprobacionEficacia(this);
        }
        
    }
    
    public void setAccionImplementacion(Accion AccionImplementacion) {
        this.AccionImplementacion = AccionImplementacion;
        if(AccionImplementacion != null){
            if(!this.AccionImplementacion.equals(AccionImplementacion))
                AccionImplementacion.setComprobacionImplementacion(this);
        }        
    }    
}
