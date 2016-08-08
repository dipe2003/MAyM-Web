/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dperez.maymweb.estado;

import com.dperez.maymweb.accion.Accion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author dperez
 */
@Entity
@Table(name="Estados")
public class Estado implements Serializable {
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private String Nombre = new String();
    private String Descripcion = new String();
    
    @OneToMany(mappedBy = "EstadoActualAccion")
    private List<Accion> AccionesConEstado;
    
    // Constructores
    public Estado(){this.AccionesConEstado = new ArrayList<>();}
    public Estado(String NombreEstado, String Descripcion){
        this.Nombre = NombreEstado;
        this.Descripcion = Descripcion;
        this.AccionesConEstado = new ArrayList<>();
    }
    
    // Getters
    public int getId() {return Id;}
    public String getNombre() {return Nombre;}
    public String getDescripcion() {return Descripcion;}

    public List<Accion> getAccionesConEstado() {return AccionesConEstado;}    
    
    // Setters
    public void setId(int Id) {this.Id = Id;}
    public void setNombre(String Nombre) {this.Nombre = Nombre;}
    public void setDescripcion(String Descripcion) {this.Descripcion = Descripcion;}

    public void setAccionesConEstado(List<Accion> AccionesConEstado) {
        this.AccionesConEstado = AccionesConEstado;
        for(Accion acc: this.AccionesConEstado){
            acc.setEstadoActualAccion(this);
        }
    }    
        
    // Listas
    public void addAccionAEstado(Accion AccionAEstado){
        this.AccionesConEstado.add(AccionAEstado);
        if(AccionAEstado.getEstadoActualAccion() == null || !AccionAEstado.getEstadoActualAccion().equals(this))
            AccionAEstado.setEstadoActualAccion(this);
    }
    
    public void removeAccionAEstado(Accion AccionAEstado){
        this.AccionesConEstado.remove(AccionAEstado);
        if(AccionAEstado.getEstadoActualAccion()!=null && AccionAEstado.getEstadoActualAccion().equals(this))
            AccionAEstado.setEstadoActualAccion(null);
    }
    
    public void removeAccionAEstado(int IdAccionAEstado){
        Iterator<Accion> it = this.AccionesConEstado.iterator() ;
        while(it.hasNext()){
            Accion ac = it.next();
            if(ac.getId()==IdAccionAEstado){                
                it.remove();
                if(ac.getEstadoActualAccion()!=null && ac.getEstadoActualAccion().equals(this))
                    ac.setEstadoActualAccion(null);
            }              
        }
    }
}
