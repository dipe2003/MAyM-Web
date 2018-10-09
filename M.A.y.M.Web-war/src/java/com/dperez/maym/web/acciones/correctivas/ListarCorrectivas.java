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
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
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
    private final DatosFiltros filtros = new DatosFiltros();
    private List<String> filtrosAplicados = new ArrayList<>();
    
    // Filtros AREA
    private Map<String, Area> areasEnRegistros = new HashMap<>();
    
    
    private String[] areasSeleccionadas;
    
    // Filtros Deteccion
    private Map<String, Deteccion> deteccionesEnRegistros = new HashMap<>();
    private String[] deteccionesSeleccionadas;
    
    // Filtros Estado
    private EnumEstado[] estadosEnRegistros = EnumEstado.values();
    private String[] estadosSeleccionados;
    
    // Filtros Codificaciones
    private Map<String, Codificacion> codificacionesEnRegistros = new HashMap<>();
    private String[] codificacionesSeleccionadas;
    
    //  Getters
    public List<Correctiva>getListaAcciones() {return ListaAcciones;}
    public Map<String, Area> getAreasEnRegistros() {return areasEnRegistros;}
    public Map<String, Codificacion> getCodificacionesEnRegistros() {return codificacionesEnRegistros;}
    public Map<String, Deteccion> getDeteccionesEnRegistros() {return deteccionesEnRegistros;}
    public EnumEstado[] getEstadosEnRegistros() {return estadosEnRegistros;}
    public String[] getAreasSeleccionadas() {return areasSeleccionadas;}
    public String[] getDeteccionesSeleccionadas() {return deteccionesSeleccionadas;}
    public String[] getEstadosSeleccionados() {return estadosSeleccionados;}
    public String[] getCodificacionesSeleccionadas() {return codificacionesSeleccionadas;}
    
    // Paginacion
    public static int getMAX_ITEMS() {return MAX_ITEMS;}
    public int getCantidadPaginas() {return CantidadPaginas;}
    public int getPaginaActual() {return PaginaActual;}
    
    //  Setters
    public void setListaAcciones(List<Correctiva> ListaAcciones) {this.ListaAcciones = ListaAcciones;}
    public void setAreasEnRegistros(Map<String, Area> areasEnRegistros) {this.areasEnRegistros = areasEnRegistros;}
    public void setCodificacionesEnRegistros(Map<String, Codificacion> codificacionesEnRegistros) {this.codificacionesEnRegistros = codificacionesEnRegistros;}
    public void setDeteccionesEnRegistros(Map<String, Deteccion> deteccionesEnRegistros) {this.deteccionesEnRegistros = deteccionesEnRegistros;}
    public void setEstadosEnRegistros(EnumEstado[] estadosEnRegistros) {this.estadosEnRegistros = estadosEnRegistros;}
    public void setAreasSeleccionadas(String[] areasSeleccionadas) {this.areasSeleccionadas = areasSeleccionadas;}
    public void setDeteccionesSeleccionadas(String[] deteccionesSeleccionadas) {this.deteccionesSeleccionadas = deteccionesSeleccionadas;}
    public void setEstadosSeleccionados(String[] estadosSeleccionados) {this.estadosSeleccionados = estadosSeleccionados;}
    public void setCodificacionesSeleccionadas(String[] codificacionesSeleccionadas) {this.codificacionesSeleccionadas = codificacionesSeleccionadas;}
    
    //**********************************************************************
    // Metodos de filtro de Areas
    //**********************************************************************
    /**
     * Llena la lista de acciones con las que contengan las areas seleccionadas.
     * @param accionesAFiltrar
     * @return
     */
    private List<Accion> filtrarPorArea(List<Correctiva> accionesAFiltrar){
        return  filtros.FiltrarAccionesPorArea((List<Accion>)(List<?>) accionesAFiltrar, ObtenerIdsSeleccionados(areasSeleccionadas));
    }
    
    /**
     * Llena la lista de acciones con todas las acciones.
     * Carga la pagina nuevamente.
     */
    public void quitarFiltroPorArea(){
        filtrosAplicados.remove("areas");
        CantidadPaginas = calcularCantidadPaginas(ListaCompletaAcciones.size());
        cargarPagina(PaginaActual, (List<Accion>)(List<?>)ListaCompletaAcciones);
        ResetListasAreas();
    }
    
    /**
     * Llena las listas para filtros con los valores originales.
     */
    private void ResetListasAreas(){
        areasEnRegistros = filtros.ExtraerAreas((List<Accion>)(List<?>) ListaCompletaAcciones);
        areasSeleccionadas = areasEnRegistros.keySet().toArray(new String[areasEnRegistros.size()]);
    }
    
    public void filtrarPorArea(AjaxBehaviorEvent event){
        filtrosAplicados.add("areas");
        FiltrarAcciones();
    }
    
    //**********************************************************************
    // Metodos de filtro de Deteccion
    //**********************************************************************
    
    /**
     * Llena la lista de acciones con las que contengan las detecciones seleccionadas.
     * @param accionesAFiltrar
     * @return
     */
    private List<Accion> filtrarPorDeteccion(List<Correctiva> accionesAFiltrar){
        return filtros.FiltrarAccionesPorDeteccion((List<Accion>)(List<?>) accionesAFiltrar, ObtenerIdsSeleccionados(deteccionesSeleccionadas));
    }
    
    /**
     * Llena la lista de acciones con todas las acciones.
     * Carga la pagina nuevamente.
     */
    public void quitarFiltroPorDeteccion(){
        filtrosAplicados.remove("detecciones");
        CantidadPaginas = calcularCantidadPaginas(ListaCompletaAcciones.size());
        cargarPagina(PaginaActual, (List<Accion>)(List<?>)ListaCompletaAcciones);
        ResetListasDeteccion();
    }
    
    /**
     * Llena las listas para filtros con los valores originales.
     */
    private void ResetListasDeteccion(){
        deteccionesEnRegistros = filtros.ExtraerDetecciones((List<Accion>)(List<?>) ListaCompletaAcciones);
        deteccionesSeleccionadas = deteccionesEnRegistros.keySet().toArray(new String[deteccionesEnRegistros.size()]);
    }
    
    public void filtrarPorDeteccion(){
        filtrosAplicados.add("detecciones");
        FiltrarAcciones();
    }
    
    //**********************************************************************
    // Metodos de la clase
    //**********************************************************************
    
    /**
     * Obtiene los ids como enteros del array de strings.
     * @param arrayIds
     * @return
     */
    private List<Integer> ObtenerIdsSeleccionados(String[] arrayIds){
        List<Integer> ids = new ArrayList<>();
        for(String id:arrayIds){
            ids.add(Integer.parseInt(id));
        }
        return ids;
    }
    
    /**
     * Aplica los filtros uno sobre otro dependiendo de los que ya se encuentren seleccionados.
     * La variable FiltrosAplicados contienen los filtros seleccionados.
     * Sigue el orden predefinido: FECHAS, AREAS, DETECCIONES, CODIFICACIONES, ESTADO.
     *
     */
    private void FiltrarAcciones(){
        List<Correctiva> accionesFiltradas = ListaCompletaAcciones;
        // Filtro de fechas
        if(filtrosAplicados.contains("fechas")){
            // aplicar filtro de fechas
            // accionesFiltradas = filtrarPorFecha(accionesAFiltrar);
            // actualizar lista de areas disponibles, detecciones, codificaciones y estados
//            areasEnRegistros = filtros.ExtraerAreas((List<Accion>)(List<?>)accionesFiltradas);
//            deteccionesEnRegistros = filtros.ExtraerDetecciones((List<Accion>)(List<?>)accionesFiltradas);
        }
        
        // Filtro de Areas
        if(filtrosAplicados.contains("areas")){
            accionesFiltradas = (List<Correctiva>)(List<?>)filtrarPorArea(accionesFiltradas);
            // actualizar lista de fechas disponibles, detecciones, codificaciones y estados
            deteccionesEnRegistros = filtros.ExtraerDetecciones((List<Accion>)(List<?>)accionesFiltradas);
            deteccionesSeleccionadas = new String[0];
        }
        
        // Filtro de Detecciones
        if(filtrosAplicados.contains("detecciones")){
            accionesFiltradas = (List<Correctiva>)(List<?>) filtrarPorDeteccion(accionesFiltradas);
            // actualizar lista de fechas disponibles, areas, codificaciones y estados
//            areasEnRegistros = filtros.ExtraerAreas((List<Accion>)(List<?>)accionesFiltradas);
        }
        
        // Filtro de Codificaciones
        if(filtrosAplicados.contains("codificaciones")){
            // aplicar filtro de Codificaciones
            // accionesFiltradas = filtrarPorCodifiacion(accionesAFiltrar);
            // actualizar lista de fechas disponibles, areas, detecciones y estados
//            areasEnRegistros = filtros.ExtraerAreas((List<Accion>)(List<?>)accionesFiltradas);
//            deteccionesEnRegistros = filtros.ExtraerDetecciones((List<Accion>)(List<?>)accionesFiltradas);
        }
        
        // Cargar pagina
        cargarPagina(PaginaActual, (List<Accion>)(List<?>)accionesFiltradas);
        CantidadPaginas = calcularCantidadPaginas(accionesFiltradas.size());
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
        ResetListasAreas ();
        
        ResetListasDeteccion();
        
        codificacionesEnRegistros = filtros.ExtraerCodificaciones((List<Accion>)(List<?>) ListaCompletaAcciones);
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
