/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.usuarios;

import com.dperez.maym.web.inicio.SesionUsuario;
import com.dperez.maymweb.area.Area;
import com.dperez.maymweb.empresa.Empresa;
import com.dperez.maymweb.facades.FacadeAdministrador;
import com.dperez.maymweb.facades.FacadeLectura;
import com.dperez.maymweb.facades.FacadeMain;
import com.dperez.maymweb.usuario.Usuario;
import com.dperez.maymweb.usuario.permiso.EnumPermiso;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_FATAL;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@ViewScoped
public class Usuarios implements Serializable {
    @Inject
    private FacadeAdministrador fAdmin;
    @Inject
    private FacadeLectura fLectura;
    @Inject
    private FacadeMain fMain;
    @Inject
    private SesionUsuario sesion;
    
    private Empresa EmpresaLogueada;
    
    private int IdUsuarioSeleccionado;
    
    private boolean ContieneRegistros;
    
    private int NumeroNuevoUsuario;
    private String Nombre;
    private String Apellido;
    private String Password;
    private String PasswordConfirmacion;
    private String PasswordNuevo;
    private String CorreoElectronico;
    private boolean RecibeAlertas;
    
    private EnumPermiso[] PermisosUsuario;
    private EnumPermiso PermisoSeleccionado;
    
    private Map<Integer, Usuario> ListaUsuarios;
    
    private boolean CambiarPassword;
    
    private List<Area> ListaAreas;
    private int AreaSeleccionada;
    
    //  Getters
    public Empresa getEmpresaLogueada() {return EmpresaLogueada;}
    public boolean isContieneRegistros() {return ContieneRegistros;}
    public String getCorreoElectronico() {return CorreoElectronico;}
    public int getNumeroNuevoUsuario() {return NumeroNuevoUsuario;}
    public String getNombre() {return this.Nombre;}
    public String getApellido(){return this.Apellido;}
    public String getPassword(){return this.Password;}
    public String getPasswordConfirmacion(){return this.PasswordConfirmacion;}
    public String getPasswordNuevo(){return this.PasswordNuevo;}
    public boolean isRecibeAlertas(){return this.RecibeAlertas;}
    
    public EnumPermiso getPermisoSeleccionado(){return this.PermisoSeleccionado;}
    public EnumPermiso[] getPermisosUsuario(){return this.PermisosUsuario;}
    
    public Map<Integer, Usuario> getListaUsuarios() {return this.ListaUsuarios;}
    
    public boolean isCambiarPassword() {return CambiarPassword;}
    
    public List<Area> getListaAreas() {return ListaAreas;}
    public int getAreaSeleccionada() {return AreaSeleccionada;}
    
    public void setEmpresaLogueada(Empresa EmpresaLogueada) {this.EmpresaLogueada = EmpresaLogueada;}
    
    //  Setters
    public void setContieneRegistros(boolean ContieneRegistros) {this.ContieneRegistros = ContieneRegistros;}
    public void setNumeroNuevoUsuario(int NumeroNuevoUsuario) {this.NumeroNuevoUsuario = NumeroNuevoUsuario;}
    public void setNombre(String Nombre) {this.Nombre = Nombre;}
    public void setApellido(String Apellido){this.Apellido = Apellido;}
    public void setPassword(String Password){this.Password = Password;}
    public void setPasswordConfirmacion(String PasswordConfirmacion){this.PasswordConfirmacion = PasswordConfirmacion;}
    public void setPasswordNuevo(String PasswordNuevo){this.PasswordNuevo = PasswordNuevo;}
    public void setCorreoElectronico(String CorreoElectronico){this.CorreoElectronico = CorreoElectronico;}
    public void setRecibeAlertas(boolean RecibeAlertas){this.RecibeAlertas = RecibeAlertas;}
    
    public void setPermisoSeleccionado(EnumPermiso PermisoSeleccionado){this.PermisoSeleccionado = PermisoSeleccionado;}
    public void setPermisosUsuario(EnumPermiso[] PermisosUsuario){this.PermisosUsuario = PermisosUsuario;}
    
    public void setListaUsuarios(Map<Integer, Usuario> ListaUsuarios) {this.ListaUsuarios = ListaUsuarios;}
    
    public void setCambiarPassword(boolean CambiarPassword) {this.CambiarPassword = CambiarPassword;}
    
    public void setListaAreas(List<Area> ListaAreas) {this.ListaAreas = ListaAreas;}
    public void setAreaSeleccionada(int AreaSeleccionada) {this.AreaSeleccionada = AreaSeleccionada;}
    
