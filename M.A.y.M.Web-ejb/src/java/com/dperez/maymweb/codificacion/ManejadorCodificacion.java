/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.codificacion;

import com.dperez.maymweb.persistencia.ConexionDB;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;

import javax.persistence.EntityManager;

/**
 *
 * @author Diego
 */

public class ManejadorCodificacion {
    private static EntityManager em;
    
    public ManejadorCodificacion(){}
    
    public ManejadorCodificacion(String NombreBaseDatos){ em = ConexionDB.getInstancia().getEntityManager(NombreBaseDatos);}
    
    public int CrearCodificacion(Codificacion codificacion){
        try{
            em.getTransaction().begin();
            em.persist(codificacion);
            em.getTransaction().commit();
            em.close();
            return codificacion.getId();
        }catch(Exception ex){
            if(em.isOpen()){
                em.getTransaction().rollback();
                em.close();
            }
            System.out.println("Error al crear codificacion: " + ex.getMessage());
        }
        return -1;
    }
    
    public int ActualizarCodificacion(Codificacion codificacion){
        try{
            em.getTransaction().begin();
            em.merge(codificacion);
            em.getTransaction().commit();
            em.close();
            return codificacion.getId();
        }catch(Exception ex){
            if(em.isOpen()){
                em.getTransaction().rollback();
                em.close();
            }
            System.out.println("Error al actualizar codificacion: " + ex.getMessage());
        }
        return -1;
    }
    
    public int BorrarCodificacion(Codificacion codificacion){
        try{
            em.getTransaction().begin();
            em.remove(codificacion);
            em.getTransaction().commit();
            em.close();
            return codificacion.getId();
        }catch(Exception ex){
            if(em.isOpen()){
                em.getTransaction().rollback();
                em.close();
            }
            System.out.println("Error al borrar codificacion: " + ex.getMessage());
        }
        return -1;
    }
    
    public Codificacion GetCodificacion(int IdCodificacion){
        Codificacion codificacion = em.find(Codificacion.class, IdCodificacion);
        return codificacion;
    }
    
    public List<Codificacion> ListarCodificaciones(){
        List<Codificacion> codificaciones = new ArrayList<>();
        TypedQuery<Codificacion> query = em.createQuery("SELECT c FROM Codificacion c", Codificacion.class);
        codificaciones = query.getResultList();
        return codificaciones;
    }
}
