/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maymweb.herramientas;

import com.dperez.maymweb.accion.Accion;
import com.dperez.maymweb.accion.acciones.Correctiva;
import com.dperez.maymweb.accion.acciones.Mejora;
import com.dperez.maymweb.accion.acciones.Preventiva;
import com.dperez.maymweb.accion.actividad.Actividad;
import com.dperez.maymweb.estado.EnumEstado;
import com.dperez.maymweb.facades.FacadeLectura;
import java.util.Date;
import java.util.List;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author dipe2
 */
@Stateless
public class RepetidorTareas {
    @Inject
    private FacadeLectura fLectura;
    @Inject
    private ControladorAlertas cAlertas;
    
    // todos los domingos de todas las semanas, todos los años, a la hora 4:00 0s
    @Schedule(dayOfWeek = "Sun", month = "*", hour = "4", dayOfMonth = "*", year = "*", minute = "0", second = "0")
    
    // solo para testing envio cada 2 minutos:
    //@Schedule(month = "*", hour = "*", dayOfMonth = "*", year = "*", minute = "0/2", second = "0")
    public void RenviarAlertasEventos() {
        
        // Acciones Correctivas
        EnviarAlertasCorrectivas(fLectura.ListarAccionesCorrectivas());
        
        // Acciones Preventivas
        EnviarAlertasPreventivas(fLectura.ListarAccionesPreventivas());
        
        // Acciones de Mejora
        EnviarAlertasMejora(fLectura.ListarAccionesMejoras());
        
    }
    