    //  Metodos
    
    /**
     * Carga de propiedades al inicio
     */
    @PostConstruct
    public void init(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        EmpresaLogueada = (Empresa)request.getSession().getAttribute("Empresa");
        
        this.PermisoSeleccionado  = EnumPermiso.DATOS;
        
        //  Usuarios
        ListaUsuarios = new HashMap<>();
        List<Usuario> tmpUsuarios = fLectura.GetUsuariosEmpresa(false, -1);
        for(Usuario usuario:tmpUsuarios){
            ListaUsuarios.put(usuario.getId(), usuario);
        }
        
        //Areas
        ListaAreas =  new ArrayList<>();
        // llenar la lista de areas con todas las areas registradas.
        List<Area> tmpAreas = fLectura.ListarAreasSectores(EmpresaLogueada.getId());
        for(Area area: tmpAreas){
            if(area.contieneEmpresa(EmpresaLogueada.getId())){
                ListaAreas.add(area);
            }
        }
    }
    
    /**
     * Crea nuevo usuario con el permiso seleccionado.
     * Muestra un mensaje de errror si no se creo, se agrega el usuario a la empres logueada y redirige a la misma pagina para ver los resultados.
     * @throws java.io.IOException
     */
    public void nuevoUsuario() throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        if(Password.equals(PasswordConfirmacion)){
            if((fAdmin.NuevoUsuario(NumeroNuevoUsuario, Nombre,Apellido,CorreoElectronico, Password,
                    PermisoSeleccionado, EmpresaLogueada.getId(), AreaSeleccionada))!=null){
                context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/Views/Configuraciones/Usuarios.xhtml");
            }else{
                context.addMessage("form_usuarios:btn_nuevo_usuario", new FacesMessage(SEVERITY_FATAL, "No se pudo crear nuevo usuario", "No se pudo crear nuevo usuario" ));
                context.renderResponse();
            }
        }else{
            context.addMessage("form_usuarios:btn_nuevo_usuario", new FacesMessage(SEVERITY_FATAL, "Los passwords no coniciden", "Los passwords no coniciden" ));
            context.renderResponse();
        }
    }
    
    /**
     * Actualiza el usuario con lo datos ingresados.
     * Muestra un mensaje de errror si no se actualizo y redirige a la misma pagina para ver los resultados.
     * @throws java.io.IOException
     */
    public void editarUsuario() throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        if((fMain.CambiarDatosUsuario(IdUsuarioSeleccionado, Nombre, Apellido, CorreoElectronico, PermisoSeleccionado, RecibeAlertas, AreaSeleccionada))!=-1){
            if(sesion.getUsuarioLogueado().getId() == IdUsuarioSeleccionado){
                sesion.setUsuarioLogueado(fLectura.GetUsuario(IdUsuarioSeleccionado));
            }
            context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/Views/Configuraciones/Usuarios.xhtml");
        }else{
            context.addMessage("form_usuarios:btn_editar_usuario", new FacesMessage(SEVERITY_FATAL, "No se pudo editar usuario", "No se pudo editar usuario" ));
            context.renderResponse();
        }
    }
    
    /**
     * Cambia las credenciales del usuario.
     * Muestra los mensajes correspondientes por cada error/informacion.
     * Redirige a la pagina si se cambio.
     * @throws java.io.IOException
     */
    public void cambiarPassword() throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        if(PasswordNuevo.equals(PasswordConfirmacion)){
            if(!PasswordNuevo.isEmpty()){
                if(fMain.ResetCredencialUsuario(IdUsuarioSeleccionado, PasswordNuevo)!=null){
                    context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/Views/Configuraciones/Usuarios.xhtml");
                }else{
                    context.addMessage("form_usuarios:btn_password_usuario", new FacesMessage(SEVERITY_FATAL, "No se pudo cambiar password", "No se pudo cambiar password" ));
                    context.renderResponse();
                }
            }else{
                context.addMessage("form_usuarios:btn_password_usuario", new FacesMessage(SEVERITY_FATAL, "El nuevo password esta vacio", "El nuevo password esta vacio" ));
                context.renderResponse();
            }
        }else{
            context.addMessage("form_usuarios:btn_password_usuario", new FacesMessage(SEVERITY_FATAL, "Los passwords no coniciden", "Los passwords no coniciden" ));
            context.renderResponse();
        }
    }
    
    /**
     * Eliminina el usuario indicado.
     * Muestra un mensaje de error si no se pudo eliminar, de lo contrario redirige a la misma pagina para visualizar los cambios.
     * @param IdUsuarioSeleccionado
     * @throws java.io.IOException
     */
    public void eliminarUsuario(int IdUsuarioSeleccionado) throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        if(fAdmin.EliminarUsuario(IdUsuarioSeleccionado)!=-1){
            context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/Views/Configuraciones/Usuarios.xhtml");
        }else{
            context.addMessage("form_usuarios:btn_eliminar_usuario_"+IdUsuarioSeleccionado, new FacesMessage(SEVERITY_ERROR, "No se pudo eliminar el usuario", "No se pudo eliminar el usuario" ));
            context.renderResponse();
        }
    }
    
    /**
     * Da de baja un usuario.
     * @param IdUsuario
     * @throws java.io.IOException
     */
    public void darDeBajaUsuario(int IdUsuario) throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        if(fAdmin.DarDeBajaUsuario(IdUsuario)== -1){
            context.addMessage("form_usuarios:btn_baja_"+IdUsuario, new FacesMessage(SEVERITY_ERROR, "No se pudo dar de baja el usuario", "No se pudo dar de baja el usuario" ));
            context.renderResponse();
        }else{
            context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/Views/Configuraciones/Usuarios.xhtml");
        }
    }
    /**
     * Da de alta un usuario.
     * @param IdUsuario
     * @throws java.io.IOException
     */
    public void darDeAltaUsuario(int IdUsuario) throws IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        if(fAdmin.DarDeAltaUsuario(IdUsuario)== -1){
            context.addMessage("form_usuarios:btn_baja_"+IdUsuario, new FacesMessage(SEVERITY_ERROR, "No se pudo dar de alta el usuario", "No se pudo dar de alta el usuario" ));
            context.renderResponse();
        }else{
            context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/Views/Configuraciones/Usuarios.xhtml");
        }
    }
    
    public void comprobarNumeroNuevoOperario(){
        FacesContext context = FacesContext.getCurrentInstance();
        if(ListaUsuarios.containsKey(NumeroNuevoUsuario)){
            context.addMessage("form_usuarios:numero_nuevo_usuario", new FacesMessage(SEVERITY_ERROR, "El numero ingresado ya esta utilzado", "El numero ingresado ya esta utilzado" ));
            context.renderResponse();
        }
    }
    
    /**
     * Carga los atributos NombreCodificacion, DescripcionCodificacion e IdCodificacionSeleccionada segun el id especificado en la vista.
     * @param IdUsuario
     */
    public void cargarDatos(int IdUsuario){
        if(IdUsuario < 0 ){
            this.Nombre  = new String();
            this.Apellido  = new String();
            this.CorreoElectronico  = new String();
            this.RecibeAlertas = false;
            this.PermisoSeleccionado  = EnumPermiso.DATOS;
            this.IdUsuarioSeleccionado = -1;
            this.NumeroNuevoUsuario = 0;
            this.CambiarPassword = false;
            this.PasswordNuevo = new String();
            this.PasswordConfirmacion= new String();
            this.AreaSeleccionada = -1;
        }else{
            this.Nombre  = ListaUsuarios.get(IdUsuario).getNombre();
            this.Apellido  = ListaUsuarios.get(IdUsuario).getApellido();
            this.CorreoElectronico  = ListaUsuarios.get(IdUsuario).getCorreo();
            this.RecibeAlertas = ListaUsuarios.get(IdUsuario).isRecibeAlertas();
            this.PermisoSeleccionado  = ListaUsuarios.get(IdUsuario).getPermisoUsuario();
            this.IdUsuarioSeleccionado = IdUsuario;
            this.NumeroNuevoUsuario = ListaUsuarios.get(IdUsuario).getId();
            this.CambiarPassword = false;
            this.PasswordNuevo = new String();
            this.PasswordConfirmacion= new String();
            this.AreaSeleccionada = ListaUsuarios.get(IdUsuario).getAreaSectorUsuario().getId();
            
            Usuario usuario =  ListaUsuarios.get(IdUsuario);
            
            // verifica que no tenga registros relacionados
            // la lista de comprobaciones y de actividades deben estar vacias => False
            if(!usuario.getComprobaciones().isEmpty() || !usuario.getMedidasResponsableImplementacion().isEmpty()){
                ContieneRegistros = true;
            }else{
                ContieneRegistros = false;
            }
        }
    }
    
    
}