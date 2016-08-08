/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dperez.maymweb.persistencia;

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
public class EmpresaTest {
    
    private final String nombreEmpresa = "Inaler S.A.";
    
    public EmpresaTest() {
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
     * Test of getId method, of class Empresa.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Empresa instance = new Empresa();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNombreEmpresa method, of class Empresa.
     */
    @Test
    public void testGetNombreEmpresa() {
        System.out.println("getNombreEmpresa");
        Empresa instance = new Empresa();
        String expResult = "";
        String result = instance.getNombreEmpresa();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBaseDatos method, of class Empresa.
     */
    @Test
    public void testGetBaseDatos() {
        System.out.println("getBaseDatos");
        Empresa instance = new Empresa(nombreEmpresa);
        String expResult = "inaler_s_a_";
        String result = instance.getBaseDatos();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Empresa.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int Id = 0;
        Empresa instance = new Empresa();
        instance.setId(Id);
    }

    /**
     * Test of setNombreEmpresa method, of class Empresa.
     */
    @Test
    public void testSetNombreEmpresa() {
        System.out.println("setNombreEmpresa");
        String NombreEmpresa = "";
        Empresa instance = new Empresa();
        instance.setNombreEmpresa(NombreEmpresa);
    }

    /**
     * Test of setBaseDatos method, of class Empresa.
     */
    @Test
    public void testSetBaseDatos() {
        System.out.println("setBaseDatos");
        String BaseDatos = "";
        Empresa instance = new Empresa();
        instance.setBaseDatos(BaseDatos);
    }
    
}
