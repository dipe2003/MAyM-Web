/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dperez.maymweb.area;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.accion.acciones.Correctiva;
import com.dperez.maymweb.accion.acciones.Mejora;
import com.dperez.maymweb.fortaleza.Fortaleza;
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
public class AreaTest {
    
    private final Fortaleza fortaleza = new Fortaleza();
    private final Accion ACorrectiva = new Correctiva();
    private final Accion AMejora = new Mejora();    
    
    public AreaTest() {
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
     * Test of getId method, of class Area.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Area instance = new Area();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNombre method, of class Area.
     */
    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        Area instance = new Area();
        String expResult = "";
        String result = instance.getNombre();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCorreo method, of class Area.
     */
    @Test
    public void testGetCorreo() {
        System.out.println("getCorreo");
        Area instance = new Area();
        String expResult = "";
        String result = instance.getCorreo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAccionesEnAreaSector method, of class Area.
     */
    @Test
    public void testGetAccionesEnAreaSector() {
        System.out.println("getAccionesEnAreaSector");
        Area instance = new Area();
        List<Accion> expResult = new ArrayList<>();
        expResult.add(AMejora);
        expResult.add(ACorrectiva);
        instance.setAccionesEnAreaSector(expResult);
        List<Accion> result = instance.getAccionesEnAreaSector();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFortalezasEnAreaSector method, of class Area.
     */
    @Test
    public void testGetFortalezasEnAreaSector() {
        System.out.println("getFortalezasEnAreaSector");
        Area instance = new Area();
        List<Fortaleza> expResult = new ArrayList<>();
        List<Fortaleza> result = instance.getFortalezasEnAreaSector();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Area.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int Id = 0;
        Area instance = new Area();
        instance.setId(Id);
    }

    /**
     * Test of setNombre method, of class Area.
     */
    @Test
    public void testSetNombre() {
        System.out.println("setNombre");
        String Nombre = "";
        Area instance = new Area();
        instance.setNombre(Nombre);
    }

    /**
     * Test of setCorreo method, of class Area.
     */
    @Test
    public void testSetCorreo() {
        System.out.println("setCorreo");
        String Correo = "";
        Area instance = new Area();
        instance.setCorreo(Correo);
    }

    /**
     * Test of setAccionesEnAreaSector method, of class Area.
     */
    @Test
    public void testSetAccionesEnAreaSector() {
        System.out.println("setAccionesEnAreaSector");
        List<Accion> AccionesEnAreaSector = new ArrayList<>();
        AccionesEnAreaSector.add(AMejora);
        AccionesEnAreaSector.add(ACorrectiva); 
        Area instance = new Area();
        instance.setAccionesEnAreaSector(AccionesEnAreaSector);
    }

    /**
     * Test of setFortalezasEnAreaSector method, of class Area.
     */
    @Test
    public void testSetFortalezasEnAreaSector() {
        System.out.println("setFortalezasEnAreaSector");
        List<Fortaleza> FortalezasEnAreaSector = new ArrayList<>();
        FortalezasEnAreaSector.add(fortaleza);
        Area instance = new Area();
        instance.setFortalezasEnAreaSector(FortalezasEnAreaSector);
    }

    /**
     * Test of addAccionEnAreaSector method, of class Area.
     */
    @Test
    public void testAddAccionEnAreaSector() {
        System.out.println("addAccionEnAreaSector");
        Area instance = new Area();
        instance.addAccionEnAreaSector(ACorrectiva);
        instance.addAccionEnAreaSector(AMejora);
    }

    /**
     * Test of removeAccionEnAreaSector method, of class Area.
     */
    @Test
    public void testRemoveAccionEnAreaSector_Accion() {
        System.out.println("removeAccionEnAreaSector");
        List<Accion> AccionesEnAreaSector = new ArrayList<>();
        AccionesEnAreaSector.add(AMejora);
        AccionesEnAreaSector.add(ACorrectiva);
        Area instance = new Area();
        instance.setAccionesEnAreaSector(AccionesEnAreaSector);
        instance.removeAccionEnAreaSector(AMejora);
        instance.removeAccionEnAreaSector(ACorrectiva);
    }

    /**
     * Test of removeAccionEnAreaSector method, of class Area.
     */
    @Test
    public void testRemoveAccionEnAreaSector_int() {
        System.out.println("removeAccionEnAreaSector");
        int IdAccionEnAreaSector = 1;
        ACorrectiva.setId(IdAccionEnAreaSector);
        Area instance = new Area();
        instance.addAccionEnAreaSector(ACorrectiva);
        instance.addAccionEnAreaSector(AMejora);
        instance.removeAccionEnAreaSector(IdAccionEnAreaSector);
    }

    /**
     * Test of addFortalezaEnAreaSector method, of class Area.
     */
    @Test
    public void testAddFortalezaEnAreaSector() {
        System.out.println("addFortalezaEnAreaSector");
        Area instance = new Area();
        instance.addFortalezaEnAreaSector(fortaleza);
    }

    /**
     * Test of removeFortalezaEnAreaSector method, of class Area.
     */
    @Test
    public void testRemoveFortalezaEnAreaSector_Fortaleza() {
        System.out.println("removeFortalezaEnAreaSector");
        Area instance = new Area();
        instance.addFortalezaEnAreaSector(fortaleza);
        instance.removeFortalezaEnAreaSector(fortaleza);
    }

    /**
     * Test of removeFortalezaEnAreaSector method, of class Area.
     */
    @Test
    public void testRemoveFortalezaEnAreaSector_int() {
        System.out.println("removeFortalezaEnAreaSector");
        int IdFortalezaEnAreaSector = 1;
        fortaleza.setId(IdFortalezaEnAreaSector);
        Area instance = new Area();
        instance.removeFortalezaEnAreaSector(IdFortalezaEnAreaSector);
    }
    
}
