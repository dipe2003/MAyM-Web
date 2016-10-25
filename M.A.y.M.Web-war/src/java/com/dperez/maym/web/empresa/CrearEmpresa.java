/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dperez.maym.web.empresa;

import com.dperez.maymweb.facades.FacadeBDAdministrador;
import com.dperez.maymweb.persistencia.Empresa;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Diego
 */
@ViewScoped
@Named
public class CrearEmpresa implements Serializable {
    @Inject
    private FacadeBDAdministrador fAdminBD;
    
    private String NombreEmpresa;
    private String NombreAdministrador;
    private String PasswordAdministrador;
    private Map<String, Empresa> EmpresasRegistradas;
    private String EmpresaSeleccionada;
    
    //  Getters
    public String getNombreEmpresa(){return this.NombreEmpresa;}
    public String getNombreAdministrador(){return this.NombreAdministrador;}
    public String getPasswordAdministrador(){return this.PasswordAdministrador;}
    public Map<String, Empresa> getEmpresasRegistradas(){return this.EmpresasRegistradas;}
    public String getEmpresaSeleccionada(){return this.EmpresaSeleccionada;}
    
    //  Setters
    public void setNombreEmpresa(String NombreEmpresa){this.NombreEmpresa = NombreEmpresa;}
    public void setNombreAdministrador(String NombreAdministrador){this.NombreAdministrador = NombreAdministrador;}
    public void setPasswordAdministador(String PasswordAdministador){this.PasswordAdministrador = PasswordAdministador;}
    public void setEmpresasRegistadas(Map<String, Empresa> EmpresasRegistradas){this.EmpresasRegistradas = EmpresasRegistradas;}
    public void setEmpresaSeleccionada(String EmpresaSeleccionada){this.EmpresaSeleccionada = EmpresaSeleccionada;}
    
    @PostConstruct
    public void init(){
        this.EmpresasRegistradas = new HashMap<>();
        List<Empresa> empresas = fAdminBD.ListarEmpresasRegistradas();
        for(Empresa empresa:empresas){
            this.EmpresasRegistradas.put(empresa.getNombreEmpresa(), empresa);
        }
    }
    
    //  Metodos
    public void CrearEmpresa(){
        
    }
    
    public void CrearAdministrador(){
        
    }
    
    
    
    
}
