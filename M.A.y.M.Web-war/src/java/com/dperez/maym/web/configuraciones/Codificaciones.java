/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.configuraciones;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.codificacion.Codificacion;
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
public class Codificaciones implements Serializable {
    @Inject
    private FacadeAdministrador fAdmin;
    @Inject
    private FacadeLectura fLectura;
    
    private Empresa EmpresaLogueada;
    
    private Codificacion CodificacionSeleccionada;
    
    private String NombreCodificacion;
    private String DescripcionCodificacion;
    private int IdCodificacionSeleccionada;
    private boolean AplicaEmpresa;
    private boolean ContieneAcciones;
    private List<Codificacion> ListaCodificaciones;
    
    // Pagination
    private static final int MAX_ITEMS = 10;
    private int CantidadPaginas;
    private int PaginaActual;
    private List<Codificacion> ListaCompletaCodificaciones;
    
    //  Getters
    
    public String getNombreCodificacion() {return NombreCodificacion;}
    public String getDescripcionCodificacion() {return DescripcionCodificacion;}
    public boolean isAplicaEmpresa() {return AplicaEmpresa;}
    public List<Codificacion> getListaCodificaciones() {return ListaCodificaciones;}
    public boolean isContieneAcciones() {return ContieneAcciones;}
    public Empresa getEmpresaLogueada(){return this.EmpresaLogueada;}
    
    public static int getMAX_ITEMS() {return MAX_ITEMS;}
    public int getCantidadPaginas() {return CantidadPaginas;}
    public int getPaginaActual() {return PaginaActual;}
    
    //  Setters
    
    public void setNombreCodificacion(String NombreCodificacion) {this.NombreCodificacion = NombreCodificacion;}
    public void setDescripcionCodificacion(String DescripcionCodificacion) {this.DescripcionCodificacion = DescripcionCodificacion;}
    public void setAplicaEmpresa(boolean AplicaEmpresa) {this.AplicaEmpresa = AplicaEmpresa;}
    public void setListaCodificaciones(List<Codificacion> ListaCodificaciones) {this.ListaCodificaciones = ListaCodificaciones;}
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
        ListaCodificaciones = new ArrayList<>();
        ListaCompletaCodificaciones = fLectura.ListarCodificaciones(-1);
        
        // Paginas
        Double resto = (double) ListaCompletaCodificaciones.size() / (double) MAX_ITEMS;
        CantidadPaginas = resto.intValue();
        resto = resto - resto.intValue();
        if(resto > 0){
            CantidadPaginas ++;
        }
        
