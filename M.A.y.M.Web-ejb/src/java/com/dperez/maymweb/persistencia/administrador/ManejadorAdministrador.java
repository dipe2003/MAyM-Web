/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.persistencia.administrador;


import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Named;
import javax.persistence.TypedQuery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Diego
 */
@Named
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ManejadorAdministrador {
    @PersistenceContext(unitName = "MAYMWEB_BDADMIN_PU")
    private EntityManager em;
    
    public ManejadorAdministrador(){}
    
    
    public int CrearAdministrador(Administrador administrador){
        try{
            em.persist(administrador);
            return administrador.getId();
        }catch(Exception ex){
            System.out.println("Error al crear administrador: " + ex.getMessage());
        }
        return -1;
    }
    
    public int ActualizarAdministrador(Administrador administrador){
        try{
            em.merge(administrador);
            return administrador.getId();
        }catch(Exception ex){
            System.out.println("Error al actualizar administrador: " + ex.getMessage());
        }
        return -1;
    }
    
    public int BorrarAdministrador(Administrador administrador){
        try{
            em.remove(administrador);
            return administrador.getId();
        }catch(Exception ex){
            System.out.println("Error al borrar administrador: " + ex.getMessage());
        }
        return -1;
    }
    
    public Administrador GetAdministrador(int IdAdministrador){
        Administrador administrador = em.find(Administrador.class, IdAdministrador);
        return administrador;
    }
    
    public List<Administrador> ListarAdministradores(){
        List<Administrador> administradores = new ArrayList<>();
        TypedQuery<Administrador> query = em.createQuery("SELECT a FROM Administrador a", Administrador.class);
        if(query.getResultList()!= null){
            administradores = query.getResultList();
        }
        return administradores;
    }
}
