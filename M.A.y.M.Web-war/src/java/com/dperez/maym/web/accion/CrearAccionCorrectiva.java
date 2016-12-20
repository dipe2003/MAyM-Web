/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.accion;

import com.dperez.maymweb.accion.adjunto.Adjunto;
import com.dperez.maymweb.area.Area;
import com.dperez.maymweb.codificacion.Codificacion;
import com.dperez.maymweb.deteccion.Deteccion;
import com.dperez.maymweb.empresa.Empresa;
import com.dperez.maymweb.facades.FacadeAdministrador;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;



@Named
@ViewScoped
public class CrearAccionCorrectiva implements Serializable {
    @Inject
    private FacadeAdministrador fAdmin;
    
    private Date FechaDeteccion;
    private String Descripcion  = new String();
    private String AnalisisCausa = new String();

    private List<Adjunto> Adjuntos;
    
    private Deteccion GeneradaPor;
    
    private Area AreaSectorAccion;
    
    private Codificacion CodificacionAccion;

    private Empresa EmpresaAccion;
    
    
    //  Getters
    

    
    //  Setters
    

    
    //  Metodos
 
    
}
