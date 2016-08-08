/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.usuario;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Diego
 */
@Entity
@Table(name="Credenciales")
public class Credencial implements Serializable{
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private String Password = new String();
    private String SaltKey = new String();
    
    @OneToOne
    private Usuario UsuarioCredencial;
    
    //  Constructores
    public Credencial(){}
    public Credencial(String Password, String Key){
        this.Password = Password;
        this.SaltKey = Key;
    }
    
    //  Getters
    
    public int getId() {return Id;}
    public String getPassword() {return Password;}
    public String getSaltKey() {return SaltKey;}
    
    public Usuario getUsuarioCredencial() {return UsuarioCredencial;}
    
    //  Setters
    
    public void setId(int Id) {this.Id = Id;}
    public void setPassword(String Password) {this.Password = Password;}
    public void setSaltKey(String SaltKey) {this.SaltKey = SaltKey;}
    
    public void setUsuarioCredencial(Usuario UsuarioCredencial) {
        this.UsuarioCredencial = UsuarioCredencial;
        if(UsuarioCredencial!= null && !UsuarioCredencial.getCredencialUsuario().equals(this))
            UsuarioCredencial.setCredencialUsuario(this);
    }
}
