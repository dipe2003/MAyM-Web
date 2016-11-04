/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.empresa;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    
    
    //  Constructores
    public Empresa(){};
    public Empresa(int idEmpresa, String NombreEmpresa, String DireccionEmpresa, String TelefonoEmpresa, String CorreoEmpresa){
        this.Id = idEmpresa;
        this.NombreEmpresa = NombreEmpresa;
        this.DireccionEmpresa = DireccionEmpresa;
        this.TelefonoEmpresa = TelefonoEmpresa;
        this.CorreoEmpresa = CorreoEmpresa;
    }
    
    //  Getters
    public int getId() {return Id;}
    public String getNombreEmpresa() {return NombreEmpresa;}
    public String getDireccionEmpresa() {return DireccionEmpresa;}
    public String getTelefonoEmpresa() {return TelefonoEmpresa;}
    public String getCorreoEmpresa() {return CorreoEmpresa;}    
    
    //  Setters
    public void setId(int Id) {this.Id = Id;}
    public void setNombreEmpresa(String NombreEmpresa) {this.NombreEmpresa = NombreEmpresa;}
    public void setDireccionEmpresa(String DireccionEmpresa) {this.DireccionEmpresa = DireccionEmpresa;}
    public void setTelefonoEmpresa(String TelefonoEmpresa) {this.TelefonoEmpresa = TelefonoEmpresa;}
    public void setCorreoEmpresa(String CorreoEmpresa) {this.CorreoEmpresa = CorreoEmpresa;}    

}

