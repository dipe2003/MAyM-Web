/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dperez.maymweb.persistencia;

import javax.persistence.EntityManager;

/**
 *
 * @author Diego
 */

public abstract class ManejadorPersistencia {
    public static String NOMBRE_BD;
    
    protected static final EntityManager em = ConexionDB.getInstancia().getEntityManager(NOMBRE_BD);
    
    public ManejadorPersistencia(){}
}
