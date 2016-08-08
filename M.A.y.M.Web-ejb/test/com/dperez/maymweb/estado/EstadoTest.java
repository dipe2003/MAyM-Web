/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.estado;

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
 * @author Diego
 */
public class EstadoTest {
    
    private final Accion ACorrectiva = new Correctiva();
    private final Accion APreventiva = new Preventiva();
    private final Accion AMejora = new Mejora();
    
    public EstadoTest() {
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
     * Test of getId method, of class Estado.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Estado instance = new Estado();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getNombre method, of class Estado.
     */
    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        Estado instance = new Estado();
        String expResult = "";
        String result = instance.getNombre();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getDescripcion method, of class Estado.
     */
    @Test
    public void testGetDescripcion() {
        System.out.println("getDescripcion");
        Estado instance = new Estado();
        String expResult = "";
        String result = instance.getDescripcion();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getAccionesConEstado method, of class Estado.
     */
    @Test
    public void testGetAccionesConEstado() {
        System.out.println("getAccionesConEstado");
        Estado instance = new Estado();
        List<Accion> expResult = new ArrayList<>();
        List<Accion> result = instance.getAccionesConEstado();
        assertEquals(expResult, result);
        
        expResult.add(AMejora);
        expResult.add(ACorrectiva);
        expResult.add(APreventiva);
        instance.addAccionAEstado(AMejora);
        instance.addAccionAEstado(ACorrectiva);
        instance.addAccionAEstado(APreventiva);
        result = instance.getAccionesConEstado();
        assertEquals(expResult, result);
        Estado estado = AMejora.getEstadoActualAccion();
        assertEquals(estado, instance);
    }
    
    /**
     * Test of setId method, of class Estado.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int Id = 0;
        Estado instance = new Estado();
        instance.setId(Id);
    }
    
    /**
     * Test of setNombre method, of class Estado.
     */
    @Test
    public void testSetNombre() {
        System.out.println("setNombre");
        String Nombre = "";
        Estado instance = new Estado();
        instance.setNombre(Nombre);
    }
    
    /**
     * Test of setDescripcion method, of class Estado.
     */
    @Test
    public void testSetDescripcion() {
        System.out.println("setDescripcion");
        String Descripcion = "";
        Estado instance = new Estado();
        instance.setDescripcion(Descripcion);
    }
    
    /**
     * Test of setAccionesConEstado method, of class Estado.
     */
    @Test
    public void testSetAccionesConEstado() {
        System.out.println("setAccionesConEstado");
        List<Accion> AccionesConEstado = new ArrayList<>();
        Estado instance = new Estado();
        instance.setAccionesConEstado(AccionesConEstado);
    }
    
    /**
     * Test of addAccionAEstado method, of class Estado.
     */
    @Test
    public void testAddAccionAEstado() {
        System.out.println("addAccionAEstado");
        Estado instance = new Estado();
        instance.addAccionAEstado(AMejora);
        instance.addAccionAEstado(APreventiva);
        instance.addAccionAEstado(ACorrectiva);
    }
    
    /**
     * Test of removeAccionAEstado method, of class Estado.
     */
    @Test
    public void testRemoveAccionAEstado_Accion() {
        System.out.println("removeAccionAEstado");
        Estado instance = new Estado();
        instance.addAccionAEstado(AMejora);
        instance.addAccionAEstado(APreventiva);
        instance.addAccionAEstado(ACorrectiva);
        
        instance.removeAccionAEstado(AMejora);
        instance.removeAccionAEstado(APreventiva);
        instance.removeAccionAEstado(ACorrectiva);
    }
    
    /**
     * Test of removeAccionAEstado method, of class Estado.
     */
    @Test
    public void testRemoveAccionAEstado_int() {
        System.out.println("removeAccionAEstado");
        int IdAccionAEstado = 1;
        Estado instance = new Estado();
        AMejora.setId(0);
        ACorrectiva.setId(IdAccionAEstado);
        APreventiva.setId(2);
        instance.addAccionAEstado(AMejora);
        instance.addAccionAEstado(APreventiva);
        instance.addAccionAEstado(ACorrectiva);
        instance.removeAccionAEstado(IdAccionAEstado);
    }
    
}
