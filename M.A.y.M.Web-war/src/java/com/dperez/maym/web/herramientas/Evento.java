/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.herramientas;

import java.io.Serializable;

/**
 *
 * @author dipe2
 */
public class Evento implements Serializable{
    private TipoEvento Tipo;
    private int IdUsuarioResponsable;
    private int IdAccion;
    private int IdActividad;
    
    public Evento(TipoEvento Tipo, int IdUsuarioResponsable, int IdAccion, int IdActividad) {
        this.Tipo = Tipo;
        this.IdUsuarioResponsable = IdUsuarioResponsable;
        this.IdAccion = IdAccion;
        this.IdActividad = IdActividad;
    }
    
    public TipoEvento getTipo() {return Tipo;}
    public void setTipo(TipoEvento Tipo) {this.Tipo = Tipo;}
    public int getUsuarioResponsable() {return IdUsuarioResponsable;}
    public void setUsuarioResponsable(int UsuarioResponsable) {this.IdUsuarioResponsable = UsuarioResponsable;}
}
