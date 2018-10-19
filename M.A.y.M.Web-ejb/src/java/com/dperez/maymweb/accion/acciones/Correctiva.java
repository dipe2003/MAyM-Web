/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package com.dperez.maymweb.accion.acciones;

import com.dperez.maymweb.accion.Accion;
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
public class Correctiva extends Accion implements Serializable {

    @OneToMany(cascade = CascadeType.REMOVE)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "medidascorrectivas_actividades", joinColumns = {
        @JoinColumn(name = "Id", referencedColumnName = "Id")},
            inverseJoinColumns = {
                @JoinColumn(name = "IdActividad", referencedColumnName = "IdActividad", unique = true)})
    private List<Actividad> MedidasCorrectivas;

    @OneToMany(cascade = CascadeType.REMOVE)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "medidaspreventiva_actividades", joinColumns = {
        @JoinColumn(name = "Id", referencedColumnName = "Id")},
            inverseJoinColumns = {
                @JoinColumn(name = "IdActividad", referencedColumnName = "IdActividad", unique = true)})
    private List<Actividad> MedidasPreventivas;
    private EnumTipoDesvio Tipo;

    @OneToMany(mappedBy = "AccionCorrectivaConProductoAfectado", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Producto> ProductosAfectados;

    // Constructores
    public Correctiva() {
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
    public List<Actividad> getMedidasCorrectivas() {
        return this.MedidasCorrectivas;
    }

    public List<Actividad> getMedidasPreventivas() {
        return this.MedidasPreventivas;
    }

    public EnumTipoDesvio getTipo() {
        return this.Tipo;
    }

    public List<Producto> getProductosAfectados() {
        return this.ProductosAfectados;
    }

    // Setters
    public void setMedidasCorrectivas(List<Actividad> MedidasCorrectivas) {
        this.MedidasCorrectivas = MedidasCorrectivas;
        for (Actividad med : this.MedidasCorrectivas) {
            med.setAccionActividad(this);
        }
    }

    public void setMedidasPreventivas(List<Actividad> MedidasPreventivas) {
        this.MedidasPreventivas = MedidasPreventivas;
        for (Actividad med : this.MedidasPreventivas) {
            med.setAccionActividad(this);
        }
    }

    public void setTipo(EnumTipoDesvio Tipo) {
        this.Tipo = Tipo;
    }

    public void setProductosAfectados(List<Producto> ProductosAfectados) {
        this.ProductosAfectados = ProductosAfectados;
        for (Producto prod : this.ProductosAfectados) {
            prod.setAccionCorrectivaConProductoAfectado(this);
        }
    }

    // Listas
    // Medidas Correctivas
    public Actividad addMedidaCorrectiva(Date fechaEstimadaImplementacion, String descripcion) {
        Actividad MedidaCorrectiva = new Actividad(fechaEstimadaImplementacion, descripcion);
        this.MedidasCorrectivas.add(MedidaCorrectiva);
        MedidaCorrectiva.setAccionActividad(this);
        return MedidaCorrectiva;
    }

    public void removeMedidaCorrectiva(Actividad MedidaCorrectiva) {
        this.MedidasCorrectivas.remove(MedidaCorrectiva);
        if (MedidaCorrectiva.getAccionActividad() != null && MedidaCorrectiva.getAccionActividad().equals(this)) {
            MedidaCorrectiva.setAccionActividad(null);
        }
    }

//Medidas Preventivas
    public Actividad addMedidaPreventiva(Date fechaEstimadaImplementacion, String descripcion) {
        Actividad MedidaPreventiva = new Actividad(fechaEstimadaImplementacion, descripcion);
        this.MedidasPreventivas.add(MedidaPreventiva);
        MedidaPreventiva.setAccionActividad(this);
        return MedidaPreventiva;
    }

    public void removeMedidaPreventiva(Actividad MedidaPreventiva) {
        this.MedidasPreventivas.remove(MedidaPreventiva);
        if (MedidaPreventiva.getAccionActividad() != null && MedidaPreventiva.getAccionActividad().equals(this)) {
            MedidaPreventiva.setAccionActividad(null);
        }
    }

//Productos Afectados
    public Producto addProductoAfectado(String nombre, String datos) {
        Producto ProductoAfectado = new Producto(nombre, datos);
        this.ProductosAfectados.add(ProductoAfectado);
        ProductoAfectado.setAccionCorrectivaConProductoAfectado(this);
        return ProductoAfectado;
    }

    public void removeProductoAfectado(Producto ProductoAfectado) {
        this.ProductosAfectados.remove(ProductoAfectado);
        if (ProductoAfectado.getAccionCorrectivaConProductoAfectado() != null && !ProductoAfectado.getAccionCorrectivaConProductoAfectado().equals(this)) {
            ProductoAfectado.setAccionCorrectivaConProductoAfectado(null);
        }
    }

    public void removeProductoAfectado(int IdProductoAfectado) {
        Iterator<Producto> it = this.ProductosAfectados.iterator();
        while (it.hasNext()) {
            Producto p = it.next();
            if (p.getId() == IdProductoAfectado) {
                it.remove();
                if (p.getAccionCorrectivaConProductoAfectado() != null && p.getAccionCorrectivaConProductoAfectado().equals(this)) {
                    p.setAccionCorrectivaConProductoAfectado(null);
                }
            }
        }
    }

    @Override
    public void CambiarEstado() {
        //Comprobar si fue desestimada ya que es el único estado que solo depende del usuario
        if (EstadoAccion != EnumEstado.DESESTIMADA) {
            // comienza con el estado por defecto de las acciones: PENDIENTES
            EstadoAccion = EnumEstado.PENDIENTE;
            if (SeComproboEficacia()) {
                EstadoAccion = EnumEstado.CERRADA;
            } else {
                if (SeComproboImplementacion()) {
                    EstadoAccion = EnumEstado.PROCESO_VER;
                } else {
                    // si las listas están vacías el estado es pendiente
                    // si alguna no está vacía, dependiendo de si fueron implementadas o no las actividades puede 
                    // cambiar el estado a PROCESO IMP
                    if (!MedidasCorrectivas.isEmpty()) {
                        if (ExisteAlgunaActividadImplementada(MedidasCorrectivas)) {
                            EstadoAccion = EnumEstado.PROCESO_IMP;
                        }
                    }
                    if (!MedidasPreventivas.isEmpty()) {
                        if (ExisteAlgunaActividadImplementada(MedidasPreventivas)) {
                            EstadoAccion = EnumEstado.PROCESO_IMP;
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean EstanImplementadaActividades() {
        return (EstanImplementadasActividades(MedidasCorrectivas) && EstanImplementadasActividades(MedidasPreventivas)) == true;
    }

    @Override
    public Actividad GetActividad(int IdActividad) {
        Actividad actividad = MedidasCorrectivas.stream()
                .filter(medida->medida.getIdActividad()== IdActividad)
                .findFirst()
                .orElse(null);
        if(actividad == null){
            actividad = MedidasPreventivas.stream()
                .filter(medida->medida.getIdActividad()== IdActividad)
                .findFirst()
                .orElse(null);
        }
        return actividad;
    }
}
