/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.dperez.maym.web.usuarios;

import com.dperez.maymweb.empresa.Empresa;
import com.dperez.maymweb.facades.FacadeAdministrador;
import com.dperez.maymweb.facades.FacadeLectura;
import com.dperez.maymweb.facades.FacadeMain;
import com.dperez.maymweb.usuario.Usuario;
import com.dperez.maymweb.usuario.permiso.EnumPermiso;
import java.io.Serializable;
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
    
    private Empresa EmpresaLogueada;
    
    private int IdUsuarioSeleccionado;
    
    private boolean ContieneRegistros;
    
    private int NumeroNuevoUsuario;
    private String Nombre;
    private String Apellido;
    private String Nickname;
    private String Password;
    private String PasswordConfirmacion;
    private String PasswordNuevo;
    private String CorreoElectronico;
    private boolean RecibeAlertas;
    
    private EnumPermiso[] PermisosUsuario;
    private EnumPermiso PermisoSeleccionado;
    
    private Map<Integer, Usuario> ListaUsuarios;
    
    private boolean CambiarPassword;
    
    //  Getters
    public Empresa getEmpresaLogueada() {return EmpresaLogueada;}
    public boolean isContieneRegistros() {return ContieneRegistros;}
    public String getCorreoElectronico() {return CorreoElectronico;}
    public int getNumeroNuevoUsuario() {return NumeroNuevoUsuario;}    
    public String getNombre() {return this.Nombre;}
    public String getApellido(){return this.Apellido;}
    public String getNickname(){return this.Nickname;}
    public String getPassword(){return this.Password;}
    public String getPasswordConfirmacion(){return this.PasswordConfirmacion;}
    public String getPasswordNuevo(){return this.PasswordNuevo;}
    public boolean isRecibeAlertas(){return this.RecibeAlertas;}
    
    public EnumPermiso getPermisoSeleccionado(){return this.PermisoSeleccionado;}
    public EnumPermiso[] getPermisosUsuario(){return this.PermisosUsuario;}
    
    public Map<Integer, Usuario> getListaUsuarios() {return this.ListaUsuarios;}
    
    public boolean isCambiarPassword() {return CambiarPassword;}
    
    public void setEmpresaLogueada(Empresa EmpresaLogueada) {this.EmpresaLogueada = EmpresaLogueada;}

    //  Setters
    public void setContieneRegistros(boolean ContieneRegistros) {this.ContieneRegistros = ContieneRegistros;}
    public void setNumeroNuevoUsuario(int NumeroNuevoUsuario) {this.NumeroNuevoUsuario = NumeroNuevoUsuario;}    
    public void setNombre(String Nombre) {this.Nombre = Nombre;}
    public void setApellido(String Apellido){this.Apellido = Apellido;}
    public void setNickname(String Nickname){this.Nickname = Nickname;}
    public void setPassword(String Password){this.Password = Password;}
    public void setPasswordConfirmacion(String PasswordConfirmacion){this.PasswordConfirmacion = PasswordConfirmacion;}
    public void setPasswordNuevo(String PasswordNuevo){this.PasswordNuevo = PasswordNuevo;}
    public void setCorreoElectronico(String CorreoElectronico){this.CorreoElectronico = CorreoElectronico;}
    public void setRecibeAlertas(boolean RecibeAlertas){this.RecibeAlertas = RecibeAlertas;}
    
    public void setPermisoSeleccionado(EnumPermiso PermisoSeleccionado){this.PermisoSeleccionado = PermisoSeleccionado;}
    public void setPermisosUsuario(EnumPermiso[] PermisosUsuario){this.PermisosUsuario = PermisosUsuario;}
    
    public void setListaUsuarios(Map<Integer, Usuario> ListaUsuarios) {this.ListaUsuarios = ListaUsuarios;}
    
    public void setCambiarPassword(boolean CambiarPassword) {this.CambiarPassword = CambiarPassword;}
    
    //  Metodos
    
    /**
     * Carga de propiedades al inicio
     */
    @PostConstruct
    public void init(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        EmpresaLogueada = (Empresa)request.getSession().getAttribute("Empresa");
        
        //  Usuarios
        ListaUsuarios = new HashMap<>();
        List<Usuario> tmpUsuarios = fLectura.GetUsuarios();
        for(Usuario usuario:tmpUsuarios){
            ListaUsuarios.put(usuario.getId(), usuario);
        }
    }
    
    /**
     * Crea nuevo usuario con el permiso seleccionado.
     * Si no se crea se muestra un mensaje
     */
    public void nuevoUsuario(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Empresa empresa = (Empresa)request.getSession().getAttribute("Empresa");
        // Crear nuevo usuario y actualizar lista
        Usuario usuario;
        if((usuario = fAdmin.NuevoUsuario(Nickname,Nombre,Apellido,CorreoElectronico, Password,PermisoSeleccionado, empresa.getId()))!=null){
            ListaUsuarios.put(usuario.getId(), usuario);
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_FATAL, "No se pudo crear nuevo usuario", "No se pudo crear nuevo usuario" ));
            FacesContext.getCurrentInstance().renderResponse();
        }
    }
    
    /**
     * Actualiza el usuario con lo datos ingresados.
     * Muestra un mensaje de errror si no se actualizo.
     * Agrega los cambios a la lista del bean.
     * @param IdUsuarioSeleccionado
     */
    public void editarUsuario(){
        if((IdUsuarioSeleccionado =fMain.CambiarDatosUsuario(IdUsuarioSeleccionado, Nickname, Nombre, Apellido, CorreoElectronico, PermisoSeleccionado, RecibeAlertas))!=-1){
            ListaUsuarios.put(IdUsuarioSeleccionado, fLectura.GetUsuario(IdUsuarioSeleccionado));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_FATAL, "No se pudo editar usuario", "No se pudo editar usuario" ));
            FacesContext.getCurrentInstance().renderResponse();
        }
    }
    
    /**
     * Cambia las credenciales del usuario.
     * Muestra los mensajes correspondientes por cada error/informacion.
     * Agrega los cambios a la lista del bean.
     * @param IdUsuarioSeleccionado
     */
    public void cambiarPassword(int IdUsuarioSeleccionado){
        if(Password.equals(PasswordNuevo)){
            if(!PasswordNuevo.isEmpty()){
                if(fMain.CambiarCredencialUsuario(IdUsuarioSeleccionado, Password, PasswordNuevo)!=null){
                    ListaUsuarios.put(IdUsuarioSeleccionado, fLectura.GetUsuario(IdUsuarioSeleccionado));
                }else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_FATAL, "No se pudo cambiar password", "No se pudo cambiar password" ));
                    FacesContext.getCurrentInstance().renderResponse();
                }
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_FATAL, "El nuevo password esta vacio", "El nuevo password esta vacio" ));
                FacesContext.getCurrentInstance().renderResponse();
            }
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_FATAL, "Los passwords no coniciden", "Los passwords no coniciden" ));
            FacesContext.getCurrentInstance().renderResponse();
        }
    }
    
    /**
     * Eliminina el usuario indicado.
     * Muestra un mensaje de error si no se pudo eliminar.
     * Remueve la deteccion de la lista del bean.
     * @param IdUsuarioSeleccionado
     */
    public void eliminarUsuario(int IdUsuarioSeleccionado){
        if(fAdmin.EliminarUsuario(IdUsuarioSeleccionado)!=-1){
            ListaUsuarios.remove(IdUsuarioSeleccionado);
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SEVERITY_ERROR, "No se pudo eliminar el usuario", "No se pudo eliminar el usuario" ));
            FacesContext.getCurrentInstance().renderResponse();
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
            this.Nickname  = new String();
            this.CorreoElectronico  = new String();
            this.RecibeAlertas = false;
            this.PermisoSeleccionado  = EnumPermiso.LECTURA;
            this.IdUsuarioSeleccionado = -1;
            this.NumeroNuevoUsuario = 0;
            this.CambiarPassword = false;
        }else{
            this.Nombre  = ListaUsuarios.get(IdUsuario).getNombre();
            this.Apellido  = ListaUsuarios.get(IdUsuario).getApellido();
            this.Nickname  = ListaUsuarios.get(IdUsuario).getNickName();
            this.CorreoElectronico  = ListaUsuarios.get(IdUsuario).getCorreo();
            this.RecibeAlertas = ListaUsuarios.get(IdUsuario).isRecibeAlertas();
            this.PermisoSeleccionado  = ListaUsuarios.get(IdUsuario).getPermisoUsuario();
            this.IdUsuarioSeleccionado = IdUsuario;
            this.NumeroNuevoUsuario = ListaUsuarios.get(IdUsuario).getId();
            this.CambiarPassword = false;
            
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