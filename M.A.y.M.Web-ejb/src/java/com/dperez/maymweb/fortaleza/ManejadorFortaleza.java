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
    private EntityManager em;
    
    public ManejadorFortaleza(){em =  ConexionDB.getInstancia().getEntityManager("maym_example");}
    
    public int CrearFortaleza(Fortaleza fortaleza){
        try{
            em.persist(fortaleza);
            return fortaleza.getId();
        }catch(Exception ex){
            System.out.println("Error al crear fortaleza: " + ex.getMessage());
        }
        return -1;
    }
    
    public int ActualizarFortaleza(Fortaleza fortaleza){
        try{
            em.merge(fortaleza);
            return fortaleza.getId();
        }catch(Exception ex){
            System.out.println("Error al actualizar fortaleza: " + ex.getMessage());
        }
        return -1;
    }
    
    public int BorrarFortaleza(Fortaleza fortaleza){
        try{
            em.remove(fortaleza);
            return fortaleza.getId();
        }catch(Exception ex){
            System.out.println("Error al borrar fortaleza: " + ex.getMessage());
        }
        return -1;
    }
    
    public Fortaleza GetFortaleza(int IdFortaleza){
        Fortaleza fortaleza = em.find(Fortaleza.class, IdFortaleza);
        return fortaleza;
    }
    
    public List<Fortaleza> ListarFortalezas(){
        em.getTransaction();
        List<Fortaleza> fortalezas = new ArrayList<>();
        TypedQuery<Fortaleza> query = em.createQuery("SELECT f FROM Fortaleza f", Fortaleza.class);
        fortalezas = query.getResultList();
        em.close();
        return fortalezas;
    }
}
