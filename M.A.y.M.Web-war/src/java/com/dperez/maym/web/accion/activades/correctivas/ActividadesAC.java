/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.accion.activades.correctivas;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.accion.acciones.Correctiva;
import com.dperez.maymweb.accion.actividad.Actividad;
import com.dperez.maymweb.empresa.Empresa;
import com.dperez.maymweb.facades.FacadeDatos;
import com.dperez.maymweb.facades.FacadeLectura;
import com.dperez.maymweb.usuario.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class ActividadesAC implements Serializable {
    @Inject
    private FacadeDatos fDatos;
    @Inject
    private FacadeLectura fLectura;
    
    private Accion AccionCorrectiva;
    
    private Map<Integer, Usuario> ListaUsuariosEmpresa;
    
    private Map<Integer, Actividad> ListaMedidasCorrectivas;
    private int MedidaCorrectivaSeleccionada;
    
    private Map<Integer, Actividad> ListaMedidasPreventivas;
    private int MedidaPreventivaSeleccionada;
    
    private List<Actividad> ActividadesARemover;
    
    private Date FechaEstimadaImplementacionMedidaCorrectiva;
    private String DescripcionMedidaCorrectiva;
    private int ResponsableMedidaCorrectiva;
    
    private Date FechaEstimadaImplementacionMedidaPreventiva;
    private String DescripcionMedidaPreventiva;
    private int ResponsableMedidaPreventiva;

    //  Getters
    public Map<Integer, Usuario> getListaUsuariosEmpresa() {return ListaUsuariosEmpresa;}
    public Map<Integer, Actividad> getListaMedidasCorrectivas() {return ListaMedidasCorrectivas;}
    public int getMedidaCorrectivaSeleccionada() {return MedidaCorrectivaSeleccionada;}
    public Map<Integer, Actividad> getListaMedidasPreventivas() {return ListaMedidasPreventivas;}
    public int getMedidaPreventivaSeleccionada() {return MedidaPreventivaSeleccionada;}
    public Date getFechaEstimadaImplementacionMedidaCorrectiva() {return FechaEstimadaImplementacionMedidaCorrectiva;}
    public String getDescripcionMedidaCorrectiva() {return DescripcionMedidaCorrectiva;}
    public int getResponsableMedidaCorrectiva() {return ResponsableMedidaCorrectiva;}
    public Date getFechaEstimadaImplementacionMedidaPreventiva() {return FechaEstimadaImplementacionMedidaPreventiva;}
    public String getDescripcionMedidaPreventiva() {return DescripcionMedidaPreventiva;}
    public int getResponsableMedidaPreventiva() {return ResponsableMedidaPreventiva;}
    
    //  Setters
    public void setListaUsuariosEmpresa(Map<Integer, Usuario> ListaUsuariosEmpresa) {this.ListaUsuariosEmpresa = ListaUsuariosEmpresa;}
    public void setListaMedidasCorrectivas(Map<Integer, Actividad> ListaMedidasCorrectivas) {this.ListaMedidasCorrectivas = ListaMedidasCorrectivas;}
    public void setMedidaCorrectivaSeleccionada(int MedidaCorrectivaSeleccionada) {this.MedidaCorrectivaSeleccionada = MedidaCorrectivaSeleccionada;}
    public void setListaMedidasPreventivas(Map<Integer, Actividad> ListaMedidasPreventivas) {this.ListaMedidasPreventivas = ListaMedidasPreventivas;}
    public void setMedidaPreventivaSeleccionada(int MedidaPreventivaSeleccionada) {this.MedidaPreventivaSeleccionada = MedidaPreventivaSeleccionada;}
    public void setFechaEstimadaImplementacionMedidaCorrectiva(Date FechaEstimadaImplementacionMedidaCorrectiva) {this.FechaEstimadaImplementacionMedidaCorrectiva = FechaEstimadaImplementacionMedidaCorrectiva;}
    public void setDescripcionMedidaCorrectiva(String DescripcionMedidaCorrectiva) {this.DescripcionMedidaCorrectiva = DescripcionMedidaCorrectiva;}
    public void setResponsableMedidaCorrectiva(int ResponsableMedidaCorrectiva) {this.ResponsableMedidaCorrectiva = ResponsableMedidaCorrectiva;}
    public void setFechaEstimadaImplementacionMedidaPreventiva(Date FechaEstimadaImplementacionMedidaPreventiva) {this.FechaEstimadaImplementacionMedidaPreventiva = FechaEstimadaImplementacionMedidaPreventiva;}
    public void setDescripcionMedidaPreventiva(String DescripcionMedidaPreventiva) {this.DescripcionMedidaPreventiva = DescripcionMedidaPreventiva;}
    public void setResponsableMedidaPreventiva(int ResponsableMedidaPreventiva) {this.ResponsableMedidaPreventiva = ResponsableMedidaPreventiva;}    
    
    //  Inicializacion del bean
    public void init(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        // recuperar el id para llenar datos de la accion correctiva y el resto de las propiedades.
        int idAccion = 0;
        idAccion = Integer.parseInt(request.getParameter("id"));
        if(idAccion != 0){
            AccionCorrectiva = (Correctiva) fLectura.GetAccion(idAccion);
            //  Medidas Correctivas
            ListaMedidasCorrectivas = new HashMap<>();
            if(!((Correctiva)AccionCorrectiva).getMedidasCorrectivas().isEmpty()){
                List<Actividad> medidas = ((Correctiva)AccionCorrectiva).getMedidasCorrectivas();
                for(Actividad medida: medidas ){
                    ListaMedidasCorrectivas.put(medida.getId(), medida);
                }
            }
            //  Medidas Preventivas
            ListaMedidasPreventivas = new HashMap<>();
            if(!((Correctiva)AccionCorrectiva).getMedidasPreventivas().isEmpty()){
                List<Actividad> medidas = ((Correctiva)AccionCorrectiva).getMedidasPreventivas();
                for(Actividad medida: medidas ){
                    ListaMedidasPreventivas.put(medida.getId(), medida);
                }
            }
            
            //  Usuarios
            this.ListaUsuariosEmpresa = new HashMap<>();
            Empresa empresa = (Empresa) request.getSession().getAttribute("Empresa");
            // llenar lista de usuarios para responsables de implementacion.
            if(empresa!=null) {
                List<Usuario> tmpUsuarios = fLectura.GetUsuariosEmpresa(empresa.getId());
                for(Usuario usuario: tmpUsuarios){
                    ListaUsuariosEmpresa.put(usuario.getId(), usuario);
                }
            }
        }
    }
    
    //  Metodos
    /**
     * Agrega una nueva medida correctiva.
     * No se persiste hasta guardar los cambios.
     */
    public void agregarMedidaCorrectiva(){
        if(!DescripcionMedidaCorrectiva.isEmpty() && FechaEstimadaImplementacionMedidaCorrectiva != null && ResponsableMedidaCorrectiva != 0){
            Actividad MedidaCorrectiva  = new Actividad();
            MedidaCorrectiva.setDescripcion(DescripcionMedidaCorrectiva);
            MedidaCorrectiva.setFechaEstimadaImplementacion(FechaEstimadaImplementacionMedidaCorrectiva);
            MedidaCorrectiva.setId(getIdTemporal(TipoMedida.CORRECTIVA));
            Usuario responsable = ListaUsuariosEmpresa.get(ResponsableMedidaCorrectiva);
            MedidaCorrectiva.setResponsableImplementacion(responsable);
            ListaMedidasCorrectivas.put(MedidaCorrectiva.getId(), MedidaCorrectiva);
        }
    }
    
    /**
     * Agrega una nueva medida preventiva.
     * No se persiste hasta guardar los cambios.
     */
    public void agregarMedidaPreventiva(){
        if(!DescripcionMedidaPreventiva.isEmpty() && FechaEstimadaImplementacionMedidaPreventiva != null && ResponsableMedidaPreventiva != 0){
            Actividad MedidaPreventiva  = new Actividad();
            MedidaPreventiva.setDescripcion(DescripcionMedidaPreventiva);
            MedidaPreventiva.setFechaEstimadaImplementacion(FechaEstimadaImplementacionMedidaPreventiva);
            MedidaPreventiva.setId(getIdTemporal(TipoMedida.PREVENTIVA));
            Usuario responsable = ListaUsuariosEmpresa.get(ResponsableMedidaPreventiva);
            MedidaPreventiva.setResponsableImplementacion(responsable);
            ListaMedidasPreventivas.put(MedidaPreventiva.getId(), MedidaPreventiva);
        }
    }
    
    /**
     * Remueve la medida correctiva de la lista de medidas correctivas.
     * Agrega la medida correctiva a la lista de actividades a remover para guardar los cambios en la base de datos.
     * No se persiste hasta guardar los cambios.
     */
    public void removerMedidaCorrectiva(){
        if(ActividadesARemover == null ) ActividadesARemover = new ArrayList<>();
        ActividadesARemover.add(ListaMedidasCorrectivas.get(MedidaCorrectivaSeleccionada));
        ListaMedidasCorrectivas.remove(MedidaCorrectivaSeleccionada);
    }
    
    /**
     * Remueve la medida preventiva de la lista de medidas preventivas.
     * Agrega la medida preventiva a la lista de actividades a remover para guardar los cambios en la base de datos.
     * No se persiste hasta guardar los cambios.
     */
    public void removerMedidaPreventiva(){
        if(ActividadesARemover == null ) ActividadesARemover = new ArrayList<>();
        ActividadesARemover.add(ListaMedidasPreventivas.get(MedidaPreventivaSeleccionada));
        ListaMedidasPreventivas.remove(MedidaPreventivaSeleccionada);
    }
    
    /**
     * Persiste los cambios realizados en la accion correctiva.
     */
    public void guardarCambios(){
        //TODO: completar guardar cambios
    }
    
    /**
     * Obtiene un id temporal las actividades ingresadas previo a persistencia.
     * Recorre la lista de actividades segun el tipo indicado y devuelve un entero menor que 0.
     * @param tipo
     * @return 
     */
    private int getIdTemporal(TipoMedida tipo){
        int id = -1;
        if(tipo.equals(TipoMedida.CORRECTIVA)){
            for(int clave: ListaMedidasCorrectivas.keySet()){
                if(id>clave) id--;
            }
        }else{
            for(int clave: ListaMedidasPreventivas.keySet()){
                if(id>clave) id--;
            }
        }
        return id;
    }
    
    /**
     * Enumeracion para metodo de obtener id
     */
    private enum TipoMedida{
        CORRECTIVA,
        PREVENTIVA
    }
}
