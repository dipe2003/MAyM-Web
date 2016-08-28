/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.facades;

import com.dperez.maymweb.controlador.registro.ControladorEdicionRegistro;
import com.dperez.maymweb.usuario.Credencial;
import com.dperez.maymweb.usuario.permiso.EnumPermiso;

/**
 *
 * @author Diego
 */
public abstract class FacadeMain {
    private final ControladorEdicionRegistro cSeg;
    
    private String NombreBaseDatosEmpresa;
    
    
    //  Constructores
    public FacadeMain(){this.cSeg = new ControladorEdicionRegistro();
    }
    public FacadeMain(String NombreEmpresa){
        this.cSeg = new ControladorEdicionRegistro();
        this.NombreBaseDatosEmpresa = NombreEmpresa;
    }
    
    //  Getter
    public String getNombreBaseDatosEmpresa() {return NombreBaseDatosEmpresa;}
    
    //  Setter
    public void setNombreBaseDatosEmpresa(String NombreBaseDatosEmpresa) {this.NombreBaseDatosEmpresa = NombreBaseDatosEmpresa;}
    
    // Metodos genericos independientes del nivel de permiso del usuario.
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
        return cSeg.CambiarDatosUsuario(IdUsuario, NombreUsuario, ApellidoUsuario, CorreoUsuario, EnumPermiso.VERIFICADOR, RecibeAlertas);
    }
    
    /**
     * Comprueba la validez del password ingresado con el correspondiente del usuario en la base de datos.
     * @param IdUsuario
     * @param Password
     * @return True si es valido | Fales si no es valido.
     */
    public boolean ComprobarValidezPassword(int IdUsuario, String Password){
        return cSeg.ComprobarValidezPassword(IdUsuario, Password);
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
        return cSeg.CambiarCredencialUsuario(IdUsuario, OldPassword, NewPassword);
    }
    
}
