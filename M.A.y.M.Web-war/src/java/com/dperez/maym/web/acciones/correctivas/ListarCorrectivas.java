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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    
    // Variabels de Filtros
    private DatosFiltros filtros = new DatosFiltros();
    private Map<String, Area> areasEnRegistros = new HashMap<>();
    private List<Codificacion> codificacionesEnRegistros = new ArrayList<>();
    private List<Deteccion> deteccionesEnRegistros = new ArrayList<>();
    private EnumEstado[] estadosEnRegistros = EnumEstado.values();
    private String[] areasSeleccionadas;
    
    //  Getters
    public List<Correctiva>getListaAcciones() {return ListaAcciones;}
    public Map<String, Area> getAreasEnRegistros() {return areasEnRegistros;}
    public List<Codificacion> getCodificacionesEnRegistros() {return codificacionesEnRegistros;}
    public List<Deteccion> getDeteccionesEnRegistros() {return deteccionesEnRegistros;}
    public EnumEstado[] getEstadosEnRegistros() {return estadosEnRegistros;}

    public String[] getAreasSeleccionadas() {
        return areasSeleccionadas;
    }
    
    // Paginacion
    public static int getMAX_ITEMS() {return MAX_ITEMS;}
    public int getCantidadPaginas() {return CantidadPaginas;}
    public int getPaginaActual() {return PaginaActual;}
    
    //  Setters
    public void setListaAcciones(List<Correctiva> ListaAcciones) {this.ListaAcciones = ListaAcciones;}
    public void setAreasEnRegistros(Map<String, Area> areasEnRegistros) {this.areasEnRegistros = areasEnRegistros;}
    public void setCodificacionesEnRegistros(List<Codificacion> codificacionesEnRegistros) {this.codificacionesEnRegistros = codificacionesEnRegistros;}
    public void setDeteccionesEnRegistros(List<Deteccion> deteccionesEnRegistros) {this.deteccionesEnRegistros = deteccionesEnRegistros;}
    public void setEstadosEnRegistros(EnumEstado[] estadosEnRegistros) {this.estadosEnRegistros = estadosEnRegistros;}

    public void setAreasSeleccionadas(String[] areasSeleccionadas) {
        this.areasSeleccionadas = areasSeleccionadas;
    }
    
    // Metodos de filtro
    public void filtrarPorArea(){
        List<Integer> areas = new ArrayList<>();
        for(String id:areasSeleccionadas){
            areas.add(Integer.parseInt(id));
        }
        cargarPagina(PaginaActual, filtros.FiltrarAccionesPorArea((List<Accion>)(List<?>) ListaCompletaAcciones, areas));
        CantidadPaginas = calcularCantidadPaginas(ListaAcciones.size());
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
        
        CantidadPaginas = calcularCantidadPaginas(ListaCompletaAcciones.size());
        
        // llenar la lista con todas las areas registradas.
        cargarPagina(PaginaActual, (List<Accion>)(List<?>)ListaCompletaAcciones);
        
        // datos para filtros
        areasEnRegistros = filtros.ExtraerAreas((List<Accion>)(List<?>) ListaCompletaAcciones);
        areasSeleccionadas = areasEnRegistros.keySet().toArray(new String[areasEnRegistros.size()]);
        
        codificacionesEnRegistros = filtros.ExtraerCodificaciones((List<Accion>)(List<?>) ListaCompletaAcciones);
        deteccionesEnRegistros = filtros.ExtraerDetecciones((List<Accion>)(List<?>) ListaCompletaAcciones);
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
     * @param acciones
     */
    public void cargarPagina(int pagina, List<Accion> acciones){
        int min = 0;
        int max = MAX_ITEMS;
        if(pagina!= 1) {
            min = (pagina-1) * MAX_ITEMS;
            max = min + MAX_ITEMS;
        }
        if(max > acciones.size()) max = acciones.size();
        ListaAcciones.clear();
        Collections.sort(acciones);
        for(int i = min; i < max; i++){
            Correctiva accion = (Correctiva) acciones.get(i);
            ListaAcciones.add(accion);
        }
        Collections.sort(ListaAcciones);
    }
    
}
