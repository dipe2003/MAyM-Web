/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dperez.maymweb.accion.medida;

import com.dperez.maymweb.usuario.Usuario;
import java.util.Date;
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
public class MedidaTest {
    
    private final Usuario usr = new Usuario();
    
    public MedidaTest() {
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
     * Test of getId method, of class Medida.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Medida instance = new MedidaImpl();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFechaEstimadaImplementacion method, of class Medida.
     */
    @Test
    public void testGetFechaEstimadaImplementacion() {
        System.out.println("getFechaEstimadaImplementacion");
        Medida instance = new MedidaImpl();
        Date expResult = null;
        Date result = instance.getFechaEstimadaImplementacion();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFechaImplementacion method, of class Medida.
     */
    @Test
    public void testGetFechaImplementacion() {
        System.out.println("getFechaImplementacion");
        Medida instance = new MedidaImpl();
        Date expResult = null;
        Date result = instance.getFechaImplementacion();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescripcion method, of class Medida.
     */
    @Test
    public void testGetDescripcion() {
        System.out.println("getDescripcion");
        Medida instance = new MedidaImpl();
        String expResult = "";
        String result = instance.getDescripcion();
        assertEquals(expResult, result);
    }

    /**
     * Test of getResponsableImplementacion method, of class Medida.
     */
    @Test
    public void testGetResponsableImplementacion() {
        System.out.println("getResponsableImplementacion");
        Medida instance = new MedidaImpl();
        Usuario expResult = null;
        Usuario result = instance.getResponsableImplementacion();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Medida.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int Id = 1;
        Medida instance = new MedidaImpl();
        instance.setId(Id);
    }

    /**
     * Test of setFechaEstimadaImplementacion method, of class Medida.
     */
    @Test
    public void testSetFechaEstimadaImplementacion() {
        System.out.println("setFechaEstimadaImplementacion");
        Date FechaEstimadaImplementacion = new Date();
        Medida instance = new MedidaImpl();
        instance.setFechaEstimadaImplementacion(FechaEstimadaImplementacion);
    }

    /**
     * Test of setFechaImplementacion method, of class Medida.
     */
    @Test
    public void testSetFechaImplementacion() {
        System.out.println("setFechaImplementacion");
        Date FechaImplementacion = new Date();
        Medida instance = new MedidaImpl();
        instance.setFechaImplementacion(FechaImplementacion);
    }

    /**
     * Test of setDescripcion method, of class Medida.
     */
    @Test
    public void testSetDescripcion() {
        System.out.println("setDescripcion");
        String Descripcion = "Descripcion";
        Medida instance = new MedidaImpl();
        instance.setDescripcion(Descripcion);
    }

    /**
     * Test of setResponsableImplementacion method, of class Medida.
     */
    @Test
    public void testSetResponsableImplementacion() {
        System.out.println("setResponsableImplementacion");
        Medida instance = new MedidaImpl();
        instance.setResponsableImplementacion(usr);
    }

    public class MedidaImpl extends Medida {
    }
    
}
