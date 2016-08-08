/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.accion.acciones;

import com.dperez.maymweb.accion.medida.medidas.ActividadPreventiva;
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
public class PreventivaTest {
    
    private final ActividadPreventiva  actividad = new ActividadPreventiva();
    
    public PreventivaTest() {
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
     * Test of getActividades method, of class Preventiva.
     */
    @Test
    public void testGetActividades() {
        System.out.println("getActividades");
        Preventiva instance = new Preventiva();
        List<ActividadPreventiva> expResult = new ArrayList<>();
        expResult.add(actividad);
        instance.setActividades(expResult);
        List<ActividadPreventiva> result = instance.getActividades();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setActividades method, of class Preventiva.
     */
    @Test
    public void testSetActividades() {
        System.out.println("setActividades");
        List<ActividadPreventiva> Actividades = new ArrayList<>();
        Preventiva instance = new Preventiva();
        instance.setActividades(Actividades);
    }
    
    
    /**
     * Test of addActividad method, of class Preventiva.
     */
    @Test
    public void testAddActividad() {
        System.out.println("addActividad");
        Preventiva instance = new Preventiva();
        instance.addActividad(actividad);
    }
    
    /**
     * Test of removeActividad method, of class Preventiva.
     */
    @Test
    public void testRemoveActividad_ActividadPreventiva() {
        System.out.println("removeActividad");
        Preventiva instance = new Preventiva();
        instance.removeActividad(actividad);
    }
    
    /**
     * Test of removeActividad method, of class Preventiva.
     */
    @Test
    public void testRemoveActividad_int() {
        System.out.println("removeActividad");
        int IdActividad = 1;
        actividad.setId(IdActividad);
        Preventiva instance = new Preventiva();
        instance.removeActividad(IdActividad);
    }
    
}
