/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.persistencia;

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
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private String NombreEmpresa = new String();
    private String BaseDatos = new String();
    
    //  Constructores
    public Empresa(){};
    public Empresa(String NombreEmpresa){
        this.NombreEmpresa = NombreEmpresa;
        this.BaseDatos = GenerarNombreBaseDeDatos(NombreEmpresa.toLowerCase());
    }
    
    //  Getters
    public int getId() {return Id;}
    public String getNombreEmpresa() {return NombreEmpresa;}
    public String getBaseDatos() {return BaseDatos;}
    
    //  Setters
    public void setId(int Id) {this.Id = Id;}
    public void setNombreEmpresa(String NombreEmpresa) {
        this.NombreEmpresa = NombreEmpresa;
        this.BaseDatos = GenerarNombreBaseDeDatos(BaseDatos.toLowerCase());
    }
    public void setBaseDatos(String BaseDatos) {
        this.BaseDatos = GenerarNombreBaseDeDatos(BaseDatos.toLowerCase());
    }
    
    //  Metodos
    private String GenerarNombreBaseDeDatos(String NombreEmpresa){
        String nombreBD = NombreEmpresa.trim();
        nombreBD = nombreBD.replaceAll(" ", "_");
        nombreBD = nombreBD.replaceAll("\\.", "_");
        return nombreBD;
    }
}

