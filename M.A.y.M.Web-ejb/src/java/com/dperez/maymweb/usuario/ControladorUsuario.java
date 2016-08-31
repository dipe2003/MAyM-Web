/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dperez.maymweb.usuario;

import java.util.Iterator;
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
public class ControladorUsuario {
    @Inject
    private ManejadorUsuario mUsuario;
    public ControladorUsuario(){
//        mUsuario = new ManejadorUsuario();
    }
    
    public Usuario GetUsuarioEmpresa(int IdEmpresa){
        List<Usuario> usuarios = mUsuario.ListarUsuarios();
        for(Usuario usr: usuarios){
            if(usr.getEmpresaUsuario().getId()==IdEmpresa)
                return  usr;
        }
        return null;
    }
    
    public List<Usuario> GetUsuariosEmpresa(int IdEmpresa){
        List<Usuario> usuarios = mUsuario.ListarUsuarios();
        Iterator it = usuarios.iterator();
        while(it.hasNext()){
            Usuario usr = (Usuario)it.next();
            if(usr.getEmpresaUsuario().getId()!=IdEmpresa)
                it.remove();
        }
        return usuarios;
    }
}
