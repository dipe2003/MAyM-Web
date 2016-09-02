/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.fortaleza;

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
public class ManejadorFortaleza {
    private static EntityManager em;
    
    public ManejadorFortaleza(){}
    
    public ManejadorFortaleza(String NombreBaseDatos){em =  ConexionDB.getInstancia().getEntityManager(NombreBaseDatos);}
    
    public int CrearFortaleza(Fortaleza fortaleza){
        try{
            em.getTransaction().begin();
            em.persist(fortaleza);
            em.getTransaction().commit();
            em.close();
            return fortaleza.getId();
        }catch(Exception ex){
            if(em.isOpen()){
                em.getTransaction().rollback();
                em.close();
            }
            System.out.println("Error al crear fortaleza: " + ex.getMessage());
        }
        return -1;
    }
    
    public int ActualizarFortaleza(Fortaleza fortaleza){
        try{
            em.getTransaction().begin();
            em.merge(fortaleza);
            em.getTransaction().commit();
            em.close();
            return fortaleza.getId();
        }catch(Exception ex){
            if(em.isOpen()){
                em.getTransaction().rollback();
                em.close();
            }
            System.out.println("Error al actualizar fortaleza: " + ex.getMessage());
        }
        return -1;
    }
    
    public int BorrarFortaleza(Fortaleza fortaleza){
        try{
            em.getTransaction().begin();
            em.remove(fortaleza);
            em.getTransaction().commit();
            em.close();
            return fortaleza.getId();
        }catch(Exception ex){
            if(em.isOpen()){
                em.getTransaction().rollback();
                em.close();
            }
            System.out.println("Error al borrar fortaleza: " + ex.getMessage());
        }
        return -1;
    }
    
    public Fortaleza GetFortaleza(int IdFortaleza){
        Fortaleza fortaleza = em.find(Fortaleza.class, IdFortaleza);
        return fortaleza;
    }
    
    public List<Fortaleza> ListarFortalezas(){
        List<Fortaleza> fortalezas = new ArrayList<>();
        TypedQuery<Fortaleza> query = em.createQuery("SELECT f FROM Fortaleza f", Fortaleza.class);
        fortalezas = query.getResultList();
        return fortalezas;
    }
}
