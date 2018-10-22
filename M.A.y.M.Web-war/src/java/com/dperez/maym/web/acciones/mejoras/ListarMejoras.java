/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.acciones.mejoras;

import com.dperez.maymweb.accion.acciones.Mejora;
import com.dperez.maymweb.facades.FacadeAdministrador;
import com.dperez.maymweb.facades.FacadeLectura;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Diego
 */
@Named
@ViewScoped
public class ListarMejoras implements Serializable{
    @Inject
    private FacadeLectura fLectura;
    @Inject
    private FacadeAdministrador fAdmin;
    
    private List<Mejora> ListaAcciones;
    
    // Pagination
    private static final int MAX_ITEMS = 30;
    private int CantidadPaginas;
    private int PaginaActual;
    private List<Mejora> ListaCompletaAcciones;
    
    //  Getters
    public List<Mejora> getListaAcciones() {return ListaAcciones;}
    
    // Paginacion
    public static int getMAX_ITEMS() {return MAX_ITEMS;}
    public int getCantidadPaginas() {return CantidadPaginas;}
    public int getPaginaActual() {return PaginaActual;}
    
    //  Setters
    public void setListaAcciones(List<Mejora> ListaAcciones) {this.ListaAcciones = ListaAcciones;}
    
    // Metodos
    
    //  Inicializacion
    @PostConstruct
    public void init(){
        // recuperar Empresa para filtrar las acciones
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        
        // Paginacion
        PaginaActual = 1;
        try{
            PaginaActual = Integer.parseInt(request.getParameter("pagina"));
        }catch(NumberFormatException ex){
            System.out.println("Error en pagina actual: " + ex.getLocalizedMessage());
        }
        
        // llenar la lista de acciones
        ListaAcciones = new ArrayList<>();
        ListaCompletaAcciones = (List<Mejora>)(List<?>)fLectura.ListarAccionesMejoras();
        
        CantidadPaginas = calcularCantidadPaginas(ListaCompletaAcciones.size());
        
        // llenar la lista con todas las areas registradas.
        cargarPagina(PaginaActual);
    }
    
    /**
     * Calcula la cantidad de paginas necsarias para mostrar el total de acciones de acuerdo al maximo definido por cada una.
     * @param cantidadAcciones
     * @return
     */
    private int calcularCantidadPaginas(int cantidadAcciones){
        Double resto = (double) cantidadAcciones / (double) MAX_ITEMS;
        int cantidad = resto.intValue();
        resto = resto - resto.intValue();
        if(resto > 0){
            cantidad ++;
        }
        return cantidad;
    }
    
    /**
     * Carga la lista de acciones para visualizar.
     * @param pagina
     */
    public void cargarPagina(int pagina){
        int min = 0;
        int max = MAX_ITEMS;
        if(pagina!= 1) {
            min = (pagina-1) * MAX_ITEMS;
            max = min + MAX_ITEMS;
        }
        if(max > ListaCompletaAcciones.size()) max = ListaCompletaAcciones.size();
        ListaAcciones.clear();
        Collections.sort(ListaCompletaAcciones);
        for(int i = min; i < max; i++){
            Mejora accion = ListaCompletaAcciones.get(i);
            ListaAcciones.add(accion);
        }
        Collections.sort(ListaAcciones);
    }
}
