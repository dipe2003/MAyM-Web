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
import java.util.List;

import com.dperez.maymweb.producto.Producto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

/**
 *
 * @author dperez
 */
@Entity
public class Correctiva extends Accion implements Serializable {
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinTable(name="medidascorrectivas_actividades",joinColumns={ @JoinColumn(name="Id", referencedColumnName="Id") },
            inverseJoinColumns={ @JoinColumn(name="IdActividad", referencedColumnName="IdActividad", unique=true) } )
    private List<Actividad> MedidasCorrectivas;
    
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinTable  (name="medidaspreventiva_actividades",joinColumns={ @JoinColumn(name="Id", referencedColumnName="Id") },
            inverseJoinColumns={ @JoinColumn(name="IdActividad", referencedColumnName="IdActividad", unique=true) } )
    private List<Actividad> MedidasPreventivas;
    private EnumTipoDesvio Tipo;
    
    @OneToMany(mappedBy = "AccionCorrectivaConProductoAfectado", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Producto> ProductosAfectados;
    
    // Constructores
    public Correctiva(){
        this.MedidasCorrectivas = new ArrayList<>();
        this.MedidasPreventivas = new ArrayList<>();
        this.ProductosAfectados = new ArrayList<>();
    }
    public Correctiva(Date FechaDeteccion, String Descripcion, EnumTipoDesvio TipoDesvio) {
        super(FechaDeteccion, Descripcion);
        this.MedidasCorrectivas = new ArrayList<>();
        this.MedidasPreventivas = new ArrayList<>();
        this.ProductosAfectados = new ArrayList<>();
        this.Tipo = TipoDesvio;
    }
    
    //  Getters
    public List<Actividad> getMedidasCorrectivas() {return this.MedidasCorrectivas;}
    public List<Actividad> getMedidasPreventivas() {return this.MedidasPreventivas;}
    public EnumTipoDesvio getTipo() {return this.Tipo;}
    
    public List<Producto> getProductosAfectados() {return this.ProductosAfectados;}
    
    // Setters
    public void setMedidasCorrectivas(List<Actividad> MedidasCorrectivas) {
        this.MedidasCorrectivas = MedidasCorrectivas;
        for(Actividad med: this.MedidasCorrectivas){
            med.setAccionActividad(this);
        }
    }
    public void setMedidasPreventivas(List<Actividad> MedidasPreventivas) {
        this.MedidasPreventivas = MedidasPreventivas;
        for(Actividad med: this.MedidasPreventivas){
            med.setAccionActividad(this);
        }
    }
    public void setTipo(EnumTipoDesvio Tipo) {this.Tipo = Tipo;}
    
    public void setProductosAfectados(List<Producto> ProductosAfectados) {
        this.ProductosAfectados  = ProductosAfectados;
        for(Producto prod: this.ProductosAfectados){
            prod.setAccionCorrectivaConProductoAfectado(this);
        }
    }
    
    // Listas
    // Medidas Correctivas
    public void addMedidaCorrectiva(Actividad MedidaCorrectiva){
        this.MedidasCorrectivas.add(MedidaCorrectiva);
        if(MedidaCorrectiva.getAccionActividad()== null || !MedidaCorrectiva.getAccionActividad().equals(this))
            MedidaCorrectiva.setAccionActividad(this);
    }
    
    public void removeMedidaCorrectiva(Actividad MedidaCorrectiva){
        this.MedidasCorrectivas.remove(MedidaCorrectiva);
        if(MedidaCorrectiva.getAccionActividad()!=null && MedidaCorrectiva.getAccionActividad().equals(this))
            MedidaCorrectiva.setAccionActividad(null);
    }
    
    
//Medidas Preventivas
    public void addMedidaPreventiva(Actividad MedidaPreventiva){
        this.MedidasPreventivas.add(MedidaPreventiva);
        if(MedidaPreventiva.getAccionActividad()== null || !MedidaPreventiva.getAccionActividad().equals(this))
            MedidaPreventiva.setAccionActividad(this);
    }
    
    public void removeMedidaPreventiva(Actividad MedidaPreventiva){
        this.MedidasPreventivas.remove(MedidaPreventiva);
        if(MedidaPreventiva.getAccionActividad()!=null && MedidaPreventiva.getAccionActividad().equals(this))
            MedidaPreventiva.setAccionActividad(null);
    }
    
//Productos Afectados
    public void addProductoAfectado(Producto ProductoAfectado){
        this.ProductosAfectados.add(ProductoAfectado);
        if(ProductoAfectado.getAccionCorrectivaConProductoAfectado()==null || !ProductoAfectado.getAccionCorrectivaConProductoAfectado().equals(this))
            ProductoAfectado.setAccionCorrectivaConProductoAfectado(this);
    }
    
    public void removeProductoAfectado(Producto ProductoAfectado){
        this.ProductosAfectados.remove(ProductoAfectado);
        if(ProductoAfectado.getAccionCorrectivaConProductoAfectado() != null && !ProductoAfectado.getAccionCorrectivaConProductoAfectado().equals(this))
            ProductoAfectado.setAccionCorrectivaConProductoAfectado(null);
    }
    
    public void removeProducoAfectado(int IdProductoAfectado){
        Iterator<Producto> it = this.ProductosAfectados.iterator() ;
        while(it.hasNext()){
            Producto p = it.next();
            if(p.getId()==IdProductoAfectado){
                it.remove();
                if(p.getAccionCorrectivaConProductoAfectado()!=null && p.getAccionCorrectivaConProductoAfectado().equals(this))
                    p.setAccionCorrectivaConProductoAfectado(null);
            }
        }
    }
    
    @Override
    public void CambiarEstado() {
        if(this.getEstadoAccion()!= EnumEstado.DESESTIMADA && this.getEstadoAccion()!= EnumEstado.CERRADA){
            // si ambas listas estan vacias el estado es pendiente
            if(this.MedidasCorrectivas.isEmpty() && this.MedidasPreventivas.isEmpty()){
                this.setEstadoAccion(EnumEstado.PENDIENTE);
            }else{
                boolean medCorrectivaImp = true;
                // chequear implementacion de todas las medidas correctivas
                for(Actividad medida: this.MedidasCorrectivas){
                    if(medida.getFechaImplementacion()==null) medCorrectivaImp = false;
                }
                boolean medPreventivaImp = true;
                // chequear implementacion de todas las medidas preventivas
                for(Actividad medida: this.MedidasPreventivas){
                    if(medida.getFechaImplementacion()==null) medPreventivaImp = false;
                }
                
                // comparar resultados de chequeos y setear nuevo estado
                if(medCorrectivaImp == true && medPreventivaImp == true){
                    if(this.getComprobacionEficacia().getResultado()!= EnumComprobacion.NO_COMPROBADA) {
                        this.setEstadoAccion(EnumEstado.CERRADA);
                    }else{
                        this.setEstadoAccion(EnumEstado.PROCESO_VER);
                    }
                }else{
                    if(medCorrectivaImp == true && medPreventivaImp != true){
                        if(this.getComprobacionImplementacion().getResultado() == EnumComprobacion.NO_COMPROBADA)
                            this.setEstadoAccion(EnumEstado.PROCESO_IMP);
                    }else{
                        if(medCorrectivaImp != true && medPreventivaImp != true){
                            this.setEstadoAccion(EnumEstado.PENDIENTE);
                        }
                    }
                }
            }
        }
    }
}
