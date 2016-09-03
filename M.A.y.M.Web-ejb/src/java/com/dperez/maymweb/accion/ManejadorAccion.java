/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.accion;

import com.dperez.maymweb.persistencia.ConexionDB;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;

import javax.persistence.EntityManager;

/**
 *
 * @author Diego
 */

public class ManejadorAccion {
    private static EntityManager em;
    
    public ManejadorAccion(){}
    
    public ManejadorAccion(String NombreBaseDatos){
        em = ConexionDB.getInstancia().getEntityManager(NombreBaseDatos);
    }
    
    public int CrearAccion(Accion accion){
        try{
            em.getTransaction().begin();
            em.persist(accion);
            em.getTransaction().commit();
            em.close();
            return accion.getId();
        }catch(Exception ex){
            if(em.isOpen()){
                em.getTransaction().rollback();
                em.close();
            }
            System.out.println("Error al crear accion: " + ex.getMessage());
        }
        return -1;
    }
    
    public int ActualizarAccion(Accion accion){
        try{
            em.getTransaction().begin();
            em.merge(accion);
            em.getTransaction().commit();
            em.close();
            return accion.getId();
        }catch(Exception ex){
            if(em.isOpen()){
                em.getTransaction().rollback();
                em.close();
            }
            System.out.println("Error al actualizar accion: " + ex.getMessage());
        }
        return -1;
    }
    
    public int BorrarAccion(Accion accion){
        try{
            em.getTransaction().begin();
            em.remove(accion);
            em.getTransaction().commit();
            em.close();
            return accion.getId();
        }catch(Exception ex){
            if(em.isOpen()){
                em.getTransaction().rollback();
                em.close();
            }
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
