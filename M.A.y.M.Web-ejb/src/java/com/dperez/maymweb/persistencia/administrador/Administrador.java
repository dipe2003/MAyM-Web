/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.persistencia.administrador;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Diego
 */
@Entity
public class Administrador implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    @Column(unique = true)
    private String NickName = new String();
    private String Password;
    private String PasswordKey;
    
    //   Constructores
    public Administrador(){}
    
    public Administrador(String Nickname){
        this.NickName = Nickname;
    }
    
    //   getters
    public int getId() {return Id;}
    public String getNickName() {return NickName;}
    public String getPassword() {return Password;}
    public String getPasswordKey() {return PasswordKey;}
    
    
    //  Setters
    public void setId(int Id) {this.Id = Id;}
    public void setNickName(String NickName) {this.NickName = NickName;}
    public void setPassword(String Password) {this.Password = Password;}
    public void setPasswordKey(String PasswordKey) {this.PasswordKey = PasswordKey;}
    
}
