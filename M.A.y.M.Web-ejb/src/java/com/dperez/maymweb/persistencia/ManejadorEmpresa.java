/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.persistencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Diego
 */

public class ManejadorEmpresa  implements Serializable{
    private static EntityManager em;

    
    public ManejadorEmpresa(String NombreBaseDatos){
        em = ConexionDB.getInstancia().getEntityManager(NombreBaseDatos);
    }
    
    public int CrearEmpresa(Empresa empresa){
        try{
            em.getTransaction().begin();
            em.persist(empresa);
            em.getTransaction().commit();
            em.close();
            return empresa.getId();
        }catch(Exception ex){
            if(em.isOpen()){
                em.getTransaction().rollback();
                em.close();
            }
            System.out.println("Error al crear empresa: " + ex.getMessage());
        }
        return -1;
    }
    
    public int ActualizarEmpresa(Empresa empresa){
        try{
            em.getTransaction().begin();
            em.merge(empresa);
            em.getTransaction().commit();
            em.close();
            return empresa.getId();
        }catch(Exception ex){
            if(em.isOpen()){
                em.getTransaction().rollback();
                em.close();
            }
            System.out.println("Error al actualizar empresa: " + ex.getMessage());
        }
        return -1;
    }
    
    public int BorrarEmpresa(Empresa empresa){
        try{
            em.getTransaction().begin();
            em.remove(empresa);
            em.getTransaction().commit();
            em.close();
            return empresa.getId();
        }catch(Exception ex){
            if(em.isOpen()){
                em.getTransaction().rollback();
                em.close();
            }
            System.out.println("Error al borrar empresa: " + ex.getMessage());
        }
        return -1;
    }
    
    public Empresa GetEmpresa(int IdEmpresa){
        Empresa empresa = em.find(Empresa.class, IdEmpresa);
        return empresa;
    }
    
    public List<Empresa> ListarEmpresas(){
        List<Empresa> empresas = new ArrayList<>();
        TypedQuery<Empresa> query = em.createQuery("SELECT e FROM Empresa e", Empresa.class);
        empresas = query.getResultList();
        return empresas;
    }
}
