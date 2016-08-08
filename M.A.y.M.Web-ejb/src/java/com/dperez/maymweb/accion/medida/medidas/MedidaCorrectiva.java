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
public class MedidaCorrectiva extends Medida{
    @ManyToOne
    private Correctiva AccionCorrectivaMedidaCorrectiva;
    
    // Constructores
    public MedidaCorrectiva(){}
    
    // Getters
    public Correctiva getAccionCorrectivaMedidaCorrectiva() {return AccionCorrectivaMedidaCorrectiva;}
    
    // Setters
    public void setAccionCorrectivaMedidaCorrectiva(Correctiva AccionCorrectivaMedidaCorrectiva) {
        if(AccionCorrectivaMedidaCorrectiva == null && this.AccionCorrectivaMedidaCorrectiva != null){
            this.AccionCorrectivaMedidaCorrectiva.getMedidasCorrectivas().remove(this);
            this.AccionCorrectivaMedidaCorrectiva = null;
        }else{
            if(AccionCorrectivaMedidaCorrectiva != null){
                this.AccionCorrectivaMedidaCorrectiva = AccionCorrectivaMedidaCorrectiva;
                if(!AccionCorrectivaMedidaCorrectiva.getMedidasCorrectivas().contains(this))
                    AccionCorrectivaMedidaCorrectiva.addMedidaCorrectiva(this);
            }
        }
    }
    
}