    /**
     * Envia las alertas para las acciones correctivas.
     * Recorrer las acciones que no esten desestimadas o cerradas.
     * Si la accion esta implementada, comprueba las fechas de implementacion y eficacia y envia,sino recorre las actividades, comprueba
     * las fechas de implementacion y envia las alertas.
     * @param AccionesCorrectivas
     */
    private void EnviarAlertasCorrectivas(List<Accion> AccionesCorrectivas){
        Date Hoy = new Date();
        for(Accion accion: AccionesCorrectivas){
            if(accion.getEstadoAccion() != EnumEstado.CERRADA && accion.getEstadoAccion() != EnumEstado.DESESTIMADA){
                // Primero Si esta implementada
                if(accion.EstaImplementada()){
                    if(accion.getEstadoAccion() == EnumEstado.PROCESO_IMP){
                        if(accion.getComprobacionImplementacion().getFechaComprobacion() == null &&
                                accion.getComprobacionImplementacion().getFechaEstimada().compareTo(Hoy)< 0){
                            Evento evento = new Evento(TipoEvento.IMPLEMENTACION_ACCION, accion.getComprobacionImplementacion().getResponsable().getId(),
                                    accion.getId(),0);
                            cAlertas.EnviarAlerta(evento);
                        }
                    }else {
                        if(accion.getComprobacionEficacia().getFechaComprobacion() == null &&
                                accion.getComprobacionEficacia().getFechaEstimada().compareTo(Hoy)< 0){
                            Evento evento = new Evento(TipoEvento.VERIFICACION_EFICACIA, accion.getComprobacionImplementacion().getResponsable().getId(),
                                    accion.getId(),0);
                            cAlertas.EnviarAlerta(evento);
                        }
                    }
                }else{
                    List<Actividad> Actividades = ((Correctiva)accion).getMedidasCorrectivas();
                    for(Actividad actividad: Actividades){
                        if(actividad.getFechaImplementacion() == null && actividad.getFechaEstimadaImplementacion().compareTo(Hoy)<0){
                            Evento evento = new Evento(TipoEvento.IMPLEMENTACION_ACTIVIDAD, actividad.getResponsableImplementacion().getId(),
                                    accion.getId(),actividad.getIdActividad());
                            cAlertas.EnviarAlerta(evento);
                        }
                    }
                    Actividades.clear();
                    Actividades = ((Correctiva)accion).getMedidasPreventivas();
                    for(Actividad actividad: Actividades){
                        if(actividad.getFechaImplementacion() == null && actividad.getFechaEstimadaImplementacion().compareTo(Hoy)<0){
                            Evento evento = new Evento(TipoEvento.IMPLEMENTACION_ACTIVIDAD, actividad.getResponsableImplementacion().getId(),
                                    accion.getId(),actividad.getIdActividad());
                            cAlertas.EnviarAlerta(evento);
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Envia las alertas para las acciones preventivas.
     * Recorrer las acciones que no esten desestimadas o cerradas.
     * Si la accion esta implementada, comprueba las fechas de implementacion y eficacia y envia,sino recorre las actividades, comprueba
     * las fechas de implementacion y envia las alertas.
     * @param AccionesPreventivas
     */
    private void EnviarAlertasPreventivas(List<Accion> AccionesPreventivas){
        Date Hoy = new Date();
        for(Accion accion: AccionesPreventivas){
            if(accion.getEstadoAccion() != EnumEstado.CERRADA && accion.getEstadoAccion() != EnumEstado.DESESTIMADA){
                // Primero Si esta implementada
                if(accion.EstaImplementada()){
                    if(accion.getEstadoAccion() == EnumEstado.PROCESO_IMP){
                        if(accion.getComprobacionImplementacion().getFechaComprobacion() == null &&
                                accion.getComprobacionImplementacion().getFechaEstimada().compareTo(Hoy)< 0){
                            Evento evento = new Evento(TipoEvento.IMPLEMENTACION_ACCION, accion.getComprobacionImplementacion().getResponsable().getId(),
                                    accion.getId(),0);
                            cAlertas.EnviarAlerta(evento);
                        }
                    }else {
                        if(accion.getComprobacionEficacia().getFechaComprobacion() == null &&
                                accion.getComprobacionEficacia().getFechaEstimada().compareTo(Hoy)< 0){
                            Evento evento = new Evento(TipoEvento.VERIFICACION_EFICACIA, accion.getComprobacionImplementacion().getResponsable().getId(),
                                    accion.getId(),0);
                            cAlertas.EnviarAlerta(evento);
                        }
                    }
                }else{
                    List<Actividad> Actividades = ((Preventiva)accion).getActividades();
                    for(Actividad actividad: Actividades){
                        if(actividad.getFechaImplementacion() == null && actividad.getFechaEstimadaImplementacion().compareTo(Hoy)<0){
                            Evento evento = new Evento(TipoEvento.IMPLEMENTACION_ACTIVIDAD, actividad.getResponsableImplementacion().getId(),
                                    accion.getId(),actividad.getIdActividad());
                            cAlertas.EnviarAlerta(evento);
                        }
                    }
                }
            }
        }
    }
    /**
     * Envia las alertas para las acciones Mejora.
     * Recorrer las acciones que no esten desestimadas o cerradas.
     * Si la accion esta implementada, comprueba las fechas de implementacion y eficacia y envia,sino recorre las actividades, comprueba
     * las fechas de implementacion y envia las alertas.
     * @param AccionesMejora
     */
    private void EnviarAlertasMejora(List<Accion> AccionesMejora){
        Date Hoy = new Date();
        for(Accion accion: AccionesMejora){
            if(accion.getEstadoAccion() != EnumEstado.CERRADA && accion.getEstadoAccion() != EnumEstado.DESESTIMADA){
                // Primero Si esta implementada
                if(accion.EstaImplementada()){
                    if(accion.getEstadoAccion() == EnumEstado.PROCESO_IMP){
                        if(accion.getComprobacionImplementacion().getFechaComprobacion() == null &&
                                accion.getComprobacionImplementacion().getFechaEstimada().compareTo(Hoy)< 0){
                            Evento evento = new Evento(TipoEvento.IMPLEMENTACION_ACCION, accion.getComprobacionImplementacion().getResponsable().getId(),
                                    accion.getId(),0);
                            cAlertas.EnviarAlerta(evento);
                        }
                    }else {
                        if(accion.getComprobacionEficacia().getFechaComprobacion() == null &&
                                accion.getComprobacionEficacia().getFechaEstimada().compareTo(Hoy)< 0){
                            Evento evento = new Evento(TipoEvento.VERIFICACION_EFICACIA, accion.getComprobacionImplementacion().getResponsable().getId(),
                                    accion.getId(),0);
                            cAlertas.EnviarAlerta(evento);
                        }
                    }
                }else{
                    List<Actividad> Actividades = ((Mejora)accion).getActividades();
                    for(Actividad actividad: Actividades){
                        if(actividad.getFechaImplementacion() == null && actividad.getFechaEstimadaImplementacion().compareTo(Hoy)<0){
                            Evento evento = new Evento(TipoEvento.IMPLEMENTACION_ACTIVIDAD, actividad.getResponsableImplementacion().getId(),
                                    accion.getId(),actividad.getIdActividad());
                            cAlertas.EnviarAlerta(evento);
                        }
                    }
                }
            }
        }
    }
}
