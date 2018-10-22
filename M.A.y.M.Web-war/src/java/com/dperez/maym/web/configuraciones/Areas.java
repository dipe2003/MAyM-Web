/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.configuraciones;

import com.dperez.maymweb.area.Area;
import com.dperez.maymweb.empresa.Empresa;
import com.dperez.maymweb.facades.FacadeAdministrador;
import com.dperez.maymweb.facades.FacadeLectura;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@ViewScoped
public class Areas implements Serializable {
    @Inject
    private FacadeAdministrador fAdmin;
    @Inject
    private FacadeLectura fLectura;
    
    private Empresa EmpresaLogueada;
    
    private Area AreaSeleccionada;
    
    private String NombreArea;
    private String CorreoArea;
    private int IdAreaSeleccionada;
    
    private boolean AplicaEmpresa;
    private boolean ContieneAcciones;
    
    private List<Area> ListaAreas;
    
    // Pagination
    private static final int MAX_ITEMS = 10;
    private int CantidadPaginas;
    private int PaginaActual;
    private List<Area> ListaCompletaAreas;
    
    //  Getters
    
    public String getNombreArea() {return NombreArea;}
    public String getCorreoArea() {return CorreoArea;}
    public List<Area> getListaAreas() {return ListaAreas;}
    
    public Empresa getEmpresaLogueada() {return EmpresaLogueada;}
    public boolean isAplicaEmpresa() {return AplicaEmpresa;}
    public boolean isContieneAcciones() {return ContieneAcciones;}
    
    public static int getMAX_ITEMS() {return MAX_ITEMS;}
    public int getCantidadPaginas() {return CantidadPaginas;}
    public int getPaginaActual() {return PaginaActual;}
    
    //  Setters
    
    public void setNombreArea(String NombreArea) {this.NombreArea = NombreArea;}
    public void setCorreoArea(String CorreoArea) {this.CorreoArea = CorreoArea;}
    public void setListaAreas(List<Area> ListaAreas) {this.ListaAreas = ListaAreas;}
    
    public void setEmpresaLogueada(Empresa EmpresaLogueada) {this.EmpresaLogueada = EmpresaLogueada;}
    public void setAplicaEmpresa(boolean AplicaEmpresa) {this.AplicaEmpresa = AplicaEmpresa;}
    public void setContieneAcciones(boolean ContieneAcciones) {this.ContieneAcciones = ContieneAcciones;}
    
    //  Metodos
    
    /**
     * Carga de propiedades al inicio
     */
    @PostConstruct
    public void init(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        EmpresaLogueada = (Empresa)request.getSession().getAttribute("Empresa");
        PaginaActual = 1;
        try{
            PaginaActual = Integer.parseInt(request.getParameter("pagina"));
        }catch(NumberFormatException ex){
            System.out.println("Error en pagina actual: " + ex.getLocalizedMessage());
        }
        //  Areas
        ListaAreas = new ArrayList<>();
        ListaCompletaAreas = fLectura.ListarAreasSectores(-1);
        
        // Paginas
        CantidadPaginas = calcularCantidadPaginas(ListaCompletaAreas.size());
        
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
     * Carga la lista de areas para visualizar.
     * @param pagina
     */
    public void cargarPagina(int pagina){
        int min = 0;
        int max = MAX_ITEMS;
        if(pagina!= 1) {
            min = (pagina-1) * MAX_ITEMS;
            max = min + MAX_ITEMS;
        }
        if(max > ListaCompletaAreas.size()) max = ListaCompletaAreas.size();
        ListaAreas.clear();
        Collections.sort(ListaCompletaAreas);
        for(int i = min; i < max; i++){
            Area area = ListaCompletaAreas.get(i);
            ListaAreas.add(area);
        }
        Collections.sort(ListaAreas);
    }
    
    /**
     * Crea el area con los datos ingresados.
     * Muestra un mensaje de error si no se creo.
     * Se agrega a la lista de areas del bean.
     * @throws java.io.IOException
     */
    public void nuevaArea() throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        if(comprobarNombreArea(NombreArea)){
            context.addMessage("form_areas:btn_nueva_area", new FacesMessage(SEVERITY_ERROR, "Ya existe un area con ese nombre", "Ya existe un area con ese nombre" ));
            context.renderResponse();
        }else{
            Area area;
            if((area = fAdmin.NuevaArea(NombreArea, CorreoArea)) != null){
                if(area != null){
                    fAdmin.ModificarEmpresaArea(area.getId(), true, EmpresaLogueada.getId());
                }
                context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/Views/Configuraciones/Areas.xhtml?pagina=1");
            }else{
                context.addMessage("form_areas:btn_nueva_area", new FacesMessage(SEVERITY_ERROR, "No se pudo crear el area", "No se pudo crear el area" ));
                context.renderResponse();
            }
        }
    }
    
