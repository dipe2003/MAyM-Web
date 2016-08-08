/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dperez.maymweb.usuario.permiso;

import com.dperez.maymweb.usuario.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author dperez
 */
@Entity
@Table(name="Permisos")
public class Permiso implements Serializable{
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    @Column(unique = true)
    private String Nombre = new String();
    private String Descripcion = new String();
    
    @OneToMany(mappedBy ="PermisoUsuario")
    private List<Usuario> UsuariosPermiso;
    
    // Constructores
    public Permiso(){
         this.UsuariosPermiso = new ArrayList<>();
    }
    public Permiso(String NombrePermiso, String DescripcionPermiso){
        this.Nombre = NombrePermiso;
        this.Descripcion = DescripcionPermiso;
        this.UsuariosPermiso = new ArrayList<>();
    }
    
    // Getters
    public int getId() {return this.Id;}
    public String getNombre() {return this.Nombre;}
    public String getDescripcion() {return this.Descripcion;}

    public List<Usuario> getUsuariosPermiso() {return UsuariosPermiso;}    
    
    // Setters
    public void setId(int Id) {this.Id = Id;}
    public void setNombre(String Nombre) {this.Nombre = Nombre;}
    public void setDescripcion(String Descripcion) {this.Descripcion = Descripcion;}

    public void setUsuariosPermiso(List<Usuario> UsuariosPermiso) {
        this.UsuariosPermiso = UsuariosPermiso;
        for(Usuario usr:this.UsuariosPermiso){
            usr.setPermisoUsuario(this);
        }
    }
    
    // Listas
    public void addUsuarioPermiso(Usuario UsuarioPermiso){
        if(UsuarioPermiso != null){
            this.UsuariosPermiso.add(UsuarioPermiso);
            if(UsuarioPermiso.getPermisoUsuario() != null && !UsuarioPermiso.getPermisoUsuario().equals(this))
                UsuarioPermiso.setPermisoUsuario(this);
        }
    }
    
    public void removeUsuarioPermiso(Usuario UsuarioPermiso){
        if(UsuarioPermiso != null){
        this.UsuariosPermiso.remove(UsuarioPermiso);
        if(UsuarioPermiso.getPermisoUsuario()!=null && UsuarioPermiso.getPermisoUsuario().equals(this))
            UsuarioPermiso.setPermisoUsuario(null);
        }        
    }
    
    public void removeUsuarioPermiso(int IdUsuarioPermiso){
        Iterator<Usuario> it = this.UsuariosPermiso.iterator() ;
        while(it.hasNext()){
            Usuario u = it.next();
            if(u.getId()== IdUsuarioPermiso){
                it.remove();
                if(u.getPermisoUsuario()!=null && u.getPermisoUsuario().equals(this))
                    u.setPermisoUsuario(null);
            }
        }
    }    
}
