/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.empresa;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.area.Area;
import com.dperez.maymweb.codificacion.Codificacion;
import com.dperez.maymweb.fortaleza.Fortaleza;
import com.dperez.maymweb.usuario.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
    
    @ManyToMany
    private List<Area> AreasEmpresa;
    
    @ManyToMany
    private List<Codificacion> CodificacionesEmpresa;
    
    //  Constructores
    public Empresa(){
        this.UsuariosEmpresa = new ArrayList<>();
        this.FortalezasEmpresa = new ArrayList<>();
        this.AccionesEmpresa = new ArrayList<>();
        this.CodificacionesEmpresa = new ArrayList<>();
        this.AreasEmpresa = new ArrayList<>();
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
        this.CodificacionesEmpresa = new ArrayList<>();
        this.AreasEmpresa = new ArrayList<>();
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
    public List<Area> getAreasEmpresa(){return this.AreasEmpresa;}
    public List<Codificacion> getCodificacionesEmpresa(){return this.CodificacionesEmpresa;}
    
    //  Setters
    public void setId(int Id) {this.Id = Id;}
    public void setNombreEmpresa(String NombreEmpresa) {this.NombreEmpresa = NombreEmpresa;}
    public void setDireccionEmpresa(String DireccionEmpresa) {this.DireccionEmpresa = DireccionEmpresa;}
    public void setTelefonoEmpresa(String TelefonoEmpresa) {this.TelefonoEmpresa = TelefonoEmpresa;}
    public void setCorreoEmpresa(String CorreoEmpresa) {this.CorreoEmpresa = CorreoEmpresa;}
    public void setUsuariosEmpresa(List<Usuario> UsuariosEmpresa){this.UsuariosEmpresa = UsuariosEmpresa;}
    public void setFortalezasEmpresa(List<Fortaleza> FortalezasEmpresa){this.FortalezasEmpresa = FortalezasEmpresa;}
    public void setAccionesEmpresa(List<Accion> AccionesEmpresa){this.AccionesEmpresa = AccionesEmpresa;}
    public void setAreasEmpresa(List<Area> AreasEmpresa){this.AreasEmpresa = AreasEmpresa;}
    public void setCodificacionesEmpresa(List<Codificacion> CodificacionesEmpresa){this.CodificacionesEmpresa = CodificacionesEmpresa;}
    
    //  Listas-Relaciones
    
    /*
    Usuarios
    */
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
    
    /*
    Fortalezas
    */
    
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
    
    /*
    Acciones
    */
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
    
    
    public void removeAccionEmpresa(Accion AccionEmpresa){
        if(this.AccionesEmpresa != null) {
            if(AccionEmpresa!=null){
                this.AccionesEmpresa.remove(AccionEmpresa);
                if(AccionEmpresa.getEmpresaAccion().equals(this)){
                    AccionEmpresa.setEmpresaAccion(null);
                }
            }
        }
    }
    
    /*
    Codificaciones
    */
    
    public void addCodificacionEmpresa(Codificacion CodificacionEmpresa){
        this.CodificacionesEmpresa.add(CodificacionEmpresa);
        if(!CodificacionEmpresa.getEmpresasCodificacion().contains(this)){
            CodificacionEmpresa.getEmpresasCodificacion().add(this);
        }
    }
    
    public void removeCodificacionEmpresa(Codificacion CodificacionEmpresa){
        this.CodificacionesEmpresa.remove(CodificacionEmpresa);
        if(CodificacionEmpresa.getEmpresasCodificacion().contains(this)){
            CodificacionEmpresa.getEmpresasCodificacion().remove(this);
        }
    }
    
    /*
    Areas
    */
    
    public void addAreaEmpresa(Area AreaEmpresa){
        this.AreasEmpresa.add(AreaEmpresa);
        if(!AreaEmpresa.getEmpresasArea().contains(this)){
            AreaEmpresa.getEmpresasArea().add(this);
        }
    }
    
    public void removeAreaEmpresa(Area AreaEmpresa){
        this.AreasEmpresa.remove(AreaEmpresa);
        if(AreaEmpresa.getEmpresasArea().contains(this)){
            AreaEmpresa.getEmpresasArea().remove(this);
        }
    }
    
}

