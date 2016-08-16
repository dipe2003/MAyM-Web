/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.accion.acciones;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.accion.medida.medidas.MedidaCorrectiva;
import com.dperez.maymweb.accion.medida.medidas.MedidaPreventiva;
import com.dperez.maymweb.estado.EnumEstado;
import java.util.List;

import com.dperez.maymweb.producto.Producto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author dperez
 */
@Entity
public class Correctiva extends Accion implements Serializable {
    @OneToMany(mappedBy = "AccionCorrectivaMedidaCorrectiva")
    private List<MedidaCorrectiva> MedidasCorrectivas;
    @OneToMany(mappedBy = "AccionCorrectivaMedidaPreventiva")
    private List<MedidaPreventiva> MedidasPreventivas;
    private EnumTipoDesvio Tipo;
    
    @OneToMany(mappedBy = "AccionCorrectivaConProductoAfectado")
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
    public List<MedidaCorrectiva> getMedidasCorrectivas() {return this.MedidasCorrectivas;}
    public List<MedidaPreventiva> getMedidasPreventivas() {return this.MedidasPreventivas;}
    public EnumTipoDesvio getTipo() {return this.Tipo;}
    
    public List<Producto> getProductosAfectados() {return this.ProductosAfectados;}
    
    // Setters
    public void setMedidasCorrectivas(List<MedidaCorrectiva> MedidasCorrectivas) {
        this.MedidasCorrectivas = MedidasCorrectivas;
        for(MedidaCorrectiva med: this.MedidasCorrectivas){
            med.setAccionCorrectivaMedidaCorrectiva(this);
        }
    }
    public void setMedidasPreventivas(List<MedidaPreventiva> MedidasPreventivas) {
        this.MedidasPreventivas = MedidasPreventivas;
        for(MedidaPreventiva med: this.MedidasPreventivas){
            med.setAccionCorrectivaMedidaPreventiva(this);
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
    public void addMedidaCorrectiva(MedidaCorrectiva MedidaCorrectiva){
        this.MedidasCorrectivas.add(MedidaCorrectiva);
        if(MedidaCorrectiva.getAccionCorrectivaMedidaCorrectiva() == null || !MedidaCorrectiva.getAccionCorrectivaMedidaCorrectiva().equals(this))
            MedidaCorrectiva.setAccionCorrectivaMedidaCorrectiva(this);
    }
    
    public void removeMedidaCorrectiva(MedidaCorrectiva MedidaCorrectiva){
        this.MedidasCorrectivas.remove(MedidaCorrectiva);
        if(MedidaCorrectiva.getAccionCorrectivaMedidaCorrectiva()!=null && MedidaCorrectiva.getAccionCorrectivaMedidaCorrectiva().equals(this))
            MedidaCorrectiva.setAccionCorrectivaMedidaCorrectiva(null);
    }
    
    public void removeMedidaCorrectiva(int IdMedidaCorrectiva){
        Iterator<MedidaCorrectiva> it = this.MedidasCorrectivas.iterator() ;
        while(it.hasNext()){
            MedidaCorrectiva mc = it.next();
            if(mc.getId()==IdMedidaCorrectiva){
                it.remove();
                if(mc.getAccionCorrectivaMedidaCorrectiva()!=null && mc.getAccionCorrectivaMedidaCorrectiva().equals(this))
                    mc.setAccionCorrectivaMedidaCorrectiva(null);
            }
        }
    }
    
//Medidas Preventivas
    public void addMedidaPreventiva(MedidaPreventiva MedidaPreventiva){
        this.MedidasPreventivas.add(MedidaPreventiva);
        if(MedidaPreventiva.getAccionCorrectivaMedidaPreventiva() == null || !MedidaPreventiva.getAccionCorrectivaMedidaPreventiva().equals(this))
            MedidaPreventiva.setAccionCorrectivaMedidaPreventiva(this);
    }
    
    public void removeMedidaPreventiva(MedidaPreventiva MedidaPreventiva){
        this.MedidasPreventivas.remove(MedidaPreventiva);
        if(MedidaPreventiva.getAccionCorrectivaMedidaPreventiva()!=null && MedidaPreventiva.getAccionCorrectivaMedidaPreventiva().equals(this))
            MedidaPreventiva.setAccionCorrectivaMedidaPreventiva(null);
    }
    
    public void removeMedidaPreventiva(int IdMedidaPreventiva){
        Iterator<MedidaPreventiva> it = this.MedidasPreventivas.iterator() ;
        while(it.hasNext()){
            MedidaPreventiva mp = it.next();
            if(mp.getId()==IdMedidaPreventiva){
                it.remove();
                if(mp.getAccionCorrectivaMedidaPreventiva()!=null && mp.getAccionCorrectivaMedidaPreventiva().equals(this))
                    mp.setAccionCorrectivaMedidaPreventiva(null);
            }
        }
    }
    
//Productos Afectados
    public void addProductoAfectado(Producto ProductoAfectado){
        this.ProductosAfectados.add(ProductoAfectado);
        if(ProductoAfectado.getAccionCorrectivaConProductoAfectado()!=null && !ProductoAfectado.getAccionCorrectivaConProductoAfectado().equals(this))
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
            boolean medCorrectivaImp = true;
            // chequear implementacion de todas las medidas correctivas
            for(MedidaCorrectiva medida: this.MedidasCorrectivas){
                if(medida.getFechaImplementacion()!=null) medCorrectivaImp = false;
            }
            boolean medPreventivaImp = true;
            // chequear implementacion de todas las medidas preventivas
            for(MedidaPreventiva medida: this.MedidasPreventivas){
                if(medida.getFechaImplementacion()!=null) medPreventivaImp = false;
            }
            
            // comparar resultados de chequeos y setear nuevo estado
            if(medCorrectivaImp == true && medPreventivaImp == true){
                if(this.getFechaVerificacion()!=null) {
                    this.setEstadoAccion(EnumEstado.CERRADA);
                }else{
                    this.setEstadoAccion(EnumEstado.PROCESO_VER);
                }
            }else{
                if(medCorrectivaImp == true && medPreventivaImp != true){
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
