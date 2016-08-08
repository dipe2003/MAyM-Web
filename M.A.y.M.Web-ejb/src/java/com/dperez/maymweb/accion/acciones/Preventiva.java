/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.accion.acciones;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.accion.medida.medidas.ActividadPreventiva;
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
public class Preventiva extends Accion implements Serializable{
    @OneToMany(mappedBy = "AccionPreventiva")
    private List<ActividadPreventiva> Actividades;
    
    // Constructores
    public Preventiva(){this.Actividades = new ArrayList<>();}
    public Preventiva(String Descripcion, Date FechaDeteccion){
        super(FechaDeteccion, Descripcion);
        this.Actividades = new ArrayList<>();
    }
    
    // Getters
    public List<ActividadPreventiva> getActividades() {return this.Actividades;}
    
    // Setters
    public void setActividades(List<ActividadPreventiva> Actividades) {
        this.Actividades = Actividades;
        for(ActividadPreventiva actividad: this.Actividades){
            actividad.setAccionPreventiva(this);
        }
    }
    
    // Listas
    public void addActividad(ActividadPreventiva Actividad){
        this.Actividades.add(Actividad);
        if(Actividad.getAccionPreventiva() == null || !Actividad.getAccionPreventiva().equals(this)) 
            Actividad.setAccionPreventiva(this);
    }
    
    public void removeActividad(ActividadPreventiva Actividad){
        this.Actividades.remove(Actividad);
        if(Actividad.getAccionPreventiva()!=null && Actividad.getAccionPreventiva().equals(this))
            Actividad.setAccionPreventiva(null);
    }
    
    public void removeActividad(int IdActividad){
        Iterator<ActividadPreventiva> it = this.Actividades.iterator() ;
        while(it.hasNext()){
            ActividadPreventiva ac = it.next();
            if(ac.getId()==IdActividad){
                it.remove();
                if(ac.getAccionPreventiva()!=null && ac.getAccionPreventiva().equals(this))
                    ac.setAccionPreventiva(null);
            }
        }
    }
}
