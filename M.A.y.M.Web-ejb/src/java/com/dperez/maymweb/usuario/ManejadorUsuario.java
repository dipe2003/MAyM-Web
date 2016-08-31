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
    private EntityManager em;
    public ManejadorUsuario(){ em = ConexionDB.getInstancia().getEntityManager("maym_example");}
    
    public int CrearUsuario(Usuario usuario){
        try{
//            em.getTransaction();
            em.persist(usuario);
//            em.close();
            return usuario.getId();
        }catch(Exception ex){
//            em.getTransaction().rollback();
//            em.close();
            System.out.println("Error al crear usuario: " + ex.getMessage());
        }
        return -1;
    }
    
    public int ActualizarUsuario(Usuario usuario){
        try{
//            em.getTransaction();
            em.merge(usuario);
//            em.close();
            return usuario.getId();
        }catch(Exception ex){
//            em.getTransaction().rollback();
//            em.close();
            System.out.println("Error al actualizar usuario: " + ex.getMessage());
        }
        return -1;
    }
    
    public int BorrarUsuario(Usuario usuario){
        try{
//            em.getTransaction();
            em.remove(usuario);
//            em.close();
            return usuario.getId();
        }catch(Exception ex){
//            em.getTransaction().rollback();
//            em.close();
            System.out.println("Error al borrar usuario: " + ex.getMessage());
        }
        return -1;
    }
    
    public Usuario GetUsuario(int IdUsuario){
//        em.getTransaction();
        Usuario usuario = em.find(Usuario.class, IdUsuario);
//        em.close();
        return usuario;
    }
    
    public List<Usuario> ListarUsuarios(){
//        em.getTransaction();
        List<Usuario> usuarios = new ArrayList<>();
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
        if(query.getResultList()!= null){
            usuarios = query.getResultList();
        }
//        em.close();
        return usuarios;
    }
}
