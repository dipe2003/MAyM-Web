/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.usuario;

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
public class ManejadorUsuario {
    private static EntityManager em;
    
    public ManejadorUsuario(){}
    
    public ManejadorUsuario(String NombreBaseDatos){ em = ConexionDB.getInstancia().getEntityManager(NombreBaseDatos);}
    
    public int CrearUsuario(Usuario usuario){
        try{
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
            em.close();
            return usuario.getId();
        }catch(Exception ex){
            if(em.isOpen()){
                em.getTransaction().rollback();
                em.close();
            }
            System.out.println("Error al crear usuario: " + ex.getMessage());
        }
        return -1;
    }
    
    public int ActualizarUsuario(Usuario usuario){
        try{
            em.getTransaction().begin();
            em.merge(usuario);
            em.getTransaction().commit();
            em.close();
            return usuario.getId();
        }catch(Exception ex){
            if(em.isOpen()){
                em.getTransaction().rollback();
                em.close();
            }
            System.out.println("Error al actualizar usuario: " + ex.getMessage());
        }
        return -1;
    }
    
    public int BorrarUsuario(Usuario usuario){
        try{
            em.getTransaction().begin();
            em.remove(usuario);
            em.getTransaction().commit();
            em.close();
            return usuario.getId();
        }catch(Exception ex){
            if(em.isOpen()){
                em.getTransaction().rollback();
                em.close();
            }
            System.out.println("Error al borrar usuario: " + ex.getMessage());
        }
        return -1;
    }
    
    public Usuario GetUsuario(int IdUsuario){
        Usuario usuario = em.find(Usuario.class, IdUsuario);
        return usuario;
    }
    
    public List<Usuario> ListarUsuarios(){
        List<Usuario> usuarios = new ArrayList<>();
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
        if(query.getResultList()!= null){
            usuarios = query.getResultList();
        }
        return usuarios;
    }
}
