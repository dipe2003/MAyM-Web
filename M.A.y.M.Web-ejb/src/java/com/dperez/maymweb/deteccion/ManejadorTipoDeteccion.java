/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.deteccion;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Named;
import javax.persistence.TypedQuery;

import com.dperez.maymweb.persistencia.ManejadorPersistencia;

/**
 *
 * @author Diego
 */
@Named
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ManejadorTipoDeteccion extends ManejadorPersistencia {
    
    public ManejadorTipoDeteccion(){}
    
    public int CrearTipoDeteccion(TipoDeteccion tipoDeteccion){
        try{
            em.persist(tipoDeteccion);
            return tipoDeteccion.getId();
        }catch(Exception ex){
            System.out.println("Error al crear tipoDeteccion: " + ex.getMessage());
        }
        return -1;
    }
    
    public int ActualizarTipoDeteccion(TipoDeteccion tipoDeteccion){
        try{
            em.merge(tipoDeteccion);
            return tipoDeteccion.getId();
        }catch(Exception ex){
            System.out.println("Error al actualizar tipoDeteccion: " + ex.getMessage());
        }
        return -1;
    }
    
    public int BorrarTipoDeteccion(TipoDeteccion tipoDeteccion){
        try{
            em.remove(tipoDeteccion);
            return tipoDeteccion.getId();
        }catch(Exception ex){
            System.out.println("Error al borrar tipoDeteccion: " + ex.getMessage());
        }
        return -1;
    }
    
    public List<TipoDeteccion> ListarTipoDetecciones(){
        List<TipoDeteccion> tipoDetecciones = new ArrayList<>();
        TypedQuery<TipoDeteccion> query = em.createQuery("SELECT t FROM TipoDeteccion t", TipoDeteccion.class);
        tipoDetecciones = query.getResultList();
        return tipoDetecciones;
    }
}
