/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.producto;

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
public class ManejadorProducto {
    private EntityManager em;
    public ManejadorProducto(){ em = ConexionDB.getInstancia().getEntityManager("maym_example");}
    
    public int CrearProducto(Producto producto){
        try{
            em.getTransaction();
            em.persist(producto);
            em.close();
            return producto.getId();
        }catch(Exception ex){
            em.getTransaction().rollback();
            em.close();
            System.out.println("Error al crear producto: " + ex.getMessage());
        }
        return -1;
    }
    
    public int ActualizarProducto(Producto producto){
        try{
            em.getTransaction();
            em.merge(producto);
            em.close();
            return producto.getId();
        }catch(Exception ex){
            em.getTransaction().rollback();
            em.close();
            System.out.println("Error al actualizar producto: " + ex.getMessage());
        }
        return -1;
    }
    
    public int BorrarProducto(Producto producto){
        try{
            em.getTransaction();
            em.remove(producto);
            em.close();
            return producto.getId();
        }catch(Exception ex){
            em.getTransaction().rollback();
            em.close();
            System.out.println("Error al borrar producto: " + ex.getMessage());
        }
        return -1;
    }
    
    public Producto GetProducto(int IdProducto){
        em.getTransaction();
        Producto producto = em.find(Producto.class, IdProducto);
        em.close();
        return producto;
    }
    
    public List<Producto> ListarProductos(){
        em.getTransaction();
        List<Producto> productos = new ArrayList<>();
        TypedQuery<Producto> query = em.createQuery("SELECT p FROM Producto p", Producto.class);
        productos = query.getResultList();
        em.close();
        return productos;
    }
}
