/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.accion.adjunto;

import com.dperez.maymweb.persistencia.ConexionDB;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;

import javax.persistence.EntityManager;

/**
 *
 * @author Diego
 */

public class ManejadorAdjunto {
    private static EntityManager em;
    
    public ManejadorAdjunto(){}
    
    public ManejadorAdjunto(String NombreBaseDatos){ em = ConexionDB.getInstancia().getEntityManager(NombreBaseDatos);};
    
    public int CrearAdjunto(Adjunto adjunto){
        try{
            em.getTransaction().begin();
            em.persist(adjunto);
            em.getTransaction().commit();
            em.close();
            return adjunto.getId();
        }catch(Exception ex){
            if(em.isOpen()){
                em.getTransaction().rollback();
                em.close();
            }
            System.out.println("Error al crear adjunto: " + ex.getMessage());
        }
        return -1;
    }
    
    public int ActualizarAdjunto(Adjunto adjunto){
        try{
            em.getTransaction().begin();
            em.merge(adjunto);
            em.getTransaction().commit();
            em.close();
            return adjunto.getId();
        }catch(Exception ex){
            if(em.isOpen()){
                em.getTransaction().rollback();
                em.close();
            }
            System.out.println("Error al actualizar adjunto: " + ex.getMessage());
        }
        return -1;
    }
    
    public int BorrarAdjunto(Adjunto adjunto){
        try{
            em.getTransaction().begin();
            em.remove(adjunto);
            em.getTransaction().commit();
            em.close();
            return adjunto.getId();
        }catch(Exception ex){
            if(em.isOpen()){
                em.getTransaction().rollback();
                em.close();
            }
            System.out.println("Error al borrar adjunto: " + ex.getMessage());
        }
        return -1;
    }
    
    public Adjunto GetAdjunto(int IdAdjunto){
        Adjunto adjunto = em.find(Adjunto.class, IdAdjunto);
        return adjunto;
    }
    
    public List<Adjunto> ListarAdjuntos(){
        List<Adjunto> adjuntos = new ArrayList<>();
        TypedQuery<Adjunto> query = em.createQuery("SELECT a FROM Adjunto a", Adjunto.class);
        adjuntos = query.getResultList();
        return adjuntos;
    }
}
