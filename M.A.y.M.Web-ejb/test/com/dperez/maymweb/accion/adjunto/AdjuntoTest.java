/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dperez.maymweb.accion.adjunto;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.accion.acciones.Correctiva;
import com.dperez.maymweb.accion.acciones.Mejora;
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
public class AdjuntoTest {
    private final Accion accCorrectiva = new Correctiva();
    private final Accion accPreventiva = new Preventiva();
    private final Accion accMejora = new Mejora();
    
    public AdjuntoTest() {
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
     * Test of getId method, of class Adjunto.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Adjunto instance = new Adjunto();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTitulo method, of class Adjunto.
     */
    @Test
    public void testGetTitulo() {
        System.out.println("getTitulo");
        Adjunto instance = new Adjunto();
        String expResult = "";
        String result = instance.getTitulo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUbicacion method, of class Adjunto.
     */
    @Test
    public void testGetUbicacion() {
        System.out.println("getUbicacion");
        Adjunto instance = new Adjunto();
        String expResult = "";
        String result = instance.getUbicacion();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAccionAdjunto method, of class Adjunto.
     */
    @Test
    public void testGetAccionAdjunto() {
        System.out.println("getAccionAdjunto");
        Adjunto instance = new Adjunto();
        Accion expResult = null;
        Accion result = instance.getAccionAdjunto();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Adjunto.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int Id = 1;
        Adjunto instance = new Adjunto();        
        instance.setId(Id);
    }

    /**
     * Test of setTitulo method, of class Adjunto.
     */
    @Test
    public void testSetTitulo() {
        System.out.println("setTitulo");
        String Titulo = "";
        Adjunto instance = new Adjunto();
        instance.setTitulo(Titulo);
    }

    /**
     * Test of setUbicacion method, of class Adjunto.
     */
    @Test
    public void testSetUbicacion() {
        System.out.println("setUbicacion");
        String Ubicacion = "";
        Adjunto instance = new Adjunto();
        instance.setUbicacion(Ubicacion);
    }

    /**
     * Test of setAccionAdjunto method, of class Adjunto.
     */
    @Test
    public void testSetAccionAdjunto() {
        System.out.println("setAccionAdjunto");
        Adjunto instance = new Adjunto();
        instance.setAccionAdjunto(accCorrectiva);
        instance.setAccionAdjunto(accCorrectiva);
        instance.setAccionAdjunto(null);
        if(accCorrectiva.getAdjuntos().contains(instance))
            fail("El metodo setAccionAdjunto no es bidireccional");
    }
    
}
