/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.accion.acciones;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.accion.EnumComprobacion;
import com.dperez.maymweb.accion.actividad.Actividad;
import com.dperez.maymweb.estado.EnumEstado;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author dperez
 */
@Entity
public class Preventiva extends Accion implements Serializable{
    @OneToMany(cascade = CascadeType.REMOVE)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name="preventivas_actividades",joinColumns={ @JoinColumn(name="Id", referencedColumnName="Id") },
            inverseJoinColumns={ @JoinColumn(name="IdActividad", referencedColumnName="IdActividad", unique=true) }  )
    private List<Actividad> Actividades;
    
    // Constructores
    public Preventiva(){this.Actividades = new ArrayList<>();}
    public Preventiva(Date FechaDeteccion, String Descripcion){
        super(FechaDeteccion, Descripcion);
        this.Actividades = new ArrayList<>();
    }
    
    // Getters
    public List<Actividad> getActividades() {return this.Actividades;}
    
    // Setters
    public void setActividades(List<Actividad> Actividades) {
        this.Actividades = Actividades;
        for(Actividad actividad: this.Actividades){
            actividad.setAccionActividad(this);
        }
    }
    
    // Listas
    public void addActividadPreventiva(Actividad ActividadPreventiva){
        this.Actividades.add(ActividadPreventiva);
        if(ActividadPreventiva.getAccionActividad()== null || !ActividadPreventiva.getAccionActividad().equals(this))
            ActividadPreventiva.setAccionActividad(this);
    }
    
    public void removeActividad(Actividad Actividad){
        this.Actividades.remove(Actividad);
        if(Actividad.getAccionActividad()!=null && Actividad.getAccionActividad().equals(this))
            Actividad.setAccionActividad(null);
    }
    
    
    @Override
    public void CambiarEstado() {
        if(this.getEstadoAccion()!= EnumEstado.DESESTIMADA && this.getEstadoAccion()!= EnumEstado.CERRADA){
            // si la lista de actividades esta vacia el estado es pendiente.
            if(this.Actividades.isEmpty()){
                this.setEstadoAccion(EnumEstado.PENDIENTE);
            }else{
                boolean actividadesImp = true;
                int numActividadesImp = 0;
                // chequear implementacion de todas las actividades
                for(Actividad actividad: this.Actividades){
                    if(actividad.getFechaImplementacion()==null) {
                        actividadesImp = false;
                    }else{
                        numActividadesImp ++;
                    }
                }
                if(actividadesImp == true && this.getComprobacionEficacia().getResultado()!= EnumComprobacion.NO_COMPROBADA){
                    this.setEstadoAccion(EnumEstado.CERRADA);
                }else{
                    if(actividadesImp == true && this.getComprobacionEficacia().getResultado()== EnumComprobacion.NO_COMPROBADA){
                        this.setEstadoAccion(EnumEstado.PROCESO_VER);
                    }else{
                        if(actividadesImp != true && numActividadesImp >= 0){
                            this.setEstadoAccion(EnumEstado.PROCESO_IMP);
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public boolean EstaImplementada() {
        boolean implementada = true;
        for(Actividad actividad: this.Actividades){
            if(actividad.getFechaImplementacion() == null){
                implementada = false;
                break;
            }
        }
        return implementada;
    }
    
    @Override
    public int compareTo(Accion OtraAccion) {        
        return OtraAccion.getFechaDeteccion().compareTo(this.FechaDeteccion);
    }
}
