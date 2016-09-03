/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.area;

import com.dperez.maymweb.persistencia.ConexionDB;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;

import javax.persistence.EntityManager;

/**
 *
 * @author Diego
 */

public class ManejadorArea{
    
    private static EntityManager em;
    
    public ManejadorArea(){}
    
    public ManejadorArea(String NombreBaseDatos){
        em = ConexionDB.getInstancia().getEntityManager(NombreBaseDatos);
    }
    
    public int CrearArea(Area area){
        try{
            em.getTransaction().begin();
            em.persist(area);
            em.getTransaction().commit();
            em.close();
            return area.getId();
        }catch(Exception ex){
            if(em.isOpen()){
                em.getTransaction().rollback();
                em.close();
            }
            System.out.println("Error al crear area: " + ex.getMessage());
        }
        return -1;
    }
    
    public int ActualizarArea(Area area){
        try{
            em.getTransaction().begin();
            em.merge(area);
            em.getTransaction().commit();
            em.close();
            return area.getId();
        }catch(Exception ex){
            if(em.isOpen()){
                em.getTransaction().rollback();
                em.close();
            }
            System.out.println("Error al actualizar area: " + ex.getMessage());
        }
        return -1;
    }
    
    public int BorrarArea(Area area){
        try{
            em.getTransaction().begin();
            em.remove(area);
            em.getTransaction().commit();
            em.close();
            return area.getId();
        }catch(Exception ex){
            if(em.isOpen()){
                em.getTransaction().rollback();
                em.close();
            }
            System.out.println("Error al borrar area: " + ex.getMessage());
        }
        return -1;
    }
    
    public Area GetArea(int IdArea){
        Area area = em.find(Area.class, IdArea);
        return area;
    }
    
    public List<Area> ListarAreas(){
        List<Area> areas = new ArrayList<>();
        TypedQuery<Area> query = em.createQuery("SELECT a FROM Area a", Area.class);
        areas = query.getResultList();
        return areas;
    }
}
