/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.inicio;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.facades.FacadeLectura;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


/**
 *
 * @author dperez
 */
@Named
@ViewScoped
public class GraficosIndex implements Serializable{
    @Inject
    private FacadeLectura fLectura;
    
    private int IdEmpresa;
    
    // Valores para grafico Estados
    private int TotalAcciones;
    private int AccionesCerradas;
    private int AccionesPendientes;
    private int AccionesDesestimadas;
    private int AccionesProcesoImp;
    private int AccionesProcesoVerif;
    
    // Getters
    public int getTotalAcciones() {return TotalAcciones;}
    public int getAccionesCerradas() {return AccionesCerradas;}
    public int getAccionesPendientes() {return AccionesPendientes;}
    public int getAccionesDesestimadas() {return AccionesDesestimadas;}
    public int getAccionesProcesoImp() {return AccionesProcesoImp;}
    public int getAccionesProcesoVerif() {return AccionesProcesoVerif;}
    
    // Setters
    public void setTotalAcciones(int TotalAcciones) {this.TotalAcciones = TotalAcciones;}
    public void setAccionesCerradas(int AccionesCerradas) {this.AccionesCerradas = AccionesCerradas;}
    public void setAccionesPendientes(int AccionesPendientes) {this.AccionesPendientes = AccionesPendientes;}
    public void setAccionesDesestimadas(int AccionesDesestimadas) {this.AccionesDesestimadas = AccionesDesestimadas;}
    public void setAccionesProcesoImp(int AccionesProcesoImp) {this.AccionesProcesoImp = AccionesProcesoImp;}
    public void setAccionesProcesoVerif(int AccionesProcesoVerif) {this.AccionesProcesoVerif = AccionesProcesoVerif;}
    
    // Metodos
    @PostConstruct
    public void init(){
        // llenar los valores de estado.
        List<Accion> lstAcciones = fLectura.ListarAcciones();
        TotalAcciones = lstAcciones.size();
        for(Accion accion:lstAcciones){
            switch(accion.getEstadoAccion()){
                case CERRADA:
                    AccionesCerradas++;
                    break;
                case DESESTIMADA:
                    AccionesDesestimadas++;
                    break;
                case PENDIENTE:
                    AccionesPendientes++;
                    break;
                case PROCESO_IMP:
                    AccionesProcesoImp++;
                    break;
                case PROCESO_VER:
                    AccionesProcesoVerif++;
                    break;
            }
        }
    }
    
}
