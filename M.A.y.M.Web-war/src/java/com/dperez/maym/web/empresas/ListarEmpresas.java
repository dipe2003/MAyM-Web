/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dperez.maym.web.empresas;

import com.dperez.maymweb.empresa.Empresa;
import com.dperez.maymweb.facades.FacadeLectura;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author dipe2
 */
@Stateless
@Named
public class ListarEmpresas implements Serializable{
    @Inject
    private FacadeLectura fLectura;
    
    private Map<Integer, Empresa> ListaEmpresas;
    
    //  Getters
    public Map<Integer, Empresa> getListaEmpresas(){return this.ListaEmpresas;}
    
    
    //  Setters
    public void setListaEmpresas(Map<Integer, Empresa> ListaEmpresas){this.ListaEmpresas = ListaEmpresas;}
    
    
    //  Metodos
    @PostConstruct
    public void init(){
        ListaEmpresas = new HashMap<>();
        List<Empresa> empresas = fLectura.ListaEmpresasRegistradas();
        for(Empresa empresa:empresas){
            ListaEmpresas.put(empresa.getId(), empresa);
        }
    }
}
