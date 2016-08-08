/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dperez.maymweb.usuario.permiso;

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
 * @author Diego
 */
public class PermisoTest {
    
    private final Usuario usuario = new Usuario();
    
    public PermisoTest() {
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
     * Test of getId method, of class Permiso.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Permiso instance = new Permiso();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNombre method, of class Permiso.
     */
    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        Permiso instance = new Permiso();
        String expResult = "";
        String result = instance.getNombre();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDescripcion method, of class Permiso.
     */
    @Test
    public void testGetDescripcion() {
        System.out.println("getDescripcion");
        Permiso instance = new Permiso();
        String expResult = "";
        String result = instance.getDescripcion();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUsuariosPermiso method, of class Permiso.
     */
    @Test
    public void testGetUsuariosPermiso() {
        System.out.println("getUsuariosPermiso");
        Permiso instance = new Permiso();
        List<Usuario> expResult = new ArrayList<>();
        List<Usuario> result = instance.getUsuariosPermiso();
        assertEquals(expResult, result);
        
        instance.addUsuarioPermiso(usuario);
        expResult.add(usuario);
        result = instance.getUsuariosPermiso();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Permiso.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int Id = 0;
        Permiso instance = new Permiso();
        instance.setId(Id);
    }

    /**
     * Test of setNombre method, of class Permiso.
     */
    @Test
    public void testSetNombre() {
        System.out.println("setNombre");
        String Nombre = "";
        Permiso instance = new Permiso();
        instance.setNombre(Nombre);
    }

    /**
     * Test of setDescripcion method, of class Permiso.
     */
    @Test
    public void testSetDescripcion() {
        System.out.println("setDescripcion");
        String Descripcion = "";
        Permiso instance = new Permiso();
        instance.setDescripcion(Descripcion);
    }

    /**
     * Test of setUsuariosPermiso method, of class Permiso.
     */
    @Test
    public void testSetUsuariosPermiso() {
        System.out.println("setUsuariosPermiso");
        List<Usuario> UsuariosPermiso = new ArrayList<>();
        Permiso instance = new Permiso();
        instance.setUsuariosPermiso(UsuariosPermiso);
        
        UsuariosPermiso.add(usuario);
        instance.addUsuarioPermiso(usuario);
    }

    /**
     * Test of addUsuarioPermiso method, of class Permiso.
     */
    @Test
    public void testAddUsuarioPermiso() {
        System.out.println("addUsuarioPermiso");
        Usuario UsuarioPermiso = null;
        Permiso instance = new Permiso();
        instance.addUsuarioPermiso(UsuarioPermiso);
        
        instance.addUsuarioPermiso(usuario);
    }

    /**
     * Test of removeUsuarioPermiso method, of class Permiso.
     */
    @Test
    public void testRemoveUsuarioPermiso_Usuario() {
        System.out.println("removeUsuarioPermiso");
        Usuario UsuarioPermiso = null;
        Permiso instance = new Permiso();
        instance.removeUsuarioPermiso(UsuarioPermiso);
        
        instance.addUsuarioPermiso(usuario);
        instance.removeUsuarioPermiso(usuario);
    }

    /**
     * Test of removeUsuarioPermiso method, of class Permiso.
     */
    @Test
    public void testRemoveUsuarioPermiso_int() {
        System.out.println("removeUsuarioPermiso");
        int IdUsuarioPermiso = 1;
        Usuario usr = new Usuario();
        usr.setId(0);
        usuario.setId(IdUsuarioPermiso);
        Permiso instance = new Permiso();
        
        instance.addUsuarioPermiso(usuario);
        instance.addUsuarioPermiso(usr);
        instance.removeUsuarioPermiso(IdUsuarioPermiso);
    }
    
}
