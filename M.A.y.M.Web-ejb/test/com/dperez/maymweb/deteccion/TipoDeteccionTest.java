/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.deteccion;

import java.util.ArrayList;
import java.util.List;
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
public class TipoDeteccionTest {
    
    private final Deteccion deteccion  = new Deteccion();
    
    public TipoDeteccionTest() {
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
     * Test of getId method, of class TipoDeteccion.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        TipoDeteccion instance = new TipoDeteccion();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getNombre method, of class TipoDeteccion.
     */
    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        TipoDeteccion instance = new TipoDeteccion();
        String expResult = "";
        String result = instance.getNombre();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getDescripcion method, of class TipoDeteccion.
     */
    @Test
    public void testGetDescripcion() {
        System.out.println("getDescripcion");
        TipoDeteccion instance = new TipoDeteccion();
        String expResult = "";
        String result = instance.getDescripcion();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getDetecciones method, of class TipoDeteccion.
     */
    @Test
    public void testGetDetecciones() {
        System.out.println("getDetecciones");
        TipoDeteccion instance = new TipoDeteccion();
        List<Deteccion> expResult = new ArrayList<>();
        List<Deteccion> result = instance.getDetecciones();
        assertEquals(expResult, result);
        
        expResult.add(deteccion);
        instance.setDetecciones(expResult);
        result = instance.getDetecciones();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setId method, of class TipoDeteccion.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int Id = 0;
        TipoDeteccion instance = new TipoDeteccion();
        instance.setId(Id);
    }
    
    /**
     * Test of setNombre method, of class TipoDeteccion.
     */
    @Test
    public void testSetNombre() {
        System.out.println("setNombre");
        String Nombre = "";
        TipoDeteccion instance = new TipoDeteccion();
        instance.setNombre(Nombre);
    }
    
    /**
     * Test of setDescripcion method, of class TipoDeteccion.
     */
    @Test
    public void testSetDescripcion() {
        System.out.println("setDescripcion");
        String Descripcion = "";
        TipoDeteccion instance = new TipoDeteccion();
        instance.setDescripcion(Descripcion);
    }
    
    /**
     * Test of setDetecciones method, of class TipoDeteccion.
     */
    @Test
    public void testSetDetecciones() {
        System.out.println("setDetecciones");
        List<Deteccion> Detecciones = new ArrayList<>();
        TipoDeteccion instance = new TipoDeteccion();
        instance.setDetecciones(Detecciones);
    }
    
    /**
     * Test of addDeteccion method, of class TipoDeteccion.
     */
    @Test
    public void testAddDeteccion() {
        System.out.println("addDeteccion");
        TipoDeteccion instance = new TipoDeteccion();
        instance.addDeteccion(deteccion);
    }
    
    /**
     * Test of removeDeteccion method, of class TipoDeteccion.
     */
    @Test
    public void testRemoveDeteccion_Deteccion() {
        System.out.println("removeDeteccion");
        TipoDeteccion instance = new TipoDeteccion();
        instance.addDeteccion(deteccion);
        instance.removeDeteccion(deteccion);
    }
    
    /**
     * Test of removeDeteccion method, of class TipoDeteccion.
     */
    @Test
    public void testRemoveDeteccion_int() {
        System.out.println("removeDeteccion");
        int IdDeteccionDeTipo = 1;
        deteccion.setId(IdDeteccionDeTipo);
        TipoDeteccion instance = new TipoDeteccion();
        instance.addDeteccion(deteccion);
        instance.removeDeteccion(IdDeteccionDeTipo);
    }
    
}
