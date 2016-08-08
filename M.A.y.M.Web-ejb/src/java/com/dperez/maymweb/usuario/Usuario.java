/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.usuario;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.accion.medida.Medida;
import com.dperez.maymweb.persistencia.Empresa;
import javax.persistence.Id;

import com.dperez.maymweb.usuario.permiso.Permiso;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
    private String Nombre = new String();
    private String Apellido = new String();
    private String Correo = new String();
    private boolean RecibeAlertas;
    
    @ManyToOne
    private Empresa EmpresaUsuario;
    
    @ManyToOne
    private Permiso PermisoUsuario;
    
    @OneToOne
    private Credencial CredencialUsuario;
    
    @OneToMany(mappedBy = "ResponsableImplementacion")
    private List<Medida> MedidasResponsableImplementacion;
    
    @OneToMany(mappedBy ="ResponsableVerificacion")
    private List<Accion> AccionesVerificacion;
    
    // Constructores
    public Usuario(){
        this.MedidasResponsableImplementacion = new ArrayList<>();
        this.AccionesVerificacion = new ArrayList<>();
    }
    public Usuario(String NombreUsuario, String ApellidoUsuario, String CorreoUsuario, boolean RecibeAlertas){
        this.Nombre = NombreUsuario;
        this.Apellido = ApellidoUsuario;
        this.Correo = CorreoUsuario;
        this.RecibeAlertas = RecibeAlertas;
        this.MedidasResponsableImplementacion = new ArrayList<>();
        this.AccionesVerificacion = new ArrayList<>();
    }
    
    // Getters
    public int getId() {return this.Id;}
    public String getNombre() {return this.Nombre;}
    public String getApellido() {return this.Apellido;}
    public String getCorreo() {return this.Correo;}
    public boolean isRecibeAlertas() {return this.RecibeAlertas;}
    
    public Permiso getPermisoUsuario() {return PermisoUsuario;}
    
    public Credencial getCredencialUsuario() {return CredencialUsuario;}
    
    public List<Medida> getMedidasResponsableImplementacion() {return MedidasResponsableImplementacion;}
    
    public List<Accion> getAccionesVerificacion() {return AccionesVerificacion;}

    public Empresa getEmpresaUsuario() {return EmpresaUsuario;}    
    
    // Setters
    public void setId(int Id) {this.Id = Id;}
    public void setNombre(String Nombre) {this.Nombre = Nombre;}
    public void setApellido(String Apellido) {this.Apellido = Apellido;}
    public void setCorreo(String Correo) {this.Correo = Correo;}
    public void setRecibeAlertas(boolean RecibeAlertas) {this.RecibeAlertas = RecibeAlertas;}
    
    public void setPermisoUsuario(Permiso PermisoUsuario) {
        if(PermisoUsuario == null && this.PermisoUsuario != null){
            this.PermisoUsuario.getUsuariosPermiso().remove(this);
            this.PermisoUsuario = null;
        }else{
            if(PermisoUsuario != null){
                this.PermisoUsuario = PermisoUsuario;
                if(!PermisoUsuario.getUsuariosPermiso().contains(this))
                    PermisoUsuario.addUsuarioPermiso(this);
            }
        }
    }
    
    public void setCredencialUsuario(Credencial CredencialUsuario) {    
        this.CredencialUsuario = CredencialUsuario;
        if(CredencialUsuario != null){
            if(CredencialUsuario.getUsuarioCredencial()!=null)
                CredencialUsuario.setUsuarioCredencial(this);
        }
    }
    
    public void setMedidasResponsableImplementacion(List<Medida> MedidasResponsableImplementacion) {
        this.MedidasResponsableImplementacion = MedidasResponsableImplementacion;
        for(Medida med: this.MedidasResponsableImplementacion){
            med.setResponsableImplementacion(this);
        }
    }
    
    public void setAccionesVerificacion(List<Accion> AccionesVerificacion) {
        this.AccionesVerificacion = AccionesVerificacion;
        for(Accion acc: AccionesVerificacion){
            acc.setResponsableVerificacion(this);
        }
    }

    public void setEmpresaUsuario(Empresa EmpresaUsuario) {this.EmpresaUsuario = EmpresaUsuario;}    
    
    // Listas
    public void addMedidaResponsableImplementacion(Medida MedidaResponsableImplementacion){
        if(MedidaResponsableImplementacion != null){
            this.MedidasResponsableImplementacion.add(MedidaResponsableImplementacion);
            if(MedidaResponsableImplementacion.getResponsableImplementacion() != null && !MedidaResponsableImplementacion.getResponsableImplementacion().equals(this))
                MedidaResponsableImplementacion.setResponsableImplementacion(this);
        }
    }
    
    public void removeMedidaResponsableImplementacion(Medida MedidaResponsableImplementacion){
        if(MedidaResponsableImplementacion != null){
            this.MedidasResponsableImplementacion.remove(MedidaResponsableImplementacion);
            if(MedidaResponsableImplementacion.getResponsableImplementacion() != null &&
                    MedidaResponsableImplementacion.getResponsableImplementacion().equals(this))
                MedidaResponsableImplementacion.setResponsableImplementacion(null);
        }
    }
    
    public void removeMedidaResponsableImplementacion(int IdMedidaResponsableImplementacion){
        Iterator<Medida> it = this.MedidasResponsableImplementacion.iterator() ;
        while(it.hasNext()){
            Medida m = it.next();
            if(m.getId()== IdMedidaResponsableImplementacion){
                it.remove();
                if(m.getResponsableImplementacion()!=null && m.getResponsableImplementacion().equals(this))
                    m.setResponsableImplementacion(null);
            }
        }
    }
    
    public void addAccionVerificacion(Accion AccionVerificacion){
        if(AccionVerificacion != null){
            this.AccionesVerificacion.add(AccionVerificacion);
            if(AccionVerificacion.getResponsableVerificacion() != null && !AccionVerificacion.getResponsableVerificacion().equals(this))
                AccionVerificacion.setResponsableVerificacion(this);
        }
    }
    
    public void removeAccionVerificacon(Accion AccionVerificacion){
        if(AccionVerificacion != null){
            this.AccionesVerificacion.remove(AccionVerificacion);
            if(AccionVerificacion.getResponsableVerificacion() != null && AccionVerificacion.getResponsableVerificacion().equals(this))
                AccionVerificacion.setResponsableVerificacion(null);
        }
    }
    
    public void removeAccionVerificacion(int IdAccionVerificacion){
        Iterator<Accion> it = this.AccionesVerificacion.iterator() ;
        while(it.hasNext()){
            Accion a = it.next();
            if(a.getId()== IdAccionVerificacion){
                it.remove();
                if(a.getResponsableVerificacion()!=null && a.getResponsableVerificacion().equals(this))
                    a.setResponsableVerificacion(null);
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
