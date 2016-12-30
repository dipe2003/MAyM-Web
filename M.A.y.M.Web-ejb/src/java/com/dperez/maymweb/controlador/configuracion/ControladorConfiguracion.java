/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.controlador.configuracion;

import com.dperez.maymweb.area.Area;
import com.dperez.maymweb.area.ManejadorArea;
import com.dperez.maymweb.codificacion.Codificacion;
import com.dperez.maymweb.codificacion.ManejadorCodificacion;
import com.dperez.maymweb.deteccion.Deteccion;
import com.dperez.maymweb.deteccion.EnumTipoDeteccion;
import com.dperez.maymweb.deteccion.ManejadorDeteccion;
import com.dperez.maymweb.empresa.Empresa;
import com.dperez.maymweb.empresa.ManejadorEmpresa;
import com.dperez.maymweb.usuario.ControladorSeguridad;
import com.dperez.maymweb.usuario.Credencial;
import com.dperez.maymweb.usuario.ManejadorUsuario;
import com.dperez.maymweb.usuario.Usuario;
import com.dperez.maymweb.usuario.permiso.EnumPermiso;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Este controlador implementa los metodos necesarios para la creacion de los objetos que se modificarán como configuracion de la aplicacion.
 *
 * @author Diego
 */
@Named
@Stateless
public class ControladorConfiguracion {
    @Inject
    private ManejadorArea mArea;
    @Inject
    private ManejadorCodificacion mCodificacion;
    @Inject
    private ManejadorDeteccion mDeteccion;
    @Inject
    private ManejadorUsuario mUsuario;
    @Inject
    private ControladorSeguridad cSeg;
    @Inject
    private ManejadorEmpresa mEmpresa;
    
    
    public ControladorConfiguracion(){}
    
