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
public class ManejadorTipoDeteccion {
    private static EntityManager em;
    
    public ManejadorTipoDeteccion(){}
    
    public ManejadorTipoDeteccion(String NombreBaseDatos){ em = ConexionDB.getInstancia().getEntityManager(NombreBaseDatos);}
    
    public int CrearTipoDeteccion(TipoDeteccion tipoDeteccion){
        try{
            em.getTransaction().begin();
            em.persist(tipoDeteccion);
            em.getTransaction().commit();
            em.close();
            return tipoDeteccion.getId();
        }catch(Exception ex){
            if(em.isOpen()){
                em.getTransaction().rollback();
                em.close();
            }
            System.out.println("Error al crear tipoDeteccion: " + ex.getMessage());
        }
        return -1;
    }
    
    public int ActualizarTipoDeteccion(TipoDeteccion tipoDeteccion){
        try{
            em.getTransaction().begin();
            em.merge(tipoDeteccion);
            em.getTransaction().commit();
            em.close();
            return tipoDeteccion.getId();
        }catch(Exception ex){
            if(em.isOpen()){
                em.getTransaction().rollback();
                em.close();
            }
            System.out.println("Error al actualizar tipoDeteccion: " + ex.getMessage());
        }
        return -1;
    }
    
    public int BorrarTipoDeteccion(TipoDeteccion tipoDeteccion){
        try{
            em.getTransaction().begin();
            em.remove(tipoDeteccion);
            em.getTransaction().commit();
            em.close();
            return tipoDeteccion.getId();
        }catch(Exception ex){
            if(em.isOpen()){
                em.getTransaction().rollback();
                em.close();
            }
            System.out.println("Error al borrar tipoDeteccion: " + ex.getMessage());
        }
        return -1;
    }
    
    public TipoDeteccion GetTipoDeteccion(int IdTipoDeteccion){
        TipoDeteccion tipo = em.find(TipoDeteccion.class, IdTipoDeteccion);
        return tipo;
    }
    
    public List<TipoDeteccion> ListarTipoDetecciones(){
        List<TipoDeteccion> tipoDetecciones = new ArrayList<>();
        TypedQuery<TipoDeteccion> query = em.createQuery("SELECT t FROM TipoDeteccion t", TipoDeteccion.class);
        tipoDetecciones = query.getResultList();
        return tipoDetecciones;
    }
}
