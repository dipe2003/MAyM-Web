/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.accion.medida;

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
public class ManejadorMedida extends ManejadorPersistencia{
  
    public ManejadorMedida(){};
    
    public int CrearMedida(Medida medida){
        try{
            em.persist(medida);
            return medida.getId();
        }catch(Exception ex){
            System.out.println("Error al crear medida: " + ex.getMessage());
        }
        return -1;
    }
    
    public int ActualizarMedida(Medida medida){
        try{
            em.merge(medida);
            return medida.getId();
        }catch(Exception ex){
            System.out.println("Error al actualizar medida: " + ex.getMessage());
        }
        return -1;
    }
    
    public int BorrarMedida(Medida medida){
        try{
            em.remove(medida);
            return medida.getId();
        }catch(Exception ex){
            System.out.println("Error al borrar medida: " + ex.getMessage());
        }
        return -1;
    }
    
    public Medida GetMedida(int IdMedida){
        Medida medida = em.find(Medida.class, IdMedida);
        return medida;
    }
    
    public List<Medida> ListarMedidas(){
        em.getTransaction();
        List<Medida> medidas = new ArrayList<>();
        TypedQuery<Medida> query = em.createQuery("SELECT m FROM Medida m", Medida.class);
        medidas = query.getResultList();
        em.close();
        return medidas;
    }
}
