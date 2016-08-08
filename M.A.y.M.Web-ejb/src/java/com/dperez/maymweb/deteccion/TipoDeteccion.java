/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.deteccion;

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
@Table(name="TiposDetecciones")
public class TipoDeteccion implements Serializable {
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private String Nombre = new String();
    private String Descripcion = new String();
    
    @OneToMany(mappedBy = "Tipo")
    private List<Deteccion> Detecciones;
    
    // Constructores
    public TipoDeteccion(){this.Detecciones = new ArrayList<>();}
    public TipoDeteccion(String NombreDeteccion, String DescripcionDeteccion){
        this.Nombre = NombreDeteccion;
        this.Descripcion = DescripcionDeteccion;
        this.Detecciones = new ArrayList<>();
    }
    
    // Getters
    public int getId() {return this.Id;}
    public String getNombre() {return this.Nombre;}
    public String getDescripcion() {return this.Descripcion;}
    
    public List<Deteccion> getDetecciones() {return Detecciones;}
    
    // Setters
    public void setId(int Id) {this.Id = Id;}
    public void setNombre(String Nombre) {this.Nombre = Nombre;}
    public void setDescripcion(String Descripcion) {this.Descripcion = Descripcion;}
    
    public void setDetecciones(List<Deteccion> Detecciones) {
        this.Detecciones = Detecciones;
        for(Deteccion det: this.Detecciones){
            det.setTipo(this);
        }
    }
    
    // Listas
    public void addDeteccion(Deteccion DeteccionDeTipo){
        this.Detecciones.add(DeteccionDeTipo);
        if(DeteccionDeTipo.getTipo() == null || !DeteccionDeTipo.getTipo().equals(this))
            DeteccionDeTipo.setTipo(this);
    }
    
    public void removeDeteccion(Deteccion DeteccionDeTipo){
        this.Detecciones.remove(DeteccionDeTipo);
        if(DeteccionDeTipo.getTipo()!=null && DeteccionDeTipo.getTipo().equals(this))
            DeteccionDeTipo.setTipo(null);
    }
    
    public void removeDeteccion(int IdDeteccionDeTipo){
        Iterator<Deteccion> it = this.Detecciones.iterator() ;
        while(it.hasNext()){
            Deteccion d = it.next();
            if(d.getId()== IdDeteccionDeTipo){
                it.remove();
                if(d.getTipo()!=null && d.getTipo().equals(this))
                    d.setTipo(null);
            }
        }
    }    
}
