/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dperez.maymweb.deteccion;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.accion.acciones.Correctiva;
import com.dperez.maymweb.accion.acciones.Mejora;
import com.dperez.maymweb.accion.acciones.Preventiva;
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
public class DeteccionTest {
    
    private final Fortaleza fortaleza = new Fortaleza();
    private final Accion ACorrectiva = new Correctiva();
    private final Accion APreventiva = new Preventiva();
    private final Accion AMejora = new Mejora();
    private final EnumTipoDeteccion tipo = EnumTipoDeteccion.INTERNA;
    
    public DeteccionTest() {
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
     * Test of getId method, of class TipoDeteccion.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Deteccion instance = new Deteccion();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNombre method, of class TipoDeteccion.
     */
    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        Deteccion instance = new Deteccion();
        String expResult = "";
        String result = instance.getNombre();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTipo method, of class TipoDeteccion.
     */
    @Test
    public void testGetTipo() {
        System.out.println("getTipo");
        Deteccion instance = new Deteccion();
        EnumTipoDeteccion expResult = null;
        EnumTipoDeteccion result = instance.getTipo();
        assertEquals(expResult, result);
        
        instance.setTipo(tipo);
        result = instance.getTipo();
        assertEquals(tipo, result);
    }

    /**
     * Test of getAccionesDetectadas method, of class TipoDeteccion.
     */
    @Test
    public void testGetAccionesDetectadas() {
        System.out.println("getAccionesDetectadas");
        Deteccion instance = new Deteccion();
        List<Accion> expResult = new ArrayList<>();
        List<Accion> result = instance.getAccionesDetectadas();
        assertEquals(expResult, result);
        
        expResult.add(AMejora);
        expResult.add(ACorrectiva);
        expResult.add(APreventiva);        
        instance.addAccionDetectada(AMejora);
        instance.addAccionDetectada(ACorrectiva);
        instance.addAccionDetectada(APreventiva);
        result = instance.getAccionesDetectadas();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFortalezasDetectadas method, of class TipoDeteccion.
     */
    @Test
    public void testGetFortalezasDetectadas() {
        System.out.println("getFortalezasDetectadas");
        Deteccion instance = new Deteccion();
        List<Fortaleza> expResult = new ArrayList<>();
        List<Fortaleza> result = instance.getFortalezasDetectadas();
        assertEquals(expResult, result);
        
        expResult.add(fortaleza);
        instance.addFortalezaDetectada(fortaleza);
        result = instance.getFortalezasDetectadas();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class TipoDeteccion.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int Id = 0;
        Deteccion instance = new Deteccion();
        instance.setId(Id);
    }

    /**
     * Test of setNombre method, of class TipoDeteccion.
     */
    @Test
    public void testSetNombre() {
        System.out.println("setNombre");
        String Nombre = "Nombre";
        Deteccion instance = new Deteccion();
        instance.setNombre(Nombre);
    }

    /**
     * Test of setTipo method, of class TipoDeteccion.
     */
    @Test
    public void testSetTipo() {
        System.out.println("setTipo");
        Deteccion instance = new Deteccion();
        instance.setTipo(tipo);
    }

    /**
     * Test of setAccionesDetectadas method, of class TipoDeteccion.
     */
    @Test
    public void testSetAccionesDetectadas() {
        System.out.println("setAccionesDetectadas");
        List<Accion> AccionesDetectadas = new ArrayList<>();
        Deteccion instance = new Deteccion();
        instance.setAccionesDetectadas(AccionesDetectadas);

        AccionesDetectadas.add(AMejora);
        AccionesDetectadas.add(APreventiva);
        AccionesDetectadas.add(ACorrectiva);
        
        instance.setAccionesDetectadas(AccionesDetectadas);        
    }

    /**
     * Test of setFortalezasDetectadas method, of class TipoDeteccion.
     */
    @Test
    public void testSetFortalezasDetectadas() {
        System.out.println("setFortalezasDetectadas");
        List<Fortaleza> FortalezasDetectadas = new ArrayList<>();
        Deteccion instance = new Deteccion();
        instance.setFortalezasDetectadas(FortalezasDetectadas);
        
        FortalezasDetectadas.add(fortaleza);
        instance.setFortalezasDetectadas(FortalezasDetectadas);
    }

    /**
     * Test of addAccionDetectada method, of class TipoDeteccion.
     */
    @Test
    public void testAddAccionDetectada() {
        System.out.println("addAccionDetectada");        
        Deteccion instance = new Deteccion();
        instance.addAccionDetectada(AMejora);
        instance.addAccionDetectada(ACorrectiva);
        instance.addAccionDetectada(APreventiva);
    }

    /**
     * Test of removeAccionDetectada method, of class TipoDeteccion.
     */
    @Test
    public void testRemoveAccionDetectada_Accion() {
        System.out.println("removeAccionDetectada");
        Deteccion instance = new Deteccion();
        instance.addAccionDetectada(AMejora);
        instance.addAccionDetectada(ACorrectiva);
        instance.addAccionDetectada(APreventiva);
        instance.removeAccionDetectada(APreventiva);
        instance.removeAccionDetectada(AMejora);
        instance.removeAccionDetectada(ACorrectiva);
    }

    /**
     * Test of removeAccionDetectada method, of class TipoDeteccion.
     */
    @Test
    public void testRemoveAccionDetectada_int() {
        System.out.println("removeAccionDetectada");
        int IdAccionDetectada = 1;
        Deteccion instance = new Deteccion();
        ACorrectiva.setId(IdAccionDetectada);
        APreventiva.setId(0);
        AMejora.setId(2);
        instance.addAccionDetectada(AMejora);
        instance.addAccionDetectada(ACorrectiva);
        instance.addAccionDetectada(APreventiva);
        instance.removeAccionDetectada(IdAccionDetectada);
    }

    /**
     * Test of addFortalezaDetectada method, of class TipoDeteccion.
     */
    @Test
    public void testAddFortalezaDetectada() {
        System.out.println("addFortalezaDetectada");
        Deteccion instance = new Deteccion();
        instance.addFortalezaDetectada(fortaleza);
    }

    /**
     * Test of removeFortalezaDetectada method, of class TipoDeteccion.
     */
    @Test
    public void testRemoveFortalezaDetectada_Fortaleza() {
        System.out.println("removeFortalezaDetectada");
        Deteccion instance = new Deteccion();
        instance.removeFortalezaDetectada(fortaleza);
    }

    /**
     * Test of removeFortalezaDetectada method, of class TipoDeteccion.
     */
    @Test
    public void testRemoveFortalezaDetectada_int() {
        System.out.println("removeFortalezaDetectada");
        int IdFortalezaDetectada = 1;
        fortaleza.setId(1);
        Deteccion instance = new Deteccion();
        instance.removeFortalezaDetectada(IdFortalezaDetectada);
    }
    
}
