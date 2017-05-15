/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.accion;

import com.dperez.maymweb.accion.actividad.Actividad;
import com.dperez.maymweb.accion.adjunto.Adjunto;
import com.dperez.maymweb.accion.adjunto.EnumTipoAdjunto;
import com.dperez.maymweb.area.Area;
import com.dperez.maymweb.codificacion.Codificacion;
import com.dperez.maymweb.deteccion.Deteccion;
import com.dperez.maymweb.deteccion.EnumTipoDeteccion;
import com.dperez.maymweb.estado.EnumEstado;
import com.dperez.maymweb.usuario.Usuario;
import java.util.ArrayList;
import java.util.Date;
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
public class AccionTest {
    private final Adjunto adjunto = new Adjunto("Titulo Adjunto", "Ubicacion Adjunto", EnumTipoAdjunto.IMAGEN);
    private final Area area = new Area("Nombre Del Area", "Correo@Area.com");
    private final Codificacion codificacion = new Codificacion("Nombre Codificacion", "Descripcion Codificacion");
    private final Deteccion GeneradaPor = new Deteccion("Aud. Ejemplo", EnumTipoDeteccion.INTERNA);
    private final Comprobacion comprobacion = new Comprobacion(new Date(), new Usuario());
    
    public AccionTest() {
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
     * Test of getId method, of class Accion.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Accion instance = new AccionImpl();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getFechaDeteccion method, of class Accion.
     */
    @Test
    public void testGetFechaDeteccion() {
        System.out.println("getFechaDeteccion");
        Accion instance = new AccionImpl();
        Date expResult = null;
        Date result = instance.getFechaDeteccion();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getDescripcion method, of class Accion.
     */
    @Test
    public void testGetDescripcion() {
        System.out.println("getDescripcion");
        Accion instance = new AccionImpl();
        String expResult = "";
        String result = instance.getDescripcion();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getAnalisisCausa method, of class Accion.
     */
    @Test
    public void testGetAnalisisCausa() {
        System.out.println("getAnalisisCausa");
        Accion instance = new AccionImpl();
        String expResult = "";
        String result = instance.getAnalisisCausa();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getAdjuntos method, of class Accion.
     */
    @Test
    public void testGetAdjuntos() {
        System.out.println("getAdjuntos");
        Accion instance = new AccionImpl();
        instance.addAdjunto(adjunto);
        List<Adjunto> expResult = new ArrayList<>();
        expResult.add(adjunto);
        List<Adjunto> result = instance.getAdjuntos();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getGeneradaPor method, of class Accion.
     */
    @Test
    public void testGetGeneradaPor() {
        System.out.println("getGeneradaPor");
        Accion instance = new AccionImpl();
        Deteccion expResult = null;
        Deteccion result = instance.getGeneradaPor();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getAreaSector method, of class Accion.
     */
    @Test
    public void testGetAreaSector() {
        System.out.println("getAreaSector");
        Accion instance = new AccionImpl();
        Area expResult = null;
        Area result = instance.getAreaSectorAccion();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getCodificacionAccion method, of class Accion.
     */
    @Test
    public void testGetCodificacionAccion() {
        System.out.println("getCodificacionAccion");
        Accion instance = new AccionImpl();
        Codificacion expResult = null;
        Codificacion result = instance.getCodificacionAccion();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getEstadoActualAccion method, of class Accion.
     */
    @Test
    public void testGetEstadoActualAccion() {
        System.out.println("getEstadoActualAccion");
        Accion instance = new AccionImpl();
        EnumEstado expResult = EnumEstado.PENDIENTE;
        EnumEstado result = instance.getEstadoAccion();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getComprobacionImplementacion method, of class Accion.
     */
    @Test
    public void testGetComprobacionImplementacion() {
        System.out.println("getComprobacionImplementacion");
        Accion instance = new AccionImpl();
        instance.setComprobacionImplementacion(comprobacion);
        Comprobacion expResult = comprobacion;
        Comprobacion result = instance.getComprobacionImplementacion();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of ComprobacionEficacia method, of class Accion.
     */
    @Test
    public void testGetComprobacionEficacia() {
        System.out.println("getComprobacionEficacia");
        Accion instance = new AccionImpl();
        instance.setComprobacionEficacia(comprobacion);
        Comprobacion expResult = comprobacion;
        Comprobacion result = instance.getComprobacionEficacia();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setId method, of class Accion.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int Id = 0;
        Accion instance = new AccionImpl();
        instance.setId(Id);
    }
    
    /**
     * Test of setFechaDeteccion method, of class Accion.
     */
    @Test
    public void testSetFechaDeteccion() {
        System.out.println("setFechaDeteccion");
        Date FechaDeteccion = null;
        Accion instance = new AccionImpl();
        instance.setFechaDeteccion(FechaDeteccion);
    }
    
    /**
     * Test of setDescripcion method, of class Accion.
     */
    @Test
    public void testSetDescripcion() {
        System.out.println("setDescripcion");
        String Descripcion = "";
        Accion instance = new AccionImpl();
        instance.setDescripcion(Descripcion);
    }
    
    /**
     * Test of setAnalisisCausa method, of class Accion.
     */
    @Test
    public void testSetAnalisisCausa() {
        System.out.println("setAnalisisCausa");
        String AnalisisCausa = "";
        Accion instance = new AccionImpl();
        instance.setAnalisisCausa(AnalisisCausa);
    }
    
    /**
     * Test of setAdjuntos method, of class Accion.
     */
    @Test
    public void testSetAdjuntos() {
        System.out.println("setAdjuntos");
        List<Adjunto> Adjuntos = new ArrayList<>();
        Adjunto adj = new Adjunto("Titulo del Adjunto", "url de ubicacion del adjunto", EnumTipoAdjunto.IMAGEN);
        Adjuntos.add(adj);
        Accion instance = new AccionImpl();
        instance.setAdjuntos(Adjuntos);
    }
    
    /**
     * Test of setGeneradaPor method, of class Accion.
     */
    @Test
    public void testSetGeneradaPor() {
        System.out.println("setGeneradaPor");
        Accion instance = new AccionImpl();
        instance.setGeneradaPor(GeneradaPor);
        instance.setGeneradaPor(null);
        if(GeneradaPor.getAccionesDetectadas().contains(instance))
            fail(" El emetodo de setGeneredaPor no es bidireccional");
    }
    
    /**
     * Test of setAreaSector method, of class Accion.
     */
    @Test
    public void testSetAreaSector() {
        System.out.println("setAreaSector");
        Accion instance = new AccionImpl();
        instance.setAreaSectorAccion(area);
        instance.setAreaSectorAccion(null);
        if(area.getAccionesEnAreaSector().contains(instance))
            fail("El metodo setArea no es bidireccional");
    }
    
    /**
     * Test of setCodificacionAccion method, of class Accion.
     */
    @Test
    public void testSetCodificacionAccion() {
        System.out.println("setCodificacionAccion");
        Accion instance = new AccionImpl();
        instance.setCodificacionAccion(codificacion);
        instance.setCodificacionAccion(null);
        if(codificacion.getAccionesConCodificacion().contains(instance))
            fail ("El metodo setCodificaicon no es bidireccional");
    }
    
    /**
     * Test of setEstadoActualAccion method, of class Accion.
     */
    @Test
    public void testSetEstadoActualAccion() {
        System.out.println("setEstadoActualAccion");
        Accion instance = new AccionImpl();
        instance.setEstadoAccion(EnumEstado.CERRADA);
    }
    
    /**
     * Test of setComprobacionImplementacion method, of class Accion.
     */
    @Test
    public void testSetComprobacionImplementacion() {
        System.out.println("setComprobacionImplementacion");
        Accion instance = new AccionImpl();
        instance.setComprobacionImplementacion(comprobacion);
    }
    
    /**
     * Test of setComprobacionEficacia method, of class Accion.
     */
    @Test
    public void testSetComprobacionEficacia() {
        System.out.println("setComprobacionEficacia");
        Accion instance = new AccionImpl();
        instance.setComprobacionEficacia(comprobacion);
    }
    
    /**
     * Test of addAdjunto method, of class Accion.
     */
    @Test
    public void testAddAdjunto() {
        System.out.println("addAdjunto");
        Accion instance = new AccionImpl();
        instance.addAdjunto(adjunto);
    }
    
    public class AccionImpl extends Accion {
        @Override
        public void CambiarEstado(){}

        @Override
        public boolean EstaImplementada() {
            assertTrue("implementada en subclases", true);
            return true;
        }

        @Override
        public int compareTo(Accion o) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        @Override
        public Actividad GetActividad(int o) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
}
