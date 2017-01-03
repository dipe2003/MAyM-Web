/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.accion;

import com.dperez.maymweb.fortaleza.Fortaleza;
import com.dperez.maymweb.area.Area;
import com.dperez.maymweb.deteccion.Deteccion;
import com.dperez.maymweb.deteccion.EnumTipoDeteccion;
import com.dperez.maymweb.empresa.Empresa;
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
public class FortalezaTest {
    private final Area area = new Area("Nombre Del Area", "Correo@Area.com", new Empresa());
    private final Deteccion GeneradaPor = new Deteccion("Aud. Ejemplo", EnumTipoDeteccion.INTERNA);
    
    public FortalezaTest() {
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
     * Test of getId method, of class Fortaleza.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Fortaleza instance = new Fortaleza();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getFechaDeteccion method, of class Fortaleza.
     */
    @Test
    public void testGetFechaDeteccion() {
        System.out.println("getFechaDeteccion");
        Fortaleza instance = new Fortaleza();
        Date expResult = null;
        Date result = instance.getFechaDeteccion();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getDescripcion method, of class Fortaleza.
     */
    @Test
    public void testGetDescripcion() {
        System.out.println("getDescripcion");
        Fortaleza instance = new Fortaleza();
        String expResult = "";
        String result = instance.getDescripcion();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getGeneradaPor method, of class Fortaleza.
     */
    @Test
    public void testGetGeneradaPor() {
        System.out.println("getGeneradaPor");
        Fortaleza instance = new Fortaleza();
        Deteccion expResult = null;
        Deteccion result = instance.getGeneradaPor();
        assertEquals(expResult, result);
        
        instance.setGeneradaPor(GeneradaPor);
        result = instance.getGeneradaPor();
        assertEquals(GeneradaPor, result);        
    }
    
    /**
     * Test of getAreaSectorFortaleza method, of class Fortaleza.
     */
    @Test
    public void testGetAreaSectorFortaleza() {
        System.out.println("getAreaSectorFortaleza");
        Fortaleza instance = new Fortaleza();
        Area expResult = null;
        Area result = instance.getAreaSectorFortaleza();
        assertEquals(expResult, result);
        
        instance.setAreaSectorFortaleza(area);
        result = instance.getAreaSectorFortaleza();
        assertEquals(area, result);
    }
    
    /**
     * Test of setId method, of class Fortaleza.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int Id = 0;
        Fortaleza instance = new Fortaleza();
        instance.setId(Id);
    }
    
    /**
     * Test of setFechaDeteccion method, of class Fortaleza.
     */
    @Test
    public void testSetFechaDeteccion() {
        System.out.println("setFechaDeteccion");
        Date FechaDeteccion = null;
        Fortaleza instance = new Fortaleza();
        instance.setFechaDeteccion(FechaDeteccion);
    }
    
    /**
     * Test of setDescripcion method, of class Fortaleza.
     */
    @Test
    public void testSetDescripcion() {
        System.out.println("setDescripcion");
        String Descripcion = "";
        Fortaleza instance = new Fortaleza();
        instance.setDescripcion(Descripcion);
    }
    
    /**
     * Test of setGeneradaPor method, of class Fortaleza.
     */
    @Test
    public void testSetGeneradaPor() {
        System.out.println("setGeneradaPor");
        Fortaleza instance = new Fortaleza();
        instance.setGeneradaPor(GeneradaPor);
                instance.setGeneradaPor(null);
        if(GeneradaPor.getFortalezasDetectadas().contains(instance))
            fail("El metodo setGeneradaPor no es bidireccional");
    }
    
    /**
     * Test of setAreaSectorFortaleza method, of class Fortaleza.
     */
    @Test
    public void testSetAreaSectorFortaleza() {
        System.out.println("setAreaSectorFortaleza");
        Fortaleza instance = new Fortaleza();
        instance.setAreaSectorFortaleza(area);
        instance.setAreaSectorFortaleza(null);
        if(area.getFortalezasEnAreaSector().contains(instance))
            fail("El metodo setAreaSectorFortaleza no es bidireccional");
    }
    
}
