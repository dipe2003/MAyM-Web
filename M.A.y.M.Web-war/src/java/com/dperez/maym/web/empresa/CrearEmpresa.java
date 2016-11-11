/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.empresa;

import com.dperez.maymweb.facades.FacadeAdministrador;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;



@Named
@ViewScoped
public class CrearEmpresa implements Serializable {
    @Inject
    private FacadeAdministrador fAdmin;
        
    private int Id;
    private String NombreEmpresa;
    private String DireccionEmpresa;
    private String TelefonoEmpresa;
    private String CorreoEmpresa;
    
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
    
    //  Metodos
    public void comprobarDisponibilidadId(int idEmpresa){
        
    }
    
    public void registrarEmpresa(){
        
    }
    
}
