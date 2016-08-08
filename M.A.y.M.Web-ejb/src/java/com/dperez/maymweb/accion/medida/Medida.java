/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.accion.medida;

import com.dperez.maymweb.usuario.Usuario;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author dperez
 */
@Entity
@Table(name="Medidas")
public abstract class Medida implements Serializable {
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    @Temporal(TemporalType.DATE)
    private Date FechaEstimadaImplementacion;
    @Temporal(TemporalType.DATE)
    private Date FechaImplementacion;
    private String Descripcion  = new String();
    
    @ManyToOne
    private Usuario ResponsableImplementacion;
    
    // Constructores
    public Medida(){}
    public Medida(Date FechaEstimadaDeImplementacion, Date FechaImplementacion, String Descripcion){
        this.FechaEstimadaImplementacion = FechaEstimadaDeImplementacion;
        this.FechaImplementacion = FechaImplementacion;
        this.Descripcion = Descripcion;
    }
    
    // Getters
    public int getId() {return this.Id;}
    public Date getFechaEstimadaImplementacion() {return this.FechaEstimadaImplementacion;}
    public Date getFechaImplementacion() {return this.FechaImplementacion;}
    public String getDescripcion() {return this.Descripcion;}
    
    public Usuario getResponsableImplementacion() {return this.ResponsableImplementacion;}
    
    // Setters
    public void setId(int Id) {this.Id = Id;}
    public void setFechaEstimadaImplementacion(Date FechaEstimadaImplementacion) {this.FechaEstimadaImplementacion = FechaEstimadaImplementacion;}
    public void setFechaImplementacion(Date FechaImplementacion) {this.FechaImplementacion = FechaImplementacion;}
    public void setDescripcion(String Descripcion) {this.Descripcion = Descripcion;}
    
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
