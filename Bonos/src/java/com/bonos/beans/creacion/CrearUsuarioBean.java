package com.bonos.beans.creacion;

import com.bonos.entidades.Roles;
import com.bonos.entidades.Usuario;
import com.bonos.facade.RolesFacade;
import com.bonos.facade.UsuariosFacade;
import java.io.Serializable;
import java.util.List;
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

    private List<Roles> roles;

    private Roles rolSeleccionado;

    @PostConstruct
    public void init() {
        nuevoUsuario = new Usuario();
        roles = rolesFacade.obtenerRoles();
        if (roles != null && !roles.isEmpty()) {
            rolSeleccionado = roles.get(0);
        }
    }

    public void guardarUsuario() {
        nuevoUsuario.setId_rol(rolSeleccionado);
        if (rolSeleccionado == null) {
            System.out.println("NULO ROL");
            utilidad.Utilidades.imprimir_msg("Aviso", "Seleccione un rol");
        } else {
            usuariosFacade.guardarUsuario(nuevoUsuario);
            nuevoUsuario = new Usuario();
        }

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

    /**
     * @return the roles
     */
    public List<Roles> getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    /**
     * @return the rolSeleccionado
     */
    public Roles getRolSeleccionado() {
        return rolSeleccionado;
    }

    /**
     * @param rolSeleccionado the rolSeleccionado to set
     */
    public void setRolSeleccionado(Roles rolSeleccionado) {
        this.rolSeleccionado = rolSeleccionado;
    }

}
