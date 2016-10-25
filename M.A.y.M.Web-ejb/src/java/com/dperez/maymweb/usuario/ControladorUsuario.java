/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.usuario;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author Diego
 */
@Named
@Stateless
public class ControladorUsuario implements Serializable{
    
    private static ManejadorUsuario mUsuario;
    
    public Usuario GetUsuario(int IdUsuario){
        return mUsuario.GetUsuario(IdUsuario);
    }
    
    /**
     * Retorna el usuario con el nickname especificado.
     * @param Nickname
     * @return Retorna el usuario. Si no se encuetra retorna Null.
     */
    public Usuario GetUsuario(String Nickname){
        List<Usuario> usuarios = mUsuario.ListarUsuarios();
        Usuario usr = null;
        for(Usuario usuario: usuarios){
            if(usuario.getNickName().equalsIgnoreCase(Nickname)) usr = usuario;
        }
        return usr;
    }
    
    public List<Usuario> GetUsuariosEmpresa(){
        return mUsuario.ListarUsuarios();
    }
    
    public boolean ExisteUsuario(String NicknameUsuario){
        boolean existe = false;
        List<Usuario> usuarios = mUsuario.ListarUsuarios();
        for(Usuario usr: usuarios){
            if(usr.getNickName().equalsIgnoreCase(NicknameUsuario))
                existe = true;
        }
        return existe;
    }
}
