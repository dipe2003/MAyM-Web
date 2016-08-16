/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.accion.acciones;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.accion.medida.medidas.ActividadMejora;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author dperez
 */
@Entity
public class Mejora extends Accion implements Serializable {
    @OneToMany(mappedBy = "AccionMejora")
    private List<ActividadMejora> Actividades;
    
    // Constructores
    public Mejora(Date FechaDeteccion, String Descripcion){
        super(FechaDeteccion, Descripcion);        
        this.Actividades = new ArrayList<>();
    }
    public Mejora(){this.Actividades = new ArrayList<>();}
    
    // Getters
    public List<ActividadMejora> getActividades() {return this.Actividades;}
    
    // Setters
    public void setActividades(List<ActividadMejora> Actividades) {
        this.Actividades = Actividades;
        for(ActividadMejora actividad: this.Actividades){
            actividad.setAccionMejora(this);
        }
    }
    
    // Listas
    public void addActividad(ActividadMejora Actividad){
        this.Actividades.add(Actividad);
        if(Actividad.getAccionMejora() == null || !Actividad.getAccionMejora().equals(this)) 
            Actividad.setAccionMejora(this);
    }
    
    public void removeActividad(ActividadMejora Actividad){
        this.Actividades.remove(Actividad);
        if(Actividad.getAccionMejora()!=null && Actividad.getAccionMejora().equals(this)) 
            Actividad.setAccionMejora(null);
    }
    
    public void removeActividad(int idActividad){
        Iterator<ActividadMejora> it = this.Actividades.iterator() ;
        while(it.hasNext()){
            ActividadMejora ac = it.next();
            if(ac.getId()==idActividad){
                it.remove();
                if(ac.getAccionMejora()!=null && ac.getAccionMejora().equals(this))
                    ac.setAccionMejora(null);
            }
        }
    }
}
