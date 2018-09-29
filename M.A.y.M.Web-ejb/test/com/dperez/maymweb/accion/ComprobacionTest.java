/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.accion;

import com.dperez.maymweb.accion.actividad.Actividad;
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
 * @author dipe2
 */
public class ComprobacionTest {
    
    private final Accion AccionComprobacion = new AccionImpl();
    
    public ComprobacionTest() {
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
     * Test of getId method, of class Comprobacion.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Comprobacion instance = new Comprobacion();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getFechaComprobacion method, of class Comprobacion.
     */
    @Test
    public void testGetFechaComprobacion() {
        System.out.println("getFechaComprobacion");
        Comprobacion instance = new Comprobacion();
        Date expResult = null;
        Date result = instance.getFechaComprobacion();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getResultado method, of class Comprobacion.
     */
    @Test
    public void testGetResultado() {
        System.out.println("getResultado");
        Comprobacion instance = new Comprobacion();
        EnumComprobacion expResult = EnumComprobacion.NO_COMPROBADA;
        EnumComprobacion result = instance.getResultado();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getObservaciones method, of class Comprobacion.
     */
    @Test
    public void testGetObservaciones() {
        System.out.println("getObservaciones");
        Comprobacion instance = new Comprobacion();
        String expResult = "";
        String result = instance.getObservaciones();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getResponsable method, of class Comprobacion.
     */
    @Test
    public void testGetResponsable() {
        System.out.println("getResponsable");
        Comprobacion instance = new Comprobacion();
        Usuario expResult = null;
        Usuario result = instance.getResponsable();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getFechaEstimada method, of class Comprobacion.
     */
    @Test
    public void testGetFechaEstimada() {
        System.out.println("getFechaEstimada");
        Comprobacion instance = new Comprobacion();
        Date expResult = null;
        Date result = instance.getFechaEstimada();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getAccionEficacia method, of class Comprobacion.
     */
    @Test
    public void testGetAccionEficacia() {
        System.out.println("getAccionEficacia");
        Comprobacion instance = new Comprobacion();
        Accion expResult = null;
        Accion result = instance.getAccionEficacia();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getAccionImplementacion method, of class Comprobacion.
     */
    @Test
    public void testGetAccionImplementacion() {
        System.out.println("getAccionImplementacion");
        Comprobacion instance = new Comprobacion();
        Accion expResult = null;
        Accion result = instance.getAccionImplementacion();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setId method, of class Comprobacion.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int Id = 0;
        Comprobacion instance = new Comprobacion();
        instance.setId(Id);
    }
    
    /**
     * Test of setFechaComprobacion method, of class Comprobacion.
     */
    @Test
    public void testSetFechaComprobacion() {
        System.out.println("setFechaComprobacion");
        Date FechaComprobacion = null;
        Comprobacion instance = new Comprobacion();
        instance.setFechaComprobacion(FechaComprobacion);
    }
    
    /**
     * Test of setResultado method, of class Comprobacion.
     */
    @Test
    public void testSetResultado() {
        System.out.println("setResultado");
        EnumComprobacion Resultado = null;
        Comprobacion instance = new Comprobacion();
        instance.setResultado(Resultado);
    }
    
    /**
     * Test of setObservaciones method, of class Comprobacion.
     */
    @Test
    public void testSetObservaciones() {
        System.out.println("setObservaciones");
        String Observaciones = "";
        Comprobacion instance = new Comprobacion();
        instance.setObservaciones(Observaciones);
    }
    
    /**
     * Test of setResponsable method, of class Comprobacion.
     */
    @Test
    public void testSetResponsable() {
        System.out.println("setResponsable");
        Usuario Responsable = null;
        Comprobacion instance = new Comprobacion();
        instance.setResponsable(Responsable);
    }
    
    /**
     * Test of setFechaEstimada method, of class Comprobacion.
     */
    @Test
    public void testSetFechaEstimada() {
        System.out.println("setFechaEstimada");
        Date FechaEstimada = null;
        Comprobacion instance = new Comprobacion();
        instance.setFechaEstimada(FechaEstimada);
    }
    
    /**
     * Test of setAccionEficacia method, of class Comprobacion.
     */
    @Test
    public void testSetAccionEficacia() {
        System.out.println("setAccionEficacia");
        Accion AccionEficacia = null;
        Comprobacion instance = new Comprobacion();
        instance.setAccionEficacia(AccionEficacia);
    }
    
    /**
     * Test of setAccionImplementacion method, of class Comprobacion.
     */
    @Test
    public void testSetAccionImplementacion() {
        System.out.println("setAccionImplementacion");
        Accion AccionImplementacion = null;
        Comprobacion instance = new Comprobacion();
        instance.setAccionImplementacion(AccionImplementacion);
    }
    
    public class AccionImpl extends Accion {
        @Override
        public void CambiarEstado(){}

        @Override
        public boolean EstanImplementadaActividades() {
            assertTrue("implementada en subclases", true);
            return true;
        }

        @Override
        public int compareTo(Accion o) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Actividad GetActividad(int IdActividad) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
}
