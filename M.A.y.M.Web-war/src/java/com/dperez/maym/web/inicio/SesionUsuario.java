/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dperez.maym.web.inicio;

import com.dperez.maymweb.empresa.Empresa;
import com.dperez.maymweb.usuario.Usuario;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;

import javax.faces.bean.ManagedBean;


/**
 *
 * @author dperez
 */
@ManagedBean
@SessionScoped
public class SesionUsuario implements Serializable {
    private static Usuario UsuarioLogueado;
    private static Empresa EmpresaSeleccionada;
    
    public SesionUsuario(){}
    
    public Usuario getUsuarioLogueado(){return UsuarioLogueado;}
    public Empresa getEmpresaSeleccionada(){return EmpresaSeleccionada;}
    
    public void setUsuarioLogueado(Usuario UsuarioLogueado){this.UsuarioLogueado = UsuarioLogueado;}
    public void setEmpresaSeleccionada(Empresa EmpresaSeleccionada){this.EmpresaSeleccionada = EmpresaSeleccionada;}
}
