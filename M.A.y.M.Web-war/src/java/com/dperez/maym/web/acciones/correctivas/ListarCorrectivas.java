/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.acciones.correctivas;

import com.dperez.maym.web.acciones.filtros.DatosFiltros;
import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.accion.acciones.Correctiva;
import com.dperez.maymweb.area.Area;
import com.dperez.maymweb.codificacion.Codificacion;
import com.dperez.maymweb.deteccion.Deteccion;
import com.dperez.maymweb.empresa.Empresa;
import com.dperez.maymweb.estado.EnumEstado;
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
public class ListarCorrectivas implements Serializable{
    @Inject
    private FacadeLectura fLectura;
    @Inject
    private FacadeAdministrador fAdmin;
    
    private List<Correctiva> ListaAcciones;
    
    // Pagination
    private static final int MAX_ITEMS = 30;
    private int CantidadPaginas;
    private int PaginaActual;
    private List<Correctiva> ListaCompletaAcciones;
    
    // Filtros
    private DatosFiltros filtros = new DatosFiltros();
    private List<Area> areasEnRegistros = new ArrayList<>();
     private List<Codificacion> codificacionesEnRegistros = new ArrayList<>();
      private List<Deteccion> deteccionesEnRegistros = new ArrayList<>();
      private EnumEstado[] estadosEnRegistros = EnumEstado.values();
    
    //  Getters
    public List<Correctiva>getListaAcciones() {return ListaAcciones;}

    public List<Area> getAreasEnRegistros() {
        return areasEnRegistros;
    }

    public List<Codificacion> getCodificacionesEnRegistros() {
        return codificacionesEnRegistros;
    }

    public List<Deteccion> getDeteccionesEnRegistros() {
        return deteccionesEnRegistros;
    }

    public EnumEstado[] getEstadosEnRegistros() {
        return estadosEnRegistros;
    }
    
    
    // Paginacion
    public static int getMAX_ITEMS() {return MAX_ITEMS;}
    public int getCantidadPaginas() {return CantidadPaginas;}
    public int getPaginaActual() {return PaginaActual;}
    
    //  Setters
    public void setListaAcciones(List<Correctiva> ListaAcciones) {this.ListaAcciones = ListaAcciones;}
    
    // Metodos

    public void setAreasEnRegistros(List<Area> areasEnRegistros) {
        this.areasEnRegistros = areasEnRegistros;
    }

    public void setCodificacionesEnRegistros(List<Codificacion> codificacionesEnRegistros) {
        this.codificacionesEnRegistros = codificacionesEnRegistros;
    }

    public void setDeteccionesEnRegistros(List<Deteccion> deteccionesEnRegistros) {
        this.deteccionesEnRegistros = deteccionesEnRegistros;
    }

    public void setEstadosEnRegistros(EnumEstado[] estadosEnRegistros) {
        this.estadosEnRegistros = estadosEnRegistros;
    }
 
    
    
    //  Inicializacion
    @PostConstruct
    public void init(){
        // recuperar Empresa para filtrar las acciones
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Empresa empresa = (Empresa)request.getSession().getAttribute("Empresa");
        
        // Paginacion
        PaginaActual = 1;
        try{
            PaginaActual = Integer.parseInt(request.getParameter("pagina"));
        }catch(NumberFormatException ex){
            System.out.println("Error en pagina actual: " + ex.getLocalizedMessage());
        }
        
        // llenar la lista de acciones
        ListaAcciones = new ArrayList<>();
        ListaCompletaAcciones = (List<Correctiva>)(List<?>)fLectura.ListarAccionesCorrectivas();
        
        Double resto = (double) ListaCompletaAcciones.size() / (double) MAX_ITEMS;
        CantidadPaginas = resto.intValue();
        resto = resto - resto.intValue();
        if(resto > 0){
            CantidadPaginas ++;
        }
        
        // llenar la lista con todas las areas registradas.
        cargarPagina(PaginaActual);
        
        // datos para filtros
        areasEnRegistros = filtros.ExtraerAreas((List<Accion>)(List<?>) ListaCompletaAcciones);
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
            Correctiva accion = ListaCompletaAcciones.get(i);
            ListaAcciones.add(accion);
        }
        Collections.sort(ListaAcciones);
    }
    
}
