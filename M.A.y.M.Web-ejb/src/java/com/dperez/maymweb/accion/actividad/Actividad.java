/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.accion.actividad;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.usuario.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author dperez
 */
@Entity
@Table(name="Actividades")
public class Actividad implements Serializable {
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private int IdActividad;
    @Temporal(TemporalType.DATE)
    private Date FechaEstimadaImplementacion;
    @Temporal(TemporalType.DATE)
    private Date FechaImplementacion;
    private String Descripcion  = new String();
    
    @OneToOne
    private Accion AccionActividad;
    
    @ManyToOne
    private Usuario ResponsableImplementacion;
    
    // Constructores
    public Actividad(){}
    public Actividad(Date FechaEstimadaDeImplementacion, String Descripcion){
        this.FechaEstimadaImplementacion = FechaEstimadaDeImplementacion;
        this.Descripcion = Descripcion;
    }
    
    // Getters
    public int getIdActividad() {return this.IdActividad;}
    public Date getFechaEstimadaImplementacion() {return this.FechaEstimadaImplementacion;}
    public Date getFechaImplementacion() {return this.FechaImplementacion;}
    public String getDescripcion() {return this.Descripcion;}
    
    public Accion getAccionActividad(){return this.AccionActividad;}
    
    public Usuario getResponsableImplementacion() {return this.ResponsableImplementacion;}
    
    // Setters
    public void setIdActividad(int IdActividad) {this.IdActividad = IdActividad;}
    public void setFechaEstimadaImplementacion(Date FechaEstimadaImplementacion) {this.FechaEstimadaImplementacion = FechaEstimadaImplementacion;}
    public void setFechaImplementacion(Date FechaImplementacion) {this.FechaImplementacion = FechaImplementacion;}
    public void setDescripcion(String Descripcion) {this.Descripcion = Descripcion;}
    
    public void setAccionActividad(Accion AccionActividad){
        this.AccionActividad = AccionActividad;
    }
    
    public void setResponsableImplementacion(Usuario ResponsableImplementacion) {
        if(ResponsableImplementacion == null && this.ResponsableImplementacion != null){
            this.ResponsableImplementacion.getMedidasResponsableImplementacion().remove(this);
            this.ResponsableImplementacion = null;
        }else{
            if(ResponsableImplementacion != null){
                this.ResponsableImplementacion = ResponsableImplementacion;
                if(!ResponsableImplementacion.getMedidasResponsableImplementacion().contains(this))
                    ResponsableImplementacion.addMedidaResponsableImplementacion(this);
            }
        }
    }
}
