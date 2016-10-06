/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.persistencia;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author Diego
 */
@Named
@Stateless
public class ControladorEmpresa implements Serializable{

    private static ManejadorEmpresa mEmp;
    
    private static ConexionDB conn = ConexionDB.getInstancia();
    
    /**
     * 
     * @param NombreEmpresa
     * @return 
     */
    public int CrearEmpresa(String NombreEmpresa){
        int res = 0;
        Empresa emp = new Empresa();
        emp.setNombreEmpresa(NombreEmpresa);
        res = mEmp.CrearEmpresa(emp);
        return res;
    }
    
    /**
     * 
     * @return 
     */
    public List<Empresa> ListarEmpresasRegistradas(){
        List<Empresa> empresas = new ArrayList<>();
        empresas = mEmp.ListarEmpresas();
        return empresas;
    }
    
    /**
     * 
     * @param NombreEmpresa
     * @return 
     */
    public int CrearBaseDeDatos(String NombreEmpresa){
        int res = -1;
        Empresa empresa = new Empresa(NombreEmpresa);
        boolean existe = conn.ExisteBaseDatos(empresa.getBaseDatos());
        if(!existe){
            res = conn.CrearBaseDatos(empresa.getBaseDatos());
            if(res > 0){
                mEmp = new ManejadorEmpresa(empresa.getBaseDatos());
                res = mEmp.CrearEmpresa(empresa);
            }
        }
        return res;
    }
    
    /**
     * 
     * @param NombreEmpresa
     * @return 
     */
    public boolean ExisteEmpresa(String NombreEmpresa){
        Empresa empresa = new Empresa(NombreEmpresa);
        boolean existe = conn.ExisteBaseDatos(empresa.getNombreEmpresa());
        return existe;
    }
}
