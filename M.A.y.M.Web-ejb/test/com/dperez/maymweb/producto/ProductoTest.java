/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dperez.maymweb.producto;

import com.dperez.maymweb.accion.acciones.Correctiva;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Diego
 */
public class ProductoTest {
    
    private final Correctiva correctiva = new Correctiva();
    
    public ProductoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Producto.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Producto instance = new Producto();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNombre method, of class Producto.
     */
    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        Producto instance = new Producto();
        String expResult = "";
        String result = instance.getNombre();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDatos method, of class Producto.
     */
    @Test
    public void testGetDatos() {
        System.out.println("getDatos");
        Producto instance = new Producto();
        String expResult = "";
        String result = instance.getDatos();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAccionCorrectivaConProductoAfectado method, of class Producto.
     */
    @Test
    public void testGetAccionCorrectivaConProductoAfectado() {
        System.out.println("getAccionCorrectivaConProductoAfectado");
        Producto instance = new Producto();
        Correctiva expResult = null;
        Correctiva result = instance.getAccionCorrectivaConProductoAfectado();
        assertEquals(expResult, result);
        
        instance.setAccionCorrectivaConProductoAfectado(correctiva);
        result = instance.getAccionCorrectivaConProductoAfectado();
        assertEquals(correctiva, result);
    }

    /**
     * Test of setId method, of class Producto.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int Id = 0;
        Producto instance = new Producto();
        instance.setId(Id);
    }

    /**
     * Test of setNombre method, of class Producto.
     */
    @Test
    public void testSetNombre() {
        System.out.println("setNombre");
        String Nombre = "";
        Producto instance = new Producto();
        instance.setNombre(Nombre);
    }

    /**
     * Test of setDatos method, of class Producto.
     */
    @Test
    public void testSetDatos() {
        System.out.println("setDatos");
        String Datos = "";
        Producto instance = new Producto();
        instance.setDatos(Datos);
    }

    /**
     * Test of setAccionCorrectivaConProductoAfectado method, of class Producto.
     */
    @Test
    public void testSetAccionCorrectivaConProductoAfectado() {
        System.out.println("setAccionCorrectivaConProductoAfectado");
        Correctiva AccionCorrectivaConProductoAfectado = null;
        Producto instance = new Producto();
        instance.setAccionCorrectivaConProductoAfectado(AccionCorrectivaConProductoAfectado);
        
        instance.setAccionCorrectivaConProductoAfectado(correctiva);
        Correctiva result = instance.getAccionCorrectivaConProductoAfectado();
        assertEquals(correctiva, result);        
    }
    
}
