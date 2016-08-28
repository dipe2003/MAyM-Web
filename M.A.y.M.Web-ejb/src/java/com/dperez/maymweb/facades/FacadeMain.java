/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.facades;

import com.dperez.maymweb.controlador.registro.ControladorEdicionRegistro;
import com.dperez.maymweb.usuario.permiso.EnumPermiso;

/**
 *
 * @author Diego
 */
public abstract class FacadeMain {
    private ControladorEdicionRegistro cSeg = new ControladorEdicionRegistro();
    
    private String NombreBaseDatosEmpresa;
    
    
    //  Constructores
    public FacadeMain(){}
    public FacadeMain(String NombreEmpresa){
        this.NombreBaseDatosEmpresa = NombreEmpresa;
    }
    
    //  Getter
    public String getNombreBaseDatosEmpresa() {return NombreBaseDatosEmpresa;}
    
    //  Setter
    public void setNombreBaseDatosEmpresa(String NombreBaseDatosEmpresa) {this.NombreBaseDatosEmpresa = NombreBaseDatosEmpresa;}
    
    // Metodos
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
    
}
