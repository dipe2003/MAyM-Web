/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dperez.maymweb.facades;

import com.dperez.maymweb.controlador.configuracion.ControladorConfiguracion;
import com.dperez.maymweb.persistencia.ControladorEmpresa;
import com.dperez.maymweb.persistencia.Empresa;
import com.dperez.maymweb.persistencia.administrador.Administrador;
import com.dperez.maymweb.persistencia.administrador.ControladorAdministrador;
import com.dperez.maymweb.usuario.Usuario;
import com.dperez.maymweb.usuario.permiso.EnumPermiso;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Diego
 */
@Stateless
public class FacadeBDAdministrador {
    @Inject
    private ControladorAdministrador cAdmin;
    @Inject
    private ControladorEmpresa cEmp;
    
    /**
     * Crea un nuevo administrador para crear bases de datos y administradores.
     * @param NicknameAdministrador
     * @param Password
     * @return 
     */
    public int NuevoBDAdministrador(String NicknameAdministrador, String Password){        
        return cAdmin.NuevoAdministrador(NicknameAdministrador, Password);
    }
    
    /**
     * Obtiene el administrador de base de datos especificado por su id.
     * @param Nickname
     * @param Password
     * @return Retorna null si no existe.
     */
    public Administrador GetBDAdministrador(String Nickname, String Password){
        return cAdmin.GetAdministrador(Nickname, Password);
    }
    
    /**
     * Crea una empresa de nombre especificado.
     * @param NombreEmpresa
     * @return 
     */
    public int CrearEmpresa(String NombreEmpresa){
        return cEmp.CrearEmpresa(NombreEmpresa);
    }
    
    /**
     * Crea un administrador para la empresa especificada.
     * @param Nickname
     * @param Password
     * @param NombreUsuario
     * @param ApellidoUsuario
     * @param CorreoUsuario
     * @param NombreBaseDatos
     * @return Retorna null si no se creo.
     */
    public Usuario CrearAdministradorEmpresa(String Nickname, String Password, String NombreUsuario, String ApellidoUsuario, 
            String CorreoUsuario, String NombreBaseDatos){
        return new ControladorConfiguracion(NombreBaseDatos).NuevoUsuario(Nickname, NombreUsuario, ApellidoUsuario, CorreoUsuario, 
                Password, EnumPermiso.ADMINISTRADOR);
    }
    
    /**
     * Comprueba la existencia del administrador con el nickname especificado en la empresa indicada.
     * @param Nickname
     * @param NombreBaseDatos
     * @return 
     */
    public boolean ExisteAdministrdorEmpresa(String Nickname, String NombreBaseDatos){
        return new ControladorConfiguracion(NombreBaseDatos).ExisteUsuario(Nickname);
    }
    
    /**
     * 
     * @return 
     */
    public List<Empresa> ListarEmpresasRegistradas(){
        return cEmp.ListarEmpresasRegistradas();
    }
    
    /**
     * 
     * @param NombreEmpresa
     * @return 
     */
    public int CrearBaseDeDatos(String NombreEmpresa){
        return cEmp.CrearBaseDeDatos(NombreEmpresa);
    }
    
    /**
     * 
     * @param NombreEmpresa
     * @return 
     */
    public boolean ExisteEmpresa(String NombreEmpresa){
        return cEmp.ExisteEmpresa(NombreEmpresa);
    }
}
