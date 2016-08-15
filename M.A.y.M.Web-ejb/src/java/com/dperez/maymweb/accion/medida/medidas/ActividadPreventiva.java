/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.accion.medida.medidas;

import com.dperez.maymweb.accion.acciones.Preventiva;
import com.dperez.maymweb.accion.medida.Medida;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author Diego
 */
@Entity
public class ActividadPreventiva extends Medida {
    @ManyToOne
    private Preventiva AccionPreventiva;
    
    // Constructores
    public ActividadPreventiva(){}
    public ActividadPreventiva(Date FechaEstimadaImplementacion, String Descripcion){
        super(FechaEstimadaImplementacion, Descripcion);        
    }
    
    // Getters
    public Preventiva getAccionPreventiva() {return AccionPreventiva;}
    
    // Setters
    public void setAccionPreventiva(Preventiva AccionPreventiva) {
        if(AccionPreventiva == null && this.AccionPreventiva != null){
            this.AccionPreventiva.getActividades().remove(this);
            this.AccionPreventiva = null;
        }else{
            if(AccionPreventiva != null){
                this.AccionPreventiva = AccionPreventiva;
                if(!AccionPreventiva.getActividades().contains(this))
                    AccionPreventiva.addActividad(this);
            }
        }
    }
    
}
