/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dperez.maymweb.empresa;

import com.dperez.maymweb.usuario.Usuario;
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
 * @author dipe2
 */
public class EmpresaTest {
    
    private final Usuario Usr1 = new Usuario();
    private final Usuario Usr2 = new Usuario();
    
    public EmpresaTest() {
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
     * Test of getId method, of class Empresa.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Empresa instance = new Empresa();
        instance.setId(1);
        int expResult = 1;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNombreEmpresa method, of class Empresa.
     */
    @Test
    public void testGetNombreEmpresa() {
        System.out.println("getNombreEmpresa");
        Empresa instance = new Empresa();
        instance.setNombreEmpresa("Nombre Empresa");
        String expResult = "Nombre Empresa";
        String result = instance.getNombreEmpresa();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDireccionEmpresa method, of class Empresa.
     */
    @Test
    public void testGetDireccionEmpresa() {
        System.out.println("getDireccionEmpresa");
        Empresa instance = new Empresa();
        instance.setDireccionEmpresa("Direccion Empresa");
        String expResult = "Direccion Empresa";
        String result = instance.getDireccionEmpresa();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTelefonoEmpresa method, of class Empresa.
     */
    @Test
    public void testGetTelefonoEmpresa() {
        System.out.println("getTelefonoEmpresa");
        Empresa instance = new Empresa();
        instance.setTelefonoEmpresa("Telefono Empresa");
        String expResult = "Telefono Empresa";
        String result = instance.getTelefonoEmpresa();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCorreoEmpresa method, of class Empresa.
     */
    @Test
    public void testGetCorreoEmpresa() {
        System.out.println("getCorreoEmpresa");
        Empresa instance = new Empresa();
        instance.setCorreoEmpresa("Correo Empresa");
        String expResult = "Correo Empresa";
        String result = instance.getCorreoEmpresa();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsuariosEmpresa method, of class Empresa.
     */
    @Test
    public void testGetUsuariosEmpresa() {
        System.out.println("getUsuariosEmpresa");
        Empresa instance = new Empresa();
        instance.addUsuario(Usr1);
        instance.addUsuario(Usr2);
        List<Usuario> expResult = new ArrayList<>();
        expResult.add(Usr1);
        expResult.add(Usr2);
        List<Usuario> result = instance.getUsuariosEmpresa();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Empresa.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int Id = 0;
        Empresa instance = new Empresa();
        instance.setId(Id);
    }

    /**
     * Test of setNombreEmpresa method, of class Empresa.
     */
    @Test
    public void testSetNombreEmpresa() {
        System.out.println("setNombreEmpresa");
        String NombreEmpresa = "nombre Empresa";
        Empresa instance = new Empresa();
        instance.setNombreEmpresa(NombreEmpresa);
    }

    /**
     * Test of setDireccionEmpresa method, of class Empresa.
     */
    @Test
    public void testSetDireccionEmpresa() {
        System.out.println("setDireccionEmpresa");
        String DireccionEmpresa = "Direccion Empresa";
        Empresa instance = new Empresa();
        instance.setDireccionEmpresa(DireccionEmpresa);
    }

    /**
     * Test of setTelefonoEmpresa method, of class Empresa.
     */
    @Test
    public void testSetTelefonoEmpresa() {
        System.out.println("setTelefonoEmpresa");
        String TelefonoEmpresa = "Telefono Empresa";
        Empresa instance = new Empresa();
        instance.setTelefonoEmpresa(TelefonoEmpresa);
    }

    /**
     * Test of setCorreoEmpresa method, of class Empresa.
     */
    @Test
    public void testSetCorreoEmpresa() {
        System.out.println("setCorreoEmpresa");
        String CorreoEmpresa = "Correo Empresa";
        Empresa instance = new Empresa();
        instance.setCorreoEmpresa(CorreoEmpresa);
    }

    /**
     * Test of setUsuariosEmpresa method, of class Empresa.
     */
    @Test
    public void testSetUsuariosEmpresa() {
        System.out.println("setUsuariosEmpresa");
        List<Usuario> UsuariosEmpresa = new ArrayList<>();
        UsuariosEmpresa.add(Usr1);
        Empresa instance = new Empresa();
        instance.setUsuariosEmpresa(UsuariosEmpresa);
    }

    /**
     * Test of addUsuario method, of class Empresa.
     */
    @Test
    public void testAddUsuario() {
        System.out.println("addUsuario");
        Empresa instance = new Empresa();
        instance.addUsuario(Usr1);
    }

    /**
     * Test of removeUsuario method, of class Empresa.
     */
    @Test
    public void testRemoveUsuario() {
        System.out.println("removeUsuario");
        Empresa instance = new Empresa();
        instance.addUsuario(Usr1);
        instance.addUsuario(Usr2);
        instance.removeUsuario(Usr1);
    }
    
}
