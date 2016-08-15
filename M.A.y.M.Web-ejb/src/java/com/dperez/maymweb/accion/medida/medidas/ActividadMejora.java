/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.accion.medida.medidas;

import com.dperez.maymweb.accion.acciones.Mejora;
import com.dperez.maymweb.accion.medida.Medida;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author Diego
 */
@Entity
public class ActividadMejora extends Medida {
    @ManyToOne
    private Mejora AccionMejora;
    
    // Constructores
    public ActividadMejora(){}
    public ActividadMejora(Date FechaEstimadaDeImplementacion, String Descripcion){
        super(FechaEstimadaDeImplementacion,  Descripcion);
    }
    
    // Getters
    public Mejora getAccionMejora() {return AccionMejora;}
    
    // Setters
    public void setAccionMejora(Mejora AccionMejora) {
        if(AccionMejora == null && this.AccionMejora != null){
            this.AccionMejora.getActividades().remove(this);
            this.AccionMejora = null;
        }else{
            if(AccionMejora != null){
                this.AccionMejora = AccionMejora;
                if(!AccionMejora.getActividades().contains(this))
                    AccionMejora.addActividad(this);
            }
        }
    }
}
