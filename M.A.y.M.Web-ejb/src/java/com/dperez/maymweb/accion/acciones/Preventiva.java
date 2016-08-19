/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.accion.acciones;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.accion.EnumComprobacion;
import com.dperez.maymweb.accion.medida.medidas.ActividadPreventiva;
import com.dperez.maymweb.estado.EnumEstado;
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
    public Preventiva(Date FechaDeteccion, String Descripcion){
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
    
    @Override
    public void CambiarEstado() {
        if(this.getEstadoAccion()!= EnumEstado.DESESTIMADA && this.getEstadoAccion()!= EnumEstado.CERRADA){
            boolean actividadesImp = true;
            int numActividadesImp = 0;
            // chequear implementacion de todas las actividades
            for(ActividadPreventiva actividad: this.Actividades){
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
