/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.usuario;

import com.dperez.maymweb.accion.actividad.Actividad;
import com.dperez.maymweb.empresa.Empresa;
import com.dperez.maymweb.usuario.permiso.EnumPermiso;
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
public class UsuarioTest {
    
    private final EnumPermiso permiso = EnumPermiso.LECTURA;
    private final Actividad medidaPreventiva = new Actividad();
    private final Actividad medidaCorrectiva = new Actividad();
    private final Actividad actividadMejora = new Actividad();
    private final Actividad actividadPreventiva = new Actividad();
    private final Credencial credencial = new Credencial();
    private final Empresa empresaUsuario = new Empresa();
    
    public UsuarioTest() {
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
     * Test of getId method, of class Usuario.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Usuario instance = new Usuario();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getNombre method, of class Usuario.
     */
    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getNombre();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getApellido method, of class Usuario.
     */
    @Test
    public void testGetApellido() {
        System.out.println("getApellido");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getApellido();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getPasswordKey method, of class Usuario.
     */
    @Test
    public void testGetCredencialUsuario() {
        System.out.println("getPasswordKey");
        Usuario instance = new Usuario();
        Credencial expResult = null;
        Credencial result = instance.getCredencialUsuario();
        assertEquals(expResult, result);
        
        expResult = credencial;
        instance.setCredencialUsuario(credencial);
        result = instance.getCredencialUsuario();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getCorreo method, of class Usuario.
     */
    @Test
    public void testGetCorreo() {
        System.out.println("getCorreo");
        Usuario instance = new Usuario();
        String expResult = "";
        String result = instance.getCorreo();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isRecibeAlertas method, of class Usuario.
     */
    @Test
    public void testIsRecibeAlertas() {
        System.out.println("isRecibeAlertas");
        Usuario instance = new Usuario();
        boolean expResult = false;
        boolean result = instance.isRecibeAlertas();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getPermisoUsuario method, of class Usuario.
     */
    @Test
    public void testGetPermisoUsuario() {
        System.out.println("getPermisoUsuario");
        Usuario instance = new Usuario();
        EnumPermiso expResult = null;
        EnumPermiso result = instance.getPermisoUsuario();
        assertEquals(expResult, result);
        
        instance.setPermisoUsuario(permiso);
        result = instance.getPermisoUsuario();
        assertEquals(permiso, result);
    }
    
    /**
     * Test of getMedidasResponsableImplementacion method, of class Usuario.
     */
    @Test
    public void testGetMedidasResponsableImplementacion() {
        System.out.println("getMedidasResponsableImplementacion");
        Usuario instance = new Usuario();
        List<Actividad> expResult = new ArrayList<>();
        List<Actividad> result = instance.getMedidasResponsableImplementacion();
        assertEquals(expResult, result);
        
        expResult.add(actividadMejora);
        expResult.add(actividadPreventiva);
        expResult.add(medidaCorrectiva);
        expResult.add(medidaPreventiva);
        instance.addMedidaResponsableImplementacion(actividadMejora);
        instance.addMedidaResponsableImplementacion(actividadPreventiva);
        instance.addMedidaResponsableImplementacion(medidaCorrectiva);
        instance.addMedidaResponsableImplementacion(medidaPreventiva);
        result = instance.getMedidasResponsableImplementacion();
        assertEquals(expResult, result);
    }
    /**
     * Test of getEmpresa method, of class Usuario.
     */
    @Test
    public void testGetEmpresa() {
        System.out.println("getEmpresa");
        Usuario instance = new Usuario();
        instance.setEmpresaUsuario(empresaUsuario);
        Empresa result = instance.getEmpresaUsuario();
        assertEquals(empresaUsuario, result);
    }
    
    /**
     * Test of setId method, of class Usuario.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int Id = 0;
        Usuario instance = new Usuario();
        instance.setId(Id);
    }
    
    /**
     * Test of setNombre method, of class Usuario.
     */
    @Test
    public void testSetNombre() {
        System.out.println("setNombre");
        String Nombre = "";
        Usuario instance = new Usuario();
        instance.setNombre(Nombre);
    }
    
    /**
     * Test of setApellido method, of class Usuario.
     */
    @Test
    public void testSetApellido() {
        System.out.println("setApellido");
        String Apellido = "";
        Usuario instance = new Usuario();
        instance.setApellido(Apellido);
    }
    
    /**
     * Test of setPasswordKey method, of class Usuario.
     */
    @Test
    public void testSetCredencialUsuario() {
        System.out.println("setCredencialUsuario");
        Usuario instance = new Usuario();
        instance.setCredencialUsuario(credencial);
    }
    
    /**
     * Test of setCorreo method, of class Usuario.
     */
    @Test
    public void testSetCorreo() {
        System.out.println("setCorreo");
        String Correo = "";
        Usuario instance = new Usuario();
        instance.setCorreo(Correo);
    }
    
    /**
     * Test of setRecibeAlertas method, of class Usuario.
     */
    @Test
    public void testSetRecibeAlertas() {
        System.out.println("setRecibeAlertas");
        boolean RecibeAlertas = false;
        Usuario instance = new Usuario();
        instance.setRecibeAlertas(RecibeAlertas);
    }
    
    /**
     * Test of setPermisoUsuario method, of class Usuario.
     */
    @Test
    public void testSetPermisoUsuario() {
        System.out.println("setPermisoUsuario");
        EnumPermiso PermisoUsuario = EnumPermiso.LECTURA;
        Usuario instance = new Usuario();
        instance.setPermisoUsuario(PermisoUsuario);
        instance.setPermisoUsuario(permiso);
    }
    
    /**
     * Test of setMedidasResponsableImplementacion method, of class Usuario.
     */
    @Test
    public void testSetMedidasResponsableImplementacion() {
        System.out.println("setMedidasResponsableImplementacion");
        List<Actividad> MedidasResponsableImplementacion = new ArrayList<>();
        Usuario instance = new Usuario();
        instance.setMedidasResponsableImplementacion(MedidasResponsableImplementacion);
    }
    /**
     * Test of setEmpresaUsuario method, of class Usuario.
     */
    @Test
    public void testSetEmpresaUsuario() {
        System.out.println("setEmpresaUsuario");
        Usuario instance = new Usuario();
        instance.setEmpresaUsuario(empresaUsuario);
    }
    
    /**
     * Test of addMedidaResponsableImplementacion method, of class Usuario.
     */
    @Test
    public void testAddMedidaResponsableImplementacion() {
        System.out.println("addMedidaResponsableImplementacion");
        Actividad MedidaResponsableImplementacion = null;
        Usuario instance = new Usuario();
        instance.addMedidaResponsableImplementacion(MedidaResponsableImplementacion);
    }
    
    /**
     * Test of removeMedidaResponsableImplementacion method, of class Usuario.
     */
    @Test
    public void testRemoveMedidaResponsableImplementacion_Medida() {
        System.out.println("removeMedidaResponsableImplementacion");
        Actividad MedidaResponsableImplementacion = null;
        Usuario instance = new Usuario();
        instance.removeMedidaResponsableImplementacion(MedidaResponsableImplementacion);
    }
    
    /**
     * Test of removeMedidaResponsableImplementacion method, of class Usuario.
     */
    @Test
    public void testRemoveMedidaResponsableImplementacion_int() {
        System.out.println("removeMedidaResponsableImplementacion");
        int IdMedidaResponsableImplementacion = 0;
        Usuario instance = new Usuario();
        instance.removeMedidaResponsableImplementacion(IdMedidaResponsableImplementacion);
    }
    
    /**
     * Test of GetNombreCompleto method, of class Usuario.
     */
    @Test
    public void testGetNombreCompleto() {
        System.out.println("GetNombreCompleto");
        Usuario instance = new Usuario();
        String expResult = " ";
        String result = instance.GetNombreCompleto();
        assertEquals(expResult, result);
    }
    
}
