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
    
    /***
     * Crea una nueva area/sector y la persiste en la base de datos.
     * Se verifica si existe el nombre del area en la base de datos.
     * @param NombreArea
     * @param CorreoArea
     * @param IdEmpresa
     * @return null si no se creo.
     */
    public Area NuevaArea(String NombreArea, String CorreoArea, int IdEmpresa){
        return cConfig.NuevaArea(NombreArea, CorreoArea, IdEmpresa);
    }
    
    /**
     * Cambia los valores de area y actualiza la base de datos.
     * Se verifica si existe el nombre del area en la base de datos.
     * @param IdArea
     * @param NombreArea
     * @param CorreoArea
     * @return Retorna -1 si no se actualizo. Retorna el IdArea si se actualizo.
     */
    public int EditarArea(int IdArea, String NombreArea, String CorreoArea){
        return cConfig.EditarArea(IdArea, NombreArea, CorreoArea);
    }
    
    /**
     * Elimina el area de la base de datos.
     * Se comprueba que no tenga acciones y fortalezas relacionadas.
     * @param IdArea
     * @return Retorna el id de la codificacion si se elimino. Retorna -1 si no se elimino.
     */
    public int EliminarArea(int IdArea){
        return cConfig.EliminarArea(IdArea);
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
    
    /**
     * Cambia los valores de Codificacion y actualiza la base de datos.
     * Se verifica si existe el nombre de la codificacion en la base de datos.
     * @param IdCodificacion
     * @param NombreCodificacion
     * @param DescripcionCodificacion
     * @return Retorna -1 si no se actualizo. Retorna el IdCodificacion si se actualizo.
     */
    public int EditarCodificacion(int IdCodificacion, String NombreCodificacion, String DescripcionCodificacion){
        return cConfig.EditarCodificacion(IdCodificacion, NombreCodificacion, DescripcionCodificacion);
    }
    
    /**
     * Elimina la codificacion de la base de datos.
     * Se comprueba que no tenga acciones relacionadas.
     * @param IdCodificacion
     * @return Retorna el id de la codificacion si se elimino. Retorna -1 si no se elimino.
     */
    public int EliminarCodificacion(int IdCodificacion){
        return cConfig.EliminarCodificacion(IdCodificacion);
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
     * Cambia los valores de Deteccion y actualiza la base de datos.
     * Se comprueba que no existe una deteccion con el mismo nombre.
     * @param IdDteccion
     * @param NombreDeteccion
     * @param TipoDeteccion
     * @return Retorna -1 si no se actualizo. Retorna el IdDeteccion si se actualizo.
     */
    public int EditarDeteccion(int IdDteccion, String NombreDeteccion, EnumTipoDeteccion TipoDeteccion){
        return cConfig.EditarDeteccion(IdDteccion, NombreDeteccion, TipoDeteccion);
    }
    
    /**
     * Elimina la deteccion de la base de datos.
     * Se comprueba que no tenga acciones y fortalezas relacionadas.
     * @param IdDeteccion
     * @return Retorna el id de la deteccion si se elimino. Retorna -1 si no se elimino.
     */
    public int EliminarDeteccion(int IdDeteccion){
        return cConfig.EliminarDeteccion(IdDeteccion);
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
    
    /**
     * Elimina la accion seleccionada.
     * @param IdAccion
     * @return return Retorna -1 si no se elimino. Retorna el id de la accion eliminada.
     */
    public int EliminarAccion(int IdAccion){
        return cEdicion.EliminarAccion(IdAccion);
    }
    
}
