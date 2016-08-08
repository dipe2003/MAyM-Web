/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.accion;

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
public class ManejadorAccion extends ManejadorPersistencia {
    
    public int CrearAccion(Accion accion){
        try{
            em.persist(accion);
            return accion.getId();
        }catch(Exception ex){
            System.out.println("Error al crear accion: " + ex.getMessage());
        }
        return -1;
    }
    
    public int ActualizarAccion(Accion accion){
        try{
            em.merge(accion);
            return accion.getId();
        }catch(Exception ex){
            System.out.println("Error al actualizar accion: " + ex.getMessage());
        }
        return -1;
    }
    
    public int BorrarAccion(Accion accion){
        try{
            em.remove(accion);
            return accion.getId();
        }catch(Exception ex){
            System.out.println("Error al borrar accion: " + ex.getMessage());
        }
        return -1;
    }
    
    public List<Accion> ListarAcciones(){
        List<Accion> acciones = new ArrayList<>();
        TypedQuery<Accion> query = em.createQuery("SELECT a FROM Accion a", Accion.class);
        acciones = query.getResultList();
        return acciones;
    }
}
