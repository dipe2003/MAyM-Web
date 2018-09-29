/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.accion.acciones;

import com.dperez.maymweb.accion.actividad.Actividad;
import com.dperez.maymweb.producto.Producto;
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
 * @author dperez
 */
public class CorrectivaTest {

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
        List<Actividad> expResult = new ArrayList<>();
        expResult.add(instance.addMedidaCorrectiva(new Date(), "descripcion"));
        instance.setMedidasCorrectivas(expResult);
        List<Actividad> result = instance.getMedidasCorrectivas();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getMedidasPreventivas method, of class Correctiva.
     */
    @Test
    public void testGetMedidasPreventivas() {
        System.out.println("getMedidasPreventivas");
        Correctiva instance = new Correctiva();
        List<Actividad> expResult = new ArrayList<>();
       expResult.add(instance.addMedidaPreventiva(new Date(), "descripcion"));
        instance.setMedidasPreventivas(expResult);
        List<Actividad> result = instance.getMedidasPreventivas();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getTipo method, of class Correctiva.
     */
    @Test
    public void testGetTipo() {
        System.out.println("getTipo");
        Correctiva instance = new Correctiva();
        instance.setTipo(EnumTipoDesvio.NC_MENOR_OBS);
        EnumTipoDesvio expResult = EnumTipoDesvio.NC_MENOR_OBS;
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
        expResult.add(instance.addProductoAfectado("nombre", "datos"));
        List<Producto> result = instance.getProductosAfectados();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setMedidasCorrectivas method, of class Correctiva.
     */
    @Test
    public void testSetMedidasCorrectivas() {
        System.out.println("setMedidasCorrectivas");
        Correctiva instance = new Correctiva();
        List<Actividad> MedidasCorrectivas = new ArrayList<>();
        MedidasCorrectivas.add(instance.addMedidaCorrectiva(new Date(), "descripcion"));        
        instance.setMedidasCorrectivas(MedidasCorrectivas);
    }
    
    /**
     * Test of setMedidasPreventivas method, of class Correctiva.
     */
    @Test
    public void testSetMedidasPreventivas() {
        System.out.println("setMedidasPreventivas");
        Correctiva instance = new Correctiva();
        List<Actividad> MedidasPreventivas = new ArrayList<>();
        MedidasPreventivas.add(instance.addMedidaPreventiva(new Date(), "descripcion"));        
        instance.setMedidasPreventivas(MedidasPreventivas);
    }
    
    /**
     * Test of setTipo method, of class Correctiva.
     */
    @Test
    public void testSetTipo() {
        System.out.println("setTipo");
        EnumTipoDesvio Tipo = EnumTipoDesvio.NC_MENOR_OBS;
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
        instance.addMedidaCorrectiva(new Date(), "descripcion");
    }
    
    /**
     * Test of removeMedidaCorrectiva method, of class Correctiva.
     */
    @Test
    public void testRemoveMedidaCorrectiva_MedidaCorrectiva() {
        System.out.println("removeMedidaCorrectiva");
        Correctiva instance = new Correctiva();
        Actividad mc = instance.addMedidaCorrectiva(new Date(), "descripcion");
        instance.removeMedidaCorrectiva(mc);
    }
        
    /**
     * Test of addMedidaPreventiva method, of class Correctiva.
     */
    @Test
    public void testAddMedidaPreventiva() {
        System.out.println("addMedidaPreventiva");
        Correctiva instance = new Correctiva();
        instance.addMedidaPreventiva(new Date(), "descripcion");
    }
    
    /**
     * Test of removeMedidaPreventiva method, of class Correctiva.
     */
    @Test
    public void testRemoveMedidaPreventiva_MedidaPreventiva() {
        System.out.println("removeMedidaPreventiva");
        Correctiva instance = new Correctiva();
        Actividad mp = instance.addMedidaPreventiva(new Date(), "descripcion");
        instance.removeMedidaPreventiva(mp);
    }
        
    /**
     * Test of addProductoAfectado method, of class Correctiva.
     */
    @Test
    public void testAddProductoAfectado() {
        System.out.println("addProductoAfectado");
        Correctiva instance = new Correctiva();
        instance.addProductoAfectado("nombre", "datos");
    }
    
    /**
     * Test of removeProductoAfectado method, of class Correctiva.
     */
    @Test
    public void testRemoveProductoAfectado() {
        System.out.println("removeProductoAfectado");
        Correctiva instance = new Correctiva();
        Producto prodAfectado = instance.addProductoAfectado("nombre", "datos");
        instance.removeProductoAfectado(prodAfectado);
    }
    
    /**
     * Test of removeProducoAfectado method, of class Correctiva.
     */
    @Test
    public void testRemoveProducoAfectado() {
        System.out.println("removeProducoAfectado");
        Correctiva instance = new Correctiva();
        int IdProductoAfectado = 1;
        Producto prodAfectado = instance.addProductoAfectado("nombre", "datos");
        prodAfectado.setId(IdProductoAfectado);
        instance.removeProducoAfectado(IdProductoAfectado);
    }
    
}
