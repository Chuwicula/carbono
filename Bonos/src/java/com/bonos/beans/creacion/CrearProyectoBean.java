package com.bonos.beans.creacion;

import com.bonos.entidades.Proyecto;
import com.bonos.entidades.Usuario;
import com.bonos.facade.ProyectosFacade;
import com.bonos.facade.UsuariosFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("crearProyectoBean")
@ViewScoped
public class CrearProyectoBean implements Serializable {

    private Proyecto nuevoProyecto;

    private Usuario usuarioSelect;

    private List<Usuario> usuarios;

    @EJB
    UsuariosFacade usuariosFacade;

    @EJB
    ProyectosFacade proyectosFacade;

    @PostConstruct
    public void init() {
        nuevoProyecto = new Proyecto();
        usuarios = usuariosFacade.obtenerUsuarios();
        if (usuarios != null && !usuarios.isEmpty()) {
            usuarioSelect = usuarios.get(0);
        }
    }

    public void guardarProyecto() {
        nuevoProyecto.setId_usuario(usuarioSelect);
        proyectosFacade.guardarProyecto(nuevoProyecto);
    }

    /**
     * @return the nuevoProyecto
     */
    public Proyecto getNuevoProyecto() {
        return nuevoProyecto;
    }

    /**
     * @param nuevoProyecto the nuevoProyecto to set
     */
    public void setNuevoProyecto(Proyecto nuevoProyecto) {
        this.nuevoProyecto = nuevoProyecto;
    }

    /**
     * @return the usuarioSelect
     */
    public Usuario getUsuarioSelect() {
        return usuarioSelect;
    }

    /**
     * @param usuarioSelect the usuarioSelect to set
     */
    public void setUsuarioSelect(Usuario usuarioSelect) {
        this.usuarioSelect = usuarioSelect;
    }

    /**
     * @return the usuarios
     */
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
