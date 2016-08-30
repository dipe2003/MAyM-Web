/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.accion.adjunto;

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
public class ManejadorAdjunto {
    private EntityManager em;
    
    public ManejadorAdjunto(){ em = ConexionDB.getInstancia().getEntityManager("maym_example");};
    
    public int CrearAdjunto(Adjunto adjunto){
        try{
            em.persist(adjunto);
            return adjunto.getId();
        }catch(Exception ex){
            System.out.println("Error al crear adjunto: " + ex.getMessage());
        }
        return -1;
    }
    
    public int ActualizarAdjunto(Adjunto adjunto){
        try{
            em.merge(adjunto);
            return adjunto.getId();
        }catch(Exception ex){
            System.out.println("Error al actualizar adjunto: " + ex.getMessage());
        }
        return -1;
    }
    
    public int BorrarAdjunto(Adjunto adjunto){
        try{
            em.remove(adjunto);
            return adjunto.getId();
        }catch(Exception ex){
            System.out.println("Error al borrar adjunto: " + ex.getMessage());
        }
        return -1;
    }
    
    public Adjunto GetAdjunto(int IdAdjunto){
        Adjunto adjunto = em.find(Adjunto.class, IdAdjunto);
        return adjunto;
    }
    
    public List<Adjunto> ListarAdjuntos(){
        em.getTransaction();
        List<Adjunto> adjuntos = new ArrayList<>();
        TypedQuery<Adjunto> query = em.createQuery("SELECT a FROM Adjunto a", Adjunto.class);
        adjuntos = query.getResultList();
        em.close();
        return adjuntos;
    }
}
