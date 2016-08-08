/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dperez.maymweb.accion.medida.medidas;

import com.dperez.maymweb.accion.acciones.Preventiva;
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
public class ActividadPreventivaTest {
    
    private final Preventiva AccionPreventiva = new Preventiva();
    
    public ActividadPreventivaTest() {
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
     * Test of getAccionPreventiva method, of class ActividadPreventiva.
     */
    @Test
    public void testGetAccionPreventiva() {
        System.out.println("getAccionPreventiva");
        ActividadPreventiva instance = new ActividadPreventiva();
        Preventiva expResult = null;
        Preventiva result = instance.getAccionPreventiva();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAccionPreventiva method, of class ActividadPreventiva.
     */
    @Test
    public void testSetAccionPreventiva() {
        System.out.println("setAccionPreventiva");        
        ActividadPreventiva instance = new ActividadPreventiva();
        instance.setAccionPreventiva(AccionPreventiva);
    }
    
}
