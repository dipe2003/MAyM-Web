/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dperez.maymweb.facades;

import com.dperez.maymweb.usuario.permiso.EnumPermiso;

/**
 * Fabrica de Facade
 * @author Diego
 */
public class FabricaFacadePermiso {
    private static FabricaFacadePermiso Instancia;
    private FabricaFacadePermiso(){}
    
    public static FabricaFacadePermiso getInstancia(){
        if(Instancia == null) Instancia = new FabricaFacadePermiso();
        return Instancia;
    }
    
    /**
     * Crea la facade segun el permiso especificado para la base de datos indicada para la empresa.
     * @param Permiso
     * @param NombreEmpresa
     * @return 
     */
    public FacadeMain getFacadePermiso(EnumPermiso Permiso, String NombreEmpresa){
        FacadeMain facade = null;
        switch(Permiso){
            case LECTURA:
               facade = new FacadeLectura();
               facade.setNombreBaseDatosEmpresa(NombreEmpresa);
               break;
               
            case DATOS:
                facade = new FacadeDatos();
                facade.setNombreBaseDatosEmpresa(NombreEmpresa);
                break;
                
            case VERIFICADOR:
                facade = new FacadeVerificador();
                facade.setNombreBaseDatosEmpresa(NombreEmpresa);
                break;
                
            case ADMINISTRADOR:
                facade = new FacadeAdministrador();
                facade.setNombreBaseDatosEmpresa(NombreEmpresa);
                break;
                
            default:
             throw new IllegalArgumentException("El permiso especificado no existe");
        }
        return facade;
    }
}
