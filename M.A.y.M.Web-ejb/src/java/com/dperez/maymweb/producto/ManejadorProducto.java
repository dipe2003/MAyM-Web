/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.producto;

import com.dperez.maymweb.persistencia.ConexionDB;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;

import javax.persistence.EntityManager;

/**
 *
 * @author Diego
 */

public class ManejadorProducto {
    private static EntityManager em;
    
    public ManejadorProducto(){}
    
    public ManejadorProducto(String NombreBaseDatos){ em = ConexionDB.getInstancia().getEntityManager(NombreBaseDatos);}
    
    public int CrearProducto(Producto producto){
        try{
            em.getTransaction();
            em.persist(producto);
            em.close();
            return producto.getId();
        }catch(Exception ex){
            if(em.isOpen()){
                em.getTransaction().rollback();
                em.close();
            }
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
            if(em.isOpen()){
                em.getTransaction().rollback();
                em.close();
            }
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
            if(em.isOpen()){
                em.getTransaction().rollback();
                em.close();
            }
            System.out.println("Error al borrar producto: " + ex.getMessage());
        }
        return -1;
    }
    
    public Producto GetProducto(int IdProducto){
        Producto producto = em.find(Producto.class, IdProducto);
        return producto;
    }
    
    public List<Producto> ListarProductos(){
        List<Producto> productos = new ArrayList<>();
        TypedQuery<Producto> query = em.createQuery("SELECT p FROM Producto p", Producto.class);
        productos = query.getResultList();
        return productos;
    }
}