        // llenar la lista con todas las areas registradas.
        cargarPagina(PaginaActual);
    }
    
    /**
     * Carga la lista de codificaciones para visualizar.
     * @param pagina 
     */
    public void cargarPagina(int pagina){
        int min = 0;
        int max = MAX_ITEMS;
        if(pagina!= 1) {
            min = (pagina-1) * MAX_ITEMS;
            max = min + MAX_ITEMS;
        }
        if(max > ListaCompletaCodificaciones.size()) max = ListaCompletaCodificaciones.size();
        ListaCodificaciones.clear();        
        Collections.sort(ListaCompletaCodificaciones);        
        for(int i = min; i < max; i++){
            Codificacion cod = ListaCompletaCodificaciones.get(i);
            ListaCodificaciones.add(cod);
        }
        Collections.sort(ListaCodificaciones);
    }
    /**
     * Crea la codificacion con los datos ingresados.
     * Muestra un mensaje de errror si no se creo, se agrega la codificacion a la empres logueada y redirige a la misma pagina para ver los resultados.
     * @throws java.io.IOException
     */
    public void nuevaCodificacion() throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        if(comprobarNombreCodificacion(NombreCodificacion)){
            context.addMessage("form_codificaciones:btn_nueva_codificacion", new FacesMessage(SEVERITY_ERROR, "Ya existe una codificacion con ese nombre", "Ya existe una codificacion con ese nombre" ));
            context.renderResponse();
        }else{
            Codificacion cod;
            if((cod = fAdmin.NuevaCodificacion(NombreCodificacion, DescripcionCodificacion)) != null){
                fAdmin.ModificarEmpresaCodificacion(cod.getId(), true, EmpresaLogueada.getId());
                context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/Views/Configuraciones/Codificaciones.xhtml?pagina=1");
            }else{
                context.addMessage("form_codificaciones:btn_nueva_codificacion", new FacesMessage(SEVERITY_ERROR, "No se pudo crear la Codificacion", "No se pudo crear la Codificacion" ));
                context.renderResponse();
            }
        }
    }
    
    /**
     * Actualiza la codificacion con lo datos ingresados.
     * Se agrega o quita relacion con empresa.
     * Muestra un mensaje de errror si no se actualizo, redirige a la misma pagina para ver los resultados.
     * @throws java.io.IOException
     */
    public void editarCodificacion() throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        if((fAdmin.EditarCodificacion(IdCodificacionSeleccionada, NombreCodificacion, DescripcionCodificacion))!=-1){
            boolean contiene = CodificacionSeleccionada.contieneEmpresa(EmpresaLogueada.getId());
            if(contiene != this.AplicaEmpresa){
                if((fAdmin.ModificarEmpresaCodificacion(IdCodificacionSeleccionada, AplicaEmpresa, EmpresaLogueada.getId()))==-1){
                    context.addMessage("form_codificaciones:btn_editar_codificacion", new FacesMessage(SEVERITY_ERROR, "No se pudo modificar empresa", "No se pudo modificar empresa" ));
                    context.renderResponse();
                }
            }
            context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/Views/Configuraciones/Codificaciones.xhtml?pagina="+PaginaActual);
        }else{
            context.addMessage("form_codificaciones:btn_editar_codificacion", new FacesMessage(SEVERITY_ERROR, "No se pudo editar la codificacion", "No se pudo editar la codificacion" ));
            context.renderResponse();
        }
    }
    
    /**
     * Comprueba que no exista una Codificacion con el mismo nombre.
     * @param NombreCodificacion
     * @return
     */
    private boolean comprobarNombreCodificacion(String NombreCodificacion){
        for(Codificacion cod: this.ListaCodificaciones){
            if (cod.getNombre().equalsIgnoreCase(NombreCodificacion)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Eliminina la codificacion indicada.
     * Muestra un mensaje de errror si no se actualizo, redirige a la misma pagina para ver los resultados.
     * @param IdCodificacionSeleccionada
     * @throws java.io.IOException
     */
    public void eliminarCodificacion(int IdCodificacionSeleccionada) throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        // primero se comprueba que pertenezca a la empresa del usuario logueado y que no aplique a otra empresa
        if(CodificacionSeleccionada.getEmpresasCodificacion().size()==1){
            if(fAdmin.EliminarCodificacion(IdCodificacionSeleccionada, EmpresaLogueada.getId())!=-1){
                context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/Views/Configuraciones/Codificaciones.xhtml?pagina=1");
            }else{
                context.addMessage("form_codificaciones:btn_eliminar_"+IdCodificacionSeleccionada, new FacesMessage(SEVERITY_ERROR, "No se pudo eliminar la codificacion", "No se pudo eliminar la codificacion" ));
                context.renderResponse();
            }
        }else{
            context.addMessage("form_codificaciones:btn_eliminar_"+IdCodificacionSeleccionada, new FacesMessage(SEVERITY_ERROR, "No se pudo eliminar la codificacion, esta utilizada por otra empresa", "No se pudo eliminar la codificacion, esta utilizada por otra empresa" ));
            context.renderResponse();
        }
    }
    
    /**
     * Carga los atributos NombreCodificacion, DescripcionCodificacion e IdCodificacionSeleccionada segun el id especificado en la vista.
     * @param IdCodificacion
     */
    public void cargarDatos(int IdCodificacion){
        if(IdCodificacion < 0 ){
            this.NombreCodificacion = new String();
            this.DescripcionCodificacion = new String();
            this.IdCodificacionSeleccionada = -1;
        }else{
            for(Codificacion cod: ListaCodificaciones){
                if(cod.getId() == IdCodificacion) {
                    CodificacionSeleccionada = cod;
                    break;
                }
            }
            this.NombreCodificacion = CodificacionSeleccionada.getNombre();
            this.DescripcionCodificacion = CodificacionSeleccionada.getDescripcion();
            this.IdCodificacionSeleccionada = IdCodificacion;
            
            // verifica si pertenece a la empresa del usuario logueado
            if(CodificacionSeleccionada.contieneEmpresa(EmpresaLogueada.getId())){
                this.AplicaEmpresa = true;
            }else{
                this.AplicaEmpresa = false;
            }
            
            // verifica que no tenga acciones codificadas para esta emrpesa
            // al encontrar la primer accion que pertenezca a la codificacion y empresa ContieneAcciones = true
            if(this.AplicaEmpresa == true){
                ContieneAcciones = false;
                for(Accion accion: CodificacionSeleccionada.getAccionesConCodificacion()){
                    if(accion.getEmpresaAccion().getId() == EmpresaLogueada.getId()){
                        ContieneAcciones = true;
                        break;
                    }
                }
            }
        }
    }
    
}