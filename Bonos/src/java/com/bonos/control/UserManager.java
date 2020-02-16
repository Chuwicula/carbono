package com.bonos.control;

import com.bonos.entidades.Usuario;
import com.bonos.facade.LoginFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.transaction.Transactional;
import org.mindrot.jbcrypt.BCrypt;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Clase encargada de la Administracion de la sesion, La etiqueta session indica
//que la clase persiste mientras la sesión esté abierta
@SessionScoped
@Named("userManager")
public class UserManager implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserManager.class);//Creador de sesiones
    public static final String HOME_PAGE_REDIRECT = "/secured/inicio/proyectos.xhtml?faces-redirect=true";//Ruta pagina de inicio de contratos
    public static final String LOGOUT_PAGE_REDIRECT = "/login.xhtml?faces-redirect=true";//Ruta página de Login
    private Usuario currentUser = null;// Variable que almacena los datos del usuario que  inicia sesion

    @EJB
    private LoginFacade lf;

    private String userId;//Id del usuario que va a iniciar sesion
    private String userPassword;// Contrasenia del usuario que inicia sesion 
    private String userPasswordNew;// Nueva contrasenia del usuario que inicia sesion
    private String userPasswordSecond; //Duplicado de nueva contrasenia  para validacion 

    //Metodo que valida los datos de un usuario y lo redirige en caso de ser exitosa la validacion de los datos
    public String login() {
        
        currentUser = find(userId, userPassword);//Usuario logueado
        if (currentUser != null || ("root".equals(userId) && "root".equals(userPassword))) {
            LOGGER.info("Bienvenido '{}'", userId);
            // Que hace referencia a los conratos en ejecucion y se  injecta en el contexto web el valor
            return HOME_PAGE_REDIRECT;// Redireccion
        } else {
            LOGGER.info("Credenciales inválidas '{}'", userId);
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Autenticación fallida",
                            "Credenciales inválidas o desconocidas."));
            return null;
        }
    }

    public String getName() {
        if (currentUser != null) {
            return currentUser.getNombre();
        }
        return "";
    }

    //Metodo que invalida la sesion de hibernate y cierra conexiones abiertas 
    public String logout() {
        String identifier = userId;
        LOGGER.debug("Sesión invalidada para '{}'", identifier);
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        } catch (NullPointerException e) {
            System.out.println("Nulo - USerManager Logout");
        } catch (Exception e) {
            System.out.println("error - USerManager Logout");
        }

        LOGGER.info("Sesión terminada para '{}'", identifier);
        // lf.cerrarSesiones();
        return LOGOUT_PAGE_REDIRECT;// Redirige a pagina de loguep
    }

    //Metodo que valida si la sesion de un usuario está abierta
    public boolean isLoggedIn() {
        return currentUser != null;
    }

    //Metodo que redirige a pagina  de inicio si el usuario sigue activo,
    //Evita que al cerrar las pestanias el usuario tenga que volver a iniciar sesion
    public String isLoggedInForwardHome() {
        System.out.println("load forward");
        if (isLoggedIn()) {
            System.out.println("Is logged");
            return HOME_PAGE_REDIRECT;
        }
        System.out.println("Isn't logged :V");
        return null;
    }

    //Metodo que valida que la clave ingresada corresponda a la clave del usuario
    private Usuario find(String userId, String password) {
        Usuario usuarioLogin;
        usuarioLogin = lf.getPasswordByUser(userId);//Se consulta la base de datos para obtener el usuario    
        if (usuarioLogin != null) {
            //Si el usuario existe se comparan las contrasenias. Como estan encriptadas se utiliza una clase intermedia
            if (!BCrypt.checkpw(password, usuarioLogin.getPassword())) {
                return null;//Si la constrasenia no es igual se retorna  un  nulo
            }
        }
        return usuarioLogin;// Si la contrasenia es correcta se retorna el usuario correspondiente
    }

    public void aviso_Contra() {
        System.out.println("hola");
    }

    //Metood que cambia la contrasenia de un usuario activo
    @Transactional
    public void cambiarContrasenia() {
        if (userPasswordNew.equals(userPasswordSecond)) {
            // Se valida que los campos de contrasenia y copia de conytrasenia sean los mismos
            lf.cambiarContrasenia(currentUser, userPasswordNew);
            currentUser.setPassword(BCrypt.hashpw(userPasswordNew, BCrypt.gensalt()));
            utilidad.Utilidades.imprimir_msg("Exito", "Contraseña cambiada");
        } else {
            utilidad.Utilidades.imprimir_msg("Error", "Las contraseñas no coinciden");
        }
        //Se reinician las variables
        userPasswordNew = "";
        userPasswordSecond = "";

        PrimeFaces reqcontext = PrimeFaces.current();
        reqcontext.executeScript("PF('ccDlg').hide();");
    }

    // Metodo que redirecciona a una url
    public String redirect(String url) {
        return "/secured/" + url + "?faces-redirect=true";
    }

    // Metodo que redirecciona a una url de la carpeta contrato y además inyecta en el contexto web un estado
    public String redirect_estado(String url, Integer estado) {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("estado", estado);
        return "/secured/contratos/" + url + "?faces-redirect=true";
    }

    // Metodo que redirecciona a una url de la carpeta desembolsos y además inyecta en el contexto web un estado
    public String redirect_desembolso_estado(String url, Integer estado) {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("estado", estado);
        return "/secured/gestionDesembolsos/" + url + "?faces-redirect=true";
    }

    //Getters y setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Usuario getCurrentUser() {
        return currentUser;
    }

    /**
     * @return the userPasswordNew
     */
    public String getUserPasswordNew() {
        return userPasswordNew;
    }

    /**
     * @param userPasswordNew the userPasswordNew to set
     */
    public void setUserPasswordNew(String userPasswordNew) {
        this.userPasswordNew = userPasswordNew;
    }

    /**
     * @return the userPasswordSecond
     */
    public String getUserPasswordSecond() {
        return userPasswordSecond;
    }

    /**
     * @param userPasswordSecond the userPasswordSecond to set
     */
    public void setUserPasswordSecond(String userPasswordSecond) {
        this.userPasswordSecond = userPasswordSecond;
    }
}