    /**
     * Actualiza el area con lo datos ingresados.
     * Muestra un mensaje de errror si no se actualizo.
     * Agrega los cambios al area de la lista del bean.
     * @throws java.io.IOException
     */
    public void editarArea() throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        if((fAdmin.EditarArea(IdAreaSeleccionada, NombreArea, CorreoArea))!=-1){
            if(AreaSeleccionada.contieneEmpresa(EmpresaLogueada.getId()) != this.AplicaEmpresa){
                if((fAdmin.ModificarEmpresaArea(IdAreaSeleccionada, AplicaEmpresa, EmpresaLogueada.getId()))==-1){
                    context.addMessage("form_areas:btn_editar_area", new FacesMessage(SEVERITY_ERROR, "No se pudo modificar empresa", "No se pudo modificar empresa" ));
                    context.renderResponse();
                }
            }
            context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/Views/Configuraciones/Areas.xhtml?pagina="+PaginaActual);
        }else{
            context.addMessage("form_areas:btn_editar_area", new FacesMessage(SEVERITY_ERROR, "No se pudo editar el area", "No se pudo editar el area" ));
            context.renderResponse();
        }
    }
    
    /**
     * Eliminina el area indicada.
     * Muestra un mensaje de error si no se pudo eliminar.
     * Remueve el area de la lista del bean.
     * @param IdAreaSeleccionada
     * @throws java.io.IOException
     */
    public void eliminarArea(int IdAreaSeleccionada) throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        // primero se comprueba que pertenezca a la empresa del usuario logueado y que no aplique a otra empresa
        if(AreaSeleccionada.contieneEmpresa(EmpresaLogueada.getId()) && AreaSeleccionada.getEmpresasArea().size()==1){
            if(fAdmin.EliminarArea(IdAreaSeleccionada, EmpresaLogueada.getId())!=-1){
                context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/Views/Configuraciones/Areas.xhtml?pagina=1");
            }else{
                context.addMessage("form_areas:btn_eliminar_"+IdAreaSeleccionada, new FacesMessage(SEVERITY_ERROR, "No se pudo eliminar el Area", "No se pudo eliminar el Area" ));
                context.renderResponse();
            }
        }else{
            context.addMessage("form_areas:btn_eliminar_"+IdAreaSeleccionada, new FacesMessage(SEVERITY_ERROR, "No se pudo eliminar el area, esta utilizada por otra empresa", "No se pudo eliminar el area, esta utilizada por otra empresa" ));
            context.renderResponse();
        }
    }
    
    /**
     * Comprueba que no exista un Area con el mismo nombre.
     * @param NombreArea
     * @return
     */
    private boolean comprobarNombreArea(String NombreArea){
        return ListaAreas.stream()
                .anyMatch(area->area.getNombre().equalsIgnoreCase(NombreArea));
    }
    
    /**
     * Carga los atributos NombreArea, CorreoArea e IdAreaSeleccionada segun el id especificado en la vista.
     * @param IdArea
     */
    public void cargarDatos(int IdArea){
        if(IdArea < 0 ){
            this.NombreArea = new String();
            this.CorreoArea = new String();
            this.IdAreaSeleccionada = -1;
        }else{
            AreaSeleccionada = ListaAreas.stream()
                    .filter(area->area.getId() == IdArea)
                    .findFirst()
                    .orElse(null);
            
            this.NombreArea = AreaSeleccionada.getNombre();
            this.CorreoArea = AreaSeleccionada.getCorreo();
            this.IdAreaSeleccionada = IdArea;
            
            // verifica si pertenece a la empresa del usuario logueado
            AplicaEmpresa = AreaSeleccionada.contieneEmpresa(EmpresaLogueada.getId());
            
            // verifica que no tenga acciones codificadas para esta empresa
            // al encontrar la primer accion que pertenezca a la codificacion y empresa ContieneAcciones = true
            if(this.AplicaEmpresa == true){
                ContieneAcciones = AreaSeleccionada.getAccionesEnAreaSector().stream()
                        .anyMatch(accion->accion.getEmpresaAccion().getId() == EmpresaLogueada.getId());
            }
        }
    }
}