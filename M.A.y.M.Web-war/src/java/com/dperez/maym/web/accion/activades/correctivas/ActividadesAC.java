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
    
    private Map<Integer, String> ListaUsuariosEmpresa;
    
    private Map<Integer, Actividad> ListaMedidasCorrectivas;
    private int MedidaCorrectivaSeleccionada;
    
    private Map<Integer, Actividad> ListaMedidasPreventivas;
    private int MedidaPreventivaSeleccionada;
    
    private Date FechaEstimadaImplementacionMedidaCorrectiva;
    private Date FechaImplementacionMedidaCorrectiva;
    private String DescripcionMedidaCorrectiva;
    private int ResponsableMedidaCorrectiva;
    
    private Date FechaEstimadaImplementacionMedidaPreventiva;
    private Date FechaImplementacionMedidaPreventiva;
    private String DescripcionMedidaPreventiva;
    private int ResponsableMedidaPreventiva;
    
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
                    ListaUsuariosEmpresa.put(usuario.getId(), usuario.GetNombreCompleto());
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
        //TODO: completar agregar medida correctiva
    }
    
    /**
     * Agrega una nueva medida preventiva.
     * No se persiste hasta guardar los cambios.
     */
    public void agregarMedidaPreventiva(){
        //TODO: completar agregar medida preventiva
    }
    
    /**
     * Remueve la medida correctiva.
     * No se persiste hasta guardar los cambios.
     */
    public void removerMedidaCorrectiva(){
        //TODO: completar remvoer medida correctiva
    }
    
    /**
     * Remueve la medida preventiva.
     * No se persiste hasta guardar los cambios.
     */
    public void removerMedidaPreventiva(){
        //TODO: completar remvoer medida preventiva
    }
    
    /**
     * Persiste los cambios realizados en la accion correctiva.
     */
    public void guardarCambios(){
        //TODO: completar guardar cambios
    }
}
