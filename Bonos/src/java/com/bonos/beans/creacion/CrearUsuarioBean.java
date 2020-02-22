package com.bonos.beans.creacion;

import com.bonos.entidades.Usuario;
import com.bonos.facade.RolesFacade;
import com.bonos.facade.UsuariosFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("crearUsuarioBean")
@ViewScoped
public class CrearUsuarioBean implements Serializable {

    private Usuario nuevoUsuario;

    @EJB
    RolesFacade rolesFacade;

    @EJB
    UsuariosFacade usuariosFacade;

    @PostConstruct
    public void init() {
        nuevoUsuario = new Usuario();
    }

    /**
     * @return the nuevoUsuario
     */
    public Usuario getNuevoUsuario() {
        return nuevoUsuario;
    }

    /**
     * @param nuevoUsuario the nuevoUsuario to set
     */
    public void setNuevoUsuario(Usuario nuevoUsuario) {
        this.nuevoUsuario = nuevoUsuario;
    }

}
