/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dperez.maymweb.usuario;

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
public class CredencialTest {
    
    public CredencialTest() {
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
     * Test of getId method, of class Credencial.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Credencial instance = new Credencial();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPassword method, of class Credencial.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        Credencial instance = new Credencial();
        String expResult = "";
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    /**
     * Test of getKey method, of class Credencial.
     */
    @Test
    public void testGetKey() {
        System.out.println("getKey");
        Credencial instance = new Credencial();
        String expResult = "";
        String result = instance.getSaltKey();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsuarioCredencial method, of class Credencial.
     */
    @Test
    public void testGetUsuarioCredencial() {
        System.out.println("getUsuarioCredencial");
        Credencial instance = new Credencial();
        Usuario expResult = null;
        Usuario result = instance.getUsuarioCredencial();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Credencial.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int Id = 0;
        Credencial instance = new Credencial();
        instance.setId(Id);
    }

    /**
     * Test of setPassword method, of class Credencial.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String Password = "";
        Credencial instance = new Credencial();
        instance.setPassword(Password);
    }

    /**
     * Test of setKey method, of class Credencial.
     */
    @Test
    public void testSetKey() {
        System.out.println("setKey");
        String Key = "";
        Credencial instance = new Credencial();
        instance.setSaltKey(Key);
    }

    /**
     * Test of setUsuarioCredencial method, of class Credencial.
     */
    @Test
    public void testSetUsuarioCredencial() {
        System.out.println("setUsuarioCredencial");
        Usuario UsuarioCredencial = null;
        Credencial instance = new Credencial();
        instance.setUsuarioCredencial(UsuarioCredencial);
    }
    
}
