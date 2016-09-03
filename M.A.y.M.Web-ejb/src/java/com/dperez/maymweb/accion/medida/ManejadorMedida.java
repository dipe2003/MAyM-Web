/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.accion.medida;

import com.dperez.maymweb.accion.medida.medidas.ActividadMejora;
import com.dperez.maymweb.accion.medida.medidas.ActividadPreventiva;
import com.dperez.maymweb.accion.medida.medidas.MedidaCorrectiva;
import com.dperez.maymweb.accion.medida.medidas.MedidaPreventiva;
import com.dperez.maymweb.persistencia.ConexionDB;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;

import javax.persistence.EntityManager;

/**
 *
 * @author Diego
 */

public class ManejadorMedida{
    private static EntityManager em;
    
    public ManejadorMedida(){}
    
    public ManejadorMedida(String NombreBaseDatos){em =  ConexionDB.getInstancia().getEntityManager(NombreBaseDatos);};
    
    public int CrearMedida(Medida medida){
        try{
            em.persist(medida);
            return medida.getId();
        }catch(Exception ex){
            if(em.isOpen()){
                em.getTransaction().rollback();
                em.close();
            }
            System.out.println("Error al crear medida: " + ex.getMessage());
        }
        return -1;
    }
    
    public int ActualizarMedida(Medida medida){
        try{
            em.merge(medida);
            return medida.getId();
        }catch(Exception ex){
            if(em.isOpen()){
                em.getTransaction().rollback();
                em.close();
            }
            System.out.println("Error al actualizar medida: " + ex.getMessage());
        }
        return -1;
    }
    
    public int BorrarMedida(Medida medida){
        try{
            em.remove(medida);
            return medida.getId();
        }catch(Exception ex){
            if(em.isOpen()){
                em.getTransaction().rollback();
                em.close();
            }
            System.out.println("Error al borrar medida: " + ex.getMessage());
        }
        return -1;
    }
    
    public Medida GetMedida(int IdMedida){
        Medida medida = em.find(Medida.class, IdMedida);
        return medida;
    }
    
    public List<Medida> ListarMedidas(){
        List<Medida> medidas = new ArrayList<>();
        TypedQuery<Medida> query = em.createQuery("SELECT m FROM Medida m", Medida.class);
        medidas = query.getResultList();
        return medidas;
    }
    
    /*
    Medidas
    */
    
    public MedidaCorrectiva GetMedidaCorrectiva(int IdMedidaCorrectiva){
        MedidaCorrectiva medida = em.find(MedidaCorrectiva.class, IdMedidaCorrectiva);
        return medida;
    }
    public MedidaPreventiva GetMedidaPreventiva(int IdMedidaPreventiva){
        MedidaPreventiva medida = em.find(MedidaPreventiva.class, IdMedidaPreventiva);
        return medida;
    }
    public ActividadMejora GetActividadMejora(int IdActividadMejora){
        ActividadMejora medida = em.find(ActividadMejora.class, IdActividadMejora);
        return medida;
    }
    public ActividadPreventiva GetActividadPreventiva(int IdActividadPreventiva){
        ActividadPreventiva medida = em.find(ActividadPreventiva.class, IdActividadPreventiva);
        return medida;
    }
}
