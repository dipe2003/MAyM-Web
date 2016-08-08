/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.codificacion;

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
public class ManejadorCodificacion extends ManejadorPersistencia {
    
    public ManejadorCodificacion(){}
    
    public int CrearCodificacion(Codificacion codificacion){
        try{
            em.persist(codificacion);
            return codificacion.getId();
        }catch(Exception ex){
            System.out.println("Error al crear codificacion: " + ex.getMessage());
        }
        return -1;
    }
    
    public int ActualizarCodificacion(Codificacion codificacion){
        try{
            em.merge(codificacion);
            return codificacion.getId();
        }catch(Exception ex){
            System.out.println("Error al actualizar codificacion: " + ex.getMessage());
        }
        return -1;
    }
    
    public int BorrarCodificacion(Codificacion codificacion){
        try{
            em.remove(codificacion);
            return codificacion.getId();
        }catch(Exception ex){
            System.out.println("Error al borrar codificacion: " + ex.getMessage());
        }
        return -1;
    }
    
    public List<Codificacion> ListarCodificaciones(){
        List<Codificacion> codificaciones = new ArrayList<>();
        TypedQuery<Codificacion> query = em.createQuery("SELECT c FROM Codificacion c", Codificacion.class);
        codificaciones = query.getResultList();
        return codificaciones;
    }
}
