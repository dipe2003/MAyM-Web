/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dperez.maymweb.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Diego
 */
public class ControladorAdministrador {
    @Inject
    private ManejadorEmpresa mEmp;
    
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
}
