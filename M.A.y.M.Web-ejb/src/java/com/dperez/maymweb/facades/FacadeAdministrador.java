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
import com.dperez.maymweb.deteccion.TipoDeteccion;
import com.dperez.maymweb.usuario.Usuario;
import com.dperez.maymweb.usuario.permiso.EnumPermiso;

/**
 * Facade con los medotos para el manejo solo de las clases que seran parte de la configuracion de la aplicacion.
 * No genera/edita registros.
 * @author Diego
 */
public class FacadeAdministrador  {    
    private final ControladorConfiguracion cConfig;
    private final ControladorEdicionRegistro cEdicion;
    
    //  Constructores
    public FacadeAdministrador(String NombreBaseDatos){
        this.cConfig = new ControladorConfiguracion(NombreBaseDatos);
        this.cEdicion = new ControladorEdicionRegistro(NombreBaseDatos);
    }
    
    public Area NuevaArea(String NombreArea, String CorreoArea){
        return cConfig.NuevaArea(NombreArea, CorreoArea);
    }
    
    /***
     * Crea una nueva codificacion y la persiste en la base de datos.
     * @param NombreCodificacion
     * @return null si no se creo.
     */
    public Codificacion NuevaCodificacion(String NombreCodificacion){
        return cConfig.NuevaCodificacion(NombreCodificacion);
    }
    /***
     * Crea una nueva deteccion y la persiste en la base de datos.
     * @param NombreDeteccion
     * @param tipoDeteccion
     * @return null si no se creo.
     */
    public Deteccion NuevaDeteccion(String NombreDeteccion, TipoDeteccion tipoDeteccion){
        return cConfig.NuevaDeteccion(NombreDeteccion, tipoDeteccion);
    }
    
    /***
     * Crea una nueva deteccion y la persiste en la base de datos.
     * @param NombreTipoDeteccion
     * @param DescripcionTipo
     * @return null si no se creo.
     */
    public TipoDeteccion NuevoTipoDeteccion(String NombreTipoDeteccion, String DescripcionTipo){
        return cConfig.NuevoTipoDeteccion(NombreTipoDeteccion, DescripcionTipo);
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
     * Comprueba si existe el usuario con el nickname especificado.
     * @param nikckname
     * @return 
     */
    public boolean ExisteUsuario(String nikckname){
        return cConfig.ExisteUsuario(nikckname);
    }
     
}
