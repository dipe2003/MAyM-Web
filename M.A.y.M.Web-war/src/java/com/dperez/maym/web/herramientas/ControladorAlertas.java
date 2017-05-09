/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.herramientas;

import com.dperez.maymweb.email.SendMail;
import com.dperez.maymweb.facades.FacadeLectura;
import com.dperez.maymweb.usuario.Usuario;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 *
 * @author Diego
 */
@Named
@Stateless
public class ControladorAlertas implements Serializable{
    private SendMail cMail = new SendMail();

    @Inject
    private FacadeLectura fLectura;
    
    private Map<Integer, Usuario> Usuarios;
    
    // Metodos
    
    /**
     * Envia un correo de alerta segun el evento.
     * Se extrae la informacion correspondiente al tipo de evento y se seleccionada el mensaje adecuado.
     * El evento contiene usuario destinatario y/o sector, se define el correo a utilizar segun el usuario recibe alerta o este vigente.
     * @param evento 
     */
    public void EnviarAlerta(Evento evento){
        Usuario usuario = Usuarios.get(evento.getIdUsuarioResponsable());
        String to = "";
        // si el usuario se dio de baja o no recibe alertas el destinatario sera el sector.
        if(usuario.getFechaBaja() != null || !usuario.isRecibeAlertas()){
            to = usuario.getAreaSectorUsuario().getCorreo();
        }else{
            to = usuario.getCorreo();
        }
        String asunto = "M.A.Y.M. - Plazo Cumplido";
        String mensaje = "";
        switch(evento.getTipo()){
            case IMPLEMENTACION_ACTIVIDAD:
                mensaje = "<h2 style='color:brown;'> Se cumplio el plazo para la implementacion de la actividad de la Accion con Id " + evento.getIdAccion() + ":</h2>";
                break;
                
            case IMPLEMENTACION_ACCION:
                mensaje = "<h2 style='color:brown;'> Se cumplio el plazo para la comprobar la implementacion de la Accion con Id " + evento.getIdAccion() + ":</h2>";
                break;
                
            case VERIFICACION_EFICACIA:
                mensaje = "<h2 style='color:brown;'> Se cumplio el plazo para la verificar la eficacia de la Accion con Id " + evento.getIdAccion() + ":</h2>";
                break;
                
        }
        mensaje = mensaje + "<p style='color:chocolate'> "+ usuario.getActividad(evento.getIdActividad()).getDescripcion() +" </p>";
        cMail.enviarMail(to, mensaje, asunto);
    }
    
    @PostConstruct
    public void init(){
        List<Usuario> lstUsuarios = fLectura.GetUsuariosEmpresa(false, -1);
        Usuarios = new HashMap<>();
        for(Usuario usuario: lstUsuarios){
            Usuarios.put(usuario.getId(), usuario);
        }
    }
}
