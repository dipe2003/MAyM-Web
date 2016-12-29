/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.accion.acciones;

import com.dperez.maymweb.accion.actividad.Actividad;
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
 * @author dperez
 */
public class MejoraTest {
    
    private final Actividad actividad = new Actividad();
    
    public MejoraTest() {
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
     * Test of getActividades method, of class Mejora.
     */
    @Test
    public void testGetActividades() {
        System.out.println("getActividades");
        Mejora instance = new Mejora();
        List<Actividad> expResult = new ArrayList<>();
        expResult.add(actividad);
        instance.setActividades(expResult);
        List<Actividad> result = instance.getActividades();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setActividades method, of class Mejora.
     */
    @Test
    public void testSetActividades() {
        System.out.println("setActividades");
        List<Actividad> Actividades = new ArrayList<>();
        Actividades.add(actividad);
        Mejora instance = new Mejora();
        instance.setActividades(Actividades);
    }
    
    /**
     * Test of addActividad method, of class Mejora.
     */
    @Test
    public void testAddActividad() {
        System.out.println("addActividad");
        Mejora instance = new Mejora();
        instance.addActividad(actividad);
    }
    
    /**
     * Test of removeActividad method, of class Mejora.
     */
    @Test
    public void testRemoveActividad_ActividadMejora() {
        System.out.println("removeActividad");
        Mejora instance = new Mejora();
        instance.removeActividad(actividad);
    }
    
    /**
     * Test of removeActividad method, of class Mejora.
     */
    @Test
    public void testRemoveActividad_int() {
        System.out.println("removeActividad");
        int idActividad = 1;
        actividad.setId(idActividad);
        Mejora instance = new Mejora();
        instance.removeActividad(idActividad);
    }
}
