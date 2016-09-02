/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.deteccion;

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
public class ManejadorDeteccion {
    private static EntityManager em;
    
    public ManejadorDeteccion(){}
    
    public ManejadorDeteccion(String NombreBaseDatos){ em = ConexionDB.getInstancia().getEntityManager(NombreBaseDatos);}
    
    public int CrearDeteccion(Deteccion deteccion){
        try{
            em.getTransaction().begin();
            em.persist(deteccion);
            em.getTransaction().commit();
            em.close();
            return deteccion.getId();
        }catch(Exception ex){
            if(em.isOpen()){
                em.getTransaction().rollback();
                em.close();
            }
            System.out.println("Error al crear deteccion: " + ex.getMessage());
        }
        return -1;
    }
    
    public int ActualizarDeteccion(Deteccion deteccion){
        try{
            em.getTransaction().begin();
            em.merge(deteccion);
            em.getTransaction().commit();
            em.close();
            return deteccion.getId();
        }catch(Exception ex){
            if(em.isOpen()){
                em.getTransaction().rollback();
                em.close();
            }
            System.out.println("Error al actualizar deteccion: " + ex.getMessage());
        }
        return -1;
    }
    
    public int BorrarDeteccion(Deteccion deteccion){
        try{
            em.getTransaction().begin();
            em.remove(deteccion);
            em.getTransaction().commit();
            em.close();
            return deteccion.getId();
        }catch(Exception ex){
            if(em.isOpen()){
                em.getTransaction().rollback();
                em.close();
            }
            System.out.println("Error al borrar deteccion: " + ex.getMessage());
        }
        return -1;
    }
    
    public Deteccion GetDeteccion(int IdDeteccion){
        Deteccion deteccion = em.find(Deteccion.class, IdDeteccion);
        return deteccion;
    }
    
    public List<Deteccion> ListarDetecciones(){
        List<Deteccion> detecciones = new ArrayList<>();
        TypedQuery<Deteccion> query = em.createQuery("SELECT d FROM Deteccion d", Deteccion.class);
        detecciones = query.getResultList();
        return detecciones;
    }
}
