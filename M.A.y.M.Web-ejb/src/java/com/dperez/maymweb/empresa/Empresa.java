/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.empresa;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.area.Area;
import com.dperez.maymweb.fortaleza.Fortaleza;
import com.dperez.maymweb.usuario.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author dperez
 */
@Entity
@Table(name="Empresas")
public class Empresa implements Serializable {
    @Id
    private int Id;
    private String NombreEmpresa = new String();
    private String DireccionEmpresa = new String();
    private String TelefonoEmpresa = new String();
    private String CorreoEmpresa = new String();
    
    @OneToMany(mappedBy = "EmpresaUsuario" )
    private List<Usuario> UsuariosEmpresa;
    
    @OneToMany(mappedBy = "EmpresaFortaleza")
    private List<Fortaleza> FortalezasEmpresa;
    
    @OneToMany(mappedBy = "EmpresaAccion")
    private List<Accion> AccionesEmpresa;
        
    //  Constructores
    public Empresa(){
         this.UsuariosEmpresa = new ArrayList<>();
         this.FortalezasEmpresa = new ArrayList<>();
         this.AccionesEmpresa = new ArrayList<>();
    }
    public Empresa(int idEmpresa, String NombreEmpresa, String DireccionEmpresa, String TelefonoEmpresa, String CorreoEmpresa){
        this.Id = idEmpresa;
        this.NombreEmpresa = NombreEmpresa;
        this.DireccionEmpresa = DireccionEmpresa;
        this.TelefonoEmpresa = TelefonoEmpresa;
        this.CorreoEmpresa = CorreoEmpresa;
        this.UsuariosEmpresa = new ArrayList<>();
        this.FortalezasEmpresa = new ArrayList<>();
        this.AccionesEmpresa = new ArrayList<>();
    }
    
    //  Getters
    public int getId() {return Id;}
    public String getNombreEmpresa() {return NombreEmpresa;}
    public String getDireccionEmpresa() {return DireccionEmpresa;}
    public String getTelefonoEmpresa() {return TelefonoEmpresa;}
    public String getCorreoEmpresa() {return CorreoEmpresa;}    
    public List<Usuario> getUsuariosEmpresa(){return this.UsuariosEmpresa;}
    public List<Fortaleza> getFortalezasEmpresa(){return this.FortalezasEmpresa;}
    public List<Accion> getAccionesEmpresa(){return this.AccionesEmpresa;}
    
    //  Setters
    public void setId(int Id) {this.Id = Id;}
    public void setNombreEmpresa(String NombreEmpresa) {this.NombreEmpresa = NombreEmpresa;}
    public void setDireccionEmpresa(String DireccionEmpresa) {this.DireccionEmpresa = DireccionEmpresa;}
    public void setTelefonoEmpresa(String TelefonoEmpresa) {this.TelefonoEmpresa = TelefonoEmpresa;}
    public void setCorreoEmpresa(String CorreoEmpresa) {this.CorreoEmpresa = CorreoEmpresa;}  
    public void setUsuariosEmpresa(List<Usuario> UsuariosEmpresa){this.UsuariosEmpresa = UsuariosEmpresa;}
    public void setFortalezasEmpresa(List<Fortaleza> FortalezasEmpresa){this.FortalezasEmpresa = FortalezasEmpresa;}
    public void setAccionesEmpresa(List<Accion> AccionesEmpresa){this.AccionesEmpresa = AccionesEmpresa;}
    
    //  Listas-Relaciones
    public void addUsuario(Usuario UsuarioEmpresa){
        if(this.UsuariosEmpresa == null) this.UsuariosEmpresa = new ArrayList<>();
        if(UsuarioEmpresa!=null){
            this.UsuariosEmpresa.add(UsuarioEmpresa);
            if(UsuarioEmpresa.getEmpresaUsuario()!= null){
                if(!UsuarioEmpresa.getEmpresaUsuario().equals(this)){
                    UsuarioEmpresa.setEmpresaUsuario(this);
                }
            }else{
                UsuarioEmpresa.setEmpresaUsuario(this);
            }
        }
    }
    
    public void removeUsuario(Usuario UsuarioEmpresa){
        if(this.UsuariosEmpresa != null) {
            if(UsuarioEmpresa!=null){
                this.UsuariosEmpresa.remove(UsuarioEmpresa);
                if(UsuarioEmpresa.getEmpresaUsuario().equals(this)){
                    UsuarioEmpresa.setEmpresaUsuario(null);
                }
            }
        }
    }
    
    public void addFortaleza(Fortaleza FortalezaEmpresa){
        if(this.FortalezasEmpresa == null) this.FortalezasEmpresa = new ArrayList<>();
        if(FortalezaEmpresa!=null){
            this.FortalezasEmpresa.add(FortalezaEmpresa);
            if(FortalezaEmpresa.getEmpresaFortaleza()!=null){
                if(!FortalezaEmpresa.getEmpresaFortaleza().equals(this)){
                    FortalezaEmpresa.setEmpresaFortaleza(this);
                }
            }
        }
    }
    
    public void removeFortaleza(Fortaleza FortalezaEmpresa){
        if(this.FortalezasEmpresa != null) {
            if(FortalezaEmpresa!=null){
                this.FortalezasEmpresa.remove(FortalezaEmpresa);
                if(FortalezaEmpresa.getEmpresaFortaleza().equals(this)){
                    FortalezaEmpresa.setEmpresaFortaleza(null);
                }
            }
        }
    }
    
    public void addAccion(Accion AccionEmpresa){
        if(this.AccionesEmpresa == null) this.AccionesEmpresa = new ArrayList<>();
        if(AccionEmpresa!=null){
            this.AccionesEmpresa.add(AccionEmpresa);
            if(AccionEmpresa.getEmpresaAccion()!=null){
                if(!AccionEmpresa.getEmpresaAccion().equals(this)){
                    AccionEmpresa.setEmpresaAccion(this);
                }
            }
        }
    }
    
    public void removeFortaleza(Accion AccionEmpresa){
        if(this.AccionesEmpresa != null) {
            if(AccionEmpresa!=null){
                this.AccionesEmpresa.remove(AccionEmpresa);
                if(AccionEmpresa.getEmpresaAccion().equals(this)){
                    AccionEmpresa.setEmpresaAccion(null);
                }
            }
        }
    }

}

