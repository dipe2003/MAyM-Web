/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.accion.acciones;

import com.dperez.maymweb.accion.medida.medidas.MedidaCorrectiva;
import com.dperez.maymweb.accion.medida.medidas.MedidaPreventiva;
import com.dperez.maymweb.producto.Producto;
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
public class CorrectivaTest {
    
    private final MedidaCorrectiva mc = new MedidaCorrectiva();
    private final MedidaPreventiva mp = new MedidaPreventiva();
    private final Producto prodAfectado = new Producto();
    
    public CorrectivaTest() {
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
     * Test of getMedidasCorrectivas method, of class Correctiva.
     */
    @Test
    public void testGetMedidasCorrectivas() {
        System.out.println("getMedidasCorrectivas");
        Correctiva instance = new Correctiva();
        List<MedidaCorrectiva> expResult = new ArrayList<>();
        expResult.add(mc);
        instance.setMedidasCorrectivas(expResult);
        List<MedidaCorrectiva> result = instance.getMedidasCorrectivas();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMedidasPreventivas method, of class Correctiva.
     */
    @Test
    public void testGetMedidasPreventivas() {
        System.out.println("getMedidasPreventivas");
        Correctiva instance = new Correctiva();
        List<MedidaPreventiva> expResult = new ArrayList<>();
        expResult.add(mp);
        instance.setMedidasPreventivas(expResult);
        List<MedidaPreventiva> result = instance.getMedidasPreventivas();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getTipo method, of class Correctiva.
     */
    @Test
    public void testGetTipo() {
        System.out.println("getTipo");
        Correctiva instance = new Correctiva();
        instance.setTipo(EnumTipoDesvio.NO_CONFORMIDAD);
        EnumTipoDesvio expResult = EnumTipoDesvio.NO_CONFORMIDAD;
        EnumTipoDesvio result = instance.getTipo();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getProductosAfectados method, of class Correctiva.
     */
    @Test
    public void testGetProductosAfectados() {
        System.out.println("getProductosAfectados");
        Correctiva instance = new Correctiva();
        List<Producto> expResult = new ArrayList<>();
        expResult.add(prodAfectado);
        instance.addProductoAfectado(prodAfectado);
        List<Producto> result = instance.getProductosAfectados();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setMedidasCorrectivas method, of class Correctiva.
     */
    @Test
    public void testSetMedidasCorrectivas() {
        System.out.println("setMedidasCorrectivas");
        List<MedidaCorrectiva> MedidasCorrectivas = new ArrayList<>();
        MedidasCorrectivas.add(mc);
        Correctiva instance = new Correctiva();
        instance.setMedidasCorrectivas(MedidasCorrectivas);
    }
    
    /**
     * Test of setMedidasPreventivas method, of class Correctiva.
     */
    @Test
    public void testSetMedidasPreventivas() {
        System.out.println("setMedidasPreventivas");
        List<MedidaPreventiva> MedidasPreventivas = new ArrayList<>();
        MedidasPreventivas.add(mp);
        Correctiva instance = new Correctiva();
        instance.setMedidasPreventivas(MedidasPreventivas);
    }
    
    /**
     * Test of setTipo method, of class Correctiva.
     */
    @Test
    public void testSetTipo() {
        System.out.println("setTipo");
        EnumTipoDesvio Tipo = EnumTipoDesvio.NO_CONFORMIDAD;
        Correctiva instance = new Correctiva();
        instance.setTipo(Tipo);
    }
    
    /**
     * Test of setProductosAfectados method, of class Correctiva.
     */
    @Test
    public void testSetProductosAfectados() {
        System.out.println("setProductosAfectados");
        List<Producto> ProductosAfectados = new ArrayList<>();
        Correctiva instance = new Correctiva();
        instance.setProductosAfectados(ProductosAfectados);
    }
    
    /**
     * Test of addMedidaCorrectiva method, of class Correctiva.
     */
    @Test
    public void testAddMedidaCorrectiva() {
        System.out.println("addMedidaCorrectiva");
        Correctiva instance = new Correctiva();
        instance.addMedidaCorrectiva(mc);
    }
    
    /**
     * Test of removeMedidaCorrectiva method, of class Correctiva.
     */
    @Test
    public void testRemoveMedidaCorrectiva_MedidaCorrectiva() {
        System.out.println("removeMedidaCorrectiva");
        Correctiva instance = new Correctiva();
        instance.addMedidaCorrectiva(mc);
        instance.removeMedidaCorrectiva(mc);
    }
    
    /**
     * Test of removeMedidaCorrectiva method, of class Correctiva.
     */
    @Test
    public void testRemoveMedidaCorrectiva_int() {
        System.out.println("removeMedidaCorrectiva");
        int IdMedidaCorrectiva = 1;
        mc.setId(IdMedidaCorrectiva);
        Correctiva instance = new Correctiva();
        instance.addMedidaCorrectiva(mc);
        instance.removeMedidaCorrectiva(IdMedidaCorrectiva);
    }
    
    /**
     * Test of addMedidaPreventiva method, of class Correctiva.
     */
    @Test
    public void testAddMedidaPreventiva() {
        System.out.println("addMedidaPreventiva");
        Correctiva instance = new Correctiva();
        instance.addMedidaPreventiva(mp);
    }
    
    /**
     * Test of removeMedidaPreventiva method, of class Correctiva.
     */
    @Test
    public void testRemoveMedidaPreventiva_MedidaPreventiva() {
        System.out.println("removeMedidaPreventiva");
        Correctiva instance = new Correctiva();
        instance.addMedidaPreventiva(mp);
        instance.removeMedidaPreventiva(mp);
    }
    
    /**
     * Test of removeMedidaPreventiva method, of class Correctiva.
     */
    @Test
    public void testRemoveMedidaPreventiva_int() {
        System.out.println("removeMedidaPreventiva");
        int IdMedidaPreventiva = 1;
        mp.setId(IdMedidaPreventiva);
        Correctiva instance = new Correctiva();
        instance.addMedidaPreventiva(mp);
        instance.removeMedidaPreventiva(IdMedidaPreventiva);
    }
    
    /**
     * Test of addProductoAfectado method, of class Correctiva.
     */
    @Test
    public void testAddProductoAfectado() {
        System.out.println("addProductoAfectado");
        Correctiva instance = new Correctiva();
        instance.addProductoAfectado(prodAfectado);
    }
    
    /**
     * Test of removeProductoAfectado method, of class Correctiva.
     */
    @Test
    public void testRemoveProductoAfectado() {
        System.out.println("removeProductoAfectado");
        Correctiva instance = new Correctiva();
        instance.removeProductoAfectado(prodAfectado);
    }
    
    /**
     * Test of removeProducoAfectado method, of class Correctiva.
     */
    @Test
    public void testRemoveProducoAfectado() {
        System.out.println("removeProducoAfectado");
        int IdProductoAfectado = 1;
        prodAfectado.setId(IdProductoAfectado);
        Correctiva instance = new Correctiva();
        instance.addProductoAfectado(prodAfectado);
        instance.removeProducoAfectado(IdProductoAfectado);
    }
    
}