    /*
    USUARIO
    */
    
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
        Usuario usuario = new Usuario(Nickname, NombreUsuario, ApellidoUsuario, CorreoUsuario, false, PermisoUsuario);
        String[] pass = cSeg.getPasswordSeguro(Password);
        // pass[0] corresponde al password | pass[1] corresponde al salt
        Credencial credencial = new Credencial(pass[1], pass[0]);
        usuario.setCredencialUsuario(credencial);
        usuario.setId(mUsuario.CrearUsuario(usuario));
        if(usuario.getId()!=-1){
            return usuario;
        }else{
            return null;
        }
    }
    
    /**
     * Comprueba si existe el usuario con id especificado.
     * @param idUsuario
     * @return
     */
    public boolean ExisteUsuario(int idUsuario){
        return mUsuario.GetUsuario(idUsuario)!=null;
    }
    
    /**
     * Setea los datos del usuario y actualiza la base de datos. No se realiza comprobacion de password.
     * Para cambiar password utilizar metodo cambiarPasswordUsuario().
     * @param IdUsuario
     * @param NombreUsuario
     * @param ApellidoUsuario
     * @param CorreoUsuario
     * @param PermisoUsuario
     * @param RecibeAlertas
     * @return -1 si no se actualizo.
     */
    public int CambiarDatosUsuario(int IdUsuario, String NombreUsuario, String ApellidoUsuario, String CorreoUsuario,
            EnumPermiso PermisoUsuario, boolean RecibeAlertas){
        Usuario usuario = mUsuario.GetUsuario(IdUsuario);
        usuario.setNombre(NombreUsuario);
        usuario.setApellido(ApellidoUsuario);
        usuario.setCorreo(CorreoUsuario);
        usuario.setPermisoUsuario(PermisoUsuario);
        usuario.setRecibeAlertas(RecibeAlertas);
        return mUsuario.ActualizarUsuario(usuario);
    }
    
    /**
     * Comprueba la validez del password ingresado con el correspondiente del usuario en la base de datos.
     * @param IdUsuario
     * @param Password
     * @return True si es valido | Fales si no es valido.
     */
    public boolean ComprobarValidezPassword(int IdUsuario, String Password){
        Usuario usuario = mUsuario.GetUsuario(IdUsuario);
        String PasswordStored = cSeg.getPasswordSeguro(Password, usuario.getCredencialUsuario().getPasswordKey());
        return PasswordStored.equals(Password);
    }
    
    /**
     * Cambia la credencial (password y password key) del usuario especificado y actualiza la base de datos.
     * Si el password ingresado no coincide con el guardado no se actualiza.
     * @param IdUsuario
     * @param OldPassword: password del usuario guardado en la base de datos.
     * @param NewPassword: nuevo password para el usuario.
     * @return Retorna la credencial del usuario actualizada. Retorna Null si no se pudo actualizar.
     */
    public Credencial CambiarCredencialUsuario(int IdUsuario, String OldPassword, String NewPassword){
        int res = -1;
        Usuario usuario = mUsuario.GetUsuario(IdUsuario);
        String PasswordStored = cSeg.getPasswordSeguro(OldPassword, usuario.getCredencialUsuario().getPasswordKey());
        if(PasswordStored.equals(OldPassword)){
            String[] nuevaCredencial = cSeg.getPasswordSeguro(NewPassword);
            usuario.getCredencialUsuario().setPassword(nuevaCredencial[1]);
            usuario.getCredencialUsuario().setPasswordKey(nuevaCredencial[0]);
            res = mUsuario.ActualizarUsuario(usuario);
        }
        if(res!=-1){
            return usuario.getCredencialUsuario();
        }else{
            return null;
        }
    }
    
    /*
    AREA
    */
    
    /***
     * Crea una nueva area/sector y la persiste en la base de datos.
     * @param NombreArea
     * @param CorreoArea
     * @return null si no se creo.
     */
    public Area NuevaArea(String NombreArea, String CorreoArea){
        Area area = new Area(NombreArea, CorreoArea);
        area.setId(mArea.CrearArea(area));
        if(area.getId()!=-1){
            return area;
        }else{
            return null;
        }
    }
    /**
     * Cambia los valores de area y actualiza la base de datos.
     * @param IdArea
     * @param NombreArea
     * @param CorreoArea
     * @return Retorna -1 si no se actualizo. Retorna el IdArea si se actualizo.
     */
    public int EditarArea(int IdArea, String NombreArea, String CorreoArea){
        Area area = mArea.GetArea(IdArea);
        area.setNombre(NombreArea);
        area.setCorreo(CorreoArea);
        return mArea.ActualizarArea(area);
    }
    
    /*
    CODIFICACION
    */
    
    /***
     * Crea una nueva codificacion y la persiste en la base de datos.
     * @param NombreCodificacion
     * @return null si no se creo.
     */
    public Codificacion NuevaCodificacion(String NombreCodificacion){
        Codificacion codificacion = new Codificacion(NombreCodificacion);
        codificacion.setId(mCodificacion.CrearCodificacion(codificacion));
        if(codificacion.getId()!=-1){
            return codificacion;
        }else{
            return null;
        }
    }
    /**
     * Cambia los valores de Codificacion y actualiza la base de datos.
     * @param IdCodificacion
     * @param NombreCodificacion
     * @return Retorna -1 si no se actualizo. Retorna el IdCodificacion si se actualizo.
     */
    public int EditarCodificacion(int IdCodificacion, String NombreCodificacion){
        Codificacion codificacion = mCodificacion.GetCodificacion(IdCodificacion);
        codificacion.setNombre(NombreCodificacion);
        return mCodificacion.ActualizarCodificacion(codificacion);
    }
    
    /*
    DETECCION Y TIPO DE DETECCION
    */
    
    /***
     * Crea una nueva deteccion y la persiste en la base de datos.
     * @param NombreDeteccion
     * @param tipoDeteccion
     * @return null si no se creo.
     */
    public Deteccion NuevaDeteccion(String NombreDeteccion, EnumTipoDeteccion tipoDeteccion){
        Deteccion deteccion = new Deteccion(NombreDeteccion, tipoDeteccion);
        deteccion.setId(mDeteccion.CrearDeteccion(deteccion));
        if(deteccion.getId()!=-1){
            return deteccion;
        }else{
            return null;
        }
    }
    
    /**
     * Cambia los valores de Deteccion y actualiza la base de datos.
     * @param IdDteccion
     * @param NombreDeteccion
     * @param TipoDeteccion
     * @return Retorna -1 si no se actualizo. Retorna el IdDeteccion si se actualizo.
     */
    public int EditarDeteccion(int IdDteccion, String NombreDeteccion, EnumTipoDeteccion TipoDeteccion){
        Deteccion deteccion = mDeteccion.GetDeteccion(IdDteccion);
        // comprobar si se cambia el tipo de deteccion para 'ahorrar' llamada a la base de datos.
        if(!deteccion.getTipo().equals(TipoDeteccion)){
            deteccion.setTipo(TipoDeteccion);
        }
        deteccion.setNombre(NombreDeteccion);
        return mDeteccion.ActualizarDeteccion(deteccion);
    }
    
    /*
        Empresa
    */
    /**
     * Crea una empresa y la persiste en la base de datos.
     * @param Id
     * @param NombreEmpresa
     * @param DireccionEmpresa
     * @param TelefonoEmpresa
     * @param CorreoEmpresa
     * @return Null si no se creo la empresa.
     */
    public Empresa NuevaEmpresa(int Id, String NombreEmpresa, String DireccionEmpresa, String TelefonoEmpresa, String CorreoEmpresa){
        Empresa empresa = new Empresa(Id, NombreEmpresa, DireccionEmpresa, TelefonoEmpresa, CorreoEmpresa);
        if(mEmpresa.CrearEmpresa(empresa)!=-1){
            return empresa;
        }else{
            return null;
        }
    }
    
    /**
     * Cambia los datos de la empresa y persiste los cambios.
     * @param Id
     * @param NombreEmpresa
     * @param DireccionEmpresa
     * @param TelefonoEmpresa
     * @param CorreoEmpresa
     * @return Retorna -1 si no se actualizo. Retorna el id de la empresa si se actualizo.
     */
    public int EditarEmpresa(int Id, String NombreEmpresa, String DireccionEmpresa, String TelefonoEmpresa, String CorreoEmpresa){
        Empresa empresa = mEmpresa.GetEmpresa(Id);
        empresa.setId(Id);
        empresa.setCorreoEmpresa(CorreoEmpresa);
        empresa.setDireccionEmpresa(DireccionEmpresa);
        empresa.setNombreEmpresa(NombreEmpresa);
        empresa.setTelefonoEmpresa(TelefonoEmpresa);
        if(mEmpresa.ActualizarEmpresa(empresa)!=-1){
            return empresa.getId();
        }else{
            return -1;
        }
    }
    
    /**
     * Comprueba la existencia de la empresa 
     * @param IdEmpresa
     * @return True si existe. Retorna False si no existe.
     */
    public boolean ExisteEmpresa(int IdEmpresa){
        return mEmpresa.GetEmpresa(IdEmpresa) != null;
    }
}
