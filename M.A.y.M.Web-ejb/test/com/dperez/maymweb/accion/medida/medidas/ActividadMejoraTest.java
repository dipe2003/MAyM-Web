/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dperez.maymweb.accion.medida.medidas;

import com.dperez.maymweb.accion.acciones.Mejora;
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
public class ActividadMejoraTest {
    
    private final Mejora AccionMejora = new Mejora();
    
    public ActividadMejoraTest() {
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
     * Test of getAccionMejora method, of class ActividadMejora.
     */
    @Test
    public void testGetAccionMejora() {
        System.out.println("getAccionMejora");
        ActividadMejora instance = new ActividadMejora();
        Mejora expResult = null;
        Mejora result = instance.getAccionMejora();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAccionMejora method, of class ActividadMejora.
     */
    @Test
    public void testSetAccionMejora() {
        System.out.println("setAccionMejora");
        ActividadMejora instance = new ActividadMejora();
        instance.setAccionMejora(AccionMejora);
    }
    
}
