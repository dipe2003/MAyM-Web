/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dperez.maymweb.accion.medida.medidas;

import com.dperez.maymweb.accion.acciones.Correctiva;
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
public class MedidaPreventivaTest {
    
    private final Correctiva AccionCorrectivaMedidaPreventiva = new Correctiva();
    
    public MedidaPreventivaTest() {
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
     * Test of getAccionCorrectivaMedidaPreventiva method, of class MedidaPreventiva.
     */
    @Test
    public void testGetAccionCorrectivaMedidaPreventiva() {
        System.out.println("getAccionCorrectivaMedidaPreventiva");
        MedidaPreventiva instance = new MedidaPreventiva();
        Correctiva expResult = null;
        Correctiva result = instance.getAccionCorrectivaMedidaPreventiva();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAccionCorrectivaMedidaPreventiva method, of class MedidaPreventiva.
     */
    @Test
    public void testSetAccionCorrectivaMedidaPreventiva() {
        System.out.println("setAccionCorrectivaMedidaPreventiva");
        MedidaPreventiva instance = new MedidaPreventiva();
        instance.setAccionCorrectivaMedidaPreventiva(AccionCorrectivaMedidaPreventiva);
    }
    
}
