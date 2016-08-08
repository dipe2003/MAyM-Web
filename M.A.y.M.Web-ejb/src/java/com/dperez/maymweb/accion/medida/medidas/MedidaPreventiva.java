/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.accion.medida.medidas;

import com.dperez.maymweb.accion.acciones.Correctiva;
import com.dperez.maymweb.accion.medida.Medida;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author Diego
 */
@Entity
public class MedidaPreventiva extends Medida{
    @ManyToOne
    private Correctiva AccionCorrectivaMedidaPreventiva;
    
    // Constructores
    public MedidaPreventiva(){}
    
    // Getters
    public Correctiva getAccionCorrectivaMedidaPreventiva() {return AccionCorrectivaMedidaPreventiva;}
    
    // Setters
    public void setAccionCorrectivaMedidaPreventiva(Correctiva AccionCorrectivaMedidaPreventiva) {
        if(AccionCorrectivaMedidaPreventiva == null && this.AccionCorrectivaMedidaPreventiva != null){
            this.AccionCorrectivaMedidaPreventiva.getMedidasPreventivas().remove(this);
            this.AccionCorrectivaMedidaPreventiva = null;
        }else{
            if(AccionCorrectivaMedidaPreventiva != null){
                this.AccionCorrectivaMedidaPreventiva = AccionCorrectivaMedidaPreventiva;
                if(!AccionCorrectivaMedidaPreventiva.getMedidasPreventivas().contains(this))
                    AccionCorrectivaMedidaPreventiva.addMedidaPreventiva(this);
            }
        }
    }
}
