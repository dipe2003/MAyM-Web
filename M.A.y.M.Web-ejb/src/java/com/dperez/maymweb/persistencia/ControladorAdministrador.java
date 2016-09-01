/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dperez.maymweb.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author Diego
 */
@Named
@Stateless
public class ControladorAdministrador implements Serializable{
    @Inject
    private ManejadorEmpresa mEmp;
    
    private static ConexionDB conn = ConexionDB.getInstancia();
    
    public int CrearEmpresa(String NombreEmpresa){
        int res = 0;
        Empresa emp = new Empresa();
        emp.setNombreEmpresa(NombreEmpresa);
        res = mEmp.CrearEmpresa(emp);
        return res;
    }
    
    public List<Empresa> ListarEmpresasRegistradas(){
        List<Empresa> empresas = new ArrayList<>();
        empresas = mEmp.ListarEmpresas();
        return empresas;
    }
    
    public int CrearBaseDeDatos(String NombreEmpresa){
        int res = -1;
        Empresa empresa = new Empresa(NombreEmpresa);
        res = conn.CrearBaseDatos(empresa.getBaseDatos());
        if(res > 0){          
           res = mEmp.CrearEmpresa(empresa);            
        }
        return res;
    }
    
    public boolean ExisteEmpresa(String NombreEmpresa){
        Empresa empresa = new Empresa(NombreEmpresa);
        int res = conn.ExisteBaseDatos(empresa.getNombreEmpresa());
        return res > 0;
    }
}
