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
    
    /**
     * Crea una empresa y la persiste en la base de datos.
     * @param Id
     * @param NombreEmpresa
     * @param DireccionEmpresa
     * @param TelefonoEmpresa
     * @param CorreoEmpresa
     * @param FaxEmpresa
     * @param Descripcion
     * @param NumeroEmpresa
     * @return Null si no se creo la empresa.
     */
    public Empresa NuevaEmpresa(int Id, String NombreEmpresa, String DireccionEmpresa, String TelefonoEmpresa, String CorreoEmpresa,
            String FaxEmpresa, String Descripcion, String NumeroEmpresa){
        return cConfig.NuevaEmpresa(Id, NombreEmpresa, DireccionEmpresa, TelefonoEmpresa, CorreoEmpresa, FaxEmpresa, Descripcion, NumeroEmpresa);
    }
    
    /***
     * Crea una nueva area/sector y la persiste en la base de datos.
     * Se verifica si existe el nombre del area en la base de datos.
     * @param NombreArea
     * @param CorreoArea
     * @return null si no se creo.
     */
    public Area NuevaArea(String NombreArea, String CorreoArea){
        return cConfig.NuevaArea(NombreArea, CorreoArea);
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
     * Agrega o Quita empresa del Area.
     * Actualiza la base de datos y el objeto Empresa relacionado.
     * @param IdArea
     * @param AgregarEmpresa True agrega, False quita.
     * @param IdEmpresa
     * @return Retorna -1 si no se actualizo. Retorna el IdCodificacion si se actualizo.
     */
    public int ModificarEmpresaArea(int IdArea, boolean AgregarEmpresa, int IdEmpresa){
        if(AgregarEmpresa){
            return cConfig.AgregarAreaEmpresa(IdArea, IdEmpresa);
        }
        return cConfig.RemoverAreaEmpresa(IdArea, IdEmpresa);
    }
    
    /**
     * Elimina el area de la base de datos.
     * Actualiza la relaciones Empesa.
     * Se comprueba que no tenga acciones y fortalezas relacionadas.
     * @param IdArea
     * @param IdEmpresa
     * @return Retorna el id del area si se elimino. Retorna -1 si no se elimino.
     */
    public int EliminarArea(int IdArea, int IdEmpresa){
        return cConfig.EliminarArea(IdArea, IdEmpresa);
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
     * Agrega o Quita empresa de la codificacion.
     * Actualiza la base de datos y el objeto Empresa relacionado.
     * @param IdCodificacion
     * @param AgregarEmpresa True agrega, False quita.
     * @param IdEmpresa
     * @return Retorna -1 si no se actualizo. Retorna el IdCodificacion si se actualizo.
     */
    public int ModificarEmpresaCodificacion(int IdCodificacion, boolean AgregarEmpresa, int IdEmpresa){
        if(AgregarEmpresa){
            return cConfig.AgregarCodificacionEmpresa(IdCodificacion, IdEmpresa);
        }
        return cConfig.RemoverCodificacionEmpresa(IdCodificacion, IdEmpresa);
    }
    
    /**
     * Elimina la codificacion de la base de datos.
     * PRE: se debe comprobar que existe a una unica empresa.
     * Solo se elimina si pertenece a una unica empresa.
     * Se comprueba que no tenga acciones relacionadas.
     * @param IdCodificacion
     * @param IdEmpresa
     * @return Retorna el id de la codificacion si se elimino. Retorna -1 si no se elimino.
     */
    public int EliminarCodificacion(int IdCodificacion, int IdEmpresa){
        return cConfig.EliminarCodificacion(IdCodificacion, IdEmpresa);
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
     * @param IdUsuario
     * @param Nickname
     * @param NombreUsuario
     * @param ApellidoUsuario
     * @param CorreoUsuario
     * @param Password
     * @param PermisoUsuario
     * @param IdEmpresa
     * @param IdArea
     * @return null si no se creo.
     */
    public Usuario NuevoUsuario(int IdUsuario, String Nickname, String NombreUsuario, String ApellidoUsuario, String CorreoUsuario, String Password, 
            EnumPermiso PermisoUsuario, int IdEmpresa, int IdArea){
        return cConfig.NuevoUsuario(IdUsuario, Nickname, NombreUsuario, ApellidoUsuario, CorreoUsuario, Password, PermisoUsuario, IdEmpresa, IdArea);
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
     * Elimina el usuario indicado por su id.
     * Si el usuario esta relacionado con comprobaciones o actividades no se elimina.
     * @param IdUsuario
     * @return Retorna <b>Tur</b> si se elimina, <b>False</b> de lo contrario.
     */
    public int EliminarUsuario(int IdUsuario) {
        return cConfig.EliminarUsuario(IdUsuario);
    }
    
    /**
     * Da de baja un usuario.
     * @param IdUsuario
     * @return 
     */
    public int DarDeBajaUsuario(int IdUsuario){
        return cConfig.CambiarEstadoUsuario(IdUsuario, false);
    }
    
    /**
     * Da de alta un usuario.
     * @param IdUsuario
     * @return 
     */
    public int DarDeAltaUsuario(int IdUsuario){
        return cConfig.CambiarEstadoUsuario(IdUsuario, true);
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
    
    /**
     * Se elimina la fortaleza indicada de la base de datos.
     * @param IdFortaleza
     * @return Retorna el id de la fortaleza si se elimino, de lo contrario retorna -1.
     */
    public int EliminarFortaleza(int IdFortaleza){
        return cEdicion.EliminarFortaleza(IdFortaleza);
    }
    
    /**
     * Agrega la codificacon a la empresa y actualiza la base de datos.
     * @param IdCodificacion
     * @param IdEmpresa
     * @return  Retorna el id de la codificacion si se agergo. Retorna -1 si no se agrego.
     */
    public int AgregarCodificacionEmpresa(int IdCodificacion, int IdEmpresa){
        return cConfig.AgregarCodificacionEmpresa(IdCodificacion, IdEmpresa);
    }
    
    /**
     * Remueve la codificacion de la empresa y actualiza la base de datos.
     * @param IdCodificacion
     * @param IdEmpresa
     * @return Retorna el id de la codificacion si se quito. Retorna -1 si no se quito.
     */
    public int RemoverCodificacionEmpresa(int IdCodificacion, int IdEmpresa){
        return cConfig.RemoverCodificacionEmpresa(IdCodificacion, IdEmpresa);
    }
    
    /**
     * Agrega el area a la empresa y actualiza la base de  datos.
     * @param IdArea
     * @param IdEmpresa
     * @return Retorna el id de la empresa si se agrego, de lo contrario retorna -1.
     */
    public int AgregarAreaEmpresa(int IdArea, int IdEmpresa){
        return cConfig.AgregarAreaEmpresa(IdArea, IdEmpresa);
    }
    /**
     * Remueve el area a la empresa y actualiza la base de  datos.
     * @param IdArea
     * @param IdEmpresa
     * @return Retorna el id de la empresa si se removio, de lo contrario retorna -1.
     */
    public int RemoverAreaEmpresa(int IdArea, int IdEmpresa){
        return cConfig.RemoverAreaEmpresa(IdArea, IdEmpresa);
    }
    
}
