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
public class MedidaCorrectivaTest {
    
    private final Correctiva AccionCorrectivaMedidaCorrectiva = new Correctiva();
    
    public MedidaCorrectivaTest() {
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
     * Test of getAccionCorrectivaMedidaCorrectiva method, of class MedidaCorrectiva.
     */
    @Test
    public void testGetAccionCorrectivaMedidaCorrectiva() {
        System.out.println("getAccionCorrectivaMedidaCorrectiva");
        MedidaCorrectiva instance = new MedidaCorrectiva();
        Correctiva expResult = null;
        Correctiva result = instance.getAccionCorrectivaMedidaCorrectiva();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAccionCorrectivaMedidaCorrectiva method, of class MedidaCorrectiva.
     */
    @Test
    public void testSetAccionCorrectivaMedidaCorrectiva() {
        System.out.println("setAccionCorrectivaMedidaCorrectiva");
        MedidaCorrectiva instance = new MedidaCorrectiva();
        instance.setAccionCorrectivaMedidaCorrectiva(AccionCorrectivaMedidaCorrectiva);
    }
    
}
