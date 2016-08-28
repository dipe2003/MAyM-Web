/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dperez.maymweb.facades;

import com.dperez.maymweb.accion.EnumComprobacion;
import com.dperez.maymweb.controlador.registro.ControladorRegistro;
import java.util.Date;

/**
 * 
 * @author Diego
 */
public class FacadeVerificador extends FacadeMain{
    
    private ControladorRegistro cReg = new ControladorRegistro();
    
    /**
     * Setea la comprobacion de implementacion de la accion, cambia el estado segun corresponda y actualiza la base de datos.
     * @param FechaComprobacionImplementacion
     * @param ComentariosImplementacion
     * @param Comprobacion
     * @param IdAccion
     * @return -1 si no se actualizo
     */
    public int SetComprobacionImplementacionAccion(Date FechaComprobacionImplementacion, String ComentariosImplementacion, EnumComprobacion Comprobacion,
            int IdAccion){
        return cReg.SetComprobacionImplementacionAccion(FechaComprobacionImplementacion, ComentariosImplementacion, EnumComprobacion.NO_COMPROBADA, IdAccion);
    }
    
    /**
     * Setea la verificacion de eficacia de la accion, deja en estado cerrado y actualiza la base de datos.
     * @param FechaVerificacionEficacia
     * @param ComentariosVerificacion
     * @param Comprobacion
     * @param IdAccion
     * @return -1 si no se actualizo
     */
    public int SetVerificacionEficaciaAccion(Date FechaVerificacionEficacia, String ComentariosVerificacion, EnumComprobacion Comprobacion,
            int IdAccion){
        return cReg.SetVerificacionEficaciaAccion(FechaVerificacionEficacia, ComentariosVerificacion, Comprobacion, IdAccion);
    }
}
