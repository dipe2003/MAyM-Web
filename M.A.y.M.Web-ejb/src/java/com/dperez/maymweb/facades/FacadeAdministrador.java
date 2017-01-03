/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.facades;

import com.dperez.maymweb.area.Area;
import com.dperez.maymweb.codificacion.Codificacion;
import com.dperez.maymweb.controlador.configuracion.ControladorConfiguracion;
import com.dperez.maymweb.controlador.registro.ControladorEdicionRegistro;
import com.dperez.maymweb.deteccion.Deteccion;
import com.dperez.maymweb.deteccion.EnumTipoDeteccion;
import com.dperez.maymweb.empresa.Empresa;
import com.dperez.maymweb.usuario.Usuario;
import com.dperez.maymweb.usuario.permiso.EnumPermiso;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Facade con los medotos para el manejo solo de las clases que seran parte de la configuracion de la aplicacion.
 * No genera/edita registros.
 * @author Diego
 */
@Named
@Stateless
public class FacadeAdministrador  {
    @Inject
    private ControladorConfiguracion cConfig;
    @Inject
    private ControladorEdicionRegistro cEdicion;
    
    //  Constructores
    public FacadeAdministrador(){}
    
    public Empresa NuevaEmpresa(int Id, String NombreEmpresa, String DireccionEmpresa, String TelefonoEmpresa, String CorreoEmpresa){
        return cConfig.NuevaEmpresa(Id, NombreEmpresa, DireccionEmpresa, TelefonoEmpresa, CorreoEmpresa);
    }
    
    public Area NuevaArea(String NombreArea, String CorreoArea, int IdEmpresa){
        return cConfig.NuevaArea(NombreArea, CorreoArea, IdEmpresa);
    }
    
    /***
     * Crea una nueva codificacion y la persiste en la base de datos.
     * @param NombreCodificacion
     * @param DescripcionCodificacion
     * @return null si no se creo.
     */
    public Codificacion NuevaCodificacion(String NombreCodificacion, String DescripcionCodificacion){
        return cConfig.NuevaCodificacion(NombreCodificacion, DescripcionCodificacion);
    }
    /***
     * Crea una nueva deteccion y la persiste en la base de datos.
     * @param NombreDeteccion
     * @param tipoDeteccion
     * @return null si no se creo.
     */
    public Deteccion NuevaDeteccion(String NombreDeteccion, EnumTipoDeteccion tipoDeteccion){
        return cConfig.NuevaDeteccion(NombreDeteccion, tipoDeteccion);
    }
        
    /**
     * Crea un nuevo usuario y lo persiste en la base de datos. El usuario creado no recibe alertas.
     * @param Nickname
     * @param NombreUsuario
     * @param ApellidoUsuario
     * @param CorreoUsuario
     * @param Password
     * @param PermisoUsuario
     * @return null si no se creo.
     */
    public Usuario NuevoUsuario(String Nickname, String NombreUsuario, String ApellidoUsuario, String CorreoUsuario, String Password, EnumPermiso PermisoUsuario){
        return cConfig.NuevoUsuario(Nickname, NombreUsuario, ApellidoUsuario, CorreoUsuario, Password, PermisoUsuario);
    }
    
    /**
     * Comprueba si existe el usuario con el id especificado.
     * @param IdUsuario
     * @return
     */
    public boolean ExisteUsuario(int IdUsuario){
        return cConfig.ExisteUsuario(IdUsuario);
    }
    
    /**
     * Comprueba si existe la empresa especificada por su nombre;
     * @param IdEmpresa
     * @return 
     */
    public boolean ExisteEmpresa(int IdEmpresa){
        return cConfig.ExisteEmpresa(IdEmpresa);
    }
    
}
