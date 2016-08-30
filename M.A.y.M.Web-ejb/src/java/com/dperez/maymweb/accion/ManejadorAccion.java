/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.accion;

import com.dperez.maymweb.persistencia.ConexionDB;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Named;
import javax.persistence.TypedQuery;

import javax.persistence.EntityManager;

/**
 *
 * @author Diego
 */
@Named
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ManejadorAccion {
    private EntityManager em;
    
    public ManejadorAccion(){ em = ConexionDB.getInstancia().getEntityManager("maym_example");}
    
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
    
    public Accion GetAccion(int IdAccion){
        Accion accion = em.find(Accion.class, IdAccion);
        return accion;
    }
    
    public List<Accion> ListarAcciones(){
        List<Accion> acciones = new ArrayList<>();
        TypedQuery<Accion> query = em.createQuery("SELECT a FROM Accion a", Accion.class);
        acciones = query.getResultList();
        return acciones;
    }
}
