/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.usuario;

import com.dperez.maymweb.accion.Comprobacion;
import com.dperez.maymweb.accion.actividad.Actividad;
import com.dperez.maymweb.empresa.Empresa;
import com.dperez.maymweb.usuario.permiso.EnumPermiso;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author dperez
 */
@Entity
@Table(name="Usuarios")
public class Usuario implements Serializable {
    @Id
    private int Id;
    @Column(unique = true)
    private String NickName = new String();
    private String Nombre = new String();
    private String Apellido = new String();
    private String Correo = new String();
    private boolean RecibeAlertas;
    
    @ManyToOne
    private Empresa EmpresaUsuario;
    
    private EnumPermiso PermisoUsuario;
    
    @OneToOne(orphanRemoval = true)
    private Credencial CredencialUsuario;
    
    @OneToMany(mappedBy = "ResponsableImplementacion")
    private List<Actividad> MedidasResponsableImplementacion;
    
    @OneToMany(mappedBy = "Responsable")
    private List<Comprobacion> Comprobaciones;
    
    // Constructores
    public Usuario(){
        this.MedidasResponsableImplementacion = new ArrayList<>();
        this.Comprobaciones = new ArrayList<>();
    }
    public Usuario(String NickName, String NombreUsuario, String ApellidoUsuario, String CorreoUsuario, boolean RecibeAlertas, EnumPermiso PermisoUsuario){
        this.NickName = NickName;
        this.Nombre = NombreUsuario;
        this.Apellido = ApellidoUsuario;
        this.Correo = CorreoUsuario;
        this.RecibeAlertas = RecibeAlertas;
        this.PermisoUsuario = PermisoUsuario;
        this.MedidasResponsableImplementacion = new ArrayList<>();
        this.Comprobaciones = new ArrayList<>();
    }
    
    // Getters
    public int getId() {return this.Id;}
    public String getNickName() {return NickName;}    
    public String getNombre() {return this.Nombre;}
    public String getApellido() {return this.Apellido;}
    public String getCorreo() {return this.Correo;}
    public boolean isRecibeAlertas() {return this.RecibeAlertas;}
    
    public EnumPermiso getPermisoUsuario() {return PermisoUsuario;}
    
    public Credencial getCredencialUsuario() {return CredencialUsuario;}
    
    public List<Actividad> getMedidasResponsableImplementacion() {return MedidasResponsableImplementacion;}
    
    public Empresa getEmpresaUsuario() {return EmpresaUsuario;}
    
    public List<Comprobacion> getComprobaciones() {return Comprobaciones;}
    
    // Setters
    public void setId(int Id) {this.Id = Id;}
    public void setNickName(String NickName) {this.NickName = NickName;}    
    public void setNombre(String Nombre) {this.Nombre = Nombre;}
    public void setApellido(String Apellido) {this.Apellido = Apellido;}
    public void setCorreo(String Correo) {this.Correo = Correo;}
    public void setRecibeAlertas(boolean RecibeAlertas) {this.RecibeAlertas = RecibeAlertas;}
    
    public void setPermisoUsuario(EnumPermiso PermisoUsuario) {
        this.PermisoUsuario = PermisoUsuario;
    }
    
    public void setCredencialUsuario(Credencial CredencialUsuario) {
        this.CredencialUsuario = CredencialUsuario;
        if(CredencialUsuario != null){
            if(CredencialUsuario.getUsuarioCredencial()!=null)
                CredencialUsuario.setUsuarioCredencial(this);
        }
    }
    
    public void setMedidasResponsableImplementacion(List<Actividad> MedidasResponsableImplementacion) {
        this.MedidasResponsableImplementacion = MedidasResponsableImplementacion;
        for(Actividad med: this.MedidasResponsableImplementacion){
            med.setResponsableImplementacion(this);
        }
    }
    
    public void setEmpresaUsuario(Empresa EmpresaUsuario) {
        this.EmpresaUsuario = EmpresaUsuario;
        if(EmpresaUsuario!=null){
            if(!EmpresaUsuario.getUsuariosEmpresa().contains(this)){
                EmpresaUsuario.getUsuariosEmpresa().add(this);
            }
        }
    }
    
    public void setComprobaciones(List<Comprobacion> Comprobaciones) {
        this.Comprobaciones = Comprobaciones;
        for(Comprobacion comprobacion: this.Comprobaciones){
            comprobacion.setResponsable(this);
        }
    }
    
    // Listas
    public void addMedidaResponsableImplementacion(Actividad MedidaResponsableImplementacion){
        if(MedidaResponsableImplementacion != null){
            this.MedidasResponsableImplementacion.add(MedidaResponsableImplementacion);
            if(MedidaResponsableImplementacion.getResponsableImplementacion() != null && !MedidaResponsableImplementacion.getResponsableImplementacion().equals(this))
                MedidaResponsableImplementacion.setResponsableImplementacion(this);
        }
    }
    
    public void removeMedidaResponsableImplementacion(Actividad MedidaResponsableImplementacion){
        if(MedidaResponsableImplementacion != null){
            this.MedidasResponsableImplementacion.remove(MedidaResponsableImplementacion);
            if(MedidaResponsableImplementacion.getResponsableImplementacion() != null &&
                    MedidaResponsableImplementacion.getResponsableImplementacion().equals(this))
                MedidaResponsableImplementacion.setResponsableImplementacion(null);
        }
    }
    
    public void removeMedidaResponsableImplementacion(int IdMedidaResponsableImplementacion){
        Iterator<Actividad> it = this.MedidasResponsableImplementacion.iterator() ;
        while(it.hasNext()){
            Actividad m = it.next();
            if(m.getId()== IdMedidaResponsableImplementacion){
                it.remove();
                if(m.getResponsableImplementacion()!=null && m.getResponsableImplementacion().equals(this))
                    m.setResponsableImplementacion(null);
            }
        }
    }
    
    public void addComprobacion(Comprobacion comprobacion){
        if(comprobacion != null){
            this.Comprobaciones.add(comprobacion);
            if(comprobacion.getResponsable() != null && !comprobacion.getResponsable().equals(this))
                comprobacion.setResponsable(this);
        }
    }
    
    public void removeComprobacion(Comprobacion comprobacion){
        if(comprobacion != null){
            this.Comprobaciones.remove(comprobacion);
            if(comprobacion.getResponsable() != null && comprobacion.getResponsable().equals(this))
                comprobacion.setResponsable(null);
        }
    }
    
    public void removeComprobacion(int IdComprobacion){
        Iterator<Comprobacion> it = this.Comprobaciones.iterator();
        while(it.hasNext()){
            Comprobacion c = it.next();
            if(c.getId() == IdComprobacion){
                it.remove();
                if(c.getResponsable() != null && c.getResponsable().equals(this))
                    c.setResponsable(null);
            }
        }
    }
    
    // Metodos
    /***
     * Devuelve el nombre completo del Usuario (Nombre+Apellido)
     * @return
     */
    public String GetNombreCompleto(){
        return this.Nombre + " " + this.Apellido;
    }
}
