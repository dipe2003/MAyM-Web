/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.persistencia.administrador;

import com.dperez.maymweb.usuario.ControladorSeguridad;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Diego
 */
@Named
@Stateless
public class ControladorAdministrador implements Serializable{
    @Inject
    private ManejadorAdministrador mAdmin;
    @Inject
    private ControladorSeguridad cSeg;
    
    public int NuevoAdministrador(String NicknameAdministrador, String Password){
        Administrador admin = new Administrador(NicknameAdministrador);
        String pass[] = new String[2];
        pass = cSeg.getPasswordSeguro(Password);
        admin.setPasswordKey(pass[0]);
        admin.setPassword(pass[1]);
        
        return mAdmin.CrearAdministrador(admin);
    }
    
    public Administrador GetAdministrador(String Nickname, String Password){
        List<Administrador> admins = mAdmin.ListarAdministradores();
        Administrador Admin = null;
        for(Administrador admin: admins){
            if(admin.getNickName().equals(Nickname)){
               if(admin.getPassword().equals(cSeg.getPasswordSeguro(Password, admin.getPasswordKey()))){
                   Admin = admin;
               }
            }
        }
        return Admin;        
    }
    
}
