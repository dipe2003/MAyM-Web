/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.codificacion;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.accion.acciones.Correctiva;
import com.dperez.maymweb.accion.acciones.Mejora;
import com.dperez.maymweb.accion.acciones.Preventiva;
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
public class CodificacionTest {
    
    private final Accion ACorrectiva = new Correctiva();
    private final Accion APreventiva = new Preventiva();
    private final Accion AMejora = new Mejora();
    
    public CodificacionTest() {
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
     * Test of getId method, of class Codificacion.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Codificacion instance = new Codificacion();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getNombre method, of class Codificacion.
     */
    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        Codificacion instance = new Codificacion();
        String expResult = "";
        String result = instance.getNombre();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getAccionesConCodificacion method, of class Codificacion.
     */
    @Test
    public void testGetAccionesConCodificacion() {
        System.out.println("getAccionesConCodificacion");
        Codificacion instance = new Codificacion();
        List<Accion> expResult = new ArrayList<>();
        List<Accion> result = instance.getAccionesConCodificacion();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setId method, of class Codificacion.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int Id = 0;
        Codificacion instance = new Codificacion();
        instance.setId(Id);
    }
    
    /**
     * Test of setNombre method, of class Codificacion.
     */
    @Test
    public void testSetNombre() {
        System.out.println("setNombre");
        String Nombre = "Nombre";
        Codificacion instance = new Codificacion();
        instance.setNombre(Nombre);
    }
    
    /**
     * Test of setAccionesConCodificacion method, of class Codificacion.
     */
    @Test
    public void testSetAccionesConCodificacion() {
        System.out.println("setAccionesConCodificacion");
        List<Accion> AccionesConCodificacion = new ArrayList<>();
        AccionesConCodificacion.add(AMejora);
        AccionesConCodificacion.add(ACorrectiva);
        AccionesConCodificacion.add(APreventiva);        
        Codificacion instance = new Codificacion();
        instance.setAccionesConCodificacion(AccionesConCodificacion);
    }
    
    /**
     * Test of addAccionCodificacion method, of class Codificacion.
     */
    @Test
    public void testAddAccionCodificacion() {
        System.out.println("addAccionCodificacion");
        Codificacion instance = new Codificacion();
        instance.addAccionCodificacion(AMejora);
        instance.addAccionCodificacion(ACorrectiva);
        instance.addAccionCodificacion(APreventiva);
    }
    
    /**
     * Test of removeAccionCodificacion method, of class Codificacion.
     */
    @Test
    public void testRemoveAccionCodificacion_Accion() {
        System.out.println("removeAccionCodificacion");
        Codificacion instance = new Codificacion();
        instance.addAccionCodificacion(AMejora);
        instance.addAccionCodificacion(ACorrectiva);
        instance.addAccionCodificacion(APreventiva);
        instance.removeAccionCodificacion(AMejora);
        instance.removeAccionCodificacion(ACorrectiva);
        instance.removeAccionCodificacion(APreventiva);
    }
    
    /**
     * Test of removeAccionCodificacion method, of class Codificacion.
     */
    @Test
    public void testRemoveAccionCodificacion_int() {
        System.out.println("removeAccionCodificacion");
        int IdAccionCodificacion = 1;
        Codificacion instance = new Codificacion();
        ACorrectiva.setId(0);
        APreventiva.setId(1);
        AMejora.setId(2);
        instance.addAccionCodificacion(AMejora);
        instance.addAccionCodificacion(ACorrectiva);
        instance.addAccionCodificacion(APreventiva);
        instance.removeAccionCodificacion(IdAccionCodificacion);
    }
    
}
