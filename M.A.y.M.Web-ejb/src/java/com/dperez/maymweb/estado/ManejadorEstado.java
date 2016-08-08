/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.estado;

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
public class ManejadorEstado extends ManejadorPersistencia{

    public ManejadorEstado(){};
    
    public int CrearEstado(Estado estado){
        try{
            em.persist(estado);
            return estado.getId();
        }catch(Exception ex){
            System.out.println("Error al crear estado: " + ex.getMessage());
        }
        return -1;
    }
    
    public int ActualizarEstado(Estado estado){
        try{
            em.merge(estado);
            return estado.getId();
        }catch(Exception ex){
            System.out.println("Error al actualizar estado: " + ex.getMessage());
        }
        return -1;
    }
    
    public int BorrarEstado(Estado estado){
        try{
            em.remove(estado);
            return estado.getId();
        }catch(Exception ex){
            System.out.println("Error al borrar estado: " + ex.getMessage());
        }
        return -1;
    }
    
    public Estado GetEstado(int IdEstado){
        Estado estado = em.find(Estado.class, IdEstado);
        return estado;
    }
    
    public List<Estado> ListarEstados(){
        em.getTransaction();
        List<Estado> estados = new ArrayList<>();
        TypedQuery<Estado> query = em.createQuery("SELECT e FROM Estado e", Estado.class);
        estados = query.getResultList();
        em.close();
        return estados;
    }
}
