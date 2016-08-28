/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dperez.maymweb.facades;

/**
 *
 * @author Diego
 */
public class FacadeMain {
    private String NombreBaseDatosEmpresa;
    
    //  Constructores
    public FacadeMain(){}
    public FacadeMain(String NombreEmpresa){
        this.NombreBaseDatosEmpresa = NombreEmpresa;
    }
    
    //  Getter
    public String getNombreBaseDatosEmpresa() {return NombreBaseDatosEmpresa;}
    
    //  Setter
    public void setNombreBaseDatosEmpresa(String NombreBaseDatosEmpresa) {this.NombreBaseDatosEmpresa = NombreBaseDatosEmpresa;}    
    
}
